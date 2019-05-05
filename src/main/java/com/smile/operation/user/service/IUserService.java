package com.smile.operation.user.service;

import com.baomidou.mybatisplus.service.IService;
import com.smile.operation.user.entity.User;

public interface IUserService extends IService<User> {
	/**
	 * 用户登录
	 * 
	 * @param user 用户实体
	 * @return
	 */
	public Long login(User user);

	/**
	 * 根据用户名判断用户否存在
	 * 
	 * @param username 用户名
	 * @return
	 */
	public User findUserByUserName(String username);
}
