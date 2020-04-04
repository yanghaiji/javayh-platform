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
	public static String formatName(String name){
		String[] nameArr = name.split("_");
		String result = "";
		for(int i=0;i<nameArr.length;i++){
			if(i==0){
				result += nameArr[i].toLowerCase();
			}else{
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
	public static String formatClassName(String name){
		String[] nameArr = name.split("_");
		String result = "";
		for(int i=0;i<nameArr.length;i++){
			result += nameArr[i].substring(0, 1).toUpperCase();
			result += nameArr[i].substring(1).toLowerCase();
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(formatName("BASE_PACKAGE_NAME"));
	}
}
