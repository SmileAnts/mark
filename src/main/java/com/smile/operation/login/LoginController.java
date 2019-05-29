package com.smile.operation.login;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smile.operation.user.entity.Users;
import com.smile.util.CodeMsg;
import com.smile.util.Result;

@Controller
@RequestMapping("/login")
public class LoginController {

	/**
	 * 登录
	 * 
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping("/login")
	public String login(Model model, Users user) {
		return "login.html";
	}

	/**
	 * 首页
	 * 
	 * @param username 账号
	 * @param password 密码
	 * @param session  session
	 * @return
	 */
	@RequestMapping("/index")
	@ResponseBody
	public Object loginUser(String username, String password, HttpSession session) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			session.setAttribute("user", subject.getPrincipal());
			Users user = (Users) subject.getPrincipal();
			return Result.success(user);
		} catch (UnknownAccountException use) {
			return Result.error(CodeMsg.FALSE, "用户不存在！");
		} catch (LockedAccountException lock) {
			return Result.error(CodeMsg.FALSE, "用户被锁定！");
		} catch (AuthenticationException e) {
			return Result.error(CodeMsg.FALSE, "账号或密码错误！");
		}
	}

	/**
	 * 登出
	 * 
	 * @param session session
	 * @return
	 */
	@RequestMapping("/logOut")
	public String logOut(HttpSession session) {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			subject.logout();
		}
		return "login.html";
	}

	/**
	 * 注册页面
	 * 
	 * @param model model
	 * @return
	 */
	@RequestMapping("/register")
	public String register(Model model) {
		return "user/register.html";
	}

}
