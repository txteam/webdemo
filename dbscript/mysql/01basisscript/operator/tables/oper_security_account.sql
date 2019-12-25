/*****************************************************************************
-- OPER_SECURITY_ACCOUNT : 
*****************************************************************************/
drop table if exists OPER_SECURITY_ACCOUNT;
create table OPER_SECURITY_ACCOUNT(
	id varchar(64) not null,
	operatorId varchar(64) not null,
	mobileNumber varchar(64),
	mobileBinding bit not null default 0,
	mobileLoginEnable bit not null default 0,
	email varchar(64),
	emailBinding bit not null default 0,
	idCardType varchar(64),
	idCardNumber varchar(64),
	idCardExpiryDate datetime(6),
	realNameAuthenticated bit not null default 0,
	realNameErrCount integer,
	realNameLastErrDate datetime(6),
	lastUpdateDate datetime(6) not null default now(6),
	createDate datetime(6) not null default now(6),
	primary key(id)
);
create unique index IDX_SECURITY_ACCOUNT_00 on OPER_SECURITY_ACCOUNT(operatorId);
