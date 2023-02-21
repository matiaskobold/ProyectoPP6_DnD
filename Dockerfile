FROM openjdk:17-alpine
ADD target/ProyectoPP6-0.0.1-SNAPSHOT.jar ProyectoPP6-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ProyectoPP6-0.0.1-SNAPSHOT.jar"]