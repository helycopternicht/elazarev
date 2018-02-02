create table if not exists users (
	  id serial primary key,
    login varchar(255) unique not null,
    password varchar(255) not null,
    email varchar(255) not null,
    is_admin boolean not null
);

create table if not exists categorys (
	  id serial primary key,
    name varchar(255) not null
);

create table if not exists questions (
		id serial primary key,
    author_id integer references users(id),
    create_date timestamp default now(),
    title varchar(512) not null,
    description text not null
);

create table if not exists answers (
	  id serial primary key,
    question_id integer references questions(id),
    author_id integer references users(id),
    create_date timestamp default now(),
    text text not null,
    solution boolean
);

create table if not exists questions_categorys (
		question_id integer references questions(id),
    category_id integer references categorys(id)
);













