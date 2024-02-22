--liquibase formatted sql

--changeset luka:subscription
--preconditions onFail:WARN
create table subscription
(
    id               SERIAL PRIMARY KEY,
    telegram_chat_id varchar(32) not null
)