package com.smile.operation.photo.controller;

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
import com.smile.operation.photo.entity.Photo;
import com.smile.operation.photo.service.PhotoService;
import com.smile.util.Result;

@Controller
@RequestMapping("/photo")
public class PhotoController {
	@Autowired
	private PhotoService photoService;

	@RequestMapping("index")
	public String index() {
		return "html/photo.html";
	}

	@RequestMapping("/query")
	@ResponseBody
	public Result<List<Photo>> query(Photo photo, HttpServletRequest request, Page<Photo> page) {
		List<Photo> photos = photoService.selectAll(photo);
		return Result.success(photos, photos.size());
	}

	@RequestMapping("/add")
	@ResponseBody
	public Result<Boolean> save(Photo photo) {
		Validate.isTrue(photo != null, "photo实体不能为null");
		Boolean result = photoService.save(photo);
		return Result.success(result);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Result<Boolean> del(@RequestParam("ids") Long[] ids) {
		Validate.isTrue(ids != null, "ids is not null");
		Boolean result = photoService.deleteIds(ids);
		return Result.success(result);
	}

	@RequestMapping("/detail")
	public String detail(@RequestParam("id") Long phoneCodeId, Model model) {
		Validate.isTrue(phoneCodeId != null, "phoneCodeId is not null");
		model.addAttribute("photo", photoService.detail(phoneCodeId));
		return "html/photo_detail.html";
	}
}
