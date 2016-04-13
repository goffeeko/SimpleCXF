package org.goffee.cxf.server.services.impl;

import org.goffee.cxf.server.services.TestWebService;

public class TestWebServiceImpl implements TestWebService {

	@Override
	public String testMethod(String xml) {
		System.out.println(xml);
		return "call test method!";
	}

}
