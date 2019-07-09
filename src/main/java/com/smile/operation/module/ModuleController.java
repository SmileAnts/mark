package com.smile.operation.module;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smile.common.FreeMarkerService;
import com.smile.util.Exceptions;
import com.smile.util.Result;

@RequestMapping("/module")
@Controller
public class ModuleController {
	@Autowired
	private FreeMarkerService freeMarkerService;

	@RequestMapping("/index")
	public String index() {

		return "html/freemarker";
	}

	@RequestMapping("/create")
	@ResponseBody
	public Result<Object> createModule(@RequestParam("username") String username) {
		try {
			freeMarkerService.save(username);
			return Result.success();
		} catch (Exception e) {
			Exceptions.unchecked("生成错误");
			return Result.error();
		}
	}
}
