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

/**
 * 命名方式工具类
 */
public class NameUtils {

	/**
	 * 驼峰命名
	 * @param name
	 * @return
	 */
	public static String formatName(String name) {
		String[] nameArr = name.split("_");
		String result = "";
		for (int i = 0; i < nameArr.length; i++) {
			if (i == 0) {
				result += nameArr[i].toLowerCase();
			}
			else {
				result += nameArr[i].substring(0, 1).toUpperCase();
				result += nameArr[i].substring(1).toLowerCase();
			}
		}
		return result;
	}

	/**
	 * 首字母大写命名
	 * @param name
	 * @return
	 */
	public static String formatClassName(String name) {
		String[] nameArr = name.split("_");
		String result = "";
		for (int i = 0; i < nameArr.length; i++) {
			result += nameArr[i].substring(0, 1).toUpperCase();
			result += nameArr[i].substring(1).toLowerCase();
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(formatName("BASE_PACKAGE_NAME"));
	}

}
