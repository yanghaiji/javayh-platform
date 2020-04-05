package com.javayh.mybatis.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.util.CollectionUtils;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;

/**
 * <p>
 *      sql记录器
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-31 17:01
 */
@Slf4j
public class SqlLog {

    /**
     * <p>
     *       封装了一下sql语句，使得结果返回完整xml路径下的sql语句节点id + sql语句
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/30
     * @param configuration
     * @param boundSql
     * @param sqlId
     * @return java.lang.String
     */
    public static String getSql(Configuration configuration, BoundSql boundSql, String sqlId){
        String sql = showSql(configuration, boundSql);
        StringBuilder str = new StringBuilder(100);
        str.append(sqlId);
        str.append(":");
        str.append(sql);
        String sqlLog = str.toString();
        log.info("执行的SQL为:{}",sqlLog);
        return sqlLog;
    }

    /**
     * <p>
     *       如果参数是String，则添加单引号， 如果是日期，则转换为时间格式器并加单引号； 对参数是null和不是null的情况作了处理
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/30
     * @param obj
     * @return java.lang.String
     */
    private static String getParameterValue(Object obj){
        String value = null;
        if (obj instanceof String){
            value = "'" + obj.toString() + "'";
        }
        else if (obj instanceof Date){
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT,
                    DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        }
        else{
            if (obj != null){
                value = obj.toString();
            }
            else {
                value = "";
            }
        }
        return value;
    }

    /**
     * <p>
     *       进行？的替换
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/31
     * @param configuration
     * @param boundSql
     * @return java.lang.String
     */
    public static String showSql(Configuration configuration, BoundSql boundSql){
        // 获取参数
        Object parameterObject = boundSql.getParameterObject();
        parameterObject = IllegalSqlFilter.sqlFilter(parameterObject);
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        // sql语句中多个空格都用一个空格代替
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (!CollectionUtils.isEmpty(parameterMappings) && parameterObject != null){
            // 获取类型处理器注册器，类型处理器的功能是进行java类型和数据库类型的转换
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            // 如果根据parameterObject.getClass(）可以找到对应的类型，则替换
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?",
                        Matcher.quoteReplacement(getParameterValue(parameterObject)));
            }
            else {
                // MetaObject主要是封装了originalObject对象，
                // 提供了get和set的方法用于获取和设置originalObject的属性值,
                // 主要支持对JavaBean、Collection、Map三种类型对象的操作
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?",
                                Matcher.quoteReplacement(getParameterValue(obj)));

                    }
                    else if (boundSql.hasAdditionalParameter(propertyName)) {
                        // 该分支是动态sql
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?",
                                Matcher.quoteReplacement(getParameterValue(obj)));
                    }
                    else {
                        // 打印出缺失，提醒该参数缺失并防止错位
                        sql = sql.replaceFirst("\\?", "缺失");
                    }
                }
            }
        }
        return sql;
    }
}
