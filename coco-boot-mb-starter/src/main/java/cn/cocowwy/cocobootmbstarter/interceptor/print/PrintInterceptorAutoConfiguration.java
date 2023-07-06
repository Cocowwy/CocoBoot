package cn.cocowwy.cocobootmbstarter.interceptor.print;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/7/6
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(value = "coco.mb.print", matchIfMissing = false, havingValue = "true")
public class PrintInterceptorAutoConfiguration {

	@Bean
	public PrintInterceptor printInterceptor() {
		return new PrintInterceptor();
	}

}
