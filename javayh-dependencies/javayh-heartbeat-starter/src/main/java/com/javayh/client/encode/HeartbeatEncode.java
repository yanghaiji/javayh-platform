package com.javayh.client.encode;

import com.javayh.common.result.MessageBody;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.ByteArrayOutputStream;

import org.apache.commons.io.IOUtils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Output;

/**
 * <p>
 * 客户端编码器
 * </p>
 *
 * @version 1.0.0
 * @author Dylan-haiji
 * @since 2020/3/10
 */
public class HeartbeatEncode extends MessageToByteEncoder<MessageBody> {
    private Kryo kryo = new Kryo();
	@Override
	protected void encode(ChannelHandlerContext handlerContext,
			MessageBody messageBody, ByteBuf byteBuf) throws Exception {
        //将对象转换为byte
        byte[] body = convertToBytes(messageBody);
        //读取消息的长度
        int dataLength = body.length;
        //先将消息长度写入，也就是消息头
        byteBuf.writeInt(dataLength);
        //消息体中包含我们要发送的数据
        byteBuf.writeBytes(body);
	}

    private byte[] convertToBytes(MessageBody messageBody) {

        ByteArrayOutputStream bos = null;
        Output output = null;
        try {
            bos = new ByteArrayOutputStream();
            output = new Output(bos);
            kryo.writeObject(output, messageBody);
            output.flush();

            return bos.toByteArray();
        } catch (KryoException e) {
            e.printStackTrace();
        }finally{
            IOUtils.closeQuietly(output);
            IOUtils.closeQuietly(bos);
        }
        return null;
    }
}
