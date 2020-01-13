-- tableInitializers: 
-- tables
-- ----------table:bd_plugin_instance---------- 


-- initdata

-- tables
-- ----------table:table_sec_role_type---------- 

-- ----------table:table_sec_role---------- 

-- ----------table:table_sec_roleref---------- 

-- ----------table:table_sec_roleref_his---------- 


-- initdata

-- tables
-- ----------table:sec_jwt_signing_key---------- 


-- initdata

-- tables
-- ----------table:table_sec_auth_type---------- 

-- ----------table:sec_auth---------- 

-- ----------table:sec_authref---------- 

-- ----------table:table_sec_authref_his---------- 


-- initdata

-- tables
-- ----------table:bd_data_dict---------- 


-- initdata

-- tables
-- ----------table:oper_sec_operate_log---------- 

-- ----------table:OPER_LOGIN_LOG---------- 


-- initdata

-- tables

-- initdata

-- tables
-- ----------bd_config_context---------- 

-- ----------bd_config_context_his---------- 


-- initdata

-- tables
drop table if exists bd_bankinfo;
create table bd_bankinfo(
	id varchar(64) not null,
	code varchar(64) not null,
	valid bit not null default 1,
	modifyAble bit not null default 1,
	name varchar(64) not null,
	aliases varchar(256) not null,
	logoFileId varchar(64),
	logoUrl varchar(256),
	personalLoginUrl varchar(256),
	institutionLoginUrl varchar(256),
	createDate datetime not null default now(),
	lastUpdateDate datetime not null default now(),
	remark varchar(2000),
	primary key(id)
);
create index idx_bd_bankinfo_00 on bd_bankinfo(code);

/*****************************************************************************
			表：bd_industry
*****************************************************************************/
drop table if exists bd_industry;
create table bd_industry(
	id varchar(64) not null,
	parentId varchar(64),
	level integer not null default 0,
	code varchar(64) not null,
	name varchar(64) not null,
	valid bit not null default 1, 
	modifyAble bit not null default 1,
	fullName varchar(64),
	remark varchar(512),
	lastUpdateDate datetime not null default now(),
	createDate datetime not null default now(),
	primary key(id)
);
create unique index idx_bd_industry_00 on bd_industry(code);

/*****************************************************************************
			表：bd_ipaddress_reposity
*****************************************************************************/
drop table if exists bd_ipaddress_reposity;
create table bd_ipaddress_reposity(
	id varchar(64) not null,
	code varchar(64) not null,
	name varchar(64),
	valid bit not null default 1,
	modifyAble bit not null default 1,
	districtId varchar(64) not null,
	continentName varchar(64),
	nationName varchar(64),
	provinceName varchar(64),
	cityName varchar(64),
	countyName varchar(64),
	en varchar(255),
	enName varchar(64),
	telecomOperatorId varchar(64),
	remark varchar(500),
	latitude decimal(32,16),
	longitude decimal(32,16),
	lastUpdateDate datetime not null default now(),
	createDate datetime not null default now(),
	primary key(id)
);
create index idx_ipaddress_reposity_00 on bd_ipaddress_reposity(code);
/*****************************************************************************
-- CLI_CLIENT_ROLE : 
*****************************************************************************/
drop table if exists CLI_CLIENT_ROLE;
create table CLI_CLIENT_ROLE(
	id varchar(64) not null,
	code varchar(64) not null,
	lastUpdateDate datetime(6) not null,
	name varchar(64) not null,
	valid bit not null,
	createDate datetime(6) not null,
	modifyAble bit not null,
	remark varchar(512) ,
	primary key(id)
);

/*****************************************************************************
-- CLI_CLIENT_SOURCE : 
*****************************************************************************/
drop table if exists CLI_CLIENT_SOURCE;
create table CLI_CLIENT_SOURCE(
	id varchar(64) not null,
	code varchar(64) not null,
	name varchar(64) not null,
	remark varchar(512),
	valid bit not null default 1,
	modifyAble bit not null default 1,
	lastUpdateDate datetime(6) not null default now(6),
	createDate datetime(6) not null default now(6),
	primary key(id)
);

