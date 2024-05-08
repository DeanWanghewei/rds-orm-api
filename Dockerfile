FROM openjdk:17-jdk-slim

MAINTAINER wanghewei


ENV PARAMS=""
ENV MY_JAVA_OPTS=""

ENV TZ=PRC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

copy target/rds-orm-api.jar /app.jar

EXPOSE 8080


ENTRYPOINT ["sh","-c","java ${JAVA_OPTS} ${MY_JAVA_OPTS} -jar /app.jar $PARAMS"]