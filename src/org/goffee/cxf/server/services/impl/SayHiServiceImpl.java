package org.goffee.cxf.server.services.impl;

import org.goffee.cxf.server.services.SayHiService;

public class SayHiServiceImpl implements SayHiService {

	@Override
	public String sayHi(String name) {
		System.out.println("[server][sayHi]" + name);
		return "Hi " + name;
	}

}
