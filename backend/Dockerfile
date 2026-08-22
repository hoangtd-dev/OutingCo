FROM gradle:8.14-jdk21 AS build
WORKDIR /workspace

COPY gradle gradle
COPY gradlew settings.gradle build.gradle ./
RUN ./gradlew --no-daemon dependencies || true

COPY src src
RUN ./gradlew --no-daemon clean bootJar

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
