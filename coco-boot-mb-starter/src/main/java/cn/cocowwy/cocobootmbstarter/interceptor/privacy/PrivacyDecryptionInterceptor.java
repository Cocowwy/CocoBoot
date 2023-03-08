package cn.cocowwy.cocobootmbstarter.interceptor.privacy;

import cn.cocowwy.common.util.AnnotatedUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 解密 拦截器
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/3/7
 */
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class,
                ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class,
                ResultHandler.class, CacheKey.class, BoundSql.class}),
})
public class PrivacyDecryptionInterceptor implements Interceptor {

    @Autowired
    private EncryptionDecryption encryptionDecryption;

    public PrivacyDecryptionInterceptor(EncryptionDecryption encryptionDecryption) {
        this.encryptionDecryption = encryptionDecryption;
    }

    public PrivacyDecryptionInterceptor() {
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (encryptionDecryption == null) {
            encryptionDecryption = new DefaultEncryptionDecryption();
        }
        Object result = invocation.proceed();

        if (result instanceof ArrayList) {
            ArrayList list = (ArrayList) result;
            if (list.size() == 0) {
                return result;
            }

            if (AnnotatedUtils.isAnnotatedWith(list.get(0), Privacy.class)) {
                list.forEach(this::decryptFields);
                return result;
            }
        }

        if (AnnotatedUtils.isAnnotatedWith(result, Privacy.class)) {
            decryptFields(result);
        }

        return result;
    }

    private void decryptFields(Object obj) {
        List<Field> fieldList = AnnotatedUtils.getAllFields(obj, Boolean.TRUE);

        fieldList.forEach(it -> {
            if (AnnotatedUtils.isAnnotatedWith(it, Privacy.class)) {
                try {
                    it.setAccessible(true);
                    Object value = it.get(obj);
                    if (null != value) {
                        String valueString = value.toString();
                        if (!StringUtils.isEmpty(valueString)) {
                            try {
                                it.set(obj, encryptionDecryption.decryption(valueString));
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                } catch (IllegalAccessException ignored) {
                } finally {
                    it.setAccessible(false);
                }
            }
        });
    }

}
