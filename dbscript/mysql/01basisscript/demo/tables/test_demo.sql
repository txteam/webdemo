/*****************************************************************************
-- TEST_DEMO : 
*****************************************************************************/
drop table if exists TEST_DEMO;
create table TEST_DEMO(
	id varchar(64) not null,
	code varchar(32) not null,
	lastUpdateDate datetime(6) not null,
	lastUpdateOperatorId varchar(64) ,
	name varchar(32) not null,
	testInt integer ,
	testLong bigint ,
	testInteger integer ,
	testBigDecimal decimal(32,8) ,
	type varchar(64) ,
	superInt integer ,
	superBoolean bit ,
	superDemoCode varchar(64) ,
	superBigDeceimal decimal(32,8) ,
	superIntegerObject integer ,
	superIsBooleanObject bit ,
	valid bit not null,
	createDate datetime(6) not null,
	createOperatorId varchar(64) ,
	expiryDate datetime(6) ,
	modifyAble bit ,
	parentId varchar(64) ,
	remark varchar(512) ,
	nested1Id varchar(64) ,
	nested2Code varchar(64) ,
	success bit ,
	effictiveDate datetime(6) ,
	attributes varchar(255) ,
	description varchar(512) ,
	primary key(id)
);
