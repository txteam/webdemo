drop table if exists OPER_OPERATOR_HIS;
CREATE TABLE OPER_OPERATOR_HIS(
	id varchar(64) not null,
	vcid varchar(64) not null,
	organizationId varchar(64) not null,
	loginName varchar(64) not null,
	userName varchar(64),
	pwdErrCount integer not null default 0,
	pwdUpdateDate datetime(6) not null default now(6),
	valid bit not null default 1,
	locked bit not null default 0,
	password varchar(64) not null,
	historyPwd varchar(64) ,
	examinePwd varchar(64) ,
	mainPostId varchar(64) ,
	invalidDate datetime(6) ,
	lastUpdateDate datetime(6) not null default now(6),
	createDate datetime(6) not null default now(6),
	primary key(id)
);
