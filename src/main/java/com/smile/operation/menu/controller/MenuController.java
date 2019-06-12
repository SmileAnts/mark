package com.smile.operation.menu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.smile.operation.menu.entity.Menu;
import com.smile.operation.menu.service.IMenuService;
import com.smile.util.Result;
import com.smile.util.WrapperUtil;

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
	private IMenuService iMenuServiceImpl;

	@RequestMapping("/index")
	public String index(Model model) {

		return "menu/menu.html";
	}

	/**
	 * 查询所有菜单
	 */
	@RequestMapping("/selectAll")
	@ResponseBody
	public Object selectAll(Menu menu, HttpServletRequest request, Page<Menu> page) {
		// page = iMenuServiceImpl.selectPage(page, WrapperUtil.selectList(menu));
		List<Menu> menus = iMenuServiceImpl.selectList(WrapperUtil.selectList(menu));
		return Result.success(menus, menus.size());
	}
}
