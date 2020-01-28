/*****************************************************************************
-- NOTE_NOTEPAD_CATALOG : 
*****************************************************************************/
drop table if exists NOTE_NOTEPAD_CATALOG;
create table NOTE_NOTEPAD_CATALOG(
	id varchar(64) not null,
	vcid varchar(64) not null,
	type varchar(64) not null,
	parentId varchar(64),
	topicType varchar(64) not null,
	topicId varchar(64) not null,
	name varchar(100) not null,
	remark varchar(500) ,
	lastUpdateDate datetime(6) not null,
	lastUpdateUserId varchar(64) ,
	createDate datetime(6) not null,
	createUserId varchar(64) ,
	primary key(id)
);
create index IDX_NOTE_NOTEPAD_CATALOG_00 on NOTE_NOTEPAD_CATALOG(vcid);
create index IDX_NOTE_NOTEPAD_CATALOG_01 on NOTE_NOTEPAD_CATALOG(topicId,topicType);
