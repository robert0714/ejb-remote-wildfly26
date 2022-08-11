# server-side
```
java -jar  -Djboss.bind.address=0.0.0.0 -Djboss.bind.address.management=0.0.0.0  ~/Desktop/ejb-remote-server-side-bootable.jar 

```
# client-side

If you consider running the client execution manually then you can execute


```
mvn clean compile assembly:single
```

and run the client application with


```
java -jar -Dhost=192.168.18.30  -Dhttp=true  target/ejb-remote-client-jar-with-dependencies.jar
```