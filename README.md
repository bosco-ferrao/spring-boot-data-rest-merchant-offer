# CRUD App with Spring Boot Data with Unit Tests

It features full REST compliance and an embedded database.

### Requirements

- Maven
- JDK 8

### Testing

To test simply type

```sh
$ mvn test
```

from the root directory.

### Running

To build and start the server simply type

```sh
$ mvn spring-boot:run
```

from the root directory.

### Using

You can see what urls are available using curl:

```sh
$ curl localhost:8080
```

You can view existing people objects using a similar request:

```sh
$ curl localhost:8080/merchantGoods
```

and can create new ones using a POST:

```sh
$ curl -X POST -H "Content-Type:application/json" -d '{ "name" : "Tooth brush", "price" : "1.75" }' localhost:8080/merchantGoods
```
