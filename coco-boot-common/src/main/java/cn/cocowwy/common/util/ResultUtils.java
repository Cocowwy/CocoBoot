package cn.cocowwy.common.util;

import cn.cocowwy.common.base.rpc.Result;
import cn.cocowwy.common.exception.DownstreamException;

/**
 * 【工具】校验（RPC）结果返回值
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/6
 */
public final class ResultUtils {
    /**
     * 校验Result结果，若不符合预期，抛出异常
     * @param result result
     */
    public static <T> void check(Result<T> result) {
        if (!result.isOk()) {
            throw new DownstreamException(result.getMsg());
        }
    }

    /**
     * 校验Result结果，若不符合预期，返回默认值
     * @param result 结果
     * @param defaultData 默认值
     * @return data
     */
    public static <T> T defaultData(Result<T> result, T defaultData) {
        if (!result.isOk()) {
            return defaultData;
        }
        return result.getData();
    }
}
