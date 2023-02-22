FROM openjdk:19-jdk-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} user-app.jar
ENTRYPOINT exec java $JAVA_OPTS  -jar /user-app.jar