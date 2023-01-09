package cn.cocowwy.cocobootopenfeginstarter.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;

import static cn.cocowwy.cocobootopenfeginstarter.prop.CocoOpenFeignProperties.Rule.*;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/9
 */
@ConfigurationProperties(prefix = "coco.openfeign")
public class CocoOpenFeignProperties {

    private boolean enable = false;

    private Rule rule = DEFAULT;

    public enum Rule {
        /**
         * 本地优先路由
         * Local preference routing
         */
        PRIORITY_LOCAL,
        /**
         * 不走本地
         * Do not route local
         */
        EXCLUDE_LOCAL,
        /**
         * Ribbon默认规则
         */
        DEFAULT,
    }
}
