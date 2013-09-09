--****************************************************************************
-- 表：BASIC_DISTRICT
--****************************************************************************
--drop table BASIC_DISTRICT;
create table BASIC_DISTRICT(
	id varchar2(64 char) not null,
	parentId varchar2(64 char),
	postalCode varchar2(64 char),
	remark varchar2(2000 char),
	name varchar2(64 char) not null,
	code varchar2(64 char) not null,
	fullName varchar2(64 char),
	idCardCode varchar2(64 char),
	type varchar2(64 char),
	primary key(ID)
);
create index idx_bas_district_00 on BASIC_DISTRICT(code);
create index idx_bas_district_01 on BASIC_DISTRICT(parentId);
create index idx_bas_district_02 on BASIC_DISTRICT(idCardCode);
create index idx_bas_district_03 on BASIC_DISTRICT(postalCode);

comment on table BASIC_DISTRICT is '区域信息表';
comment on column BASIC_DISTRICT.idCardCode is '区域对应身份证编码';
comment on column BASIC_DISTRICT.postalCode is '区域对应邮政编码';
