package com.javayh.mybatis.filter;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-04-05 11:44
 */
@Configuration
@AutoConfigureAfter(PageHelperAutoConfiguration.class)
public class MybatisFilterAutoConfiguration {

	@Autowired(required = false)
	private List<SqlSessionFactory> sqlSessionFactoryList;

	@PostConstruct
	public void addMyInterceptor() {
		MybatisInterceptor e = new MybatisInterceptor();
		for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
			sqlSessionFactory.getConfiguration().addInterceptor(e);
		}
	}

}
