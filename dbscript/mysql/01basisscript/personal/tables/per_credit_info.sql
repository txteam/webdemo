/*****************************************************************************
-- PER_CREDIT_INFO : 
*****************************************************************************/
drop table if exists PER_CREDIT_INFO;
create table PER_CREDIT_INFO(
	id varchar(64) not null,
	clientId varchar(64) not null,
	idCardType varchar(64) not null,
	idCardNumber varchar(64) not null,
	version integer not null,
	versionType varchar(64) not null,
	lastUpdateDate datetime(6) not null,
	lastUpdateUserId varchar(64) ,
	createDate datetime(6) not null,
	createUserId varchar(64) ,
	primary key(id)
);
create unique index idx_per_credit_info_00 on PER_CREDIT_INFO(clientId,version,versionType);
create unique index idx_per_credit_info_01 on PER_CREDIT_INFO(idCardNumber,idCardType,version,versionType);
