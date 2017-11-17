create database cars;

create table engines (
	id SERIAL PRIMARY KEY,
    name varchar(256)
);

create table bodys (
	id SERIAL PRIMARY KEY,
    name varchar(256)
);

create table transmissions (
	id SERIAL PRIMARY KEY,
    name varchar(256)
);

create table car (
	name varchar(256),
    engine integer REFERENCES engines(id),
    body integer REFERENCES bodys(id),
    trans integer REFERENCES transmissions(id),
    PRIMARY KEY (name, engine, body, trans)
);