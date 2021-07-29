FROM tomcat:8.5-alpine

RUN apk update \
    && apk add --no-cache openjdk8 \
    && apk add --no-cache maven \
    && apk add --no-cache git \
    && rm -f /var/cache/apk/*

WORKDIR /tmp
RUN git clone https://github.com/ikonovi/docker-test-task_L3.git

WORKDIR /tmp/docker-test-task_L3
RUN mvn clean package \
    && cp ./target/docker-task-1.0.war /usr/local/tomcat/webapps/
