FROM openjdk:8
ADD target/HospitalManagementSystem-0.0.1-SNAPSHOT.jar HospitalManagementSystem-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "HospitalManagementSystem-0.0.1-SNAPSHOT.jar"]