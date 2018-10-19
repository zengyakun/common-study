package com.test.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HelloServer {

	private int port;

	public HelloServer(int port) {
		this.port = port;
	}

	/**
	 * Netty 服务器的通信步骤为：
	 * 
	 * 1.创建两个NIO线程组，一个专门用于接收来自客户端的连接，另一个则用于处理已经被接收的连接。
	 * 2.创建一个ServerBootstrap对象，配置Netty的一系列参数，例如接受传出数据的缓存大小等。
	 * 3.创建一个用于实际处理数据的类ChannelInitializer，进行初始化的准备工作，比如设置接受传出数据的字符集、格式以及实际处理数据的接口。
	 * 4.绑定端口，执行同步阻塞方法等待服务器端启动即可。
	 */
	public void run() {
		// 用来接收进来的连接
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		// 用来处理已经被接收的连接
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		System.out.println("准备运行端口：" + port);

		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							// 自定义处理类
							ch.pipeline().addLast(new HelloServerHandler());
						}
					}).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

			// 绑定端口，开始接收进来的连接
			ChannelFuture f = b.bind(port).sync();
			// 等待服务端socket关闭
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) {
		int port = 10110;
		new HelloServer(port).run();
	}
}
