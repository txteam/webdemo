/*****************************************************************************
-- ORG_ORGANIZATION : 
*****************************************************************************/
drop table if exists ORG_ORGANIZATION;
create table ORG_ORGANIZATION(
	id varchar(64) not null,			
	vcid varchar(64) not null,			
	type varchar(64) not null,			
	code varchar(64) not null,		
	parentId varchar(64),
	companyId varchar(64),
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
create unique index idx_org_organization_01 on ORG_ORGANIZATION(code,vcid);
create index idx_org_organization_02 on ORG_ORGANIZATION(parentId);
create index idx_org_organization_04 on ORG_ORGANIZATION(companyId);
create index idx_org_organization_05 on ORG_ORGANIZATION(departmentId);
create index idx_org_organization_06 on ORG_ORGANIZATION(districtId);
