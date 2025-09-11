# Use an official Maven image as the base image
FROM mvkvl/maven:jdk-21-alpine AS build
# Set the working directory in the container
WORKDIR /app
# Copy the pom.xml and the project files to the container
COPY pom.xml .
COPY src ./src
# Build the application using Maven
RUN mvn clean install -DskipTests

# Use an official OpenJDK image as the base image
FROM alpine/java:21-jdk
# Set the working directory in the container
WORKDIR /app
# Copy the built JAR file from the previous stage to the container
COPY --from=build /app/target/javaadvanced-0.0.1-SNAPSHOT.jar  .
# Set the command to run the application
CMD ["java", "-jar", "javaadvanced-0.0.1-SNAPSHOT.jar"]
