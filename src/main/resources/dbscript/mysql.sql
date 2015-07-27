-- ****************************************************************************
-- 表：MAINFRAME_LOGIN_LOG
-- ****************************************************************************
CREATE TABLE MAINFRAME_LOGIN_LOG(
	CLIENTIPADDRESS varchar(255),
	SYSTEMID varchar(64),
	ORGANIZATIONID varchar(64),
	MESSAGE varchar(255),
	CREATEDATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间', 
	VCID varchar(64) COMMENT '虚中心',
	LOGINTYPE TINYINT(1) NOT NULL,
	ID varchar(64)  NOT NULL,
	OPERATORID varchar(64),
	OPERATORNAME varchar(64),
	OPERATORLOGINNAME varchar(64),
	primary key(ID)
);
-- ****************************************************************************
-- 表：MAINFRAME_SYSOPE_LOG
-- ****************************************************************************
create table MAINFRAME_SYSOPE_LOG(
	CLIENTIPADDRESS varchar(255),
	FUNCTION varchar(255),
	SYSTEMID varchar(64),
	ORGANIZATIONID varchar(64),
	MESSAGE varchar(255),
	CREATEDATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	VCID varchar(64),
	ID varchar(64) not null,
	OPERATORID varchar(64),
	OPERATORNAME varchar(64),
	OPERATORLOGINNAME varchar(64),
	primary key(ID)
);
-- ****************************************************************************
-- 表：OPER_EMPLOYEEINFO
-- ****************************************************************************
CREATE TABLE OPER_EMPLOYEEINFO(
	leavingDate date,
	sex TINYINT(1),
	operatorid varchar(64),
	code varchar(64),
	officialDate date,
	entryDate date,
	trialPeriodEndDate date,
	leaving TINYINT(1),
	name varchar(64),
	age TINYINT(1),
	official TINYINT(1) default 0 not null,
	lastUpdatePhoneLinkInfoDate date,
	cardNum varchar(255),
	primary key(OPERATORID)
);
create unique index idx_oper_emp_00 on OPER_EMPLOYEEINFO(code);
-- ****************************************************************************
-- 表：OPER_EMPLOYEEINFO
-- ****************************************************************************
CREATE TABLE OPER_EMPLOYEEINFO_HIS(
	leavingDate date,
	sex TINYINT(1),
	operatorid varchar(64),
	code varchar(64),
	officialDate date,
	entryDate date,
	trialPeriodEndDate date,
	leaving TINYINT(1),
	name varchar(64),
	age TINYINT(1),
	official TINYINT(1) default 0 not null,
	lastUpdatePhoneLinkInfoDate date,
	cardNum varchar(255),
	primary key(OPERATORID)
);
-- ****************************************************************************
-- 表：OPER_OPERATOR
-- ****************************************************************************
DROP TABLE IF EXISTS `OPER_OPERATOR`; 
CREATE TABLE OPER_OPERATOR(
	id varchar(64) not null,
	valid TINYINT(1) DEFAULT 1 NOT NULL comment '是否有效 0 无效  1有效  默认有效',
	pwdErrCount int(10),
	historyPwd varchar(255),
	organizationId varchar(64),
	password varchar(255),
	invalidDate date,
	lastUpdateDate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	pwdUpdateDate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	mainPostId varchar(64),
	userName varchar(64),
	locked TINYINT(1) default 0 not null,
	createDate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	examinePwd varchar(255),
	loginName varchar(64)  not null,
	primary key(ID)
) comment='系统使用人员信息表';
create unique index idx_oper_oper_00 on OPER_OPERATOR(loginName);
create index idx_oper_oper_01 on OPER_OPERATOR(loginName,password);

