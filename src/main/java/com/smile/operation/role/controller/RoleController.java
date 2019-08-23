package com.smile.operation.role.controller;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smile.operation.common.BaseController;
import com.smile.operation.role.entity.Role;
import com.smile.operation.role.service.IRoleServiceImpl;
import com.smile.util.CodeMsg;
import com.smile.util.Converters;
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
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Result<Object> add(Role role) {
		Validate.isTrue(role != null, "role is null");
		role.setCreateTime(Converters.nowTime());
		Boolean result = iRoleServiceImpl.saveOrUpdate(role);
		return result ? Result.success(CodeMsg.SUCCESS, "新增成功") : Result.error(CodeMsg.FALSE, "新增失败");
	}

	/**
	 * 删除用户
	 * 
	 * @param id 用户id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result<Boolean> delete(@RequestParam("ids") Long[] ids) {
		Validate.notEmpty(ids, "参数ids 不能为空");
		Boolean delete = iRoleServiceImpl.deleteIds(ids);
		return Result.success(delete);
	}

	/**
	 * 给菜单赋予角色
	 * 
	 * @param menuIds 菜单的id
	 * @param roleIds 角色的id
	 */
	@RequestMapping("/setMenuRole")
	@ResponseBody
	public void setMenuRole(@RequestParam("menuIds") String menuIds, @RequestParam("roleIds") String roleIds) {
		Validate.notEmpty(menuIds, "menuIds must not be empty");
		Validate.notEmpty(roleIds, "roleIds must not be empty");
		iRoleServiceImpl.setMenuRole(Arrays.asList(roleIds.split(",")), Arrays.asList(menuIds.split(",")));
	}

	/**
	 * 给用户赋予角色
	 * 
	 * @param userIds 用户id
	 * @param roleIds 角色id
	 */
	@RequestMapping("/setUserRole")
	@ResponseBody
	public void setUserRole(@RequestParam("userIds") String userIds, @RequestParam("roleIds") String roleIds) {
		Validate.notEmpty(userIds, "userIds must not be empty");
		Validate.notEmpty(roleIds, "roleIds must not be empty");
		iRoleServiceImpl.setUserRole(Arrays.asList(roleIds.split(",")), Arrays.asList(userIds.split(",")));
	}
}
