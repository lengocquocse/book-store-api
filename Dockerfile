# Use the official OpenJDK as a parent image
FROM openjdk:22-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built jar file from the target folder into the container
COPY target/*.jar app.jar

# Expose the application port (change this if your app uses a different port)
EXPOSE 8443

# Run the application using the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
