# Build stage
FROM gradle:7.2.0-jdk17 AS build
WORKDIR /workspace/app

COPY build.gradle .
COPY settings.gradle .
COPY gradle gradle
COPY --chown=gradle:gradle src src
COPY --chown=gradle:gradle gradlew .
COPY --chown=gradle:gradle gradlew.bat .

# Grant execution permissions to the Gradle wrapper
RUN chmod +x ./gradlew

RUN ./gradlew build -x test --no-daemon

FROM openjdk:17
VOLUME /tmp
COPY --from=build /workspace/app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
