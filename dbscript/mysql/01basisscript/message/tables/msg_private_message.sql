/*****************************************************************************
-- MSG_PRIVATE_MESSAGE : 
*****************************************************************************/
drop table if exists MSG_PRIVATE_MESSAGE;
create table MSG_PRIVATE_MESSAGE(
	id varchar(64) not null,
	vcid varchar(64) not null,
	catalogId varchar(64),
	userType varchar(64) not null,
	userId varchar(64) not null,
	username varchar(64),
	sender varchar(64),
	senderId varchar(64),
	title varchar(100) not null,
	content varchar(4000),
	unread bit not null default 1,
	readDate datetime(6),
	lastUpdateDate datetime(6) not null,
	lastUpdateUserId varchar(64),
	createDate datetime(6) not null,
	createUserId varchar(64),
	primary key(id)
);
create index idx_private_message_01 on MSG_PRIVATE_MESSAGE(userId);
create index idx_private_message_02 on MSG_PRIVATE_MESSAGE(senderId);
create index idx_private_message_03 on MSG_PRIVATE_MESSAGE(catalogId);
create index idx_private_message_04 on MSG_PRIVATE_MESSAGE(createDate);
