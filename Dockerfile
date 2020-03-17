FROM openjdk:11.0.6-jdk

COPY /target/falcon-task-0.0.1-SNAPSHOT.jar /falcon-task-0.0.1-SNAPSHOT.jar

EXPOSE 8090

RUN chmod +x /falcon-task-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/falcon-task-0.0.1-SNAPSHOT.jar"]
