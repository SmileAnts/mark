package com.smile.operation.common;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.smile.util.Result;

@RequestMapping("/upload")
@Controller
public class UploadController {

	@RequestMapping("/file")
	@ResponseBody
	public Result<Object> oneImgUpload(MultipartFile file, HttpServletRequest request) {
		// 文件名
		String oldName = file.getOriginalFilename();
		// 获取源文件的后缀名
		String extName = oldName.substring(oldName.lastIndexOf("."));
		// 生成自定义的新文件名，这里以UUID.jpg|png|xxx作为格式（可选操作，也可以不自定义新文件名）
		String uuid = UUID.randomUUID().toString();
		String newName = uuid + extName;
		// 要保存文件的路径
		// 5.保存图片
		String desFilePath = this.path() + "/" + newName;
		File desFile = new File(desFilePath);
		try {
			file.transferTo(desFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.success(this.path() + "/" + newName);
	}

	/**
	 * 获取项目根路径
	 * 
	 * @return
	 */
	private String path() {
		String path = System.getProperty("user.dir");
		int sub = path.lastIndexOf("/");
		return path.substring(0, sub);
	}
}
