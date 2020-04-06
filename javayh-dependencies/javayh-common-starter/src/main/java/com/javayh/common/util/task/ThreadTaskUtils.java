package com.javayh.common.util.task;

import com.javayh.common.mdc.MdcThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 * 线程池
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-01 23:08
 */
public class ThreadTaskUtils {

	private static MdcThreadPoolTaskExecutor taskExecutor = null;

	static {
		taskExecutor = new MdcThreadPoolTaskExecutor();
		// 核心线程数
		taskExecutor.setCorePoolSize(5);
		// 最大线程数
		taskExecutor.setMaxPoolSize(50);
		// 队列最大长度
		taskExecutor.setQueueCapacity(1000);
		// 线程池维护线程所允许的空闲时间(单位秒)
		taskExecutor.setKeepAliveSeconds(120);
		// 线程池对拒绝任务(无线程可用)的处理策略 ThreadPoolExecutor.CallerRunsPolicy策略
		// ,调用者的线程会执行该任务,如果执行器已关闭,则丢弃.
		taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());

		taskExecutor.initialize();
	}

	public static void run(Runnable runnable) {
		taskExecutor.execute(runnable);
	}

}
