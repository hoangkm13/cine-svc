#
# Build stage
#
FROM maven:3.6.0-jdk-11 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml -B package -DskipTests

#
# Package stage
#
FROM openjdk:11
COPY --from=build /home/app/target/cinema-0.0.1-SNAPSHOT.jar cinema.jar

ENV JAVA_OPTS="-Djdk.tls.client.protocols=TLSv1.2 -Xms400m"
EXPOSE 5000
ENTRYPOINT ["java","-jar","-Xms400M","cinema.jar"]