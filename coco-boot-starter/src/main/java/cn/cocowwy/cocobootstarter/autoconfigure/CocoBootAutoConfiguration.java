package cn.cocowwy.cocobootstarter.autoconfigure;

import cn.cocowwy.cocobootstarter.impl.AfterRunnerDoImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/9
 */
@Configuration(proxyBeanMethods = false)
//@ConditionalOnProperty(value = "coco.boot.enable", matchIfMissing = true, havingValue = "true")
@ComponentScan(basePackages = {"cn.cocowwy.cocobootstarter.holder"})
public class CocoBootAutoConfiguration {
    @Bean
    public AfterRunnerDoImpl afterRunnerDoPlus() {
        return new AfterRunnerDoImpl();
    }
}
