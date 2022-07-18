FROM openjdk:17-jdk-alpine
ARG JAR_FILE=lazyAddress-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} myboot.jar
ENTRYPOINT ["java","-jar","/myboot.jar"]