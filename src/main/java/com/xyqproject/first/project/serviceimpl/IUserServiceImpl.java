package com.xyqproject.first.project.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyqproject.first.project.entity.User;
import com.xyqproject.first.project.mapper.UserMapper;
import com.xyqproject.first.project.service.IUserService;

@Service
public class IUserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public Long login(User user) {
		return userMapper.login(user);
	}

	@Override
	public User findUserByUserName(String username) {
		return userMapper.findByUserName(username);
	}

}
