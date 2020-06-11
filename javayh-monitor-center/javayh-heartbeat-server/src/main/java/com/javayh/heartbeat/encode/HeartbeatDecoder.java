package com.javayh.heartbeat.encode;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.javayh.common.result.MessageBody;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
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

    private Kryo kryo = new Kryo();

	@Override
	protected void decode(ChannelHandlerContext handlerContext, ByteBuf byteBuf,
			List<Object> list) throws Exception {
        //标记一下当前的readIndex的位置
        byteBuf.markReaderIndex();
        // 读取传送过来的消息的长度。ByteBuf 的readInt()方法会让他的readIndex增加4
        int dataLength = byteBuf.readInt();
        // 我们读到的消息体长度为0，这是不应该出现的情况，这里出现这情况，关闭连接。
        if (dataLength < 0) {
            handlerContext.close();
        }
        //读到的消息体长度如果小于我们传送过来的消息长度，则resetReaderIndex.
        //这个配合markReaderIndex使用的。把readIndex重置到mark的地方
        if (byteBuf.readableBytes() < dataLength) {
            byteBuf.resetReaderIndex();
            return;
        }
        //传输正常
        byte[] body = new byte[dataLength];
        byteBuf.readBytes(body);
        //将byte数据转化为我们需要的对象
        Object o = convertToObject(body);
        list.add(o);
	}


    private Object convertToObject(byte[] body) {
        Input input = null;
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(body);
            input = new Input(bais);

            return kryo.readObject(input, MessageBody.class);
        } catch (KryoException e) {
            e.printStackTrace();
        }finally{
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(bais);
        }

        return null;
    }

}
