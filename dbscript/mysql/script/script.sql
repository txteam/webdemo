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
-- TestM1 : 
*****************************************************************************/
drop table if exists TestM1;
create table TestM1(
	id varchar(64) not null,
	code varchar(32) not null,
	lastUpdateDate datetime(6) not null,
	lastUpdateOperatorId varchar(64) ,
	name varchar(32) not null,
	testInt integer ,
	testLong bigint ,
	testBigDecimal decimal(32,8) ,
	type varchar(64) ,
	valid bit not null,
	createDate datetime(6) not null,
	createOperatorId varchar(64) ,
	expiryDate datetime(6) ,
	modifyAble bit ,
	parentId varchar(64) ,
	remark varchar(512) ,
	success bit ,
	effictiveDate datetime(6) ,
	attributes varchar(255) ,
	description varchar(512) ,
	primary key(id)
);

/*****************************************************************************
-- OPER_EMPLOYEE_INFO : 
*****************************************************************************/
drop table if exists OPER_EMPLOYEE_INFO;
create table OPER_EMPLOYEE_INFO(
	id varchar(64) not null,
	operatorId varchar(64) not null,
	code varchar(64) not null,
	name varchar(64) ,
	idCardType varchar(64),
	idCardNumber varchar(64),
	birthday datetime(6) ,
	age integer ,
	sex integer ,
	email varchar(255) ,
	entryDate datetime(6) ,
	phoneNumber varchar(64) ,
	leaving bit default 0,
	leavingDate datetime(6) ,
	official bit default 0,
	officialDate datetime(6),
	createDate datetime(6) not null default now(6),
	lastUpdateDate datetime(6) not null default now(6),
	primary key(id)
);

drop table if exists OPER_EMPLOYEE_INFO_HIS;
CREATE TABLE OPER_EMPLOYEE_INFO_HIS(
	id varchar(64) not null,
	operatorId varchar(64) not null,
	code varchar(64) not null,
	name varchar(64) ,
	idCardType varchar(64),
	idCardNumber varchar(64),
	birthday datetime(6) ,
	age integer ,
	sex integer ,
	email varchar(255) ,
	entryDate datetime(6) ,
	phoneNumber varchar(64) ,
	leaving bit default 0,
	leavingDate datetime(6) ,
	official bit default 0,
	officialDate datetime(6),
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
	organizationId varchar(64) not null,
	loginName varchar(64) not null,
	userName varchar(64),
	pwdErrCount integer not null default 0,
	pwdUpdateDate datetime(6) not null default now(6),
	valid bit not null default 1,
	locked bit not null default 0,
	password varchar(64) not null,
	historyPwd varchar(64) ,
	examinePwd varchar(64) ,
	mainPostId varchar(64) ,
	invalidDate datetime(6) ,
	lastUpdateDate datetime(6) not null default now(6),
	createDate datetime(6) not null default now(6),
	primary key(id)
);
create unique index idx_oper_oper_00 on oper_operator(loginName);
create index idx_oper_oper_01 on oper_operator(vcid);
create index idx_oper_oper_02 on oper_operator(organizationId);

drop table if exists OPER_OPERATOR_HIS;
CREATE TABLE OPER_OPERATOR_HIS(
	id varchar(64) not null,
	vcid varchar(64) not null,
	organizationId varchar(64) not null,
	loginName varchar(64) not null,
	userName varchar(64),
	pwdErrCount integer not null default 0,
	pwdUpdateDate datetime(6) not null default now(6),
	valid bit not null default 1,
	locked bit not null default 0,
	password varchar(64) not null,
	historyPwd varchar(64) ,
	examinePwd varchar(64) ,
	mainPostId varchar(64) ,
	invalidDate datetime(6) ,
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
CREATE UNIQUE INDEX idx_oper_operref_00 ON oper_operator_ref(refId,refType,operatorId);
CREATE INDEX idx_oper_operref_01 ON oper_operator_ref(operatorId);
CREATE INDEX idx_oper_operref_02 ON oper_operator_ref(effectiveDate);
CREATE INDEX idx_oper_operref_03 ON oper_operator_ref(expiryDate);

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
	vcid varchar(64),
	valid bit not null default 1,
	modifyAble bit not null default 1,
	remark varchar(512) ,
	primary key(id)
);

