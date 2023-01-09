package cn.cocowwy.common.base.rpc;

import java.io.Serializable;


/**
 * 统一RPC响应体
 *
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/6
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    private String msg;
    private T data;

    public Result() {
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return success("操作成功", data);
    }

    public static <T> Result<T> successMsg(String msg) {
        return success(msg, null);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result(200, msg, data);
    }

    public static <T> Result<T> error(String msg) {
        return error(4000, msg);
    }

    public static <T> Result<T> error(int code, String message) {
        return new Result(code, message, (Object) null);
    }

    public boolean isOk() {
        return this.code == 200;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
