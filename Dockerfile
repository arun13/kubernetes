FROM openjdk:8
ADD target/account-app.jar account-app.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","account-app.jar"]