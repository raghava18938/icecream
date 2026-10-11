# Multi-stage build for Spring Boot app
FROM maven:3.9-eclipse-temurin-21-alpine AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ src/
RUN mvn clean package -DskipTests

# Final stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/icecream-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Set environment for Spring Boot
ENV SPRING_PROFILES_ACTIVE=prod
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

