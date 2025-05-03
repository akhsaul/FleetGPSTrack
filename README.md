# FleetGPSTrack

This is a Back-end for Fleet GPS Tracking.

Endpoints:
<details>
  <summary>save GPS log</summary>

### Request
Method: **POST**

Path: `/api/gps`

Request body:
```json
{
  "vehicle_id": 100,
  "latitude": -75.993178,
  "longitude": 162.017737,
  "speed": 44.92,
  "time_stamp": "2023-12-18T22:03:43Z"
}
```

### Response body when successfully saving the log
Status code: **201**

Response body:
```json
{
  "status": "success",
  "message": "Successfully save GPS log",
  "data": null
}
```

### Response body when some property/input is not valid
Status code: **400**

Request body:
```json
{
  "vehicle_id": 100,
  "latitude": -75.993178,
  "longitude": 162.017737,
  "timestamp": "2023-12-18T22:03:43Z"
}
```
Response body:
```json
{
  "status": "error",
  "message": "speed must not be null",
  "data": null
}
```

### Response body when vehicle id is not found
Status code: **404**

Response body:
```json
{
  "status": "error",
  "message": "Vehicle not found",
  "data": null
}
```

### Response body when JWT token is not valid
Status code: **403**

Response body: **No response body**
</details>


<details>
  <summary>Get last location of vehicle</summary>

### Request
Method: **GET**

Path: `/api/vehicle/{id}/last-location`

Request body: **No request body**


### Response body when vehicle has data location
Status code: **200**

Response body:
```json
{
  "status": "success",
  "message": "successfully get last location",
  "data": [
    {
      "id": 1,
      "vehicleId": 100,
      "latitude": -75.993178,
      "longitude": 162.017737,
      "speed": 44,
      "timestamp": "2023-12-18T22:03:43Z",
      "speedViolation": false
    }
  ]
}
```

### Response body when vehicle has no data location
Status code: **200**

Response body:
```json
{
  "status": "success",
  "message": "successfully get last location",
  "data": null
}
```

### Response body when vehicle id is not found
Status code: **404**

Response body:
```json
{
  "status": "error",
  "message": "Vehicle not found",
  "data": null
}
```

### Response body when JWT token is not valid
Status code: **403**

Response body: **No response body**
</details>


<details>
  <summary>Get location history of vehicle</summary>


### Request
Method: **GET**

Path: `/api/vehicle/{id}/history?from={from}&to={to}`

Request body: **No request body**

### Response body when vehicle has data location
Return a location data from date `from` until date `to`

Status code: **200**

Request Parameter: `from=2000-07-25T14:00:00Z&to=2025-02-26T10:00:00Z`

Response body:
```json
{
  "status": "success",
  "message": "successfully get history",
  "data": [
    {
      "id": 1,
      "vehicleId": 100,
      "latitude": -75.993178,
      "longitude": 162.017737,
      "speed": 44,
      "timestamp": "2023-12-18T22:03:43Z",
      "speedViolation": false
    },
    {
      "id": 2,
      "vehicleId": 100,
      "latitude": 50.993178,
      "longitude": 102.017737,
      "speed": 80,
      "timestamp": "2020-12-18T22:03:43Z",
      "speedViolation": false
    }
  ]
}
```

### Response body when vehicle has no data location
Status code: **200**

Request Parameter: `from=2000-07-25T14:00:00Z&to=2025-02-26T10:00:00Z`

Response body:
```json
{
  "status": "success",
  "message": "successfully get history",
  "data": null
}
```

### Response body when vehicle has data location but `to` is not specified
Return a location data from date `from` until now.

Status code: **200**

Request Parameter: `from=2000-07-25T14:00:00Z`

Response body:
```json
{
  "status": "success",
  "message": "successfully get history",
  "data": [
    {
      "id": 1,
      "vehicleId": 100,
      "latitude": -75.993178,
      "longitude": 162.017737,
      "speed": 44,
      "timestamp": "2023-12-18T22:03:43Z",
      "speedViolation": false
    }
  ]
}
```

### Response body when vehicle has data location but `from` is not specified
Return a location data 30-days ago until date `to`.

Status code: **200**

Request Parameter: `to=2025-02-26T10:00:00Z`

Response body:
```json
{
  "status": "success",
  "message": "successfully get history",
  "data": [
    {
      "id": 5,
      "vehicleId": 100,
      "latitude": -75.993178,
      "longitude": 162.017737,
      "speed": 44,
      "timestamp": "2025-01-10T10:00:00Z",
      "speedViolation": false
    }
  ]
}
```

### Response body when vehicle has data location without specified any request parameter 
Return all vehicle location data without date limit.

Status code: **200**

Request Parameter: **No request parameter**

Response body:
```json
{
  "status": "success",
  "message": "successfully get history",
  "data": [
    {
      "id": 1,
      "vehicleId": 100,
      "latitude": -75.993178,
      "longitude": 162.017737,
      "speed": 44,
      "timestamp": "2023-12-18T22:03:43Z",
      "speedViolation": false
    },
    {
      "id": 2,
      "vehicleId": 100,
      "latitude": 50.993178,
      "longitude": 102.017737,
      "speed": 80,
      "timestamp": "2020-12-18T22:03:43Z",
      "speedViolation": false
    }
  ]
}
```

### Response body when vehicle id is not found
Status code: **404**

Response body:
```json
{
  "status": "error",
  "message": "Vehicle not found",
  "data": null
}
```

### Response body when JWT token is not valid
Status code: **403**

Response body: **No response body**
</details>

## Features
1. saving GPS log
2. get vehicle last location
3. get history of vehicle location with specific time range
4. speed violation flag in GPS log
5. validate all input from request and return useful error message
6. automatic remove old GPS log
7. JWT authentication for all endpoint with **hardcoded credential**

## Setup
1. Install JDK
2. Install postgresql. See instruction from [postgresql](https://www.postgresql.org/docs/17/tutorial-install.html)
3. Set your environment with your specific configuration
```shell
set DB_URL=jdbc:postgresql://127.0.0.1:5432/gps_track
set DB_USERNAME=postgres
set DB_PASSWORD=postgres
```
4. Run your springboot
```shell
./gradlew bootRun
```
5. Test with Postman Collection
   - import file `Fleet-GPS-Track.postman_collection.json` into your Postman
   - set vehicle id to collection environment (preferred) or global environment.
   - run login endpoint to get JWT token (automatic set token to collection environment)


## Run with docker
1. Install JDK
2. Build jar file
```shell
./gradlew assemble
```
3. Install docker. See instruction from [docker](https://docs.docker.com/desktop/setup/install/windows-install/)
4. Install postgresql. See instruction from [postgresql](https://www.postgresql.org/docs/17/tutorial-install.html)
5. Setup database and table. Import database schema with file `schema.sql`
6. Build the image
```shell
docker build -t akhsaul/fleet-gps-track .
```
7. Run it with specific environment (connect to postgresql via localhost)
```shell
docker run -d -p 8080:8080 --name fleet-gps-app -e DB_URL="jdbc:postgresql://host.docker.internal:5432/gps_track" -e DB_USERNAME="postgres" -e DB_PASSWORD="postgres" akhsaul/fleet-gps-track
```
