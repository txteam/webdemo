/*****************************************************************************
-- OPER_ROLE_CATALOG : 
*****************************************************************************/
drop table if exists OPER_ROLE_CATALOG;
create table OPER_ROLE_CATALOG(
	id varchar(64) not null,
	name varchar(64) not null,
	vcid varchar(64) not null,
	valid bit not null default 1,
	modifyAble bit not null default 1,
	parentId varchar(64) ,
	remark varchar(512),
	lastUpdateDate datetime(6) not null default now(6),
	createDate datetime(6) not null default now(6),
	primary key(id)
);
