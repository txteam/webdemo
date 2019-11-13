/*****************************************************************************
-- OPER_POST : 
*****************************************************************************/
drop table if exists OPER_POST;
create table OPER_POST(
	id varchar(64) not null,
	parentId varchar(64) not null,
	code varchar(64) not null,
	vcid varchar(64) not null,
	name varchar(64) not null,
	fullName varchar(256) ,
	valid bit not null,
	remark varchar(256) ,
	organizationId varchar(64) ,
	primary key(id)
);
create index idx_oper_post_00 on oper_post(parentId);
create unique index idx_oper_post_01 on oper_post(code,vcid);
