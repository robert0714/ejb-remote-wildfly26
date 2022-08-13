# server-side
```
java -jar  -Djboss.bind.address=0.0.0.0 -Djboss.bind.address.management=0.0.0.0   ejb-remote-server-side-bootable.jar 

java -jar  -Djboss.bind.address=0.0.0.0   \
           -javaagent:elastic-apm-agent-1.33.0.jar  \
                -Djboss.modules.system.pkgs=org.jboss.byteman,com.manageengine  \
                -Djava.util.logging.manager=org.jboss.logmanager.LogManager  \
                -Delastic.apm.server_urls=http://192.168.50.92:8200   \
                -Delastic.apm.service_name=ejb-remote-server-side   \
                -Delastic.apm.application_packages=org.jboss.as.quickstarts.ejb   \
                -Delastic.apm.trace_methods=org.jboss.as.quickstarts.ejb   \
                -Delastic.apm.trace_methods_duration_threshold=100ms   \
                -Delastic.apm.stack_trace_limit=80     ejb-remote-server-side-bootable.jar 

java -jar  -Djboss.bind.address=0.0.0.0   \
           -javaagent:elastic-apm-agent-1.33.0.jar  \
                -Delastic.apm.server_urls=http://192.168.50.92:8200   \
                -Delastic.apm.service_name=ejb-remote-server-side   \
                -Delastic.apm.application_packages=org.jboss.as.quickstarts.ejb   \
                -Delastic.apm.trace_methods=org.jboss.as.quickstarts.ejb   \
                -Delastic.apm.log_level=DEBUG   \
                ejb-remote-server-side-bootable.jar 
          
export OTEL_RESOURCE_ATTRIBUTES=service.name=ejb-remote-server-side,service.version=1.1,deployment.environment=production
export OTEL_EXPORTER_OTLP_ENDPOINT=http://192.168.50.92:8200 
export OTEL_METRICS_EXPORTER="otlp" 
export OTEL_LOGS_EXPORTER="otlp" 
java   -jar  -Djboss.bind.address=0.0.0.0   \
       -javaagent:opentelemetry-javaagent.jar \
      ejb-remote-server-side-bootable.jar
```
# client-side

If you consider running the client execution manually then you can execute


```
mvn clean compile assembly:single
```

and run the client application with


```
java -jar -Dhost=192.168.18.30  -Dhttp=true  target/ejb-remote-client-jar-with-dependencies.jar

java -jar -Dhost=192.168.18.30 -Dremote.connection.default.username=admin  -Dremote.connection.default.password=123456 -Dhttp=true  target/ejb-remote-client-jar-with-dependencies.jar
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