/*****************************************************************************
-- CI_CONTENT_LEVEL : 
*****************************************************************************/
drop table if exists CI_CONTENT_LEVEL;
create table CI_CONTENT_LEVEL(
	id varchar(64) not null,
	code varchar(64) ,
	lastUpdateDate datetime(6) ,
	lastUpdateOperatorId varchar(64) ,
	name varchar(64) ,
	valid bit ,
	createDate datetime(6) ,
	createOperatorId varchar(64) ,
	modifyAble bit ,
	remark varchar(512) ,
	categoryCode varchar(64) ,
	primary key(id)
);
