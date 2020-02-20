/*****************************************************************************
-- MSG_PRIVATE_MESSAGE : 
*****************************************************************************/
drop table if exists MSG_PRIVATE_MESSAGE;
create table MSG_PRIVATE_MESSAGE(
	id varchar(64) not null,
	vcid varchar(64) not null,
	type varchar(64) not null,
	userId varchar(64) not null,
	username varchar(64) not null,
	userType varchar(64) not null,
	senderUserId varchar(64) not null,
	senderUsername varchar(64) not null,
	senderUserType varchar(64) not null,
	unread bit not null default 1,
	readDate datetime(6) ,
	title varchar(100) not null,
	content varchar(4000) ,
	lastUpdateDate datetime(6) not null,
	lastUpdateUserId varchar(64) ,
	createDate datetime(6) not null,
	createUserId varchar(64) ,
	primary key(id)
);
create index IDX_PRIVATE_MESSAGE_01 on MSG_PRIVATE_MESSAGE(userId,userType);
create index IDX_PRIVATE_MESSAGE_02 on MSG_PRIVATE_MESSAGE(senderUserId,senderUserType);
create index IDX_PRIVATE_MESSAGE_03 on MSG_PRIVATE_MESSAGE(createDate);
