#!/bin/sh
SERVER_PORT=8086
mvn clean package && java -jar target/gs-rest-service-0.1.0.jar --server.port=${SERVER_PORT}