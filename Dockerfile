FROM openjdk:11
ADD target/newys-rest.jar newys-rest.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "newys-rest.jar"]