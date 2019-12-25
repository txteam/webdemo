drop table if exists OPER_EMPLOYEE_INFO_HIS;
CREATE TABLE OPER_EMPLOYEE_INFO_HIS(
	id varchar(64) not null,
	operatorId varchar(64) not null,
	number varchar(64) not null,
	idCardType varchar(64),
	idCardNumber varchar(64),
	realName varchar(64),
	mobileNumber varchar(64),
	email varchar(64),
	sex varchar(64),
	birthday datetime(6),
	entryDate datetime(6),
	official bit default 0,
	officialDate datetime(6),
	leaving bit default 0,
	leavingDate datetime(6),
	createDate datetime(6) not null default now(6),
	lastUpdateDate datetime(6) not null default now(6),
	primary key(id)
);

