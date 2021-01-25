FROM adoptopenjdk/openjdk11:alpine-slim
MAINTAINER Eduardo Ramos
RUN apk update && apk add bash
RUN mkdir -p /opt/app
ENV PROJECT_HOME /opt/app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} $PROJECT_HOME/pokedex.jar
WORKDIR $PROJECT_HOME
CMD ["java", "-jar", "./pokedex.jar"]