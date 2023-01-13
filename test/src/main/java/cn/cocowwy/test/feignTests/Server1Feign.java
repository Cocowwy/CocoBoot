package cn.cocowwy.test.feignTests;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/13
 */
@FeignClient(value = "server1")
interface Server1Feign {

    @GetMapping("/")
    String getFromServer1();

}