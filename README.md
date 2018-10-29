[![Build Status](https://travis-ci.org/Garden-Share/GardenShare.svg?branch=master)](https://travis-ci.org/Garden-Share/GardenShare)


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