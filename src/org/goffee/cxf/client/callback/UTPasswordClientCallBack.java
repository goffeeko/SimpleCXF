package org.goffee.cxf.client.callback;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.wss4j.common.ext.WSPasswordCallback;

public class UTPasswordClientCallBack implements CallbackHandler {

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];

		switch (pc.getUsage()) {
		case WSPasswordCallback.SIGNATURE:
			pc.setPassword("clientKeyPassword");
			System.out.println("[client][callback]SIGNATURE");
			break;
		case WSPasswordCallback.DECRYPT:
			pc.setPassword("clientKeyPassword");
			System.out.println("[client][callback]DECRYPT");
			break;
		case WSPasswordCallback.USERNAME_TOKEN:
			System.out.println("[client][callback]USERNAME_TOKEN");
			pc.setPassword("password");
		default:
			break;
		}
			
		System.out.println("[UTPasswordClientCallBack]Client Identifier=" + pc.getIdentifier());
		System.out.println("[UTPasswordClientCallBack]Client Password=" + pc.getPassword());
		System.out.println("[UTPasswordClientCallBack]Client usage=" + pc.getUsage());
	}

}