package com.javayh.datasource.aop;

import com.javayh.datasource.annotation.DataSource;
import com.javayh.datasource.constant.DataSourceKey;
import com.javayh.datasource.util.DataSourceHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;

/**
 * @ClassName  DataSourceAOP
 * @Description 切换数据源Advice
 * @Author Yang haiji
 * @Version 1.0.0
 */
@Slf4j
@Aspect
@Order(-1) // 保证该AOP在@Transactional之前执行
public class DataSourceAOP {


    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint point, DataSource ds) throws Throwable {
        String dsId = ds.name();
        try {
            DataSourceKey dataSourceKey = DataSourceKey.valueOf(dsId);
            DataSourceHolder.setDataSourceKey(dataSourceKey);
        } catch (Exception e) {
            log.error("数据源[{}]不存在，使用默认数据源 > {}", ds.name(), point.getSignature());
        }


    }

    @After("@annotation(ds)")
    public void restoreDataSource(JoinPoint point, DataSource ds) {
    	log.debug("Revert DataSource : {transIdo} > {}", ds.name(), point.getSignature());
        DataSourceHolder.clearDataSourceKey();
    }

}