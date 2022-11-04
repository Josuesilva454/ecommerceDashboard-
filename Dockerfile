FROM openjdk:11

ARG JAR_FILE=target/demo.jar

ADD ${JAR_FILE} app.jar


ENTRYPOINT ["java","-jar","/app.jar"]


