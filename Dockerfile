FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/*.jar app.jar
# expose et utiliser par kubernetes pour exposer le port voulue
EXPOSE 8000

CMD ["java", "-jar", "app.jar"]
