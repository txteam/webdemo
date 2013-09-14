--****************************************************************************
-- 职员信息表：OPER_EMPLOYEEINFO
--****************************************************************************
create table OPER_EMPLOYEEINFO(
	leavingDate timestamp,
	sex integer,
	OPERATORID varchar(64),
	code varchar(64),
	officialDate timestamp,
	entryDate timestamp,
	trialPeriodEndDate timestamp,
	leaving bit,
	name varchar(64),
	age integer,
	official bit,
	lastUpdatePhoneLinkInfoDate timestamp,
	cardNum varchar(255),
	primary key(OPERATORID)
);
