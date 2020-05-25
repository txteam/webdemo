update bd_district set `level` = -1;
update bd_district set `level` = 1 where parentId is null or parentId = '';

update bd_district t0,bd_district t1 set t0.`level` = 2 where 
t0.parentId = t1.id and t1.`level` = 1;
update bd_district t0,bd_district t1 set t0.`level` = 3 where 
t0.parentId = t1.id and t1.`level` = 2;
update bd_district t0,bd_district t1 set t0.`level` = 4 where 
t0.parentId = t1.id and t1.`level` = 3;
update bd_district t0,bd_district t1 set t0.`level` = 5 where 
t0.parentId = t1.id and t1.`level` = 4;
update bd_district t0,bd_district t1 set t0.`level` = 6 where 
t0.parentId = t1.id and t1.`level` = 5;

select * from bd_district t where t.`name` = '重庆市';
select * from bd_district t where t.parentId = '500000000000';

select * from bd_district t where t.provinceId = '500000000000' and t.`level` = 5;