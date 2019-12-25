/*****************************************************************************
-- OPER_EMPLOYEE_INFO : 
*****************************************************************************/
drop table if exists OPER_EMPLOYEE_INFO;
create table OPER_EMPLOYEE_INFO(
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
create unique index idx_employee_info_00 on OPER_EMPLOYEE_INFO(operatorId);
create unique index idx_employee_info_01 on OPER_EMPLOYEE_INFO(number);