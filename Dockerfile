FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /home
COPY pom.xml .
COPY employee-domain/pom.xml employee-domain/pom.xml
COPY employee-domain/domaine-entities/pom.xml employee-domain/domaine-entities/pom.xml
COPY employee-domain/domaine-use-cases/pom.xml employee-domain/domaine-use-cases/pom.xml
COPY employee-datasource/pom.xml employee-datasource/pom.xml
COPY employee-api/pom.xml employee-api/pom.xml

RUN mvn dependency:go-offline -B
COPY . .
RUN mvn -B clean package -DskipTests
FROM eclipse-temurin:21-jre-alpine

WORKDIR /home

COPY --from=build /home/employee-api/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]