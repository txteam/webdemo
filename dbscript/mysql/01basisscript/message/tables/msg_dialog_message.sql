/*****************************************************************************
-- MSG_DIALOG_MESSAGE : 
*****************************************************************************/
drop table if exists MSG_DIALOG_MESSAGE;
create table MSG_DIALOG_MESSAGE(
	id varchar(64) not null,
	vcid varchar(64) not null,
	parentId varchar(64),
	type varchar(64) not null,
	title varchar(100) not null,
	content varchar(4000),
	userId varchar(64),
	userType varchar(64),
	topicId varchar(64),
	topicType varchar(64),
	lastUpdateDate datetime(6) not null,
	lastUpdateUserId varchar(64),
	createDate datetime(6) not null,
	createUserId varchar(64),
	primary key(id)
);
create index IDX_DIALOG_MESSAGE_01 on MSG_DIALOG_MESSAGE(userId,userType);
create index IDX_DIALOG_MESSAGE_02 on MSG_DIALOG_MESSAGE(topicId,topicType);
create index IDX_DIALOG_MESSAGE_03 on MSG_DIALOG_MESSAGE(createDate);