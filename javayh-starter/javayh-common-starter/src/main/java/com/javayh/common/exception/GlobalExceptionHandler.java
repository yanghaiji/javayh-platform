package com.javayh.common.exception;

import com.javayh.common.result.ResultData;
import com.javayh.common.util.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 * 全局异常
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-01 21:36
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String PREFIX = "Java有货---";
    @Autowired(required = false)
    private TaskExecutor taskExecutor;
    /**
     * <p>
     *       全局Base异常处理
     * </p>
     * @version 1.0.0
     * @author Dylan
     * @since 2020/2/27
     * @param e
     */
    @ExceptionHandler({BaseException.class})
    public ResultData customExceptionHandler(BaseException e) {
        sysLog("全局Base异常处理",e.getStackTrace());
        return ResultData.fail(e.getMessage());
    }

    /**
     * <p>
     *       其他类型的异常处理
     * </p>
     * @version 1.0.0
     * @author Dylan
     * @since 2020/2/27
     * @param e
     */
    @ExceptionHandler({Exception.class})
    public ResultData customExceptionHandler(Exception e) {
        sysLog("未知的运行异常",e.getStackTrace());
        return ResultData.fail();
    }

    /**
     * <p>
     *       参数异常处理
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/2/28
     * @param exception
     */
    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public ResultData methodNotValidHandler(MethodArgumentNotValidException exception) {
        sysLog("参数异常处理",exception.getStackTrace());
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        return  ResultData.fail(fieldErrors.get(0).getDefaultMessage());
    }

    /**
     * <p>
     *       统一日志输出
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/2/28
     * @param
     * @param stackTrace
     * @return void
     */
    private void sysLog(String pr, StackTraceElement[] stackTrace){
        HttpServletRequest request = RequestUtils.getRequest();
        CompletableFuture.runAsync(() -> {
            String requestUri = request.getRequestURI();
            log.error("异常  method --- {}",request.getMethod());
            log.error("异常 requestURI --- {}",requestUri);
            for (StackTraceElement stackTraceElement : stackTrace) {
                log.error(PREFIX + pr + "{}","---错误详细信息 : "+ stackTraceElement);
            }
        },taskExecutor);
    }

}
