/*****************************************************************************
-- OPER_ORGANIZATION : 
*****************************************************************************/
drop table if exists OPER_ORGANIZATION;
create table OPER_ORGANIZATION(
	id varchar(64) not null,
	code varchar(64) not null,
	fullName varchar(256) ,
	fullAddress varchar(256) ,
	name varchar(64) not null,
	type varchar(64) not null,
	vcid varchar(64) not null,
	alias varchar(64) ,
	chiefId varchar(64) ,
	chiefType varchar(64) ,
	valid bit not null,
	parentId varchar(64) not null,
	remark varchar(256) ,
	address varchar(64) ,
	companyId varchar(64) ,
	districtId varchar(64) ,
	primary key(id)
);
