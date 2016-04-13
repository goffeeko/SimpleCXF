package org.goffee.cxf.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.goffee.cxf.client.callback.UTPasswordClientCallBack;
import org.goffee.cxf.server.services.TestWebService;

/**
 * 客户端调用服务端公共类
 * 
 * @author yu
 * 
 */
public class Client {
	
//	private final static String CA_CLIENT_PROPERTIES_PATH = new File("WebContent").getAbsolutePath() + "/WEB-INF/ca/client.properties";
	private final static String CA_CLIENT_PROPERTIES_PATH = "org/goffee/cxf/client/ca/client.properties";
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
        outProps.put("action", "Signature Encrypt");
        outProps.put("user", "clientprivatekey");// 客户端证书别名
        outProps.put("passwordCallbackClass", UTPasswordClientCallBack.class.getName());
        outProps.put("encryptionUser", "serverprivatekey");// 服务端证书别名
        outProps.put("encryptionPropFile", CA_CLIENT_PROPERTIES_PATH);// 客户端证书配置信息
        outProps.put("signatureUser", "clientprivatekey");// 客户端证书别名
        outProps.put("signaturePropFile", CA_CLIENT_PROPERTIES_PATH);
        WSS4JOutInterceptor outInterceptor = new WSS4JOutInterceptor(outProps);
        outInterceptor.setAllowMTOM(true);
        factory.getOutInterceptors().add(outInterceptor);
        
        // 服务端响应客户端请求时，客户端对服务端签名和加密进行处理，对应服务端响应拦截器
        System.out.println("Set server response to client");
        Map<String, Object> inProps = new HashMap<String, Object>();
        inProps.put("action", "Signature Encrypt");
        inProps.put("passwordCallbackClass", UTPasswordClientCallBack.class.getName());
        inProps.put("decryptionPropFile", CA_CLIENT_PROPERTIES_PATH);
        inProps.put("signaturePropFile", CA_CLIENT_PROPERTIES_PATH);
        factory.getInInterceptors().add(new WSS4JInInterceptor(inProps));
        TestWebService webService = (TestWebService) factory.create();
        return webService;
    }

}
