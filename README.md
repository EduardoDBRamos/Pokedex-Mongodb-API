# **Pokedex API**

![Pokedex](pokedex.png)

This API was created for the purpose of studies of programming. 

A pokedex its a device used from peoples in popular japanese animation, Pokemon. This device brings informations about creatures called pokemons from this same universe.

To recreate this techology, the following tools were used: Gradle, Docker, Spring, Swagger and MongoDB.

## How to use

To use this application you gonna need:
* Java 11
* Gradle 7.1.1+
* Docker and Docker compose
* The following ports must be free 8080, 8081 and 27017

## How to start application

With the tools installeds use the command:
>docker-compose up --build -d

After this, the application is running in the port 8080, the mongodb is running in the port 27017 and a mongodb express in the port 8081.

You can access the Swagger in <http://localhost:8080/swagger-ui.html>

I hope this API gonna help you on your studies. Have fun.