drop table if exists OPER_ROLE_HIS;
create table OPER_ROLE_HIS(
	id varchar(64) not null,
	name varchar(64) not null,
	vcid varchar(64),
	valid bit not null default 1,
	modifyAble bit not null default 1,
	remark varchar(512),
	primary key(id)
);

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
create unique index idx_org_organization_01 on ORG_ORGANIZATION(code,vcid);
create index idx_org_organization_02 on ORG_ORGANIZATION(parentId);
create index idx_org_organization_04 on ORG_ORGANIZATION(companyId);
create index idx_org_organization_05 on ORG_ORGANIZATION(departmentId);
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

-- sequences
-- initdata
INSERT INTO OPER_OPERATOR(ID,VCID,ORGANIZATIONID,LOGINNAME,PASSWORD,USERNAME)
	values('123456','1000000000','1000000000','admin','admin','admin');
INSERT INTO OPER_OPERATOR(ID,VCID,ORGANIZATIONID,LOGINNAME,PASSWORD,USERNAME)
	values('123456001','1000000000','1000000000','yr','yr','yr');
INSERT INTO OPER_OPERATOR(ID,VCID,ORGANIZATIONID,LOGINNAME,PASSWORD,USERNAME)
	values('123456002','1000000000','1000000000','pqy','pqy','pqy');
update oper_operator t set t.createDate = now(),t.invalidDate = null,t.lastUpdateDate = now(),t.pwdUpdateDate = now();
commit;

