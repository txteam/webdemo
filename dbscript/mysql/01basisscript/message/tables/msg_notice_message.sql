/*****************************************************************************
-- MSG_NOTICE_MESSAGE : 
*****************************************************************************/
drop table if exists MSG_NOTICE_MESSAGE;
create table MSG_NOTICE_MESSAGE(
	id varchar(64) not null,
	vcid varchar(64) not null,
	catalogId varchar(64),
	title varchar(100) not null,
	content text,
	attributes varchar(4000),
	sender varchar(256),
	valid bit not null default 1,
	invalidDate datetime(6),
	published bit not null default 1,
	publishDate datetime(6),
	priority varchar(64),
	priorityValue integer,
	lastUpdateDate datetime(6) not null,
	lastUpdateUserId varchar(64) ,
	createDate datetime(6) not null,
	createUserId varchar(64) ,
	primary key(id)
);
create index idx_notice_message_01 on MSG_NOTICE_MESSAGE(catalogId);
create index idx_notice_message_02 on MSG_NOTICE_MESSAGE(createDate);
