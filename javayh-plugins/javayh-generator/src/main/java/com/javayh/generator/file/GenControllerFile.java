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
 * 生成控制器
 */
public class GenControllerFile {

	private GenMain gMain;

	private GenBeanFile beanFile;

	private GenBeanDTOFile beanDtoFile;

	private GenIServiceFile serviceFile;

	private String packageName;

	private String className;

	private String fileName;

	public void generate() {
		// 包名
		packageName = GenerateConfig.BASE_PACKAGE_NAME + "." + GenerateConfig.PROJECT_NAME
				+ "." + gMain.getModelName() + "." + GenerateConfig.CONTROLLER_NAME;
		className = NameUtils.formatClassName(gMain.getTableName())
				+ GenerateConfig.CONTROLLER_FILENAME;
		fileName = className + ".java";

		File file = new File(
				GenerateConfig.basepathPrefix + packageName.replace(".", "/"), fileName);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		System.out.println("生成controller.java文件的目录" + file.getAbsolutePath());

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
		content.append(NotesUtils.getFileNotes());

		// 写入文件包名
		content.append("package ").append(packageName).append(";").append(lineSeparator);
		content.append(lineSeparator);

		// 写入导入的包名
		content.append("import ")
				.append(GenerateConfig.BASE_PACKAGE_NAME + "."
						+ GenerateConfig.PROJECT_NAME)
				.append(".common.result.ResultData;").append(lineSeparator);
		content.append("import io.swagger.annotations.Api;").append(lineSeparator);
		content.append("import io.swagger.annotations.ApiOperation;")
				.append(lineSeparator);
		content.append("import org.springframework.web.bind.annotation.DeleteMapping;")
				.append(lineSeparator);
		content.append("import org.springframework.web.bind.annotation.GetMapping;")
				.append(lineSeparator);
		content.append("import org.springframework.web.bind.annotation.PostMapping;")
				.append(lineSeparator);
		content.append("import org.springframework.web.bind.annotation.PutMapping;")
				.append(lineSeparator);
		content.append("import org.springframework.web.bind.annotation.RequestMapping;")
				.append(lineSeparator);
		content.append("import org.springframework.web.bind.annotation.RequestBody;")
				.append(lineSeparator);
		content.append("import org.springframework.web.bind.annotation.RestController;")
				.append(lineSeparator);
		content.append("import org.springframework.beans.factory.annotation.Autowired;")
				.append(lineSeparator);
		content.append("import  com.javayh.mybatis.page.PageQuery;")
				.append(lineSeparator);
		content.append("import ").append(beanFile.getClassQuaName()).append(";")
				.append(lineSeparator);
		content.append("import ").append(beanDtoFile.getClassQuaName()).append(";")
				.append(lineSeparator);
		content.append("import ").append(serviceFile.getClassQuaName()).append(";")
				.append(lineSeparator);
		content.append(lineSeparator);

		// 写入文件注释
		content.append(NotesUtils.getTypeNotes(gMain.getTableDesc() + "Controller"));

		// 写入文件名 注解
		String str = "/" + gMain.getTableName().replaceAll("_", "/");// 表名下划线转斜杠
		content.append("@RestController").append(lineSeparator);
		content.append("@Api(tags = \"").append("\")").append(lineSeparator);// 没使用swagger的可以注销掉
		content.append("@RequestMapping(\"").append(str).append("\")")
				.append(lineSeparator);
		content.append("public class ").append(className).append(" {")
				.append(lineSeparator);

		// 注入service @Autowired自动注入
		String serviceName = NameUtils.formatName(gMain.getTableName()) + "ServiceImpl";
		content.append(lineSeparator);
		content.append("\t").append("@Autowired").append(lineSeparator);
		content.append("\t").append("private ").append(serviceFile.getClassName())
				.append(" ").append(serviceName).append("; ").append(lineSeparator);
		content.append(lineSeparator);

		// 写入分页查找方法 分页查询
		content.append(NotesUtils.getMethodNotes("根据条件查找-分页"));
		content.append("\t").append("@ApiOperation(value = \"根据条件查找-分页\")")
				.append(lineSeparator);
		content.append("\t").append("@PostMapping(value = \"/findByPage\")")
				.append(lineSeparator);
		content.append("\t").append("public ResultData<PageQuery>")
				.append(" findByPage (")
				// .append("PageQuery pageQuery, ")
				.append("@RequestBody " + beanDtoFile.getClassName() + " "
						+ StringUtil.toFirstUpCase(beanDtoFile.getClassName()))
				.append(")").append("{").append(lineSeparator);
		content.append("\t\t").append("return ResultData.success(").append(serviceName)
				.append(".findByPage(")
				// .append("pageQuery, ")
				.append(StringUtil.toFirstUpCase(beanDtoFile.getClassName()))
				.append("));").append(lineSeparator);
		content.append("\t").append("}").append(lineSeparator);
		content.append(lineSeparator);

		// 写入单个查找方法 根据id查询
		content.append(NotesUtils.getMethodNotes("根据id查询"));
		content.append("\t").append("@ApiOperation(value = \"根据id查询\")")
				.append(lineSeparator);
		content.append("\t").append("@GetMapping(value = \"/findById\")")
				.append(lineSeparator);
		content.append("\t").append("public ResultData<").append(beanFile.getClassName())
				.append("> findById(Integer ")
				.append(gMain.getStructureList().get(0).getFieldName()).append(")")
				.append("{").append(lineSeparator);
		content.append("\t\t").append("return ResultData.success(").append(serviceName)
				.append(".findById(")
				.append(gMain.getStructureList().get(0).getFieldName()).append("));")
				.append(lineSeparator);
		content.append("\t").append("}").append(lineSeparator);
		content.append(lineSeparator);

		// 写入增加方法
		content.append(NotesUtils.getMethodNotes("添加数据"));
		content.append("\t").append("@ApiOperation(value = \"添加数据\")")
				.append(lineSeparator);
		content.append("\t").append("@PostMapping(value = \"/insert\")")
				.append(lineSeparator);
		content.append("\t").append("public ResultData<Integer> insert(")
				.append("@RequestBody " + beanFile.getClassName()).append(" ")
				.append(NameUtils.formatName(gMain.getTableName())).append(")")
				.append("{").append(lineSeparator);
		content.append("\t\t").append(serviceName).append(".insert(")
				.append(NameUtils.formatName(gMain.getTableName())).append(");")
				.append(lineSeparator);
		content.append("\t\t").append("return ResultData.success();")
				.append(lineSeparator);
		content.append("\t").append("}").append(lineSeparator);
		content.append(lineSeparator);

		// 写入修改方法
		content.append(NotesUtils.getMethodNotes("修改数据"));
		content.append("\t").append("@ApiOperation(value = \"修改数据\")")
				.append(lineSeparator);
		content.append("\t").append("@PutMapping(value = \"/update\")")
				.append(lineSeparator);
		content.append("\t").append("public ResultData<Integer> update(")
				.append("@RequestBody " + beanFile.getClassName()).append(" ")
				.append(NameUtils.formatName(gMain.getTableName())).append(")")
				.append("{").append(lineSeparator);
		content.append("\t\t").append(serviceName).append(".update(")
				.append(NameUtils.formatName(gMain.getTableName())).append(");")
				.append(lineSeparator);
		content.append("\t\t").append("return ResultData.success();")
				.append(lineSeparator);
		content.append("\t").append("}").append(lineSeparator);
		content.append(lineSeparator);

		// 写入删除方法 根据id删除
		content.append(NotesUtils.getMethodNotes("根据id删除"));
		content.append("\t").append("@ApiOperation(value = \"根据id删除\")")
				.append(lineSeparator);
		content.append("\t").append("@DeleteMapping(value = \"/deleteById\")")
				.append(lineSeparator);
		content.append("\t").append("public ResultData<Integer> deleteById(Integer ")
				.append(gMain.getStructureList().get(0).getFieldName()).append(")")
				.append("{").append(lineSeparator);
		content.append("\t\t").append(serviceName).append(".deleteById(")
				.append(gMain.getStructureList().get(0).getFieldName()).append(");")
				.append(lineSeparator);
		content.append("\t\t").append("return ResultData.success();")
				.append(lineSeparator);
		content.append("\t").append("}").append(lineSeparator);
		content.append(lineSeparator);
		content.append("}");
		return content.toString();
	}

	/**
	 */
	public GenControllerFile(GenMain gMain, GenBeanFile beanFile,
			GenBeanDTOFile beanDTOFile, GenIServiceFile serviceFile) {
		super();
		this.gMain = gMain;
		this.beanFile = beanFile;
		this.beanDtoFile = beanDTOFile;
		this.serviceFile = serviceFile;
	}

}
