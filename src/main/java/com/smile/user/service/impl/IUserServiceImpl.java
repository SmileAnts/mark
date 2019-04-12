package com.smile.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.smile.user.dao.UserDao;
import com.smile.user.entity.User;
import com.smile.user.service.IUserService;

@Service
public class IUserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {
	@Autowired
	private UserDao userDao;

	@Override
	public Long login(User user) {
		return userDao.login(user);
	}

	@Override
	public User findUserByUserName(String username) {
		return userDao.findByUserName(username);
	}

}
