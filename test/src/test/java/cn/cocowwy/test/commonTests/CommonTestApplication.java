package cn.cocowwy.test.commonTests;

import cn.cocowwy.cocobootmbstarter.interceptor.utils.AesBase64Utils;
import cn.cocowwy.common.util.PrivacyUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/10
 */
@SpringBootTest
@Slf4j
public class CommonTestApplication {

    @Test
    public void testPrivacy() {
        System.out.println(PrivacyUtils.hidePhone("11111111111"));
        System.out.println(PrivacyUtils.hidePhone("11111111111"));
        System.out.println(PrivacyUtils.hideEmail("abcdefg@qq.com"));
        System.out.println(PrivacyUtils.hideValue("Cocowwy is a good boy", 1, 1, "*"));
    }

    @Test
    public void test() throws Exception {
        String encrypt = AesBase64Utils.encrypt("123213");
        System.out.println(AesBase64Utils.decrypt(encrypt));
    }
}
