[![Build Status](https://travis-ci.org/Garden-Share/GardenShare.svg?branch=master)](https://travis-ci.org/Garden-Share/GardenShare)


MySQL Setup
=====

1. Download and Install Mysql server and a mysql client

2. Start the server

3. Connect as root user (On unix you have to connect as unix root user) `mysql -u root`

4. `CREATE USER 'gardenshare'@'localhost' IDENTIFIED BY 'somepassword'`

5. Create a new user for gardenshare `GRANT ALL PRIVILEGES ON *.* to 'gardenshare'@'localhost';`

6. `FLUSH PRIVILEGES;`

7. Create the database `CREATE DATABASE gardenshare;`


How to build
=====

#### Locally

1. Set up a mysql server locally

2. Create a user and database table in mysql

3. Create a 'secrets' file in `secrets/env.sh`

It should look like this (make sure that it has LF endings and not CRLF)

```
export DB_URL=**************/DB_TABLE_NAME
export DB_USER=**************
export DB_PASS=**************
```

4. Run `. secrets/env.sh` to load the secrets

5. Run `mvn spring-boot:run` to start the server

6. Connect to the server using `http://localhost:8080`

#### Dockerfile

1. Set up a mysql server locally

2. Create a user and database table in mysql

3. Run `docker build . -t gardenshare`

4. Run `docker run -it --network=host -e DB_URL=****/DB_TABLE_NAME -e DB_USER=**** -e DB_PASS=***** gardenshare`

5. Connect to the server using `http://localhost:8080`