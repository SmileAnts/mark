package com.smile.operation.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smile.operation.common.BaseController;
import com.smile.operation.user.entity.User;
import com.smile.operation.user.service.IUserService;

/**
 * 用户类 用户得一系列操作
 * 
 * @author 许永强
 *
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private IUserService userService;

	/**
	 * 注册新用户
	 * 
	 * @return
	 */
	public Object register(User user) {
		if (checkUser(user.getUsername())) {
			userService.insert(user);
		}
		return null;
	}

	/**
	 * 判断注册用户是否存在
	 * 
	 * @return
	 */
	private Boolean checkUser(String username) {
		User user = userService.findUserByUserName(username);
		if (user == null) {
			return true;
		}
		return false;
	}
}
