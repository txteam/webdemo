/*****************************************************************************
-- CI_CONTENT_TYPE : 
*****************************************************************************/
drop table if exists CI_CONTENT_TYPE;
create table CI_CONTENT_TYPE(
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
	primary key(id)
);
