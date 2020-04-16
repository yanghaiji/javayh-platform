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

import com.javayh.generator.config.GenerateConfig;
import com.javayh.generator.util.FileUtils;
import com.javayh.generator.util.NameUtils;
import com.javayh.generator.util.NotesUtils;
import com.javayh.generator.util.StringUtil;

import java.io.File;
import java.io.IOException;

/**
 * 生成 Mapper接口
 */
public class GenMapperJavaFile {

	private GenMain gMain;

	private GenBeanFile beanFile;

	private GenBeanDTOFile beanDTOFile;

	private String packageName;

	private String className;

	private String fileName;

	private String classQuaName; // 全称

	public void generate() {
		// 包名
		packageName = GenerateConfig.BASE_PACKAGE_NAME + "." + GenerateConfig.PROJECT_NAME
				+ "." + gMain.getModelName() + "." + GenerateConfig.DAO_NAME;
		className = NameUtils.formatClassName(gMain.getTableName())
				+ GenerateConfig.DAO_FILENAME;
		fileName = className + ".java";
		classQuaName = packageName + "." + className;

		File file = new File(
				GenerateConfig.basepathPrefix + packageName.replace(".", "/"), fileName);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		System.out.println("生成mapper.java文件的目录" + file.getAbsolutePath());

		if (!file.exists()) {
			try {
				file.createNewFile();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 修改文件编码
		FileUtils.changeFileEncoding(file, "UTF-8");

		// 向文件写入内容
		FileUtils.WriteStringToFile(file, genContent());
	}

	private String genContent() {
		String lineSeparator = System.getProperty("line.separator");
		StringBuffer content = new StringBuffer();
		// 写入文件注释
//		content.append(NotesUtils.getFileNotes());

		// 写入文件包名
		content.append("package ").append(packageName).append(";").append(lineSeparator);
		content.append(lineSeparator);

		// 写入导入的包名
		content.append("import org.apache.ibatis.annotations.Mapper;")
				.append(lineSeparator);
		content.append("import com.javayh.mybatis.mapper.BaseMapper;").append(lineSeparator);
		content.append(lineSeparator);

		// 写入文件注释
		content.append(NotesUtils.getTypeNotes(gMain.getTableDesc() + "iMapper"));

		// 写入文件名
		content.append("@Mapper").append(lineSeparator);
		content.append("public interface ").append(className).append(" extends BaseMapper{")
				.append(lineSeparator);
		content.append("}");
		return content.toString();
	}

	/**
	 * @param gMain
	 */
	public GenMapperJavaFile(GenMain gMain, GenBeanFile beanFile,
			GenBeanDTOFile beanDTOFile) {
		super();
		this.gMain = gMain;
		this.beanFile = beanFile;
		this.beanDTOFile = beanDTOFile;
	}

	/**
	 * @return the classQuaName
	 */
	public String getClassQuaName() {
		return classQuaName;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}


}
