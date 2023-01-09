package cn.cocowwy.cocobootopenfeginstarter.autoconfigure;

import cn.cocowwy.cocobootopenfeginstarter.autoconfigure.ribbon.RouteLocalPriorityRule;
import cn.cocowwy.cocobootopenfeginstarter.prop.CocoOpenFeignProperties;
import com.netflix.loadbalancer.PredicateBasedRule;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.ribbon.RibbonClientConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/9
 */
@EnableConfigurationProperties(CocoOpenFeignProperties.class)
@AutoConfigureBefore(RibbonClientConfiguration.class)
@ConditionalOnProperty(value = "coco.openfeign.route-local", matchIfMissing = true, havingValue = "true")
public class CocoOpenFeignAutoConfiguration {

    /**
     * 优先路由本地服务
     * Route local services first
     * @param cocoOpenFeignProperties {@link CocoOpenFeignProperties}
     * @return {@link RouteLocalPriorityRule}
     */
    @Bean
    @ConditionalOnMissingBean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PredicateBasedRule localHostAwareRule(CocoOpenFeignProperties cocoOpenFeignProperties) {
        return new RouteLocalPriorityRule();
    }

}
