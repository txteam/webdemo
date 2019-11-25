/*****************************************************************************
-- ORG_ORGANIZATION : 
*****************************************************************************/
drop table if exists ORG_ORGANIZATION_HIS;
create table ORG_ORGANIZATION_HIS(
	id varchar(64) not null,			
	vcid varchar(64) not null,			
	type varchar(64) not null,			
	code varchar(64) not null,		
	parentId varchar(64),
	companyId varchar(64),
	departmentId varchar(64),
	name varchar(64) not null,
	alias varchar(64),
	fullName varchar(256),
	districtId varchar(64),
	address varchar(64) ,
	fullAddress varchar(256) ,
	valid bit not null,
	remark varchar(512) ,
	createDate datetime(6) not null default now(6),
	lastUpdateDate datetime(6) not null default now(6),
	primary key(id)
);
