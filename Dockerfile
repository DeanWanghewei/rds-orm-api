FROM openjdk:17-jre-slim
MAINTAINER wanghewei

ENV PARAMS=""

ENV TZ=PRC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

copy target/rds-orm-api.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /app.jar $PARAMS"]