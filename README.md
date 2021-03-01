# Newys

This is a Spring Boot REST api. The database used in this project is MySql (version 8). 
The database and tomcat server are ran from two separate docker containers.

How to run:</br>
From project root directory run command: docker-compose -f docker-compose.yml up

Application is bootstrapped with 4 initial accounts for demo purposes: <br/>
Each account has corresponding roles AUTHOR, READER, and ADMIN.
Certain endpoints require a specific role or otherwise response is 403 Forbidden

1) username: admin </br> password: admin  
  
2) username: author </br> password: author 
   
3) username: reader </br> password: reader
   
4) username: author_reader </br> password: author.reader


# Login
The jwt bearer token will be generated and sent to you in a header authorization field.  

The generated token must be used in authorization field when sending requests to access the specific endpoint.

POST http://localhost:8090/login </br>
Request body example (JSON): 
    {
    "username":"author",
    "password":"author"
    } 



# Endpoints
### Create article
POST http://localhost:8090/api/article/ 

Body: {
"header": "article header",
"content": "some content",
"username": "author"
} 

### Get article by ID
GET http://localhost:8090/api/article/{id}

### Get all articles
GET http://localhost:8090/api/article/all

#### Delete article
DELETE http://localhost:8090/api/article/{id}

### Update article
POST http://localhost:8090/api/article/{id}
body {
    "header": "updated article",
    "content": "updated content"
}

### Like article
POST http://localhost:8090/api/likes/{articleId}

### Unlike article
POST http://localhost:8090/api/likes/{articleId}

### Get all users that liked specific article
GET http://localhost:8090/api/likes/{articleId}

### Create new group for articles
POST http://localhost:8090/api/group/{groupName}

### Add an article to group
POST http://localhost:8090/api/group/{groupId}/{articleId}

## Admin Endpoints 

### Add new user
POST http://localhost:8090/api/admin/user/ 

Body {
"username":"new",
"password":"new",
"role": "ROLE"
}

### Delete user
DELETE http://localhost:8090/api/admin/user/{username}







