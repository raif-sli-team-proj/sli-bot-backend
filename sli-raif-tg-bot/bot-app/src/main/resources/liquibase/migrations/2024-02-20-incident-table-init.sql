--liquibase formatted sql

--changeset fedor:incident-init-migrations
--preconditions onFail:WARN
create table incident
(
    incident_id BIGSERIAL PRIMARY KEY,
    service_name VARCHAR(30),
    incident_status VARCHAR(15),
    incident_start_time TIMESTAMP,
    incident_end_time TIMESTAMP
);

create table commentary
(
    id BIGSERIAL PRIMARY KEY,
    user_id TEXT,
    contents TEXT,
    incident_id BIGINT REFERENCES incident(incident_id)
)
