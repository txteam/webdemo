--****************************************************************************
-- ±í£ºOPER_EMPLOYEEINFO
--****************************************************************************
drop table OPER_EMPLOYEEINFO;
create table OPER_EMPLOYEEINFO(
	leavingDate date,
	sex number(10,0),
	operatorid varchar2(64 char),
	code varchar2(64 char),
	officialDate date,
	entryDate date,
	trialPeriodEndDate date,
	leaving number(1,0),
	name varchar2(64 char),
	age number(10,0),
	official number(1,0) default 0 not null,
	lastUpdatePhoneLinkInfoDate date,
	cardNum varchar2(255 char),
	primary key(OPERATORID)
);
create unique index idx_oper_emp_00 on OPER_EMPLOYEEINFO(code);
