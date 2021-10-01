# reackus Project


## App


Running the application in dev mode
```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

Package the application
```shell script
./gradlew build
```
> **_NOTE:_**  It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.  
> The dependencies are copied into the `build/quarkus-app/lib/` directory.

Package the application in an _über-jar_
```shell script
./gradlew build -Dquarkus.package.type=uber-jar
```

Run the application
```shell script
java -jar build/quarkus-app/quarkus-run.jar
```

## Creating a native executable

Create a native executable: 
```shell script
./gradlew build -Dquarkus.package.type=native
```

Create a native executable without GraalVm:
```shell script
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```

Run the application
```shell script
./build/reackus-1.0.0-SNAPSHOT-runner
```

## Database

Start Database with dev docker compose
Build flyway image if it is not already built with 
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

