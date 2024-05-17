FROM --platform=linux/amd64 openjdk:17

EXPOSE 8080

WORKDIR /app

COPY /target/*.jar reserva.jar

ENTRYPOINT ["java", "-jar", "reserva.jar"]