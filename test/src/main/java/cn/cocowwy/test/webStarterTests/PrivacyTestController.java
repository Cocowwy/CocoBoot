package cn.cocowwy.test.webStarterTests;

import cn.cocowwy.common.base.rpc.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/10
 */
@RestController
@RequestMapping("/coco/privacy")
public class PrivacyTestController {

    /**
     * curl localhost:8080/coco/privacy/test
     */
    @GetMapping("/test")
    Result<Person> test() {
        Person person = new Person();
        person.setName("Cocowwy");
        person.setEmail("abcdefg@qq.com");
        person.setMobile("11111111111");
        return Result.success(person);
    }
}