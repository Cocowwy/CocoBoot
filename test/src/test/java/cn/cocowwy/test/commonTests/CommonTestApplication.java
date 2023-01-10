package cn.cocowwy.test.commonTests;

import cn.cocowwy.common.util.PrivacyUtil;
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
        System.out.println(PrivacyUtil.hidePhone("11111111111"));
        System.out.println(PrivacyUtil.hidePhone("11111111111"));
        System.out.println(PrivacyUtil.hideEmail("abcdefg@qq.com"));
        System.out.println(PrivacyUtil.hideValue("Cocowwy is a good boy", 1, 1, "*"));
    }
}
