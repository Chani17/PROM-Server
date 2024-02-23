# Use the offical OpenJDK base image
FROM openjdk:17
CMD ["/.gradlew", "clean", "package"]
ARG JAR_FILE_PATH=build/libs/*.jar
COPY ${JAR_FILE_PATH} from.jar
# Expose the port app is running on(change to match app’s port)
EXPOSE 8081
# Command to run the application
ENTRYPOINT ["java", "-jar", "from.jar"]