package com.test.server;

import com.coder.hello.client.Time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 服务器数据编码类
 * 
 * @author zyk
 *
 */
public class TimeEncoderPOJO extends MessageToByteEncoder<Time> {

	// 发送数据时调用
	@Override
	protected void encode(ChannelHandlerContext ctx, Time msg, ByteBuf out) throws Exception {
		// 只传输当前时间，精确到秒
		out.writeInt(((int) msg.value()));
	}

}
