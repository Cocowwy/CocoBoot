package cn.cocowwy.cocobootmbstarter.interceptor.explain;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;

import java.util.Properties;

/**
 * MySQL执行计划拦截器
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/3/7
 */
public class ExplainInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return null;
    }

    @Override
    public Object plugin(Object target) {
        return Interceptor.super.plugin(target);
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }
}
