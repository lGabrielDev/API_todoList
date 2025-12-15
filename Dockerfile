FROM  openjdk:17-ea-10-jdk

WORKDIR /app

COPY /target/todo.jar /app/

EXPOSE 8080

CMD [ "java", "-jar", "/app/todo.jar" ]