# Создание магазина техники (Документирование с помощью Swagger)

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

## SWAGGER

http://localhost:8080/product/swagger-ui/index.html

## Описание задания

Предусловие:
•	Язык программирования: Java 8;
•	Frameworks: Spring Boot Starter, Spring Web, Spring JPA, Spring Hibernate;
•	База данных: Postgres SQL;
•	Библиотека для генерации документации: springdoc-openapi v1.5.9
Ход работ:  
1.	Необходимо реализовать реестр техники (телевизоры, пылесосы, холодильники, смартфоны и ПК) с привязкой к ним моделей с собственными характеристиками. Например, смартфоны Iphone имеют ряд общих атрибутов, равных для всей линейки, но каждая модель линейки имеет собственные характеристики.  
В роли сущностей должен выступать вид техники: Телевизор, пылесос, холодильник, смартфон и ПК. Атрибуты сущности: Наименование, Страна производитель, Фирма производитель, возможность заказа онлайн(да/нет), возможность оформления рассрочки(да/нет), модели в наличии (выборка из моделей, которые представлены в виде справочника).  
Сами модели должны иметь ряд атрибутов в зависимости от вида техники:   
•	Телевизоры: наименование, серийный номер, цвет,
размер, цена, категория, технология, наличие товара (Да/Нет);  
•	Пылесосы: наименование, серийный номер, цвет,
размер, цена, объём пылесборника, количество режимов, наличие товара (Да/Нет);  
•	Холодильники: наименование, серийный номер, цвет,
размер, цена, количество дверей, тип компрессора, наличие товара (Да/Нет);  
•	Смартфоны: наименование, серийный номер, цвет,
размер, цена, память, количество камер, наличие товара (Да/Нет);  
•	Компьютеры: наименование, серийный номер, цвет,
размер, цена, категория, тип процессора, наличие товара (Да/Нет);  
В реестр достаточно добавить по три позиции на каждый вид техники с двумя моделями для каждой.  
2.	По выделенным атрибутам необходимо реализовать поиск по наименованию,
вне зависимости от регистра, а также реализовать фильтрацию по виду техники, цвету, цене (от/до). Остальные фильтры сделать зависимыми от выбора вида техники и фильтровать по атрибутам моделей. 
3.	Реализовать сортировку реестра техники по алфавиту и по стоимости;
4.	Реализовать возможность добавлять новые позиции и модели к ним, в зависимости от выбранного вида техники.

