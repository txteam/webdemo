/*****************************************************************************
-- TestM1 : 
*****************************************************************************/
drop table if exists TestM1;
create table TestM1(
	id varchar(64) not null,
	code varchar(32) not null,
	lastUpdateDate datetime(6) not null,
	lastUpdateOperatorId varchar(64) ,
	name varchar(32) not null,
	testInt integer ,
	testLong bigint ,
	testBigDecimal decimal(32,8) ,
	type varchar(64) ,
	valid bit not null,
	createDate datetime(6) not null,
	createOperatorId varchar(64) ,
	expiryDate datetime(6) ,
	modifyAble bit ,
	parentId varchar(64) ,
	remark varchar(512) ,
	success bit ,
	effictiveDate datetime(6) ,
	attributes varchar(255) ,
	description varchar(512) ,
	primary key(id)
);
