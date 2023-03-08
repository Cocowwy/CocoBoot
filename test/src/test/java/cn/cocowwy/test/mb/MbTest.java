package cn.cocowwy.test.mb;

import cn.cocowwy.test.TestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/3/8
 */
@SpringBootTest(classes = TestApplication.class)
public class MbTest {

    @Autowired
    private NumberMapper numberMapper;

    @Test
    void contextLoads() {
        Number number = new Number();
        number.setRemark("aaaaa");
        numberMapper.insert(number);
        System.out.println(numberMapper.selectById(number.getId()));
    }


}
