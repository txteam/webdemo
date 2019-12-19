/*****************************************************************************
-- OPER_EMPLOYEE_INFO : 
*****************************************************************************/
drop table if exists OPER_EMPLOYEE_INFO;
create table OPER_EMPLOYEE_INFO(
	id varchar(64) not null,
	idCardType varchar(64) ,
	idCardNumber varchar(64) ,
	sex varchar(64) ,
	lastUpdateDate datetime(6) ,
	realName varchar(64) ,
	email varchar(255) ,
	entryDate datetime(6) ,
	createDate datetime(6) ,
	mobileNumber varchar(64) ,
	number varchar(64) ,
	leaving bit ,
	leavingDate datetime(6) ,
	birthday datetime(6) ,
	official bit ,
	officialDate datetime(6) ,
	operatorId varchar(64) ,
	primary key(id)
);
