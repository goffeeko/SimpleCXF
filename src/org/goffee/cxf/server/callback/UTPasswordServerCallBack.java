package org.goffee.cxf.server.callback;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.wss4j.common.ext.WSPasswordCallback;

public class UTPasswordServerCallBack implements CallbackHandler {

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
		pc.setPassword("keypass");
		System.out.println("[UTPasswordServerCallBack]Client Identifier=" + pc.getIdentifier());
		System.out.println("[UTPasswordServerCallBack]Client Password=" + pc.getPassword());
		System.out.println("[UTPasswordServerCallBack]Client usage=" + pc.getUsage());
	}

}
