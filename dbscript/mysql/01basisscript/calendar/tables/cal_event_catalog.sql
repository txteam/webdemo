/*****************************************************************************
-- CAL_EVENT_CATALOG : 
*****************************************************************************/
drop table if exists CAL_EVENT_CATALOG;
create table CAL_EVENT_CATALOG(
	id varchar(64) not null,
	type varchar(64) not null,
	vcid varchar(64) not null,
	code varchar(64) not null,
	name varchar(100) not null,
	remark varchar(500),
	topicType varchar(64) not null,
	valid bit not null default 1,
	modifyAble bit not null default 1,
	lastUpdateDate datetime(6) not null,
	lastUpdateUserId varchar(64) ,
	createDate datetime(6) not null,
	createUserId varchar(64),
	primary key(id)
);
create unique index idx_event_catalog_01 on CAL_EVENT_CATALOG(code,vcid);
