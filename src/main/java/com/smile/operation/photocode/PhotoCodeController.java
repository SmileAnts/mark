package com.smile.operation.photocode;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.smile.operation.photocode.entity.PhotoCode;
import com.smile.operation.photocode.service.PhotoCodeService;
import com.smile.util.Result;
import com.smile.util.WrapperUtil;

@Controller
@RequestMapping("/code")
public class PhotoCodeController {
	private static final Integer WIDTH = 300;
	private static final Integer HEIGHT = 300;
	@Autowired
	private PhotoCodeService photoCodeService;

	@RequestMapping("/index")
	public String index() {
		return "html/photo_code.html";
	}

	@RequestMapping("/query")
	@ResponseBody
	public Result<List<PhotoCode>> query(PhotoCode photoCode, HttpServletRequest request, Page<PhotoCode> page) {
		List<PhotoCode> photoCodes = photoCodeService.selectList(WrapperUtil.selectList(photoCode));
		return Result.success(photoCodes, photoCodes.size());
	}

	@RequestMapping("/add")
	@ResponseBody
	public Result<Boolean> save(PhotoCode photoCode) {
		Validate.isTrue(photoCode != null, "photoCode实体不能为null");
		Boolean result = photoCodeService.save(photoCode);
		return Result.success(result);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Result<Boolean> del(@RequestParam("ids") Long[] ids) {
		Validate.isTrue(ids != null, "id is not null");
		Boolean result = photoCodeService.deleteIds(ids);
		return Result.success(result);
	}

	/**
	 * 创建二维码
	 */
	@RequestMapping("/create")
	@ResponseBody
	public void code() {
		try {
			System.out.println(System.getProperty("user.dir"));
			String text = "http://www.baidu.com"; // 二维码内容
			String format = "jpg";// 二维码的图片格式

			Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 内容所使用字符集编码

			BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
			// 生成二维码
			File outputFile = new File(System.getProperty("user.dir") + "/" + "new.jpg");
			MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
