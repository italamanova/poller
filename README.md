# Polling app

A simple service poller that keeps track of services and checks if they are up.

## Usage

To install this example application, run the following commands:

```bash
git clone https://github.com/italamanova/poller.git
cd poller/backend
mvn clean install
java -jar target\poller-0.0.1-SNAPSHOT.jar
cd ../frontend
npm install
npm start
```

### Architecture

Polling logic is implemented by using two schedulers, on the backend and frontend parts.
On the backend the get request is send to the service(endpoint) every 5 seconds and the result is written in the database.
On the frontend the data is constantly updated by getting the latest data from a server every 3 seconds.

On the website the user can add a new service by url, see the list of all services, their status, creation and latest update date.
The user can delete a service. Also the url should have a valid format.

The server is implemented using Java and Spring Boot framework.  
The client is implemented using React.

#### Future improvements

- add more tests to both backend and frontend
- add updating service functionality

