package com.lean.lumen;

import com.lean.lumen.bean.User;
import com.lean.lumen.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = TravelApplication.class)
@RunWith(SpringRunner.class)
public class TravelApplicationTests {

	@Resource
	UserService userService;

	@Test
	public void contextLoads() {
		User user = new User();
		user.setEmail("test.test@test.com");
		user.setPassword("123456");
		user.setUsername("lumen");

		userService.registerUser(user);
	}

}
