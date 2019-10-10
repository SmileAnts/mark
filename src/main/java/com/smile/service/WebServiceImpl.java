package com.smile.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebServiceImpl implements WebService{
	private Logger logger = LoggerFactory.getLogger(WebServiceImpl.class);
	@Override
	public String sayHello(String name) {
		logger.info("成功了");
		String hello = name + "哈哈";
		return hello;
	}

}
