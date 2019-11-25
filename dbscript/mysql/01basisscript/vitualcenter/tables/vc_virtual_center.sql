/*****************************************************************************
-- VC_VIRTUAL_CENTER : 
*****************************************************************************/
drop table if exists VC_VIRTUAL_CENTER;
create table VC_VIRTUAL_CENTER(
	id varchar(64) not null,
	code varchar(64) not null,
	name varchar(64) not null,
	valid bit not null,
	createDate datetime(6) not null,
	lastUpdateDate datetime(6) not null,
	modifyAble bit not null,
	parentId varchar(64) ,
	remark varchar(512) ,
	primary key(id)
);
