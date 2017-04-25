# Dcument Restful Server

## Overview

Manage document information in json format.
You must be logged in to get information.


## build and run 
- java install

- git clone ~~
- cd project  
- ./gradlew bootRun

## Environment

- main language : **Java 1.8**
- java framework : **Spring-boot, Spring-security**
- build system : **Gradle 3.2.1**
- ORM : **mybatis3**
- DB : **H2(default)**

## Usage

| url         | method | consumes                       | produces | description                              |
| ----------- | ------ | ------------------------------ | -------- | ---------------------------------------- |
| /user/login | POST   | JSON: id, password             | JSON     | login and return username, name, token   |
| /doc        | GET    | JSON: categoryid               | JSON     | return document list (login requiered)   |
| /doc/detail | GET    | JSON: doc_idx                  | JSON     | return document detail information (login requiered) |
| /doc        | POST   | JSON:  title, writer, category | JSON     | write document and return result         |
| /doc        | DELETE | JSON: doc_id                   | JSON     | delete document by document id  and return result |

## Setting File

*/src/main/resource/application.properties*

```

spring.datasource.initialize=true

```

**/src/main/resources/schema/schema_*.sql** is initialized
