DROP TABLE OPER_EMPLOYEEINFO;
CREATE TABLE OPER_EMPLOYEEINFO(
	leavingDate timestamp,
	sex smallint,
	operatorid varchar(64),
	code varchar(64),
	officialDate timestamp,
	entryDate timestamp,
	trialPeriodEndDate timestamp,
	leaving smallint,
	name varchar(64),
	age smallint,
	official smallint default 0 not null,
	lastUpdatePhoneLinkInfoDate timestamp,
	cardNum varchar(255),
	primary key(OPERATORID)
);
create unique index idx_oper_emp_00 on OPER_EMPLOYEEINFO(code);
