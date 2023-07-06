package cn.cocowwy.cocobootmbstarter.interceptor.print;

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

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/7/6
 */
@Intercepts({
		@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class,
				ResultHandler.class}),
		@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class,
				ResultHandler.class, CacheKey.class, BoundSql.class}),
})
public class PrintInterceptor implements Interceptor {
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		return null;
	}
}
