/*****************************************************************************
-- INS_INSTITUTION : 
*****************************************************************************/
drop table if exists INS_INSTITUTION;
create table INS_INSTITUTION(
	id varchar(64) not null,
	type varchar(64) not null,
	vcid varchar(64) not null,
	clientId varchar(64) not null,
	modifyAble bit not null default 1,
	name varchar(64),
	linkName varchar(64) ,
	linkMobileNumber varchar(64) ,
	provinceId varchar(64) ,
	cityId varchar(64) ,
	countyId varchar(64) ,
	districtId varchar(64) ,
	address varchar(256) ,
	fullAddress varchar(256),
	postcode varchar(64) ,
	remark varchar(500) ,
	lastUpdateDate datetime(6) not null,
	lastUpdateUserId varchar(64) ,
	createDate datetime(6) not null,
	createUserId varchar(64) ,
	primary key(id)
);
create unique index idx_ins_institution_00 on INS_INSTITUTION(clientId);
