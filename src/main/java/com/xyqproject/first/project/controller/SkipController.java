package com.xyqproject.first.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于页面的跳转
 * 
 * @author smile
 *
 */
@RequestMapping("/skip")
@Controller
public class SkipController {
	/**
	 * 个人中心
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/myinfo")
	public String myInfo(Model model) {
		return "myinfo/myinfo.html";
	}

	/**
	 * 个人动态
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/mydynic")
	public String myDynic(Model model) {
		return "myinfo/mydynic";
	}
}
