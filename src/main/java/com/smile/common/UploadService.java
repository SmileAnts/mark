package com.smile.common;

import javax.servlet.http.Part;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploadService {
	@PostMapping(path = "/upload")
	public String fileUpload(@RequestPart(name = "file1") MultipartFile file1, @RequestPart(name = "file2") Part file2,
			@RequestPart String param1) {
		return null;
	}
}
