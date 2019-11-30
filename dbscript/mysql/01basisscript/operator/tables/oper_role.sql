/*****************************************************************************
-- OPER_ROLE : 
*****************************************************************************/
drop table if exists OPER_ROLE;
create table OPER_ROLE(
	id varchar(64) not null,
	name varchar(64) not null,
	vcid varchar(64) not null,
	parentId varchar(64),
	roleTypeId varchar(64) not null,
	valid bit not null default 1,
	modifyAble bit not null default 1,
	remark varchar(512) ,
	createDate datetime(6) not null default now(6),
	lastUpdateDate datetime(6) not null default now(6),
	primary key(id)
);
create unique index idx_role_01 on OPER_ROLE(name,vcid);
