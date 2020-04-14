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
package com.javayh.common.i18n;

import com.javayh.common.result.ResultData;
import com.javayh.common.util.log.Log;
import com.javayh.common.util.servlet.RequestUtils;
import com.javayh.common.util.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 国际化处理
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-27 16:44
 */
@ControllerAdvice(basePackages = { "com.javayh" })
public class I18nResponseBodyAdvice implements ResponseBodyAdvice<Object> {

	@Override
	public Object beforeBodyWrite(Object obj, MethodParameter method, MediaType type,
			Class<? extends HttpMessageConverter<?>> converter, ServerHttpRequest request,
			ServerHttpResponse response) {
		try {
			if (obj instanceof ResultData) {
				ResultData result = (ResultData) obj;
				String resultCode = result.getMsg();
				if (resultCode != null) {
					HttpServletRequest req = RequestUtils.getRequest();
					String i18nMsg = getMessage(req, resultCode);
					if (!StringUtils.isEmpty(i18nMsg)) {
						result.setMsg(i18nMsg);
					}
				}
			}
		}
		catch (Exception e) {
			Log.error("返回值国际化拦截异常", e.getStackTrace());
		}
		return obj;
	}

	@Override
	public boolean supports(MethodParameter arg0,
			Class<? extends HttpMessageConverter<?>> arg1) {
		return true;
	}

	/**
	 * 返回国际化的值
	 * @param request
	 * @param key
	 * @return
	 */
	public String getMessage(HttpServletRequest request, String key) {
		String value = "";
		try {
			RequestContext requestContext = new RequestContext(request);
			value = requestContext.getMessage(key);
		}
		catch (Exception e) {
			Log.error(e.getMessage(), e.getStackTrace());
			value = "";
		}
		return value;
	}

}
