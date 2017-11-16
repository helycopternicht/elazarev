CREATE TABLE roles (
	  id SERIAL PRIMARY KEY,
    name varchar(255) NOT NULL
);

CREATE TABLE roles_rights (
	  id SERIAL PRIMARY KEY,
	  name varchar(255) NOT NULL,
    description text,
    role_id integer REFERENCES roles (id) 
);

CREATE TABLE users (
	  id SERIAL PRIMARY KEY,
    login varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    role_id integer REFERENCES roles (id),
    create_date timestamp DEFAULT now()
);

CREATE TABLE request_categorys (
	  id SERIAL PRIMARY KEY,
    name varchar(255)
);

CREATE TYPE request_status AS ENUM ('created', 'in_progress', 'done');

CREATE TABLE requests (
	  id SERIAL PRIMARY KEY,
    title varchar(2000) NOT NULL,
    description text,
    date timestamp NOT NULL,
    author_id integer REFERENCES users (id),
    category_id integer REFERENCES request_categorys (id),
    status request_status
);

CREATE TABLE comments  (
	  id SERIAL PRIMARY KEY,
    author_id integer REFERENCES users (id),
    comment text,
    create_date timestamp
);

CREATE TABLE files (
	  id SERIAL PRIMARY KEY,
    url varchar(1000)
);

CREATE TABLE requests_files (
	  request_id integer REFERENCES requests (id),
    file_id integer REFERENCES files (id)
);

