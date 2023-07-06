package cn.cocowwy.cocobootmbstarter.interceptor.privacy;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/3/8
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(value = "coco.mb.privacy", matchIfMissing = false, havingValue = "true")
public class PrivacyInterceptorAutoConfiguration {

    /**
     * 解密拦截器
     */
    @Bean
    public PrivacyDecryptionInterceptor privacyDecryptionInterceptor() {
        return new PrivacyDecryptionInterceptor();
    }

    /**
     * 加密拦截器
     */
    @Bean
    public PrivacyEncryptionInterceptor privacyEncryptionInterceptor() {
        return new PrivacyEncryptionInterceptor();
    }

    /**
     * 设置默认的加密解密算法
     */
    @Bean
    @ConditionalOnMissingBean(EncryptionDecryption.class)
    public DefaultEncryptionDecryption defaultEncryptionDecryption() {
        return new DefaultEncryptionDecryption();
    }

}
