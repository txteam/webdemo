/*****************************************************************************
-- ORG_POST : 
*****************************************************************************/
drop table if exists ORG_POST;
create table ORG_POST(
	id varchar(64) not null,
	vcid varchar(64) not null,
	organizationId varchar(64) not null,
	parentId varchar(64),
	code varchar(64) not null,
	name varchar(64) not null,
	fullName varchar(256) ,
	remark varchar(512) ,
	valid bit not null default 1,
	createDate datetime(6) not null default now(6),
	lastUpdateDate datetime(6) not null default now(6),
	primary key(id)
);
