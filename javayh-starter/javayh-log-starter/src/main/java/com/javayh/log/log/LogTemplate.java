package com.javayh.log.log;

/**
 * <p>
 *
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-15 17:04
 */
public abstract class LogTemplate {
    /**前缀*/
    protected static final String PREFIX = "Java有货";
    /**链接符*/
    protected static final String JOINER = "----->";

    /**
     * <p>
     *       自定义前缀输出
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/15
     * @param prefix
     * @param e
     * @return void
     */
    protected abstract void logError(String prefix, Exception e);

    /**
     * <p>
     *      默认输出
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/15
     * @param e
     * @return void
     */
    protected abstract void logError(Exception e);

    /**
     * <p>
     *     自定义前缀输出
     *     Exception : Throwable cause = e.getCause();
     *     但是 cause.printStackTrace(); 会报空指针
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/15
     * @param prefix
     * @param e
     * @return void
     */
    protected abstract void logError(String prefix, Throwable e);

    /**
     * <p>
     *       默认前缀输出
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/15
     * @param e
     * @return void
     */
    protected abstract void logError(Throwable e);

}
