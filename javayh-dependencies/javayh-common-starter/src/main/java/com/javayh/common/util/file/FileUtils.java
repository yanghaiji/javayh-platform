package com.javayh.common.util.file;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * <p>
 * 文件操作
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-30 11:10
 */
@Slf4j
public class FileUtils {

	public static void writeFile(String content, String path) {
		// 检测文件夹是否存在，不存在则创建文件夹和文件
		createFile(path);
		FileWriter writer = null;
		try {
			writer = new FileWriter(path);
			writer.write(content);
			writer.flush();
		}
		catch (IOException e) {
			log.error("写入文件错误 " + e);
		}
		finally {
			try {
				writer.close();
			}
			catch (IOException e) {
				log.error("文件流关闭异常 " + e);
			}
		}
	}

	/**
	 * <p>
	 * 创建文件
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/30
	 * @param path 路径
	 * @return java.io.File
	 */
	private static File createFile(String path) {
		// 创建文件夹
		if (path.contains("/")) {
			String[] split = path.split("/");
			String fileName = split[split.length - 1];
			String dirPath = path.replace(fileName, "");
			File dir = new File(dirPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
		}
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}

}
