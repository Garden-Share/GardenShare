FROM maven:latest

WORKDIR /app
ADD . /app

RUN mvn package

EXPOSE 8080

CMD mvn spring-boot:run
