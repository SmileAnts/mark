package com.smile.operation.role.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.smile.operation.redis.RedisClient;
import com.smile.operation.role.dao.RoleMapper;
import com.smile.operation.role.entity.Role;

@Service
public class IRoleServiceImpl extends ServiceImpl<RoleMapper, Role> {
	@Autowired
	private RedisClient redisClient;

	public Boolean saveOrUpdate(Role role) {
		if (role.getId() == null) {
			role.setId(redisClient.nextId());
		}
		return insertOrUpdate(role);
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

	/**
	 * 为菜单赋予角色
	 * 
	 * @param roleIds 角色
	 * @param userIds 用户
	 */
	@Transactional
	public void setMenuRole(List<Object> roleIds, List<Object> menuIds) {
		for (Object menuId : menuIds) {
			this.deleteMenuRole(Long.valueOf(menuId.toString()));
			for (Object roleId : roleIds) {
				baseMapper.setMenuRole(redisClient.nextId(), Long.valueOf(roleId.toString()), Long.valueOf(menuId.toString()));
			}
		}
	}

	/**
	 * 为用户赋予角色
	 * 
	 * @param roleIds
	 * @param userIds
	 */
	public void setUserRole(List<Object> roleIds, List<Object> userIds) {
		for (Object userId : userIds) {
			this.deleteUserRole(Long.valueOf(userId.toString()));
			for (Object roleId : roleIds) {
				baseMapper.setUserRole(redisClient.nextId(), Long.valueOf(roleId.toString()), Long.valueOf(userId.toString()));
			}
		}
	}

	/**
	 * 删除菜单的角色
	 * 
	 * @param userId
	 */
	private void deleteMenuRole(Long menuId) {
		baseMapper.deleteMenuRole(menuId);
	}

	/**
	 * 删除用户的角色
	 * 
	 * @param userId
	 */
	private void deleteUserRole(Long userId) {
		baseMapper.deleteUserRole(userId);
	}
}
