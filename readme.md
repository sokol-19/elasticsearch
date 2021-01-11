## Spring Boot Data ElasticSearch
+ [Постановка задачи](readme.md#постановка-задачи)
+ [Краткое описание решения](readme.md#краткое-описание-решения)
+ [Сборка приложения](readme.md#сборка-приложения)

### Постановка задачи
Разработать REST-сервис на spring-boot. 
Должно быть реализовано два метода:
1. Импорт каталога по URL. Пример: http://frontend.tanuki.ru/feeds/raiden-delivery-club/

XML должен быть загружен в Elasticsearch через Spring Data

2. Получение списка продуктов (один метод) с возможностью полнотекстового поиска и
фильтрацией по ид категории

Сборка и запуск приложение должна быть через докер.

[к оглавлению](#readme)

### Краткое описание решения
#### Настройки
Настройки приложения находятся в файле application.properties.
    # хост с ElasticSearch    
    elasticsearch.host=elasticsearch
    # порт ElasticSearch
    elasticsearch.port=9200
    # url для загрузки каталога товаров
    catalog.url=http://frontend.tanuki.ru/feeds/raiden-delivery-club/

#### Основная функциональность
+ Получение каталога товаров - http://localhost:8081/catalog/get
+ Импорт каталога в ElasticSearch - http://localhost:8081/catalog/import
+ Поиска продуктов http://localhost:8081/product?name=<искомое>
  где <**искомое**> - текст, который нужно найти
+ Поиска продуктов с фильтрацией по идентификатору категории товара http://localhost:8081/product?name=<искомое>&category=<идентификатор>
где <**искомое**> - текст, который ищем, а <**идентификатор**> - идентификатор категории товара
+ Поиск по категории товаров - http://localhost:8081/category?name=<искомое>
где <**искомое**> - текст, который ищем в категории товаров

[к оглавлению](#readme)

### Сборка приложения
Варианты сборок приложения:
+ Обычная сборка приложения

    mvn clean package
    
+ Сборка приложения с оправкой его в контейнер

    mvn clean package
    
    // запуск из каталога с исходниками
    
    docker build --tag vsokol17/appes:1.0 -f .\Dockerfile.ex .
    
    // запуск контейнера с приложением
    
    docker run -d --name app-es --network=docker-net -p 8081:8080 vsokol17/appes:1.0

где docker-net - сеть типа bridge

+ Сборка приложения внутри контейнера

    // запуск из каталога с исходниками
    
    docker build --tag vsokol17/appes:1.0 -f .\Dockerfile.in .
    
    // запуск контейнера с приложением
    
    docker run -d --name app-es --network=docker-net -p 8081:8080 vsokol17/appes:1.0

где docker-net - сеть типа bridge

Для контейнерных сборок использовался образ maven:3.6.1-jdk-8-alpine 

[к оглавлению](#readme)
