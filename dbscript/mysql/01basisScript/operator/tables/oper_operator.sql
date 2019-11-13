/*****************************************************************************
-- OPER_OPERATOR : 
*****************************************************************************/
drop table if exists OPER_OPERATOR;
create table OPER_OPERATOR(
	id varchar(64) not null,
	vcid varchar(64) ,
	organizationId varchar(64) ,
	loginName varchar(64) not null,
	password varchar(64) not null,
	userName varchar(64) ,
	mainPostId varchar(64) ,
	invalidDate datetime(6) ,
	examinePwd varchar(64) ,
	historyPwd varchar(64) ,
	pwdErrCount integer not null default 0,
	pwdUpdateDate datetime(6) not null default now(6),
	valid bit NOT NULL default 1 ,
	locked bit not null default 0 ,
	createDate datetime(6) not null default now(6),
	lastUpdateDate datetime(6) not null default now(6),
	primary key(id)
);
create unique index idx_oper_oper_00 on oper_operator(loginName);
create index idx_oper_oper_01 on oper_operator(vcid);
create index idx_oper_oper_02 on oper_operator(organizationId);