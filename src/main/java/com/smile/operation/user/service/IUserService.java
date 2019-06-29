package com.smile.operation.user.service;

import com.baomidou.mybatisplus.service.IService;
import com.smile.operation.user.entity.Users;

public interface IUserService extends IService<Users> {
	/**
	 * 用户登录
	 * 
	 * @param user 用户实体
	 * @return
	 */
	public Long login(Users user);

	/**
	 * 根据用户名判断用户否存在
	 * 
	 * @param username 用户名
	 * @return
	 */
	public Users findUserByUserName(String username);

	/**
	 * 通过id 删除用户
	 * 
	 * @param ids 多删除
	 * @return
	 */
	public Boolean deleteIds(Long[] ids);
}
