drop table if exists bd_district;
create table bd_district(
	id varchar(64) not null,
	parentId varchar(64),
	level integer not null default 0,
	code varchar(64) not null,
	type varchar(64) not null,
	name varchar(64) not null,
	pinyin varchar(64),
	py varchar(64),
	zipCode varchar(64) not null,
	provinceId varchar(64),
	cityId varchar(64),
	countyId varchar(64),
	valid bit not null default 1, 
	modifyAble bit not null default 1,
	fullName varchar(64),
	remark varchar(512),
	attributes varchar(512),
	lastUpdateDate datetime not null default now(),
	createDate datetime not null default now(),
	primary key(id)
);
create unique index idx_bd_district_00 on bd_district(code);
create index idx_bd_district_01 on bd_district(zipCode);
create index idx_bd_district_02 on bd_district(provinceId);
create index idx_bd_district_03 on bd_district(cityId);
create index idx_bd_district_04 on bd_district(countyId);
create index idx_bd_district_05 on bd_district(pinyin);
create index idx_bd_district_06 on bd_district(py);
