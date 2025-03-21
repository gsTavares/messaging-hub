FROM maven:3.8-openjdk-17 AS build
COPY . .
RUN mvn clean package

FROM openjdk:17-jdk-slim AS package
COPY --from=build /target/authentication-0.0.1-SNAPSHOT.jar messaging-hub-be-authentication.jar
EXPOSE 8081

ENTRYPOINT [ "java", "-jar", "messaging-hub-be-authentication.jar" ]