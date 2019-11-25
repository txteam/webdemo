/*****************************************************************************
-- CI_CONTENT_CATEGORY : 
*****************************************************************************/
drop table if exists CI_CONTENT_CATEGORY;
create table CI_CONTENT_CATEGORY(
	id varchar(64) not null,
	code varchar(64) ,
	lastUpdateDate datetime(6) ,
	lastUpdateOperatorId varchar(64) ,
	name varchar(64) ,
	typeCode varchar(64) ,
	level integer ,
	orderIndex integer ,
	valid bit ,
	createDate datetime(6) ,
	createOperatorId varchar(64) ,
	modifyAble bit ,
	parentId varchar(64) ,
	remark varchar(512) ,
	primary key(id)
);
