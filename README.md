# VehicleRouting
A micro service Spring Boot app with an genetic algorithm to solve the vehicle routing problem

It was developed for a job interview process

## Problem description:

It provides the following endpoints with these minimal mandatory attributes:
- Restaurants: create, update and get by id
`{"id": 1, "lat" : "0.0", "lon" : "0.0"}`
- Clients: create, update and get by id
`{"id": 1, "lat" : "0.0", "lon" : "0.0"}`
- Orders: Create, get by id and query by restaurant with optional filtering by delivery time. The pickup time is the moment when the dish is ready and the delivery time is the due time to deliver the dish.
```json
{
    "id": 1,
    "restaurantId": 1,
    "clientId": 1,
    "pickup": "2018-06-05T13:37:00Z",
    "delivery": "2018-06-05T13:54:00Z"
}
```
_* This example illustrates the problem._

It also provides an endpoint which returns the solution proposed following the example below.
```json
{
	"routes": [
		{
			"orders": [1, 2, 5]
		}
		{
			"orders": [3]
		}
	]
}
```
_* Same consideration. The ids are the orders that are picked and delivered in one route by a driver._

## The goals of the challenge:

- You have limitless drivers.
	-> but it is better to minimize the number of drivers.
- A worker can do at most 3 deliveries of the same restaurant at the same time (i.e. in the same route)
- A worker can only do deliveries of a single restaurant at a time (i.e. in the same route)
- You should try to avoid delivering an order after the delivery time
- You CAN'T pick up an order before the pickup time
- The driver is on the restaurant at the pickup time
- Consider that the driver goes 0.1 units in line each 5 minute.
- Each order must be optimized only once
- You should elaborate the algorithm to solve the Vehicle Routing Problem (do not use libraries for that)



## How to

Building and running, execute in project directory

`./gradlew clean build && java -jar build/libs/vehicle-routing-0.0.1-SNAPSHOT.jar`


After started all endpoints should be listed at:

http://localhost:8080/swagger-ui.html


If you want to reduce the response time of the endpoint /routes/optmize
go to application-context.properties and change it:
`vr.ga.max-generations=2500 (minimum 500 is recomended)`


start a new thread pool with the number of cpus avaliable to run optmizations:
`vr.ga.parallel=true`

OBS: This project is configured with an embedded mongodb (in memory).
Everything that have persisted will be lost after restarting
