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
