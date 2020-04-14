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
