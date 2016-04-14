package org.goffee.cxf.server.callback;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.wss4j.common.ext.WSPasswordCallback;

public class UTPasswordServerCallBack implements CallbackHandler {

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

		for (int i = 0; i < callbacks.length; i++) {
			WSPasswordCallback pc = (WSPasswordCallback) callbacks[i];
			
			String id = pc.getIdentifier();
			int usage = pc.getUsage();

			if(WSPasswordCallback.DECRYPT == usage){
				System.out.println("IN DECRYPT");
				pc.setPassword("serverKeyPassword");
			}else if(WSPasswordCallback.SIGNATURE == usage){
				System.out.println("IN SIGNATURE");
				pc.setPassword("serverKeyPassword");
			}else if(WSPasswordCallback.USERNAME_TOKEN == usage){
				System.out.println("IN USERNAME_TOKEN");
				if ("goffee".equals(id)) {
					pc.setPassword("password");
				}
			}
						
			String pwd = pc.getPassword();
			System.out.println("[UTPasswordServerCallBack][" + i + "]Client Identifier=" + id);
			System.out.println("[UTPasswordServerCallBack][" + i + "]Client usage=" + usage);
			System.out.println("[UTPasswordServerCallBack][" + i + "]Client Password=" + pwd);
			
		}

	}

}
