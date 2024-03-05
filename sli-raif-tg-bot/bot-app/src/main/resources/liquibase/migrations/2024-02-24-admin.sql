--liquibase formatted sql

--changeset fedor:admin-init-migrations
--preconditions onFail:WARN
create table admin
(
    user_id VARCHAR(50) PRIMARY KEY
);

insert into admin(user_id)
values ('admin');
insert into admin(user_id)
values ('becausedafedya');
insert into admin(user_id)
values ('asminimulin');
insert into admin(user_id)
values ('lulu_fw01');