#!/bin/sh

sed -i -e "s/DB_PASSWORD/$DB_PASSWORD/g" src/main/resources/application.properties
