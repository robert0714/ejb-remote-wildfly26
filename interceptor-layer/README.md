# interceptor-layer

This project is needed to create a custom user-developer layer that will be used to configure JBoss EAP through the Galleon framework. The project [wildfly-datasources-galleon-pack](https://github.com/jbossas/eap-datasources-galleon-pack) includes several layers to add the most common jdbc datasources (postgresql, mysql and oracle) inside the OpenShift Container Platform (OCP) but I would like to demonstrate how to customize a JBoss EAP subsystem and also show you how use a different database that is not included in the eap-datasources-galleon-pack.

# Reference data

* Reference
   * {WILDFLY_HOME}/modules/system/layers/base/io/opentracing/contrib/opentracing-interceptors/main/module.xml
   
  ```xml
	<?xml version="1.0" encoding="UTF-8"?>
	
	<module name="io.opentracing.contrib.opentracing-interceptors" xmlns="urn:jboss:module:1.9">
	
	    <properties>
	        <property name="jboss.api" value="private"/>
	    </properties>
	
	    <resources>
	        <resource-root path="opentracing-interceptors-0.1.3.jar"/>
	    </resources>
	
	    <dependencies>
	        <module name="java.logging"/>
	
	        <module name="javax.annotation.api"/>
	        <module name="javax.enterprise.api"/>
	        <module name="javax.inject.api"/>
	        <module name="javax.ws.rs.api"/>
	
	        <module name="io.opentracing.opentracing-api"/>
	        <module name="io.opentracing.opentracing-util"/>
	        <module name="io.opentracing.contrib.opentracing-tracerresolver"/>
	        <module name="org.eclipse.microprofile.opentracing"/>
	        <module name="org.wildfly.microprofile.opentracing-smallrye"/>
	    </dependencies>
	</module>
	
  ```
  
  
   * [Redhat EAP 7.3 Documentation - 7.1.5. Configuring a Container Interceptor](https://access.redhat.com/documentation/es-es/red_hat_jboss_enterprise_application_platform/7.3/html/developing_ejb_applications/ejb_interceptors#configuring-a-container-interceptor_dev-guide-ejbs)

  ```shell
   jboss-cli
   You are disconnected at the moment. Type 'connect' to connect to the server or 'help' for the list of supported commands.
   [disconnected /] connect
   [standalone@localhost:9990 /]
   
   
   /subsystem=ejb3:write-attribute(name=allow-ejb-name-regex,value=true)
   /subsystem=ejb3:list-add(name=server-interceptors,value={module=org.wildfly.quickstarts,class=co.elastic.apm.ejb.ApmInterceptor})
	
  ```
 