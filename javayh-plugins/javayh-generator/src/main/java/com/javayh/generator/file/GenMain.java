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
package com.javayh.generator.file;

import com.javayh.generator.bean.FieldBean;
import com.javayh.generator.config.GenerateConfig;
import com.javayh.generator.util.NameUtils;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;
import java.util.*;

/**
 * 生成总入口
 */
public class GenMain {

	private List<FieldBean> structureList = null;

	private Set<String> classNameSet = new HashSet<String>();

	private boolean isGenBeanOnly;

	private String tableName;

	private String tableDesc;

	private String modelName;

	public void generate() throws Exception {
		// 初始化连接
		initConn();
		// 生成bean文件
		GenBeanFile beanFile = new GenBeanFile(this);
		beanFile.generate();
		GenBeanDTOFile beanDtoFile = new GenBeanDTOFile(this);
		beanDtoFile.generate();
		if (!isGenBeanOnly) {
			GenMapperJavaFile mapperFile = new GenMapperJavaFile(this, beanFile,
					beanDtoFile);
			mapperFile.generate();
			GenMapperXmlFile mapperXmlFile = new GenMapperXmlFile(this, beanFile,
					mapperFile);
			mapperXmlFile.generate();
			GenIServiceFile serviceFile = new GenIServiceFile(this, beanFile,
					beanDtoFile);
			serviceFile.generate();
			GenServiceImplFile serviceImplFile = new GenServiceImplFile(this, beanFile,
					beanDtoFile, mapperFile, serviceFile);
			serviceImplFile.generate();
			GenControllerFile genControllerFile = new GenControllerFile(this, beanFile,
					beanDtoFile, serviceFile);
			genControllerFile.generate();
		}
	}

	private void initConn() throws Exception {
		Class.forName(GenerateConfig.jdbcDriverClassName);// 指定连接类型
		Connection connection = DriverManager.getConnection(GenerateConfig.jdbcUrl,
				GenerateConfig.jdbcUsername, GenerateConfig.jdbcPassword);// 获取连接
		// 查询所有字段
		String sql = "SELECT COLUMN_NAME,COLUMN_COMMENT,DATA_TYPE FROM information_schema.COLUMNS WHERE TABLE_SCHEMA "
				+ "= '" + GenerateConfig.jdbcName + "' AND TABLE_NAME = '" + tableName
				+ "' order by ORDINAL_POSITION";
		PreparedStatement pst = connection.prepareStatement(sql);// 准备执行语句
		ResultSet rSet = pst.executeQuery();
		structureList = convertList(rSet);
		sql = "select TABLE_NAME,TABLE_COMMENT from information_schema.TABLES where TABLE_SCHEMA = '"
				+ GenerateConfig.jdbcName + "' AND TABLE_NAME = '" + tableName + "'";
		pst = connection.prepareStatement(sql);// 准备执行语句
		rSet = pst.executeQuery();
		tableDesc = getTableComment(rSet);
		pst.close();
		connection.close();
		if (structureList.size() == 0) {
			throw new Exception("转换失败，请确认表名是否存在");
		}
	}

	private String getTableComment(ResultSet rs) throws SQLException {
		if (rs.next()) {
			return rs.getObject(2).toString();
		}
		return "";
	}

	private List<FieldBean> convertList(ResultSet rs) throws SQLException {
		List<FieldBean> list = new ArrayList<FieldBean>();
		while (rs.next()) {
			FieldBean field = new FieldBean();
			field.setColumnName(rs.getObject(1).toString());
			field.setFieldContent(rs.getObject(2).toString());
			field.setFieldName(NameUtils.formatName(field.getColumnName()));
			field.setFieldNameUp(NameUtils.formatClassName(field.getColumnName()));
			switch (rs.getObject(3).toString()) {
			case "varchar":
			case "text":
				// 字符串
				field.setVhClass(String.class);
				break;
			case "datetime":
			case "date":
				// 日期
				field.setVhClass(Date.class);
				classNameSet.add("java.util.Date");
				break;
			case "decimal":
				field.setVhClass(BigDecimal.class);
				classNameSet.add("java.math.BigDecimal");
				break;
			case "timestamp":
				// 时间搓
				field.setVhClass(Long.class);
				break;
			default:
				// 默认int
				field.setVhClass(Integer.class);
				break;
			}
			list.add(field);
		}
		return list;
	}

	/**
	 * @param tableName
	 * @param modelName
	 */
	public GenMain(String tableName, String modelName, boolean isGenBeanOnly) {
		super();
		this.tableName = tableName;
		this.modelName = modelName;
		this.isGenBeanOnly = isGenBeanOnly;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the modelName
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * @param modelName the modelName to set
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	/**
	 * @return the structureList
	 */
	public List<FieldBean> getStructureList() {
		return structureList;
	}

	/**
	 * @param structureList the structureList to set
	 */
	public void setStructureList(List<FieldBean> structureList) {
		this.structureList = structureList;
	}

	/**
	 * @return the isGenBeanOnly
	 */
	public boolean isGenBeanOnly() {
		return isGenBeanOnly;
	}

	/**
	 * @param isGenBeanOnly the isGenBeanOnly to set
	 */
	public void setGenBeanOnly(boolean isGenBeanOnly) {
		this.isGenBeanOnly = isGenBeanOnly;
	}

	/**
	 * @return the classNameSet
	 */
	public Set<String> getClassNameSet() {
		return classNameSet;
	}

	/**
	 * @param classNameSet the classNameSet to set
	 */
	public void setClassNameSet(Set<String> classNameSet) {
		this.classNameSet = classNameSet;
	}

	/**
	 * @return the tableDesc
	 */
	public String getTableDesc() {
		return tableDesc;
	}

}
