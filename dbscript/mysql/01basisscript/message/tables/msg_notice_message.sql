drop table if exists MSG_NOTICE_MESSAGE;
create table MSG_NOTICE_MESSAGE(
	id varchar(64) not null,
	vcid varchar(64) not null,
	messageType varchar(64) not null,
	messageId varchar(64) not null,
	attachmentId varchar(64) not null,
	attachmentUrl varchar(256) not null,
	lastUpdateDate datetime(6) not null,
	createDate datetime(6) not null,
	primary key(id)
);
create index IDX_NOTICE_MSG_01 on MSG_NOTICE_MESSAGE(messageId,messageType);
create index IDX_NOTICE_MSG_02 on MSG_NOTICE_MESSAGE(attachmentId);

