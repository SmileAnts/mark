package com.smile.operation.menu.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.smile.operation.menu.dao.MenuMapper;
import com.smile.operation.menu.entity.Menu;

@Service
public class IMenuServiceImpl extends ServiceImpl<MenuMapper, Menu> {

	public List<Menu> menus(Long parentId) {
		List<Menu> menus = baseMapper.menus(parentId);
		this.childMenu(menus);
		return menus;
	}

	/**
	 * 获取子菜单(递归调用)
	 */
	public List<Menu> childMenu(List<Menu> menus) {
		if (menus.size() > 0) {
			menus.forEach(menu -> {
				List<Menu> childMenus = this.menus(Long.valueOf(menu.getId().toString()));
				childMenus = this.childMenu(childMenus);
				menu.setChildren(childMenus);
			});
		}
		return menus;
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
}
