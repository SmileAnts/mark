package com.smile.operation.login;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smile.operation.menu.entity.Menu;
import com.smile.operation.menu.service.IMenuService;
import com.smile.operation.user.entity.Users;
import com.smile.util.Constants;
import com.smile.util.LoginUtil;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private IMenuService iMenusSerivce;

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
		return LoginUtil.login(username, password);
	}

	/**
	 * 首页
	 * 
	 * @param username 账号
	 * @param password 密码
	 * @param session  session
	 * @return
	 */
	@RequestMapping("/goIndex")
	public String loginIndex(Model model) {
		Users user = (Users) SecurityUtils.getSubject().getPrincipal();
		List<Menu> goldMenus = iMenusSerivce.childMenu(iMenusSerivce.menus(Constants.MENU_GOLD));
		model.addAttribute("user", user);
		model.addAttribute("menus", goldMenus);
		return "index.html";
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
