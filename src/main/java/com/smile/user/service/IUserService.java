package com.smile.user.service;

import com.baomidou.mybatisplus.service.IService;
import com.smile.user.entity.User;

public interface IUserService extends IService<User> {
	public Long login(User user);
	public User findUserByUserName(String username);
}
