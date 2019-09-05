package com.smile.operation.menu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.smile.operation.menu.entity.Menu;
import com.smile.operation.menu.service.IMenuServiceImpl;
import com.smile.util.Constants;
import com.smile.util.Converters;
import com.smile.util.Result;

/**
 * 菜单管理
 * 
 * @author 许永强
 *
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
	@Autowired
	private IMenuServiceImpl iMenuServiceImpl;

	@RequestMapping("/index")
	public String index(Model model) {
		return "menu/menu.html";
	}

	/**
	 * 查询所有菜单
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Result<List<Menu>> selectAll(Menu menu, HttpServletRequest request, Page<Menu> page) {
		List<Menu> menus = iMenuServiceImpl.menus(Constants.MENU_GOLD);
		return Result.success(menus, menus.size());
	}

	@RequestMapping("/add")
	@ResponseBody
	public Result<Integer> add(Menu menu) {
		Validate.isTrue(menu != null, "menu 不能为空");
		menu.setCreateTime(Converters.nowTime());
		Boolean status = iMenuServiceImpl.saveOrUpdate(menu);
		if (status) {
			return Result.success();
		}
		return Result.error();
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Result<Integer> delete(@RequestParam("ids") Long[] ids) {
		Validate.notEmpty(ids, "参数ids 不能为空");
		Boolean delete = iMenuServiceImpl.deleteIds(ids);
		if (delete) {
			return Result.success();
		}
		return Result.error();
	}

	/**
	 * 根据父菜单 查询子菜单
	 * 
	 * @return
	 */
	@RequestMapping("/parent")
	@ResponseBody
	public Result<List<Menu>> selectByParentId(@RequestParam("parentId") Long parentId) {
		Validate.isTrue(parentId != null, "parentId不能为空");
		List<Menu> menus = iMenuServiceImpl.menus(parentId);
		return Result.success(menus, menus.size());
	}
}
