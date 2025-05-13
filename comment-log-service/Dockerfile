FROM openjdk:25-ea-4-jdk-oraclelinux9

WORKDIR /app

COPY ./target/ target/

ENV instance_name="Main Instance"

EXPOSE 8080

# We change the name of the build file into lab4 in pom.xml
ENTRYPOINT ["java", "-jar", "target/task.jar"]