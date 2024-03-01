Config Vault API documentation
======================
## Framework Tools
#### Spring Boot 3
#### Java Jdk 17
#### Mysql 5.7

### MySql Configuration
Inside application.properties file you can find the following database configuration setup.
```
spring.datasource.url=jdbc:mysql://${DB_HOST:localhost}:${DB_PORT:3306}/${DB_NAME:config_vault}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:root}
```
Here, change each db variable config as needed. For e.g DB_USERNAME:root denotes that root is the user name value for db connection. 

APIs
=====================
## Users
### Create user
```
  POST http://localhost:8080/users
  Header: Content-Type application/json
  Body:
  {
    "name": "Kumar Chapagain",
    "userId": "kchapagain"
  }
```
```
Response:
Status: 201 (Created)
Body:
{
    "id": 1,
    "userId": "kchapagain",
    "name": "Kumar Chapagain"
}
```
### Create user Failed Request(Try creating user with existing userId)
```
  POST http://localhost:8080/users
  Header: Content-Type application/json
  Body:
  {
    "name": "Kumar Chapagain",
    "userId": "kchapagain"
  }
```
```
Response:
Status: 400 (Bad Request)
Body:
{
    "message": "User already exists",
    "status": false
}
```
### Get users
```
  GET http://localhost:8080/users
 
```
```
Response:
Status: 200 (Success)
Body:
[
    {
        "id": 1,
        "userId": "kchapagain",
        "name": "Kumar Chapagain"
    },
    {
        "id": 2,
        "userId": "allex",
        "name": "Alex"
    },
    {
        "id": 3,
        "userId": "tomiie",
        "name": "Tom"
    }
]
```
### Get user by ID
```
  GET http://localhost:8080/users/1
 
```
```
Response:
Status: 200 (Success)
Body:
{
    "id": 1,
    "userId": "kchapagain",
    "name": "Kumar Chapagain"
}
```
### Update user
```
  PUT http://localhost:8080/users/1
  Header: Content-Type application/json
  Body:
  {
    "name": "Kumar",
    "userId": "kchapagain"
  }
```
```
Response:
Status: 200 (Success)
Body:
{
    "id": 1,
    "userId": "kchapagain",
    "name": "Kumar"
}
```
## Environments
### Create environment
```
  POST http://localhost:8080/environments
  Header: Content-Type application/json
  Body:
  {
    "name":"Dev",
    "ip": "12.22.333"
}
```
```
Response:
Status: 201 (Created)
Body:
{
    "id": 4,
    "name": "Dev",
    "ip": "12.22.333"
}
```
### Create environment Failed Request(Try creating environment with existing name)
```
  POST http://localhost:8080/users
  Header: Content-Type application/json
  Body:
  {
    "name":"Dev",
    "ip": "12.22.333"
}
```
```
Response:
Status: 400 (Bad Request)
Body:
{
    "message": "Environment already exists with environment name: Dev",
    "status": false
}
```
### Get environments
```
  GET http://localhost:8080/environments
 
```
```
Response:
Status: 200 (Success)
Body:
[
    {
        "id": 4,
        "name": "Dev",
        "ip": "12.22.333"
    },
    {
        "id": 5,
        "name": "Qa",
        "ip": "12.22.333"
    },
    {
        "id": 6,
        "name": "Prod",
        "ip": "12.22.333"
    }
]
```
### Get environment by ID
```
  GET http://localhost:8080/environments/4
 
```
```
Response:
Status: 200 (Success)
Body:
{
    "id": 4,
    "name": "Dev",
    "ip": "12.22.333"
}
```
### Update environment
```
  PUT http://localhost:8080/environments/4
  Header: Content-Type application/json
  Body:
  {
    "name":"Develop",
    "ip": "12.22.333"
}
```
```
Response:
Status: 200 (Success)
Body:
{
    "id": 4,
    "name": "Develop",
    "ip": "12.22.333"
}
```
### Delete environment
```
  DELETE http://localhost:8080/environments/4
  
```
```
Response:
Status: 200 (Success)
Body:
{
    "message": "Environment deleted successfully",
    "status": true
}
```
## Keys
### Create key
```
  POST http://localhost:8080/keys
  Header: Content-Type application/json
  Body:
  {
    "name": "key.test",
    "value": "key.value",
    "creator": "key.creator",
    "description": "description",
    "jira": "https://spring.io/",
    "user": "Tom",
    "environment": "Qa"
}
```
```
Response:
Status: 201 (Created)
Body:
{
    "id": 8,
    "description": "description",
    "jira": "https://spring.io/",
    "user": "Tom",
    "environment": "Qa",
    "dateCreated": "2024-02-29T17:57:48.146+00:00",
    "lastUpdated": "2024-02-29T17:57:48.146+00:00",
    "name": "key.test",
    "value": "key.value",
    "creator": "key.creator"
}
```
### Create key Failed Request(Try creating key without name and value)
```
  POST http://localhost:8080/keys
  Header: Content-Type application/json
  Body:
  {
    "creator": "key.creator",
    "description": "description",
    "jira": "https://spring.io/",
    "user": "Tom",
    "environment": "Qa"
}
```
```
Response:
Status: 400 (Bad Request)
Body:
{
    "name": "must not be blank",
    "value": "must not be blank"
}
```
### Get keys
```
  GET http://localhost:8080/keys
 
```
```
Response:
Status: 200 (Success)
Body:
[
    {
        "id": 5,
        "description": "description",
        "jira": "https://spring.io/",
        "user": "Kumar",
        "environment": null,
        "dateCreated": "2024-02-29T09:16:18.132+00:00",
        "lastUpdated": "2024-02-29T09:16:18.132+00:00",
        "name": "key",
        "value": "value",
        "creator": "creator"
    },
    {
        "id": 8,
        "description": "description",
        "jira": "https://spring.io/",
        "user": "Tom",
        "environment": "Qa",
        "dateCreated": "2024-02-29T17:57:48.146+00:00",
        "lastUpdated": "2024-02-29T17:57:48.146+00:00",
        "name": "key.test",
        "value": "key.value",
        "creator": "key.creator"
    }
]
```
### Get key by ID
```
  GET http://localhost:8080/keys/8
 
```
```
Response:
Status: 200 (Success)
Body:
{
    "id": 8,
    "description": "description",
    "jira": "https://spring.io/",
    "user": "Tom",
    "environment": "Qa",
    "dateCreated": "2024-02-29T17:57:48.146+00:00",
    "lastUpdated": "2024-02-29T17:57:48.146+00:00",
    "name": "key.test",
    "value": "key.value",
    "creator": "key.creator"
}
```
### Get keys by environment
```
  GET http://localhost:8080/keys/environment/Qa // Qa = env name
 
```
```
Response:
Status: 200 (Success)
Body:
[
    {
        "id": 2,
        "description": "description",
        "jira": "https://spring.io/",
        "user": "Tom",
        "environment": "Qa",
        "dateCreated": "2024-02-29T07:47:20.653+00:00",
        "lastUpdated": "2024-02-29T18:03:22.589+00:00",
        "name": "key.test",
        "value": "key.value",
        "creator": "key.creator"
    },
    {
        "id": 8,
        "description": "description",
        "jira": "https://spring.io/",
        "user": "Tom",
        "environment": "Qa",
        "dateCreated": "2024-02-29T17:57:48.146+00:00",
        "lastUpdated": "2024-02-29T17:57:48.146+00:00",
        "name": "key.test",
        "value": "key.value",
        "creator": "key.creator"
    }
]
```
### Get keys by key name
```
  GET http://localhost:8080/keys/key/key1 // key1 = key name
 
```
```
Response:
Status: 200 (Success)
Body:
[
    {
        "id": 1,
        "description": "description",
        "jira": "https://spring.io/",
        "user": "Tom",
        "environment": "Qa",
        "dateCreated": "2024-02-29T07:45:25.260+00:00",
        "lastUpdated": "2024-02-29T18:13:43.659+00:00",
        "name": "key1",
        "value": "value2",
        "creator": "key.creator"
    },
    {
        "id": 2,
        "description": "description",
        "jira": "https://spring.io/",
        "user": "Tom",
        "environment": "Qa",
        "dateCreated": "2024-02-29T07:47:20.653+00:00",
        "lastUpdated": "2024-02-29T18:13:13.250+00:00",
        "name": "key1",
        "value": "value1",
        "creator": "key.creator"
    }
]
```
### Update key
```
  PUT http://localhost:8080/keys/2
  Header: Content-Type application/json
  Body:
  {
    "name": "key.test",
    "value": "key.value",
    "creator": "key.creator",
    "description": "description",
    "jira": "https://spring.io/",
    "user": "Tom",
    "environment": "Qa"
}
```
```
Response:
Status: 200 (Success)
Body:
{
    "id": 2,
    "description": "description",
    "jira": "https://spring.io/",
    "user": "Tom",
    "environment": "Qa",
    "dateCreated": "2024-02-29T07:47:20.653+00:00",
    "lastUpdated": "2024-02-29T18:03:22.589+00:00",
    "name": "key.test",
    "value": "key.value",
    "creator": "key.creator"
}
```
### Delete key
```
  DELETE http://localhost:8080/keys/1
  
```
```
Response:
Status: 200 (Success)
Body:
{
    "message": "Key deleted successfully",
    "status": true
}
```
## Properties
### Create property
```
  POST http://localhost:8080/properties
  Header: Content-Type application/json
  Body:
  {
    "name": "property1",
    "value": "property_value1",
    "creator": "creator",
    "description": "description",
    "jira": "https://spring.io/",
    "user": "Tom",
    "environment": "Qa"
}
```
```
Response:
Status: 201 (Created)
Body:
{
    "id": 1,
    "description": "description",
    "jira": "https://spring.io/",
    "user": "Tom",
    "environment": "Qa",
    "dateCreated": "2024-02-29T18:18:50.792+00:00",
    "lastUpdated": "2024-02-29T18:18:50.792+00:00",
    "name": "property1",
    "value": "property_value1",
    "creator": "creator"
}
```
### Get properties
```
  GET http://localhost:8080/properties
 
```
```
Response:
Status: 200 (Success)
Body:
[
    {
        "id": 1,
        "description": "description",
        "jira": "https://spring.io/",
        "user": "Tom",
        "environment": "Qa",
        "dateCreated": "2024-02-29T18:18:50.792+00:00",
        "lastUpdated": "2024-02-29T18:21:51.364+00:00",
        "name": "property_name1",
        "value": "property_value1",
        "creator": "creator"
    },
    {
        "id": 2,
        "description": "description",
        "jira": "https://spring.io/",
        "user": "Tom",
        "environment": "Qa",
        "dateCreated": "2024-02-29T18:23:07.249+00:00",
        "lastUpdated": "2024-02-29T18:23:07.249+00:00",
        "name": "property2",
        "value": "property_value2",
        "creator": "creator"
    }
    ]
```
### Get property by ID
```
  GET http://localhost:8080/properties/1
 
```
```
Response:
Status: 200 (Success)
Body:
{
    "id": 1,
    "description": "description",
    "jira": "https://spring.io/",
    "user": "Tom",
    "environment": "Qa",
    "dateCreated": "2024-02-29T18:18:50.792+00:00",
    "lastUpdated": "2024-02-29T18:21:51.364+00:00",
    "name": "property_name1",
    "value": "property_value1",
    "creator": "creator"
}
```
### Get properties by environment
```
  GET http://localhost:8080/properties/environment/Qa // Qa = env name
 
```
```
Response:
Status: 200 (Success)
Body:
[
    {
        "id": 1,
        "description": "description",
        "jira": "https://spring.io/",
        "user": "Tom",
        "environment": "Qa",
        "dateCreated": "2024-02-29T18:18:50.792+00:00",
        "lastUpdated": "2024-02-29T18:21:51.364+00:00",
        "name": "property_name1",
        "value": "property_value1",
        "creator": "creator"
    },
    {
        "id": 2,
        "description": "description",
        "jira": "https://spring.io/",
        "user": "Tom",
        "environment": "Qa",
        "dateCreated": "2024-02-29T18:23:07.249+00:00",
        "lastUpdated": "2024-02-29T18:23:07.249+00:00",
        "name": "property2",
        "value": "property_value2",
        "creator": "creator"
    }
]
```
### Get properties by property name
```
  GET http://localhost:8080/properties/property/property2 // property2 = property name
 
```
```
Response:
Status: 200 (Success)
Body:
[
    {
        "id": 2,
        "description": "description",
        "jira": "https://spring.io/",
        "user": "Tom",
        "environment": "Qa",
        "dateCreated": "2024-02-29T18:23:07.249+00:00",
        "lastUpdated": "2024-02-29T18:23:07.249+00:00",
        "name": "property2",
        "value": "property_value2",
        "creator": "creator"
    },
    {
        "id": 3,
        "description": "description",
        "jira": "https://spring.io/",
        "user": "Tom",
        "environment": "Qa",
        "dateCreated": "2024-02-29T18:23:14.996+00:00",
        "lastUpdated": "2024-02-29T18:23:14.996+00:00",
        "name": "property2",
        "value": "property_value3",
        "creator": "creator"
    }
]
```
### Update property
```
  PUT http://localhost:8080/properties/1
  Header: Content-Type application/json
  Body:
  {
    "name": "property_name1",
    "value": "property_value1",
    "creator": "creator",
    "description": "description",
    "jira": "https://spring.io/",
    "user": "Tom",
    "environment": "Qa"
}
```
```
Response:
Status: 200 (Success)
Body:
{
    "id": 1,
    "description": "description",
    "jira": "https://spring.io/",
    "user": "Tom",
    "environment": "Qa",
    "dateCreated": "2024-02-29T18:18:50.792+00:00",
    "lastUpdated": "2024-02-29T18:21:51.364+00:00",
    "name": "property_name1",
    "value": "property_value1",
    "creator": "creator"
}
```
### Delete property
```
  DELETE http://localhost:8080/properties/3
  
```
```
Response:
Status: 200 (Success)
Body:
{
    "message": "Property deleted successfully",
    "status": true
}
```