package cn.cocowwy.cocobootstarter.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/10
 */
@ConfigurationProperties(prefix = "coco.boot")
public class CocoBootProperties {

    private Boolean enable = true;

    private Web web;

    private OpenFeign openFeign;

    private static class Web {
        private Boolean enable;

        public Boolean getEnable() {
            return enable;
        }

        public void setEnable(Boolean enable) {
            this.enable = enable;
        }
    }

    private static class OpenFeign {
        private Boolean enable;

        public Boolean getEnable() {
            return enable;
        }

        public void setEnable(Boolean enable) {
            this.enable = enable;
        }
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Web getWeb() {
        return web;
    }

    public void setWeb(Web web) {
        this.web = web;
    }

    public OpenFeign getOpenFeign() {
        return openFeign;
    }

    public void setOpenFeign(OpenFeign openFeign) {
        this.openFeign = openFeign;
    }
}
