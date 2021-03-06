# API representing an online betting roulette

_This project was made using spring boot and redis_

## Begining🚀

_You must install redis on your machine to test the API._


## New roulette creation endpoint that returns the id of the newly created roulette ⚙️


```
POST  http://localhost:8080/api/roulette
```

### Roulette opening endpoint (the input is a roulette id) ⌨️
_that allows the
subsequent wagering requests, it should simply return a status that
confirm that the operation was successful or denied_ 


```
PUT http://localhost:8080/api/roulette/open/{id}
```

## Endpoint bet on a number (valid numbers to bet are from 0 to 36)
or color (black or red) of the roulette a certain amount of money (maximum
$ 10,000) to an open roulette wheel. 📦


```
POST http://localhost:8080/api/bet

BODY {
  "betNum": "5",
  "betColor": "rojo",
  "betAmount": "1050",
  "rouletteId": "917be453-1051-4dcb-94cc-f13088b600db"
}
```

## Endpoint of closing bets given a roulette id, this endpoint should return the
result of bets made from opening to closing

```
GET http://localhost:8080/api/bet/close/{RouletteId}

```



## Roulette list endpoint created with their states (open or closed)📦


```
GET http://localhost:8080/api/roulette/status

```

## Autor ✒️

* **Jhon Arias** - *Developer* - [7honarias](https://github.com/7honarias)
