package cn.cocowwy.cocobootstarter.holder;

import cn.cocowwy.cocobootstarter.annotation.AfterRunnerDo;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 指定Bean方法的持有者
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/8
 */
public class BeanMethodHolder implements SmartInitializingSingleton {

    private static List<AfterRunnerDoMethodWrapper> AFTER_RUNNER_DO_METHOD_HOLDER = null;

    public static List<AfterRunnerDoMethodWrapper> afterRunnerDoList() {
        if (CollectionUtils.isEmpty(AFTER_RUNNER_DO_METHOD_HOLDER)) {
            return Collections.emptyList();
        }
        return AFTER_RUNNER_DO_METHOD_HOLDER;
    }

    @Override
    public void afterSingletonsInstantiated() {
        ApplicationContext applicationContext = SpringHolder.getApplicationContext();
        String[] beanNames = applicationContext.getBeanNamesForType(Object.class, false, true);

        for (String beanName : beanNames) {
            Object bean = applicationContext.getBean(beanName);

            Map<Method, AfterRunnerDo> afterRunnerDoMethodsMap = MethodIntrospector
                    .selectMethods(bean.getClass(),
                            (MethodIntrospector.MetadataLookup<AfterRunnerDo>) method -> AnnotatedElementUtils.findMergedAnnotation(method, AfterRunnerDo.class));

            if (CollectionUtils.isEmpty(afterRunnerDoMethodsMap)) {
                continue;
            }

            for (Map.Entry<Method, AfterRunnerDo> entry : afterRunnerDoMethodsMap.entrySet()) {
                AfterRunnerDoMethodWrapper afterRunnerDoMethodWrapper = AfterRunnerDoMethodWrapper.builderWrapper(bean, entry.getKey(), entry.getValue());
                if (AFTER_RUNNER_DO_METHOD_HOLDER == null) {
                    AFTER_RUNNER_DO_METHOD_HOLDER = new ArrayList<>(1);
                }
                AFTER_RUNNER_DO_METHOD_HOLDER.add(afterRunnerDoMethodWrapper);
            }
        }

    }

    /**
     * 注解方法 {@link AfterRunnerDo} 执行的包装类
     */
    public static class AfterRunnerDoMethodWrapper extends BeanMethodHolder.MethodWrapper {
        private AfterRunnerDo afterRunnerDo;

        private static AfterRunnerDoMethodWrapper builderWrapper(Object bean, Method method, AfterRunnerDo afterRunnerDo) {
            AfterRunnerDoMethodWrapper afterRunnerDoMethodWrapper = new AfterRunnerDoMethodWrapper();
            afterRunnerDoMethodWrapper.setAfterRunnerDo(afterRunnerDo);
            afterRunnerDoMethodWrapper.setMethod(method);
            afterRunnerDoMethodWrapper.setTarget(bean);
            return afterRunnerDoMethodWrapper;
        }

        public Object getTarget() {
            return target;
        }

        public Method getMethod() {
            return method;
        }

        public AfterRunnerDo getAfterRunnerDo() {
            return afterRunnerDo;
        }

        public void setAfterRunnerDo(AfterRunnerDo afterRunnerDo) {
            this.afterRunnerDo = afterRunnerDo;
        }
    }

    /**
     * 方法包装基类
     */
    private abstract static class MethodWrapper {
        /**
         * Bean
         */
        protected Object target;
        /**
         * 方法
         */
        protected Method method;

        protected void setTarget(Object target) {
            this.target = target;
        }

        protected void setMethod(Method method) {
            this.method = method;
        }
    }
}
