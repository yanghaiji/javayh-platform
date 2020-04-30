package com.javayh.common.util.log;

import com.javayh.common.constant.ConstantUtils;
import com.javayh.common.util.servlet.RequestUtils;
import com.javayh.common.util.spring.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 * 		Log记录
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-04-01 21:53
 */
@Slf4j
public class Log {

	@Autowired(required = false)
	private static TaskExecutor taskExecutor = SpringUtils.getBean("taskExecutor",
			TaskExecutor.class);

	/**
	 * <p>
	 * 统一日志输出
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/2/28
	 * @param
	 * @param stackTrace
	 * @return void
	 */
	public static void error(String pr, StackTraceElement[] stackTrace) {
		HttpServletRequest request = RequestUtils.getRequest();
		StringBuilder sb = new StringBuilder();
        String requestUri = request.getRequestURI();
        sb.append(ConstantUtils.NEWLINE).append(ConstantUtils.PREFIX).append(pr).append("异常 method -->").append(request.getMethod());
        sb.append(ConstantUtils.NEWLINE).append(ConstantUtils.PREFIX).append(pr).append("异常 requestURI -->").append(requestUri);
		CompletableFuture.runAsync(() -> {
			for (int depth = 1,count = 0; depth < stackTrace.length; ++depth) {
				sb.append(ConstantUtils.NEWLINE).append(ConstantUtils.PREFIX).append(pr).append(" -->").append(stackTrace[depth]);
				if (count == ConstantUtils.MAX_STACK_DEPTH) {
					break;
				}
				count ++;
			}
			log.error(sb.toString());
		}, taskExecutor);
	}

}
