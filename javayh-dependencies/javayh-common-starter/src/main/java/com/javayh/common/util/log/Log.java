package com.javayh.common.util.log;

import com.javayh.common.util.servlet.RequestUtils;
import com.javayh.common.util.spring.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 *
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-04-01 21:53
 */
@Slf4j
public class Log {

	private static final String PREFIX = "Java有货---";

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
		CompletableFuture.runAsync(() -> {
			String requestUri = request.getRequestURI();
			log.error("异常  method --- {}", request.getMethod());
			log.error("异常 requestURI --- {}", requestUri);
			for (StackTraceElement stackTraceElement : stackTrace) {
				log.error(PREFIX + pr + "{}", "---错误详细信息 : " + stackTraceElement);
			}
		}, taskExecutor);
	}

}
