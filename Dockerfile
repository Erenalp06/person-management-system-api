FROM openjdk:19
WORKDIR /app
EXPOSE 9643
ADD target/person-management-system-api-v1.0.jar person-management-system-api-v1.0.jar
ENTRYPOINT ["java", "-jar", "/app/person-management-system-api-v1.0.jar"]