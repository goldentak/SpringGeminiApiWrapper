# SpringGeminiApiWrapper
This project is a Spring Boot application following an MVC structure. App.java serves as the main entry point. SecurityConfig.java manages authentication, using Spring Security. MainController.java and AiController.java handle web requests, while UserService.java contains business logic for users. UserRepository.java is an in-memory store using HashMap. JwtUtil.java handles JWT authentication.
___
[![Maven Central](https://img.shields.io/maven-central/v/com.example/app?color=orange)](https://central.sonatype.com/artifact/com.example/app)
[![Java Version](https://img.shields.io/badge/Java-17%2B-blueviolet)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-green)](https://spring.io/projects/spring-boot)
[![GitHub Pull Requests](https://img.shields.io/github/issues-pr/com-example/app?color=blueviolet)](https://github.com/com-example/app/pulls)
[![License](https://img.shields.io/github/license/com-example/app?color=blueviolet)](https://github.com/com-example/app/blob/main/LICENSE)
[![Forks](https://img.shields.io/github/forks/goldentak/SpringGeminiApiWrapper?style=social)](https://github.com/goldentak/SpringGeminiApiWrapper/network/members)


---
### Stack
+ Java 17
+ Spring framework 3.2.2 
+ Spring security

### Utilities
+ JSONWebToken (JWT)
+ lombok
+ Gemini API

---
### Info & Configuration
API documentation: https://ai.google.dev/gemini-api/docs?hl=ru



```sh
mvn clean install && mvn spring-boot:run
```

tree: 
```angular2html
.
├── README.md
├── pom.xml
├── src
│   └── main
│       ├── java
│       │   └── com
│       │       └── example
│       │           └── app
│       │               ├── App.java
│       │               ├── config
│       │               │   └── SecurityConfig.java
│       │               ├── controllers
│       │               │   ├── AiController.java
│       │               │   └── MainController.java
│       │               ├── dto
│       │               │   └── UserDto.java
│       │               ├── models
│       │               │   ├── Message.java
│       │               │   └── User.java
│       │               ├── repository
│       │               │   └── UserRepository.java
│       │               └── services
│       │                   ├── GeminiService.java
│       │                   ├── JwtUtil.java
│       │                   └── UserService.java
│       └── resources
│           └── application.properties
└── target
    ├── classes
    │   ├── application.properties
    │   └── com
    │       └── example
    │           └── app
    │               ├── App.class
    │               ├── config
    │               │   └── SecurityConfig.class
    │               ├── controllers
    │               │   ├── AiController.class
    │               │   └── MainController.class
    │               ├── dto
    │               │   └── UserDto.class
    │               ├── models
    │               │   ├── Message.class
    │               │   └── User.class
    │               ├── repository
    │               │   └── UserRepository.class
    │               └── services
    │                   ├── GeminiService.class
    │                   ├── JwtUtil.class
    │                   └── UserService.class
    ├── fromzero-1.0-SNAPSHOT.jar
    ├── generated-sources
    │   └── annotations
    ├── maven-archiver
    │   └── pom.properties
    └── maven-status
        └── maven-compiler-plugin
            └── compile
                └── default-compile
                    ├── createdFiles.lst
                    └── inputFiles.lst

```


## API Endpoints

### Register
<span style="color: #ffb86c;">**POST** `/auth/register`</span>
```json
{
  "username": "user123",
  "password": "securePassword"
}
```

### Login
<span style="color: #ffb86c;">**POST** `/auth/login`</span>
```json
{
  "username": "user123",
  "password": "securePassword"
}
```
### Profile
<span style="color: #ff79c6;">**GET** `/auth/profile`</span>
```text
Key: Authorization Value: Bearer <your JWT token>
```
### Message
<span style="color: #ffb86c;">**POST** `/api/message`</span>
```text
your query
```
### Messages
<span style="color: #ff79c6;">**GET** `/api/messages`</span>
```json

```