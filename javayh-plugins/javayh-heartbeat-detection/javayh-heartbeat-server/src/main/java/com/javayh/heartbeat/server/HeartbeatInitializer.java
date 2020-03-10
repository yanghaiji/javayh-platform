package com.javayh.heartbeat.server;

import com.javayh.heartbeat.encode.HeartbeatDecoder;
import com.javayh.heartbeat.handle.HeartBeatSimpleHandle;
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
public class HeartbeatInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline()
                //五秒没有收到消息 将IdleStateHandler 添加到 ChannelPipeline 中
                .addLast(new IdleStateHandler(5, 0, 0))
                .addLast(new HeartbeatDecoder())
                .addLast(new HeartBeatSimpleHandle());
    }
}
