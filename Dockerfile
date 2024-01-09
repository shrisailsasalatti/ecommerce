FROM openjdk:17-jdk-alpine  # Base image for Java

WORKDIR /app  # Set working directory

COPY target/<gradle/wrapper/gradle-wrapper>.jar  # Copy JAR file

ENTRYPOINT ["java", "-jar", "gradle-wrapper.jar"]  # Start the application
