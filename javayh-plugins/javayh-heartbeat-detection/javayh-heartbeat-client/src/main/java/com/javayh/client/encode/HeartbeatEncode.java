package com.javayh.client.encode;

import com.javayh.common.result.MessageBody;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * <p>
 *       客户端编码器
 * </p>
 * @version 1.0.0
 * @author Dylan-haiji
 * @since 2020/3/10
 */
public class HeartbeatEncode extends MessageToByteEncoder<MessageBody> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MessageBody messageBody, ByteBuf byteBuf) throws Exception {
        byteBuf.writeLong(messageBody.getMsgId()) ;
        byteBuf.writeBytes(messageBody.getMsg().getBytes()) ;
    }
}
