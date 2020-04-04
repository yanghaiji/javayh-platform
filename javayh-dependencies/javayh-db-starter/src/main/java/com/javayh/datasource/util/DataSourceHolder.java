package com.javayh.datasource.util;

import com.javayh.datasource.constant.DataSourceKey;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName  DataSourceHolder
 * @Description 用于数据源切换
 * @Author Yang haiji
 * @Version 1.0.0
 */
@Slf4j
public class DataSourceHolder {

    /**
     *  获取线程数据源
     */
    private static final ThreadLocal<DataSourceKey> dataSourceKey = new ThreadLocal<>();

    /**
     * @Description  得到当前的数据库连接
     * @UserModule: javayh-datasource
     * @author Yang haiji
     * @date 2020/1/8
     */
    public static DataSourceKey getDataSourceKey() {
        return DataSourceHolder.dataSourceKey.get();
    }

    /**
     * @Description  设置当前的数据库连接
     * @UserModule: javayh-datasource
     * @author Yang haiji
     * @date 2020/1/8
     */
    public static void setDataSourceKey(DataSourceKey type) {
        dataSourceKey.set(type);
    }

    /**
     * @Description  清除当前的数据库连接
     * @UserModule: javayh-datasource
     * @author Yang haiji
     * @date 2020/1/8
     */
    public static void clearDataSourceKey() {
        dataSourceKey.remove();
    }


}