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
		pc.setPassword("keypass");
		System.out.println("[UTPasswordClientCallBack]Client Identifier=" + pc.getIdentifier());
		System.out.println("[UTPasswordClientCallBack]Client Password=" + pc.getPassword());
		System.out.println("[UTPasswordClientCallBack]Client usage=" + pc.getUsage());
	}

}