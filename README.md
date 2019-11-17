### Development Setup
- Run below command to execute the project:<br/>
  `mvn spring-boot:run`
- Server is running on `http://localhost:8080`

### Run Through Docker
```
mvn clean package
docker rmi --force simple-kv-service
docker build --build-arg version=${project.version} -t simple-kv-service .
docker run -it -p8080:8080 -e JVM_OPTS="-Xmx2048m" -e DB_URL="jdbc:mysql://docker.for.mac.localhost:3306/common?useSSL=false" -e DB_PASSWORD="${db.password}" simple-kv-service
```

### Swagger
- http://localhost:8080/swagger-ui.html