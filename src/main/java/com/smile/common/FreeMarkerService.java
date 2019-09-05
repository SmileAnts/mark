package com.smile.common;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.springframework.stereotype.Component;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Component
@org.springframework.context.annotation.Configuration
public class FreeMarkerService {

	public void save(String name) throws Exception {
		Configuration cfg = FreeMarkerUtil.init();
		cfg.setDirectoryForTemplateLoading(new File(System.getProperty("user.dir")));

		Map<String, String> root = new HashMap<>();
		root.put("user", name);

		Template temp = cfg.getTemplate("/src/main/resources/templates/ftl/html.ftl");
		File file = new File(System.getProperty("user.dir") + "/html");
		String indexPath = System.getProperty("user.dir") + "/html/smile.html";
		if (!file.exists()) {
			file.mkdirs();
		}
		FileWriterWithEncoding out = new FileWriterWithEncoding(indexPath, "UTF-8");
		temp.process(root, out);
		out.close();
	}

	public void generateEntity() {

	}

	public void generateController() {

	}

	public void generateService() {

	}

	public void generateMapper() {

	}

	public void generateXml() {

	}
}
