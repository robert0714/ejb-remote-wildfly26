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
# Telemetry In GKE 

## Cloud Ops Agent
* https://cloud.google.com/logging/docs/agent/ops-agent?hl=en
* https://cloud.google.com/logging/docs/agent/ops-agent?hl=zh-cn

### Wildfly
* https://cloud.google.com/stackdriver/docs/solutions/agents/ops-agent/third-party/wildfly
* https://cloud.google.com/logging/docs/agent/ops-agent/third-party/wildfly?hl=zh-cn

# wildfly bootablr jar doc
https://docs.wildfly.org/bootablejar/#wildfly_jar_configuring_cloud