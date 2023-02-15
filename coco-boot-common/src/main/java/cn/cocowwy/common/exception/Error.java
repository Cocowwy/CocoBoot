package cn.cocowwy.common.exception;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/2/15
 */
public class Error {
    private Integer code;
    private String msg;

    public Error(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
