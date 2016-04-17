package org.goffee.cxf.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.goffee.cxf.client.callback.UTPasswordClientSignatureOnlyCallBack;
import org.goffee.cxf.server.services.HelloWorldService;

/*
 * Please reference CXF lib example:
 * samples/ws_security/sign_enc/src/main/java/demo/wssec/client/Client.java
 * samples/ws_security/sign_enc/src/main/java/demo/wssec/server/Server.java
 * */
public class ClientForSignatureOnly {
		
	private final static String CA_CLIENT_ENCRYPT_SIGN_PATH = "org/goffee/cxf/client/ca/client_for_signature_only_sgin.properties";
	private final static String CA_CLIENT_ENCRYPT_ENCRYPT_PATH = "org/goffee/cxf/client/ca/client_for_signature_only_encrypt.properties";
	private final static String SIGN_USER = "clientx509v1";
    /**
     * 调用服务端接口时，先调用该方法，获得服务端接口方法，该方法设置数字签名的加解密信息
     * 
     * @param address
     *            wsdl地址，包含“?wsdl”后缀
     * @return
     */
    public HelloWorldService call(String address) throws Exception {
    	
    	System.out.println("Start Call");
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(HelloWorldService.class);
        factory.setAddress(address);
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("mtom-enabled", Boolean.TRUE);
        factory.setProperties(properties);
        
        // 客户端请求服务端时，客户端进行签名和加密，对应服务端请求拦截器。
        System.out.println("Set client request to server");
        Map<String, Object> outProps = new HashMap<String, Object>();        
        outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE);
        outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, UTPasswordClientSignatureOnlyCallBack.class.getName());
        outProps.put(WSHandlerConstants.SIGNATURE_USER, SIGN_USER);
        outProps.put(WSHandlerConstants.SIG_PROP_FILE, CA_CLIENT_ENCRYPT_SIGN_PATH);
        WSS4JOutInterceptor outInterceptor = new WSS4JOutInterceptor(outProps);
        factory.getOutInterceptors().add(outInterceptor);
        
        // 服务端响应客户端请求时，客户端对服务端签名和加密进行处理，对应服务端响应拦截器
        System.out.println("Set server response to client");
        Map<String, Object> inProps = new HashMap<String, Object>();
        inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE);
        inProps.put(WSHandlerConstants.SIG_PROP_FILE, CA_CLIENT_ENCRYPT_ENCRYPT_PATH);
        factory.getInInterceptors().add(new WSS4JInInterceptor(inProps));
        
        HelloWorldService webService = (HelloWorldService) factory.create();
        return webService;
    }

}
