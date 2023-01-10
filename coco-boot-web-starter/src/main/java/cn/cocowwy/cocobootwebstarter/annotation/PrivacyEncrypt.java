package cn.cocowwy.cocobootwebstarter.annotation;

import cn.cocowwy.cocobootwebstarter.annotation.enums.PrivacyTypeEnum;
import cn.cocowwy.cocobootwebstarter.annotation.serializer.PrivacySerializer;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 隐私加密
 * {@link PrivacySerializer} 序列化规则
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/10
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = PrivacySerializer.class)
public @interface PrivacyEncrypt {
    /**
     * 脱敏数据类型
     */
    PrivacyTypeEnum type();

    /**
     * 前置保留字段长，type = CUSTOMER 该值有效
     */
    int prefixNoHideLen() default 1;

    /**
     * 后置保留字段长， type = CUSTOMER 该值有效
     */
    int suffixNoHideLen() default 1;

    /**
     * 打码字符串，仅 PrivacyTypeEnum = CUSTOMER 时生效
     */
    String symbol() default "*";
}
