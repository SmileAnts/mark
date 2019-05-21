package com.smile.operation.user.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.smile.operation.common.CommonService;
import com.smile.operation.user.dao.UserMapper;
import com.smile.operation.user.entity.User;
import com.smile.operation.user.service.IUserService;

@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService, CommonService {

	@Override
	public Long login(User user) {
		return baseMapper.login(user);
	}

	@Override
	public User findUserByUserName(String username) {
		return baseMapper.findByUserName(username);
	}

	@Override
	public <T> void saveOrUpdate(T entity) {

	}

}
