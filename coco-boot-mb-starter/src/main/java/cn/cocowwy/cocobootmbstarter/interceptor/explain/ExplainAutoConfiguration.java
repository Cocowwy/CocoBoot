package cn.cocowwy.cocobootmbstarter.interceptor.explain;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/3/8
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(value = "coco.mb.explain", matchIfMissing = false, havingValue = "true")
public class ExplainAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(ExplainInterceptor.class)
    public ExplainInterceptor explainInterceptor() {
        return new ExplainInterceptor();
    }

}
