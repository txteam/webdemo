/*****************************************************************************
-- CI_CONTENT_INFO : 
*****************************************************************************/
drop table if exists CI_CONTENT_INFO;
create table CI_CONTENT_INFO(
	id varchar(64) not null,
	fileId varchar(64) ,
	fileUrl varchar(255) ,
	lastUpdateDate datetime(6) ,
	lastUpdateOperatorId varchar(64) ,
	linkUrl varchar(255) ,
	name varchar(64) ,
	typeCode varchar(64) ,
	levelCode varchar(64) ,
	orderIndex integer ,
	title varchar(255) ,
	valid bit ,
	createDate datetime(6) ,
	createOperatorId varchar(64) ,
	remark varchar(512) ,
	content varchar(255) ,
	categoryCode varchar(64) ,
	keywords varchar(255) ,
	primary key(id)
);
