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
package com.javayh.common.filter;

import com.javayh.common.constant.ConstantUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * <p>
 * 敏感词过滤
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-30 11:37
 */
@Slf4j
@Data
public class SensitiveWordFilter {

	/** 初始化 */
	private StringBuilder replaceAll;

	/** 设置编码 */
	private String encoding = ConstantUtils.UTF;

	/** 和谐的字码 */
	private String replacedWord = "*";

	/** 初始长度 */
	private int size = 500;

	/** 需要过滤的初始化文本 */
	private String fileName = "SensitiveWord.txt";

	/** 词库 */
	private CopyOnWriteArraySet<String> onWriteArraySet;

	/**
	 * <p>
	 * 文件要求路径在src或resource下，默认文件名为SensitiveWord.txt
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/30
	 * @param fileName 词库文件名(含后缀)
	 */
	public SensitiveWordFilter(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * <p>
	 *
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/30
	 * @param replacedWord 敏感词被转换的字符
	 * @param size 初始转义容量
	 */
	public SensitiveWordFilter(String replacedWord, int size) {
		this.replacedWord = fileName;
		this.size = size;
	}

	public SensitiveWordFilter() {
	}

	/**
	 * <p>
	 *
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/30
	 * @param str 将要被过滤信息
	 * @return java.lang.String 过滤后的信息
	 */
	public String filterInfo(String str) {
		StringBuilder buffer = new StringBuilder(str);
		Map<Integer, Integer> hash = new ConcurrentHashMap<Integer, Integer>(
				onWriteArraySet.size());
		String temp;
		for (String s : onWriteArraySet) {
			temp = s;
			int findIndexSize = 0;
			for (int start = -1; (start = buffer.indexOf(temp, findIndexSize)) > -1;) {
				// 从已找到的后面开始找
				findIndexSize = start + temp.length();
				// 起始位置
				Integer mapStart = hash.get(start);
				// 满足1个，即可更新map
				if (mapStart == null || findIndexSize > mapStart) {
					hash.put(start, findIndexSize);
				}
			}
		}
		Collection<Integer> values = hash.keySet();
		for (Integer startIndex : values) {
			Integer endIndex = hash.get(startIndex);
			buffer.replace(startIndex, endIndex,
					replaceAll.substring(0, endIndex - startIndex));
		}
		hash.clear();
		return buffer.toString();
	}

	/**
	 * 初始化敏感词库
	 */
	public void initializationWork() {
		replaceAll = new StringBuilder(size);
        replaceAll.append(String.valueOf(replacedWord).repeat(Math.max(0, size)));
		// 加载词库
		onWriteArraySet = new CopyOnWriteArraySet<>();
		InputStreamReader read = null;
		BufferedReader bufferedReader = null;
		try {
			read = new InputStreamReader(Objects.requireNonNull(SensitiveWordFilter.class
					.getClassLoader().getResourceAsStream(fileName)), encoding);
			bufferedReader = new BufferedReader(read);
			for (String txt = null; (txt = bufferedReader.readLine()) != null;) {
				if (!onWriteArraySet.contains(txt)) {
					onWriteArraySet.add(txt);
				}
			}
		}
		catch (IOException e) {
			log.error("敏感词过滤 " + e);
		}
		finally {
			try {
				if (null != bufferedReader) {
					bufferedReader.close();
				}
			}
			catch (IOException e) {
				log.error("敏感词过滤 文件流关闭 " + e);
			}
			try {
				if (null != read) {
					read.close();
				}
			}
			catch (IOException e) {
				log.error("敏感词过滤 文件流关闭 " + e);
			}
		}
	}

	 public static void main(String args[]) {
	 SensitiveWordFilter sw = new SensitiveWordFilter();
	 sw.initializationWork();
	 long startNuder = System.currentTimeMillis();
	 String str = "射手你是sb吗?打野你是傻逼?";
	 System.out.println("被检测字符长度:" + str.length());
	 str = sw.filterInfo(str);
	 long endNumber = System.currentTimeMillis();
	 System.out.println("耗时(毫秒):" + (endNumber - startNuder));
	 System.out.println("过滤之后:" + str);
	 }

}
