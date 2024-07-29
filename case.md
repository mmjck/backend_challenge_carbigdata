# Case study for the api


## Success case

- Create client
- Optional (Login)
- Create occurrence
- List all occurrences
- Finish some occurrence


**Doing**
### Step 1 

Create client

Post `api/v1/register` with body

```json 
{
	"name": "Joao da Silva",
	"birth_date": "2000-01-01T10:00:00",
	"cpf": "48210135090",
	"password": "teste123"
}
```
Response
```json
{
	"token": "<token>"
}
```

Use token return in the headers of other requests

`Authorization: "Bearer <token>"``


### Step 2 
Create occorrence

Include same datao of user used in last request

Change type of body of this request to **Multipart**

Include this field with data:

body:
```json
{
	"zip_code": "85509-270",
	"steet": "Rua Senador Teotônio Vilela, 1212",
	"city": "Pato Branco",
	"state": "PR",
	"district": "Betania",
	"cpf": "48210135090",
	"full_name": "Joao da Silva",
}
```

include this field  with data:

- `files` with ramdon image 



Response

```json
{
	"id": 6,
	"status": "ACTIVE",
	"createdAt": "2024-07-28T17:49:34.058639",
	"addressId": 8,
	"clientId": 2
}
```


## Step 3

List all occurrences
`GET /api/v1/occurrence/list`

```json
[
	{
		"id": "1",
		"state": "PR",
		"createdAt": "2024-07-28 17:49:34.058639",
		"status": "ACTIVE",
		"cpf": "48210135090",
		"district": "Betania",
		"city": "Pato Branco",
		"fullName": "Joao da Silva",
		"zipCode": "85509-270",
		"clientId": 2,
		"images": [
			{
				"hash": "66fd82d227260f1d3d17",
				"path": "<path>"
			}
		]
	},
]
```



## Step 4
Finish occorrence with id
In this case, id=1
`POST /api/v1/occurence/finish/1` 


Response:

HTTP STATUS `200`