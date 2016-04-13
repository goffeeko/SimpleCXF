Runtime:
```
JAVA Version: JDK6
Tomcat Version: tomcat 7
CXF Version: 3.0.9
```

reference:
http://www.ibm.com/developerworks/cn/java/j-jws13.html
http://blog.csdn.net/wangchsh2008/article/details/6708270

Using X.509 Certificates keytool example
http://cxf.apache.org/docs/ws-security.html
````````````````````````````````````````````````````````````````````````````````````````
keytool -genkey -alias myAlias -keypass myAliasPassword -keystore privatestore.jks -storepass keyStorePassword -dname "cn=myAlias" -keyalg RSA
keytool -selfcert -alias myAlias -keystore privatestore.jks -storepass keyStorePassword -keypass myAliasPassword
keytool -export -alias myAlias -file key.rsa -keystore privatestore.jks -storepass keyStorePassword
keytool -import -alias myAlias  -file key.rsa -keystore publicstore.jks -storepass keyStorePassword
````````````````````````````````````````````````````````````````````````````````````````





Using X.509 Certificates SIGNATURE + ENCRYPT / SIGNATURE + DECRYPT
````````````````````````````````````````````````````````````````````````````````````````
client for SimpleCXF
```
keytool -genkey -alias clientAlias -keypass clientKeyPassword -keystore clientKeyStore.jks -storepass clientStorePassword -dname "cn=clientAlias" -keyalg RSA
keytool -selfcert -alias clientAlias -keystore clientKeyStore.jks -storepass clientStorePassword -keypass clientKeyPassword
keytool -export -alias clientAlias -file clientKeyStore.rsa -keystore clientKeyStore.jks -storepass clientStorePassword
```

server for SimpleCXF
```
keytool -genkey -alias serverAlias -keypass serverKeyPassword -keystore serverKeyStore.jks -storepass serverStorePassword -dname "cn=serverAlias" -keyalg RSA
keytool -selfcert -alias serverAlias -keystore serverKeyStore.jks -storepass serverStorePassword -keypass serverKeyPassword
keytool -export -alias serverAlias -file serverKeyStore.rsa -keystore serverKeyStore.jks -storepass serverStorePassword
```

import
``` 
keytool -import -alias serverAlias -file serverKeyStore.rsa -keystore clientKeyStore.jks -storepass clientStorePassword
keytool -import -alias clientAlias -file clientKeyStore.rsa -keystore serverKeyStore.jks -storepass serverStorePassword
```

client CA path:
/SimpleCXF/src/org/goffee/cxf/client/ca/client.properties
/SimpleCXF/src/org/goffee/cxf/client/ca/client.jks

server CA path:
/SimpleCXF/src/org/goffee/cxf/server/ca/server.properties
/SimpleCXF/src/org/goffee/cxf/server/ca/server.jks
````````````````````````````````````````````````````````````````````````````````````````

