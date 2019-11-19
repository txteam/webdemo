/*****************************************************************************
-- OPER_EMPLOYEE_INFO : 
*****************************************************************************/
drop table if exists OPER_EMPLOYEE_INFO;
create table OPER_EMPLOYEE_INFO(
	id varchar(64) not null,
	code varchar(64) ,
	idCardType varchar(64) ,
	idCardNumber varchar(64) ,
	age integer ,
	sex integer ,
	lastUpdateDate datetime(6) ,
	name varchar(64) ,
	email varchar(255) ,
	entryDate datetime(6) ,
	phoneNumber varchar(64) ,
	createDate datetime(6) ,
	leaving bit ,
	leavingDate datetime(6) ,
	birthday datetime(6) ,
	official bit ,
	officialDate datetime(6) ,
	operatorId varchar(64) ,
	primary key(id)
);
