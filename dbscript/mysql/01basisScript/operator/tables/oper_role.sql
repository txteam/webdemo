/*****************************************************************************
-- OPER_ROLE : 
*****************************************************************************/
drop table if exists OPER_ROLE;
create table OPER_ROLE(
	id varchar(64) not null,
	code varchar(64) ,
	name varchar(64) ,
	vcid varchar(64) ,
	valid bit ,
	modifyAble bit ,
	remark varchar(512) ,
	primary key(id)
);
create unique index idx_oper_role_01 on oper_role(code,vcid);
