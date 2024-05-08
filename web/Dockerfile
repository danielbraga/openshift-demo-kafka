FROM eclipse-temurin:17-centos7

USER root

RUN mkdir -p /deployments

# JAVA_APP_DIR is used by run-java.sh for finding the binaries
ENV JAVA_APP_DIR=/deployments \
    JAVA_MAJOR_VERSION=17 \
    JAVA_APP_JAR=app.jar \
    JAVA_OPTIONS="-Xmx64m -Xss256k"

# Add run script as /deployments/run-java.sh and make it executable
#COPY run-java.sh /deployments/
#RUN chmod 755 /deployments/run-java.sh

# Run under user "jboss" and prepare for be running
# under OpenShift, too
RUN groupadd -r jboss -g 1000 \
  && useradd -u 1000 -r -g jboss -m -d /opt/jboss -s /sbin/nologin jboss \
  && chmod 755 /opt/jboss \
  && chown -R jboss /deployments \
  && usermod -g root -G `id -g jboss` jboss \
  && chmod -R "g+rwX" /deployments \
  && chown -R jboss:root /deployments

RUN /usr/bin/rm -f /etc/localtime
RUN ln -s /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime
RUN yum clean all && yum makecache && yum update tzdata tzdata-java -y

RUN mkdir -p /config && touch /config/application.properties && ln -s /config/application.properties /deployments/application.properties

USER jboss

#EXPOSE 8080

#ADD application.properties /config/
#ADD distribuidor-jar-with-dependencies.jar /deployments/
ENV JAVA_APP_JAR op-demo-kafka-web.jar
ADD target/op-demo-kafka-web.jar /deployments/
ADD src/main/resources/application.properties /config/

WORKDIR /deployments

CMD [ "/usr/bin/bash", "-c", "/opt/java/openjdk/bin/java ${JAVA_OPTIONS} -cp . -jar /deployments/${JAVA_APP_JAR}" ]
