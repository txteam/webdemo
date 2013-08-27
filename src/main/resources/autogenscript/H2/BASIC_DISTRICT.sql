--****************************************************************************
-- 表：BASIC_DISTRICT
--****************************************************************************
create table BASIC_DISTRICT(
	id varchar(64),
	parentId varchar(64),
	description varchar(2000),
	name varchar(64),
	type varchar(64),
	primary key(ID)
);
--create index idx_xxxx_xxxx on BASIC_DISTRICT(xxxx);
--create unique index idx_xxxx_xxxx on BASIC_DISTRICT(xxxx);

--comment on table BASIC_DISTRICT is 'demo信息表';
--comment on column BASIC_DISTRICT.id is 'xxxx';
--comment on column BASIC_DISTRICT.id is 'xxxx';
--comment on column BASIC_DISTRICT.parentId is 'xxxx';
--comment on column BASIC_DISTRICT.description is 'xxxx';
--comment on column BASIC_DISTRICT.name is 'xxxx';
--comment on column BASIC_DISTRICT.type is 'xxxx';
