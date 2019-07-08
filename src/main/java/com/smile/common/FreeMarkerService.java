package com.smile.common;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

@Component
@Lazy
public class FreeMarkerService {
	protected Configuration configuration;

	@Value("${freemarker.templateDirectory}")
	private String templateDirectory;

	@PostConstruct
	public void init() throws IOException {
		configuration = new Configuration(Configuration.VERSION_2_3_27);
		configuration.setDirectoryForTemplateLoading(new File(""));
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		configuration.setDefaultEncoding("UTF-8");
		configuration.setLogTemplateExceptions(false);
		configuration.setWrapUncheckedExceptions(true);
	}
}
