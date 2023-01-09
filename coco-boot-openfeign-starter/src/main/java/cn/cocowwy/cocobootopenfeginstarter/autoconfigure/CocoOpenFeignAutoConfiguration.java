package cn.cocowwy.cocobootopenfeginstarter.autoconfigure;

import cn.cocowwy.cocobootopenfeginstarter.prop.CocoOpenFeignProperties;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.ribbon.RibbonClientConfiguration;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/9
 */
@EnableConfigurationProperties(CocoOpenFeignProperties.class)
@AutoConfigureBefore(RibbonClientConfiguration.class)
@ConditionalOnProperty(value = "coco.openfeign.enable", matchIfMissing = true, havingValue = "true")
public class CocoOpenFeignAutoConfiguration {

//    @Bean
//    @ConditionalOnMissingBean
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    public PredicateBasedRule localHostAwareRule() {
//        return new CocoAwareRule();
//    }

}
