# Use the official OpenJDK 17 image as the base image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle files
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle

# Give execute permission to gradlew
RUN chmod +x ./gradlew

# Set the Gradle version
ENV GRADLE_VERSION=8.5

# Download and install Gradle
RUN wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -P /tmp && \
    unzip -d /opt/gradle /tmp/gradle-${GRADLE_VERSION}-bin.zip && \
    ln -s /opt/gradle/gradle-${GRADLE_VERSION} /opt/gradle/latest

ENV GRADLE_HOME=/opt/gradle/latest
ENV PATH=$PATH:$GRADLE_HOME/bin

# Print the current directory and list its content
RUN pwd && ls -al

# Print the content of the gradlew file
RUN cat ./gradlew

# Run the Gradle build with additional debugging
RUN ./gradlew build --no-daemon --stacktrace --info

# Copy the rest of the project
COPY . /app/

# Expose the port that your application will run on
EXPOSE 8080

# Define the command to run your application
CMD ["./gradlew", "bootRun"]