INSERT INTO ORG_ORGANIZATION (`id`, `vcid`, `code`, `parentId`, `companyId`, `departmentId`, `name`, `alias`, `type`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6e63121c016e6366e2570000', '1200000000', 'JTFGS2', '1000000000', '4028819d6e63121c016e6366e2570000', NULL, '集团子公司2', '', 'COMPANY', '集团子公司2', NULL, NULL, NULL,  '', '', '2019-11-13 14:15:17.335000', '2019-11-13 14:15:17.335000');
INSERT INTO ORG_ORGANIZATION (`id`, `vcid`, `code`, `parentId`, `companyId`, `departmentId`, `name`, `alias`, `type`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6e63121c016e636757e70001', '1200000000', 'JTFGS2_CW', '4028819d6e63121c016e6366e2570000', NULL, '4028819d6e63121c016e636757e70001', '财务部', '', 'DEPARTMENT', '集团子公司2_财务部', NULL, NULL, NULL,  '', '11111', '2019-11-13 14:15:47.431000', '2019-11-13 14:30:19.567000');
INSERT INTO ORG_ORGANIZATION (`id`, `vcid`, `code`, `parentId`, `companyId`, `departmentId`, `name`, `alias`, `type`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('1000000000', '1000000000', 'JT', '', '1000000000', NULL, '集团公司', '', 'GROUP_COMPANY', '集团公司', NULL, NULL, NULL,'', '集团公司', '2019-11-10 22:47:34.364000', '2019-11-10 22:47:34.364000');
INSERT INTO ORG_ORGANIZATION (`id`, `vcid`, `code`, `parentId`, `companyId`, `departmentId`, `name`, `alias`, `type`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e607079be0000', '1000000000', 'JT_XTYF', '1000000000', '1000000000', '40289f596e606bdd016e607079be0000', '系统研发部', '', 'DEPARTMENT', '集团公司_系统研发部', NULL, NULL, NULL,'', '', '2019-11-13 00:26:54.270000', '2019-11-13 00:26:54.270000');
INSERT INTO ORG_ORGANIZATION (`id`, `vcid`, `code`, `parentId`, `companyId`, `departmentId`, `name`, `alias`, `type`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e6073ba0a0001', '1000000000', 'JT_FXGL', '1000000000', '1000000000', '40289f596e606bdd016e6073ba0a0001', '风险管理部', '', 'DEPARTMENT', '集团公司_风险管理部', NULL, NULL, NULL,'', '', '2019-11-13 00:30:27.338000', '2019-11-13 00:30:27.338000');
INSERT INTO ORG_ORGANIZATION (`id`, `vcid`, `code`, `parentId`, `companyId`, `departmentId`, `name`, `alias`, `type`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e6074e1de0002', '1000000000', 'JT_XTYF_CS', '40289f596e606bdd016e607079be0000', '1000000000', '40289f596e606bdd016e6074e1de0002', '测试部', '', 'DEPARTMENT', '集团公司_系统研发部_测试部', NULL, NULL, NULL,  '', '', '2019-11-13 00:31:43.070000', '2019-11-13 00:31:43.070000');
INSERT INTO ORG_ORGANIZATION (`id`, `vcid`, `code`, `parentId`, `companyId`, `departmentId`, `name`, `alias`, `type`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e6075e9c00003', '1000000000', 'JT_CW', '1000000000', '1000000000', '40289f596e606bdd016e6075e9c00003', '财务部', '', 'DEPARTMENT', '集团公司_财务部', NULL, NULL, NULL,'', '财务部', '2019-11-13 00:32:50.624000', '2019-11-13 00:32:50.624000');
INSERT INTO ORG_ORGANIZATION (`id`, `vcid`, `code`, `parentId`, `companyId`, `departmentId`, `name`, `alias`, `type`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e6076c0ae0004', '5000000001', 'CS1', '', '40289f596e606bdd016e6076c0ae0004', NULL, '测试公司1', '', 'COMPANY', '测试公司1', NULL, NULL, NULL,  '', '', '2019-11-13 00:33:45.646000', '2019-11-13 00:33:45.646000');
INSERT INTO ORG_ORGANIZATION (`id`, `vcid`, `code`, `parentId`, `companyId`, `departmentId`, `name`, `alias`, `type`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e607709e10005', '5000000002', 'CS2', '', '40289f596e606bdd016e607709e10005', NULL, '测试公司2', '', 'COMPANY', '测试公司2', NULL, NULL, NULL,  '', '', '2019-11-13 00:34:04.385000', '2019-11-13 00:34:04.385000');
INSERT INTO ORG_ORGANIZATION (`id`, `vcid`, `code`, `parentId`, `companyId`, `departmentId`, `name`, `alias`, `type`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e6077c4af0006', '1100000000', 'JTFGS1', '1000000000', '40289f596e606bdd016e6077c4af0006', NULL, '集团子公司1', '', 'COMPANY', '集团子公司1', NULL, NULL, NULL, '', '', '2019-11-13 00:34:52.207000', '2019-11-13 00:34:52.207000');
INSERT INTO ORG_ORGANIZATION (`id`, `vcid`, `code`, `parentId`, `companyId`, `departmentId`, `name`, `alias`, `type`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e607843220007', '1100000000', 'JTFGS1_CW', '40289f596e606bdd016e6077c4af0006', '40289f596e606bdd016e6077c4af0006', '40289f596e606bdd016e607843220007', '财务部', '', 'DEPARTMENT', '集团子公司1_财务部', NULL, NULL, NULL,  '', '', '2019-11-13 00:35:24.578000', '2019-11-13 00:35:24.578000');
INSERT INTO ORG_ORGANIZATION (`id`, `vcid`, `code`, `parentId`, `companyId`, `departmentId`, `name`, `alias`, `type`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e6078b7f90008', '5000000002', 'CS2_CW', '40289f596e606bdd016e607709e10005', '40289f596e606bdd016e607709e10005', '40289f596e606bdd016e6078b7f90008', '财务部', '', 'DEPARTMENT', '测试公司2_财务部', NULL, NULL, NULL,  '', '', '2019-11-13 00:35:54.489000', '2019-11-13 00:35:54.489000');
INSERT INTO ORG_ORGANIZATION (`id`, `vcid`, `code`, `parentId`, `companyId`, `departmentId`, `name`, `alias`, `type`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e607940a90009', '5000000001', 'CS1_CW', '40289f596e606bdd016e6076c0ae0004', '40289f596e606bdd016e6076c0ae0004', '40289f596e606bdd016e607940a90009', '财务部', '', 'DEPARTMENT', '测试公司1_财务部', NULL, NULL, NULL,  '', '', '2019-11-13 00:36:29.481000', '2019-11-13 00:36:29.481000');
INSERT INTO ORG_ORGANIZATION (`id`, `vcid`, `code`, `parentId`, `companyId`, `departmentId`, `name`, `alias`, `type`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e6089f7e3000a', '1000000000', 'JT_PPYY', '1000000000', '1000000000', '40289f596e606bdd016e6089f7e3000a', '品牌运营部', '', 'DEPARTMENT', '集团公司_品牌运营部', NULL, NULL, NULL, '', '', '2019-11-13 00:54:44.963000', '2019-11-13 00:54:44.963000');
INSERT INTO ORG_ORGANIZATION (`id`, `vcid`, `code`, `parentId`, `companyId`, `departmentId`, `name`, `alias`, `type`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e608a7f86000b', '1000000000', 'JT_RLZY', '1000000000', '1000000000', NULL, '人力资源管理部', '', 'DEPARTMENT', '集团公司_人力资源管理部', NULL, NULL, NULL,  '', '', '2019-11-13 00:55:19.686000', '2019-11-13 00:55:19.686000');
commit;
INSERT INTO `webdemo_new`.`org_post` (`id`, `parentId`, `code`, `vcid`, `name`, `fullName`, `valid`, `remark`, `organizationId`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6e7d6029016e7d61d0e10000', '', '财务总监', '1000000000', 'JT_CWZJ', NULL, '', '', '1000000000', '0000-00-00 00:00:00.000000', '0000-00-00 00:00:00.000000');
INSERT INTO `webdemo_new`.`org_post` (`id`, `parentId`, `code`, `vcid`, `name`, `fullName`, `valid`, `remark`, `organizationId`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6e7d6029016e7d626e3c0001', '', '技术总监', '1000000000', 'JT_JSZJ', NULL, '', '', '1000000000', '0000-00-00 00:00:00.000000', '0000-00-00 00:00:00.000000');
INSERT INTO `webdemo_new`.`org_post` (`id`, `parentId`, `code`, `vcid`, `name`, `fullName`, `valid`, `remark`, `organizationId`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6e7d6029016e7d635f870002', '4028819d6e7d6029016e7d61d0e10000', '财务部经理', '1000000000', 'JT_CW_JL', NULL, '', '', '40289f596e606bdd016e6075e9c00003', '0000-00-00 00:00:00.000000', '0000-00-00 00:00:00.000000');
commit;
INSERT INTO vc_virtual_center (`id`, `code`, `name`, `valid`, `createDate`, `lastUpdateDate`, `modifyAble`, `parentId`, `remark`) VALUES ('1000000000', 'JT', '集团公司', '', '2019-11-10 11:43:42.580000', '2019-11-10 11:43:42.580000', '\0', NULL, NULL);
INSERT INTO vc_virtual_center (`id`, `code`, `name`, `valid`, `createDate`, `lastUpdateDate`, `modifyAble`, `parentId`, `remark`) VALUES ('1100000000', 'JTZGS1', '集团子公司1', '', '2019-11-10 11:43:42.754000', '2019-11-10 11:43:43.111000', '\0', '1000000000', NULL);
INSERT INTO vc_virtual_center (`id`, `code`, `name`, `valid`, `createDate`, `lastUpdateDate`, `modifyAble`, `parentId`, `remark`) VALUES ('1200000000', 'JTZGS2', '集团子公司2', '', '2019-11-10 11:43:42.841000', '2019-11-10 11:43:43.246000', '\0', '1000000000', NULL);
INSERT INTO vc_virtual_center (`id`, `code`, `name`, `valid`, `createDate`, `lastUpdateDate`, `modifyAble`, `parentId`, `remark`) VALUES ('5000000001', 'CSGS1', '测试公司1', '', '2019-11-10 11:43:42.931000', '2019-11-10 11:43:42.931000', '\0', NULL, NULL);
INSERT INTO vc_virtual_center (`id`, `code`, `name`, `valid`, `createDate`, `lastUpdateDate`, `modifyAble`, `parentId`, `remark`) VALUES ('5000000002', 'CSGS2', '测试公司2', '', '2019-11-10 11:43:43.021000', '2019-11-10 11:43:43.021000', '\0', NULL, NULL);
commit;
-- foreignkey
-- functions
-- procedures
-- triggers
-- jobs
