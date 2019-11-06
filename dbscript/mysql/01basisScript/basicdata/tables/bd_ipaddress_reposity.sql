/*****************************************************************************
			表：bd_ipaddress_reposity
*****************************************************************************/
drop table if exists bd_ipaddress_reposity;
create table bd_ipaddress_reposity(
	id varchar(64) not null,
	code varchar(64) not null,
	name varchar(64),
	valid bit not null default 1,
	modifyAble bit not null default 1,
	districtId varchar(64) not null,
	continentName varchar(64),
	nationName varchar(64),
	provinceName varchar(64),
	cityName varchar(64),
	countyName varchar(64),
	en varchar(255),
	enName varchar(64),
	telecomOperatorId varchar(64),
	remark varchar(500),
	latitude decimal(32,16),
	longitude decimal(32,16),
	lastUpdateDate datetime not null default now(),
	createDate datetime not null default now(),
	primary key(id)
);
create index idx_ipaddress_reposity_00 on bd_ipaddress_reposity(code);