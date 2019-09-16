package com.smile.operation.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.smile.operation.role.entity.Role;
import com.smile.operation.role.service.IRoleServiceImpl;
import com.smile.operation.user.entity.Users;
import com.smile.operation.user.service.IUserServiceImpl;
import com.smile.util.WrapperUtil;

public class BaseController {
	@Autowired
	private IRoleServiceImpl roleService;
	@Autowired
	private IUserServiceImpl userService;

	/**
	 * 根据用户id 获取该用户所有角色
	 */
	public List<Role> roles(Long id) {
		Users user = getUser(id);
		EntityWrapper<Role> wrapper = WrapperUtil.selectList(new Role());
		wrapper.where("where id in (select id from role_user where user_id = ?)", user.getId());
		return roleService.selectList(wrapper);
	}

	public Users getUser(Long id) {
		return userService.selectById(id);
	}
}