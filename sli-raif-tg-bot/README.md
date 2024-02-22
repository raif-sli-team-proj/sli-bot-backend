для запуска только бд с помощью docker-compose
```bash
docker-compose -f docker-compose-local.yml up -d
```
и поправить:
+ application.properties
    ```
    spring.datasource.url=jdbc:postgresql://localhost:5433/BOT_DB
    ```
+ quartz.properties
    ```
    org.quartz.dataSource.myDS.URL=jdbc:postgresql://localhost:5433/BOT_DB
    ```
  
Для запуска на проде использовать
```bash
docker-compose -f docker-compose.yml up -d
```