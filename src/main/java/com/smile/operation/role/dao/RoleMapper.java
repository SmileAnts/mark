package com.smile.operation.role.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.smile.operation.role.entity.Role;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

	public void setMenuRole(Long id, Long roleId, Long menuId);

	public void setUserRole(Long id, Long roleId, Long userId);

	public void deleteMenuRole(Long menuId);

	public void deleteUserRole(Long userId);
}
