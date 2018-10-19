package com.test.server.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class SimpleChatServer {

	private int port;

	public SimpleChatServer(int port) {
		this.port = port;
	}

	public void run() {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap sb = new ServerBootstrap();
			sb.group(bossGroup, workerGroup);
			sb.channel(NioServerSocketChannel.class);
			sb.childHandler(new SimpleChatServerInitializer());
			sb.option(ChannelOption.SO_BACKLOG, 128);
			sb.childOption(ChannelOption.SO_KEEPALIVE, true);

			System.out.println("SimpleChatServer 启动了");
			ChannelFuture future = sb.bind(port).sync();
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
			System.out.println("SimpleChatServer 关闭了");
		}
	}

	public static void main(String[] args) {
		int port = 8080;
		new SimpleChatServer(port).run();
	}
}
