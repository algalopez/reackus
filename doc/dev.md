# Dev

## Database

Start Database with dev docker compose
Build flyway image (if it is not already built) with
```shell script
./gradlew buildFlywayImage
```
Create tables with
```shell script
./gradlew createDb
./gradlew createDbPre
```

## Sonarqube

Start Sonarqube with dev docker compose  
Create a token in sonarqube and set its value to a new environment variable SONAR_LOGIN
```shell script
./gradlew sonarqube -Dsonar.projectKey=reackus -Dsonar.host.url=http://localhost:19000 -Dsonar.login=$SONAR_LOGIN
```

