# Stage 1: Build
FROM docker.io/gradle:8.12.1-jdk23 AS builder
WORKDIR /app

# Copy only necessary files to take advantage of caching
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle.kts build.gradle.kts
COPY settings.gradle.kts settings.gradle.kts
COPY spotless.xml spotless.xml
COPY libs.versions.toml libs.versions.toml

# Pre-fetch dependencies for caching
RUN ./gradlew --no-daemon dependencies
RUN ./gradlew --no-daemon check --dry-run

# Copy source code and build the application
COPY src src
RUN ./gradlew --no-daemon clean build

# Stage 2: Runtime
FROM docker.io/amazoncorretto:23 AS runtime
WORKDIR /app

# Copy the built application from the builder stage
COPY --from=builder /app/build/libs/kafka-data-processor-0.0.1-SNAPSHOT.jar app.jar

# Default command
CMD ["java", "-Djava.security.manager=allow", "-jar", "app.jar"]
