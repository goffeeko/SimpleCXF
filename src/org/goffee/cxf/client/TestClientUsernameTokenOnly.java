package org.goffee.cxf.client;

import org.goffee.cxf.server.services.SayHiService;

public class TestClientUsernameTokenOnly {
	// 服务端wsdl地址，包含"?wsdl"后缀,wsdl地址一般放在数据库等地方，方便服务端改动时，客户端好更改.
	private static String address = "http://localhost:8080/SimpleCXF/services/sayHi?wsdl";
	
	public static void main(String[] args) {
		try {
			ClientForUsernameTokenOnly client = new ClientForUsernameTokenOnly();
			SayHiService webService = client.call(address);
			String result = webService.sayHi("goffee");
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
