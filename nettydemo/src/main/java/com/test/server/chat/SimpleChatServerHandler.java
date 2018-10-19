package com.test.server.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 服务端处理器
 * 
 * @author zyk
 *
 */
public class SimpleChatServerHandler extends SimpleChannelInboundHandler<String> {

	public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	/**
	 * 收到新的客户端连接时调用
	 * 将客户端channel存入列表，并广播消息
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
		// 广播加入消息
		channels.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + "加入\n");
		channels.add(incoming);
	}
	
	/**
	 * 客户端连接断开时调用
	 * 广播消息
	 */
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
		channels.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + "离开\n");
		// channel会自动从ChannelGroup中删除
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		Channel incoming = ctx.channel();
		for(Channel channel : channels) {
			if(channel != incoming) {
				channel.writeAndFlush("[" + incoming.remoteAddress() + "]" + msg + "\n");
			} else {
				channel.writeAndFlush("[you]" + msg + "\n");
			}
		}
	}
	
	/**
	 * 监听到客户端活动时调用
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
		System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "在线");
	}
	
	/**
	 * 监听到客户端不活动时调用
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
		System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "掉线");
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		Channel incoming = ctx.channel();
		System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "异常");;
	}
}
