# Teams Microservice

<p align="center">The teams microservice consists of a database that manages the teams, leagues and matches from a variety of sports.</p>


<p align="center">
<img src="http://img.shields.io/static/v1?label=STATUS&message=IN%20PROGRESS&color=GREEN&style=for-the-badge"/>
</p>

## ✔️ stack

- ``Java 11``
- ``InteliJ IDEA``
- ``Springboot``
- ``Hibernate``
- ``Spring Data JPA``


## ✔️ features

- ``team insertion and update``
- ``league management trough season insertion and update``
- ``matches insertion and/or upload as well as score update``


# Instalation guide

In order to deploy teams ms you should have installed Java 11 as well as docker. For debugging purposes, please install an IDE of your choice.
Intellij community is enough for debugging teams microservice codebase.

After dowloading and installing the according tools, you run the following commands to setup the database.

```bash
docker pull mysql:latest
docker run -p 3306:3306 -p 33060:33060 --name mysql-container -e MYSQL_ROOT_PASSWORD=password123 -d mysql:latest
docker exec -it mysql-container mysql -uroot -ppassword123 -e "show databases;"
docker exec -it mysql-container mysql -uroot -ppassword123 -e "create database teams_db; show databases;"
docker exec -it mysql-container mysql -uroot -ppassword123 -e "CREATE USER 'user_odds'@'%' IDENTIFIED BY 'masterkey'; GRANT ALL PRIVILEGES ON * . * TO 'user_odds'@'%'; FLUSH PRIVILEGES;"
```

After the database setup, you need to run the command for generating the teams microservice jar file to be used in the respective image building.
Hint: run this command inside Intellij integrated terminal

```bash
chmod +x gradlew && ./gradlew build -x test
```

After the jar file is generated you can execute the docker commands to build the microservice image and run the container, where its going to be deployed

```bash
docker build -t ms-teams .


docker run -d -p 8080:8080 \
 -e ODDS_DB_PASSWORD='masterkey' \
 -e ODDS_DB_URL='jdbc:mysql://mysql-container:3306/teams_db?allowPublicKeyRetrieval=true&useSSL=false&useTimezone=true&serverTimezone=UTC' \
 -e ODDS_DB_USER='user_odds' --name teams-ms-dev --link mysql-container:mysql/mysql-server ms-teams
```

after running the command to setup the container, you should see the image below. I use postman, but any REST API client tool is fine.

![Screenshot from 2022-09-16 02-21-50](https://user-images.githubusercontent.com/36819882/190562797-1c6d941f-a419-4679-8229-b11ca85b1f55.png)


