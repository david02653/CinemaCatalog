FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD cinemacatalog-3.1.2-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]