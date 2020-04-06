package com.javayh.generator.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

/**
 * 创建文件工具类
 */
public class FileUtils {

	public static void WriteStringToFile(File file, String content) {
		try {
			PrintStream ps = new PrintStream(new FileOutputStream(file));
			ps.println(content);// 往文件里写入字符串
			ps.close();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void changeFileEncoding(File file, String encoding) {
		try {
			OutputStreamWriter osw = new OutputStreamWriter(
					new FileOutputStream(file, true), encoding);
			osw.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
