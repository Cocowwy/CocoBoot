package cn.cocowwy.common.exception;

/**
 * 业务异常
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/6
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
