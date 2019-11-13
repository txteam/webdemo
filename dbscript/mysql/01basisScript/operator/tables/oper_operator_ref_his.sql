drop table if exists oper_operator_ref_his;
CREATE TABLE oper_operator_ref_his(
	id varchar(64) not null,
	operatorId varchar(64) not null,
	refId varchar(64) not null,
	refType varchar(64) not null,
	effectiveDate datetime(6) not null,
	expiryDate datetime(6) ,
	lastUpdateDate datetime(6) not null,
	lastUpdateOperatorId varchar(64) ,
	createDate datetime(6) not null,
	createOperatorId varchar(64) ,
	primary key(id)
);
