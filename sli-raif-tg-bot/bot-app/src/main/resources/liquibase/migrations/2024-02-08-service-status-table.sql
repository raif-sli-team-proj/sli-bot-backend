--liquibase formatted sql

--changeset luka:quartz-migrations
--preconditions onFail:WARN
create table service_status
(
    id            SERIAL PRIMARY KEY,
    service_name  varchar(10)  not null,
    endpoint_name varchar(128) not null,
    status        varchar(10)  not null,
    add_date      timestamp    not null
)