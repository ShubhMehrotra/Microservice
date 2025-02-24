# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim

LABEL maintainer="Shubh Mehrotra"\
      email="ShubhMehro@gmail.com"\
      name="API gateway Microservices"

WORKDIR /app

COPY target/Shipping.jar app.jar

EXPOSE 8099

ENTRYPOINT ["java", "-jar", "app.jar"]
