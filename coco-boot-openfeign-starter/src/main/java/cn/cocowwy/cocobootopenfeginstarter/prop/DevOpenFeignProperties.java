package cn.cocowwy.cocobootopenfeginstarter.prop;

import cn.cocowwy.cocobootopenfeginstarter.autoconfigure.ribbon.DevRouteRule;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;


/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/9
 */
@ConfigurationProperties(prefix = "coco.openfeign.dev")
public class DevOpenFeignProperties {
    /**
     * 是否开启 {@link DevRouteRule} Ribbon策略
     */
    private boolean enable = false;

    /**
     * 路由地址
     * 请遵循：key为 服务名 value为 host:port
     */
    private Map<String, String> route;


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Map<String, String> getRoute() {
        return route;
    }

    public void setRoute(Map<String, String> route) {
        this.route = route;
    }
}
