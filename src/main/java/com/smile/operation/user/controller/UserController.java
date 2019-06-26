package com.smile.operation.user.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smile.operation.common.BaseController;
import com.smile.operation.user.entity.Users;
import com.smile.operation.user.service.IUserService;
import com.smile.util.CodeMsg;
import com.smile.util.Md5Util;
import com.smile.util.Result;
import com.smile.util.WrapperUtil;

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
	@RequestMapping("/register")
	@ResponseBody
	public Object register(Users user) {
		if (user == null || StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getUsername())) {
			return Result.error(new CodeMsg(500, "用户名或密码不能为空！", false));
		}
		user = Md5Util.password(user);
		user.setRegistTime(new Date());
		if (checkUser(user.getUsername())) {
			return Result.success(userService.insert(user));
		}
		return Result.error(CodeMsg.USER_EXSIST);
	}

	/**
	 * 判断注册用户是否存在
	 * 
	 * @return
	 */
	private Boolean checkUser(String username) {
		Users user = userService.findUserByUserName(username);
		if (user == null) {
			return true;
		}
		return false;
	}

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<Users> list() {
		List<Users> users = new ArrayList<>();
		userService.selectList(WrapperUtil.selectList(new Users()));
		return users;
	}
}
