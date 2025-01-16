FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/*.jar app.jar
# 8080 port is using by jenkins server
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=uat","app.jar"]
