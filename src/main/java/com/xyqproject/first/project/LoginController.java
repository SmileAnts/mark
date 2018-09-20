package com.xyqproject.first.project;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xyqproject.first.project.entity.User;
import com.xyqproject.util.Constants;

@Controller
@RequestMapping("/login")
public class LoginController {
	// @Autowired
	// private RedisClient redisClient;

	@RequestMapping("/login")
	public String login(Model model, User user) {
		return "login.html";
	}

	@RequestMapping("/index")
	@ResponseBody
	public Object loginUser(String username, String password, HttpSession session) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			User user = (User) subject.getPrincipal();
			return user;
		} catch (AuthenticationException e) {
			return Constants.FALSE;
		}
	}

	@RequestMapping("/logOut")
	public String logOut(HttpSession session) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "login.html";
	}
}
