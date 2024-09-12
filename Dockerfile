# Use an official Maven image to build the application
# This image includes Maven and JDK
FROM maven:3.8.6-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven POM file and the source code
COPY pom.xml .
COPY src ./src

# Package the application (this will create a JAR file in the target directory)
RUN mvn clean package -DskipTests

# Use an official OpenJDK runtime image to run the application
# This image includes only the JDK runtime, which is lighter than the Maven image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/demo-for-restful-services-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that the application will run on
EXPOSE 8080

# Specify the command to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
