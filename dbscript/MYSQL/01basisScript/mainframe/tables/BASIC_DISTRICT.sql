--****************************************************************************
-- ±í£ºBASIC_DISTRICT
--****************************************************************************
--drop table BASIC_DISTRICT;
create table BASIC_DISTRICT(
	id varchar(64) not null,
	parentId varchar(64),
	postalCode varchar(64),
	remark varchar(2000),
	name varchar(64) not null,
	code varchar(64) not null,
	fullName varchar(64),
	idCardCode varchar(64),
	type varchar(64),
	primary key(ID)
);
create index idx_bas_district_00 on BASIC_DISTRICT(code);
create index idx_bas_district_01 on BASIC_DISTRICT(parentId);
create index idx_bas_district_02 on BASIC_DISTRICT(idCardCode);
create index idx_bas_district_03 on BASIC_DISTRICT(postalCode);
