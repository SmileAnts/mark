package com.smile.operation.redis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisClient {
	@Autowired
	private JedisPool jedisPool;
	@Autowired
	RedisTemplate<String, String> redisTemplate;

	private final static String KEY_ID = "_key_id_";

	private Jedis getRedis() {
		return jedisPool.getResource();
	}

	public Long nextId() {
		RedisAtomicLong redisAtomicLong = new RedisAtomicLong(KEY_ID, redisTemplate.getConnectionFactory());
		Long id = redisAtomicLong.getAndIncrement();
		return id;
	}

	/**
	 * 关闭reids
	 */
	public void close(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}

	/**
	 * 设置key value
	 */
	public String set(String key, String value) {
		return getRedis().set(key, value);
	}

	/**
	 * 获取key对应value
	 */
	public String get(String key) {
		return getRedis().get(key);
	}

	/**
	 * 删除key
	 */
	public Long del(String key) {
		return getRedis().del(key);
	}

	/**
	 * 判断key 是否存在
	 */
	public Boolean exists(String key) {
		return getRedis().exists(key);
	}

	/**
	 * 设置key value,如果key已经存在则返回0
	 */
	public Long setnx(String key, String value) {
		return getRedis().setnx(key, value);
	}

	/**
	 * 设置key value并指定这个键值的有效期
	 */
	public String setex(String key, int seconds, String value) {
		return getRedis().setex(key, seconds, value);
	}

	/**
	 * 通过批量的key获取批量的value
	 */
	public List<String> mget(String... keys) {
		return getRedis().mget(keys);
	}

	/**
	 * 设置key的值,并返回一个旧值
	 */
	public String getSet(String key, String value) {
		return getRedis().getSet(key, value);
	}

	/**
	 * 通过key 对value进行加值+1操作,当value不是int类型时会返回错误,当key不存在是则value为1
	 */
	public Long incr(String key) {
		return getRedis().incr(key);
	}

	/**
	 * 通过key给指定的value加值,如果key不存在,则这是value为该值
	 */
	public Long incrBy(String key, long integer) {
		return getRedis().incrBy(key, integer);
	}

	/**
	 * 对key的值做减减操作,如果key不存在,则设置key为-1
	 */
	public Long decr(String key) {
		return getRedis().decr(key);
	}

	/**
	 * 减去指定的值
	 */
	public Long decrBy(String key, long integer) {
		return getRedis().decrBy(key, integer);
	}

	/**
	 * 设置key的超时时间为seconds
	 */
	public Long expire(String key, int seconds) {
		return getRedis().expire(key, seconds);
	}

}
