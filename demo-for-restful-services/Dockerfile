# Use the official OpenJDK 17 image from Docker Hub
FROM openjdk:17
# Set working directory inside the container
WORKDIR /app
# Copy the compiled Java application JAR file into the container
COPY ./target/demo-for-restful-services.jar /app
# Expose the port the Spring Boot application will run on
EXPOSE 8080
# Command to run the application
x = y
CMD ["java", "-jar", "demo-for-restful-services.jar"]
