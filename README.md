## Business Intelligence application

### Description
The Business Intelligence application (BI demo) offers RESTful resources for accessing financial sales data. Implemented with the "diamond" datacube, it features three dimensions, each with a hierarchical structure.

A React frontend complements this setup, presenting sales information in a table format with collapsible/expandable rows and columns.

The primary goal is to show how dimensions can be easily swap showing any slice of the financial data. It provides users with the flexibility to manipulate the datacube, allowing them to view information tailored to their needs. Currently, the frontend table displays all dimension nodes, but the intention is to refine this functionality. 
It is implemented that users can fix specific dimensions to particular nodes, displaying only the selected node and its associated children. This approach ensures that users can efficiently navigate through the data and extract the most relevant insights for their analysis.
### Implementation
The application is implemented following the Domain Driven Design as a showcase how a project can be structured in a way to be easily maintainable and extendable with layer isolation.
The requirements of the task could have been covered with a simpler implementation, but the project would not have been modified easily if the requirements change.

The project consists of the following layers:
* application - provides an API for all functionality provided by the application
* domain - contains the core domain logic
* infrastructure - contains interfaces to the outer world. If the data source is changed, it will not affect the other layers.

### Setting up the database
Go to the following directory:

```
/{projectDirectory}/docker
```
Download the following
[MySQL dump](https://drive.google.com/file/d/1toUEFeBSV6Kp2pfRlCRxhegOHxaaaQla/view?usp=sharing)
and place it there. Then execute the following command:
```
docker-compose up
```
Have in mind that importing the SQL dump might take a while.

### Running the application with Maven
You can start the application by running:
```
./mvnw spring-boot:run
```
### Running the application with Docker
First create a docker image with the following command:
```
./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=jedox/bidemo
```
Then start the application by running:
```
docker run -p 8080:8080 jedox/bidemo
```
It might be that sometimes it doesn't see the MySQL container. It depends on the local settings. So please try
```
docker run --add-host=host.docker.internal:host-gateway -e MYSQL_HOST=host.docker.internal -p 8080:8080 jedox/bidemo
```
### Frontend can be run 
Please start the project in the directory 
```
/{projectDirectory}/bidemo-react
```
by running 
```
npm start
```
It runs on the
[http://localhost:3000](http://localhost:3000)
### Running the tests
Have in mind that it is required to have a configured and running database for the load tests. You can run the tests with the following command:
```
./mvnw clean verify
```

### Documentation
After starting the application the documentation can be found here:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Improvements
The following improvements can be done:
* more tests - unit, integration and load testing
* an isolated MySQL server used only in the load tests e.g. with testcontainers.
* React frontend can be improved.
* There needs to be Filters and the way user alowed to swap Dimensions and choose the parent dimension to limit the hierarchy.
