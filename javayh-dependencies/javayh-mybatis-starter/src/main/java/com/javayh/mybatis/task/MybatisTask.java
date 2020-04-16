package com.javayh.mybatis.task;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * <p>
 *
 * </p>
 *
 * @author Yang Haiji
 * @version 1.0.0
 * @since 2020-04-16 14:55
 */
@Slf4j
public class MybatisTask<T> extends RecursiveTask<Long> {


    static final int THRESHOLD = 2;
    private List<T> list;
    volatile int start;
    volatile int end;
    public MybatisTask(List<T> list, int start, int end) {
        if(CollectionUtils.isEmpty(list)){
            list = new ArrayList<>();
        }
        this.list = list;
        this.start = start;
        this.end = end;
    }

    @SneakyThrows
    @Override
    protected Long compute() {
       long flag = 0;
        if (end - start <= THRESHOLD) {
            // 如果任务足够小,直接操作:
            try{
                log.info("成功操作:" + (end - start));
                flag =  end - start;
            }catch (Exception e){
                log.info("操作失败的区间---开始位置{},终止位置{}",start,end);
                log.error("Fork Join {}",e.getStackTrace());
                throw new Exception("Fork Join");
            }
            return flag;
        }
        // 任务太大,一分为二:
        int middle = (end + start) / 2;
        log.info(String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle, middle, end));
        MybatisTask insert1 = new MybatisTask(this.list, start, middle);
        MybatisTask insert2 = new MybatisTask(this.list, middle, end);
        invokeAll(insert1, insert2);
        Long join1 = (Long) insert1.join();
        Long join2 = (Long) insert2.join();
        return join1 + join2;
    }
}
