# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim

LABEL maintainer="Shubh Mehrotra"\
      email="ShubhMehro@gmail.com"\
      name="Service Registry server"

WORKDIR /app

COPY target/ServiceRegistry.jar app.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "app.jar"]
