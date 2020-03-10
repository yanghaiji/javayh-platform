package com.javayh.client.heart;

import com.javayh.client.encode.HeartbeatEncode;
import com.javayh.client.handle.EchoClientHandle;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * <p>
 *
 * </p>
 * @version 1.0.0
 * @author Dylan-haiji
 * @since 2020/3/10
 */
public class CustomerHandleInitializer extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline()
                //10 秒没发送消息 将IdleStateHandler 添加到 ChannelPipeline 中
                .addLast(new IdleStateHandler(0, 10, 0))
                .addLast(new HeartbeatEncode())
                .addLast(new EchoClientHandle());
    }
}
