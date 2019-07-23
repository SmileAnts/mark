package util;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.Application;
import com.smile.operation.redis.RedisClient;

import redis.clients.jedis.Jedis;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Test {

	@Autowired
	private RedisClient redisClient;

	@org.junit.Test
	public void MyTest() {
		Jedis jedis;
		try {
			jedis = redisClient.getRedis();
			jedis.set("id", "123");
			String value = jedis.get("id");
			System.out.println(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
