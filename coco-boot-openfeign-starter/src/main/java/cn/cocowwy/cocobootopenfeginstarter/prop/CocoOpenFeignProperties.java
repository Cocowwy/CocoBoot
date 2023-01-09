package cn.cocowwy.cocobootopenfeginstarter.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/9
 */
@ConfigurationProperties(prefix = "coco.openfeign")
public class CocoOpenFeignProperties {

    private boolean routeLocal = false;

}
