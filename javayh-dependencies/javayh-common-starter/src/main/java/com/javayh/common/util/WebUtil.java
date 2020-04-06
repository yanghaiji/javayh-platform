package com.javayh.common.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.javayh.common.util.spring.SpringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Http与Servlet工具类.
 *
 */
public class WebUtil extends ServletUtil {

	/**
	 * 设置让浏览器弹出下载对话框的Header.
	 * @param fileName 下载后的文件名.
	 */
	public static void setFileDownloadHeader(HttpServletResponse response,
			String fileName) {
		try {
			// 中文文件名支持
			String encodedfileName = new String(fileName.getBytes(), "ISO8859-1");
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
					"attachment; filename=\"" + encodedfileName + "\"");
		}
		catch (UnsupportedEncodingException e) {
			e.getMessage();
		}
	}

	/**
	 * 获取表单数据request中获得参数，并返回可读的Map application/x-www-form-urlencode application/json
	 * application/json;charset=UTF-8
	 * @param request
	 * @return
	 */
	public static Map<String, String> getFormMap(HttpServletRequest request) {
		String contentType = request
				.getHeader(org.springframework.http.HttpHeaders.CONTENT_TYPE);
		Map<String, String> returnMap = new HashMap();
		if (contentType != null
				&& contentType.contains(MediaType.MULTIPART_FORM_DATA_VALUE)) {
			// form-data表单
			MultipartResolver multipartResolver = SpringUtils
					.getBean(MultipartResolver.class);
			MultipartHttpServletRequest multiReq = multipartResolver
					.resolveMultipart(request);
			returnMap = conventMap(multiReq.getParameterMap());
		}
		else if (MediaType.APPLICATION_JSON_VALUE.equals(contentType)
				|| MediaType.APPLICATION_JSON_UTF8_VALUE.equals(contentType)) {
			// json表单
			String body = getBody(request);
			if (StrUtil.isNotBlank(body)) {
				try {
					returnMap = JSONObject.parseObject(body, Map.class);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		else {
			// 普通表单
			returnMap = conventMap(request.getParameterMap());
		}
		// 参数Map
		return returnMap;
	}

	private static Map conventMap(Map map) {
		Map<String, String> returnMap = new HashMap();
		// 返回值Map
		Iterator entries = map.entrySet().iterator();
		Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			}
			else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				if (values != null && values.length > 0) {
					for (int i = 0; i < values.length; i++) {
						value = values[i] + ",";
					}
					value = value.substring(0, value.length() - 1);
				}
			}
			else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		return returnMap;
	}

	/**
	 * 是否是Ajax异步请求
	 * @param request
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		return (request.getHeader("X-Requested-With") != null && "XMLHttpRequest"
				.equals(request.getHeader("X-Requested-With").toString()))
				|| (request.getHeader("Content-Type") != null && request
						.getHeader("Content-Type").startsWith("application/json"));
	}

	/**
	 * 客户端返回JSON字符串
	 * @param response
	 * @param object
	 * @return
	 */
	public static void writeJson(HttpServletResponse response, Object object) {
		writeJson(response, JSON.toJSONString(object),
				MediaType.APPLICATION_JSON_UTF8_VALUE);
	}

	/**
	 * 客户端返回字符串
	 * @param response
	 * @param string
	 * @return
	 */
	public static void writeJson(HttpServletResponse response, String string,
			String type) {
		try {
			response.setContentType(type);
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(string);
			response.getWriter().flush();
			response.getWriter().close();
		}
		catch (IOException e) {
		}
	}

	public static String getServerUrl(HttpServletRequest request) {
		String url = request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath();
		return url;
	}

	public static String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}

	public static HttpServletRequest getHttpServletRequest() {
		try {
			return ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest();
		}
		catch (Exception e) {
			return null;
		}
	}

	public static Map<String, String> getHttpHeaders(HttpServletRequest request) {
		Map<String, String> map = new LinkedHashMap<>();
		if (request != null) {
			Enumeration<String> enumeration = request.getHeaderNames();
			if (enumeration != null) {
				while (enumeration.hasMoreElements()) {
					String key = enumeration.nextElement();
					String value = request.getHeader(key);
					map.put(key, value);
				}
			}
		}

		return map;
	}

	/**
	 * url追加参数
	 * @param url 传入的url
	 * ex："http://exp.kunnr.com/so/index.html?kunnrId=16&userProfile=16#/app/home"
	 * @param name 参数名
	 * @param value 参数值
	 */
	public static String appendURIParam(String url, String name, String value) {
		url += (url.indexOf('?') == -1 ? '?' : '&');
		url += name + '=' + value;
		return URLUtil.encode(url);
	}

	/**
	 * 组装新的URL
	 * @param url
	 * @param map
	 * @return
	 */
	public static String appendURIParam(String url, Map<String, String> map) {
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			url = appendURIParam(url, entry.getKey(), entry.getValue());
		}
		return url;
	}

}
