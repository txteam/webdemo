drop table if exists oper_employeeinfo;
CREATE TABLE oper_employeeinfo(
	leavingDate datetime,
	sex smallint,
	operatorid varchar(64),
	code varchar(64),
	officialDate datetime,
	entryDate datetime,
	trialPeriodEndDate datetime,
	leaving smallint,
	name varchar(64),
	age smallint,
	official smallint default 0 not null,
	lastUpdatePhoneLinkInfoDate datetime,
	cardNum varchar(255),
	primary key(OPERATORID)
);
create unique index idx_oper_emp_00 on OPER_EMPLOYEEINFO(code);
