--****************************************************************************
-- 表：OPER_OPERATOR
--****************************************************************************
create table OPER_OPERATOR(
	valid boolean,
	pwdErrCount integer,
	historyPwd varchar(255),
	organizationId varchar(64),
	password varchar(255),
	invalidDate timestamp,
	lastUpdateDate timestamp,
	id varchar(64),
	pwdUpdateDate timestamp,
	mainPostId varchar(64),
	userName varchar(64),
	locked boolean,
	createDate timestamp,
	examinePwd varchar(255),
	loginName varchar(64),
	primary key(ID)
);
--create index idx_xxxx_xxxx on OPER_OPERATOR(xxxx);
--create unique index idx_xxxx_xxxx on OPER_OPERATOR(xxxx);

--comment on table OPER_OPERATOR is 'demo信息表';
--comment on column OPER_OPERATOR.valid is 'xxxx';
--comment on column OPER_OPERATOR.pwdErrCount is 'xxxx';
--comment on column OPER_OPERATOR.historyPwd is 'xxxx';
--comment on column OPER_OPERATOR.organizationId is 'xxxx';
--comment on column OPER_OPERATOR.password is 'xxxx';
--comment on column OPER_OPERATOR.invalidDate is 'xxxx';
--comment on column OPER_OPERATOR.lastUpdateDate is 'xxxx';
--comment on column OPER_OPERATOR.id is 'xxxx';
--comment on column OPER_OPERATOR.pwdUpdateDate is 'xxxx';
--comment on column OPER_OPERATOR.mainPostId is 'xxxx';
--comment on column OPER_OPERATOR.userName is 'xxxx';
--comment on column OPER_OPERATOR.locked is 'xxxx';
--comment on column OPER_OPERATOR.createDate is 'xxxx';
--comment on column OPER_OPERATOR.examinePwd is 'xxxx';
--comment on column OPER_OPERATOR.loginName is 'xxxx';
