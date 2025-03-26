FROM maven:3.8-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim AS package
COPY --from=build /target/producer-0.0.1-SNAPSHOT.jar messaging-hub-be-producer.jar
EXPOSE 8086

ENTRYPOINT [ "java", "-jar", "messaging-hub-be-producer.jar" ]