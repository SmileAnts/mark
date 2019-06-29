package com.smile.operation.user.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.smile.operation.common.CommonService;
import com.smile.operation.user.dao.UserMapper;
import com.smile.operation.user.entity.Users;
import com.smile.operation.user.service.IUserService;

@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper, Users> implements IUserService, CommonService {

	@Override
	public Long login(Users user) {
		return baseMapper.login(user);
	}

	@Override
	public Users findUserByUserName(String username) {
		return baseMapper.findByUserName(username);
	}

	@Override
	public <T> void saveOrUpdate(T entity) {

	}

	@Override
	@Transactional
	public Boolean deleteIds(Long[] ids) {
		for (Long id : ids) {
			Boolean delete = this.deleteById(id);
			if (!delete) {
				return delete;
			}
		}
		return Boolean.TRUE;
	}

}
