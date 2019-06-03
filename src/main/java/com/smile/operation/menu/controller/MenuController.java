package com.smile.operation.menu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜单管理
 * 
 * @author 许永强
 *
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

	@RequestMapping("/index")
	public String index(Model model) {

		return "html/menu.html";
	}
}
