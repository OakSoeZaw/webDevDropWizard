# CourseFriends

How to start the CourseFriends application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/coursefriends-1.0-SNAPSHOT.jar server config.yml`
2. export DATABASEPASSWORD=yourpass; need to be added when running it locally. 
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
