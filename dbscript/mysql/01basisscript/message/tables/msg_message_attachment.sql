/*****************************************************************************
-- MSG_MESSAGE_ATTACHMENT : 
*****************************************************************************/
drop table if exists MSG_MESSAGE_ATTACHMENT;
create table MSG_MESSAGE_ATTACHMENT(
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
create index idx_attachment_01 on MSG_MESSAGE_ATTACHMENT(messageId,messageType);
