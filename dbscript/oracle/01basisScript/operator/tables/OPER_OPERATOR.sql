--****************************************************************************
-- 表：OPER_OPERATOR
--****************************************************************************
DROP TABLE OPER_OPERATOR;
CREATE TABLE OPER_OPERATOR(
	id varchar2(64) not null,
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
create unique index idx_oper_oper_00 on OPER_OPERATOR(loginName);
create index idx_oper_oper_01 on OPER_OPERATOR(loginName,password);

comment on table OPER_OPERATOR is '系统使用人员信息表';
comment on column OPER_OPERATOR.valid is '是否有效 0 无效  1有效  默认有效';
