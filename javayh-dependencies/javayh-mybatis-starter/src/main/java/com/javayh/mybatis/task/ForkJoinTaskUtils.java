package com.javayh.mybatis.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * <p>
 *      分片工具
 * </p>
 *
 * @author Yang Haiji
 * @version 1.0.0
 * @since 2020-04-16 14:12
 */
public class ForkJoinTaskUtils<T extends Object >implements Serializable {


    public static <T> Long batchOperation(List<T> list){
        // fork/join:
        ForkJoinTask<Long> task = new MybatisTask( list, 0, list.size());
        Long invoke = ForkJoinPool.commonPool().invoke(task);
        return invoke;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1234");
        list.add("1234");
        list.add("1234");
        list.add("1234");
        list.add("1234");
        list.add("1234");
        list.add("1234");
        list.add("1234");
        list.add("1234");
        Long aLong = batchOperation(list);
        System.out.println(aLong);
    }

}
