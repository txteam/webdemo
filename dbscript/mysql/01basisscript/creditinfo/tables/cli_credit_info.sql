/*****************************************************************************
-- CLI_CREDIT_INFO : 
*****************************************************************************/
drop table if exists CLI_CREDIT_INFO;
create table CLI_CREDIT_INFO(
	id varchar(64) not null,
	clientId varchar(64) not null,
	version integer not null,
	type varchar(64) not null,
	idCardNumber varchar(64),
	idCardType varchar(64),
	baseVersion integer not null,
	lastUpdateDate datetime(6) not null,
	lastUpdateUserId varchar(64),
	createDate datetime(6) not null,
	createUserId varchar(64) ,
	locked bit not null,
	primary key(id)
);
create unique index idx_CLI_CREDIT_INFO_00 on CLI_CREDIT_INFO(clientId,version,type);
create index idx_CLI_CREDIT_INFO_01 on CLI_CREDIT_INFO(idCardNumber,idCardType);
create index idx_CLI_CREDIT_INFO_02 on CLI_CREDIT_INFO(idCardNumber,idCardType);