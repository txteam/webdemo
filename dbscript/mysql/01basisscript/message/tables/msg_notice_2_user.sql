/*****************************************************************************
-- MSG_NOTICE_2_USER : 
*****************************************************************************/
drop table if exists MSG_NOTICE_2_USER;
create table MSG_NOTICE_2_USER(
	id varchar(64) not null,
	userType varchar(64) not null,
	userId varchar(64) not null,
	noticeId varchar(64) not null,
	unread bit not null default 1,
	readDate datetime(6),
	primary key(id)
);
create index idx_notice_2_user_01 on MSG_NOTICE_2_USER(noticeId);
create index idx_notice_2_user_02 on MSG_NOTICE_2_USER(userId,userType);
