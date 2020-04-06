package com.javayh.generator.file;

import com.javayh.generator.config.GenerateConfig;
import com.javayh.generator.util.FileUtils;
import com.javayh.generator.util.NameUtils;
import com.javayh.generator.util.NotesUtils;
import com.javayh.generator.util.StringUtil;

import java.io.File;
import java.io.IOException;

/**
 * 生成接口实现类
 */
public class GenServiceImplFile {

	private GenMain gMain;

	private GenBeanFile beanFile;

	private GenBeanDTOFile beanDTOFile;

	private GenMapperJavaFile mapperFile;

	private GenIServiceFile serviceFile;

	private String packageName;

	private String className;

	private String fileName;

	public void generate() {
		// 包名
		packageName = GenerateConfig.BASE_PACKAGE_NAME + "." + GenerateConfig.PROJECT_NAME
				+ "." + gMain.getModelName() + "." + GenerateConfig.IMPL_NAME;
		className = NameUtils.formatClassName(gMain.getTableName())
				+ GenerateConfig.IMPL_FILENAME;
		fileName = className + ".java";

		File file = new File(
				GenerateConfig.basepathPrefix + packageName.replace(".", "/"), fileName);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		System.out.println("生成serviceImpl.java文件的目录" + file.getAbsolutePath());

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
		content.append("import org.springframework.beans.factory.annotation.Autowired;")
				.append(lineSeparator);
		content.append("import org.springframework.stereotype.Service;")
				.append(lineSeparator);
		content.append("import com.github.pagehelper.PageHelper;").append(lineSeparator);
		content.append("import java.util.List;").append(lineSeparator);
		content.append("import com.javayh.mybatis.page.PageQuery;").append(lineSeparator);
		content.append("import ").append(beanFile.getClassQuaName()).append(";")
				.append(lineSeparator);
		content.append("import ").append(beanDTOFile.getClassQuaName()).append(";")
				.append(lineSeparator);
		content.append("import ").append(mapperFile.getClassQuaName()).append(";")
				.append(lineSeparator);
		content.append("import ").append(serviceFile.getClassQuaName()).append(";")
				.append(lineSeparator);
		content.append(lineSeparator);

		// 写入文件注释
		content.append(NotesUtils.getTypeNotes(gMain.getTableDesc() + "ServiceImpl"));

		// 写入文件名
		content.append("@Service").append(lineSeparator);
		content.append("public class ").append(className).append(" implements ")
				.append(serviceFile.getClassName()).append(" {").append(lineSeparator);

		// 注入mapper
		String mapperName = NameUtils.formatName(gMain.getTableName()) + "Mapper";
		content.append(lineSeparator);
		content.append("\t").append("@Autowired").append(lineSeparator);
		content.append("\t").append("private ").append(mapperFile.getClassName())
				.append(" ").append(mapperName).append("; ").append(lineSeparator);
		content.append(lineSeparator);

		// 写入单个查找方法
		content.append(NotesUtils.getMethodNotes("根据条件查询-分页"));
		content.append("\t").append("@Override").append("\r");
		content.append("\t").append("public ")
				.append("PageQuery<" + beanFile.getClassName() + ">")
				.append(" findByPage (")
				.append(beanDTOFile.getClassName() + " "
						+ StringUtil.toFirstUpCase(beanDTOFile.getClassName()))
				.append(")").append("{").append(lineSeparator);
		content.append("\t\t").append("PageHelper.startPage(")
				.append(StringUtil.toFirstUpCase(beanDTOFile.getClassName())
						+ ".getPageNo()")
				.append("," + StringUtil.toFirstUpCase(beanDTOFile.getClassName())
						+ ".getPageSize());")
				.append(lineSeparator);
		String beanName = StringUtil.toFirstUpCase(beanFile.getClassName()) + "List";
		content.append("\t\t")
				.append("List<" + beanFile.getClassName() + ">" + " " + beanName)
				.append(" = ").append(mapperName).append(".findByPage(")
				.append(StringUtil.toFirstUpCase(beanDTOFile.getClassName())).append(");")
				.append(lineSeparator);
		content.append("\t\t").append("PageQuery page = ")
				.append("PageQuery.ofPage(" + beanName + ");").append(lineSeparator);
		content.append("\t\t").append("return ").append("page;").append(lineSeparator);
		content.append("\t").append("}").append(lineSeparator);
		content.append(lineSeparator);

		// content.append("\t").append("public
		// ").append("PageInfo<"+beanFile.getClassName()+">").append(" findByPage (")
		// .append("PageQuery pageQuery, ")
		// .append(beanFile.getClassName() + " "+
		// StringUtil.toFirstUpCase(beanFile.getClassName()))
		// .append(")").append("{").append(lineSeparator);
		// content.append("\t\t").append("PageUtil.page(pageQuery);").append(lineSeparator);

		// String beanName = StringUtil.toFirstUpCase(beanFile.getClassName())+"List";
		// content.append("\t\t").append("List<" + beanFile.getClassName()+">"+ "
		// "+beanName).append(" =
		// ").append(mapperName).append(".findByPage(").append(StringUtil.toFirstUpCase(beanFile.getClassName())).append(");").append(lineSeparator);
		// content.append("\t\t").append("PageInfo<"+beanFile.getClassName()+"> pageInfo =
		// ").append("new PageInfo<>("+beanName+");").append(lineSeparator);
		// content.append("\t\t").append("return
		// ").append("pageInfo;").append(lineSeparator);
		// content.append("\t").append("}").append(lineSeparator);
		// content.append(lineSeparator);

		// 写入单个查找方法
		content.append(NotesUtils.getMethodNotes("根据id查询"));
		content.append("\t").append("@Override").append("\r");
		content.append("\t").append("public ").append(beanFile.getClassName())
				.append(" findById(Integer ")
				.append(gMain.getStructureList().get(0).getFieldName()).append(")")
				.append("{").append(lineSeparator);
		content.append("\t\t").append("return ").append(mapperName).append(".findById(")
				.append(gMain.getStructureList().get(0).getFieldName()).append(");")
				.append(lineSeparator);
		content.append("\t").append("}").append(lineSeparator);
		content.append(lineSeparator);

		// 写入增加方法
		content.append(NotesUtils.getMethodNotes("添加数据"));
		content.append("\t").append("@Override").append("\r");
		content.append("\t").append("public int insert(").append(beanFile.getClassName())
				.append(" ").append(NameUtils.formatName(gMain.getTableName()))
				.append(")").append("{").append(lineSeparator);
		content.append("\t\t").append("return ").append(mapperName).append(".insert(")
				.append(NameUtils.formatName(gMain.getTableName())).append(");")
				.append(lineSeparator);
		content.append("\t").append("}").append(lineSeparator);
		content.append(lineSeparator);

		// 写入修改方法
		content.append(NotesUtils.getMethodNotes("修改数据"));
		content.append("\t").append("@Override").append("\r");
		content.append("\t").append("public int update(").append(beanFile.getClassName())
				.append(" ").append(NameUtils.formatName(gMain.getTableName()))
				.append(")").append("{").append(lineSeparator);
		content.append("\t\t").append("return ").append(mapperName).append(".update(")
				.append(NameUtils.formatName(gMain.getTableName())).append(");")
				.append(lineSeparator);
		content.append("\t").append("}").append(lineSeparator);
		content.append(lineSeparator);

		// 写入删除方法
		content.append(NotesUtils.getMethodNotes("根据id删除"));
		content.append("\t").append("@Override").append("\r");
		content.append("\t").append("public int deleteById(Integer ")
				.append(gMain.getStructureList().get(0).getFieldName()).append(")")
				.append("{").append(lineSeparator);
		content.append("\t\t").append("return ").append(mapperName).append(".deleteById(")
				.append(gMain.getStructureList().get(0).getFieldName()).append(");")
				.append(lineSeparator);
		content.append("\t").append("}").append(lineSeparator);
		content.append(lineSeparator);
		content.append("}");
		return content.toString();
	}

	/**
	 * @param gMain
	 * @param beanFile
	 * @param mapperFile
	 */
	public GenServiceImplFile(GenMain gMain, GenBeanFile beanFile,
			GenBeanDTOFile beanDTOFile, GenMapperJavaFile mapperFile,
			GenIServiceFile serviceFile) {
		super();
		this.gMain = gMain;
		this.beanFile = beanFile;
		this.beanDTOFile = beanDTOFile;
		this.mapperFile = mapperFile;
		this.serviceFile = serviceFile;
	}

}
