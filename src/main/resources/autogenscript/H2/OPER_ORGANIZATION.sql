--****************************************************************************
-- 表：OPER_ORGANIZATION
--****************************************************************************
create table OPER_ORGANIZATION(
	provinceId varchar(64),
	valid boolean,
	fullAddress varchar(255),
	remark varchar(2000),
	cityId varchar(64),
	alias varchar(255),
	code varchar(64),
	type varchar(64),
	areaId varchar(64),
	id varchar(64),
	parentId varchar(64),
	chiefType integer,
	address varchar(255),
	name varchar(64),
	fullName varchar(64),
	chiefId integer,
	primary key(ID)
);
--create index idx_xxxx_xxxx on OPER_ORGANIZATION(xxxx);
--create unique index idx_xxxx_xxxx on OPER_ORGANIZATION(xxxx);

--comment on table OPER_ORGANIZATION is 'demo信息表';
--comment on column OPER_ORGANIZATION.provinceId is 'xxxx';
--comment on column OPER_ORGANIZATION.valid is 'xxxx';
--comment on column OPER_ORGANIZATION.fullAddress is 'xxxx';
--comment on column OPER_ORGANIZATION. is 'xxxx';
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
