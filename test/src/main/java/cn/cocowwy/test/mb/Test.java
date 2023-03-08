package cn.cocowwy.test.mb;

import cn.cocowwy.cocobootstarter.annotation.AfterRunnerDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/3/8
 */
@Service
public class Test {
    @Autowired
    private NumberMapper numberMapper;

    @AfterRunnerDo
    public void contextLoads() {
        Number number = new Number();
        number.setRemark("aaaaa");
        numberMapper.insert(number);
        Number number1 = numberMapper.selectById(number.getId());
        System.out.println(number1);
    }

}
