package cn.cocowwy.cocobootopenfeginstarter.autoconfigure;

import cn.cocowwy.cocobootopenfeginstarter.autoconfigure.ribbon.DevRouteRule;
import cn.cocowwy.cocobootopenfeginstarter.prop.DevOpenFeignProperties;
import com.netflix.loadbalancer.PredicateBasedRule;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.ribbon.RibbonClientConfiguration;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/9
 */
@Configuration
@EnableConfigurationProperties(DevOpenFeignProperties.class)
@AutoConfigureBefore(RibbonClientConfiguration.class)
public class CocoOpenFeignAutoConfiguration {

    /**
     * 便于Dev环境使用的路由规则 {@link DevRouteRule}
     * @param devOpenFeignProperties DevOpenFeignProperties
     * @return PredicateBasedRule
     */
    @Bean
    @ConditionalOnProperty(value = "coco.openfeign.dev.enable", matchIfMissing = true, havingValue = "true")
    @ConditionalOnMissingBean
    public PredicateBasedRule localHostAwareRule(DevOpenFeignProperties devOpenFeignProperties) {
        return new DevRouteRule(devOpenFeignProperties);
    }
}
