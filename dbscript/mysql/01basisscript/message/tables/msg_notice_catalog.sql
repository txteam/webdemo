/*****************************************************************************
-- MSG_NOTICE_CATALOG : 
*****************************************************************************/
drop table if exists MSG_NOTICE_CATALOG;
create table MSG_NOTICE_CATALOG(
	id varchar(64) not null,
	code varchar(64) ,
	lastUpdateDate datetime(6) ,
	name varchar(64) ,
	vcid varchar(64) not null,
	valid bit ,
	createDate datetime(6) ,
	modifyAble bit ,
	remark varchar(512) ,
	primary key(id)
);
