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
import com.javayh.generator.util.FileUtils;
import com.javayh.generator.util.NameUtils;
import com.javayh.generator.util.NotesUtils;

import java.io.File;
import java.io.IOException;

/**
 * 生成实体类
 */
public class GenBeanFile {

	private GenMain gMain;

	private String packageName;

	private String className;

	private String fileName;

	private String classQuaName; // 全称

	public void generate() {
		// 包名
		packageName = GenerateConfig.BASE_PACKAGE_NAME + "." + GenerateConfig.PROJECT_NAME
				+ "." + gMain.getModelName() + "." + GenerateConfig.POJO_BO_NAME;
		className = NameUtils.formatClassName(gMain.getTableName());
		fileName = className + ".java";
		classQuaName = packageName + "." + className;

		File file = new File(
				GenerateConfig.basepathPrefix + packageName.replace(".", "/"), fileName);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		System.out.println("生成bean文件的目录" + file.getAbsolutePath());

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
		content.append("import lombok.*;").append(lineSeparator);
		content.append(lineSeparator);

		// 写入导入的包名
		content.append("import io.swagger.annotations.ApiModelProperty;")
				.append(lineSeparator);// 没使用swagger的注释掉
		for (String str : gMain.getClassNameSet()) {
			content.append("import ").append(str).append(";").append(lineSeparator);
		}

		// 写入文件注释
		content.append(NotesUtils.getTypeNotes(gMain.getTableDesc() + "Bean"));

		// 写入文件名
		content.append("@SuppressWarnings(\"serial\")").append(lineSeparator);
		content.append("@Data").append(lineSeparator);
		content.append("@NoArgsConstructor").append(lineSeparator);
		content.append("@AllArgsConstructor").append(lineSeparator);
		content.append("@Builder").append(lineSeparator);
		content.append("@ToString").append(lineSeparator);
		content.append("public class ").append(className)
				./* append(" extends BaseEntity"). */append(
						" implements java.io.Serializable")
				.append("{").append(lineSeparator);

		// 写入字段名
		content.append(lineSeparator);
		for (FieldBean field : gMain.getStructureList()) {
			content.append(NotesUtils.getMethodNotes(field.getFieldContent()));
			if (field.getFieldContent() == null || field.getFieldContent().equals("")) {
				content.append("\t").append("@ApiModelProperty(hidden = true)")
						.append(lineSeparator);
			}
			else {
				content.append("\t").append("@ApiModelProperty(value = \"")
						.append(field.getFieldContent()).append("\")")
						.append(lineSeparator);
			}
			content.append("\t").append("private ")
					.append(field.getVhClass().getSimpleName()).append(" ")
					.append(field.getFieldName()).append("; ").append(lineSeparator);
		}

		content.append(lineSeparator);
		content.append("}");
		return content.toString();
	}

	/**
	 * @param gMain
	 */
	public GenBeanFile(GenMain gMain) {
		super();
		this.gMain = gMain;
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

	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

}
