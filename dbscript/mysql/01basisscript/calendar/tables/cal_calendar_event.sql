/*****************************************************************************
-- CAL_CALENDAR_EVENT : 
*****************************************************************************/
drop table if exists CAL_CALENDAR_EVENT;
create table CAL_CALENDAR_EVENT(
	id varchar(64) not null,
	vcid varchar(64) not null,
	catalogId varchar(64),
	type varchar(64) not null,
	topicType varchar(64) not null,
	topicId varchar(64),
	title varchar(100) not null,
	daysOfWeek varchar(64),
	start datetime(6),
	end datetime(6),
	url varchar(128),
	editable bit not null default 0,
	allDay bit not null default 1,
	repeated bit not null default 0,
	overlap bit not null default 1,
	rrule varchar(1024),	
	duration varchar(32),
	className varchar(64),
	attributes varchar(2000),
	remark varchar(100),
	creator varchar(64),
	createDate datetime(6) not null,
	createUserId varchar(64),
	lastUpdateDate datetime(6) not null,
	lastUpdateUserId varchar(64),
	primary key(id)
);
create index IDX_CALENDAR_EVENT_00 on CAL_CALENDAR_EVENT(vcid);
create index IDX_CALENDAR_EVENT_01 on CAL_CALENDAR_EVENT(topicId,topicType);
