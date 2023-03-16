DROP TABLE IF EXISTS iiit_user;

CREATE TABLE iiit_user (
    id integer not null,
    username varchar(40) not null,
    password varchar(20) not null,
    role varchar(20) not null,
    PRIMARY KEY(id )
   
);

