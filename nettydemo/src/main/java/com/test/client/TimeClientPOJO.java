package com.test.client;

import java.nio.charset.Charset;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClientPOJO {
	
	public static void main(String[] args) {
		String host = "127.0.0.1";
		int port = 8080;
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					// TODO Auto-generated method stub
					ch.pipeline().addLast(new TimeDecoderPOJO(), new TimeClientHandlerPOJO());
				}
			});
			
			ChannelFuture f = b.connect(host, port).sync();
			
			f.addListener(new ChannelFutureListener() {
				public void operationComplete(ChannelFuture future) throws Exception {
					if (future.isSuccess()) { // 连接成功
						ByteBuf buf = Unpooled.copiedBuffer("hello", // 写数据
								Charset.defaultCharset());
						ChannelFuture wf = future.channel().writeAndFlush(buf); // 发送数据
					} else {
						// 打印错误
						Throwable cause = future.cause();
						cause.printStackTrace();
					}
				}
			});
			
			f.channel().closeFuture().sync();
			
		} catch (Exception e) {
			workerGroup.shutdownGracefully();
		}
	}
}
