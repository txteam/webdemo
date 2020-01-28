/*****************************************************************************
-- MSG_PRIVATE_MESSAGE : 
*****************************************************************************/
drop table if exists MSG_PRIVATE_MESSAGE;
create table MSG_PRIVATE_MESSAGE(
	id varchar(64) not null,
	vcid varchar(64) not null,
	type varchar(64) not null,
	catalog varchar(64) not null,
	userType varchar(64) not null,
	userId varchar(64) not null,
	sourceId varchar(64) ,
	title varchar(100) not null,
	content varchar(4000) ,
	unread bit not null default 1,
	readDate datetime(6),
	lastUpdateDate datetime(6) not null,
	lastUpdateUserId varchar(64) ,
	createDate datetime(6) not null,
	createUserId varchar(64) ,
	primary key(id)
);
create index IDX_PRIVATE_MESSAGE_00 on MSG_PRIVATE_MESSAGE(vcid);
create index IDX_PRIVATE_MESSAGE_01 on MSG_PRIVATE_MESSAGE(userId,userType);
create index IDX_PRIVATE_MESSAGE_02 on MSG_PRIVATE_MESSAGE(sourceId);
