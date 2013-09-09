--****************************************************************************
-- 表：BASIC_DISTRICT
--****************************************************************************
create table BASIC_DISTRICT(
	id varchar(64),
	parentId varchar(64),
	postalCode varchar(64),
	remark varchar(2000),
	name varchar(64),
	code varchar(64),
	fullName varchar(64),
	idCardCode varchar(64),
	type varchar(64),
	primary key(ID)
);
--create index idx_xxxx_xxxx on BASIC_DISTRICT(xxxx);
--create unique index idx_xxxx_xxxx on BASIC_DISTRICT(xxxx);

--comment on table BASIC_DISTRICT is 'demo信息表';
--comment on column BASIC_DISTRICT.id is 'xxxx';
--comment on column BASIC_DISTRICT.parentId is 'xxxx';
--comment on column BASIC_DISTRICT.postalCode is 'xxxx';
--comment on column BASIC_DISTRICT.remark is 'xxxx';
--comment on column BASIC_DISTRICT.name is 'xxxx';
--comment on column BASIC_DISTRICT.code is 'xxxx';
--comment on column BASIC_DISTRICT.fullName is 'xxxx';
--comment on column BASIC_DISTRICT.idCardCode is 'xxxx';
--comment on column BASIC_DISTRICT.type is 'xxxx';
