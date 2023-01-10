package cn.cocowwy.cocobootstarter.autoconfigure;

import cn.cocowwy.cocobootstarter.impl.AfterRunnerDoImpl;
import cn.cocowwy.cocobootstarter.prop.CocoBootProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/9
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({CocoBootProperties.class})
@ConditionalOnProperty(value = "coco.boot.enable", matchIfMissing = true, havingValue = "true")
@ComponentScan(basePackages = {
        "cn.cocowwy.cocobootstarter.holder",
})
public class CocoBootAutoConfiguration {
    @Bean
    public AfterRunnerDoImpl afterRunnerDoPlus() {
        return new AfterRunnerDoImpl();
    }
}
