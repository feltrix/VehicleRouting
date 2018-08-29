# VehicleRouting
A microservice Spring Boot app with an genetic algorithm to solve the vehicle routing problem

Building and running, execute in project directory

./gradlew clean build && java -jar build/libs/vehicle-routing-0.0.1-SNAPSHOT.jar


After started all endpoints should listed at:
http://localhost:8080/swagger-ui.html


If you want to reduce the response time of the endpoint /routes/optmize
go on application-context and change:
vr.ga.max-generations=2500 (minimum 500 is recomended)


start a new thread pool with the number of cpus avaliable to run optmizations
vr.ga.parallel=true