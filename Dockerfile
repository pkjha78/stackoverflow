# Start with a base image containing Java runtime
FROM java:8

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080, 8443 available to the world outside this container
EXPOSE 8080
EXPOSE 8443

# The application's jar file
ARG JAR_FILE=build/libs/*SNAPSHOT.jar

# Copy the application's jar to the container
COPY ${JAR_FILE} /app.jar

# Run the jar file 
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=prod", "-jar", "/app.jar"]