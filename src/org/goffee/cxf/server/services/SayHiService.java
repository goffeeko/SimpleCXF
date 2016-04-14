package org.goffee.cxf.server.services;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "SayHiService", targetNamespace = "http://cxf.goffee.org/")
public interface SayHiService {
	String sayHi(@WebParam(name = "name") String name);
}
