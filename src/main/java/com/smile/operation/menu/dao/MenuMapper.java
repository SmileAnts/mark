package com.smile.operation.menu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.smile.operation.menu.entity.Menu;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
	/**
	 * 查找一级菜单
	 */
	public List<Menu> menus(Integer parentId);
}
