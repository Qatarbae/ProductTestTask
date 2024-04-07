# Тестовое задание

## Создание network

```shell
docker network create product-network
```

## PostgreSQL

Запуск в Docker:

```shell
docker run --name product-db -p 5432:5432 -e POSTGRES_USER=spring -e POSTGRES_PASSWORD=password -e POSTGRES_DB=product --network=product-network postgres:15
```

## Spring

build проекта

```shell
docker build  -t sandbox-spring:24.4.7 .
```

запуск проекта

```shell
docker run --name spring-app -p 8080:8080 --network=product-network sandbox-spring:24.4.7
```


