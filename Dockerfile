FROM  openjdk:17

WORKDIR /app

COPY /target/todo.jar /app/

EXPOSE 8080

CMD [ "java", "-jar", "/app/todo.jar" ]