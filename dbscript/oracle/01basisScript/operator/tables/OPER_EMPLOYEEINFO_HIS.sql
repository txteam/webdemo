--****************************************************************************
-- ±í£ºOPER_EMPLOYEEINFO
--****************************************************************************
DROP TABLE OPER_EMPLOYEEINFO_HIS;
CREATE TABLE OPER_EMPLOYEEINFO(
	leavingDate date,
	sex number(10,0),
	operatorid varchar2(64),
	code varchar2(64),
	officialDate date,
	entryDate date,
	trialPeriodEndDate date,
	leaving number(1,0),
	name varchar2(64),
	age number(10,0),
	official number(1,0) default 0 not null,
	lastUpdatePhoneLinkInfoDate date,
	cardNum varchar2(255),
	primary key(OPERATORID)
);
