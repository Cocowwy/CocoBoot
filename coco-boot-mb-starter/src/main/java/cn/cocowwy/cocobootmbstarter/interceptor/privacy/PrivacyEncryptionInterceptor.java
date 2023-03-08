package cn.cocowwy.cocobootmbstarter.interceptor.privacy;

import cn.cocowwy.common.util.AesBase64Utils;
import cn.cocowwy.common.util.AnnotatedUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 加密 拦截器
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/3/7
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class PrivacyEncryptionInterceptor implements Interceptor {

    @Autowired
    private EncryptionDecryption encryptionDecryption;

    public PrivacyEncryptionInterceptor(EncryptionDecryption encryptionDecryption) {
        this.encryptionDecryption = encryptionDecryption;
    }

    public PrivacyEncryptionInterceptor() {
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (encryptionDecryption == null) {
            return new DefaultEncryptionDecryption();
        }

        MappedStatement statement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = invocation.getArgs()[1];
        SqlCommandType sqlCommandType = statement.getSqlCommandType();
        if (null == parameter) {
            return invocation.proceed();
        }

        if (SqlCommandType.INSERT == sqlCommandType && AnnotatedUtils.isAnnotatedWith(parameter, Privacy.class)) {
            encryptFields(parameter);
        }
        if (SqlCommandType.UPDATE == sqlCommandType) {
            if (parameter instanceof MapperMethod.ParamMap) {
                MapperMethod.ParamMap<?> paramMap = (MapperMethod.ParamMap<?>) parameter;
                if (paramMap.containsKey("et")) {
                    parameter = paramMap.get("et");
                } else {
                    parameter = paramMap.get("param1");
                }

                if (null == parameter) {
                    return invocation.proceed();
                }
            }
            if (AnnotatedUtils.isAnnotatedWith(parameter, Privacy.class)) {
                encryptFields(parameter);
            }
        }

        return invocation.proceed();
    }

    private void encryptFields(Object parameter) {
        List<Field> fieldList = AnnotatedUtils.getAllFields(parameter, Boolean.TRUE);

        fieldList.forEach(it -> {
            if (AnnotatedUtils.isAnnotatedWith(it, Privacy.class)) {
                try {
                    it.setAccessible(true);
                    Object value = it.get(parameter);
                    if (null != value) {
                        String valueString = value.toString();
                        if (!StringUtils.isEmpty(valueString)) {
                            it.set(parameter, encryptionDecryption.encryption(valueString));
                        }
                    }
                } catch (IllegalAccessException ignored) {
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    it.setAccessible(false);
                }
            }
        });
    }

}
