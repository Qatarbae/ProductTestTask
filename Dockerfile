FROM maven:3.9.4-eclipse-temurin-17 as build
LABEL authors="Qatarbae"

COPY src src
COPY pom.xml pom.xml

RUN mvn clean package
FROM bellsoft/liberica-openjdk-debian:17

RUN adduser --system manager && addgroup --system manager && adduser manager manager
USER manager

WORKDIR /app

COPY --from=build target/ProductTestTask-0.0.1-SNAPSHOT.jar ./application.jar

ENTRYPOINT ["java", "-jar", "./application.jar"]