# 📝 Todo API

A simple RESTful API for managing tasks (Todos), built with **Spring Boot**.  
This project allows you to create, list, update, and delete tasks through HTTP endpoints.

## Features

- Create new todos
- List all todos
- Update existing todos
- Delete todos by ID

## Tech Stack

- Java 17+
- Spring Boot
- Jakarta Validation
- Maven (or Gradle, depending on your setup)


## Installation

```bash
# Clone the repo
git clone https://github.com/Vediniz/JavaSpringBoot-ToDo_List
cd JavaSpringBoot-ToDo_List
```

## Database Configuration (MySQL)
### Create Database
    CREATE DATABASE todo_db;
### Configure application.properties
    spring.datasource.url=jdbc:mysql://localhost:3306/todo_db
    spring.datasource.username=your_dbuser
    spring.datasource.password=your_password

# How to use
## Create TODO
    POST /todos
    Content-Type: application/json

    body:
    {
    "title": "Study Spring Boot",
    "description": "Read the docs and build a demo app",
    "done": false
    }

## Update TODO

    PUT /todos
    Content-Type: application/json

    body:
    {
    "id": 1,
    "title": "Updated Title",
    "description": "Updated description",
    "done": true
    }

## Delete TODO 
    DELETE /todos/{id}

## Examples
# Create
    curl -X POST http://localhost:8080/todos \
    -H "Content-Type: application/json" \
    -d '{"title":"Learn Spring","description":"API creation","done":false}'

# List
    curl http://localhost:8080/todos

# Update
    curl -X PUT http://localhost:8080/todos \
    -H "Content-Type: application/json" \
    -d '{"id":1,"title":"Updated","description":"Now done","done":true}'

# Delete
    curl -X DELETE http://localhost:8080/todos/1
