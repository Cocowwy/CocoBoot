package cn.cocowwy.cocobootmbstarter.interceptor.privacy;

import java.lang.annotation.*;

/**
 * 标记实体类有字段在持久化到数据库时需要进行加密，从数据库读取数据时进行解密。
 * 1. 实体类和其中要进行加密的字段上都必须有该注解
 * 2. 数据库字段必须为字符串类型
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/3/8
 */
@Documented
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Privacy {
}
