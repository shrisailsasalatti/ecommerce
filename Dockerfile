FROM openjdk:17-jdk-alpine  # Base image for Java

WORKDIR /app  # Set working directory

RUN ./gradlew build  # Build the application

COPY target/<your-app-jar-name>.jar /app/app.jar  # Copy the built JAR file

ENTRYPOINT ["java", "-jar", "app.jar"]  # Start the application