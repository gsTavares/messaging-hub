FROM maven:3.8-openjdk-17 AS build

RUN git clone https://github.com/gsTavares/messaging-hub-be-core.git && \
    cd messaging-hub-be-core && \
    mvn clean install -DskipTests && \
    cd ..

COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim AS package
COPY --from=build /target/consumer-0.0.1-SNAPSHOT.jar messaging-hub-be-consumer.jar
EXPOSE 8081

ENTRYPOINT [ "java", "-jar", "messaging-hub-be-consumer.jar" ]