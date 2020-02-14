/*****************************************************************************
-- MSG_DIALOG_MESSAGE : 
*****************************************************************************/
drop table if exists MSG_DIALOG_MESSAGE;
create table MSG_DIALOG_MESSAGE(
	id varchar(64) not null,
	vcid varchar(64) not null,
	parentId varchar(64),
	catalogId varchar(64),
	userType varchar(64) not null,
	userId varchar(64),
	topicType varchar(64) not null,
	topicId varchar(64),
	title varchar(100) not null,
	content varchar(4000) ,
	lastUpdateDate datetime(6) not null default now(6),
	lastUpdateUserId varchar(64),
	createDate datetime(6) not null default now(6),
	createUserId varchar(64),
	primary key(id)
);
create index idx_dialog_message_01 on MSG_PRIVATE_MESSAGE(catalogId);
create index idx_dialog_message_02 on MSG_PRIVATE_MESSAGE(userId,userType);
create index idx_dialog_message_03 on MSG_PRIVATE_MESSAGE(topicId,topicType);
create index idx_dialog_message_04 on MSG_PRIVATE_MESSAGE(createDate);