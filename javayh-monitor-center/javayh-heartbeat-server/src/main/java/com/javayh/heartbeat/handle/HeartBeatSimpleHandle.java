package com.javayh.heartbeat.handle;

import com.javayh.common.constant.ConstantUtils;
import com.javayh.common.result.MessageBody;
import com.javayh.heartbeat.util.NettySocketHolder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * <p>
 * 服务端
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-10 13:52
 */
@Slf4j
public class HeartBeatSimpleHandle extends SimpleChannelInboundHandler<MessageBody> {

	private static final ByteBuf HEART_BEAT = Unpooled.unreleasableBuffer(
			Unpooled.copiedBuffer(
			        MessageBody.builder()
                            .msgId(20200202L)
                            .msg("pong")
                            .appName("HeartBeat-Server")
                            .createDate(new Date()).build().toString(),
					CharsetUtil.UTF_8));

	/**
	 * <p>
	 * 链接验证
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/10
	 * @param context
	 * @param messageBody
	 * @return void
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext context, MessageBody messageBody)
			throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("服务端消息接收成功==>").append(messageBody);
		log.info(sb.toString());
		// 保存客户端与 Channel 之间的关系
		NettySocketHolder.put(messageBody.getAppName(),
				(NioSocketChannel) context.channel());
	}

	/**
	 * <p>
	 * 取消绑定
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/10
	 * @param ctx
	 * @return void
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		NettySocketHolder.remove((NioSocketChannel) ctx.channel());
	}

	/**
	 * <p>
	 * 尝试建立链接
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/10
	 * @param ctx
	 * @param evt
	 * @return void
	 */
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
			if (idleStateEvent.state() == IdleState.READER_IDLE) {
				log.info("已经5秒没有收到信息！");
				// 向客户端发送消息
				ctx.writeAndFlush(HEART_BEAT)
						.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
			}
		}
		super.userEventTriggered(ctx, evt);
	}

}
