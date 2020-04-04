package com.javayh.generator.util;

import com.javayh.generator.config.GenerateConfig;

import java.util.Date;

/**
 * 注释配置工具
 */
public class NotesUtils {
	
	public static String getFileNotes(){
		String lineSeparator = System.getProperty("line.separator");
		StringBuffer content = new StringBuffer();
		content.append("/**").append(lineSeparator);
		content.append(" * 版权所属： ").append(GenerateConfig.ORG_NAME).append(lineSeparator);
		content.append(" * 作者：").append(GenerateConfig.fileAuthor).append(lineSeparator);
		content.append(" * 版本：V1.0").append(lineSeparator);
		content.append(" * 创建日期：").append(TimeUtils.formatDate(new Date(), "yyyy-MM-dd")).append(lineSeparator);
		content.append(" * 修改日期：").append(TimeUtils.formatDate(new Date(), "yyyy-MM-dd")).append(lineSeparator);
		content.append(" */").append(lineSeparator);
		return content.toString();
	}
	
	public static String getTypeNotes(){
		return getTypeNotes("");
	}
	
	public static String getTypeNotes(String desc){
		String lineSeparator = System.getProperty("line.separator");
		StringBuffer content = new StringBuffer();
		content.append("/**").append(lineSeparator);
		content.append(" * <p> ");
		content.append(" *  ").append(desc).append(lineSeparator);
		content.append(" * </p> ");
		content.append(" * @author：").append(GenerateConfig.classAuthor).append(lineSeparator);
		content.append(" * @version：V1.0").append(lineSeparator);
		content.append(" * @since：").append(TimeUtils.formatDate(new Date(), "yyyy-MM-dd")).append(lineSeparator);
		content.append(" */").append(lineSeparator);
		return content.toString();
	}
	
	public static String getMethodNotes(String str){
		String lineSeparator = System.getProperty("line.separator");
		StringBuffer content = new StringBuffer();
		content.append("\t").append("/**").append(lineSeparator);
		content.append("\t").append(" *  ").append(str).append(lineSeparator);
		content.append("\t").append(" */").append(lineSeparator);
		return content.toString();
	}
}
