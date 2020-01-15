/*****************************************************************************
-- NOTE_NOTEPAD : 
*****************************************************************************/
drop table if exists NOTE_NOTEPAD;
create table NOTE_NOTEPAD(
	id varchar(64) not null,
	type varchar(64) not null,
	topicType varchar(64) not null,
	topicId varchar(64) not null,
	catalogId varchar(64) ,
	title varchar(100) not null,
	content varchar(4000) ,
	attributes varchar(4000) ,
	remark varchar(500) ,
	lastUpdateDate datetime(6) not null,
	lastUpdateUserId varchar(64) ,
	createDate datetime(6) not null,
	createUserId varchar(64) ,
	primary key(id)
);
