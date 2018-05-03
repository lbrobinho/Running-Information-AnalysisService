# Running-Information-AnalysisService

 Implement a Running Information Analysis Service

This is a service designed in **Spring Boot/Data** to provide **REST** APIs for storing/retrieving runner's information in **MySQL** database.

## Development environment
- MySQL DB is running on **docker**, image specified in **docker-compose.yml** file, and started by **docker-compose**.
- **Spring Data Rest** is used for quick DB access and verification.
- **Spring Boot** is used for fast REST API development.
- **lombok** is used to eliminate constructors and getter/setter implementation for cleaner coding style.

## Features
- Each RunningInformation includes runningId, latitude, longitude, runningDistance, totalRunningTime,heartRate, timestamp and userInfo.
```json
ex:
{
  "runningId": "7c08973d-bed4-4cbd-9c28-9282a02a6032", 
  "latitude": "38.9093216",
  "longitude": "-77.0036435",
  "runningDistance": "39492",
  "totalRunningTime": "2139.25",
  "heartRate": 0,
  "timestamp": "2017-04-01T18:50:35Z",
  "userInfo": 
  {
    "username": "ross0",
    "address": "504 CS Street, Mountain View, CA 88888" 
  }
}
```
- UserInfo includes username, address.
- Initial heartRate is 0, need to randomly generate a value between 60 to 200 to insert into DB.
- RunningInformation is stored into table named "private".
- The REST API returns the following JSON response:
```json
ex: 
{
  "runningId": "7c08973d-bed4-4cbd-9c28-9282a02a6032", 
  "totalRunningTime": 2000,
  “heartRate”: 75,
  “userId”: 1,
  "userName": "Ross",
  "userAddress": "504 CS Street, Mountain View, CA 88888", "healthWarningLevel": HIGH
}
```
- The response is sorted in descending order or healthWarningLevel, 2 items per page.
- Provide delete by running ID API.
- healthWarningLevel is decided by heartRate:
  - heartRate >= 60 && <= 75, healthWarningLevel = "LOW".
  - heartRate > 75 && <= 120, healthWarningLevel = "NORMAL".
  - heartRate > 120, healthWarningLevel = "HIGH".
- Running information is stored into MySQL DB.

## Project structure
```
src/
|-- main/
|------ java/
|---------- demo/
|-------------- domain/
|------------------ RunningInformation.java
|------------------ UserInfo.java
|-------------- repository/
|------------------ RunningInformationRepository.java
|-------------- service/
|------------------ impl/
|---------------------- RunningInformationServiceImpl.java
|------------------ RunningInformationService.java
|------ resources/
|---------- application.yml
docker-compose.yml
pom.xml
supplyRunningInformations.json
upload-running-information.sh
```

## Getting started
### Download code
``` 
git clone https://github.com/hackjutsu/RunningInformationAnalysisServiceTA.git
cd RunningInformationAnalysisServiceTA
```

### launch database

#### launch MySQL Docker
```
docker-compose up -d
```

### log in database

 log in database console
```
mysql --host=127.0.0.1 --port=3306 --user=root --password=root 
```

 view exist database
```
show databases;
```

If running_information_analysis_db is not existed，create a new one
```
create database running_information_analysis_db;
```

log out database console
```
\q
```

### launch Spring Application
```
mvn clean install
```

#### launch server

```
java -jar ./target/running-information-analysis-service-1.0.0.BUILD-SNAPSHOT.jar
```



Follow rel: **runningInformations GET** to investigate runningInformatios stored on DB.
### Explore by Postman

#### For search: **GET**
```
http://localhost:9002/runningInformations?page=0&size=10&property=healthWarningLevel&desc=true

page: starting page #
size: # of items per page
property: name of property to sort by
desc: true: descending, false: ascending
```
#### For delete by runningId: **DELETE**
```
http://localhost:9002/runningInformations/7c08973d-bed4-4cbd-9c28-9282a02a6032
```
## LICENSE

[MIT](./License.txt)

