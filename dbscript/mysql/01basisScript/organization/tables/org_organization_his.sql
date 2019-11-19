/*****************************************************************************
-- ORG_ORGANIZATION : 
*****************************************************************************/
drop table if exists ORG_ORGANIZATION_HIS;
create table ORG_ORGANIZATION_HIS(
	id varchar(64) not null,			-- 主键
	vcid varchar(64) not null,			-- 组织虚中心id
	code varchar(64) not null,			-- 组织编码
	parentId varchar(64) not null,		-- 父组织id
	companyId varchar(64),
	departmentId varchar(64),
	name varchar(64) not null,			-- 组织名称
	alias varchar(64),					-- 组织别名
	type varchar(64) not null,			-- 组织类型
	fullName varchar(256) ,				-- 组织全名（含上级组织）
	districtId varchar(64),
	address varchar(64) ,
	fullAddress varchar(256) ,
	chiefId varchar(64) ,
	chiefType varchar(64) ,
	valid bit not null,
	remark varchar(256) ,
	createDate datetime(6) not null default now(6),
	lastUpdateDate datetime(6) not null default now(6),
	primary key(id)
);