/*****************************************************************************
-- CLI_PROMOTION_CHANNEL : 
*****************************************************************************/
drop table if exists CLI_PROMOTION_CHANNEL;
create table CLI_PROMOTION_CHANNEL(
	id varchar(64) not null,
	code varchar(64) not null,
	lastUpdateDate datetime(6) not null,
	name varchar(64) not null,
	valid bit not null,
	createDate datetime(6) not null,
	modifyAble bit not null,
	remark varchar(512) ,
	primary key(id)
);

/*****************************************************************************
-- CLI_CLIENT_SOURCE : 
*****************************************************************************/
drop table if exists CLI_CLIENT_SOURCE;
create table CLI_CLIENT_SOURCE(
	id varchar(64) not null,
	code varchar(64) not null,
	lastUpdateDate datetime(6) not null,
	name varchar(64) not null,
	valid bit not null,
	createDate datetime(6) not null,
	modifyAble bit not null,
	remark varchar(512) ,
	primary key(id)
);

/*****************************************************************************
-- CLI_PROMOTION_CHANNEL : 
*****************************************************************************/
drop table if exists CLI_PROMOTION_CHANNEL;
create table CLI_PROMOTION_CHANNEL(
	id varchar(64) not null,
	code varchar(64) not null,
	lastUpdateDate datetime(6) not null,
	name varchar(64) not null,
	valid bit not null,
	createDate datetime(6) not null,
	modifyAble bit not null,
	remark varchar(512) ,
	primary key(id)
);

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

/*****************************************************************************
-- CI_CONTENT_INFO : 
*****************************************************************************/
drop table if exists CI_CONTENT_INFO;
create table CI_CONTENT_INFO(
	id varchar(64) not null,
	vcid varchar(64) not null,
	type varchar(64) not null,
	categoryId varchar(64) not null,
	levelId varchar(64) not null,
	keywords varchar(256),
	name varchar(128),
	title varchar(128),
	content varchar(1000),
	attachment varchar(1000),
	attributes varchar(1000),
	author varchar(64),
	publish bit not null default 1,
	publishDate datetime not null default now(),
	top bit not null default 0,
	topDate datetime not null default now(),
	remark varchar(256),
	effectiveDate datetime not null default now(),
	expiryDate datetime,
	valid bit not null default 1,
	orders integer not null default 0,
	lastUpdateDate datetime not null default now(),
	createDate datetime not null default now(),
	lastUpdateOperatorId varchar(64),
	createOperatorId varchar(64),
	primary key(id)
);
create index idx_content_info_00 on CI_CONTENT_INFO(categoryId);
create index idx_content_info_01 on CI_CONTENT_INFO(levelId);
create index idx_content_info_02 on CI_CONTENT_INFO(type);
create index idx_content_info_03 on CI_CONTENT_INFO(title);
create index idx_content_info_04 on CI_CONTENT_INFO(name);
create index idx_content_info_05 on CI_CONTENT_INFO(createDate);

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

/*****************************************************************************
-- TEST_DEMO : 
*****************************************************************************/
drop table if exists TEST_DEMO;
create table TEST_DEMO(
	id varchar(64) not null,
	code varchar(32) not null,
	lastUpdateDate datetime(6) not null,
	lastUpdateOperatorId varchar(64) ,
	name varchar(32) not null,
	testInt integer ,
	testLong bigint ,
	testInteger integer ,
	testBigDecimal decimal(32,8) ,
	type varchar(64) ,
	superInt integer ,
	superBoolean bit ,
	superDemoCode varchar(64) ,
	superBigDeceimal decimal(32,8) ,
	superIntegerObject integer ,
	superIsBooleanObject bit ,
	valid bit not null,
	createDate datetime(6) not null,
	createOperatorId varchar(64) ,
	expiryDate datetime(6) ,
	modifyAble bit ,
	parentId varchar(64) ,
	remark varchar(512) ,
	nested1Id varchar(64) ,
	nested2Code varchar(64) ,
	success bit ,
	effictiveDate datetime(6) ,
	attributes varchar(255) ,
	description varchar(512) ,
	primary key(id)
);

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

