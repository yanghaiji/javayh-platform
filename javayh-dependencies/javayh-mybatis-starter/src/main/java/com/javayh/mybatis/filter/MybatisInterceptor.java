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
package com.javayh.mybatis.filter;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import com.github.pagehelper.autoconfigure.PageHelperProperties;
import com.javayh.mybatis.exception.MybatisInjectionException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * <p>
 *
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-30 14:55
 */
@Slf4j
@Configuration
@Intercepts({ @Signature(type = Executor.class, method = "query", args = {
		MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }),
		@Signature(type = Executor.class, method = "query", args = {
				MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class,
				CacheKey.class, BoundSql.class }), })
public class MybatisInterceptor implements Interceptor {

	static final int MAPPED_STATEMENT_INDEX = 0;
	static final int PARAMETER_INDEX = 1;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
	    try{
            final Object[] queryArgs = invocation.getArgs();
            final MappedStatement mappedStatement = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
            final Object parameter = queryArgs[PARAMETER_INDEX];
            SqlLog.getSql(mappedStatement.getConfiguration(),
                    mappedStatement.getBoundSql(parameter), mappedStatement.getId());
//		Boolean illegalStr = IllegalSqlFilter.isIllegalStr(parameter.toString());
//		Boolean filter = (Boolean) IllegalSqlFilter.sqlStrFilter(parameter);
//		if (!filter ) {
//			log.error("Sql 存在注入风险!");
//			return new MybatisInjectionException("Sql Exception");
//		}
            // 进行sql修改
            // final BoundSql boundSql =
            // mappedStatement.getBoundSql(IllegalSqlFilter.sqlStrFilter(parameter));
            // String sql = boundSql.getSql();
            // // 重新new一个查询语句对像
            // BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), sql,
            // boundSql.getParameterMappings(), boundSql.getParameterObject());
            // // 把新的查询放到statement里
            // MappedStatement newMs = copyFromMappedStatement(mappedStatement, new
            // BoundSqlSqlSource(newBoundSql));
            // for (ParameterMapping mapping : boundSql.getParameterMappings()) {
            // String prop = mapping.getProperty();
            // if (boundSql.hasAdditionalParameter(prop)) {
            // newBoundSql.setAdditionalParameter(prop,
            // boundSql.getAdditionalParameter(prop));
            // }
            // }
            // queryArgs[MAPPED_STATEMENT_INDEX] = newMs;
            // return invocation.proceed();
            // 执行完上面的任务后，不改变原有的sql执行过程
        }catch (Exception e){
            log.error("SQL There is an exception, please check whether your SQL is standardized",e);
            throw new SQLException("SQL 存在异常,请检查您的SQL是否规范");
        }
		return invocation.proceed();
	}

	private MappedStatement copyFromMappedStatement(MappedStatement ms,
			SqlSource newSqlSource) {
		MappedStatement.Builder builder = new MappedStatement.Builder(
				ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		if (ms.getKeyProperties() != null && ms.getKeyProperties().length > 0) {
			builder.keyProperty(ms.getKeyProperties()[0]);
		}
		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		builder.resultMaps(ms.getResultMaps());
		builder.resultSetType(ms.getResultSetType());
		builder.cache(ms.getCache());
		builder.flushCacheRequired(ms.isFlushCacheRequired());
		builder.useCache(ms.isUseCache());
		return builder.build();
	}

	private static class BoundSqlSqlSource implements SqlSource {

		private BoundSql boundSql;

		public BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}

		@Override
		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}

	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}

}