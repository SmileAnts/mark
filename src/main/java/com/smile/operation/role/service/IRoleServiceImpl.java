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
	public void setMenuRole(List<Long> roleIds, List<Long> menuIds) {
		for (Long menuId : menuIds) {
			this.deleteMenuRole(menuId);
			for (Long roleId : roleIds) {
				baseMapper.setMenuRole(redisClient.nextId(), roleId, menuId);
			}
		}
	}

	/**
	 * 为用户赋予角色
	 * 
	 * @param roleIds
	 * @param userIds
	 */
	@Transactional
	public void setUserRole(List<Long> roleIds, List<Long> userIds) {
		for (Long userId : userIds) {
			this.deleteUserRole(userId);
			for (Long roleId : roleIds) {
				baseMapper.setUserRole(redisClient.nextId(), roleId, userId);
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
