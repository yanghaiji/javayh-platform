package com.javayh.heartbeat.encode;

import com.javayh.common.result.MessageBody;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * <p>
 * 服务端解码器
 * </p>
 *
 * @version 1.0.0
 * @author Dylan-haiji
 * @since 2020/3/10
 */
public class HeartbeatDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf,
			List<Object> list) throws Exception {
		long id = byteBuf.readLong();
		byte[] bytes = new byte[byteBuf.readableBytes()];
		byteBuf.readBytes(bytes);
		String content = new String(bytes);
		MessageBody body = new MessageBody();
		body.setMsgId(id);
		body.setMsg(content);
		list.add(body);
	}

}
