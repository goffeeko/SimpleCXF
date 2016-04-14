package org.goffee.cxf.server.services.impl;

import org.goffee.cxf.server.services.HelloWorldService;

public class HelloWorldServiceImpl implements HelloWorldService {

	@Override
	public String helloWorld(String name) {
		System.out.println("[server][helloWorld] " + name);
		return "Hello World " + name;
	}

}
