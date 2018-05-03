# Running-Information-AnalysisService

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
