package org.goffee.cxf.server.services;

import javax.jws.WebParam;
import javax.jws.WebService;

//配置targetNamespace，不进行配置，将使用当前类所在的包名顺序反向为命名空间。
//不进行命名空间设置,会存在客户端和服务端需要保持包名一致的问题，这在现实开发中肯定是很麻烦的事
@WebService(name = "HelloWorldService", targetNamespace = "http://cxf.goffee.org/")
public interface HelloWorldService {
	
	// 接口方法的参数最好使用annotation设定接口方法的参数名，不进行设置，默认会在参数前加上“{”等符号（具体符号可能有误），
	// 这样可能会给客户端调用带来不必要的麻烦,cxf有好几个annotation配置，如果客户端和服务端传递数据复杂类型参数时出现null等情况，一般进行相应annotation配置即可
	String helloWorld(@WebParam(name = "name") String name);
}