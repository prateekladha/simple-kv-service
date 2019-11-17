ARG BASE_IMAGE=openjdk:8-alpine
FROM ${BASE_IMAGE}
ARG version=0.0.1
COPY target/simple-kv-service-$version.jar /simple-kv-service.jar
ENTRYPOINT ["/bin/sh", "-c"]
CMD ["/usr/bin/java ${JVM_OPTS} -jar /simple-kv-service.jar"]
