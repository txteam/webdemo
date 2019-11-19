drop table if exists  OPER_POST_HIS;
create table OPER_POST_HIS(
	id varchar(64) not null,
	parentId varchar(64) not null,
	code varchar(64) not null,
	vcid varchar(64),
	name varchar(64) not null,
	fullName varchar(256) ,
	valid bit not null,
	remark varchar(512) ,
	organizationId varchar(64) ,
	createDate not null default now(6),
	lastUpdateDate not null default now(6)
	primary key(id)
);
