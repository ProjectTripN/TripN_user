FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
ARG JAR_FILE=*.jar
EXPOSE 8080:8080
COPY ./build/libs/*.jar app.jar
CMD ["java", "-jar", "/app.jar"]