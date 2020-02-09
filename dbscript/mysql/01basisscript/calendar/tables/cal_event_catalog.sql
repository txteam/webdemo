/*****************************************************************************
-- CAL_EVENT_CATALOG : 
*****************************************************************************/
drop table if exists CAL_EVENT_CATALOG;
create table CAL_EVENT_CATALOG(
	id varchar(64) not null,
	vcid varchar(64) not null,
	parentId varchar(64),
	type varchar(64) not null,
	topicType varchar(64) not null,
	topicId varchar(64) ,
	name varchar(100) not null,
	remark varchar(500) ,
	lastUpdateDate datetime(6) not null default now(6),
	lastUpdateUserId varchar(64) ,
	createDate datetime(6) not null default now(6),
	createUserId varchar(64) ,
	primary key(id)
);
create index idx_event_catalog_01 on CAL_EVENT_CATALOG(vcid);
create index idx_event_catalog_02 on CAL_EVENT_CATALOG(topicId,topicType);
