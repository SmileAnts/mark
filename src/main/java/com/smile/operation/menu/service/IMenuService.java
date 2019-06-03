package com.smile.operation.menu.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.smile.operation.menu.entity.Menu;

public interface IMenuService extends IService<Menu> {
	/**
	 * 查找一级菜单
	 */
	public List<Menu> menus(Integer parentId);

	/**
	 * 查询子菜单
	 */
	public List<Menu> childMenu(List<Menu> menus);
}
