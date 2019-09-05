package com.smile.common;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

public class FreeMarkerUtil {
	private static class ConfigurationUtil {
		public final static Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);
	}

	public static Configuration init() {
		Configuration configuration = ConfigurationUtil.configuration;
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		configuration.setDefaultEncoding("UTF-8");
		configuration.setLogTemplateExceptions(false);
		configuration.setWrapUncheckedExceptions(true);
		return configuration;
	}

}
