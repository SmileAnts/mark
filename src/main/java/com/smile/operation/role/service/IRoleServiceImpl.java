package com.smile.operation.role.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.smile.operation.role.dao.RoleMapper;
import com.smile.operation.role.entity.Role;

@Service
public class IRoleServiceImpl extends ServiceImpl<RoleMapper, Role> {

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
