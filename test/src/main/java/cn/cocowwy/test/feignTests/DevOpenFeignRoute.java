package cn.cocowwy.test.feignTests;

import cn.cocowwy.cocobootstarter.annotation.AfterRunnerDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/13
 */
@Component
public class DevOpenFeignRoute {

    @Autowired
    private Server1Feign server1Feign;

    @AfterRunnerDo
    public void testDevOpenFeign() {
        System.out.println(server1Feign.getFromServer1());
    }
}

