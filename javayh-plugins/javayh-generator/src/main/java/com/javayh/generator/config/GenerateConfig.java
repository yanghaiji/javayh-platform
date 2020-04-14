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
package com.javayh.generator.config;

public class GenerateConfig {

	// 获取项目路径
	private final static String Property = System.getProperty("user.dir");

	private final static String PropertyOther = "\\javayh-demo\\javayh-demo-api";

	// java代码路径的前缀
	public final static String basepathPrefix = Property + PropertyOther
			+ "\\src\\main\\java\\";

	// xml文件路径的前缀
	public final static String xmlbasepathPrefix = Property + PropertyOther
			+ "\\src\\main\\resources\\";

	// 作者信息 选填（不填就给空字符串）
	public final static String classAuthor = "Dylan";

	public final static String fileAuthor = "杨海吉";

	// 公司信息信息 选填（不填就给空字符串）
	public final static String ORG_NAME = "Java有货";

	/** 类文件前后缀 必填 */
	public static final String CONTROLLER_FILENAME = "Controller";

	public static final String SERVICE1_FILENAME = "I";

	public static final String SERVICE2_FILENAME = "Service";

	public static final String IMPL_FILENAME = "ServiceImpl";

	public static final String DAO_FILENAME = "Mapper";

	public static final String MAPPER_FILENAME = "Mapper";

	// 包名 必填
	public static final String BASE_PACKAGE_NAME = "com";

	public static final String PROJECT_NAME = "javayh";

	public static final String CONTROLLER_NAME = "controller";

	public static final String SERVICE_NAME = "service";

	public static final String IMPL_NAME = "service.impl";

	public static final String POJO_BO_NAME = "pojo";

	public static final String POJO_DTO_NAME = "dto";

	public static final String DAO_NAME = "dao";

	public static final String MAPPER_NAME = "mapper";

	// 数据信息 必填
	public final static String jdbcDriverClassName = "com.mysql.cj.jdbc.Driver";

	public final static String jdbcUrl = "jdbc:mysql://localhost:3306/db1?serverTimezone=CTT&characterEncoding=utf8&autoReconnect=true&useUnicode=true&useSSL=true";// 数据库地址

	public final static String jdbcName = "db1";// 数据库名

	public final static String jdbcUsername = "root";// 用户名

	public final static String jdbcPassword = "root";// 密码

}
