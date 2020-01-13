/*****************************************************************************
-- NoticeCatalog : 
*****************************************************************************/
drop table if exists NoticeCatalog;
create table NoticeCatalog(
	id varchar(64) not null,
	code varchar(64) ,
	lastUpdateDate datetime(6) ,
	name varchar(64) ,
	vcid varchar(64) ,
	valid bit ,
	createDate datetime(6) ,
	modifyAble bit ,
	remark varchar(512) ,
	primary key(id)
);
