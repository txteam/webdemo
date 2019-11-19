/*****************************************************************************
-- OPER_ROLE : 
*****************************************************************************/
drop table if exists OPER_ROLE;
create table OPER_ROLE(
	id varchar(64) not null,
	name varchar(64) not null,
	vcid varchar(64),
	valid bit not null default 1,
	modifyAble bit not null default 1,
	remark varchar(512) ,
	primary key(id)
);
