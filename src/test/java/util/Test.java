package util;

import java.util.Vector;

import org.jboss.logging.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.Application;
import com.smile.operation.common.PropTest;
import com.smile.operation.redis.RedisClient;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Test {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private RedisClient redisClient;
	@Autowired
	private PropTest propTest;

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

	@org.junit.Test
	public void pro() {
		logger.info(propTest.renName);
		logger.info(propTest.getName());
	}
	
	public static void main(String[] args) {
		Vector<String> v = new Vector<>();
		v.add("123");
		for (String string : v) {
			System.out.println(string);
		}
	}
}
