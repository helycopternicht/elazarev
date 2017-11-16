insert into roles (name) VALUES ('admin');
insert into roles (name) VALUES ('user');

START TRANSACTION;
SET CONSTRAINTS ALL DEFERRED;
insert into users (login, password, role_id, create_date) 
VALUES ('administrator', 'qwerty123', 1, '2017-11-15 16:45:06');
COMMIT;

START TRANSACTION;
SET CONSTRAINTS ALL DEFERRED;
insert into users (login, password, role_id, create_date)
VALUES ('user', '123', 2, '2017-11-15 16:46:07');
COMMIT;

START TRANSACTION;
SET CONSTRAINTS ALL DEFERRED;
insert into users (login, password, role_id, create_date)
VALUES ('user1', '1234', 2, '2017-11-15 16:46:07');
COMMIT;


insert into request_categorys (name) values ('bugs');
insert into request_categorys (name) values ('features');


insert into files (url) values ('1.doc');

START TRANSACTION;
SET CONSTRAINTS ALL DEFERRED;
insert into requests (title, description, date, author_id, category_id, status) 
values ('Error on page contacts.html', 'Dont work page /contacts.html', '2017-11-15 16:59:07', 2, 1, 'created');
COMMIT;

START TRANSACTION;
SET CONSTRAINTS ALL DEFERRED;
insert into requests (title, description, date, author_id, category_id, status) 
values ('Error on page main.html', 'Dont work page /main.html', '2017-11-15 16:59:07', 3, 1, 'done');
COMMIT;

START TRANSACTION;
SET CONSTRAINTS ALL DEFERRED;
insert into requests (title, description, date, author_id, category_id, status) 
values ('Create chat on main page', 'We need to create chat on main page', '2017-11-15 16:59:08', 3, 2, 'created');
COMMIT;

START TRANSACTION;
SET CONSTRAINTS ALL DEFERRED;
insert into requests_files (request_id, file_id) values (3, 1);
COMMIT;

START TRANSACTION;
SET CONSTRAINTS ALL DEFERRED;
insert into comments (author_id, comment, create_date) values (1, 'ok, will see', '2017-11-15 16:59:08');
COMMIT;

START TRANSACTION;
SET CONSTRAINTS ALL DEFERRED;
insert into comments (author_id, comment, create_date) values (1, 'will check', '2017-11-15 16:59:08');
COMMIT;

-- select * from comments;
