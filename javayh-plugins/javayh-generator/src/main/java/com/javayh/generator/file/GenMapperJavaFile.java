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
		content.append(NotesUtils.getFileNotes());

		// 写入文件包名
		content.append("package ").append(packageName).append(";").append(lineSeparator);
		content.append(lineSeparator);

		// 写入导入的包名
		content.append("import ").append(beanFile.getClassQuaName()).append(";")
				.append(lineSeparator);
		content.append("import ").append(beanDTOFile.getClassQuaName()).append(";")
				.append(lineSeparator);
		content.append("import org.apache.ibatis.annotations.Param;")
				.append(lineSeparator);
		content.append("import org.apache.ibatis.annotations.Mapper;")
				.append(lineSeparator);
		content.append("import java.util.List;").append(lineSeparator);
		content.append(lineSeparator);

		// 写入文件注释
		content.append(NotesUtils.getTypeNotes(gMain.getTableDesc() + "iMapper"));

		// 写入文件名
		content.append("@Mapper").append(lineSeparator);
		content.append("public interface ").append(className).append("{")
				.append(lineSeparator);

		// 写入分页查询的方法
		content.append(lineSeparator);
		content.append(NotesUtils.getMethodNotes("分页查询"));
		content.append("\t").append("List<" + beanFile.getClassName() + ">")
				.append(" findByPage( ")
				.append(beanDTOFile.getClassName() + " "
						+ StringUtil.toFirstUpCase(beanDTOFile.getClassName()))
				.append(")").append(";").append(lineSeparator);
		content.append(lineSeparator);

		// 写入单个查找方法
		content.append(lineSeparator);
		content.append(NotesUtils.getMethodNotes("根据id查找"));
		content.append("\t").append(beanFile.getClassName()).append(" findById(@Param(\"")
				.append(gMain.getStructureList().get(0).getFieldName())
				.append("\") Integer ")
				.append(gMain.getStructureList().get(0).getFieldName()).append(")")
				.append(";").append(lineSeparator);

		// 写入增加方法
		content.append(lineSeparator);
		content.append(NotesUtils.getMethodNotes("新增"));
		content.append("\t").append("int insert(").append(beanFile.getClassName())
				.append(" ").append(NameUtils.formatName(gMain.getTableName()))
				.append(")").append(";").append(lineSeparator);

		// 写入修改方法
		content.append(lineSeparator);
		content.append(NotesUtils.getMethodNotes("修改"));
		content.append("\t").append("int update(").append(beanFile.getClassName())
				.append(" ").append(NameUtils.formatName(gMain.getTableName()))
				.append(")").append(";").append(lineSeparator);

		// 写入删除方法
		content.append(lineSeparator);
		content.append(NotesUtils.getMethodNotes("根据id删除"));
		content.append("\t").append("int deleteById(@Param(\"")
				.append(gMain.getStructureList().get(0).getFieldName())
				.append("\") Integer ")
				.append(gMain.getStructureList().get(0).getFieldName()).append(")")
				.append(";").append(lineSeparator);
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
	//
	// public static void main(String[] args) {
	// long startTime = System.nanoTime(); //开始時間
	// //这里写操作
	// //消耗時間
	// long consumingTime = System.nanoTime() - startTime;
	// System.out.println("操作消耗时间--" + System.nanoTime()+ "纳秒");
	// }

}
