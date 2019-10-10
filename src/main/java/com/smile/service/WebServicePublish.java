package com.smile.service;

import javax.xml.ws.Endpoint;

public class WebServicePublish {
	public static void main(String[] args) {
		String adress = "https://localhost:8989/WS_Server/Webservice";
		Endpoint.publish(adress, new WebServiceImpl());
	}
}
