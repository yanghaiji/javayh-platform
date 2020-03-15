package com.javayh.log.log;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 堆信息详细输出
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-15 16:36
 */
@Slf4j
public class LogError extends LogTemplate {

    /**
     * <p>
     *       错误日志记录
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/15
     * @param prefix
     * @param e
     * @return void
     */
    @Override
    public void logError(String prefix, Exception e) {
        log(prefix ,e );
    }

    /**
     * <p>
     *       错误日志记录
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/15
     * @param e
     * @return void
     */
    @Override
    public void logError(Exception e) {
        log(e);
    }

    /***
     * <p>
     *       错误日志记录
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/15
     * @param prefix
     * @param e
     * @return void
     */
    @Override
    public void logError(String prefix, Throwable e) {
        log(prefix ,e );
    }

    /**
     * <p>
     *       错误日志记录
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/15
     * @param e
     * @return void
     */
    @Override
    public void logError(Throwable e) {
       log(e);
    }

    /**
     * <p>
     *       错误日志记录，内部输出
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/15
     * @param prefix
     * @param e
     * @return void
     */
    private void log(String prefix,Throwable e){
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            log.error(PREFIX + JOINER + "{}"," 错误详细信息 : "+ stackTraceElement.toString());
        }
    }

    /**
     * <p>
     *       错误日志记录，内部输出
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/15
     * @param e
     * @return void
     */
    private void log(Throwable e){
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            log.error(PREFIX + JOINER + "{}"," 错误详细信息 : "+ stackTraceElement.toString());
        }
    }


}
