# Build Stage
FROM openjdk:17-jdk-alpine as builder

WORKDIR /app

# Copy only the necessary files for running the Gradle build
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle

# Change permissions to make the Gradle wrapper script executable
RUN chmod +x ./gradlew

# Run the Gradle wrapper to download and cache the Gradle distribution
RUN ./gradlew build --no-daemon

# Copy the rest of the project
COPY . .

# Final Stage
FROM openjdk:17-jdk-alpine

WORKDIR /app

# Copy the generated JAR file from the build stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the port your Spring Boot app will run on (default is 8080)
EXPOSE 8080

# Entrypoint command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
