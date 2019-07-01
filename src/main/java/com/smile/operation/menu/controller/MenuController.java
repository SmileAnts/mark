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
import com.smile.operation.menu.service.IMenuServiceImpl;
import com.smile.util.Constants;
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
		return Result.result(menus, Constants.SUCCESS, Constants.SUCCESS_CODE);
	}
}
