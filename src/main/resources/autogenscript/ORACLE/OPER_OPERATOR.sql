--****************************************************************************
-- 表：OPER_OPERATOR
--****************************************************************************
create table OPER_OPERATOR(
	mainPost varchar2(64 char),
	valid number(1,0),
	pwdErrCount number(10,0),
	historyPwd varchar2(255 char),
	password varchar2(255 char),
	invalidDate timestamp,
	lastUpdateDate timestamp,
	id varchar2(64 char),
	organization varchar2(64 char),
	pwdUpdateDate timestamp,
	userName varchar2(64 char),
	locked number(1,0),
	createDate timestamp,
	examinePwd varchar2(255 char),
	loginName varchar2(64 char),
	primary key(ID)
);
--create index idx_xxxx_xxxx on OPER_OPERATOR(xxxx);
--create unique index idx_xxxx_xxxx on OPER_OPERATOR(xxxx);

--comment on table OPER_OPERATOR is 'demo信息表';
--comment on column OPER_OPERATOR.mainPost is 'xxxx';
--comment on column OPER_OPERATOR.valid is 'xxxx';
--comment on column OPER_OPERATOR.pwdErrCount is 'xxxx';
--comment on column OPER_OPERATOR.historyPwd is 'xxxx';
--comment on column OPER_OPERATOR.password is 'xxxx';
--comment on column OPER_OPERATOR.invalidDate is 'xxxx';
--comment on column OPER_OPERATOR.lastUpdateDate is 'xxxx';
--comment on column OPER_OPERATOR.id is 'xxxx';
--comment on column OPER_OPERATOR.organization is 'xxxx';
--comment on column OPER_OPERATOR.pwdUpdateDate is 'xxxx';
--comment on column OPER_OPERATOR.userName is 'xxxx';
--comment on column OPER_OPERATOR.locked is 'xxxx';
--comment on column OPER_OPERATOR.createDate is 'xxxx';
--comment on column OPER_OPERATOR.examinePwd is 'xxxx';
--comment on column OPER_OPERATOR.loginName is 'xxxx';
