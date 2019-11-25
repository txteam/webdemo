/*****************************************************************************
			表：bd_industry
*****************************************************************************/
drop table if exists bd_industry;
create table bd_industry(
	id varchar(64) not null,
	parentId varchar(64),
	level integer not null default 0,
	code varchar(64) not null,
	name varchar(64) not null,
	valid bit not null default 1, 
	modifyAble bit not null default 1,
	fullName varchar(64),
	remark varchar(512),
	lastUpdateDate datetime not null default now(),
	createDate datetime not null default now(),
	primary key(id)
);
create unique index idx_bd_industry_00 on bd_industry(code);
