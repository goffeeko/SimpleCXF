<h1>Runtime</h1>
```
JAVA Version: JDK6
Tomcat Version: tomcat 7
CXF Version: 3.0.9
IDE: Eclipse Java EE IDE for Web Developers [Mars.2 Release](4.5.2)
```

<h1>Usage</h1>
```
1.run on Server and open this address "http://localhost:8080/SimpleCXF/services" in browse.
2.run test client(path: "org/goffee/cxf/client/") as java application:
#TestClient.java
#TestClientSignatureOnly.java
#TestClientUsernameTokenOnly.java
```

<h1>Note</h1>
```
if missing lib folder in WebContent/WEB-INF/ , please create new one.
```

<h1>Project struct</h1>
```
SimpleCXF
	|-src
		|-org.goffee.cxf
			|-client
				|-ca
				|-calback
			|-server
				|-ca
				|-callback
				|-services
					|-impl
```	

<h1>Reference</h1>
```
http://www.ibm.com/developerworks/cn/java/j-jws13.html
http://blog.csdn.net/wangchsh2008/article/details/6708270
http://web-gmazza.rhcloud.com/blog/entry/cxf-x509-profile
```

Using X.509 Certificates keytool example
http://cxf.apache.org/docs/ws-security.html
```
keytool -genkey -alias myAlias -keypass myAliasPassword -keystore privatestore.jks -storepass keyStorePassword -dname "cn=myAlias" -keyalg RSA
keytool -selfcert -alias myAlias -keystore privatestore.jks -storepass keyStorePassword -keypass myAliasPassword
keytool -export -alias myAlias -file key.rsa -keystore privatestore.jks -storepass keyStorePassword
keytool -import -alias myAlias  -file key.rsa -keystore publicstore.jks -storepass keyStorePassword
```

<h1>Demo</h1>


<h4>Using UsernameToken [TestClientUsernameTokenOnly.java]</h4>
```
client CA path:
N/A

server CA path:
N/A
```


<h4>Using X.509 Certificates SIGNATURE [TestClientSignatureOnly.java]</h4>

```
Please reference CXF lib example:
 samples/ws_security/sign_enc/src/main/java/demo/wssec/client/Client.java
 samples/ws_security/sign_enc/src/main/java/demo/wssec/server/Server.java
```

```
client CA path:
SimpleCXF/src/org/goffee/cxf/client/ca/client_for_signature_only_encrypt.properties
SimpleCXF/src/org/goffee/cxf/client/ca/client_for_signature_only_sgin.properties
SimpleCXF/src/org/goffee/cxf/client/ca/client-keystore.jks
SimpleCXF/src/org/goffee/cxf/client/ca/client-truststore.jks

server CA path:
SimpleCXF/src/org/goffee/cxf/server/ca/server_for_signature_only_decrypt.properties
SimpleCXF/src/org/goffee/cxf/server/ca/server_for_signature_only_sign.properties
SimpleCXF/src/org/goffee/cxf/server/ca/server-keystore.jks
SimpleCXF/src/org/goffee/cxf/server/ca/server-truststore.jks
```


<h4>Using X.509 Certificates with USERNAME_TOKEN + TIMESTAMP + SIGNATURE + ENCRYPT [TestClient.java]</h4>

<b>client for SimpleCXF</b>
```
keytool -genkey -alias clientAlias -keypass clientKeyPassword -keystore clientKeyStore.jks -storepass clientStorePassword -dname "cn=clientAlias" -keyalg RSA
keytool -selfcert -alias clientAlias -keystore clientKeyStore.jks -storepass clientStorePassword -keypass clientKeyPassword
keytool -export -alias clientAlias -file clientKeyStore.rsa -keystore clientKeyStore.jks -storepass clientStorePassword
```

<b>server for SimpleCXF</b>
```
keytool -genkey -alias serverAlias -keypass serverKeyPassword -keystore serverKeyStore.jks -storepass serverStorePassword -dname "cn=serverAlias" -keyalg RSA
keytool -selfcert -alias serverAlias -keystore serverKeyStore.jks -storepass serverStorePassword -keypass serverKeyPassword
keytool -export -alias serverAlias -file serverKeyStore.rsa -keystore serverKeyStore.jks -storepass serverStorePassword
```

<b>import</b>
``` 
keytool -import -alias serverAlias -file serverKeyStore.rsa -keystore clientKeyStore.jks -storepass clientStorePassword
keytool -import -alias clientAlias -file clientKeyStore.rsa -keystore serverKeyStore.jks -storepass serverStorePassword
```
```
client CA path:
SimpleCXF/src/org/goffee/cxf/client/ca/client.properties
SimpleCXF/src/org/goffee/cxf/client/ca/client.jks

server CA path:
SimpleCXF/src/org/goffee/cxf/server/ca/server.properties
SimpleCXF/src/org/goffee/cxf/server/ca/server.jks
```


