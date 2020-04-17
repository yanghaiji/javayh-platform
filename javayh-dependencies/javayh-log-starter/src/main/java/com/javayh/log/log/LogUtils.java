package com.javayh.log.log;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * <p>
 *
 * </p>
 *
 * @author Yang Haiji
 * @version 1.0.0
 * @since 2020-04-17 15:39
 */
@Slf4j
public class LogUtils {
    private static final Logger LOG = LoggerFactory.getLogger(LogUtils.class);
    // 是否启动
    private static boolean canSave = true;

    // 允许日志存活在队列中的最大长度，超过这个数值将会触发一次存储
    private static final int MAX_LOG_ITEMS = 50;

    // 用来存储考试操作相关日志
    private static final ArrayBlockingQueue
            LOG_QUEUE = new ArrayBlockingQueue(200,true);

    // 定义信号量
    private static final Semaphore LOG_SEM = new Semaphore(1);
    private static final Thread LOG_SAVER = new Thread(() -> {
        try {
            // 线程开始立即从信号中获取许可，获取不到立即返回
            boolean canUpdate = LOG_SEM.tryAcquire();
            if (!canUpdate) {
                LOG.warn("there is another thread saving log!");
                return;
            }
            if(CollectionUtil.isNotEmpty(LOG_QUEUE)){
                logSave(LOG_QUEUE);
            }
        } catch (Exception e) {
            LOG.error("save log has error!", e);
        } finally {
            LOG_SEM.release();
        }
    });

    private static final ScheduledExecutorService scheduledThreadPool =
            Executors.newScheduledThreadPool(1);
    static {
        // 注册考试日志周期保存线程到线程池里
        scheduledThreadPool.scheduleAtFixedRate(LOG_SAVER, 0, 10, TimeUnit.SECONDS);
    }


    /**
     * 存储
     * @param logQueue
     * @param mongoLogEntity
     * @param <T>
     */
    private static <T> void oneItemPushInto(ArrayBlockingQueue<T> logQueue, T mongoLogEntity) {
        logQueue.offer(mongoLogEntity);
        if (logQueue.size() >= MAX_LOG_ITEMS) {
            // 阻塞之插入更改为线程启动执行
            LOG_SAVER.start();
        }
    }

    /**
     * 将数据持久化
     */
    private static void logSave(ArrayBlockingQueue logQueue) {
        List<ArrayList> tempQueue = new ArrayList<>(logQueue.size());
        logQueue.drainTo(tempQueue);
        // null check
        if (CollectionUtil.isEmpty(tempQueue)) {
            return;
        }
        if (canSave) {
            //TODO 持久化

        } else {
            for (Object log : tempQueue) {
                LOG.info(String.valueOf(log));
            }
        }
    }
}
