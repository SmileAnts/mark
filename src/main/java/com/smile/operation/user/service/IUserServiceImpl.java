package com.smile.operation.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.smile.operation.user.dao.UserMapper;
import com.smile.operation.user.entity.Users;

@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper, Users> {

	public Long login(Users user) {
		return baseMapper.login(user);
	}

	public Users findUserByUserName(String username) {
		return baseMapper.findByUserName(username);
	}

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
