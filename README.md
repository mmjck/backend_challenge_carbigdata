<h2 align="center">
Backend Challenge Carbigdata
</h2>



# :rocket: Technologies used

* [Docker](https://docs.docker.com/engine/install/)
* [PostgreSql](https://www.postgresql.org/)
* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) 
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Maven](https://maven.apache.org/)
* [JUnit](https://junit.org/junit5/)
* [MinIo](https://min.io/)
* [Flyway](https://flywaydb.org/)



# How to run

- Clone the repository
```
git clone https://github.com/mmjck/backend_challenge_carbigdata.git
```


- Build **Application** image on Docker

```
docker-compose build -d
```

Application will be running  `http://localhost:8080/` ✅   
 

# Endpoints 

The REST API app is described below.


## Auth module

### 1. Create new Client
`POST /api/v1/auth/register`

```json
{
	"name": "Joao das silva",
	"birth_date": "2000-01-01T10:00:00",
	"cpf": "00000000000",
	"password": "teste123"
}
```

### 2. Login 

`POST /api/v1/auth/login`

```json
{
	"cpf": "00000000000",
	"password": "teste123"
}
```
----
:warning:
Both cases return a json with token generated. Ex: 
```json
{
	"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJtcy1zZXJ2aWNlIiwic3ViIjoiMDI2ODMxMDkyMDIiLCJleHAiOjE3MjIyMDMxODZ9.oDNyay6YTZokNIavC5l_O0DjjElUQ14fU7pUMWoqb5s"
}
```

----



## Clients

### 1. Update client
`PUT /api/v1/client/{id}`

```json
{
	"name": "Joao das silva",
	"birth_date": "2000-01-01T10:00:00",
	"cpf": "00000000000",
	
}
```

### 2. Get client by id
`GET /api/v1/client/{id}`

Example os response 
```json
{
	"id": 1,
	"fullName": "Maria das silva",
	"cpf": "00000000000",
	"birthDate": "2000-01-01T10:00:00",
	"createdAt": "2024-07-28T14:46:26.450442"
}
```

### 3. Delete client
`DELETE /api/v1/client/{id}`


## Address Module

### 1. Create address
`POST /api/v1/address`

```json
{
	"zip_code": "123123",
	"steet": "street",
	"city": "city",
	"state": "State",
	"district": "district"
}

```

### 2. Update address

`PUT /api/v1/address`

body: 

```
{
	"zip_code": "123123",
	"steet": "street",
	"city": "city",
	"state": "State",
	"district": "district"
}

```

### 3. Get adddress by id

`GET /api/v1/address/{id}`


Example of response:
```json
{
	"id": 2,
	"state": "state",
	"city": "city",
	"zipCode": "zipCode",
	"district": "district",
	"createdAt": "2024-07-28T13:41:34.976314"
}

```

### 4. Delete address
`DELETE /api/v1/address/{id}`




## Occurrence images Module

### 1. Create occurrence image
`POST /api/v1/occurrence-images`

This is `multipart/form-data` request 
You need includ some fields:
occurrence_id: {occurrenceId}
files: array of images



### 2. Update occurrence image 
- Only can update images

`POST /api/v1/occurrence-images/{id}`

This is `multipart/form-data` request 
You need includ some fields:
occurrence_id: {occurrenceId}
files: array of images



### 3. Get by occurrence image id
`GET /api/v1/occurrence-images/{id}`


Example of response

```json
{
	"id": 4,
	"occurrenceId": 4,
	"hash": "<path>",
	"path": "<url>",
	"createdAt": "2024-07-28T15:35:19.153032"
}
```


### 4. Delete
`DELETE /api/v1/occurrence-images/{id}`

## Occurrences Module
### 1. Create Occurrence
`POST api/v1/occurrence`


This is `multipart/form-data` request 
You need includ some fields:
body: 
```json
{
	"zip_code": "<zipcode>",
	"steet": "<steet>",
	"city": "<city>",
	"state": "<state>",
	"district": "<district>",
	"cpf": "<cpf>",
	"full_name": "<full_name>"
}
```
files: array of images

### 2. Get All
`GET /api/v1/occurrence/list`

```json
[
	{
		"id": "4",
		"state": "<state>",
		"createdAt": "2024-07-28 15:35:19.134404",
		"status": "ACTIVE",
		"cpf": "<cpf>",
		"district": "<district>",
		"city": "<city>",
		"fullName": "<fullname>",
		"zipCode": "<zicode>",
		"clientId": 1,
		"images": [
			{
				"hash": "<hash>",
				"path": "<hash>"
			},
			
		]
	}
]
```


### 3. Finish occurrence
`GET /api/v1/occurrence/finish/{id}`







# Missing requirements

- Ordering the list of occurrences by creation date  
    - ASC
    - DESC   


- Ordering the list of occurrences by city name
    - ASC
    - DESC

- Update field occurrence_id by endpoint api/v1/occurrence-images


- Update related fields from  api/v1/occurrence