-- ****************************************************************************
-- 表：OPER_OPERATOR
-- ****************************************************************************
DROP TABLE IF EXISTS `OPER_OPERATOR_HIS`; 
CREATE TABLE OPER_OPERATOR_HIS(
	id varchar(64),
	valid TINYINT(1) DEFAULT 1 NOT NULL,
	pwdErrCount INT(10),
	historyPwd varchar(255),
	organizationId varchar(64),
	password varchar(255),
	invalidDate date,
	lastUpdateDate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	pwdUpdateDate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	mainPostId varchar(64),
	userName varchar(64),
	locked TINYINT(1) default 0 not null,
	createDate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	examinePwd varchar(255),
	loginName varchar(64)  not null,
	primary key(ID)
);
-- ****************************************************************************
-- 人员引用表：负责处理人员关联角色，关联分组，等关联关系：OPER_OPERATOR
-- ****************************************************************************
DROP TABLE IF EXISTS `OPER_OPERATOR_REF`; 
CREATE TABLE OPER_OPERATOR_REF(
	EFFECTIVEDATE timestamp,
	CREATEDATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	OPERATORID varchar(64) not null,
	REFID varchar(64) not null,
	ENDDATE timestamp,
	INVALIDDATE timestamp,
	REFTYPE varchar(255) not null,
	PRIMARY KEY(OPERATORID,REFID,REFTYPE)
);
CREATE INDEX IDX_OPER_OPERREF_00 ON OPER_OPERATOR_REF(OPERATORID,REFTYPE);
CREATE INDEX IDX_OPER_OPERREF_01 ON OPER_OPERATOR_REF(REFID,REFTYPE);
CREATE INDEX IDX_OPER_OPERREF_02 ON OPER_OPERATOR_REF(INVALIDDATE);
/*****************************************************************************
			表：OPER_POSTTYPE
*****************************************************************************/
create table OPER_POSTTYPE(
	NAME varchar(64),
	CREATEDATE datetime,
	ID varchar(64),
	REMARK varchar(2000),
	LASTUPDATEDATE datetime,
	VALID bit,
	CODE varchar(64),
	primary key(ID)
);
-- ****************************************************************************
-- 人员引用表：负责处理人员关联角色，关联分组，等关联关系：OPER_OPERATOR
-- ****************************************************************************
DROP TABLE IF EXISTS `OPER_OPERATOR_REF_HIS`; 
CREATE TABLE OPER_OPERATOR_REF_HIS(
	EFFECTIVEDATE timestamp,
	CREATEDATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	OPERATORID varchar(64) not null,
	REFID varchar(64) not null,
	ENDDATE timestamp not null,
	INVALIDDATE timestamp,
	REFTYPE varchar(255) not null
);
CREATE INDEX IDX_OPER_OPERREF_HIS_00 ON OPER_OPERATOR_REF(OPERATORID,REFTYPE);
CREATE INDEX IDX_OPER_OPERREF_HIS_01 ON OPER_OPERATOR_REF(REFID,REFTYPE);
CREATE INDEX IDX_OPER_OPERREF_HIS_02 ON OPER_OPERATOR_REF(INVALIDDATE);
CREATE INDEX IDX_OPER_OPERREF_HIS_10 ON OPER_OPERATOR_REF(OPERATORID,REFID,REFTYPE);
-- ****************************************************************************
-- 表：OPER_ORGANIZATION
-- ****************************************************************************
DROP TABLE IF EXISTS `OPER_ORGANIZATION`; 
CREATE TABLE OPER_ORGANIZATION(
  vcid varchar(64) not null,
  valid TINYINT(1) default 1 not null comment '是否有效 1 有效 0 无效',
  fullAddress varchar(255),
  remark varchar(2000),
  alias varchar(255),
  code varchar(64) not null,
  type varchar(64) comment '组织类型',
  id varchar(64) not null,
  parentId varchar(64),
  districtId varchar(64),
  chiefType varchar(64) comment '组织主管类型：可以是人员，可以是职位，也可以是其他...',
  address varchar(255),
  name varchar(64) not null,
  fullName varchar(64) not null,
  chiefId varchar(64),
  primary key(ID)
) comment = '组织信息';
create unique index idx_oper_organ_01 on OPER_ORGANIZATION(code);
create index idx_oper_organ_02 on OPER_ORGANIZATION(vcid);
create index idx_oper_organ_03 on OPER_ORGANIZATION(parentId);
create index idx_oper_organ_04 on OPER_ORGANIZATION(chiefType,chiefId);

