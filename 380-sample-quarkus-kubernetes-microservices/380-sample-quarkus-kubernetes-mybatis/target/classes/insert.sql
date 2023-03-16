CREATE TABLE USERS (
    id integer not null primary key,
    name varchar(80) not null
);

DELETE FROM users;
insert into users (id, name) values(1, 'Test User1');
insert into users (id, name) values(2, 'Test User2');
insert into users (id, name) values(3, 'Test User3');


CREATE TABLE iiit_projects (
    id integer not null primary key,
    name varchar(80) not null
);
DELETE FROM iiit_projects;
insert into  iiit_projects(id, name) values (1, '项目A');
insert into  iiit_projects(id, name) values (2, '项目B');
insert into  iiit_projects(id, name) values (3, '项目C');
insert into  iiit_projects(id, name) values (4, '项目D');
insert into  iiit_projects(id, name) values (5, '项目E');

