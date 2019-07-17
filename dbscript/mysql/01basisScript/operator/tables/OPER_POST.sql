/*****************************************************************************
-- OPER_POST : 
*****************************************************************************/
drop table if exists OPER_POST;
create table OPER_POST(
	id varchar(64) not null,
	code varchar(64) not null,
	fullName varchar(256) ,
	name varchar(64) not null,
	vcid varchar(64) not null,
	valid bit not null,
	parentId varchar(64) not null,
	remark varchar(256) ,
	organizationId varchar(64) ,
	primary key(id)
);
