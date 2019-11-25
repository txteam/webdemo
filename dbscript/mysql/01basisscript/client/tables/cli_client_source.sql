/*****************************************************************************
-- CLI_CLIENT_SOURCE : 
*****************************************************************************/
drop table if exists CLI_CLIENT_SOURCE;
create table CLI_CLIENT_SOURCE(
	id varchar(64) not null,
	code varchar(64) not null,
	name varchar(64) not null,
	remark varchar(512),
	valid bit not null default 1,
	modifyAble bit not null default 1,
	lastUpdateDate datetime(6) not null default now(6),
	createDate datetime(6) not null default now(6),
	primary key(id)
);
