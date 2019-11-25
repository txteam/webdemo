/*****************************************************************************
-- OPER_OPERATOR_REF : 
*****************************************************************************/
drop table if exists OPER_OPERATOR_REF;
create table OPER_OPERATOR_REF(
	id varchar(64) not null,
	operatorId varchar(64) not null,
	refId varchar(64) not null,
	refType varchar(64) not null,
	effectiveDate datetime(6) not null default now(6),
	expiryDate datetime(6) ,
	lastUpdateDate datetime(6) not null default now(6),
	lastUpdateOperatorId varchar(64) ,
	createDate datetime(6) not null default now(6),
	createOperatorId varchar(64) ,
	primary key(id)
);
CREATE UNIQUE INDEX idx_oper_operref_00 ON oper_operator_ref(refId,refType,operatorId);
CREATE INDEX idx_oper_operref_01 ON oper_operator_ref(operatorId);
CREATE INDEX idx_oper_operref_02 ON oper_operator_ref(effectiveDate);
CREATE INDEX idx_oper_operref_03 ON oper_operator_ref(expiryDate);
