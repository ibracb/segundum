FROM maven:3.8-openjdk-11-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

FROM jetty:9.4-jre11-slim
COPY --from=build /app/target/*.war /var/lib/jetty/webapps/ROOT.war
EXPOSE 8080
