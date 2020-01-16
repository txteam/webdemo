/*****************************************************************************
-- CAL_CALENDAR_EVENT : 
*****************************************************************************/
drop table if exists CAL_CALENDAR_EVENT;
create table CAL_CALENDAR_EVENT(
	id varchar(64) not null,
	vcid varchar(64) not null,
	topicType varchar(64) not null,
	topicId varchar(64),
	type varchar(64) not null,
	title varchar(100) not null,
	remark varchar(500) ,
	allDay bit not null default 0,
	editable bit not null default 0,
	start datetime(6) not null,
	end datetime(6) not null,
	linkUrl varchar(128) ,
	classValue varchar(64) ,
	styleValue varchar(64) ,
	attributes varchar(4000) ,
	lastUpdateDate datetime(6) not null,
	lastUpdateUserId varchar(64) ,
	createDate datetime(6) not null,
	createUserId varchar(64) ,
	primary key(id)
);
create index IDX_CALENDAR_EVENT_00 on CAL_CALENDAR_EVENT(vcid);
create index IDX_CALENDAR_EVENT_01 on CAL_CALENDAR_EVENT(topicId,topicType);
