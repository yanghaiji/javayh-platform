package com.javayh.client.handle;

import com.javayh.common.result.MessageBody;
import com.javayh.common.util.spring.SpringUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *
 * </p>
 * @version 1.0.0
 * @author Dylan-haiji
 * @since 2020/3/10
 */
@Slf4j
public class EchoClientHandle extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if (idleStateEvent.state() == IdleState.WRITER_IDLE) {
                log.info("已经10秒没收到消息了");
                //向服务端发送消息
                MessageBody heartBeat = SpringUtils.getBean("heartBeat",MessageBody.class);
                ctx.writeAndFlush(heartBeat).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }

        }
        super.userEventTriggered(ctx, evt);
    }

    /**
     *  每当从服务端接收到新数据时，都会使用收到的消息调用此方法 channelRead0(),在此示例中，接收消息的类型是ByteBuf。
     * @param channelHandlerContext
     * @param byteBuf
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        //从服务端收到消息时被调用
        log.info("客户端收到消息={}", byteBuf.toString(CharsetUtil.UTF_8));
    }
}
