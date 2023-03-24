package cn.cocowwy.common.exception;

/**
 * 业务异常
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/6
 */
public class BusinessException extends RuntimeException {

    private Error error = ErrorDefinition.UNDEFINED_EXCEPTION;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Error error) {
        super(error.getMsg());
    }

    public Error getError() {
        return error;
    }
}
