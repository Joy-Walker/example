FROM openjdk:8
MAINTAINER panligang

ENV APP_JAR_NAME="example-1.0-SNAPSHOT-1.0.tar.gz"

ADD $APP_JAR_NAME /app/

EXPOSE 8080

WORKDIR /app

CMD sh example-1.0-SNAPSHOT/start.sh dev



