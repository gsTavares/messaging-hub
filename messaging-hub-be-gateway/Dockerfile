FROM maven:3.8-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim AS package
COPY --from=build /target/gateway-0.0.1-SNAPSHOT.jar messaging-hub-be-gateway.jar
EXPOSE 8082

ENTRYPOINT [ "java", "-jar", "messaging-hub-be-gateway.jar" ]