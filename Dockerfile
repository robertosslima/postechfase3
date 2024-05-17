FROM --platform=linux/amd64 openjdk:17

EXPOSE 8090

WORKDIR /app

COPY /target/*.jar reserva.jar

ENTRYPOINT ["java", "-jar", "reserva.jar"]