/*****************************************************************************
-- OPER_EMPLOYEE_INFO : 
*****************************************************************************/
drop table if exists OPER_EMPLOYEE_INFO;
create table OPER_EMPLOYEE_INFO(
	id varchar(64) not null,
	operatorId varchar(64) not null,
	number varchar(64) not null,
	idCardType varchar(64),
	idCardNumber varchar(64),
	realName varchar(64),
	mobileNumber varchar(64),
	email varchar(64),
	sex varchar(64),
	birthday datetime(6),
	entryDate datetime(6),
	official bit default 0,
	officialDate datetime(6),
	leaving bit default 0,
	leavingDate datetime(6),
	createDate datetime(6) not null default now(6),
	lastUpdateDate datetime(6) not null default now(6),
	primary key(id)
);
create unique index idx_employee_info_00 on OPER_EMPLOYEE_INFO(operatorId);
create unique index idx_employee_info_01 on OPER_EMPLOYEE_INFO(number);
drop table if exists OPER_EMPLOYEE_INFO_HIS;
CREATE TABLE OPER_EMPLOYEE_INFO_HIS(
	id varchar(64) not null,
	operatorId varchar(64) not null,
	number varchar(64) not null,
	idCardType varchar(64),
	idCardNumber varchar(64),
	realName varchar(64),
	mobileNumber varchar(64),
	email varchar(64),
	sex varchar(64),
	birthday datetime(6),
	entryDate datetime(6),
	official bit default 0,
	officialDate datetime(6),
	leaving bit default 0,
	leavingDate datetime(6),
	createDate datetime(6) not null default now(6),
	lastUpdateDate datetime(6) not null default now(6),
	primary key(id)
);


/*****************************************************************************
-- OPER_OPERATOR : 
*****************************************************************************/
drop table if exists OPER_OPERATOR;
create table OPER_OPERATOR(
	id varchar(64) not null,
	vcid varchar(64) not null,
	username varchar(64) not null,
	usernameChangeCount int not null default 0,
	usernameChangeAble bit not null default 1,
	name varchar(64),
	organizationId varchar(64) not null,
	mainPostId varchar(64),
	password varchar(64) not null,
	historyPwd varchar(64),
	pwdErrCount integer not null default 0,
	pwdUpdateDate datetime(6),
	examinePwd varchar(64),
	locked bit not null default 0,
	modifyAble bit not null default 1,
	valid bit not null default 1,
	invalidDate datetime(6),
	lastUpdateDate datetime(6) not null default now(6),
	createDate datetime(6) not null default now(6),
	primary key(id)
);
create unique index idx_oper_oper_00 on oper_operator(username);
create index idx_oper_oper_01 on oper_operator(vcid);
create index idx_oper_oper_02 on oper_operator(organizationId);
create index idx_oper_oper_03 on oper_operator(createDate);

drop table if exists OPER_OPERATOR_HIS;
create table OPER_OPERATOR_HIS(
	id varchar(64) not null,
	vcid varchar(64) not null,
	username varchar(64) not null,
	usernameChangeCount int not null default 0,
	usernameChangeAble bit not null default 1,
	name varchar(64),
	organizationId varchar(64) not null,
	mainPostId varchar(64),
	password varchar(64) not null,
	historyPwd varchar(64),
	pwdErrCount integer not null default 0,
	pwdUpdateDate datetime(6),
	examinePwd varchar(64),
	locked bit not null default 0,
	modifyAble bit not null default 1,
	valid bit not null default 1,
	invalidDate datetime(6),
	lastUpdateDate datetime(6) not null default now(6),
	createDate datetime(6) not null default now(6),
	primary key(id)
);

/*****************************************************************************
-- OPER_OPERATOR_REF : 
*****************************************************************************/
drop table if exists OPER_OPERATOR_REF;
create table OPER_OPERATOR_REF(
	id varchar(64) not null,
	operatorId varchar(64) not null,
	refId varchar(64) not null,
	refType varchar(64) not null,
	effectiveDate datetime(6) not null default now(6),
	expiryDate datetime(6) ,
	lastUpdateDate datetime(6) not null default now(6),
	lastUpdateOperatorId varchar(64) ,
	createDate datetime(6) not null default now(6),
	createOperatorId varchar(64) ,
	primary key(id)
);
CREATE UNIQUE INDEX idx_oper_operref_00 ON OPER_OPERATOR_REF(refId,refType,operatorId);
CREATE INDEX idx_oper_operref_01 ON OPER_OPERATOR_REF(operatorId);
CREATE INDEX idx_oper_operref_02 ON OPER_OPERATOR_REF(effectiveDate);
CREATE INDEX idx_oper_operref_03 ON OPER_OPERATOR_REF(expiryDate);
CREATE INDEX idx_oper_operref_04 ON OPER_OPERATOR_REF(createDate);

