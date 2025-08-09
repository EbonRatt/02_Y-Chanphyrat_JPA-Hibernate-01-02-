# Stage 1: Build the jar
FROM gradle:8.10.0-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle clean build -x test

# Stage 2: Run the jar
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
