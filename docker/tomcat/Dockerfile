FROM tomcat:8.5-alpine

RUN apk update \
    && apk add --no-cache openjdk8 \
    && apk add --no-cache maven \
    && apk fetch git && apk add --no-cache git \
    && rm -f /var/cache/apk/*

WORKDIR /tmp
RUN rm -fR ./docker-test-task_L3 \
    && git clone https://github.com/ikonovi/docker-test-task_L3.git

WORKDIR /tmp/docker-test-task_L3
RUN mvn clean package \
    && rm -fR /usr/local/tomcat/webapps/docker-task-1.0.war \
    && cp ./target/docker-task-1.0.war /usr/local/tomcat/webapps/
