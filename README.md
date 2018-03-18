# Skip the Dishes API project


## Setup using docker

1. Create mysql using docker:

  `$ docker run --name skip-mysql -e MYSQL_ROOT_PASSWORD=root -d -p 3306:3306 mysql:5.7`

2. Connect
  
  `$ mysql -h 127.0.0.1 -P 3306 -u root -p`

3. Run the following commands:
```
create database skip;
create user 'skip'@'%' identified by 'skipthep4ssword';
grant all on skip.* to 'skip'@'%';
```

4. Start application on another terminal

  `mvn package && java -jar target/user-rest-api-0.0.1-SNAPSHOT.jar`

5. Back to mysql terminal, describe user table:
```
use user;
desc user;
```


## Setup using docker-compose

1. Pack application

 `mvn package`

2. Start docker-compose

 `docker-compose up`
 
 

