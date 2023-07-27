FROM openjdk:20
EXPOSE 8080
LABEL maintainer="alexanderleonidasguenzel@gmail.com"
ADD "backend/target/capstone-easyplan.jar" app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
