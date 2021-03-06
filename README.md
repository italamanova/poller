# Polling app

A simple service poller that keeps track of services and checks if they are up.

## Usage

To install this application and run server, run the following commands:

```bash
git clone https://github.com/italamanova/poller.git
cd poller/backend
mvn clean install
java -jar target/poller-0.0.1-SNAPSHOT.jar
```

To run the client, run the following commands:

```bash
cd poller/backend
npm install
npm start
```

To access the website go to:

```
http://localhost:3000/
```

### Overview

Polling logic is implemented by using two schedulers, on the backend and frontend parts. In the backend the get request
is sent to the service(endpoint) every 5 seconds and the result is written to the database. In the frontend the data is
constantly updated by getting the latest version from a server every 3 seconds.

On the website the user can add a new service by url, see the list of all services, their status, creation and latest
update date. The user can delete a service. Also the url should have a valid format.

#### Technologies

The server is implemented using Java and Spring Boot framework.  
For storing data PostgreSql database was used.  
The client is implemented using Javascript and React library.

#### Future improvements

- add more tests to both backend and frontend
- add updating service functionality

