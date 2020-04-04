package com.javayh.generator.file;


import com.javayh.generator.config.GenerateConfig;
import com.javayh.generator.util.FileUtils;
import com.javayh.generator.util.NameUtils;
import com.javayh.generator.util.NotesUtils;
import com.javayh.generator.util.StringUtil;

import java.io.File;
import java.io.IOException;

/**
 * 生成service接口
 */
public class GenIServiceFile {
	private GenMain gMain;
	private GenBeanFile beanFile;
	private GenBeanDTOFile beanDtoFile;
	private String packageName;
	private String className;
	private String fileName;
	private String classQuaName; //全称
	
	public void generate(){
		//包名
		packageName =
				GenerateConfig.BASE_PACKAGE_NAME + "." + GenerateConfig.PROJECT_NAME + "." + gMain.getModelName() + "." + GenerateConfig.SERVICE_NAME;
		className =
				GenerateConfig.SERVICE1_FILENAME + NameUtils.formatClassName(gMain.getTableName()) + GenerateConfig.SERVICE2_FILENAME;
		fileName = className + ".java";
		classQuaName = packageName+"."+className;
		
		File file = new File(GenerateConfig.basepathPrefix + packageName.replace(".", "/"),fileName);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		System.out.println("生成Iservice.java文件的目录"+file.getAbsolutePath());
		
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//修改文件编码
		FileUtils.changeFileEncoding(file, "UTF-8");
		
		//向文件写入内容
		FileUtils.WriteStringToFile(file, genContent());
	}

	private String genContent(){
		String lineSeparator = System.getProperty("line.separator");
		StringBuffer content = new StringBuffer();
		//写入文件注释
		content.append(NotesUtils.getFileNotes());
		
		//写入文件包名
		content.append("package ").append(packageName).append(";").append(lineSeparator);
		content.append(lineSeparator);

		//写入导入的包名
		content.append("import ").append(beanFile.getClassQuaName()).append(";").append(lineSeparator);
		content.append("import ").append(beanDtoFile.getClassQuaName()).append(";").append(lineSeparator);
		content.append("import ").append(GenerateConfig.BASE_PACKAGE_NAME + "." + GenerateConfig.PROJECT_NAME).append(".common.page.PageQuery;").append(lineSeparator);
        content.append("import  com.javayh.common.page.PageQuery;").append(lineSeparator);
		content.append(lineSeparator);

		//写入文件注释
		content.append(NotesUtils.getTypeNotes(gMain.getTableDesc()+"iService"));
		
		//写入文件名
		content.append("public interface ").append(className).append(" {").append(lineSeparator);

		//写入分页查找方法
		content.append(NotesUtils.getMethodNotes("根据条件查找-分页"));
        content.append("\t").append("PageQuery<").append(beanFile.getClassName()).append("> findByPage (")
                .append(beanDtoFile.getClassName() + " "+ StringUtil.toFirstUpCase(beanDtoFile.getClassName())).
                append(")").append(";").append(lineSeparator);
        content.append(lineSeparator);
//		content.append("\t").append("PageInfo<").append(beanFile.getClassName()).append("> findByPage (")
//				.append("PageQuery pageQuery, ")
//				.append(beanFile.getClassName() + " "+ StringUtil.toFirstUpCase(beanFile.getClassName())).
//				append(")").append(";").append(lineSeparator);
//		content.append(lineSeparator);

		//写入单个查找方法
		content.append(NotesUtils.getMethodNotes("根据id查询"));
		content.append("\t").append(beanFile.getClassName()).append(" findById(Integer ").append(gMain.getStructureList().get(0).getFieldName()).append(")").append(";").append(lineSeparator);
		content.append(lineSeparator);
		
		//写入增加方法
		content.append(lineSeparator);
		content.append(NotesUtils.getMethodNotes("添加数据"));
		content.append("\t").append("int insert(").append(beanFile.getClassName()).append(" ").append(NameUtils.formatName(gMain.getTableName())).append(")").append(";").append(lineSeparator);
		content.append(lineSeparator);

		//写入修改方法
		content.append(NotesUtils.getMethodNotes("修改数据"));
		content.append("\t").append("int update(").append(beanFile.getClassName()).append(" ").append(NameUtils.formatName(gMain.getTableName())).append(")").append(";").append(lineSeparator);
		content.append(lineSeparator);

		//写入删除方法
		content.append(NotesUtils.getMethodNotes("根据id删除"));
		content.append("\t").append("int deleteById(Integer ").append(gMain.getStructureList().get(0).getFieldName()).append(")").append(";").append(lineSeparator);
		content.append(lineSeparator);
		content.append("}");
		return content.toString();
	}

	/**
	 * @param gMain
	 * @param beanFile
	 */
	public GenIServiceFile(GenMain gMain, GenBeanFile beanFile, GenBeanDTOFile beanDTOFile) {
		super();
		this.gMain = gMain;
		this.beanFile = beanFile;
		this.beanDtoFile = beanDTOFile;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @return the classQuaName
	 */
	public String getClassQuaName() {
		return classQuaName;
	}

}