-- ****************************************************************************
-- 组织信息表历史表：OPER_ORGANIZATION_HIS
-- ****************************************************************************
DROP TABLE IF EXISTS `OPER_ORGANIZATION_HIS`; 
CREATE TABLE OPER_ORGANIZATION_HIS(
  vcid varchar(64),
  valid TINYINT(1) default 1 not null,
  fullAddress varchar(255),
  remark varchar(2000),
  alias varchar(255),
  code varchar(64) not null,
  type varchar(64),
  id varchar(64) not null,
  parentId varchar(64),
  districtId varchar(64),
  chiefType varchar(64),
  address varchar(255),
  name varchar(64) not null,
  fullName varchar(64) not null,
  chiefId varchar(64),
  primary key(ID)
);
-- ****************************************************************************
-- 职位信息表：OPER_POST
-- ****************************************************************************
DROP TABLE IF EXISTS `OPER_POST`; 
CREATE TABLE OPER_POST(
	id varchar(64) not null,
	valid TINYINT(1) default 1 not null,
	parentId varchar(64),
	remark varchar(2000),
	name varchar(64),
	organizationId varchar(64),
	code varchar(64) comment '职位编码',
	fullName varchar(255),
	primary key(ID)
) comment ='职位信息';
create index idx_oper_post_00 on OPER_POST(parentId);
create index idx_oper_post_01 on OPER_POST(code);
create index idx_oper_post_02 on OPER_POST(organizationId);

-- ****************************************************************************
-- 职位信息表历史表：OPER_POST
-- ****************************************************************************
DROP TABLE IF EXISTS `OPER_POST_HIS`; 
create table OPER_POST_HIS(
	id varchar(64) not null,
	valid TINYINT(1) default 1 not null,
	parentId varchar(64),
	remark varchar(2000),
	name varchar(64),
	organizationId varchar(64),
	code varchar(64),
	fullName varchar(255),
	primary key(ID)
) comment = '历史职位信息';
-- ****************************************************************************
-- 表：OPER_VC
-- ****************************************************************************
DROP TABLE IF EXISTS `OPER_VC`; 
create table OPER_VC(
	id varchar(64) not null,
	parentId varchar(64),
	remark varchar(2000),
	name varchar(64) not null comment '虚中心名',
	primary key(ID)
) comment = '虚中心';
create unique index idx_OPER_VC on OPER_VC(name);

-- prompt "webdemo模块:创建表逻辑  end...";
-- prompt "webdemo模块:创建sequence  start...";
-- prompt "webdemo模块:创建sequence end...";
-- prompt "webdemo模块:创建sequence  start...";
-- prompt "webdemo模块:创建sequence end...";
-- prompt "webdemo模块:创建包   start...";
-- prompt "webdemo模块:创建包  end...";
-- prompt "webdemo模块:创建包   start...";
-- prompt "webdemo模块:创建包  end...";
-- prompt "webdemo模块:创建函数逻辑  start...";
-- prompt "webdemo模块:创建函数逻辑  end...";
-- prompt "webdemo模块:创建函数逻辑  start...";
-- prompt "webdemo模块:创建函数逻辑  end...";
-- prompt "webdemo模块:创建存储过程逻辑  start...";
-- prompt "webdemo模块:创建存储过程逻辑  end...";
-- prompt "webdemo模块:创建存储过程逻辑  start...";
-- prompt "webdemo模块:创建存储过程逻辑  end...";
-- prompt "webdemo模块:创建触发器   start...";
-- prompt "webdemo模块:创建触发器  end...";
-- prompt "webdemo模块:创建触发器   start...";
-- prompt "webdemo模块:创建触发器  end...";
-- prompt "webdemo模块:创建视图逻辑  start...";
-- prompt "webdemo模块:创建视图逻辑  start...";
-- prompt "webdemo模块:初始化基础数据  start...";

