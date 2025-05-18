# Use a stable JDK image
FROM openjdk:25-ea-4-jdk-oraclelinux9

# Set the working directory
WORKDIR /app

# Copy the JAR file (make sure this path is correct)
COPY target/notification-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
