/*****************************************************************************
-- D_DICT : 
*****************************************************************************/
drop table if exists D_DICT;
create table D_DICT(
	id varchar(64) not null,
	name varchar(64) ,
	pic1 varchar(255) ,
	pic2 varchar(255) ,
	primary key(id)
);
