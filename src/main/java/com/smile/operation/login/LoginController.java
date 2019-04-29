package com.smile.operation.login;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smile.operation.user.entity.User;
import com.smile.util.Constants;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	/**
	  * 登录
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping("/login")
	public String login(Model model, User user) {
		return "login.html";
	}
	
	/**
	  *  首页
	 * @param username 账号
	 * @param password 密码
	 * @param session session
	 * @return
	 */
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
	
	/**
	  *  登出
	 * @param session session
	 * @return
	 */
	@RequestMapping("/logOut")
	public String logOut(HttpSession session) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "login.html";
	}
	
	/**
	  *  注册页面
	 * @param model model
	 * @return
	 */
	@RequestMapping("/register")
	public String register(Model model) {
		return "user/register.html";
	}
	
}
