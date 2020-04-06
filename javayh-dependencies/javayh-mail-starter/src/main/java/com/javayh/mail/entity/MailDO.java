package com.javayh.mail.entity;

import lombok.Data;

import java.util.Map;

/**
 * <p>
 * 邮件接收参数
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-08 15:01
 */
@Data
public class MailDO {

	/** 标题 */
	private String title;

	/** 内容 */
	private String content;

	/** 接收人邮件地址 */
	private String email;

	/** 模板名称 */
	private String templateName;

	/** 附加，value 文件的绝对地址/动态模板数据 */
	private Map<String, Object> attachment;

}
