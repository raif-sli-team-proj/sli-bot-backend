--liquibase formatted sql

--changeset fedor:admin-init-migrations
--preconditions onFail:WARN
create table admin
(
    user_id VARCHAR(50) PRIMARY KEY
);