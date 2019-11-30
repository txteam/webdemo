/*****************************************************************************
-- OPER_ROLE_CATALOG : 
*****************************************************************************/
drop table if exists OPER_ROLE_CATALOG;
create table OPER_ROLE_CATALOG(
	id varchar(64) not null,
	lastUpdateDate datetime(6) ,
	name varchar(64) ,
	vcid varchar(64) ,
	valid bit ,
	createDate datetime(6) ,
	modifyAble bit ,
	parentId varchar(64) ,
	remark varchar(512) ,
	primary key(id)
);
