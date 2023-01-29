package cn.cocowwy.cocobootwebstarter.annotation.advice;

import cn.cocowwy.common.base.rpc.Result;
import cn.cocowwy.common.exception.BusinessException;
import cn.cocowwy.common.exception.DownstreamException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/29
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理所有不可知的异常
     * @param e Exception
     * @return Result
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<Void> handleException(Exception e) {

        LOGGER.error(e.getMessage(), e);

        return Result.error("操作失败！");
    }

    /**
     * 处理所有业务异常
     * @param e BusinessException
     * @return Result
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result<Void> handleBusinessException(BusinessException e) {

        LOGGER.error(e.getMessage(), e);

        return Result.error(e.getMessage());
    }

    /**
     * 处理所有业务异常
     * @param e BusinessException
     * @return Result
     */
    @ExceptionHandler(DownstreamException.class)
    @ResponseBody
    public Result<Void> handleDownstreamException(DownstreamException e) {

        LOGGER.error(e.getMessage(), e);

        return Result.error("下游报错：" + e.getMessage());
    }
}
