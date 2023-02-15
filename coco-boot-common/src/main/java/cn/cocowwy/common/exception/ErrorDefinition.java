package cn.cocowwy.common.exception;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/2/15
 */
public final class ErrorDefinition {

    public static final Error UNDEFINED_EXCEPTION = new Error(1000, "未知异常");

    public static final Error BUSSINESS_EXCEPTION = new Error(1001, "业务异常");

}
