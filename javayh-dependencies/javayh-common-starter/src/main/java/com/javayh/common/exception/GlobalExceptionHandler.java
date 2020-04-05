package com.javayh.common.exception;

import com.javayh.common.result.ResultData;
import com.javayh.common.util.log.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.List;

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
        Log.error("全局Base异常处理",e.getStackTrace());
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
    public ResultData exceptionHandler(Exception e) {
        Log.error("未知的运行异常",e.getStackTrace());
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
        Log.error("参数异常处理",exception.getStackTrace());
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        return  ResultData.fail(fieldErrors.get(0).getDefaultMessage());
    }

}
