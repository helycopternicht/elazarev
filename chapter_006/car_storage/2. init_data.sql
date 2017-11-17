-- engines
insert into engines(name) values ('v6');
insert into engines(name) values ('v8');
insert into engines(name) values ('v12');

-- bodys
insert into bodys(name) values ('sedan');
insert into bodys(name) values ('hatchback');
insert into bodys(name) values ('universal');
insert into bodys(name) values ('suv');

-- transmissions
insert into transmissions(name) values ('manual');
insert into transmissions(name) values ('automatic');
insert into transmissions(name) values ('variator');

-- cars
insert into car(name, engine, body, trans) values ('mazda 323', 1, 2, 1);
insert into car(name, engine, body, trans) values ('honda fit', 1, 2, 3);
insert into car(name, engine, body, trans) values ('toyota ipsum', 1, 3, 2);
insert into car(name, engine, body, trans) values ('lexus rx 300', 3, 4, 2);
