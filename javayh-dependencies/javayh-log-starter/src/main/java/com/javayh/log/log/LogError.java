/**
 *   Copyright [2020] [Yang Hai Ji of copyright owner]
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.javayh.log.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import java.util.concurrent.CompletableFuture;

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

	@Autowired(required = false)
	private TaskExecutor taskExecutor;

	private static final String MSG = " 错误详细信息 : ";

	/**
	 * <p>
	 * 错误日志记录
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/15
	 * @param prefix
	 * @return void
	 */
	@Override
	protected void logError(String prefix, StackTraceElement[] stackTrace) {
		log(prefix, stackTrace);
	}

	/**
	 * <p>
	 * 错误日志记录
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/15
	 * @return void
	 */
	@Override
	protected void logError(StackTraceElement[] stackTrace) {
		log(stackTrace);
	}

	/**
	 * <p>
	 * 错误日志记录，内部输出
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/15
	 * @param prefix
	 * @return void
	 */
	private void log(String prefix, StackTraceElement[] stackTrace) {
		CompletableFuture.runAsync(() -> {
			for (StackTraceElement stackTraceElement : stackTrace) {
				log.error(PREFIX + JOINER + prefix.trim() + JOINER + "{}",
						MSG + stackTraceElement.toString());
			}
		}, taskExecutor);

	}

	/**
	 * <p>
	 * 错误日志记录，内部输出
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/15
	 * @return void
	 */
	private void log(StackTraceElement[] stackTrace) {
		CompletableFuture.runAsync(() -> {
			for (StackTraceElement stackTraceElement : stackTrace) {
				log.error(PREFIX + "{}", JOINER + MSG + stackTraceElement);
			}
		}, taskExecutor);
	}

}
