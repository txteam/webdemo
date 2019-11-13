/*****************************************************************************
-- OPER_EMPLOYEE_INFO : 
*****************************************************************************/
drop table if exists OPER_EMPLOYEE_INFO;
create table OPER_EMPLOYEE_INFO(
	id varchar(64) not null,
	operatorId varchar(64) not null,
	code varchar(64) not null,
	name varchar(64) ,
	idCardType varchar(64),
	idCardNumber varchar(64),
	age integer ,
	sex integer ,
	email varchar(255) ,
	entryDate datetime(6) ,
	phoneNumber varchar(64) ,
	birthday datetime(6) ,
	official bit not null default 0,
	officialDate datetime(6) ,
	leaving bit not null default 0,
	lastUpdateDate datetime(6) not null default now(6),
	createDate datetime(6) not null default now(6),
	primary key(id)
);
create unique index idx_oper_emp_00 on OPER_EMPLOYEE_INFO(operatorId);
create unique index idx_oper_emp_01 on OPER_EMPLOYEE_INFO(code);