-- prompt "webdemo模块:初始化基础数据  end...";
-- prompt "webdemo模块:初始化基础数据  start...";
INSERT INTO OPER_OPERATOR(ID,ORGANIZATIONID,LOGINNAME,PASSWORD,USERNAME)
	values('123456','1000000','admin','admin','admin');
INSERT INTO OPER_OPERATOR(ID,ORGANIZATIONID,LOGINNAME,PASSWORD,USERNAME)
	values('123456002','1000000','pqy','pqy','pqy');
commit;
-- TRUNCATE TABLE OPER_ORGANIZATION;
insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('1000000',null,'人员','集团公司','集团公司','1000000','1000000','公司');
 
insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('1000000','1000000','人员','系统开发部','集团公司_系统开发部','1100000001','1100000001','部门');
insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('1000000','1000000','人员','人力资源部','集团公司_系统开发部','1100000002','1100000002','部门');
insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('1000000','1000000','人员','市场部','集团公司_市场部','1100000004','1100000004','部门');
insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('1000000','1100000004','人员','销售一科','集团公司_市场部_销售一科','1100000004001','1100000004001','部门');
insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('1000000','1100000004','人员','销售二科','集团公司_市场部_销售二科','1100000004002','1100000004002','部门');

insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('1000001','1000000','人员','分公司A','集团公司_分公司A','1000001','1000001','分公司');
insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('1000001','1000001','人员','分行A','集团公司_分公司A_分行A','100000101','100000101','分行');
insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('1000001','1000001','人员','分行B','集团公司_分公司A_分行B','100000102','100000102','分行');
insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('1000001','1000001','人员','分行C','集团公司_分公司A_分行C','100000103','100000103','分行'); 
 
insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('1000002','1000000','人员','分公司B','分公司B','1000002','1000002','分公司');
insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('1000002','1000002','人员','分公司网点B','分公司网点B','1000002002','1000002002','部门');

insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('2000000',null,'人员','合作方公司A','合作方公司A','2000000','2000000','分公司');
insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('3000000',null,'人员','合作方公司B','合作方公司B','3000000','3000000','分公司');
insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('3000000','3000000','人员','办事处A','合作方公司B_办事处A','30000001','30000001','部门');
commit;
insert into OPER_POST(id,parentId,name,organizationId,code,remark)
	values('1000000301',null,'部门经理','1000000',null,'集团公司系统开发部部门经理');
insert into OPER_POST(id,parentId,name,organizationId,code,remark)
	values('100000030101','1000000301','项目经理','1000000',null,'集团公司系统开发部项目经理');
insert into OPER_POST(id,parentId,name,organizationId,code,remark)
	values('100000030102','1000000301','SE','1000000',null,'集团公司系统开发部SE');
insert into OPER_POST(id,parentId,name,organizationId,code,remark)
	values('10000003010101','100000030101','高级软件工程师','1000000',null,'集团公司系统开发部高级软件工程师');
insert into OPER_POST(id,parentId,name,organizationId,code,remark)
	values('10000003010102','100000030101','工程师','1000000',null,'集团公司系统开发部工程师');
insert into OPER_POST(id,parentId,name,organizationId,code,remark)
	values('10000003010103','100000030101','助理工程师','1000000',null,'集团公司系统开发部助理工程师');
commit;
INSERT INTO OPER_VC(ID,PARENTID,NAME,REMARK)
	values('001',null,'集团公司','集团公司');
INSERT INTO OPER_VC(ID,PARENTID,NAME,REMARK)
	values('00101','001','分公司一','分公司一');
INSERT INTO OPER_VC(ID,PARENTID,NAME,REMARK)
	values('00102','001','分公司二','分公司二');
INSERT INTO OPER_VC(ID,PARENTID,NAME,REMARK)
	values('002','001','合作方公司A','合作方公司A');
INSERT INTO OPER_VC(ID,PARENTID,NAME,REMARK)
	values('003','001','合作方公司B','合作方公司B');
commit;
-- prompt "webdemo模块:初始化基础数据  end...";
-- prompt "webdemo模块:创建任务job  end...";
-- prompt "webdemo模块:创建任务job  end...";
