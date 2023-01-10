package cn.cocowwy.cocobootwebstarter.annotation.enums;

/**
 * 隐私类型枚举
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/10
 */
public enum PrivacyTypeEnum {
    /**
     * 用户自定义，需设置脱敏的范围
     */
    CUSTOMER,
    /**
     * 全部字符
     */
    ALL,
    /**
     * 邮箱脱敏
     */
    EMAIL,
    /**
     * 手机号脱敏
     */
    PHONE,
}
