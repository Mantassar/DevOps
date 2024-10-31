FROM openjdk:11-jdk-slim
EXPOSE 8082

# Add the JAR file to the Docker image
ADD target/DevOps_Project-1.0.jar DevOps_Project-1.0.jar

# Set the entry point to run the JAR file
ENTRYPOINT ["java", "-jar", "/DevOps_Project-1.0.jar"]
