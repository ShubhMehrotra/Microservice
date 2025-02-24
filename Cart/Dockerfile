# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim

LABEL maintainer="Shubh Mehrotra"\
      email="ShubhMehro@gmail.com"\
      name="API gateway Microservices"

WORKDIR /app

COPY target/Cart.jar app.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "app.jar"]
