package cn.cocowwy.common.exception;

/**
 * 下游异常
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/6
 */
public class DownstreamException extends RuntimeException {
    public DownstreamException(String message) {
        super(message);
    }
}
