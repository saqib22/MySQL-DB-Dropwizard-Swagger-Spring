# MySQL-DB-Dropwizard-Swagger-Spring

## Introduction
This project is based on the concept of dropwizard based REST API to expose various functions to Register Person, Delete Person, and Fetch Person into and from a MySQL database using Spring's hibernate delegator functions.

The Last two end points must be extended and should be accessible only to the authorized person. In this regards, you have to implement a filter which will intercept delete person and fetch person request and then extract value of Authorization header filed which is in following format:

> Basic BASE64(username|password)

Decode Base64 data into normal string and then extract username and password. Match this username and password with the values stored in the database. If it matches then execute the request otherwise send back an Unauthorized message to the client. 
