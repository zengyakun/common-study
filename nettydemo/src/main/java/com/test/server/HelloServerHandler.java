package com.test.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * 输出接收到的消息
 * 
 * @author zyk
 *
 */
public class HelloServerHandler extends ChannelInboundHandlerAdapter {

	/**
	 * 收到数据时调用
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		try {
			ByteBuf in = (ByteBuf) msg;
			System.out.println(in.toString(CharsetUtil.UTF_8));
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}

	/**
	 * 当Netty由于IO错误或者处理器在处理事件时抛出异常时调用
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
