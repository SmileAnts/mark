package util;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.Application;
import com.smile.operation.module.ModuleUtil;
import com.smile.operation.redis.RedisClient;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Test {

	@Autowired
	private RedisClient redisClient;
	@Autowired
	private ModuleUtil moduleUtil;

	@org.junit.Test
	public void MyTest() {
		try {
			for (int i = 1; i < 2000; i++) {
				Runnable run = new Runnable() {
					@Override
					public void run() {
						Long value = redisClient.nextId();
						System.out.println(value);
					}
				};
				run.run();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@org.junit.Test
	public void modulTest() {
	}
	
}
