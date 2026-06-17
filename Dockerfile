FROM quay.io/wildfly/wildfly:latest
COPY target/jsf-bedrock.war /opt/jboss/wildfly/standalone/deployments/
EXPOSE 8080
