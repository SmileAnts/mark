package com.smile.operation.menu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.smile.operation.common.CommonService;
import com.smile.operation.menu.dao.MenuMapper;
import com.smile.operation.menu.entity.Menu;
import com.smile.operation.menu.service.IMenuService;

@Service
public class IMenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService, CommonService {
	@Autowired
	private MenuMapper menuMapper;

	@Override
	public <T> void saveOrUpdate(T entity) {

	}

	@Override
	public List<Menu> menus(Integer parentId) {
		List<Menu> menus = menuMapper.menus(parentId);
		this.childMenu(menus);
		return menus;
	}

	/**
	 * 获取子菜单(递归调用)
	 */
	public List<Menu> childMenu(List<Menu> menus) {
		if (menus.size() > 0) {
			menus.forEach(menu -> {
				List<Menu> childMenus = this.menus(Integer.valueOf(menu.getId().toString()));
				childMenus = this.childMenu(childMenus);
				menu.setChildren(childMenus);
			});
		}
		return menus;
	}

}
