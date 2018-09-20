package com.xyqproject.first.project.service;

import com.xyqproject.first.project.entity.User;

public interface IUserService {
	public Long login(User user);
	public User findUserByUserName(String username);
}
