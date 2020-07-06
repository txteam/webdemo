/*****************************************************************************
-- CL_SOCIAL_ACCOUNT : 
*****************************************************************************/
drop table if exists CL_SOCIAL_ACCOUNT;
create table CL_SOCIAL_ACCOUNT(
	id varchar(64) not null,
	type varchar(64) not null,
	clientId varchar(64) not null,
	uniqueId varchar(64) not null,
	username varchar(64),
	headImgUrl varchar(255) ,
	attributes varchar(500) ,
	lastUpdateDate datetime(6) ,
	createDate datetime(6) ,
	primary key(id)
);
create unique index IDX_CL_SOCIAL_ACCOUNT_00 on CL_SOCIAL_ACCOUNT(uniqueId,type);
create unique index IDX_CL_SOCIAL_ACCOUNT_01 on CL_SOCIAL_ACCOUNT(clientId,type);
