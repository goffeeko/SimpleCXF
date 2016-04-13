Runtime:
```
JAVA Version: JDK6
Tomcat Version: tomcat 7
CXF Version: 3.0.9
```

Using X.509 Certificates keytool example
http://cxf.apache.org/docs/ws-security.html
````````````````````````````````````````````````````````````````````````````````````````
keytool -genkey -alias myAlias -keypass myAliasPassword -keystore privatestore.jks -storepass keyStorePassword -dname "cn=myAlias" -keyalg RSA
keytool -selfcert -alias myAlias -keystore privatestore.jks -storepass keyStorePassword -keypass myAliasPassword
keytool -export -alias myAlias -file key.rsa -keystore privatestore.jks -storepass keyStorePassword
keytool -import -alias myAlias  -file key.rsa -keystore publicstore.jks -storepass keyStorePassword
````````````````````````````````````````````````````````````````````````````````````````

client for SimpleCXF
```
keytool -genkey -alias clientprivatekey -keypass keypass -keystore client.jks -storepass storepass -dname "CN=zs.com,C=CN" -keyalg RSA
keytool -selfcert -keystore client.jks -storepass storepass -alias clientprivatekey -keypass keypass
keytool -export -alias clientprivatekey -file client.rsa -keystore client.jks -storepass storepass
```

server for SimpleCXF
```
keytool -genkey -alias serverprivatekey -keypass keypass -keystore server.jks -storepass storepass -dname "CN=zs.com,C=CN" -keyalg RSA
keytool -selfcert -keystore server.jks -storepass storepass -alias serverprivatekey -keypass keypass
keytool -export -alias serverprivatekey -file server.rsa -keystore server.jks -storepass storepass

import
``` 
keytool -import -alias serverprivatekey -file server.rsa -keystore client.jks -storepass storepass
keytool -import -alias clientprivatekey -file client.rsa -keystore server.jks -storepass storepass

client CA path:
/SimpleCXF/src/org/goffee/cxf/client/ca/client.properties
/SimpleCXF/src/org/goffee/cxf/client/ca/client.jks

server CA path:
/SimpleCXF/src/org/goffee/cxf/server/ca/server.properties
/SimpleCXF/src/org/goffee/cxf/server/ca/server.jks


reference:
http://www.ibm.com/developerworks/cn/java/j-jws13.html
http://blog.csdn.net/wangchsh2008/article/details/6708270