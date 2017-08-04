/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.koogame.redis;

/**
 *  JRedisCache 异常类
 */
@SuppressWarnings("serial")
public class JRedisCacheException extends RuntimeException {

	public JRedisCacheException(String s) {
		super(s);
	}

	public JRedisCacheException(String s, Throwable e) {
		super(s, e);
	}

	public JRedisCacheException(Throwable e) {
		super(e);
	}
	
}
