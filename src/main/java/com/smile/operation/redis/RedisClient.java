package com.smile.operation.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisClient {
	@Autowired
	private JedisPool jedisPool;

	public Jedis getRedis() throws Exception {
		try {
			return jedisPool.getResource();
		} catch (Exception e) {
			throw new Exception("redis未启动");
		}
	}
}
