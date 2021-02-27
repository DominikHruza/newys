FROM openjdk:8
ADD target/newys-rest.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "newys-rest.jar"]