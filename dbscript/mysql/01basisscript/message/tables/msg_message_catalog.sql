/*****************************************************************************
-- MSG_MESSAGE_CATALOG : 
*****************************************************************************/
drop table if exists MSG_MESSAGE_CATALOG;
create table MSG_MESSAGE_CATALOG(
	id varchar(64) not null,
	parentId varchar(64),
	vcid varchar(64) not null,
	messageType varchar(64) not null,
	code varchar(64) not null,
	name varchar(64) not null,
	valid bit not null default 1,
	modifyAble bit not null default 1,
	remark varchar(512),
	lastUpdateDate datetime(6) not null,
	createDate datetime(6) not null,
	primary key(id)
);
create unique index idx_msg_catalog_01 on MSG_MESSAGE_CATALOG(code,vcid);
