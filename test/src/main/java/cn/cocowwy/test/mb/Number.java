package cn.cocowwy.test.mb;

import cn.cocowwy.cocobootmbstarter.interceptor.privacy.Privacy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/3/7
 */
@Data
@Privacy
public class Number {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer num;
    @Privacy
    private String remark;
}
