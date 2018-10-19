package com.test.server;

import com.coder.hello.client.Time;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 服务器解码器连接建立时发送当前时间
 * 
 * @author zyk
 *
 */
public class TimeServerHandlerPOJO extends ChannelInboundHandlerAdapter {

	/**
	 * 连接建立的时候并且准备通信时被调用
	 */
	@Override
	public void channelActive(io.netty.channel.ChannelHandlerContext ctx) throws Exception {
		// 发送当前时间信息
		ChannelFuture f = ctx.writeAndFlush(new Time());
		// 发送完毕后关闭channel
		f.addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
