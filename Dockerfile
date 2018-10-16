FROM maven:latest

WORKDIR /app
ADD . /app

RUN mvn compile

EXPOSE 8080

CMD mvn spring-boot:run
