package com.javayh.datasource.util;

import com.javayh.datasource.constant.DataSourceKey;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * spring动态数据源（需要继承AbstractRoutingDataSource）
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	private Map<Object, Object> datasources;

	public DynamicDataSource() {
		datasources = new HashMap<>();

		super.setTargetDataSources(datasources);

	}

	public <T extends DataSource> void addDataSource(DataSourceKey key, T data) {
		datasources.put(key, data);
	}

	protected Object determineCurrentLookupKey() {
		return DataSourceHolder.getDataSourceKey();
	}

}