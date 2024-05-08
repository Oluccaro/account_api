# account_api

This application is build for a take home test for Ebanx!

It is build in java with Spring Boot with JPA. It stores data on a temporary H2 data base. It also runs on Docker 

---

To run this application you must have installed

- docker 23+
- docker-compose 1.29+
- maven compiler to generate the .jar file

---

Then you must execute the `start.sh` script, located on the `/account` folder. It will package the application, create the docker image and run the image with docker-compose.
First you give the permissions to execute the script `chmod 774 start.sh` then run
```sh
./start.sh
``` 

