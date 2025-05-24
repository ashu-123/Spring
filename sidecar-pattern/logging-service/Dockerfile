FROM openjdk:21

VOLUME /tmp
VOLUME /logs

WORKDIR /app

COPY ./target/logging-service.jar /app

EXPOSE 8081

CMD ["java", "-jar", "logging-service.jar"]