package cn.cocowwy.cocobootstarter.holder;

import cn.cocowwy.cocobootstarter.annotation.AfterRunnerDo;
import cn.cocowwy.cocobootstarter.util.SpringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 指定Bean方法的持有者
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/8
 */
public class BeanMethodHolder implements SmartInitializingSingleton {

    private static final Log LOG = LogFactory.getLog(BeanMethodHolder.class);
    private static final ConcurrentMap<Method, AfterRunnerDo> AFTER_RUNNER_DO_METHOD_HOLDER = new ConcurrentHashMap<>(0);

    @Override
    public void afterSingletonsInstantiated() {
        ApplicationContext applicationContext = SpringUtil.getApplicationContext();
        String[] beanNames = applicationContext.getBeanNamesForType(Object.class, false, true);

        Map<Method, AfterRunnerDo> AfterRunnerDoMethodsMap = null;
        for (String beanName : beanNames) {
            Object bean = applicationContext.getBean(beanName);

            AfterRunnerDoMethodsMap = MethodIntrospector
                    .selectMethods(bean.getClass(), (MethodIntrospector.MetadataLookup<AfterRunnerDo>) method
                            -> AnnotatedElementUtils.findMergedAnnotation(method, AfterRunnerDo.class));
        }

        if (AfterRunnerDoMethodsMap == null || AfterRunnerDoMethodsMap.isEmpty()) {
            return;
        }

        // register
        for (Map.Entry<Method, AfterRunnerDo> entry : AfterRunnerDoMethodsMap.entrySet()) {
            Method method = entry.getKey();
            AfterRunnerDo afterRunnerDo = entry.getValue();
            if (afterRunnerDo.effect()) {
                method.setAccessible(true);
                AFTER_RUNNER_DO_METHOD_HOLDER.put(method, afterRunnerDo);
            }
        }
    }
}
