drop table if exists la_refund_record;
create table la_refund_record(
	id varchar(64) not null,
	amount decimal(16,2) not null,
	
	loanAccountId varchar(64) not null,
	clientId varchar(64) not null,
	clientName varchar(64),
	clientIdCardType varchar(64) not null,
	clientIdCardNumber varchar(64) not null,
	
	
	status varchar(255),
	
	tradingRecordId varchar(64),
	
	bankAccountId varchar(64),
	contractNumber varchar(255),
	
	
	sourceBankAccountId varchar(255),
	sourceRequestSourceType varchar(255),
	
	createOperatorId varchar(64),
	createDate datetime,
	lastUpdateOperatorId varchar(64),
	lastUpdateDate datetime,
	primary key(ID)
);
