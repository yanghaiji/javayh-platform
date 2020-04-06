package com.javayh.generator;

import com.javayh.generator.file.GenMain;

/**
 * 执行逆向测试类
 */
public class GeneratorMain {

	/**
	 * 时间工具类：util.TimeUtils 命名方式工具类：util.NameUtils 注释信息工具类：util.NotesUtils
	 * 数据库、包名、类的前后缀的配置文件：config.GenerateConfig new GenMain("数据库对应的表名","代码生成所在的包名",false)
	 * @param args
	 */
	public static void main(String[] args) {
		GenMain gMain = new GenMain("sys_logistics", "sys", false);
		try {
			gMain.generate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
