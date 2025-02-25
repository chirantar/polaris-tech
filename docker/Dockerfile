# Use OpenJDK 17
FROM maven:3.8.4-openjdk-17-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project file
COPY pom.xml .

# Download the project dependencies
RUN mvn dependency:go-offline -B

# Copy the application source code
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Use a smaller base image for the runtime environment
FROM openjdk:17.0.2 as app

RUN microdnf update && microdnf install -y procps

# Set the working directory in the container
WORKDIR /app

# Copy Maven build
COPY  --from=build /app/target/polaris-tech-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
