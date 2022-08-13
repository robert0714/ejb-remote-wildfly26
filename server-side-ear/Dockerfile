# BUILD STAGE
FROM ubuntu:14.04  AS builder

WORKDIR /app0

RUN  apt-get update \
  && apt-get install -y wget \
  && wget -O elastic-apm-agent-1.33.0.jar https://repo1.maven.org/maven2/co/elastic/apm/elastic-apm-agent/1.33.0/elastic-apm-agent-1.33.0.jar -P /app0/ \
  && wget -O opentelemetry-javaagent.jar https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.14.0/opentelemetry-javaagent.jar -P /app0/ 


# FINAL STAGE
# FROM  quay.io/eduk8s/jdk8-environment:latest # for Debug  
# FROM adoptopenjdk/openjdk8:alpine
# FROM adoptopenjdk/openjdk8:x86_64-ubi-jdk8u342-b07
FROM registry.access.redhat.com/ubi8/openjdk-8:1.13

COPY ./target/ejb-remote-server-side-bootable.jar  /app/ejb-remote-server-side-bootable.jar
WORKDIR /app

COPY --from=builder /app0 .

 


EXPOSE 8080/tcp 9990/tcp
ENV TZ=Asia/Taipei
ENV JAVA_OPTS=""

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS     -jar  /app/ejb-remote-server-side-bootable.jar"]