# MySQL-DB-Dropwizard-Swagger-Spring

## Introduction
This project is based on the concept of Dropwizard based REST API to expose various functions to Register, Delete and Fetch a Person object into and from a MySQL database using Spring's hibernate delegator functions. This project can be used as a baseline template and can be extended to build many interesting applications that requires using RESTful services to interact with a backend database. This project uses Spring's optimized hibernate features for a fast query into the database and returning results in POJO (plain old java object) format. The application also take advantage of Swagger API that lets the client discover different requests that the application can process.

The Last two end points must be extended and should be accessible only to the authorized person. In this regards, you have to implement a filter which will intercept delete person and fetch person request and then extract value of Authorization header filed which is in following format:

> Basic BASE64(username|password)

Decode Base64 data into normal string and then extract username and password. Match this username and password with the values stored in the database. If it matches then execute the request otherwise send back an Unauthorized message to the client. 

## How to run

* Clone the repo. 
* After that import both projects into eclipse and then add spring.db project into dropwizard project by using BuildPath or if you are using IntelliJ the two projects can be merged if you right click on the active project and import the other project.
* Now carefully check the db properties and then then execute dropwizard AggregatorApplication. 
* After that open your browser and then specify following URL. 

> http://localhost:18080/swagger

It will open swagger page which has a get endpoint. Tryout get endpoint and your will see information of the persons in the Response Body area.