drop table if exists oper_operator_ref_his;
CREATE TABLE oper_operator_ref_his(
	id varchar(64) not null,
	operatorId varchar(64) not null,
	refId varchar(64) not null,
	refType varchar(64) not null,
	effectiveDate datetime(6) not null,
	expiryDate datetime(6) ,
	lastUpdateDate datetime(6) not null,
	lastUpdateOperatorId varchar(64) ,
	createDate datetime(6) not null,
	createOperatorId varchar(64) ,
	primary key(id)
);

/*****************************************************************************
-- OPER_ROLE : 
*****************************************************************************/
drop table if exists OPER_ROLE;
create table OPER_ROLE(
	id varchar(64) not null,
	name varchar(64) not null,
	vcid varchar(64) not null,
	parentId varchar(64),
	catalogId varchar(64) ,
	valid bit not null default 1,
	modifyAble bit not null default 1,
	remark varchar(512) ,
	createDate datetime(6) not null default now(6),
	lastUpdateDate datetime(6) not null default now(6),
	primary key(id)
);
create unique index idx_role_01 on OPER_ROLE(name,vcid);

/*****************************************************************************
-- OPER_ROLE_CATALOG : 
*****************************************************************************/
drop table if exists OPER_ROLE_CATALOG;
create table OPER_ROLE_CATALOG(
	id varchar(64) not null,
	name varchar(64) not null,
	vcid varchar(64) not null,
	valid bit not null default 1,
	modifyAble bit not null default 1,
	parentId varchar(64) ,
	remark varchar(512),
	lastUpdateDate datetime(6) not null default now(6),
	createDate datetime(6) not null default now(6),
	primary key(id)
);

drop table if exists OPER_ROLE_HIS;
create table OPER_ROLE_HIS(
	id varchar(64) not null,
	name varchar(64) not null,
	vcid varchar(64) not null,
	parentId varchar(64),
	catalogId varchar(64) ,
	valid bit not null default 1,
	modifyAble bit not null default 1,
	remark varchar(512) ,
	createDate datetime(6) not null default now(6),
	lastUpdateDate datetime(6) not null default now(6),
	primary key(id)
);

/*****************************************************************************
-- OPER_SECURITY_ACCOUNT : 
*****************************************************************************/
drop table if exists OPER_SECURITY_ACCOUNT;
create table OPER_SECURITY_ACCOUNT(
	id varchar(64) not null,
	operatorId varchar(64) not null,
	mobileNumber varchar(64),
	mobileBinding bit not null default 0,
	mobileLoginEnable bit not null default 0,
	email varchar(64),
	emailBinding bit not null default 0,
	idCardType varchar(64),
	idCardNumber varchar(64),
	idCardExpiryDate datetime(6),
	realNameAuthenticated bit not null default 0,
	realNameErrCount integer,
	realNameLastErrDate datetime(6),
	lastUpdateDate datetime(6) not null default now(6),
	createDate datetime(6) not null default now(6),
	primary key(id)
);
create unique index IDX_SECURITY_ACCOUNT_00 on OPER_SECURITY_ACCOUNT(operatorId);

/*****************************************************************************
-- OPER_SOCIAL_ACCOUNT : 
*****************************************************************************/
drop table if exists OPER_SOCIAL_ACCOUNT;
create table OPER_SOCIAL_ACCOUNT(
	id varchar(64) not null,
	operatorId varchar(64) not null,
	type varchar(64) not null,
	uniqueId varchar(64) not null,
	username varchar(64),
	headImgUrl varchar(256),
	attributes varchar(1000),
	lastUpdateDate datetime(6) not null default now(6),
	createDate datetime(6) not null default now(6),
	primary key(id)
);
create unique index IDX_SOCIAL_ACCOUNT_00 on OPER_SOCIAL_ACCOUNT(uniqueId,type);
create unique index IDX_SOCIAL_ACCOUNT_01 on OPER_SOCIAL_ACCOUNT(operatorId,type);
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
create index idx_org_organization_06 on ORG_ORGANIZATION(districtId);

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

