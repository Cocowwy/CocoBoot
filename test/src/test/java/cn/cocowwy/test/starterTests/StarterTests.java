package cn.cocowwy.test.starterTests;

import cn.cocowwy.cocobootstarter.annotation.AfterRunnerDo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/9
 */
@SpringBootTest
@Slf4j
public class StarterTests {

    @Test
    public void testAfterRunnerDo() {
        log.info("testAfterRunnerDo");
    }

}
