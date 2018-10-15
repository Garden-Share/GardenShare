FROM maven:latest

WORKDIR /app
ADD . /app

RUN mvn package

EXPOSE 8080

RUN /app/inject_creds.sh

CMD mvn spring-boot:run