/*****************************************************************************
-- ORG_POST : 
*****************************************************************************/
drop table if exists ORG_POST;
create table ORG_POST(
	id varchar(64) not null,
	vcid varchar(64) not null,
	organizationId varchar(64) not null,
	parentId varchar(64),
	code varchar(64) not null,
	name varchar(64) not null,
	fullName varchar(256) ,
	remark varchar(512) ,
	valid bit not null default 1,
	createDate datetime(6) not null default now(6),
	lastUpdateDate datetime(6) not null default now(6),
	primary key(id)
);
create index idx_ORG_post_00 on ORG_POST(parentId);
create unique index idx_ORG_post_01 on ORG_POST(code,vcid);
/*****************************************************************************
-- ORG_POST : 
*****************************************************************************/
drop table if exists ORG_POST;
create table ORG_POST(
	id varchar(64) not null,
	vcid varchar(64) not null,
	organizationId varchar(64) not null,
	parentId varchar(64),
	code varchar(64) not null,
	name varchar(64) not null,
	fullName varchar(256) ,
	remark varchar(512) ,
	valid bit not null default 1,
	createDate datetime(6) not null default now(6),
	lastUpdateDate datetime(6) not null default now(6),
	primary key(id)
);

/*****************************************************************************
-- VC_VIRTUAL_CENTER : 
*****************************************************************************/
drop table if exists VC_VIRTUAL_CENTER;
create table VC_VIRTUAL_CENTER(
	id varchar(64) not null,
	code varchar(64) not null,
	name varchar(64) not null,
	valid bit not null,
	createDate datetime(6) not null,
	lastUpdateDate datetime(6) not null,
	modifyAble bit not null,
	parentId varchar(64) ,
	remark varchar(512) ,
	primary key(id)
);

-- views
-- functions
-- procedures
-- sequences
-- initdata

INSERT INTO OPER_OPERATOR(id,vcid,organizationId,username,password,modifyAble,name)
	values('123456','JT','JT','admin','E10ADC3949BA59ABBE56E057F20F883E',0,'超级管理员');
INSERT INTO OPER_OPERATOR(id,vcid,organizationId,username,password,modifyAble,name)
	values(REPLACE(UUID(),"-",""),'JT','JT','pqy','E10ADC3949BA59ABBE56E057F20F883E',1,'彭清杨');
INSERT INTO OPER_OPERATOR(id,vcid,organizationId,username,password,modifyAble,name)
	values(REPLACE(UUID(),"-",""),'JT','JT','zlk','E10ADC3949BA59ABBE56E057F20F883E',1,'张礼科');
INSERT INTO OPER_OPERATOR(id,vcid,organizationId,username,password,modifyAble,name)
	values(REPLACE(UUID(),"-",""),'JT','JT','zw','E10ADC3949BA59ABBE56E057F20F883E',1,'张威');
INSERT INTO OPER_OPERATOR(id,vcid,organizationId,username,password,modifyAble,name)
	values(REPLACE(UUID(),"-",""),'JT','JT','xrx','E10ADC3949BA59ABBE56E057F20F883E',1,'徐茹霞');
