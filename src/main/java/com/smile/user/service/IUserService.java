package com.smile.user.service;

import com.smile.user.entity.User;

public interface IUserService {
	public Long login(User user);
	public User findUserByUserName(String username);
}
