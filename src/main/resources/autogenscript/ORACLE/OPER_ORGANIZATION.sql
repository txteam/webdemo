--****************************************************************************
-- 表：OPER_ORGANIZATION
--****************************************************************************
create table OPER_ORGANIZATION(
	provinceId varchar2(64 char),
	valid number(1,0),
	fullAddress varchar2(255 char),
	remark varchar2(2000 char),
	cityId varchar2(64 char),
	alias varchar2(255 char),
	code varchar2(64 char),
	type varchar2(64 char),
	areaId varchar2(64 char),
	id varchar2(64 char),
	parentId varchar2(64 char),
	chiefType number(10,0),
	address varchar2(255 char),
	name varchar2(64 char),
	fullName varchar2(64 char),
	chiefId number(10,0),
	primary key(ID)
);
--create index idx_xxxx_xxxx on OPER_ORGANIZATION(xxxx);
--create unique index idx_xxxx_xxxx on OPER_ORGANIZATION(xxxx);

--comment on table OPER_ORGANIZATION is 'demo信息表';
--comment on column OPER_ORGANIZATION.provinceId is 'xxxx';
--comment on column OPER_ORGANIZATION.valid is 'xxxx';
--comment on column OPER_ORGANIZATION.fullAddress is 'xxxx';
--comment on column OPER_ORGANIZATION.remark is 'xxxx';
--comment on column OPER_ORGANIZATION.cityId is 'xxxx';
--comment on column OPER_ORGANIZATION.alias is 'xxxx';
--comment on column OPER_ORGANIZATION.code is 'xxxx';
--comment on column OPER_ORGANIZATION.type is 'xxxx';
--comment on column OPER_ORGANIZATION.areaId is 'xxxx';
--comment on column OPER_ORGANIZATION.id is 'xxxx';
--comment on column OPER_ORGANIZATION.parentId is 'xxxx';
--comment on column OPER_ORGANIZATION.chiefType is 'xxxx';
--comment on column OPER_ORGANIZATION.address is 'xxxx';
--comment on column OPER_ORGANIZATION.name is 'xxxx';
--comment on column OPER_ORGANIZATION.fullName is 'xxxx';
--comment on column OPER_ORGANIZATION.chiefId is 'xxxx';
