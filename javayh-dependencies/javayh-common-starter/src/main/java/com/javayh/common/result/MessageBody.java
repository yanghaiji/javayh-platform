package com.javayh.common.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 心跳健康消息体
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-10 13:56
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageBody implements Serializable {

	private static final long serialVersionUID = 1L;
    /**服务标识*/
	private Long msgId;
    /**发送的消息*/
	private String msg;
	/**服务名*/
	private String appName;
	/**接受消息的时间*/
	private Date createDate;

}
