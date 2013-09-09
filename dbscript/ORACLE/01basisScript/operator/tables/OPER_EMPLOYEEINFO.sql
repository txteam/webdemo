--****************************************************************************
-- 表：OPER_EMPLOYEEINFO
--****************************************************************************
create table OPER_EMPLOYEEINFO(
	leavingDate date,
	sex number(10,0),
	OPERATORID varchar2(64 char),
	code varchar2(64 char),
	officialDate date,
	entryDate date,
	trialPeriodEndDate date,
	leaving number(1,0),
	name varchar2(64 char),
	age number(10,0),
	official number(1,0),
	lastUpdatePhoneLinkInfoDate date,
	cardNum varchar2(255 char),
	primary key(OPERATORID)
);
--create index idx_xxxx_xxxx on OPER_EMPLOYEEINFO(xxxx);
--create unique index idx_xxxx_xxxx on OPER_EMPLOYEEINFO(xxxx);

--comment on table OPER_EMPLOYEEINFO is 'demo信息表';
--comment on column OPER_EMPLOYEEINFO.leavingDate is 'xxxx';
