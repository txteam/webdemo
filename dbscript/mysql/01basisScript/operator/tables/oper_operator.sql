/*****************************************************************************
-- OPER_OPERATOR : 
*****************************************************************************/
drop table if exists OPER_OPERATOR;
create table OPER_OPERATOR(
	id varchar(64) not null,
	pwdErrCount integer ,
	pwdUpdateDate datetime(6) ,
	lastUpdateDate datetime(6) ,
	mainPostId varchar(64) ,
	userName varchar(64) ,
	vcid varchar(64) ,
	loginName varchar(64) ,
	valid bit ,
	createDate datetime(6) ,
	locked bit ,
	examinePwd varchar(255) ,
	historyPwd varchar(255) ,
	invalidDate datetime(6) ,
	password varchar(255) ,
	organizationId varchar(64) ,
	primary key(id)
);
