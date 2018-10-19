package com.test.client;

import java.util.Date;

/**
 * 自定义时间数据类
 * 
 * @author zyk
 *
 */
public class Time {

	private final long value;

	public Time() {
		this(System.currentTimeMillis() / 1000L);
	}

	public Time(long value) {
		this.value = value;
	}

	public long value() {
		return value;
	}

	@Override
	public String toString() {
		return new Date((value()) * 1000L).toString();
	}
}
