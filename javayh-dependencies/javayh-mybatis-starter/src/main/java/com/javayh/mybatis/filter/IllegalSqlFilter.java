package com.javayh.mybatis.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.javayh.mybatis.uitl.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.MapperMethod;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 过滤注入
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-31 13:31
 */
@Slf4j
public class IllegalSqlFilter {

	private static final String REGX = "!|！|@|◎|#|＃|(\\$)|￥|%|％|(\\^)|……|(\\&)|※|(\\*)|×|(\\()|（|(\\))|）|_|——|(\\+)|＋|(\\|)|§ ";

	/**
	 * <p>
	 * 对常见的sql注入攻击进行拦截，并对注入的参数进行修改
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/31
	 * @param obj
	 * @return java.lang.Object
	 */
	public static Object sqlStrFilter(Object obj) {
		MapperMethod.ParamMap<String> paramMap = JSONObject
				.parseObject(JSON.toJSONString(obj), MapperMethod.ParamMap.class);
		for (Map.Entry<String, String> a : paramMap.entrySet()) {
			String key = a.getKey();
			String input = a.getValue().toUpperCase();
			if (input.contains("DELETE")) {
				paramMap.put(key, input.substring(0, input.indexOf(" DELETE ")));
				log.error("该参数存在SQL注入风险: {}", key + "=" + input);
			}
			else if (input.contains("ASCII")) {
				paramMap.put(key, input.substring(0, input.indexOf(" ASCII ")));
				log.error("该参数存在SQL注入风险: {}", key + "=" + input);
			}
			else if (input.contains("UPDATE")) {
				paramMap.put(key, input.substring(0, input.indexOf(" UPDATE ")));
				log.error("该参数存在SQL注入风险: {}", key + "=" + input);
			}
			else if (input.contains("SELECT")) {
				paramMap.put(key, input.substring(0, input.indexOf(" SELECT ")));
				log.error("该参数存在SQL注入风险: {}", key + "=" + input);
			}
			else if (input.contains("'")) {
				paramMap.put(key, input.substring(0, input.indexOf(" ' ")));
				log.error("该参数存在SQL注入风险: {}", key + "=" + input);
			}
			else if (input.contains("SUBSTR(")) {
				paramMap.put(key, input.substring(0, input.indexOf(" SUBSTR( ")));
				log.error("该参数存在SQL注入风险: {}", key + "=" + input);
			}
			else if (input.contains("COUNT(")) {
				paramMap.put(key, input.substring(0, input.indexOf(" COUNT( ")));
				log.error("该参数存在SQL注入风险: {}", key + "=" + input);
			}
			else if (input.contains(" OR ")) {
				paramMap.put(key, input.substring(0, input.indexOf(" OR ")));
				log.error("该参数存在SQL注入风险: {}", key + "=" + input);
			}
			else if (input.contains(" AND ")) {
				paramMap.put(key, input.substring(0, input.indexOf(" AND ")));
				log.error("该参数存在SQL注入风险: {}", key + "=" + input);
			}
			else if (input.contains("DROP")) {
				paramMap.put(key, input.substring(0, input.indexOf(" DROP ")));
				log.error("该参数存在SQL注入风险: {}", key + "=" + input);
			}
			else if (input.contains("EXECUTE")) {
				paramMap.put(key, input.substring(0, input.indexOf(" EXECUTE ")));
				log.error("该参数存在SQL注入风险: {}", key + "=" + input);
			}
			else if (input.contains("EXEC")) {
				paramMap.put(key, input.substring(0, input.indexOf(" EXEC ")));
				log.error("该参数存在SQL注入风险: {}", key + "=" + input);
			}
			else if (input.contains("TRUNCATE")) {
				paramMap.put(key, input.substring(0, input.indexOf(" TRUNCATE ")));
				log.error("该参数存在SQL注入风险: {}", key + "=" + input);
			}
			else if (input.contains("INTO")) {
				paramMap.put(key, input.substring(0, input.indexOf(" INTO ")));
				log.error("该参数存在SQL注入风险: {}", key + "=" + input);
			}
			else if (input.contains("DECLARE")) {
				paramMap.put(key, input.substring(0, input.indexOf(" DECLARE ")));
				log.error("该参数存在SQL注入风险: {}", key + "=" + input);
			}
			else if (input.contains("MASTER")) {
				paramMap.put(key, input.substring(0, input.indexOf(" MASTER ")));
				log.error("该参数存在SQL注入风险: {}", key + "=" + input);
			}
		}
		log.info("通过sql检测");
		return paramMap;
	}

	/**
	 * <p>
	 * 对非法字符进行检测
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/31
	 * @param input
	 * @return java.lang.Boolean
	 */
	public static Boolean isIllegalStr(String input) {

		if (input == null || input.trim().length() == 0) {
			return false;
		}
		input = input.trim();
		Pattern compile = Pattern.compile(REGX, Pattern.CASE_INSENSITIVE);
		Matcher matcher = compile.matcher(input);
		log.info("通过字符串检测");
		return matcher.find();
	}

	/**
	 * <p>
	 * 对常见的sql注入攻击进行拦截，并对注入的参数进行修改
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/31
	 * @param obj
	 * @return java.lang.Object
	 */
	public static Boolean sqlFilter(Object obj) {
		String input = obj.toString();
		if (input == null || input.trim().length() == 0) {
			return false;
		}
		input = input.toUpperCase();
		if (input.contains(Constant.DELETE) || input.contains(Constant.ASCII)
				|| input.contains(Constant.UPDATE) || input.contains(Constant.SELECT)
				|| input.contains(Constant.S) || input.contains(Constant.SUBSTR)
				|| input.contains(Constant.COUNT) || input.contains(Constant.OR)
				|| input.contains(Constant.AND) || input.contains(Constant.DROP)
				|| input.contains(Constant.EXECUTE) || input.contains(Constant.EXEC)
				|| input.contains(Constant.TRUNCATE) || input.contains(Constant.INTO)
				|| input.contains(Constant.DECLARE) || input.contains(Constant.MASTER)) {
			log.error("该参数存在SQL注入风险：sInput=" + input);
			return false;
		}
		log.info("通过sql检测");
		return true;
	}

}
