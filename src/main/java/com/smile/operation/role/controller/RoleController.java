package com.smile.operation.role.controller;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smile.operation.common.BaseController;
import com.smile.operation.role.entity.Role;
import com.smile.operation.role.service.IRoleServiceImpl;
import com.smile.util.Result;
import com.smile.util.WrapperUtil;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
	@Autowired
	private IRoleServiceImpl iRoleServiceImpl;

	/**
	 * 角色页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "html/role.html";
	}

	/**
	 * 角色列表
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Result<List<Role>> list() {
		List<Role> roles = iRoleServiceImpl.selectList(WrapperUtil.selectList(new Role()));
		return Result.success(roles, roles.size());
	}

	/**
	 * 新增角色
	 */
	@RequestMapping("/add")
	@ResponseBody
	public void add(Role role) {
		Validate.isTrue(role != null, "role is null");
		Boolean result = iRoleServiceImpl.insertOrUpdate(role);
	}
}
