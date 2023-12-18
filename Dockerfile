FROM openjdk:17
EXPOSE 8181
ADD target/quizapp-3.1.1.jar quizapp-3.1.1.jar
ENTRYPOINT ["java","-jar","/quizapp-3.1.1.jar"]