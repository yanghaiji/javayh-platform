package com.javayh.api.web;

import com.javayh.common.qrcode.QRCodeGenerator;
import com.javayh.common.result.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-06 9:48
 */
@Api("测试 API")
@RestController
@RequestMapping(value = "/api/")
public class ApiController {

	@ApiOperation(value = "swagger测试", notes = "测试")
	@GetMapping(value = "swagger")
	public ResultData getSwagger() {
		return ResultData.success("Hello Swagger!");
	}

	@ApiOperation(value = "二维码渲染", notes = "二维码")
	@RequestMapping("/test")
	public void getQRcode(HttpServletResponse response) throws Exception {
		// 参数为二维码的内容、长、宽、响应对象
		String text = "https://blog.csdn.net/weixin_38937840";
		String logoPath = "C:\\Users\\haiyang\\Desktop\\webwxgetmsgimg.jpg";
		QRCodeGenerator.outputQRCode(text, logoPath, response);
	}


}
