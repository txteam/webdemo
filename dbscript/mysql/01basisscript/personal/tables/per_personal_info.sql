/*****************************************************************************
-- PER_PERSONAL_INFO : 
*****************************************************************************/
drop table if exists PER_PERSONAL_INFO;
create table PER_PERSONAL_INFO(
	id varchar(64) not null,
	type varchar(64) not null,
	vcid varchar(64) not null,
	clientId varchar(64) not null,
	creditInfoBinding bit not null default 0,
	creditInfoId varchar(64),
	modifyAble bit not null default 1,
	name varchar(32),
	sex varchar(64),
	birthday datetime(6) ,
	fristName varchar(32),
	lastName varchar(32),
	provinceId varchar(64) ,
	cityId varchar(64) ,
	countyId varchar(64) ,
	districtId varchar(64) ,
	address varchar(256) ,
	fullAddress varchar(256) ,
	remark varchar(500),
	lastUpdateDate datetime(6) not null,
	lastUpdateUserId varchar(64) ,
	createDate datetime(6) not null,
	createUserId varchar(64) ,
	primary key(id)
);
create unique index idx_per_personal_info_00 on PER_PERSONAL_INFO(clientId);
create index idx_per_personal_info_01 on PER_PERSONAL_INFO(creditInfoId);
