--****************************************************************************
-- ±í£ºOPER_OPERATOR
--****************************************************************************
create table OPER_OPERATOR(
	valid bit default 1 not null,
	pwdErrCount integer,
	historyPwd varchar(255),
	organizationId varchar(64),
	password varchar(255) not null,
	invalidDate timestamp,
	lastUpdateDate timestamp default sysdate() not null,
	id varchar(64) not null,
	pwdUpdateDate timestamp default sysdate() not null,
	mainPostId varchar(64),
	userName varchar(64),
	locked bit default 0 not null,
	createDate timestamp default sysdate() not null,
	examinePwd varchar(255),
	loginName varchar(64) not null,
	primary key(ID)
);
create unique index idx_oper_oper_00 on OPER_OPERATOR(loginName);
create index idx_oper_oper_01 on OPER_OPERATOR(loginName,password);
