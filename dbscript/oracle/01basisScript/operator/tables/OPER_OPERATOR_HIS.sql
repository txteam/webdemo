--****************************************************************************
-- ±í£ºOPER_OPERATOR
--****************************************************************************
DROP TABLE OPER_OPERATOR_HIS;
CREATE TABLE OPER_OPERATOR_HIS(
	id varchar2(64),
	vcid varchar2(64),
	valid number(1,0) DEFAULT 1 NOT NULL,
	pwdErrCount number(10,0),
	historyPwd varchar2(255),
	organizationId varchar2(64),
	password varchar2(255),
	invalidDate date,
	lastUpdateDate date default sysdate not null,
	pwdUpdateDate date default sysdate not null,
	mainPostId varchar2(64),
	userName varchar2(64),
	locked number(1,0) default 0 not null,
	createDate date default sysdate not null,
	examinePwd varchar2(255),
	loginName varchar2(64)  not null,
	primary key(ID)
);