commit;
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('JT', 'JT', 'GROUP_COMPANY', 'JT', '', 'JT', '集团公司', '', '集团公司', NULL, NULL, NULL, '', '集团公司', '2019-11-10 22:47:34.364000', '2019-11-10 22:47:34.364000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6e63121c016e6366e2570000', 'JTZGS2', 'COMPANY', 'JTFGS2', 'JT', '4028819d6e63121c016e6366e2570000', '集团子公司2', '', '集团子公司2', NULL, NULL, NULL, '', '', '2019-11-13 14:15:17.335000', '2019-11-13 14:15:17.335000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6e63121c016e636757e70001', 'JTZGS2', 'DEPARTMENT', 'JTFGS2_CW', '4028819d6e63121c016e6366e2570000', NULL, '财务部', '', '集团子公司2_财务部', NULL, NULL, NULL, '', '11111', '2019-11-13 14:15:47.431000', '2019-11-28 11:46:14.317000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6eb00d4d016eb01f53180002', 'JT', 'BRANCH_DEPARTMENT', 'JT_XTYF_CPGH', '40289f596e606bdd016e607079be0000', 'JT', '产品规划部', '', '集团公司_系统研发部_产品规划部', NULL, NULL, NULL, '', '', '2019-11-28 11:47:53.240000', '2019-11-28 12:33:25.616000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e607079be0000', 'JT', 'DEPARTMENT', 'JT_XTYF', 'JT', 'JT', '系统研发部', '', '集团公司_系统研发部', NULL, NULL, NULL, '', '', '2019-11-13 00:26:54.270000', '2019-11-13 00:26:54.270000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e6073ba0a0001', 'JT', 'DEPARTMENT', 'JT_FXGL', 'JT', 'JT', '风险管理部', '', '集团公司_风险管理部', NULL, NULL, NULL, '', '', '2019-11-13 00:30:27.338000', '2019-11-13 00:30:27.338000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e6074e1de0002', 'JT', 'BRANCH_DEPARTMENT', 'JT_XTYF_CS', '40289f596e606bdd016e607079be0000', 'JT', '测试部', '', '集团公司_系统研发部_测试部', NULL, NULL, NULL, '', '', '2019-11-13 00:31:43.070000', '2019-11-28 11:54:48.411000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e6075e9c00003', 'JT', 'DEPARTMENT', 'JT_CW', 'JT', 'JT', '财务部', '', '集团公司_财务部', NULL, NULL, NULL, '', '财务部', '2019-11-13 00:32:50.624000', '2019-11-28 01:58:25.016000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e6076c0ae0004', 'CSGS1', 'COMPANY', 'CS1', '', '40289f596e606bdd016e6076c0ae0004', '测试公司1', '', '测试公司1', NULL, NULL, NULL, '', '', '2019-11-13 00:33:45.646000', '2019-11-13 00:33:45.646000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e607709e10005', 'CSGS2', 'COMPANY', 'CS2', '', '40289f596e606bdd016e607709e10005', '测试公司2', '', '测试公司2', NULL, NULL, NULL, '', '', '2019-11-13 00:34:04.385000', '2019-11-13 00:34:04.385000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e6077c4af0006', 'JTZGS1', 'COMPANY', 'JTFGS1', 'JT', '40289f596e606bdd016e6077c4af0006', '集团子公司1', '', '集团子公司1', NULL, NULL, NULL, '', '', '2019-11-13 00:34:52.207000', '2019-11-13 00:34:52.207000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e607843220007', 'JTZGS1', 'DEPARTMENT', 'JTFGS1_CW', '40289f596e606bdd016e6077c4af0006', '40289f596e606bdd016e6077c4af0006', '财务部', '', '集团子公司1_财务部', NULL, NULL, NULL, '', '', '2019-11-13 00:35:24.578000', '2019-11-13 00:35:24.578000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e6078b7f90008', 'CSGS2', 'DEPARTMENT', 'CS2_CW', '40289f596e606bdd016e607709e10005', '40289f596e606bdd016e607709e10005', '财务部', '', '测试公司2_财务部', NULL, NULL, NULL, '', '', '2019-11-13 00:35:54.489000', '2019-11-13 00:35:54.489000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e607940a90009', 'CSGS1', 'DEPARTMENT', 'CS1_CW', '40289f596e606bdd016e6076c0ae0004', '40289f596e606bdd016e6076c0ae0004', '财务部', '', '测试公司1_财务部', NULL, NULL, NULL, '', '', '2019-11-13 00:36:29.481000', '2019-11-13 00:36:29.481000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e6089f7e3000a', 'JT', 'DEPARTMENT', 'JT_PPYY', 'JT', 'JT', '品牌运营部', '', '集团公司_品牌运营部', NULL, NULL, NULL, '', '', '2019-11-13 00:54:44.963000', '2019-11-13 00:54:44.963000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e608a7f86000b', 'JT', 'DEPARTMENT', 'JT_RLZY', 'JT', 'JT', '人力资源管理部', '', '集团公司_人力资源管理部', NULL, NULL, NULL, '', '', '2019-11-13 00:55:19.686000', '2019-11-13 00:55:19.686000');
commit;
INSERT INTO `webdemo_new`.`org_post` (`id`, `parentId`, `code`, `vcid`, `name`, `fullName`, `valid`, `remark`, `organizationId`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6e7d6029016e7d61d0e10000', '', '财务总监', 'JT', 'JT_CWZJ', NULL, '', '', 'JT', '0000-00-00 00:00:00.000000', '0000-00-00 00:00:00.000000');
INSERT INTO `webdemo_new`.`org_post` (`id`, `parentId`, `code`, `vcid`, `name`, `fullName`, `valid`, `remark`, `organizationId`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6e7d6029016e7d626e3c0001', '', '技术总监', 'JT', 'JT_JSZJ', NULL, '', '', 'JT', '0000-00-00 00:00:00.000000', '0000-00-00 00:00:00.000000');
INSERT INTO `webdemo_new`.`org_post` (`id`, `parentId`, `code`, `vcid`, `name`, `fullName`, `valid`, `remark`, `organizationId`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6e7d6029016e7d635f870002', '4028819d6e7d6029016e7d61d0e10000', '财务部经理', 'JT', 'JT_CW_JL', NULL, '', '', '40289f596e606bdd016e6075e9c00003', '0000-00-00 00:00:00.000000', '0000-00-00 00:00:00.000000');
commit;
INSERT INTO vc_virtual_center (`id`, `code`, `name`, `valid`, `createDate`, `lastUpdateDate`, `modifyAble`, `parentId`, `remark`) VALUES ('JT', 'JT', '集团公司', '', '2019-11-10 11:43:42.580000', '2019-11-10 11:43:42.580000', '\0', NULL, NULL);
INSERT INTO vc_virtual_center (`id`, `code`, `name`, `valid`, `createDate`, `lastUpdateDate`, `modifyAble`, `parentId`, `remark`) VALUES ('JTZGS1', 'JTZGS1', '集团子公司1', '', '2019-11-10 11:43:42.754000', '2019-11-10 11:43:43.111000', '\0', 'JT', NULL);
INSERT INTO vc_virtual_center (`id`, `code`, `name`, `valid`, `createDate`, `lastUpdateDate`, `modifyAble`, `parentId`, `remark`) VALUES ('JTZGS2', 'JTZGS2', '集团子公司2', '', '2019-11-10 11:43:42.841000', '2019-11-10 11:43:43.246000', '\0', 'JT', NULL);
INSERT INTO vc_virtual_center (`id`, `code`, `name`, `valid`, `createDate`, `lastUpdateDate`, `modifyAble`, `parentId`, `remark`) VALUES ('CSGS1', 'CSGS1', '测试公司1', '', '2019-11-10 11:43:42.931000', '2019-11-10 11:43:42.931000', '\0', NULL, NULL);
INSERT INTO vc_virtual_center (`id`, `code`, `name`, `valid`, `createDate`, `lastUpdateDate`, `modifyAble`, `parentId`, `remark`) VALUES ('CSGS2', 'CSGS2', '测试公司2', '', '2019-11-10 11:43:43.021000', '2019-11-10 11:43:43.021000', '\0', NULL, NULL);
commit;
-- foreignkey
ALTER TABLE OPER_EMPLOYEE_INFO ADD CONSTRAINT FK_EMPLOYEE_01 FOREIGN KEY(operatorId) REFERENCES OPER_OPERATOR(id);

ALTER TABLE OPER_OPERATOR ADD CONSTRAINT FK_OPERATOR_01 FOREIGN KEY(vcid) REFERENCES VC_VIRTUAL_CENTER(id);
ALTER TABLE OPER_OPERATOR ADD CONSTRAINT FK_OPERATOR_02 FOREIGN KEY(organizationId) REFERENCES ORG_ORGANIZATION(id);
ALTER TABLE OPER_OPERATOR ADD CONSTRAINT FK_OPERATOR_03 FOREIGN KEY(mainPostId) REFERENCES ORG_POST(id);

ALTER TABLE ORG_ORGANIZATION ADD CONSTRAINT FK_ORGANIZATION_01 FOREIGN KEY(parentId) REFERENCES ORG_ORGANIZATION(id);
ALTER TABLE ORG_ORGANIZATION ADD CONSTRAINT FK_ORGANIZATION_02 FOREIGN KEY(vcid) REFERENCES VC_VIRTUAL_CENTER(id);

ALTER TABLE ORG_POST ADD CONSTRAINT FK_POST_01 FOREIGN KEY(parentId) REFERENCES ORG_POST(id);
ALTER TABLE ORG_POST ADD CONSTRAINT FK_POST_02 FOREIGN KEY(organizationId) REFERENCES ORG_ORGANIZATION(id);
ALTER TABLE ORG_POST ADD CONSTRAINT FK_POST_03 FOREIGN KEY(vcid) REFERENCES VC_VIRTUAL_CENTER(id);

-- jobs
-- triggers
