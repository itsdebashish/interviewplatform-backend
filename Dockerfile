# ---------- Build Stage ----------
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /workspace

# Copy Gradle wrapper and settings
COPY gradlew .
COPY gradlew.bat .
COPY gradle gradle
COPY settings.gradle .

# Copy app module
COPY app app

RUN chmod +x gradlew

# Build the Spring Boot application
RUN ./gradlew :app:bootJar --no-daemon

# ---------- Runtime Stage ----------
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /workspace/app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
