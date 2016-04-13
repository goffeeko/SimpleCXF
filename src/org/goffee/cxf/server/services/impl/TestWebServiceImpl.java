package org.goffee.cxf.server.services.impl;

import org.goffee.cxf.server.services.TestWebService;

public class TestWebServiceImpl implements TestWebService {

	@Override
	public String testMethod(String name) {
		System.out.println("[server][testMethod]" + name);
		return "call test method!";
	}

}
