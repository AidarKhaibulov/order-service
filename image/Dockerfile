FROM openjdk:21-jdk
WORKDIR /app
COPY build/libs/*.jar /app/app.jar
EXPOSE 8070
CMD ["java", "-jar", "./app.jar"]