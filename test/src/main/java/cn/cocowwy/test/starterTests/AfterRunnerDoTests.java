package cn.cocowwy.test.starterTests;

import cn.cocowwy.cocobootstarter.annotation.AfterRunnerDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/9
 */
@Service
@Slf4j
public class AfterRunnerDoTests {

    @AfterRunnerDo
    public void testAfterRunnerDoMethod(String[] args) {
        log.info("testAfterRunnerDoMethod do");
    }

    @AfterRunnerDo(blockOnError = false, sort = 100)
//    @AfterRunnerDo(blockOnError = true)
    public void testAfterRunnerDoMethodOnError(String[] args) {
        throw new RuntimeException("testAfterRunnerDoMethodOnError");
    }

    @AfterRunnerDo(sort = 100)
    public void testSort100(String[] args) {
        log.info("testSort100 do");
    }

    @AfterRunnerDo(sort = 10)
    public void testSort10(String[] args) {
        log.info("testSort10 do");
    }
}
