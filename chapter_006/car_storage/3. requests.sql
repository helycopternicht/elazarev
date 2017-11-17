-- select all cars with its details names.
select
	c.name car_name,
    e.name eng_name,
    b.name body_name,
    t.name trans_name
from car c
    inner join engines e on c.engine = e.id
    inner join bodys b on c.engine = b.id
    inner join transmissions t on c.engine = t.id;
    
-- select unused details
select e.name from engines e left outer join car c on e.id = c.engine where engine is null
union all
select e.name from bodys e left outer join car c on e.id = c.body where body is null
union all
select e.name from transmissions e left outer join car c on e.id = c.trans where trans is null