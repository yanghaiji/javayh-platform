package com.javayh.datasource.conf;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.javayh.datasource.aop.DataSourceAOP;
import com.javayh.datasource.constant.DataSourceKey;
import com.javayh.datasource.util.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @ClassName DataSourceAutoConfig
 * @Description 在设置了spring.datasource.enable.dynamic 等于true是开启多数据源
 * @Author Yang haiji
 * @Version 1.0.0
 */
@Configuration
@AutoConfigureBefore(DruidDataSourceAutoConfigure.class)
@ConditionalOnProperty(name = {
		"spring.datasource.dynamic.enable" }, matchIfMissing = false, havingValue = "true")
@Import(DataSourceAOP.class)
public class DataSourceAutoConfig {

	@Bean
	@ConfigurationProperties("spring.datasource.druid.core")
	public DataSource dataSourceCore() {
		return DruidDataSourceBuilder.create().build();
	}

	@Bean
	@ConfigurationProperties("spring.datasource.druid.follower")
	public DataSource dataSourceLog() {
		return DruidDataSourceBuilder.create().build();
	}

	/**
	 * <p>
	 * 只需要纳入动态数据源到spring容器
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/3
	 * @param
	 * @return javax.sql.DataSource
	 */
	@Primary
	@Bean
	public DataSource dataSource() {
		DynamicDataSource dataSource = new DynamicDataSource();
		DataSource coreDataSource = dataSourceCore();
		DataSource logDataSource = dataSourceLog();
		dataSource.addDataSource(DataSourceKey.core, coreDataSource);
		dataSource.addDataSource(DataSourceKey.follower, logDataSource);
		dataSource.setDefaultTargetDataSource(coreDataSource);
		return dataSource;
	}

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(
			@Qualifier("dataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource);
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources("classpath*:mapper/*/*.xml"));
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
		configuration.setMapUnderscoreToCamelCase(true);
		configuration.setCacheEnabled(false);
		sqlSessionFactory.setConfiguration(configuration);
		return sqlSessionFactory.getObject();
	}

	/**
	 * <p>
	 * 将数据源纳入spring事物管理
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/3
	 * @param dataSource
	 * @return org.springframework.jdbc.datasource.DataSourceTransactionManager
	 */
	@Bean
	public DataSourceTransactionManager transactionManager(
			@Qualifier("dataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

}
