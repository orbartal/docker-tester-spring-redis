# docker-tester-spring-redis

### Description

A simple example of compose and 3 dockers: springboot server, redis db and an e2e tester.

### Use cases and spring profiles

There are currently 3 spring profiles that define the redis db that the backend server will use:
1. test: use [embeded in memeory redis db](https://mvnrepository.com/artifact/it.ozimov/embedded-redis). No changes are needed in the config. Good for auto testing.
2. dev: use a local redis server. Fill in details for local DB in application-dev.properties. Good for manual testing locally.
3. comp: use a remote/docker redis server. Fill in detail in the compose file. Good for e2e testing with docker compose.

There are currently 4 mods to testing the backend:
1. Running integration test without running the real backend server. Using spring profile test.
2. Running the backend server as local app togther with a local redis db. Using spring profile dev.
3. Running the backend server as a docker directly. With embeded (test) or external DB (dev).
4. Running the backend server as a docker using compose. It uses the compose postgresql docker.

### Instructions on how to build/run the backend locally 

How to build, test, run and use the project server

Download the project from its github [repo](https://github.com/orbartal/docker-tester-spring-redis)
You can downalod it as zip and extract it. Or use git clone.

Install java 17 from [oracle](https://www.oracle.com/java/technologies/downloads/#java17) 

Install maven, on any OS, using [baeldung](https://www.baeldung.com/install-maven-on-windows-linux-mac) guide. 

In the terminal (or cmd) cd into dir "..\docker-tester-spring-sql\backend" and run:

mvn clean install

mvn spring-boot:run

Open url in browser and use the [swagger-ui](http://localhost:8080/swagger-ui/index.html)

### Instructions on how to build/run a single docker 

Install docker on your host.

In the terminal (or cmd) cd into dir "..\docker-tester-spring-redis\backend" and run 3 commands:
- mvn clean install -Dmaven.test.skip=true
- docker build -t orbartal/demo-spring-redis-backend .
- docker run -p 8080:8080 orbartal/spring-swagger-demo -d 

You can also run redis as a standalone docker.
- docker run -p 6379:6379 -d redis:latest
- docker run -p 8081:8081 --env REDIS_HOSTS=local:172.17.0.2:6379 -d rediscommander/redis-commander:latest

### Instructions on how to build and run a all dockers

Install docker on your host.

1 In the terminal (or cmd) cd into dir "..\docker-tester-spring-redis\backend" and run 2 commands:
- mvn clean install -Dmaven.test.skip=true
- docker build -t orbartal/demo-redis-spring-backend .

2 In the terminal (or cmd) cd into dir "..\docker-tester-spring-redis\tester" and run 2 commands:
- mvn clean install -Dmaven.test.skip=true
- docker build -t orbartal/demo-redis-spring-tester .
- docker run -p 8080:8080 orbartal/demo-spring-redis-tester -d 


3 In the terminal (or cmd) cd into dir "..\docker-tester-spring-redis" and run command:
- docker compose  --env-file .env up

4 Now open a browser with the following tabs:
- [redis-commander-ui](http://localhost:8081/)
- [demo-backend-ui](http://localhost:8080/swagger-ui/index.html)
- [demo-tester-ui](http://localhost:8090/swagger-ui/index.html)

How to validate the project
- You can query & manage redis directly with redis-commander-ui
- Use the backend rest api to do crud actions on redis
- Run full end to end test with the tester. First run a test. Then use its uid to get a report about its results.

#### Instructions on how to login into commander and manage redis tables

All the below details are define, and can be modify, in the ./docker-compose.yml file.

1 Open url in browser and use the [redis-commander](http://localhost:8081)

2 Login into commander (http://localhost:8081) using user and password:
- user: root
- password: root

3 Open the demo db in redis

4 Add a new key-value pair

5 In the side menu select an existing key value and edit or delete it

### All urls

Remember that the ports number depend on the .env values use by docker-compose
- [redis-commander-ui](http://localhost:8081/)
- [demo-backend-ui](http://localhost:8080/swagger-ui/index.html)
- [demo-tester-ui](http://localhost:8090/swagger-ui/index.html)