package com.javayh.common.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 *      心跳健康消息体
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-10 13:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageBody implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long msgId;
    private String msg;
}
