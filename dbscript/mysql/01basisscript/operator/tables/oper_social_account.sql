/*****************************************************************************
-- OPER_SOCIAL_ACCOUNT : 
*****************************************************************************/
drop table if exists OPER_SOCIAL_ACCOUNT;
create table OPER_SOCIAL_ACCOUNT(
	id varchar(64) not null,
	operatorId varchar(64) not null,
	type varchar(64) not null,
	uniqueId varchar(64) not null,
	username varchar(64),
	headImgUrl varchar(256),
	attributes varchar(1000),
	lastUpdateDate datetime(6) not null default now(6),
	createDate datetime(6) not null default now(6),
	primary key(id)
);
create unique index IDX_SOCIAL_ACCOUNT_00 on OPER_SOCIAL_ACCOUNT(uniqueId,type);
create unique index IDX_SOCIAL_ACCOUNT_01 on OPER_SOCIAL_ACCOUNT(operatorId,type);