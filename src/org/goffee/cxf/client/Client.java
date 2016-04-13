package org.goffee.cxf.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.goffee.cxf.client.callback.UTPasswordClientCallBack;
import org.goffee.cxf.server.services.TestWebService;

public class Client {
	
	private final static String CA_CLIENT_PROPERTIES_PATH = "org/goffee/cxf/client/ca/client.properties";
	private final static String CA_CLIENT_ALIAS = "clientprivatekey"; // 客户端证书别名
	private final static String CA_SERVER_ALIAS = "serverprivatekey"; // 客户端证书别名
	
    /**
     * 调用服务端接口时，先调用该方法，获得服务端接口方法，该方法设置数字签名的加解密信息
     * 
     * @param address
     *            wsdl地址，包含“?wsdl”后缀
     * @return
     */
    public TestWebService call(String address) throws Exception {
    	
    	System.out.println("Start Call");
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(TestWebService.class);
        factory.setAddress(address);
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("mtom-enabled", Boolean.TRUE);
        factory.setProperties(properties);
        
        // 客户端请求服务端时，客户端进行签名和加密，对应服务端请求拦截器。
        System.out.println("Set client request to server");
        Map<String, Object> outProps = new HashMap<String, Object>();        
        outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE + " " + WSHandlerConstants.ENCRYPT);
        outProps.put(WSHandlerConstants.USER, CA_CLIENT_ALIAS);
        outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, UTPasswordClientCallBack.class.getName());
        outProps.put(WSHandlerConstants.ENCRYPTION_USER, CA_SERVER_ALIAS); 
        outProps.put(WSHandlerConstants.ENC_PROP_FILE, CA_CLIENT_PROPERTIES_PATH);
        outProps.put(WSHandlerConstants.SIGNATURE_USER, CA_CLIENT_ALIAS);
        outProps.put(WSHandlerConstants.SIG_PROP_FILE, CA_CLIENT_PROPERTIES_PATH);
        WSS4JOutInterceptor outInterceptor = new WSS4JOutInterceptor(outProps);
        factory.getOutInterceptors().add(outInterceptor);
        
        // 服务端响应客户端请求时，客户端对服务端签名和加密进行处理，对应服务端响应拦截器
        System.out.println("Set server response to client");
        Map<String, Object> inProps = new HashMap<String, Object>();
        inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE + " " + WSHandlerConstants.ENCRYPT);
        inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, UTPasswordClientCallBack.class.getName());
        inProps.put(WSHandlerConstants.DEC_PROP_FILE, CA_CLIENT_PROPERTIES_PATH);
        inProps.put(WSHandlerConstants.SIG_PROP_FILE, CA_CLIENT_PROPERTIES_PATH);
        factory.getInInterceptors().add(new WSS4JInInterceptor(inProps));
        TestWebService webService = (TestWebService) factory.create();
        return webService;
    }

}
