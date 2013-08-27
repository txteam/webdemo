--****************************************************************************
-- 表：OPER_EMPLOYEE
--****************************************************************************
create table OPER_EMPLOYEE(
	leavingDate timestamp,
	sex number(10,0),
	operatorId varchar2(64 char),
	code varchar2(64 char),
	officialDate timestamp,
	entryDate timestamp,
	trialPeriodEndDate timestamp,
	leaving number(1,0),
	name varchar2(64 char),
	age number(10,0),
	official number(1,0),
	lastUpdatePhoneLinkInfoDate timestamp,
	cardNum varchar2(255 char),
	primary key(OPERATORID)
);
--create index idx_xxxx_xxxx on OPER_EMPLOYEE(xxxx);
--create unique index idx_xxxx_xxxx on OPER_EMPLOYEE(xxxx);

--comment on table OPER_EMPLOYEE is 'demo信息表';
--comment on column OPER_EMPLOYEE.leavingDate is 'xxxx';
--comment on column OPER_EMPLOYEE.sex is 'xxxx';
--comment on column OPER_EMPLOYEE.operatorId is 'xxxx';
--comment on column OPER_EMPLOYEE.code is 'xxxx';
--comment on column OPER_EMPLOYEE.officialDate is 'xxxx';
--comment on column OPER_EMPLOYEE.entryDate is 'xxxx';
--comment on column OPER_EMPLOYEE.trialPeriodEndDate is 'xxxx';
--comment on column OPER_EMPLOYEE.leaving is 'xxxx';
--comment on column OPER_EMPLOYEE.name is 'xxxx';
--comment on column OPER_EMPLOYEE.age is 'xxxx';
--comment on column OPER_EMPLOYEE.official is 'xxxx';
--comment on column OPER_EMPLOYEE.lastUpdatePhoneLinkInfoDate is 'xxxx';
--comment on column OPER_EMPLOYEE.cardNum is 'xxxx';
