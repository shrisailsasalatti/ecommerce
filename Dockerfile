FROM openjdk:17-jdk-alpine  # Base image for Java

WORKDIR /app  # Set working directory

COPY target/gradle-wrapper.jar /app/app.jar  # Copy JAR file

ENTRYPOINT ["java", "-jar", "app.jar"]  # Start the application

RUN ./gradlew build
