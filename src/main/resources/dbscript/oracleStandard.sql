prompt "webdemoģ��:�������߼�  start..."  
--****************************************************************************
-- ����BASIC_DISTRICT
--****************************************************************************
drop table BASIC_DISTRICT;
create table BASIC_DISTRICT(
	id varchar2(64 char) not null,
	parentId varchar2(64 char),
	postalCode varchar2(64 char),
	remark varchar2(2000 char),
	name varchar2(64 char) not null,
	code varchar2(64 char) not null,
	fullName varchar2(64 char),
	idCardCode varchar2(64 char),
	type varchar2(64 char),
	primary key(ID)
);
create index idx_bas_district_00 on BASIC_DISTRICT(code);
create index idx_bas_district_01 on BASIC_DISTRICT(parentId);
create index idx_bas_district_02 on BASIC_DISTRICT(idCardCode);
create index idx_bas_district_03 on BASIC_DISTRICT(postalCode);

comment on table BASIC_DISTRICT is '������Ϣ��';
comment on column BASIC_DISTRICT.idCardCode is '�����Ӧ����֤����';
comment on column BASIC_DISTRICT.postalCode is '�����Ӧ��������';
prompt "webdemoģ��:�������߼�  end..."
prompt "webdemoģ��:�������߼�  start..."  
--****************************************************************************
-- ����OPER_EMPLOYEEINFO
--****************************************************************************
drop table OPER_EMPLOYEEINFO;
create table OPER_EMPLOYEEINFO(
	leavingDate date,
	sex number(10,0),
	operatorid varchar2(64 char),
	code varchar2(64 char),
	officialDate date,
	entryDate date,
	trialPeriodEndDate date,
	leaving number(1,0),
	name varchar2(64 char),
	age number(10,0),
	official number(1,0) default 0 not null,
	lastUpdatePhoneLinkInfoDate date,
	cardNum varchar2(255 char),
	primary key(OPERATORID)
);
create unique index idx_oper_emp_00 on OPER_EMPLOYEEINFO(code);
--****************************************************************************
-- ����OPER_OPERATOR
--****************************************************************************
drop table OPER_OPERATOR;
create table OPER_OPERATOR(
	valid number(1,0) DEFAULT 1 NOT NULL,
	pwdErrCount number(10,0),
	historyPwd varchar2(255 char),
	organizationId varchar2(64 char),
	password varchar2(255 char),
	invalidDate date,
	lastUpdateDate date default sysdate not null,
	id varchar2(64 char),
	pwdUpdateDate date default sysdate not null,
	mainPostId varchar2(64 char),
	userName varchar2(64 char),
	locked number(1,0) default 0 not null,
	createDate date default sysdate not null,
	examinePwd varchar2(255 char),
	loginName varchar2(64 char)  not null,
	primary key(ID)
);
create unique index idx_oper_oper_00 on OPER_OPERATOR(loginName);
create index idx_oper_oper_01 on OPER_OPERATOR(loginName,password);

comment on table OPER_OPERATOR is 'ϵͳʹ����Ա��Ϣ��';
comment on column OPER_OPERATOR.valid is '�Ƿ���Ч 0 ��Ч  1��Ч  Ĭ����Ч';
--****************************************************************************
-- ��֯��Ϣ����OPER_ORGANIZATION
--****************************************************************************
drop table OPER_ORGANIZATION;
create table OPER_ORGANIZATION(
  fullAddress varchar2(255),
  remark varchar2(2000),
  alias varchar2(255),
  code varchar2(64) not null,
  type varchar2(64),
  id varchar2(64) not null,
  parentId varchar2(64),
  districtId varchar2(64),
  chiefType varchar2(64),
  address varchar2(255),
  name varchar2(64) not null,
  fullName varchar2(255) not null,
  chiefId varchar2(64),
  primary key(ID)
);
create unique index idx_oper_organ_01 on OPER_ORGANIZATION(code);
create index idx_oper_organ_02 on OPER_ORGANIZATION(parentId);
create index idx_oper_organ_03 on OPER_ORGANIZATION(chiefType,chiefId);

comment on table OPER_ORGANIZATION is '��֯��Ϣ��';
comment on column OPER_ORGANIZATION.type is '��֯����';
comment on column OPER_ORGANIZATION.chiefType is '��֯�������ͣ���������Ա��������ְλ��Ҳ����������...';
--****************************************************************************
-- ��֯��Ϣ����ʷ����OPER_ORGANIZATION_HIS
--****************************************************************************
drop table OPER_ORGANIZATION_HIS;
CREATE TABLE OPER_ORGANIZATION_HIS(
  fullAddress varchar2(255),
  remark varchar2(2000),
  alias varchar2(255),
  code varchar2(64) not null,
  type varchar2(64),
  id varchar2(64) not null,
  parentId varchar2(64),
  districtId varchar2(64),
  chiefType varchar2(64),
  address varchar2(255),
  name varchar2(64) not null,
  fullName varchar2(255) not null,
  chiefId varchar2(64),
  primary key(ID)
);
--****************************************************************************
-- ְλ��Ϣ����OPER_POST
--****************************************************************************
drop table OPER_POST;
create table OPER_POST(
	id varchar2(64) not null,
	parentId varchar2(64),
	remark varchar2(2000),
	name varchar2(64),
	organizationId varchar2(64),
	code varchar2(64),
	fullName varchar2(255),
	primary key(ID)
);
create index idx_oper_post_00 on OPER_POST(parentId);
create index idx_oper_post_01 on OPER_POST(code);
create index idx_oper_post_02 on OPER_POST(organizationId);

comment on table OPER_POST is 'ְλ��Ϣ��';
comment on column OPER_POST.code is 'ְλ����';
--****************************************************************************
-- ְλ��Ϣ����ʷ����OPER_POST
--****************************************************************************
drop table OPER_POST_HIS;
create table OPER_POST_HIS(
	id varchar2(64) not null,
	parentId varchar2(64),
	remark varchar2(2000),
	name varchar2(64),
	organizationId varchar2(64),
	code varchar2(64),
	fullName varchar2(255),
	primary key(ID)
);
prompt "webdemoģ��:�������߼�  end..."
prompt "webdemoģ��:����sequence  start..."  
prompt "webdemoģ��:����sequence end..."  
prompt "webdemoģ��:����sequence  start..."  
prompt "webdemoģ��:����sequence end..."  
prompt "webdemoģ��:������   start..."  
prompt "webdemoģ��:������  end..."
prompt "webdemoģ��:������   start..."  
prompt "webdemoģ��:������  end..."
prompt "webdemoģ��:���������߼�  start..."  
prompt "webdemoģ��:���������߼�  end..." 
prompt "webdemoģ��:���������߼�  start..."  
prompt "webdemoģ��:���������߼�  end..." 
prompt "webdemoģ��:�����洢�����߼�  start..." 
prompt "webdemoģ��:�����洢�����߼�  end..."
prompt "webdemoģ��:�����洢�����߼�  start..." 
prompt "webdemoģ��:�����洢�����߼�  end..."
prompt "webdemoģ��:����������   start..."  
prompt "webdemoģ��:����������  end..." 
prompt "webdemoģ��:����������   start..."  
prompt "webdemoģ��:����������  end..." 
prompt "webdemoģ��:������ͼ�߼�  start..."  
prompt "webdemoģ��:������ͼ�߼�  start..."  
prompt "webdemoģ��:��ʼ����������  start..." 
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dd5defbf548e49068167bb4834e5ae48', '3d4bd283cbd04c1c85722c6865f1b772', null, null, '������', '06', '������', '130600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a39aa124fea245c2b0ffbfffd0a772fd', '3d4bd283cbd04c1c85722c6865f1b772', null, null, '�żҿ���', '07', '�żҿ���', '130700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7cb33ba7dcc9456caac8ec92e8640636', '3d4bd283cbd04c1c85722c6865f1b772', null, null, '�е���', '08', '�е���', '130800', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c012a6672c5a4d51a2dd7c869e6e2f94', '3d4bd283cbd04c1c85722c6865f1b772', null, null, '������', '09', '������', '130900', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e9fcaf25bb7e4954ba725288edd530b4', '3d4bd283cbd04c1c85722c6865f1b772', null, null, '�ȷ���', '10', '�ȷ���', '131000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('23a701cb7a9441e7b601f2b4522d90ff', '3d4bd283cbd04c1c85722c6865f1b772', null, null, '��ˮ��', '11', '��ˮ��', '131100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b7f87c79f8344d97accf425e966da23a', '5f8761dec97040dfb75f459d1657a84b', null, null, '̫ԭ��', '01', '̫ԭ��', '140100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6bdf07875675456cad494b60cf611d83', '5f8761dec97040dfb75f459d1657a84b', null, null, '��ͬ��', '02', '��ͬ��', '140200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('23f034f938e246278059476a291b3738', '5f8761dec97040dfb75f459d1657a84b', null, null, '��Ȫ��', '03', '��Ȫ��', '140300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('87c57bcb3f14405ab32d70bb5e6af000', '5f8761dec97040dfb75f459d1657a84b', null, null, '������', '04', '������', '140400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('39513e1f4dc34528937b429ee5ab7bd1', '5f8761dec97040dfb75f459d1657a84b', null, null, '������', '05', '������', '140500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3a78300165d5408f878939f11f57fb8e', '5f8761dec97040dfb75f459d1657a84b', null, null, '˷����', '06', '˷����', '140600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('38ee852f7125442d8c90540ecf8fbfcc', '5f8761dec97040dfb75f459d1657a84b', null, null, '������', '07', '������', '140700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a38ecbcdd84a434280f6a45b609558ad', '5f8761dec97040dfb75f459d1657a84b', null, null, '�˳���', '08', '�˳���', '140800', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('264018e21b854c78866dd48622f2d326', '5f8761dec97040dfb75f459d1657a84b', null, null, '������', '09', '������', '140900', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f01f08e264f240c78e12fea9d8d2c41a', '5f8761dec97040dfb75f459d1657a84b', null, null, '�ٷ���', '10', '�ٷ���', '141000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('246df1df62e141c68397e33cc08f5adf', '5f8761dec97040dfb75f459d1657a84b', null, null, '������', '11', '������', '141100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dc525fdeeb4d4d4a93e1a8ee410cbb15', 'd5795820975447d090bdbd135c4e4edc', null, null, '���ͺ�����', '01', '���ͺ�����', '150100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f52d20e8efa14e458b7e12d803073951', 'd5795820975447d090bdbd135c4e4edc', null, null, '��ͷ��', '02', '��ͷ��', '150200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('746ab0b6b0974a89b45cde14f80c15be', 'd5795820975447d090bdbd135c4e4edc', null, null, '�ں���', '03', '�ں���', '150300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d740c573976d40e880ce13ec316fdda9', 'd5795820975447d090bdbd135c4e4edc', null, null, '�����', '04', '�����', '150400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fb5216a01a6448768f4b174d3601242e', 'd5795820975447d090bdbd135c4e4edc', null, null, 'ͨ����', '05', 'ͨ����', '150500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('594fcd2dd10b4962a685bb3aff60a5f8', 'd5795820975447d090bdbd135c4e4edc', null, null, '������˹��', '06', '������˹��', '150600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ecb2eddd1a1e4129af8155c03ae0253a', 'd5795820975447d090bdbd135c4e4edc', null, null, '���ױ�����', '07', '���ױ�����', '150700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fab236d4b9814f9481383155c5a1cde7', 'd5795820975447d090bdbd135c4e4edc', null, null, '�����׶���', '08', '�����׶���', '150800', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a0b6ea307e034ce6ba5d89c5eb5a71a8', 'd5795820975447d090bdbd135c4e4edc', null, null, '�����첼��', '09', '�����첼��', '150900', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2ba645b8f3634fa8b493153e4a5ed8f6', 'd5795820975447d090bdbd135c4e4edc', null, null, '�˰���', '22', '�˰���', '152200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8e3b599f883a4795a8ebede218cfa3c5', 'd5795820975447d090bdbd135c4e4edc', null, null, '���ֹ�����', '25', '���ֹ�����', '152500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('750ccb3644114acbaf7cb9f3979fa14e', 'd5795820975447d090bdbd135c4e4edc', null, null, '��������', '29', '��������', '152900', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d4dc7fd748b54bfd85730b31208b8983', '2478110f8a104a189fb6f4a0eb4bde1c', null, null, '������', '01', '������', '210100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5246279f38814f9a80d551d0c4ec88cb', '2478110f8a104a189fb6f4a0eb4bde1c', null, null, '������', '02', '������', '210200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('77697bfbce0b42e3a0ab3a35f66cc591', '2478110f8a104a189fb6f4a0eb4bde1c', null, null, '��ɽ��', '03', '��ɽ��', '210300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2dae9d3b1ff147988878f48b30ca29e6', '2478110f8a104a189fb6f4a0eb4bde1c', null, null, '��˳��', '04', '��˳��', '210400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('133d81bc3b4c48e7803bde263c887d8f', '2478110f8a104a189fb6f4a0eb4bde1c', null, null, '��Ϫ��', '05', '��Ϫ��', '210500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('21f243e300a74fb4abb0138b942daf00', '2478110f8a104a189fb6f4a0eb4bde1c', null, null, '������', '06', '������', '210600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6cd8d97a762c424985e64493c40c3dd1', '2478110f8a104a189fb6f4a0eb4bde1c', null, null, '������', '07', '������', '210700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2395708e898b4fb59076a40fba93436a', '2478110f8a104a189fb6f4a0eb4bde1c', null, null, 'Ӫ����', '08', 'Ӫ����', '210800', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('575d6ce067aa4da9a890efc1ee49b8d7', '2478110f8a104a189fb6f4a0eb4bde1c', null, null, '������', '09', '������', '210900', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fb07ee3fe7964963bc75133a6263f5f2', '2478110f8a104a189fb6f4a0eb4bde1c', null, null, '������', '10', '������', '211000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aa1484c1a2fa40f8ae030411fe7df533', '1884978b76d6469c871140b620617381', '314300', null, '������', '24', '������', '330424', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('323ddd65fccd4f8e93f0320cbb6efbe9', '1884978b76d6469c871140b620617381', '314400', null, '������', '81', '������', '330481', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8ed40842b3014cd499f3c094222b1513', '1884978b76d6469c871140b620617381', '314200', null, 'ƽ����', '82', 'ƽ����', '330482', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('838a8204d3c94ee3925de8459dc633ff', '1884978b76d6469c871140b620617381', '314500', null, 'ͩ����', '83', 'ͩ����', '330483', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('75b39c1f74b445319410ef34beb28c3b', '31f843473a4d4710a5d8cb462ee84cd1', null, null, '������', '04', '������', '350400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('60da25ef271a45e88a92c016ef7e469c', '31f843473a4d4710a5d8cb462ee84cd1', null, null, 'Ȫ����', '05', 'Ȫ����', '350500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('523109f4617c4f9394d802e616806354', '31f843473a4d4710a5d8cb462ee84cd1', null, null, '������', '06', '������', '350600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('66c6fb2856b6486cad0f949d7c1045e3', '31f843473a4d4710a5d8cb462ee84cd1', null, null, '��ƽ��', '07', '��ƽ��', '350700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('55b987a4d8ed49d99701fc0c6ad87720', '31f843473a4d4710a5d8cb462ee84cd1', null, null, '������', '08', '������', '350800', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6fd17ced4f124cc4881a50ad26321855', '31f843473a4d4710a5d8cb462ee84cd1', null, null, '������', '09', '������', '350900', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4f7002a7097045428cafa1878f18556a', '89af740e57c443e8bf70af185481d025', null, null, '�ϲ���', '01', '�ϲ���', '360100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('647fb0abceaf4c5d9ea5b24cdf2de227', '89af740e57c443e8bf70af185481d025', null, null, '��������', '02', '��������', '360200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('63879d4cb0b9445c86e98966d15f22d6', '89af740e57c443e8bf70af185481d025', null, null, 'Ƽ����', '03', 'Ƽ����', '360300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('160289800e4949a18ce764e082fa4bd0', '89af740e57c443e8bf70af185481d025', null, null, '�Ž���', '04', '�Ž���', '360400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e776f1045e3441a3bf1a91a222e248eb', '89af740e57c443e8bf70af185481d025', null, null, '������', '05', '������', '360500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f48fd507cbb24c9d85e8c2a2181c9115', '89af740e57c443e8bf70af185481d025', null, null, 'ӥ̶��', '06', 'ӥ̶��', '360600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0a3c848e26ed49d396eb5b00ecce7de0', '89af740e57c443e8bf70af185481d025', null, null, '������', '07', '������', '360700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('04639f4c045a44258ebff7fc94fec3e5', '89af740e57c443e8bf70af185481d025', null, null, '������', '08', '������', '360800', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ba92d925173c48ebac5841355f105db5', '89af740e57c443e8bf70af185481d025', null, null, '�˴���', '09', '�˴���', '360900', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bfcd8a0a0e484b12ac10a25b91a97e68', '89af740e57c443e8bf70af185481d025', null, null, '������', '10', '������', '361000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ae3e3d63e8b541b3a63dd96ec498e5d0', '89af740e57c443e8bf70af185481d025', null, null, '������', '11', '������', '361100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f1f171efba3d4ecb9a3489ae544cdd47', '43e6e48fca984902b2da31dc7babd4f5', null, null, '������', '01', '������', '370100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('adf505a23af74179b00c02a084407ca8', '43e6e48fca984902b2da31dc7babd4f5', null, null, '�ൺ��', '02', '�ൺ��', '370200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('85fb1b8433304911b85e1cd6cdb26d9e', '43e6e48fca984902b2da31dc7babd4f5', null, null, '�Ͳ���', '03', '�Ͳ���', '370300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7ceca56d1b8146d48e88aabf26741991', '43e6e48fca984902b2da31dc7babd4f5', null, null, '��ׯ��', '04', '��ׯ��', '370400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f2eb632af2014a9289fe6a736c9c12ac', '43e6e48fca984902b2da31dc7babd4f5', null, null, '��Ӫ��', '05', '��Ӫ��', '370500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5dc548802c6c40a3803e58511ec474bf', '43e6e48fca984902b2da31dc7babd4f5', null, null, '��̨��', '06', '��̨��', '370600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4ae4336f1dab45c0a465fae057c383fe', '43e6e48fca984902b2da31dc7babd4f5', null, null, 'Ϋ����', '07', 'Ϋ����', '370700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b34159526635417eac1df82dd9c7859b', '43e6e48fca984902b2da31dc7babd4f5', null, null, '������', '08', '������', '370800', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f04b7fdbaab14550914ec25ef9299498', '43e6e48fca984902b2da31dc7babd4f5', null, null, '̩����', '09', '̩����', '370900', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a39d0fc8049d4c8e8ac862df4bbb7ebc', '43e6e48fca984902b2da31dc7babd4f5', null, null, '������', '10', '������', '371000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3344436933104790acb9de9e63a859e9', '43e6e48fca984902b2da31dc7babd4f5', null, null, '������', '11', '������', '371100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7844d926e0c94aeca55889ee6ede41d8', '43e6e48fca984902b2da31dc7babd4f5', null, null, '������', '12', '������', '371200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ef87eb2c68254dda8ded0308e20e4dac', '43e6e48fca984902b2da31dc7babd4f5', null, null, '������', '13', '������', '371300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0dc3aad6447a4a92818dc72d59bb1a44', '43e6e48fca984902b2da31dc7babd4f5', null, null, '������', '14', '������', '371400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('54d95647b0ee4c219e70c84a2a74b396', '43e6e48fca984902b2da31dc7babd4f5', null, null, '�ĳ���', '15', '�ĳ���', '371500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('18d0a9122294487eb4aa8d5d6849ec93', '43e6e48fca984902b2da31dc7babd4f5', null, null, '������', '16', '������', '371600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3d9e443c24e1403caf4ab9e0e6fd05ed', '43e6e48fca984902b2da31dc7babd4f5', null, null, '������', '17', '������', '371700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('849e41d6e28c41eea4e73753ec790c9b', '8c9c2e275ecb461fa3bd1039d130a011', null, null, '֣����', '01', '֣����', '410100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4dbc4026e4944f11bae16adcae5b5e26', '8c9c2e275ecb461fa3bd1039d130a011', null, null, '������', '02', '������', '410200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b62c1ee0eef24fee8b6941a8b36c5a40', '8c9c2e275ecb461fa3bd1039d130a011', null, null, '������', '03', '������', '410300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('37804a77ddc4428f948d66acea1d5229', '8c9c2e275ecb461fa3bd1039d130a011', null, null, 'ƽ��ɽ��', '04', 'ƽ��ɽ��', '410400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('521eaa766608414e800f68a916592753', '8c9c2e275ecb461fa3bd1039d130a011', null, null, '������', '05', '������', '410500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1b78854ef6cb4ca9bed5b981062bc3e5', '8c9c2e275ecb461fa3bd1039d130a011', null, null, '�ױ���', '06', '�ױ���', '410600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0685a404d9eb48e69f442f0fecd81238', '8c9c2e275ecb461fa3bd1039d130a011', null, null, '������', '07', '������', '410700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2c262f32b8254b56b1e1468a5812ad71', '8c9c2e275ecb461fa3bd1039d130a011', null, null, '������', '08', '������', '410800', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('620533741dd14610b937e008ac996c69', '8c9c2e275ecb461fa3bd1039d130a011', null, null, '�����', '09', '�����', '410900', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a1c30fcc46124323a7742e62970e5e79', '8c9c2e275ecb461fa3bd1039d130a011', null, null, '������', '10', '������', '411000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('318e394e38bd4bd38a60562a064fac5d', '8c9c2e275ecb461fa3bd1039d130a011', null, null, '�����', '11', '�����', '411100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1d21c167dbe048d7942a636ec4ce172c', '8c9c2e275ecb461fa3bd1039d130a011', null, null, '����Ͽ��', '12', '����Ͽ��', '411200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e16c41e4d0b5480786a44c95e8fc7a5c', '8c9c2e275ecb461fa3bd1039d130a011', null, null, '������', '13', '������', '411300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('815767f8485d4636acbaa4eae0b5234c', '8c9c2e275ecb461fa3bd1039d130a011', null, null, '������', '14', '������', '411400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('380f7b3b49a44eae82cfa793b89dbb92', '8c9c2e275ecb461fa3bd1039d130a011', null, null, '������', '15', '������', '411500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('78e3f9f0494b42a789f858385a476d44', '8c9c2e275ecb461fa3bd1039d130a011', null, null, '�ܿ���', '16', '�ܿ���', '411600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7d42b8147aa24128ae7315db8d920b90', '8c9c2e275ecb461fa3bd1039d130a011', null, null, 'פ������', '17', 'פ������', '411700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8ebfb0cbf62d4b6597fd742963815e4d', '3cda75cb85e3483881ba373b0ef35585', null, null, '�人��', '01', '�人��', '420100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f0236aa5f8f246bfaa238b0f756c17d4', '3cda75cb85e3483881ba373b0ef35585', null, null, '��ʯ��', '02', '��ʯ��', '420200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d3a817abfa1a4d2f80ce7ca14cb04134', '3cda75cb85e3483881ba373b0ef35585', null, null, 'ʮ����', '03', 'ʮ����', '420300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e3d5484dc1394cbb81b162633c379be5', '3cda75cb85e3483881ba373b0ef35585', null, null, '�˲���', '05', '�˲���', '420500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cd4e23e0d23048a3837e85d24ad2783a', '3cda75cb85e3483881ba373b0ef35585', null, null, '�差��', '06', '�差��', '420600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('41054de8248f40979f122baf019dda58', '3cda75cb85e3483881ba373b0ef35585', null, null, '������', '07', '������', '420700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3ed095ba7eea4793a022a5b3b2e21ce6', '3cda75cb85e3483881ba373b0ef35585', null, null, '������', '08', '������', '420800', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a9623076ba7f459882a4de744213da9f', '3cda75cb85e3483881ba373b0ef35585', null, null, 'Т����', '09', 'Т����', '420900', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4a6dbf97ae1346a1ae058aada593c83b', '3cda75cb85e3483881ba373b0ef35585', null, null, '������', '10', '������', '421000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('88495c56c828443ca445e66c6d99e988', '3cda75cb85e3483881ba373b0ef35585', null, null, '�Ƹ���', '11', '�Ƹ���', '421100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5cbfd640dd1e427e93dee73568bc7f41', '3cda75cb85e3483881ba373b0ef35585', null, null, '������', '12', '������', '421200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a2c47feb005a467e83020bce64d6a551', '3cda75cb85e3483881ba373b0ef35585', null, null, '������', '13', '������', '421300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ede3533eebc24764a993c9c8b694c26b', '3cda75cb85e3483881ba373b0ef35585', null, null, '��ʩ����������������', '28', '��ʩ����������������', '422800', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('368a7e6a7a6f409e8af26a6793354867', '3cda75cb85e3483881ba373b0ef35585', null, null, 'ʡֱϽ������λ', '90', 'ʡֱϽ������λ', '429000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1e44bd32e5c84c3c852e00f22f6fda95', '33bc3bb1a9dc4533b9de70de59c1ec1c', null, null, '��ɳ��', '01', '��ɳ��', '430100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1cb06b4200264436a52680229df355c0', '33bc3bb1a9dc4533b9de70de59c1ec1c', null, null, '������', '02', '������', '430200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1366c663bb614b20b4d433b637b286ed', '33bc3bb1a9dc4533b9de70de59c1ec1c', null, null, '��̶��', '03', '��̶��', '430300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4d775ed4147c4a29896d82e845939e8b', '33bc3bb1a9dc4533b9de70de59c1ec1c', null, null, '������', '04', '������', '430400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('495e8497aed747bf8741de8bd42abaf7', '33bc3bb1a9dc4533b9de70de59c1ec1c', null, null, '������', '05', '������', '430500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3f6620721bd74a3aade384dc03f7e7b9', 'd740c573976d40e880ce13ec316fdda9', '025500', null, '��³�ƶ�����', '21', '��³�ƶ�����', '150421', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('37dda40329e04ffdba47d0c7064a35ac', 'd740c573976d40e880ce13ec316fdda9', '025450', null, '��������', '22', '��������', '150422', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('01d033ac428d4bfca15f5931f200df8e', 'd740c573976d40e880ce13ec316fdda9', '025150', null, '��������', '23', '��������', '150423', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('249e55b0df1f4d51a58905a46811b5d6', 'd740c573976d40e880ce13ec316fdda9', '025250', null, '������', '24', '������', '150424', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d569337671da4fb8a85f698264ef4d61', 'd740c573976d40e880ce13ec316fdda9', '025350', null, '��ʲ������', '25', '��ʲ������', '150425', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8ab0555e136948a3a71681ace1e2e16b', 'd740c573976d40e880ce13ec316fdda9', '024500', null, '��ţ����', '26', '��ţ����', '150426', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('76406462eff84cf5939fad4d4e91c5ab', 'd740c573976d40e880ce13ec316fdda9', '024400', null, '��������', '28', '��������', '150428', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('df261d93babe4955915ac54d943c538a', 'd740c573976d40e880ce13ec316fdda9', '024200', null, '������', '29', '������', '150429', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('46ca4f3291ee42cf81a1815f0201a49a', 'd740c573976d40e880ce13ec316fdda9', '024300', null, '������', '30', '������', '150430', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2cd816572c0343d2beae701bcaf0e05a', 'fb5216a01a6448768f4b174d3601242e', '028000', null, '��Ͻ��', '01', '��Ͻ��', '150501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e96afb0405a6470792800c4a9835862f', 'fb5216a01a6448768f4b174d3601242e', '028000', null, '�ƶ�����', '02', '�ƶ�����', '150502', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8276292c416e4239b37cb89844474626', 'fb5216a01a6448768f4b174d3601242e', '029300', null, '�ƶ�����������', '21', '�ƶ�����������', '150521', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('22a22423feb94cbc885c08348ab1801b', 'fb5216a01a6448768f4b174d3601242e', '028100', null, '�ƶ�����������', '22', '�ƶ�����������', '150522', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9fb97cb7b04244e381543dfdfddaa8e0', 'fb5216a01a6448768f4b174d3601242e', '028400', null, '��³��', '23', '��³��', '150523', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1aafe84915bd47a6971382da176abdd3', 'fb5216a01a6448768f4b174d3601242e', '028200', null, '������', '24', '������', '150524', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c9c992d1e3984fb8ac62e8c208860394', 'fb5216a01a6448768f4b174d3601242e', '028300', null, '������', '25', '������', '150525', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7f428dc524e04f59b24fee13286fa753', 'fb5216a01a6448768f4b174d3601242e', '029100', null, '��³����', '26', '��³����', '150526', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('635aded1f07547ac8d79b3e3222b8008', 'fb5216a01a6448768f4b174d3601242e', '029200', null, '���ֹ�����', '81', '���ֹ�����', '150581', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5219f4405243401ca5ef1dafdc9da20f', '594fcd2dd10b4962a685bb3aff60a5f8', '017000', null, '��ʤ��', '02', '��ʤ��', '150602', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c9880a04d8db4894b85399f7d2e07e35', '594fcd2dd10b4962a685bb3aff60a5f8', '014300', null, '��������', '21', '��������', '150621', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('13c31a7238784391a61690d856bab2ec', '594fcd2dd10b4962a685bb3aff60a5f8', '017100', null, '׼�����', '22', '׼�����', '150622', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2d5d1376b8f4446cb9ed4184e81bb0f8', '594fcd2dd10b4962a685bb3aff60a5f8', '016200', null, '���п�ǰ��', '23', '���п�ǰ��', '150623', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f000d607fdf6406bba184f474650c426', '594fcd2dd10b4962a685bb3aff60a5f8', '016100', null, '���п���', '24', '���п���', '150624', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('23101960c7ca4d45b3f5f5a6d5af6e3e', '594fcd2dd10b4962a685bb3aff60a5f8', '017400', null, '������', '25', '������', '150625', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('946c34f9a9ad4752a8939c5ff59ec0ea', '594fcd2dd10b4962a685bb3aff60a5f8', '017300', null, '������', '26', '������', '150626', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d4b009fd03674ecba18204dedbadcaca', '594fcd2dd10b4962a685bb3aff60a5f8', '017200', null, '���������', '27', '���������', '150627', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8414286d343549919e59f89909ee9ef0', 'ecb2eddd1a1e4129af8155c03ae0253a', '021000', null, '��Ͻ��', '01', '��Ͻ��', '150701', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('00483f5c69874e248afe1d8c59252ba0', 'ecb2eddd1a1e4129af8155c03ae0253a', '021000', null, '��������', '02', '��������', '150702', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7f090d66a3bb4677b52382c608345d39', 'ecb2eddd1a1e4129af8155c03ae0253a', '162750', null, '������', '21', '������', '150721', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2b000b387cde4ad294598da73781dc63', 'ecb2eddd1a1e4129af8155c03ae0253a', '162850', null, 'Ī�����ߴ��Ӷ���������', '22', 'Ī�����ߴ��Ӷ���������', '150722', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b7f3faa378c045059541626847516e4b', 'ecb2eddd1a1e4129af8155c03ae0253a', '022450', null, '���״�������', '23', '���״�������', '150723', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a9d1200460bb48e49a32b15b4df78340', 'ecb2eddd1a1e4129af8155c03ae0253a', '021100', null, '���¿���������', '24', '���¿���������', '150724', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9497e32a805e4295880520a432a73e3e', 'ecb2eddd1a1e4129af8155c03ae0253a', '021500', null, '�°Ͷ�����', '25', '�°Ͷ�����', '150725', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1e49dc865415483b97e95f96c8e42261', 'ecb2eddd1a1e4129af8155c03ae0253a', '021200', null, '�°Ͷ�������', '26', '�°Ͷ�������', '150726', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('03cb796b97a043128a1f9bc88d7b54e2', 'ecb2eddd1a1e4129af8155c03ae0253a', '021300', null, '�°Ͷ�������', '27', '�°Ͷ�������', '150727', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c2c5985d7f3d4b2b8125d86a03d9876e', 'ecb2eddd1a1e4129af8155c03ae0253a', '021400', null, '��������', '81', '��������', '150781', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fe49e564c7f24b0a9403f03097178f27', 'ecb2eddd1a1e4129af8155c03ae0253a', '022150', null, '����ʯ��', '82', '����ʯ��', '150782', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('949cac5ef2c44e73a33250aedcb05e66', 'ecb2eddd1a1e4129af8155c03ae0253a', '162650', null, '��������', '83', '��������', '150783', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fe99c2294d894601b28378d8b41e4424', 'ecb2eddd1a1e4129af8155c03ae0253a', '022250', null, '���������', '84', '���������', '150784', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b7b4b82254e24822915feb6bb62aa895', 'ecb2eddd1a1e4129af8155c03ae0253a', '022350', null, '������', '85', '������', '150785', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e2ff940cf41645de8412fcd19fa09295', 'fab236d4b9814f9481383155c5a1cde7', '015000', null, '��Ͻ��', '01', '��Ͻ��', '150801', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('48e555ed46b24472b0e0b92058224d5e', 'fab236d4b9814f9481383155c5a1cde7', '015000', null, '�ٺ���', '02', '�ٺ���', '150802', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('29ff191fe54441219d75b85d0442190c', 'fab236d4b9814f9481383155c5a1cde7', '015100', null, '��ԭ��', '21', '��ԭ��', '150821', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('392d0be7a5b248bca8539bcecd0f7193', 'fab236d4b9814f9481383155c5a1cde7', '015200', null, '�����', '22', '�����', '150822', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('531adf0c1d5344c8a1ac85d205d9739c', 'fab236d4b9814f9481383155c5a1cde7', '014400', null, '������ǰ��', '23', '������ǰ��', '150823', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2f872e2b58f84e5c86040e5d34f33986', 'fab236d4b9814f9481383155c5a1cde7', '015300', null, '����������', '24', '����������', '150824', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e2046a09232342b49825016907924c4d', 'fab236d4b9814f9481383155c5a1cde7', '015500', null, '�����غ���', '25', '�����غ���', '150825', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5a1eba2dbcca47e9941580733e7b7a94', 'fab236d4b9814f9481383155c5a1cde7', '015400', null, '��������', '26', '��������', '150826', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('56adc260dcfe462ab23a15ba20f680c2', 'a0b6ea307e034ce6ba5d89c5eb5a71a8', '012000', null, '��Ͻ��', '01', '��Ͻ��', '150901', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2377296d1337496e8456a276d790d620', 'a0b6ea307e034ce6ba5d89c5eb5a71a8', '012000', null, '������', '02', '������', '150902', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ec66f744a5d4424c8bb5eca6ac82d0d1', 'a0b6ea307e034ce6ba5d89c5eb5a71a8', '012300', null, '׿����', '21', '׿����', '150921', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fbf5291250404c97b2f4404b9797d0b7', 'a0b6ea307e034ce6ba5d89c5eb5a71a8', '013350', null, '������', '22', '������', '150922', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0e4bee0867354dce98af099a87c969b6', 'a0b6ea307e034ce6ba5d89c5eb5a71a8', '013400', null, '�̶���', '23', '�̶���', '150923', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('173cb3ba6e494af1bd106d2f10af59a4', 'a0b6ea307e034ce6ba5d89c5eb5a71a8', '013650', null, '�˺���', '24', '�˺���', '150924', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('112b0099929c46429a31e10333d9aa9d', 'a0b6ea307e034ce6ba5d89c5eb5a71a8', '013750', null, '������', '25', '������', '150925', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b1a7197950d049b1bfd4d42f56d2afbd', 'a0b6ea307e034ce6ba5d89c5eb5a71a8', '012200', null, '���������ǰ��', '26', '���������ǰ��', '150926', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3dcd74eff4ea470e8b7db5d38f460e16', 'a0b6ea307e034ce6ba5d89c5eb5a71a8', '013500', null, '�������������', '27', '�������������', '150927', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e530a0e8718c46ed8da3877cb82335d1', 'a0b6ea307e034ce6ba5d89c5eb5a71a8', '012400', null, '�������������', '28', '�������������', '150928', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('96d5f89fc11245c6a0cdb9e9e065b688', 'a0b6ea307e034ce6ba5d89c5eb5a71a8', '011800', null, '��������', '29', '��������', '150929', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5d75c7e55d5d47ac988500c715db089b', 'a0b6ea307e034ce6ba5d89c5eb5a71a8', '012100', null, '������', '81', '������', '150981', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b7aaf275ffe04792adcf47668fc7f7ba', null, null, null, '������', '11', '������', '110000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d6b0f77426fb44c690e4059d93094127', '79c341fcd1fe4b91a5d97cbb1a070068', null, null, '��ɽ����������', '34', '��ɽ����������', '513400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f8bee6d70b4d4aaebf536852686432c1', 'd3b62fa78aa843649485428da6b36015', null, null, '������', '01', '������', '520100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f027f86e80f547bd898e22f4695c1146', 'd3b62fa78aa843649485428da6b36015', null, null, '����ˮ��', '02', '����ˮ��', '520200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('74c8764cd28d4fe797df07e2e0444dc8', 'b1661da6929548f58fc4ac41d14d2b89', '055350', null, '¡Ң��', '25', '¡Ң��', '130525', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7e5c64d267b24dcd84d530484510a1ed', 'b1661da6929548f58fc4ac41d14d2b89', '055150', null, '����', '26', '����', '130526', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f33112b026d44d768bca37498f8153bb', 'b1661da6929548f58fc4ac41d14d2b89', '054400', null, '�Ϻ���', '27', '�Ϻ���', '130527', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1f30ceb3d3f04b01b0c1776030387378', 'b1661da6929548f58fc4ac41d14d2b89', '055550', null, '������', '28', '������', '130528', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ee6ba53896994d148703c571274f69da', 'b1661da6929548f58fc4ac41d14d2b89', '055250', null, '��¹��', '29', '��¹��', '130529', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('51ca2a956d574dfbab8158684bcbc233', 'b1661da6929548f58fc4ac41d14d2b89', '051730', null, '�º���', '30', '�º���', '130530', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1127c468d3114a528f92bb6719d6fb28', 'b1661da6929548f58fc4ac41d14d2b89', '054600', null, '������', '31', '������', '130531', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f255ada3a3724eccb41ba12a35618053', 'b1661da6929548f58fc4ac41d14d2b89', '054500', null, 'ƽ����', '32', 'ƽ����', '130532', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a8cddbfa463f4d0e85bcfe0b1d00a7b8', 'b1661da6929548f58fc4ac41d14d2b89', '054700', null, '����', '33', '����', '130533', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('419ab62c72e6498fbcfcf10c03c131c7', 'b1661da6929548f58fc4ac41d14d2b89', '054800', null, '�����', '34', '�����', '130534', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2f4b120e14a04eee8fe219667b5271e5', 'b1661da6929548f58fc4ac41d14d2b89', '054900', null, '������', '35', '������', '130535', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4162de0dcab54e33aebca065a6c81ade', 'b1661da6929548f58fc4ac41d14d2b89', '051800', null, '�Ϲ���', '81', '�Ϲ���', '130581', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2bd83122b2d44230893e997763c0d1fc', 'b1661da6929548f58fc4ac41d14d2b89', '054100', null, 'ɳ����', '82', 'ɳ����', '130582', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('16ffb9b0dc7f4d68a8f7b3850584c5a3', 'dd5defbf548e49068167bb4834e5ae48', '071000', null, '��Ͻ��', '01', '��Ͻ��', '130601', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7013d03be9714d57a59b643771bd5efe', 'dd5defbf548e49068167bb4834e5ae48', '071000', null, '������', '02', '������', '130602', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('47925bfc8f5c4946baa451af497581f5', 'dd5defbf548e49068167bb4834e5ae48', '071000', null, '������', '03', '������', '130603', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('07866f9d10c94cf6950b5c115e110c60', 'dd5defbf548e49068167bb4834e5ae48', '071000', null, '������', '04', '������', '130604', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('64d277aecff640dfa62a23d839df4a83', 'dd5defbf548e49068167bb4834e5ae48', '072150', null, '������', '21', '������', '130621', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('59ad1eb93c6e447a87362a936d7f1032', 'dd5defbf548e49068167bb4834e5ae48', '071100', null, '��Է��', '22', '��Է��', '130622', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e9a8b13e289a4f1eafb3458ec72fe3f5', 'dd5defbf548e49068167bb4834e5ae48', '074100', null, '�ˮ��', '23', '�ˮ��', '130623', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('25fbf8372f434159962bfa0df084203c', 'dd5defbf548e49068167bb4834e5ae48', '073200', null, '��ƽ��', '24', '��ƽ��', '130624', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cfde84f663c0464ba97574b9232804e9', 'dd5defbf548e49068167bb4834e5ae48', '072550', null, '��ˮ��', '25', '��ˮ��', '130625', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2ea5b84fde544890b0998fdeeea81b68', 'dd5defbf548e49068167bb4834e5ae48', '072650', null, '������', '26', '������', '130626', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5f9612fb00884a34bb10130caf9a86cc', 'dd5defbf548e49068167bb4834e5ae48', '072350', null, '����', '27', '����', '130627', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1584c1e752544307aaaff59790dbf17e', 'dd5defbf548e49068167bb4834e5ae48', '071500', null, '������', '28', '������', '130628', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f287916e3e3e4ad6a8a6d750f0c75fdb', 'dd5defbf548e49068167bb4834e5ae48', '071700', null, '�ݳ���', '29', '�ݳ���', '130629', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1fd3f6d2808d4381b1a2e4fc5e320e0b', 'dd5defbf548e49068167bb4834e5ae48', '102900', null, '�Դ��', '30', '�Դ��', '130630', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('38f661ab5191467daeac49ba0a98b64d', 'dd5defbf548e49068167bb4834e5ae48', '072450', null, '������', '31', '������', '130631', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('650599f311254bf68c14e465798525c7', 'dd5defbf548e49068167bb4834e5ae48', '071600', null, '������', '32', '������', '130632', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d972ed621e0a4bec9c9881213b6bada3', 'dd5defbf548e49068167bb4834e5ae48', '074200', null, '����', '33', '����', '130633', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b484040da40b4706876d0640082194ef', 'dd5defbf548e49068167bb4834e5ae48', '073100', null, '������', '34', '������', '130634', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d712f7e05e104368aae3015f11431f1f', 'dd5defbf548e49068167bb4834e5ae48', '071400', null, '���', '35', '���', '130635', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('91fbf0ea56384d06bf3ff948371f90a4', 'dd5defbf548e49068167bb4834e5ae48', '072250', null, '˳ƽ��', '36', '˳ƽ��', '130636', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9de10fdf61074afa90ab3e27c6e07409', 'dd5defbf548e49068167bb4834e5ae48', '071300', null, '��Ұ��', '37', '��Ұ��', '130637', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('98a73c50b8e34ca2b49f122efc8d580f', 'dd5defbf548e49068167bb4834e5ae48', '071800', null, '����', '38', '����', '130638', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7abf45109ddb4b2d9dac50da4c9b595a', 'dd5defbf548e49068167bb4834e5ae48', '072750', null, '������', '81', '������', '130681', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dc000f3ec99e4266a85460150160da10', 'dd5defbf548e49068167bb4834e5ae48', '073000', null, '������', '82', '������', '130682', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e3e2427142b346b690676df54a3a0e18', 'dd5defbf548e49068167bb4834e5ae48', '071200', null, '������', '83', '������', '130683', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4fc61e0e39c244fbbee5537b108143a7', 'dd5defbf548e49068167bb4834e5ae48', '074000', null, '�߱�����', '84', '�߱�����', '130684', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('61084baffd014f86ac167d2d7886bbec', 'a39aa124fea245c2b0ffbfffd0a772fd', '075000', null, '��Ͻ��', '01', '��Ͻ��', '130701', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('87d4333c126449d7817992d1dc848927', 'a39aa124fea245c2b0ffbfffd0a772fd', '075000', null, '�Ŷ���', '02', '�Ŷ���', '130702', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1804ce18533e43d9a413ec6dba9fac63', 'a39aa124fea245c2b0ffbfffd0a772fd', '075000', null, '������', '03', '������', '130703', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('63bc9dc9bd8a42af80eef1220732e1c1', 'a39aa124fea245c2b0ffbfffd0a772fd', '075000', null, '������', '05', '������', '130705', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('945feda1a400414383ef63636f5f1cf8', 'a39aa124fea245c2b0ffbfffd0a772fd', '075000', null, '�»�԰��', '06', '�»�԰��', '130706', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3005ea611eb44657959d073407bc8d49', 'a39aa124fea245c2b0ffbfffd0a772fd', '075100', null, '������', '21', '������', '130721', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('515fd63216b4464f8791094cfad4e23f', 'a39aa124fea245c2b0ffbfffd0a772fd', '076450', null, '�ű���', '22', '�ű���', '130722', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6436c3486b1a4a4cacd384d5c5ffb6ed', 'a39aa124fea245c2b0ffbfffd0a772fd', '076650', null, '������', '23', '������', '130723', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fbf45edbe2664fe79ea58667043ad382', 'a39aa124fea245c2b0ffbfffd0a772fd', '076550', null, '��Դ��', '24', '��Դ��', '130724', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('422eca1df4b54ecda72f1d356584004f', 'a39aa124fea245c2b0ffbfffd0a772fd', '076750', null, '������', '25', '������', '130725', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bdf69438790d4e73ad8e422ee1d5e21f', 'a39aa124fea245c2b0ffbfffd0a772fd', '075700', null, 'ε��', '26', 'ε��', '130726', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6451f6f1b35b4cd08d94980967f50174', 'a39aa124fea245c2b0ffbfffd0a772fd', '075800', null, '��ԭ��', '27', '��ԭ��', '130727', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0a020e2ab98b43d4954c6f1325118e3f', 'a39aa124fea245c2b0ffbfffd0a772fd', '076150', null, '������', '28', '������', '130728', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7aff36442f3a4446be50efeb97bee7b8', 'a39aa124fea245c2b0ffbfffd0a772fd', '076250', null, '��ȫ��', '29', '��ȫ��', '130729', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4059bf0c7c394be69a83e745788c6d99', 'a39aa124fea245c2b0ffbfffd0a772fd', '075400', null, '������', '30', '������', '130730', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('569c51e2e5c94dbc95270346a75930b1', 'a39aa124fea245c2b0ffbfffd0a772fd', '075600', null, '��¹��', '31', '��¹��', '130731', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('688ed79f2ff542e1b677d1222c9eebf1', 'a39aa124fea245c2b0ffbfffd0a772fd', '075500', null, '�����', '32', '�����', '130732', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b1fa398ff17e468a832d4fd1bb24b2bd', 'a39aa124fea245c2b0ffbfffd0a772fd', '076350', null, '������', '33', '������', '130733', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ad6b9825e5034247970da2b328a901bd', '7cb33ba7dcc9456caac8ec92e8640636', '067000', null, '��Ͻ��', '01', '��Ͻ��', '130801', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a53ca1b59ce74e0a80d63d385aa2c643', '7cb33ba7dcc9456caac8ec92e8640636', '067000', null, '˫����', '02', '˫����', '130802', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('74393877982143feac781092b3767c14', '7cb33ba7dcc9456caac8ec92e8640636', '067000', null, '˫����', '03', '˫����', '130803', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fa9eac3743434ade8736cf44bd080920', '7cb33ba7dcc9456caac8ec92e8640636', '067200', null, 'ӥ��Ӫ�ӿ���', '04', 'ӥ��Ӫ�ӿ���', '130804', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('40883de60ec4400386106b3e780655aa', '7cb33ba7dcc9456caac8ec92e8640636', '067400', null, '�е���', '21', '�е���', '130821', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3c25b17f174541bfb2ba7642a854a940', '7cb33ba7dcc9456caac8ec92e8640636', '067300', null, '��¡��', '22', '��¡��', '130822', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f278bea6cb784b248e34205cfbd66fbe', '7cb33ba7dcc9456caac8ec92e8640636', '067500', null, 'ƽȪ��', '23', 'ƽȪ��', '130823', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ef198bc9f1db404c87c320dfa258a5b8', '7cb33ba7dcc9456caac8ec92e8640636', '068250', null, '��ƽ��', '24', '��ƽ��', '130824', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('91a1979757e044498a86b74363db63a8', '7cb33ba7dcc9456caac8ec92e8640636', '068150', null, '¡����', '25', '¡����', '130825', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7ffe4f666c2d4862b49f75ca80f3bc37', '6bdf07875675456cad494b60cf611d83', '037300', null, '��ͬ��', '27', '��ͬ��', '140227', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('eb5e5da70b6f4ecab159c7fc31d12714', '23f034f938e246278059476a291b3738', '045000', null, '��Ͻ��', '01', '��Ͻ��', '140301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('be67444325ab4647a913a15976c90b5e', '23f034f938e246278059476a291b3738', '045000', null, '����', '02', '����', '140302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('962b3ceb5f4a4b6ca0fc3e26c14f74cb', '23f034f938e246278059476a291b3738', '045000', null, '����', '03', '����', '140303', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('034db3c8afd749a480c0b47fa98499c1', '23f034f938e246278059476a291b3738', '045000', null, '����', '11', '����', '140311', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('af17a4be1b9f4b91b5c5aa4d7f94d224', '23f034f938e246278059476a291b3738', '045200', null, 'ƽ����', '21', 'ƽ����', '140321', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2ffbcfb1332e442aa46bfcc46d39e013', '23f034f938e246278059476a291b3738', '045100', null, '����', '22', '����', '140322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2a684ccfc6a945359bf6cdd1fa647a1f', '87c57bcb3f14405ab32d70bb5e6af000', '046000', null, '��Ͻ��', '01', '��Ͻ��', '140401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6701b90b83fb42c09a7b4f5d9f3608f4', '87c57bcb3f14405ab32d70bb5e6af000', '046000', null, '����', '02', '����', '140402', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('17edf7da4d914634b952761910161589', '87c57bcb3f14405ab32d70bb5e6af000', '046000', null, '����', '11', '����', '140411', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8078b3e101d144c79f8b710edfb60ac3', '87c57bcb3f14405ab32d70bb5e6af000', '046000', null, '������', '21', '������', '140421', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('511f6d9913b54c218a5b3ca32b35792d', '87c57bcb3f14405ab32d70bb5e6af000', '046200', null, '��ԫ��', '23', '��ԫ��', '140423', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6ddbb309655743bebc819b46dc159088', '87c57bcb3f14405ab32d70bb5e6af000', '046100', null, '������', '24', '������', '140424', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8f5b717acec34584821f641076c1ba5c', '87c57bcb3f14405ab32d70bb5e6af000', '047400', null, 'ƽ˳��', '25', 'ƽ˳��', '140425', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ca41184d44c945f28fdbc282c0f7b5dc', '87c57bcb3f14405ab32d70bb5e6af000', '047600', null, '�����', '26', '�����', '140426', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1430f57ccaa3451580f0faab8b4752c3', '87c57bcb3f14405ab32d70bb5e6af000', '047300', null, '������', '27', '������', '140427', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('32071bb121604240877cbe7e5c627021', '87c57bcb3f14405ab32d70bb5e6af000', '046600', null, '������', '28', '������', '140428', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('be9aafaf93364e6f92fb00cb3102a308', '87c57bcb3f14405ab32d70bb5e6af000', '046300', null, '������', '29', '������', '140429', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2a566ff2fa3843698a3a014ba14f7420', '87c57bcb3f14405ab32d70bb5e6af000', '046400', null, '����', '30', '����', '140430', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fc6acbac15ae4fdb99af95a4945f120f', '87c57bcb3f14405ab32d70bb5e6af000', '046500', null, '��Դ��', '31', '��Դ��', '140431', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ac94ee6edf1744178a7a7526e58a4644', '87c57bcb3f14405ab32d70bb5e6af000', '047500', null, 'º����', '81', 'º����', '140481', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('90e18069b12d4079b9806d026813202d', '39513e1f4dc34528937b429ee5ab7bd1', '048000', null, '��Ͻ��', '01', '��Ͻ��', '140501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c42a61b6e5fd4745adfccb6c8c2ef85f', '39513e1f4dc34528937b429ee5ab7bd1', '048000', null, '����', '02', '����', '140502', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('53a9500d506e453fb923a0cf301f36eb', '39513e1f4dc34528937b429ee5ab7bd1', '048200', null, '��ˮ��', '21', '��ˮ��', '140521', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d3e1f73bd2bb45ad930edfb306724203', '39513e1f4dc34528937b429ee5ab7bd1', '048100', null, '������', '22', '������', '140522', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3b9ae530d9284ce1bde9df909e3c454a', '39513e1f4dc34528937b429ee5ab7bd1', '048300', null, '�괨��', '24', '�괨��', '140524', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6392c7f3349c4c9183a810a92afa7191', '39513e1f4dc34528937b429ee5ab7bd1', '048000', null, '������', '25', '������', '140525', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e609b4dcad2f42a1ae41392bb3e3a677', '39513e1f4dc34528937b429ee5ab7bd1', '046700', null, '��ƽ��', '81', '��ƽ��', '140581', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('de82f836f82d4f3e81cd3d27b7130ebe', '3a78300165d5408f878939f11f57fb8e', '038500', null, '��Ͻ��', '01', '��Ͻ��', '140601', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('29ad4bf32fe94f50b0386566c161bc6f', '3a78300165d5408f878939f11f57fb8e', '038500', null, '˷����', '02', '˷����', '140602', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('964d4dd50bef48c9b62c8981ec6d9336', '3a78300165d5408f878939f11f57fb8e', '038500', null, 'ƽ³��', '03', 'ƽ³��', '140603', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('095e5ac6a2454a829f35e53e41c6c05e', '3a78300165d5408f878939f11f57fb8e', '038400', null, 'ɽ����', '21', 'ɽ����', '140621', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7fedf45a4199497e8518c58c43a2969a', '3a78300165d5408f878939f11f57fb8e', '037600', null, 'Ӧ��', '22', 'Ӧ��', '140622', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('861787f236d94d47b2b6b3568ec6abbc', '3a78300165d5408f878939f11f57fb8e', '037200', null, '������', '23', '������', '140623', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('29ffe65544ac4df2b6c67991d4766b74', '3a78300165d5408f878939f11f57fb8e', '038300', null, '������', '24', '������', '140624', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9b4d258586794478a38e9f56124cccaf', '38ee852f7125442d8c90540ecf8fbfcc', '030600', null, '��Ͻ��', '01', '��Ͻ��', '140701', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6e174e6bbfd644839b0b86108f223920', '38ee852f7125442d8c90540ecf8fbfcc', '030600', null, '�ܴ���', '02', '�ܴ���', '140702', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5bb5cafc0d4c48dda83c229ca1650c54', '38ee852f7125442d8c90540ecf8fbfcc', '031800', null, '������', '21', '������', '140721', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('15a4e461220b4b8c9b87f321046048c4', '38ee852f7125442d8c90540ecf8fbfcc', '032600', null, '��Ȩ��', '22', '��Ȩ��', '140722', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('31c9c37439e249b38a2a83daadcb87b6', '38ee852f7125442d8c90540ecf8fbfcc', '032700', null, '��˳��', '23', '��˳��', '140723', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aaa6b533af274bf6a4a2321dc02a1030', 'dc525fdeeb4d4d4a93e1a8ee410cbb15', '010000', null, '��Ȫ��', '04', '��Ȫ��', '150104', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('83e171d48a9b4a81907b3d79bb64508e', 'dc525fdeeb4d4d4a93e1a8ee410cbb15', '010000', null, '������', '05', '������', '150105', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('947d71e5a72f4f7e8e826fc80d1be96a', 'dc525fdeeb4d4d4a93e1a8ee410cbb15', '010100', null, '��Ĭ������', '21', '��Ĭ������', '150121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9d611d0af77f4c88afb41aae6cb753cd', 'dc525fdeeb4d4d4a93e1a8ee410cbb15', '010200', null, '�п�����', '22', '�п�����', '150122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dcad9dad0aa6403aa4aab785d87f5bbb', 'dc525fdeeb4d4d4a93e1a8ee410cbb15', '011500', null, '���ָ����', '23', '���ָ����', '150123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('846f95f34462486e99640049a6c1b102', 'dc525fdeeb4d4d4a93e1a8ee410cbb15', '011600', null, '��ˮ����', '24', '��ˮ����', '150124', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fe36ca85253f4f1b81bcb5d783185a0f', 'dc525fdeeb4d4d4a93e1a8ee410cbb15', '011700', null, '�䴨��', '25', '�䴨��', '150125', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e9c7c81df00c446c8150affcd854a394', 'f52d20e8efa14e458b7e12d803073951', '014000', null, '��Ͻ��', '01', '��Ͻ��', '150201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b8c1640596cc41558ea91fadc480896b', 'f52d20e8efa14e458b7e12d803073951', '014000', null, '������', '02', '������', '150202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d1f06bab5948465b8c36c8458dbde44f', 'f52d20e8efa14e458b7e12d803073951', '014000', null, '��������', '03', '��������', '150203', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('412e5b8438b24a35b64646ceccacc5bb', 'f52d20e8efa14e458b7e12d803073951', '014000', null, '��ɽ��', '04', '��ɽ��', '150204', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('62b213d6aef04e55b0691f2d8fe3506f', 'f52d20e8efa14e458b7e12d803073951', '014000', null, 'ʯ����', '05', 'ʯ����', '150205', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2cc76515069745b6892ecafbe421f4d3', 'f52d20e8efa14e458b7e12d803073951', '014000', null, '���ƿ���', '06', '���ƿ���', '150206', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('45da2347afce4edcb070dcc837a485cd', 'f52d20e8efa14e458b7e12d803073951', '014000', null, '��ԭ��', '07', '��ԭ��', '150207', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('044b759c45bb4ea897ebe489d5e272c1', 'f52d20e8efa14e458b7e12d803073951', '014100', null, '��Ĭ������', '21', '��Ĭ������', '150221', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('08d20aff6dba4304994e67687bc1c7e7', 'f52d20e8efa14e458b7e12d803073951', '014200', null, '������', '22', '������', '150222', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('72650270e2df4222806faca054cbffb0', 'f52d20e8efa14e458b7e12d803073951', '014500', null, '�����ï����������', '23', '�����ï����������', '150223', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('96dc4a9b168342adb83c0dc42cb66087', '746ab0b6b0974a89b45cde14f80c15be', '016000', null, '��Ͻ��', '01', '��Ͻ��', '150301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fa0577b49c6c45c7a1bad3139917c246', '746ab0b6b0974a89b45cde14f80c15be', '016000', null, '��������', '02', '��������', '150302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bddd42d9a2c9480a8f74688e98b40e18', '746ab0b6b0974a89b45cde14f80c15be', '016000', null, '������', '03', '������', '150303', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a056db900e15428089631ac67ebd0005', '746ab0b6b0974a89b45cde14f80c15be', '016000', null, '�ڴ���', '04', '�ڴ���', '150304', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3c9c215df86a40b0b1ef5ed0b1c06302', 'd740c573976d40e880ce13ec316fdda9', '024000', null, '��Ͻ��', '01', '��Ͻ��', '150401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9a7d4908393643eba3d2423fd5507a02', 'b1661da6929548f58fc4ac41d14d2b89', '055450', null, '������', '24', '������', '130524', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b73c6dad6de342b2b2bd67c778e4235f', 'b34159526635417eac1df82dd9c7859b', '272000', null, '������', '82', '������', '370882', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8b4428f9c6ce40699e6cd287de3ef590', 'b34159526635417eac1df82dd9c7859b', '273500', null, '�޳���', '83', '�޳���', '370883', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fabd48dbec2040df8a071f44875d149b', 'f04b7fdbaab14550914ec25ef9299498', '271000', null, '��Ͻ��', '01', '��Ͻ��', '370901', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6bde0d56455a4955bf84eeb603fe7267', 'f04b7fdbaab14550914ec25ef9299498', '271000', null, '̩ɽ��', '02', '̩ɽ��', '370902', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2a160fecd3a5457eb3614e1bb98ca792', 'f04b7fdbaab14550914ec25ef9299498', '271000', null, '�����', '03', '�����', '370903', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c329858962344f38bd22b85cdaf57d95', 'f04b7fdbaab14550914ec25ef9299498', '271400', null, '������', '21', '������', '370921', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fd958ed79f7a4601bec87253ac51f7ee', 'f04b7fdbaab14550914ec25ef9299498', '271500', null, '��ƽ��', '23', '��ƽ��', '370923', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8573632193fa4f3c91f79bd623ca1105', 'f04b7fdbaab14550914ec25ef9299498', '271200', null, '��̩��', '82', '��̩��', '370982', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2a682dc2fea8437b840f91ca5b0e7308', 'f04b7fdbaab14550914ec25ef9299498', '271600', null, '�ʳ���', '83', '�ʳ���', '370983', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6360f3f5b2694f31887554e48beb3701', 'a39d0fc8049d4c8e8ac862df4bbb7ebc', '264000', null, '��Ͻ��', '01', '��Ͻ��', '371001', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ead14d9b549843cc82d4bff538dd79c4', 'a39d0fc8049d4c8e8ac862df4bbb7ebc', '264200', null, '������', '02', '������', '371002', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f95df306d9bd48a5b72474ebe9085760', 'a39d0fc8049d4c8e8ac862df4bbb7ebc', '264400', null, '�ĵ���', '81', '�ĵ���', '371081', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3de8adb9eea84ea4b1f394abada07e15', 'a39d0fc8049d4c8e8ac862df4bbb7ebc', '264300', null, '�ٳ���', '82', '�ٳ���', '371082', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('67e216b4da924e48931982a998793943', 'a39d0fc8049d4c8e8ac862df4bbb7ebc', '264500', null, '��ɽ��', '83', '��ɽ��', '371083', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cf222d85cdb44339a2e93a8a5d887064', '3344436933104790acb9de9e63a859e9', '276800', null, '��Ͻ��', '01', '��Ͻ��', '371101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('74b01b66ca0344c09a9eea07f14f0bba', '3344436933104790acb9de9e63a859e9', '276800', null, '������', '02', '������', '371102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9a717bbf6c754b43a14014e11912f2d9', '3344436933104790acb9de9e63a859e9', '276800', null, '�ɽ��', '03', '�ɽ��', '371103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('650a30c0bc27482d9080a061a745b469', '3344436933104790acb9de9e63a859e9', '262300', null, '������', '21', '������', '371121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ae130d4713b24c8b8b61491684ed7de1', '3344436933104790acb9de9e63a859e9', '276500', null, '����', '22', '����', '371122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4fb0e96d617f4441acb2d5539a338c26', '7844d926e0c94aeca55889ee6ede41d8', '271100', null, '��Ͻ��', '01', '��Ͻ��', '371201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f9f2a49bfbc446d8a921ee31056444ad', '7844d926e0c94aeca55889ee6ede41d8', '271100', null, '������', '02', '������', '371202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c0e358db08404c09ad57713bebe86b2d', '7844d926e0c94aeca55889ee6ede41d8', '271100', null, '�ֳ���', '03', '�ֳ���', '371203', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('02fae101a73d4633801309b6f4e1aa65', 'ef87eb2c68254dda8ded0308e20e4dac', '276000', null, '��Ͻ��', '01', '��Ͻ��', '371301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('473a3ae4601b49249a3dcfc5110a2dbf', 'ef87eb2c68254dda8ded0308e20e4dac', '276000', null, '��ɽ��', '02', '��ɽ��', '371302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('10b2650e10d347fab62c94e946e3f2b2', 'ef87eb2c68254dda8ded0308e20e4dac', '276000', null, '��ׯ��', '11', '��ׯ��', '371311', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4b166e2e03d544bd9673bfb5541189cc', 'ef87eb2c68254dda8ded0308e20e4dac', '276000', null, '�Ӷ���', '12', '�Ӷ���', '371312', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6bd8dcb9e2f84d66be5738f89786e980', 'ef87eb2c68254dda8ded0308e20e4dac', '276300', null, '������', '21', '������', '371321', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e19c4b3f237b4c0795bb4cf1b7f22b74', 'ef87eb2c68254dda8ded0308e20e4dac', '276100', null, '۰����', '22', '۰����', '371322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('91bf7b227c3f4bd084cf312d14ce1ae4', 'ef87eb2c68254dda8ded0308e20e4dac', '276400', null, '��ˮ��', '23', '��ˮ��', '371323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0700732fb342431c871d64c14b95d7d6', 'ef87eb2c68254dda8ded0308e20e4dac', '277700', null, '��ɽ��', '24', '��ɽ��', '371324', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e367aef2581e4bc48ad8ad2da5f077dc', 'ef87eb2c68254dda8ded0308e20e4dac', '273400', null, '����', '25', '����', '371325', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('44eb7202423c4071b76fda37a1672ae3', 'ef87eb2c68254dda8ded0308e20e4dac', '273300', null, 'ƽ����', '26', 'ƽ����', '371326', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('90e584032fe54f95acc6785e4791acfe', 'ef87eb2c68254dda8ded0308e20e4dac', '276600', null, '������', '27', '������', '371327', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b8c4f1db0c9648ce853b351d83befac4', 'ef87eb2c68254dda8ded0308e20e4dac', '276200', null, '������', '28', '������', '371328', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8dbf259716e74774a17b75d11e5c2e63', 'ef87eb2c68254dda8ded0308e20e4dac', '276700', null, '������', '29', '������', '371329', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9019261eac9241a2b9d10d2b9b5cd466', '0dc3aad6447a4a92818dc72d59bb1a44', '253000', null, '��Ͻ��', '01', '��Ͻ��', '371401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cf33d24f3aef4a00aba7cdfa86e5840c', '0dc3aad6447a4a92818dc72d59bb1a44', '253000', null, '�³���', '02', '�³���', '371402', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b2e74ae857b34787a20fc1b0f4830d2f', '0dc3aad6447a4a92818dc72d59bb1a44', '253500', null, '����', '21', '����', '371421', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2e969a1fc3784c39aed85e8b50838565', '0dc3aad6447a4a92818dc72d59bb1a44', '253400', null, '������', '22', '������', '371422', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('937df3535b6b4465a94ae1b1b17cedc5', '0dc3aad6447a4a92818dc72d59bb1a44', '253700', null, '������', '23', '������', '371423', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ef2d68912d884895b7c116e41883e3df', '0dc3aad6447a4a92818dc72d59bb1a44', '251500', null, '������', '24', '������', '371424', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ee830df5504344c9bbf04d1b6789066c', '0dc3aad6447a4a92818dc72d59bb1a44', '251100', null, '�����', '25', '�����', '371425', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('be512cdb35c9472eb29f7f3990d60d4e', '0dc3aad6447a4a92818dc72d59bb1a44', '253100', null, 'ƽԭ��', '26', 'ƽԭ��', '371426', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('371b8a1c806c486ba8d273e7bf2c7470', '0dc3aad6447a4a92818dc72d59bb1a44', '253200', null, '�Ľ���', '27', '�Ľ���', '371427', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c3bb20246b5d44f5a16bcd64d573cf90', '0dc3aad6447a4a92818dc72d59bb1a44', '253300', null, '�����', '28', '�����', '371428', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d970cf05f8284d00bbc44a746598ea31', '0dc3aad6447a4a92818dc72d59bb1a44', '253600', null, '������', '81', '������', '371481', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3a154a70dcd9470d92a19219264a80c6', '38ee852f7125442d8c90540ecf8fbfcc', '045300', null, '������', '24', '������', '140724', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2ecd0ebc4a93434d8922f494c908bbfc', '38ee852f7125442d8c90540ecf8fbfcc', '031700', null, '������', '25', '������', '140725', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c906c5e504fb4c1fa697cf7fecd99129', '38ee852f7125442d8c90540ecf8fbfcc', '030800', null, '̫����', '26', '̫����', '140726', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0e687bfcebac4ec29fcf2642d563a3f9', '38ee852f7125442d8c90540ecf8fbfcc', '030900', null, '����', '27', '����', '140727', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7613fc9781ab41ecb7f8dab60b0b2398', '38ee852f7125442d8c90540ecf8fbfcc', '031100', null, 'ƽң��', '28', 'ƽң��', '140728', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('67d8a0c0aae94e598a53d74db3742bd4', '38ee852f7125442d8c90540ecf8fbfcc', '031300', null, '��ʯ��', '29', '��ʯ��', '140729', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bc19c3dfe5bb4c7fb1b6745e44311a44', '38ee852f7125442d8c90540ecf8fbfcc', '031200', null, '������', '81', '������', '140781', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('12bbce2056c04be99dd6b7f5024deb87', 'a38ecbcdd84a434280f6a45b609558ad', '044000', null, '��Ͻ��', '01', '��Ͻ��', '140801', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cee8d97a190a4514b92ad9b02ddbdbe7', 'a38ecbcdd84a434280f6a45b609558ad', '044000', null, '�κ���', '02', '�κ���', '140802', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('56746a1c830f425ea23fb2fff5b38c03', 'a38ecbcdd84a434280f6a45b609558ad', '044100', null, '�����', '21', '�����', '140821', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('49ce98edec3c4c4d9d6adb1e20655ae3', 'a38ecbcdd84a434280f6a45b609558ad', '044200', null, '������', '22', '������', '140822', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b9025e12217f4f1b8e158077a731ab2f', 'a38ecbcdd84a434280f6a45b609558ad', '043800', null, '��ϲ��', '23', '��ϲ��', '140823', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f197e058522043779d7f0065b3c6ff4e', 'a38ecbcdd84a434280f6a45b609558ad', '043200', null, '�ɽ��', '24', '�ɽ��', '140824', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d04766511b7e4c749f4012c8072ad7d8', 'a38ecbcdd84a434280f6a45b609558ad', '043100', null, '�����', '25', '�����', '140825', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f62b3ab567674ef79481bd0f3528e7b2', 'a38ecbcdd84a434280f6a45b609558ad', '043600', null, '���', '26', '���', '140826', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8ed3b0fdfaa249ab880986d10a89adfa', 'a38ecbcdd84a434280f6a45b609558ad', '043700', null, 'ԫ����', '27', 'ԫ����', '140827', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aa4929ccd44c4f59905aa0bcdb7041d9', 'a38ecbcdd84a434280f6a45b609558ad', '044400', null, '����', '28', '����', '140828', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4a1ab9bd7026481181d4c3854047c564', 'a38ecbcdd84a434280f6a45b609558ad', '044300', null, 'ƽ½��', '29', 'ƽ½��', '140829', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6718d9cf2c7d42e9bd5d2c76ce493d81', 'a38ecbcdd84a434280f6a45b609558ad', '044600', null, '�ǳ���', '30', '�ǳ���', '140830', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8ee8ffd5f71e4b75931e6cf85bb79b3a', 'a38ecbcdd84a434280f6a45b609558ad', '044500', null, '������', '81', '������', '140881', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4b20cc3bdf7d43eea6d930008bf08b1f', 'a38ecbcdd84a434280f6a45b609558ad', '043300', null, '�ӽ���', '82', '�ӽ���', '140882', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2abcac830dbe4c65b57919f07b9a86c3', '264018e21b854c78866dd48622f2d326', '034000', null, '��Ͻ��', '01', '��Ͻ��', '140901', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5f4a7e8d9a3c4501a25530c3325996c9', '264018e21b854c78866dd48622f2d326', '034000', null, '�ø���', '02', '�ø���', '140902', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f2ad0b5ac4b34677bb40688f2c21a89e', '264018e21b854c78866dd48622f2d326', '035400', null, '������', '21', '������', '140921', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('46ebeee4199448b384cc820ad8eb0656', '264018e21b854c78866dd48622f2d326', '035500', null, '��̨��', '22', '��̨��', '140922', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b50d5d44171d484eb2017c598bd0ec6b', '264018e21b854c78866dd48622f2d326', '034200', null, '����', '23', '����', '140923', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('302f606adff6466ab441b5dbc007c85c', '264018e21b854c78866dd48622f2d326', '034300', null, '������', '24', '������', '140924', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('48e7437afbfa4b9384056edd6c4f54cc', '264018e21b854c78866dd48622f2d326', '035100', null, '������', '26', '������', '140926', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('620d1ea79eff4212b5f044920225893a', '264018e21b854c78866dd48622f2d326', '036100', null, '�����', '27', '�����', '140927', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3abd24f7cbff4b21838af30ee9ce50c9', '264018e21b854c78866dd48622f2d326', '036200', null, '��կ��', '28', '��կ��', '140928', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('61ba01eecce44de686cf7e0e926d87fb', '264018e21b854c78866dd48622f2d326', '036300', null, '����', '29', '����', '140929', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('913cff4f1eb14c0397aa13368b8e9d4d', '264018e21b854c78866dd48622f2d326', '036500', null, '������', '30', '������', '140930', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1cf81dfc77874b9d9553b0ee34abb1aa', '264018e21b854c78866dd48622f2d326', '036600', null, '������', '31', '������', '140931', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8f46a7df564f449ead83ff990c705853', '264018e21b854c78866dd48622f2d326', '036400', null, 'ƫ����', '32', 'ƫ����', '140932', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6835bd2ffd7a4c8ab5af6c4b0c41bfdb', '264018e21b854c78866dd48622f2d326', '034100', null, 'ԭƽ��', '81', 'ԭƽ��', '140981', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('af32776d0bc24af3bccfb7e711380eb7', 'f01f08e264f240c78e12fea9d8d2c41a', '041000', null, '��Ͻ��', '01', '��Ͻ��', '141001', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5ef3d1b138c54fc8becb54d608c0c4dd', 'f01f08e264f240c78e12fea9d8d2c41a', '041000', null, 'Ң����', '02', 'Ң����', '141002', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('30aaf52391964371878d5196ae135070', 'f01f08e264f240c78e12fea9d8d2c41a', '043400', null, '������', '21', '������', '141021', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('24dac856bfb744868e887b678b7fe36b', 'f01f08e264f240c78e12fea9d8d2c41a', '043500', null, '������', '22', '������', '141022', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a19192a013df4e8490970f35b0a0e0b3', 'f01f08e264f240c78e12fea9d8d2c41a', '041500', null, '�����', '23', '�����', '141023', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('55e37726dc7b4ec08e97145d4a04059b', 'f01f08e264f240c78e12fea9d8d2c41a', '031600', null, '�鶴��', '24', '�鶴��', '141024', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5da54d06a34a4e6ea62a1492d9d7d0d6', 'f01f08e264f240c78e12fea9d8d2c41a', '042400', null, '����', '25', '����', '141025', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('352ee25e1bd345da9e9f8614197bc553', 'f01f08e264f240c78e12fea9d8d2c41a', '042500', null, '������', '26', '������', '141026', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('12545d65f1404790875c3a9769a4eedb', 'f01f08e264f240c78e12fea9d8d2c41a', '042600', null, '��ɽ��', '27', '��ɽ��', '141027', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c1190ff09d0249c88a9bd1b55569a8b9', 'f01f08e264f240c78e12fea9d8d2c41a', '042200', null, '����', '28', '����', '141028', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0f5b28d122e24e18a26aaf3100b60ec2', 'f01f08e264f240c78e12fea9d8d2c41a', '042100', null, '������', '29', '������', '141029', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aa55af7cbd994a63afee28d5c5fcde0c', 'f01f08e264f240c78e12fea9d8d2c41a', '042300', null, '������', '30', '������', '141030', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a04c003dcffe43388aedaf35a87589aa', 'f01f08e264f240c78e12fea9d8d2c41a', '041300', null, '����', '31', '����', '141031', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('eb6222aef95447ae9f93810e60ea15f0', 'f01f08e264f240c78e12fea9d8d2c41a', '041400', null, '������', '32', '������', '141032', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1ad35eb650bb43ba97b876794178bb89', 'f01f08e264f240c78e12fea9d8d2c41a', '041200', null, '����', '33', '����', '141033', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5609a41f5211452c8f33c58b80e5834e', 'f01f08e264f240c78e12fea9d8d2c41a', '031500', null, '������', '34', '������', '141034', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('19952609fd3040d2bde9bdb48e563f78', 'f01f08e264f240c78e12fea9d8d2c41a', '043000', null, '������', '81', '������', '141081', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e50b42dd754c41bfb492f2c94e2db831', 'f01f08e264f240c78e12fea9d8d2c41a', '031400', null, '������', '82', '������', '141082', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6811640211f945899d6559880f28eecf', '246df1df62e141c68397e33cc08f5adf', '033000', null, '��Ͻ��', '01', '��Ͻ��', '141101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('581367ab9f4947cbaa6debb7cbc0b08a', '246df1df62e141c68397e33cc08f5adf', '033000', null, '��ʯ��', '02', '��ʯ��', '141102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4c69c7e1f8394887b30914b60f9e6cc0', '246df1df62e141c68397e33cc08f5adf', '032100', null, '��ˮ��', '21', '��ˮ��', '141121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('adbde55b2c614c7ab30441d21442e357', '246df1df62e141c68397e33cc08f5adf', '030500', null, '������', '22', '������', '141122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5d43561218d844cc9d100cf353b418ff', '246df1df62e141c68397e33cc08f5adf', '035300', null, '����', '23', '����', '141123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b0351e0129844cc6a25ebc36cef08b41', '246df1df62e141c68397e33cc08f5adf', '033200', null, '����', '24', '����', '141124', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('504ee72aacaf47febd87248fdff03da2', '246df1df62e141c68397e33cc08f5adf', '033300', null, '������', '25', '������', '141125', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('402fa8a3daee435993cfa4e9a365267b', '246df1df62e141c68397e33cc08f5adf', '032500', null, 'ʯ¥��', '26', 'ʯ¥��', '141126', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('82cc48b7b5c749e78f3896982c85d014', '246df1df62e141c68397e33cc08f5adf', '035200', null, '���', '27', '���', '141127', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bcd71751a3924b3ea0547f493bf5f199', '246df1df62e141c68397e33cc08f5adf', '033100', null, '��ɽ��', '28', '��ɽ��', '141128', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('17ff75b15f244604ba7738af63dd0300', '246df1df62e141c68397e33cc08f5adf', '033400', null, '������', '29', '������', '141129', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dd9015f7fb7a4e9c8cbf01105454935b', '246df1df62e141c68397e33cc08f5adf', '032400', null, '������', '30', '������', '141130', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('650313fbb4444d45b7328541feff8622', '246df1df62e141c68397e33cc08f5adf', '032300', null, 'Т����', '81', 'Т����', '141181', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('14d02bbe448b462583def46663277f36', '246df1df62e141c68397e33cc08f5adf', '032200', null, '������', '82', '������', '141182', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2b9d372059984c2eb2b8fb3e187d98fc', 'dc525fdeeb4d4d4a93e1a8ee410cbb15', '010000', null, '��Ͻ��', '01', '��Ͻ��', '150101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('079b17fb41cb4f6bb9950bbd3c1320aa', 'dc525fdeeb4d4d4a93e1a8ee410cbb15', '010000', null, '�³���', '02', '�³���', '150102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a9aef79fde404bca9158ad2869f1a6c1', 'dc525fdeeb4d4d4a93e1a8ee410cbb15', '010000', null, '������', '03', '������', '150103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('baa17a588482457a8549ea1fbfb118cc', 'cbf57834e6314708abbbb577e400e698', '050500', null, '������', '26', '������', '130126', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a0dac0b1b7ff4e12be73c135e500dae2', 'cbf57834e6314708abbbb577e400e698', '051330', null, '������', '27', '������', '130127', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('eac50389544743c290a2355c952aadc6', 'cbf57834e6314708abbbb577e400e698', '052500', null, '������', '28', '������', '130128', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bbdeae76c7ed4e31898772a2c2c50f9f', 'cbf57834e6314708abbbb577e400e698', '051230', null, '�޻���', '29', '�޻���', '130129', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('971de97bfb714c6aab74d68540a8a0eb', 'cbf57834e6314708abbbb577e400e698', '052400', null, '�޼���', '30', '�޼���', '130130', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f21321500c3f4dde95da60b3f0dc183c', 'cbf57834e6314708abbbb577e400e698', '050400', null, 'ƽɽ��', '31', 'ƽɽ��', '130131', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b47ad47df3144017b85aff600426c8dc', 'cbf57834e6314708abbbb577e400e698', '051130', null, 'Ԫ����', '32', 'Ԫ����', '130132', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('115ff955eb24492f9e6440fe3419f41b', 'cbf57834e6314708abbbb577e400e698', '051530', null, '����', '33', '����', '130133', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('90ee9675129d4ff18ff6632591b2d3f3', 'cbf57834e6314708abbbb577e400e698', '052300', null, '������', '81', '������', '130181', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('91a6887d2b9b4b9993d4ec23a8e76261', 'cbf57834e6314708abbbb577e400e698', '052160', null, '޻����', '82', '޻����', '130182', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1a9417dd48704efc87ced3a5daff4796', 'cbf57834e6314708abbbb577e400e698', '052200', null, '������', '83', '������', '130183', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a949fc32634a49e7842019cd2d173920', 'cbf57834e6314708abbbb577e400e698', '050700', null, '������', '84', '������', '130184', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9d077d035f2f456fb59a6f1954b136ae', 'cbf57834e6314708abbbb577e400e698', '050200', null, '¹Ȫ��', '85', '¹Ȫ��', '130185', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3a1b44126800481490755c695d4f7f69', 'eb9674438d3b4d568aa8119ea8881a91', '063000', null, '��Ͻ��', '01', '��Ͻ��', '130201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e8a4facc75cc40638ce426a5bdc1d8e0', 'eb9674438d3b4d568aa8119ea8881a91', '063000', null, '·����', '02', '·����', '130202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7f18f84aedec4f0db65971d173afc6bd', 'eb9674438d3b4d568aa8119ea8881a91', '063000', null, '·����', '03', '·����', '130203', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('952f588c28b94c97a53850135cce67fb', 'eb9674438d3b4d568aa8119ea8881a91', '063000', null, '��ұ��', '04', '��ұ��', '130204', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('57fa9d89e9e8453b87458652176c5500', 'eb9674438d3b4d568aa8119ea8881a91', '063000', null, '��ƽ��', '05', '��ƽ��', '130205', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3498e9226eea41a49163397b63b1a0a6', 'eb9674438d3b4d568aa8119ea8881a91', '063300', null, '������', '07', '������', '130207', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9056dd864c07498caea12d79782757bf', 'eb9674438d3b4d568aa8119ea8881a91', '063000', null, '������', '08', '������', '130208', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1761ceef11a846b8adb5c66dc60a7b90', 'eb9674438d3b4d568aa8119ea8881a91', '063700', null, '����', '23', '����', '130223', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ec397fdb9083406e9d38fdeef2773081', 'eb9674438d3b4d568aa8119ea8881a91', '063500', null, '������', '24', '������', '130224', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('84191657686f4a58b62477101d4f2148', 'eb9674438d3b4d568aa8119ea8881a91', '063600', null, '��ͤ��', '25', '��ͤ��', '130225', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('61ffbdcdad8e4c64ab34c81124c6b657', 'eb9674438d3b4d568aa8119ea8881a91', '064300', null, 'Ǩ����', '27', 'Ǩ����', '130227', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('73344bc43f3443d8ad8fa5ac4f66bfba', 'eb9674438d3b4d568aa8119ea8881a91', '064100', null, '������', '29', '������', '130229', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ddebf5a2889646c2878033c3679ea706', 'eb9674438d3b4d568aa8119ea8881a91', '063200', null, '�ƺ���', '30', '�ƺ���', '130230', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0878b2cfd8f34d119e5187a768602b3c', 'eb9674438d3b4d568aa8119ea8881a91', '064200', null, '����', '81', '����', '130281', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fe51187e39cd4d6490ddf3df6de5b7d6', 'eb9674438d3b4d568aa8119ea8881a91', '064400', null, 'Ǩ����', '83', 'Ǩ����', '130283', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e33b11f79a674b55938bc232476ec0ed', '0c198b04fac8436fbd64f504af86ed74', '066000', null, '��Ͻ��', '01', '��Ͻ��', '130301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('83100b9338f643be91d6d7450eef83f2', '0c198b04fac8436fbd64f504af86ed74', '066000', null, '������', '02', '������', '130302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fafe9d5da9fc40ff859a4db253c2785a', '0c198b04fac8436fbd64f504af86ed74', '066200', null, 'ɽ������', '03', 'ɽ������', '130303', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0ecda5b707034efbbf2fc5afba80da2b', '0c198b04fac8436fbd64f504af86ed74', '066100', null, '��������', '04', '��������', '130304', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0d4434be5e9c440dbe20e64f635bf624', '0c198b04fac8436fbd64f504af86ed74', '066500', null, '��������������', '21', '��������������', '130321', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('56a689958f6b4c8b995b059372881cef', '0c198b04fac8436fbd64f504af86ed74', '066600', null, '������', '22', '������', '130322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('11cc57151d174c4a856865c9cbac6180', '0c198b04fac8436fbd64f504af86ed74', '066300', null, '������', '23', '������', '130323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1c3fd33ee6b14bc4b68280a0639744c4', '0c198b04fac8436fbd64f504af86ed74', '066400', null, '¬����', '24', '¬����', '130324', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e88c1dfa2b034ffeacac8d0e7093808b', 'c6db83b3a6b24136bffb63610712f5de', '056000', null, '��Ͻ��', '01', '��Ͻ��', '130401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4f56a0b05576432f9d57814c943e7bf6', 'c6db83b3a6b24136bffb63610712f5de', '056000', null, '��ɽ��', '02', '��ɽ��', '130402', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4c4e3bcacce940458c44c667265ed96d', 'c6db83b3a6b24136bffb63610712f5de', '056000', null, '��̨��', '03', '��̨��', '130403', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('79b59cb7c9db41c399fe26b4356c3c4c', 'c6db83b3a6b24136bffb63610712f5de', '056000', null, '������', '04', '������', '130404', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a29a1f5383d245c098a150df498cc3cc', 'c6db83b3a6b24136bffb63610712f5de', '056200', null, '������', '06', '������', '130406', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('79ea42b462774142999854508ad3dd79', 'c6db83b3a6b24136bffb63610712f5de', '056000', null, '������', '21', '������', '130421', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('31f7b481a00349348c990e642fb0c58c', 'c6db83b3a6b24136bffb63610712f5de', '056600', null, '������', '23', '������', '130423', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7cd4ae18d98a47789d4542207c29cb21', 'c6db83b3a6b24136bffb63610712f5de', '056700', null, '�ɰ���', '24', '�ɰ���', '130424', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b5499fe42541494ba756b398748219a4', 'c6db83b3a6b24136bffb63610712f5de', '056900', null, '������', '25', '������', '130425', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b3fae10223b2438290f03dfc29630982', 'c6db83b3a6b24136bffb63610712f5de', '056400', null, '����', '26', '����', '130426', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f51a7c73393240698debefbb8fd07981', 'c6db83b3a6b24136bffb63610712f5de', '056500', null, '����', '27', '����', '130427', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2840af9e8b784705a20cd1254e00fa3e', 'c6db83b3a6b24136bffb63610712f5de', '057550', null, '������', '28', '������', '130428', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2b0fb1ca2d8c40ba981a3233b323da89', 'c6db83b3a6b24136bffb63610712f5de', '057150', null, '������', '29', '������', '130429', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f119b8b1d0ed4c5eba42ba6f58289db2', 'c6db83b3a6b24136bffb63610712f5de', '057450', null, '����', '30', '����', '130430', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cd3b56e8175c4c51b10191a5a7f5ff53', 'c6db83b3a6b24136bffb63610712f5de', '057350', null, '������', '31', '������', '130431', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6149d8fca7854cf0955e99098527a900', 'c6db83b3a6b24136bffb63610712f5de', '057650', null, '��ƽ��', '32', '��ƽ��', '130432', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('39fb48cfc2b04f17bdf41dd17e22420a', 'c6db83b3a6b24136bffb63610712f5de', '057750', null, '������', '33', '������', '130433', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0f911fe820d3418dbd3e3455c98b7b88', 'c6db83b3a6b24136bffb63610712f5de', '056800', null, 'κ��', '34', 'κ��', '130434', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7187b8b1033e48fc9df00f991bdfdbff', 'c6db83b3a6b24136bffb63610712f5de', '057250', null, '������', '35', '������', '130435', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fc0faa8286604eb1828ed6d05807a845', 'c6db83b3a6b24136bffb63610712f5de', '056300', null, '�䰲��', '81', '�䰲��', '130481', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('00ebb2441059466cbb9c1074dfe64c87', 'b1661da6929548f58fc4ac41d14d2b89', '054000', null, '��Ͻ��', '01', '��Ͻ��', '130501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('19933fd8681d46a888d2c483e5d3cb3a', 'b1661da6929548f58fc4ac41d14d2b89', '054000', null, '�Ŷ���', '02', '�Ŷ���', '130502', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f443e4f903d34a55b54c01a71ae546e3', 'b1661da6929548f58fc4ac41d14d2b89', '054000', null, '������', '03', '������', '130503', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0e6a53fa392a478d8fcecfc9ab26b79f', 'b1661da6929548f58fc4ac41d14d2b89', '054000', null, '��̨��', '21', '��̨��', '130521', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('479ce1e593b647e3a4e19ed5e822a0db', 'b1661da6929548f58fc4ac41d14d2b89', '054300', null, '�ٳ���', '22', '�ٳ���', '130522', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ec08f816e3fd4e9893ceebbc4b48f215', 'b1661da6929548f58fc4ac41d14d2b89', '054200', null, '������', '23', '������', '130523', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('71705e5963b74a2aa26ee1c8035e3cd4', 'bd2d77f833b2472d8a828a90f402f861', '131300', null, '����', '82', '����', '220882', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a99a6e1e37444388bd37a30e2560f74e', 'd740c573976d40e880ce13ec316fdda9', '024000', null, '��ɽ��', '02', '��ɽ��', '150402', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5bd9c996a097442daf7adb5a62437ccc', 'd740c573976d40e880ce13ec316fdda9', '024000', null, 'Ԫ��ɽ��', '03', 'Ԫ��ɽ��', '150403', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7ba5fcf148844f0aa36b54f8d92f243b', 'd740c573976d40e880ce13ec316fdda9', '024000', null, '��ɽ��', '04', '��ɽ��', '150404', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('59e5792ffdab44c184a9d653b6be2928', '33bc3bb1a9dc4533b9de70de59c1ec1c', null, null, '������', '06', '������', '430600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3d3589d39ff2453ba5f0cfc69cb129e5', '33bc3bb1a9dc4533b9de70de59c1ec1c', null, null, '������', '07', '������', '430700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0f4a3711d5f4424aabfb4f4be55050da', '33bc3bb1a9dc4533b9de70de59c1ec1c', null, null, '�żҽ���', '08', '�żҽ���', '430800', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4ebefadf49c442bb86cd3047a05b1859', '33bc3bb1a9dc4533b9de70de59c1ec1c', null, null, '������', '09', '������', '430900', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('73cb965eec364b56bd1f37da06615a45', '33bc3bb1a9dc4533b9de70de59c1ec1c', null, null, '������', '10', '������', '431000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fd915aadafda49b68d1cf622418a9f82', '33bc3bb1a9dc4533b9de70de59c1ec1c', null, null, '������', '11', '������', '431100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7b81bf0743fd447ebdf08758d3308330', '33bc3bb1a9dc4533b9de70de59c1ec1c', null, null, '������', '12', '������', '431200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('adcc2c768e274f2d89f802ea109c3235', '33bc3bb1a9dc4533b9de70de59c1ec1c', null, null, '¦����', '13', '¦����', '431300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0f23a4dca3524daabdae8f9e25604b96', '33bc3bb1a9dc4533b9de70de59c1ec1c', null, null, '��������������������', '31', '��������������������', '433100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('74c34515a15e469e92d05c95aaca78ab', 'b51c615743e24581bf67975def934d06', null, null, '������', '01', '������', '440100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a7a5db86046e4359b593d5bb51806516', 'b51c615743e24581bf67975def934d06', null, null, '�ع���', '02', '�ع���', '440200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6a4a9d34b6904464805411c3d91a1313', 'b51c615743e24581bf67975def934d06', null, null, '������', '03', '������', '440300', null);
commit;
prompt 500 records committed...
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fc52a64b6b3748229bd8f9afab749793', 'b51c615743e24581bf67975def934d06', null, null, '�麣��', '04', '�麣��', '440400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5fe5cbee4e394883b75404f0b700c38f', 'b51c615743e24581bf67975def934d06', null, null, '��ͷ��', '05', '��ͷ��', '440500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a12ecc146129443e9fd9fa29cdf3f4ec', 'b51c615743e24581bf67975def934d06', null, null, '��ɽ��', '06', '��ɽ��', '440600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aa148f66edba4d79a319608c3110200c', 'b51c615743e24581bf67975def934d06', null, null, '������', '07', '������', '440700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3a654d6fa00b41afbd9e9eaa5d123224', 'b51c615743e24581bf67975def934d06', null, null, 'տ����', '08', 'տ����', '440800', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('af7ede8649de428e81c513d1ba57420d', 'b51c615743e24581bf67975def934d06', null, null, 'ï����', '09', 'ï����', '440900', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6fed53cde91a4336b0b2023c9c7da06b', 'b51c615743e24581bf67975def934d06', null, null, '������', '12', '������', '441200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('42ed28a2d2cf4a0ab128fdba1486ba90', 'b51c615743e24581bf67975def934d06', null, null, '������', '13', '������', '441300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ab33919c65c7421e95b62e88c00a809e', 'b51c615743e24581bf67975def934d06', null, null, '÷����', '14', '÷����', '441400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bcc33574fca64b2c9936de8b2e69fa53', 'b51c615743e24581bf67975def934d06', null, null, '��β��', '15', '��β��', '441500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9ab8b8c02064408fb354679975276d42', 'b51c615743e24581bf67975def934d06', null, null, '��Դ��', '16', '��Դ��', '441600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2bd37a3d909f4620b756803efe2fa5da', 'b51c615743e24581bf67975def934d06', null, null, '������', '17', '������', '441700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b14f71ca712e4aea9fdb427384026a75', 'b51c615743e24581bf67975def934d06', null, null, '��Զ��', '18', '��Զ��', '441800', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('131cd1bc6ab44780924b517a43ebccbb', 'b51c615743e24581bf67975def934d06', null, null, '������', '51', '������', '445100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('03a5d1e6c2c24023ba09c41ba51b7b94', 'b51c615743e24581bf67975def934d06', null, null, '������', '52', '������', '445200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('42db031cba074ff2a68b68b2eb36e043', 'b51c615743e24581bf67975def934d06', null, null, '�Ƹ���', '53', '�Ƹ���', '445300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9e986231263c41118f58c81e9d4cab3b', '47cf09ecda50413398cdf4afa27101e8', null, null, '������', '01', '������', '450100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3a8faee14d6a473493ddf5b00fe9eab7', '47cf09ecda50413398cdf4afa27101e8', null, null, '������', '02', '������', '450200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('87571143e8fd43b8b3aff17c93e3041b', '47cf09ecda50413398cdf4afa27101e8', null, null, '������', '03', '������', '450300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a9dc7c04401b43fc92297dfe7d331f81', '47cf09ecda50413398cdf4afa27101e8', null, null, '������', '04', '������', '450400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1f022fdf2fe84a96acc2b187e208d31b', '47cf09ecda50413398cdf4afa27101e8', null, null, '������', '05', '������', '450500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3ea02e362c824c1680a222ff06d7611b', '47cf09ecda50413398cdf4afa27101e8', null, null, '���Ǹ���', '06', '���Ǹ���', '450600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('330e2c8f1a1441dd8bee96e040e1ef7f', '47cf09ecda50413398cdf4afa27101e8', null, null, '������', '07', '������', '450700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c4e897a6a2174240baf29d7bfad235ba', '47cf09ecda50413398cdf4afa27101e8', null, null, '�����', '08', '�����', '450800', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e420d3d39d12490ab67e5091b54bcdab', '47cf09ecda50413398cdf4afa27101e8', null, null, '������', '09', '������', '450900', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5944b21cd62d4404bab999965db12d4a', '47cf09ecda50413398cdf4afa27101e8', null, null, '��ɫ��', '10', '��ɫ��', '451000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('94e911567a7647aebcaf864e4d2e0e59', '47cf09ecda50413398cdf4afa27101e8', null, null, '������', '11', '������', '451100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('241206b6912b4472bc71573bab0a2b1b', '47cf09ecda50413398cdf4afa27101e8', null, null, '�ӳ���', '12', '�ӳ���', '451200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a3b03952a36b474dbb441c665fab6913', '47cf09ecda50413398cdf4afa27101e8', null, null, '������', '13', '������', '451300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2e26f89fa8a54d59b4d85abfc42b1ebe', '47cf09ecda50413398cdf4afa27101e8', null, null, '������', '14', '������', '451400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('64a868e81b664b20b3f818bff115cf71', 'c87dbdbabac04801a83a8f7c8070235e', null, null, '������', '01', '������', '460100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e123506da0ff4e6fa019c8fceece5a0e', 'c87dbdbabac04801a83a8f7c8070235e', null, null, '������', '02', '������', '460200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('97727b55303e4938afa1eb316b3d58cb', 'c87dbdbabac04801a83a8f7c8070235e', null, null, 'ʡֱϽ�ؼ�������λ', '90', 'ʡֱϽ�ؼ�������λ', '469000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b980cca7c3e04419a36d045d0745ad97', '005b3f1def644d8aae1231bb6c7499d5', null, null, '������', '01', '������', '500100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('08cb7a128990421a99d0528514ebe24c', '79c341fcd1fe4b91a5d97cbb1a070068', null, null, '�ɶ���', '01', '�ɶ���', '510100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c119b8ebe0464e6e8535bca0598e13eb', '79c341fcd1fe4b91a5d97cbb1a070068', null, null, '�Թ���', '03', '�Թ���', '510300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e416c82c26c848c1a9532943280f7738', '79c341fcd1fe4b91a5d97cbb1a070068', null, null, '��֦����', '04', '��֦����', '510400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('988dcd4876d041d190834258b0f2a8d4', '79c341fcd1fe4b91a5d97cbb1a070068', null, null, '������', '05', '������', '510500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c87b68c64f854490888b630fbcbd0b15', '79c341fcd1fe4b91a5d97cbb1a070068', null, null, '������', '06', '������', '510600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('48fb275bc9004d74aeb5ff42510ade11', '79c341fcd1fe4b91a5d97cbb1a070068', null, null, '������', '07', '������', '510700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9d9c7883ebbe4190abfa32a3b2e001d6', '79c341fcd1fe4b91a5d97cbb1a070068', null, null, '��Ԫ��', '08', '��Ԫ��', '510800', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aded03dddeff43778b7b5b6f0c070a56', '79c341fcd1fe4b91a5d97cbb1a070068', null, null, '������', '09', '������', '510900', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('20cbbf557ea1420a960c6e9ce04de1e3', '79c341fcd1fe4b91a5d97cbb1a070068', null, null, '�ڽ���', '10', '�ڽ���', '511000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('69bd3ecc0d324699ba96fe24b0356639', '79c341fcd1fe4b91a5d97cbb1a070068', null, null, '��ɽ��', '11', '��ɽ��', '511100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7652787b72664f18bbfd327f363fb188', '79c341fcd1fe4b91a5d97cbb1a070068', null, null, '�ϳ���', '13', '�ϳ���', '511300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8800e3a2c9d24c758e8ef1eea71aa8a4', '79c341fcd1fe4b91a5d97cbb1a070068', null, null, 'üɽ��', '14', 'üɽ��', '511400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('199ea73f863b4550982e14d759a1ad27', '79c341fcd1fe4b91a5d97cbb1a070068', null, null, '�˱���', '15', '�˱���', '511500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bbf2f2bcf0ec4c41b319bffbcfb51941', '79c341fcd1fe4b91a5d97cbb1a070068', null, null, '�㰲��', '16', '�㰲��', '511600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c29af6ded1b64cbea4cd6a79fa153e07', '79c341fcd1fe4b91a5d97cbb1a070068', null, null, '������', '17', '������', '511700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('87081bc7c2b44b4db666df033cb09e88', '79c341fcd1fe4b91a5d97cbb1a070068', null, null, '�Ű���', '18', '�Ű���', '511800', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b6f3e780164d4fd8b64fbe128d7ca50d', '79c341fcd1fe4b91a5d97cbb1a070068', null, null, '������', '19', '������', '511900', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b467acdde0f64c6ba0739ba8b386215d', '79c341fcd1fe4b91a5d97cbb1a070068', null, null, '������', '20', '������', '512000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('405b1da4465e49088c798360db70c15b', '79c341fcd1fe4b91a5d97cbb1a070068', null, null, '���Ӳ���Ǽ��������', '32', '���Ӳ���Ǽ��������', '513200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e2b4b10999ff41bbafb98f60bb00c0ae', '79c341fcd1fe4b91a5d97cbb1a070068', null, null, '���β���������', '33', '���β���������', '513300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('70c6e0c0b2e0451f99b6b8986f13ca07', '862442793c45460da8884117d171c83d', null, null, '������', '01', '������', '640100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d211795dd1e6415294416b8d85e1ca3d', '862442793c45460da8884117d171c83d', null, null, 'ʯ��ɽ��', '02', 'ʯ��ɽ��', '640200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c12b062dc20e422ca8b66e9578738b74', '862442793c45460da8884117d171c83d', null, null, '������', '03', '������', '640300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('48a58b66b9394f1aa364c352ba1da9d3', '862442793c45460da8884117d171c83d', null, null, '��ԭ��', '04', '��ԭ��', '640400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('462784740efd4b7787f04c3ca0411c64', '862442793c45460da8884117d171c83d', null, null, '������', '05', '������', '640500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1bcb916fa3f64e599569392aaaf05b7a', '2f37fad27ab344108f4da87d9d21e767', null, null, '��³ľ����', '01', '��³ľ����', '650100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1a1c0daee49a410abe7540db0be7088a', '2f37fad27ab344108f4da87d9d21e767', null, null, '����������', '02', '����������', '650200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3d7c967cb651462cbf8ebf9e1d44676e', '2f37fad27ab344108f4da87d9d21e767', null, null, '��³������', '21', '��³������', '652100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('722028de91e449f9a753de307cdb0d97', '2f37fad27ab344108f4da87d9d21e767', null, null, '���ܵ���', '22', '���ܵ���', '652200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('abb7d66db87e4d2996304371f67e37a6', '2f37fad27ab344108f4da87d9d21e767', null, null, '��������������', '23', '��������������', '652300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dfcf7241f1744ba1a5102d7ac628aee8', '2f37fad27ab344108f4da87d9d21e767', null, null, '���������ɹ�������', '27', '���������ɹ�������', '652700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b9b5bc2ccede4027a1f74f80e6341da6', '2f37fad27ab344108f4da87d9d21e767', null, null, '���������ɹ�������', '28', '���������ɹ�������', '652800', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2ff89e0bff1b460db144ac09b148128d', '2f37fad27ab344108f4da87d9d21e767', null, null, '�����յ���', '29', '�����յ���', '652900', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('39d4b2c4532c408c8f5c415bca3646d0', '2f37fad27ab344108f4da87d9d21e767', null, null, '�������տ¶�����������', '30', '�������տ¶�����������', '653000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ab1026be2a6249d6970816590fc19145', '2f37fad27ab344108f4da87d9d21e767', null, null, '��ʲ����', '31', '��ʲ����', '653100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bb8405b610ee45ee9b7aac1f46c2fee3', '2f37fad27ab344108f4da87d9d21e767', null, null, '�������', '32', '�������', '653200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5af36941bec94e909e006b557150a1d4', '2f37fad27ab344108f4da87d9d21e767', null, null, '���������������', '40', '���������������', '654000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9e7badeaaf6e4b81947d64eee34d696e', '2f37fad27ab344108f4da87d9d21e767', null, null, '���ǵ���', '42', '���ǵ���', '654200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9e4de59c90544481a5c5163355474dee', '2f37fad27ab344108f4da87d9d21e767', null, null, '����̩����', '43', '����̩����', '654300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('25a0ad7d58b1411ca8fd5fe2a73bdb6f', '2f37fad27ab344108f4da87d9d21e767', null, null, 'ʡֱϽ������λ', '90', 'ʡֱϽ������λ', '659000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0d92c2dd3d8b4d568f5e4213b994f731', '3c96b28c5ada4a2f825530c2b6759f57', null, null, '���', '81', '���', '810000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b071cf5bb5b14629a19e2929bc219029', 'fcbe56f302194a42a7f51066cadc4577', null, null, '����', '82', '����', '820000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f46010d30af84774a1f8385753b28a93', '1e0a610de3b241058546ad6c891fcfb4', null, null, '̨��', '71', '̨��', '710000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9e7baa2d1a5b422189e813d735c19a6e', 'bb2c98b3923c431da6a1566bc808c10g', '100000', null, '������', '01', '������', '110101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5312ba82363b4471ab2ccb710b8868e3', 'bb2c98b3923c431da6a1566bc808c10g', '100000', null, '������', '02', '������', '110102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0326216041a14a9bb179d60266995789', 'bb2c98b3923c431da6a1566bc808c10g', '100000', null, '������', '03', '������', '110103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0321a2b255544dfeae29fbeabd299b4c', 'bb2c98b3923c431da6a1566bc808c10g', '100000', null, '������', '04', '������', '110104', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('61e7e61897984f3dbbfce087561216d7', 'bb2c98b3923c431da6a1566bc808c10g', '100000', null, '������', '05', '������', '110105', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a7bec1a275cc4882bcb31280a81d2a36', 'bb2c98b3923c431da6a1566bc808c10g', '100000', null, '��̨��', '06', '��̨��', '110106', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('816b41debc0b42a1a22a94d3cb9a5f4d', 'bb2c98b3923c431da6a1566bc808c10g', '100000', null, 'ʯ��ɽ��', '07', 'ʯ��ɽ��', '110107', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5f3c922147224269957308256500574c', 'bb2c98b3923c431da6a1566bc808c10g', '100000', null, '������', '08', '������', '110108', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6ab356797086471bacfc11d2ea2d6319', 'bb2c98b3923c431da6a1566bc808c10g', '102300', null, '��ͷ����', '09', '��ͷ����', '110109', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f23d0b9bb06b4847aee556272c9415f6', 'bb2c98b3923c431da6a1566bc808c10g', '102400', null, '��ɽ��', '11', '��ɽ��', '110111', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('183f6863e1064e2ba196a6d9826f955c', 'bb2c98b3923c431da6a1566bc808c10g', '101100', null, 'ͨ����', '12', 'ͨ����', '110112', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9e88083d921d4dd0b01a897fc6ded595', 'bb2c98b3923c431da6a1566bc808c10g', '101300', null, '˳����', '13', '˳����', '110113', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e9ebe2960c5e4948a35d8c6a64cb273c', 'bb2c98b3923c431da6a1566bc808c10g', '102200', null, '��ƽ��', '14', '��ƽ��', '110114', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2863a85c2ede4b7d8ee8474894f4e025', 'bb2c98b3923c431da6a1566bc808c10g', '102600', null, '������', '15', '������', '110115', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f170a19379c3427d843d06b7750d2994', 'bb2c98b3923c431da6a1566bc808c10g', '101400', null, '������', '16', '������', '110116', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2e6f72ccdd264bcb8edf7adcb4b01958', 'bb2c98b3923c431da6a1566bc808c10g', '101200', null, 'ƽ����', '17', 'ƽ����', '110117', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ddaa8a34894846aa9bd598f083f7b18b', 'bb2c98b3923c431da6a1566bc808c10g', '101500', null, '������', '28', '������', '110228', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('006e818c772c491b866b4b0d235c6981', 'bb2c98b3923c431da6a1566bc808c10g', '102100', null, '������', '29', '������', '110229', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fbfd1574ac7b4db5905b6ef722ca826f', 'bb2c98b3923c431da6a1566bc808c10f', '300000', null, '��ƽ��', '01', '��ƽ��', '120101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('447aadfea7f147aaa2c6499fdfa2dcc9', 'bb2c98b3923c431da6a1566bc808c10f', '300000', null, '�Ӷ���', '02', '�Ӷ���', '120102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d5489554ef3149888624e732f07d5082', 'bb2c98b3923c431da6a1566bc808c10f', '300000', null, '������', '03', '������', '120103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a3864f60e5b648c4acb76376e6c69f5e', 'bb2c98b3923c431da6a1566bc808c10f', '300000', null, '�Ͽ���', '04', '�Ͽ���', '120104', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('72f69932d24546ab9ad836c32da3dd09', 'bb2c98b3923c431da6a1566bc808c10f', '300000', null, '�ӱ���', '05', '�ӱ���', '120105', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6fc343f53db4465c9920a70d0ae610f6', 'bb2c98b3923c431da6a1566bc808c10f', '300000', null, '������', '06', '������', '120106', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('585ece77331543fbaca9139f13074ca0', 'bb2c98b3923c431da6a1566bc808c10f', '300450', null, '������', '07', '������', '120107', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7fe5efabd32346d19390ae1404f1536c', 'bb2c98b3923c431da6a1566bc808c10f', '300480', null, '������', '08', '������', '120108', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bd5eedcee2654305a8fd3f72aac1bd82', 'bb2c98b3923c431da6a1566bc808c10f', '300000', null, '�����', '09', '�����', '120109', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('42233a14f78b41f0b25a2c523f1d0458', 'bb2c98b3923c431da6a1566bc808c10f', '300000', null, '������', '10', '������', '120110', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f8f13e8e3ff54d5a9fb8709419a31593', 'bb2c98b3923c431da6a1566bc808c10f', '300000', null, '������', '11', '������', '120111', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9fb067ed377b4eef80da1c46594fdf1d', 'bb2c98b3923c431da6a1566bc808c10f', '300000', null, '������', '12', '������', '120112', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('29e847fcbe824faaae66d4aab671b4b5', 'bb2c98b3923c431da6a1566bc808c10f', '300000', null, '������', '13', '������', '120113', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('79e19f4266f74d4b973f07f6c1f41aef', 'bb2c98b3923c431da6a1566bc808c10f', '301700', null, '������', '14', '������', '120114', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4446bd43335c42aab1092401a1501640', 'bb2c98b3923c431da6a1566bc808c10f', '301800', null, '������', '15', '������', '120115', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('42de56306a6943318faf413132d9e0a4', 'bb2c98b3923c431da6a1566bc808c10f', '301500', null, '������', '21', '������', '120221', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7f963d9f93054dda9a81458a06c63643', 'bb2c98b3923c431da6a1566bc808c10f', '301600', null, '������', '23', '������', '120223', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3d8332dda9f14751b53aec5b492426fe', 'bb2c98b3923c431da6a1566bc808c10f', '301900', null, '����', '25', '����', '120225', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('03089d1b5b2441f592b9da1894ce332d', 'cbf57834e6314708abbbb577e400e698', '050000', null, '��Ͻ��', '01', '��Ͻ��', '130101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6c61249cca294362a6f568ac5ab99e9c', 'cbf57834e6314708abbbb577e400e698', '050000', null, '������', '02', '������', '130102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6f51616bbfbc4f148353be23fcd40eed', 'cbf57834e6314708abbbb577e400e698', '050000', null, '�Ŷ���', '03', '�Ŷ���', '130103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3bb45a837d114653814383bcacf480f5', 'cbf57834e6314708abbbb577e400e698', '050000', null, '������', '04', '������', '130104', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cd61b5e0b7324852b89697eb04f94032', 'cbf57834e6314708abbbb577e400e698', '050000', null, '�»���', '05', '�»���', '130105', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('76a931178f594f3eab2e1d3e51c95e1b', 'cbf57834e6314708abbbb577e400e698', '051000', null, '�������', '07', '�������', '130107', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6f19262da77c47388024b918fbc9071d', 'cbf57834e6314708abbbb577e400e698', '050000', null, 'ԣ����', '08', 'ԣ����', '130108', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8b7f649ed9844c88843c8c702ad82baa', 'cbf57834e6314708abbbb577e400e698', '050300', null, '������', '21', '������', '130121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ec1b21d82b25432594821d2cf415b088', 'cbf57834e6314708abbbb577e400e698', '050800', null, '������', '23', '������', '130123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('994a6826c2fc4459833bc60a14bfe6df', 'cbf57834e6314708abbbb577e400e698', '051430', null, '�����', '24', '�����', '130124', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('27c244a5af05481787fb9396882d9b12', 'cbf57834e6314708abbbb577e400e698', '050600', null, '������', '25', '������', '130125', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bccb2f3371c549638fb5e083d7badfbf', '2478110f8a104a189fb6f4a0eb4bde1c', null, null, '�̽���', '11', '�̽���', '211100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0e4703c44e4540d6b28fba9380400e92', '2478110f8a104a189fb6f4a0eb4bde1c', null, null, '������', '12', '������', '211200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('15afb64f4c90488f9b38836c2eb77d18', '2478110f8a104a189fb6f4a0eb4bde1c', null, null, '������', '13', '������', '211300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f09d74ecd8c34b269879aa4c409cafcf', '2478110f8a104a189fb6f4a0eb4bde1c', null, null, '��«����', '14', '��«����', '211400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3a3f1ba7751f47dabb57b457b678be60', '26697abbf2fb49239b261c010a69a343', null, null, '������', '01', '������', '220100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7774e0ff0e204d1e8f1c239d87344aa2', '26697abbf2fb49239b261c010a69a343', null, null, '������', '02', '������', '220200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('00d948ff21b342779471d96e783f9822', '26697abbf2fb49239b261c010a69a343', null, null, '��ƽ��', '03', '��ƽ��', '220300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8ce5a37dedc84f23832b14be8e0c601e', '26697abbf2fb49239b261c010a69a343', null, null, '��Դ��', '04', '��Դ��', '220400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c2cf2135b6be4b99b8fd891bbd43f686', '26697abbf2fb49239b261c010a69a343', null, null, 'ͨ����', '05', 'ͨ����', '220500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3551b526f5e7417fae31cf0d2edb4d9d', '26697abbf2fb49239b261c010a69a343', null, null, '��ɽ��', '06', '��ɽ��', '220600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a0ee1a7af5be4b53b88627407200545f', '26697abbf2fb49239b261c010a69a343', null, null, '��ԭ��', '07', '��ԭ��', '220700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bd2d77f833b2472d8a828a90f402f861', '26697abbf2fb49239b261c010a69a343', null, null, '�׳���', '08', '�׳���', '220800', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('02f726a87a1e43ddb4fac108dd68b46b', '26697abbf2fb49239b261c010a69a343', null, null, '�ӱ߳�����������', '24', '�ӱ߳�����������', '222400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('18f439ed744a48ae89ca7f469af554fd', '909b15dd3d9c4c4b9aeee646b5ffc8a8', null, null, '��������', '01', '��������', '230100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4f42860a0c8e4a6ab50d18f9e73d84a4', '909b15dd3d9c4c4b9aeee646b5ffc8a8', null, null, '���������', '02', '���������', '230200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c87e34444f484566923db62964bf3fc0', '909b15dd3d9c4c4b9aeee646b5ffc8a8', null, null, '������', '03', '������', '230300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('23fc3577d5b5453eae1ae4af187d0c86', '909b15dd3d9c4c4b9aeee646b5ffc8a8', null, null, '�׸���', '04', '�׸���', '230400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8fdba2c226b74f689e9095d060a98cd2', '909b15dd3d9c4c4b9aeee646b5ffc8a8', null, null, '˫Ѽɽ��', '05', '˫Ѽɽ��', '230500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b4f698e29e7e44a5b58a6d86e8783e36', '909b15dd3d9c4c4b9aeee646b5ffc8a8', null, null, '������', '06', '������', '230600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('31a1d04a3c394bf3bf77d67153cdc2c6', '909b15dd3d9c4c4b9aeee646b5ffc8a8', null, null, '������', '07', '������', '230700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0625ff8646c740ae83931ea039f1d8ba', '909b15dd3d9c4c4b9aeee646b5ffc8a8', null, null, '��ľ˹��', '08', '��ľ˹��', '230800', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7d964b3b2e26428b8f12284ddf2654c4', '909b15dd3d9c4c4b9aeee646b5ffc8a8', null, null, '��̨����', '09', '��̨����', '230900', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a850022c32f047a39f070ecdb64c10cf', '909b15dd3d9c4c4b9aeee646b5ffc8a8', null, null, 'ĵ������', '10', 'ĵ������', '231000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e94124d72ea049559b68d29c33d5e9f6', '909b15dd3d9c4c4b9aeee646b5ffc8a8', null, null, '�ں���', '11', '�ں���', '231100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0700bce39eff4bbaad9989a4e5283ea5', '909b15dd3d9c4c4b9aeee646b5ffc8a8', null, null, '�绯��', '12', '�绯��', '231200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ba25a32ee5ea4cbaae42084151c8cd4c', '909b15dd3d9c4c4b9aeee646b5ffc8a8', null, null, '���˰������', '27', '���˰������', '232700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9cf48c1bd1494b26b7dfeb12ea3238d8', '3b6cce816ea04ff7b3dc68abeca243dd', null, null, '�Ϻ���', '01', '�Ϻ���', '310100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e15988d61b834780bec69b7dac6c283a', '02c8619f11814155982eb5b1873353bd', null, null, '�Ͼ���', '01', '�Ͼ���', '320100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b02f8deca60a49b98e7ed5aa0428e5e6', '02c8619f11814155982eb5b1873353bd', null, null, '������', '02', '������', '320200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('02fd4185ea2343ffaae15ca2c5caf2b3', '02c8619f11814155982eb5b1873353bd', null, null, '������', '03', '������', '320300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0c09b62ba8c74384bc7edf68979f3caf', '02c8619f11814155982eb5b1873353bd', null, null, '������', '04', '������', '320400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('09e191fc259b41d49524f1ffa57f641f', '02c8619f11814155982eb5b1873353bd', null, null, '������', '05', '������', '320500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b98b1f8bf0b94b8785ca162c17011d3f', '02c8619f11814155982eb5b1873353bd', null, null, '��ͨ��', '06', '��ͨ��', '320600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c85fe163313c4fd78e007d8ab1d9a9c7', '02c8619f11814155982eb5b1873353bd', null, null, '���Ƹ���', '07', '���Ƹ���', '320700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('83fcca5dad594e5da9b2c4e7fe0c6ba8', '02c8619f11814155982eb5b1873353bd', null, null, '������', '08', '������', '320800', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('789b2371daed489babc5ce0b82cf9bec', '02c8619f11814155982eb5b1873353bd', null, null, '�γ���', '09', '�γ���', '320900', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a9330bf8add64d57927ab4faae098a13', '02c8619f11814155982eb5b1873353bd', null, null, '������', '10', '������', '321000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1f444db499d740989434bb11e14f54a3', '02c8619f11814155982eb5b1873353bd', null, null, '����', '11', '����', '321100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('835c9ac0e6824b37a4af2b626524362f', '02c8619f11814155982eb5b1873353bd', null, null, '̩����', '12', '̩����', '321200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d6fe0afb4aec42bc8cb70ad349bfb31a', '02c8619f11814155982eb5b1873353bd', null, null, '��Ǩ��', '13', '��Ǩ��', '321300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('948adc095d0448cbbc8a28a43b126cd7', 'ea63e61e420c4134be3db192220422f9', null, null, '������', '01', '������', '330100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b579455d7ae4445fb895e24f98caa299', 'ea63e61e420c4134be3db192220422f9', null, null, '������', '02', '������', '330200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('725d382f33c54a83a7db831c3da82665', 'ea63e61e420c4134be3db192220422f9', null, null, '������', '03', '������', '330300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1884978b76d6469c871140b620617381', 'ea63e61e420c4134be3db192220422f9', null, null, '������', '04', '������', '330400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('77e868a833554971a102184336921723', 'ea63e61e420c4134be3db192220422f9', null, null, '������', '05', '������', '330500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('962cb7e4670547ffb73d8ee3b44532dd', 'ea63e61e420c4134be3db192220422f9', null, null, '������', '06', '������', '330600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8df5d2d1bf024ffa92c29fe60871da10', 'ea63e61e420c4134be3db192220422f9', null, null, '����', '07', '����', '330700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('39ef5e82145c4f11a07dc5a6503d596b', 'ea63e61e420c4134be3db192220422f9', null, null, '������', '08', '������', '330800', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c56cf7ba5aec447eaa0588684c1347f8', 'ea63e61e420c4134be3db192220422f9', null, null, '��ɽ��', '09', '��ɽ��', '330900', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1901b2c30bc24b45a4abca1c5b03b4ba', 'ea63e61e420c4134be3db192220422f9', null, null, '̨����', '10', '̨����', '331000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e2d22a554b0141759f6ffc200a5a7c5a', 'ea63e61e420c4134be3db192220422f9', null, null, '��ˮ��', '11', '��ˮ��', '331100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('db9ba52f83f943c3bb606a0d52ad769e', '82f82898ec5d42a797e25a8039a7c1da', null, null, '�Ϸ���', '01', '�Ϸ���', '340100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e3a4a4ba5c56445c92c6a4d932ae463e', '82f82898ec5d42a797e25a8039a7c1da', null, null, '�ߺ���', '02', '�ߺ���', '340200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a2c7a37f793c4880a33dd7ca60198d51', '82f82898ec5d42a797e25a8039a7c1da', null, null, '������', '03', '������', '340300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('eebd1b2f46a7490ead76d682d03caa3d', '82f82898ec5d42a797e25a8039a7c1da', null, null, '������', '04', '������', '340400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0d19c7615d3e4c3e882056f94b5aecae', '82f82898ec5d42a797e25a8039a7c1da', null, null, '����ɽ��', '05', '����ɽ��', '340500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('99b8d897937b424c98d2d8f6a66e15bc', '82f82898ec5d42a797e25a8039a7c1da', null, null, '������', '06', '������', '340600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('db87ace1e601499894596f7ad2f1ec8a', '82f82898ec5d42a797e25a8039a7c1da', null, null, 'ͭ����', '07', 'ͭ����', '340700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b7a3e50258a6477c986d9617cb2da1ca', '82f82898ec5d42a797e25a8039a7c1da', null, null, '������', '08', '������', '340800', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aeb93b0d0bad495ebf128dfccc009d5a', '82f82898ec5d42a797e25a8039a7c1da', null, null, '��ɽ��', '10', '��ɽ��', '341000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('56f6e70756b24b2b9b6819c3263e4c19', '82f82898ec5d42a797e25a8039a7c1da', null, null, '������', '11', '������', '341100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5afe0ab733f946d2be079f263536d1e4', '82f82898ec5d42a797e25a8039a7c1da', null, null, '������', '12', '������', '341200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cc3bd80ea79c413ea58d4d69cb283e70', '82f82898ec5d42a797e25a8039a7c1da', null, null, '������', '13', '������', '341300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3777ebad7996423cb39c8c8a5eef1038', '82f82898ec5d42a797e25a8039a7c1da', null, null, '������', '14', '������', '341400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aabcff251e8d49538a041fd8a1992561', '82f82898ec5d42a797e25a8039a7c1da', null, null, '������', '15', '������', '341500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e78a8a06ed6b4b469f72d85bcf4331af', '82f82898ec5d42a797e25a8039a7c1da', null, null, '������', '16', '������', '341600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('77f0da77ecc84e2c9cdd0bf884cb7437', '82f82898ec5d42a797e25a8039a7c1da', null, null, '������', '17', '������', '341700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('915283302a1448d8a6a5f4c3fe3811b7', '82f82898ec5d42a797e25a8039a7c1da', null, null, '������', '18', '������', '341800', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('61188ed689124e8693ad2da355082375', '31f843473a4d4710a5d8cb462ee84cd1', null, null, '������', '01', '������', '350100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c600d641b3d3489399d3557c9bc5e188', '31f843473a4d4710a5d8cb462ee84cd1', null, null, '������', '02', '������', '350200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f90bd8facf994bdbb6571b195d0de4cd', '7cb33ba7dcc9456caac8ec92e8640636', '068350', null, '��������������', '26', '��������������', '130826', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9a9e0dab70c840508a7c3b6a0a2a4191', '7cb33ba7dcc9456caac8ec92e8640636', '067600', null, '��������������', '27', '��������������', '130827', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('720ecd35507141fc921fa1ce7dbe24ce', '7cb33ba7dcc9456caac8ec92e8640636', '068450', null, 'Χ�������ɹ���������', '28', 'Χ�������ɹ���������', '130828', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fd22b6601c2046cab327224dc1260736', 'c012a6672c5a4d51a2dd7c869e6e2f94', '061000', null, '��Ͻ��', '01', '��Ͻ��', '130901', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('78e1e223b6544377910df22b27e6a719', 'c012a6672c5a4d51a2dd7c869e6e2f94', '061000', null, '�»���', '02', '�»���', '130902', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d630dbe29fdc4ffca95cfe36a90915bc', 'c012a6672c5a4d51a2dd7c869e6e2f94', '061000', null, '�˺���', '03', '�˺���', '130903', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d91f8e6e517945ef9e2171e2356410af', 'c012a6672c5a4d51a2dd7c869e6e2f94', '061000', null, '����', '21', '����', '130921', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0c9c2d1fcb5c4a68b972506e5886f18d', 'c012a6672c5a4d51a2dd7c869e6e2f94', '062650', null, '����', '22', '����', '130922', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('12205ce938074a8aa1a83d007af0d771', 'c012a6672c5a4d51a2dd7c869e6e2f94', '061600', null, '������', '23', '������', '130923', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b53562981fca4e43b0359787756f891a', 'c012a6672c5a4d51a2dd7c869e6e2f94', '061200', null, '������', '24', '������', '130924', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5c910bd552544d84a62b18059e65f127', 'c012a6672c5a4d51a2dd7c869e6e2f94', '061300', null, '��ɽ��', '25', '��ɽ��', '130925', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e1333d235f494e98b5adad8993d9068a', 'c012a6672c5a4d51a2dd7c869e6e2f94', '062350', null, '������', '26', '������', '130926', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('075bd1db89874ad48377cbd31fcfb607', 'c012a6672c5a4d51a2dd7c869e6e2f94', '061500', null, '��Ƥ��', '27', '��Ƥ��', '130927', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d3e2f3d16af44acbb428e507a907539b', 'c012a6672c5a4d51a2dd7c869e6e2f94', '061800', null, '������', '28', '������', '130928', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('70e3354f92f24d13b12ef96238882cd7', 'c012a6672c5a4d51a2dd7c869e6e2f94', '062250', null, '����', '29', '����', '130929', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9d608756899b474889d6465ef86696d7', 'c012a6672c5a4d51a2dd7c869e6e2f94', '061400', null, '�ϴ����������', '30', '�ϴ����������', '130930', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4c9415d5f1a54fdfb021253c409d17ec', 'c012a6672c5a4d51a2dd7c869e6e2f94', '062150', null, '��ͷ��', '81', '��ͷ��', '130981', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('edec66ffbb7f4aea8c21e8e7a4d7eab3', 'c012a6672c5a4d51a2dd7c869e6e2f94', '062550', null, '������', '82', '������', '130982', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('eaa8ca4fa0d54cb68581dbefcee53384', 'c012a6672c5a4d51a2dd7c869e6e2f94', '061100', null, '������', '83', '������', '130983', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('23b53f58af3f4c63960ff086c47e1042', 'c012a6672c5a4d51a2dd7c869e6e2f94', '062450', null, '�Ӽ���', '84', '�Ӽ���', '130984', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6a07a628b50b462e9f5404ce10d4a31a', 'e9fcaf25bb7e4954ba725288edd530b4', '065000', null, '��Ͻ��', '01', '��Ͻ��', '131001', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('963fded5b145401091e7936c646850e9', 'e9fcaf25bb7e4954ba725288edd530b4', '065000', null, '������', '02', '������', '131002', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d6f713640b8543419e2777757297f1ff', 'e9fcaf25bb7e4954ba725288edd530b4', '065000', null, '������', '03', '������', '131003', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4a2623c4a7bd4fd4a51d1a82381822c0', 'e9fcaf25bb7e4954ba725288edd530b4', '065500', null, '�̰���', '22', '�̰���', '131022', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b60e9ebf9baa49d69543f71a2cca11c2', 'e9fcaf25bb7e4954ba725288edd530b4', '065600', null, '������', '23', '������', '131023', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3f8df74edb1f4a23861222cc40591b2c', 'e9fcaf25bb7e4954ba725288edd530b4', '065400', null, '�����', '24', '�����', '131024', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('84aaac05e529456d8a33685e79a2a7a4', 'e9fcaf25bb7e4954ba725288edd530b4', '065900', null, '�����', '25', '�����', '131025', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('16772954b63441eeb94814a36e2bdd38', 'e9fcaf25bb7e4954ba725288edd530b4', '065800', null, '�İ���', '26', '�İ���', '131026', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a9cd68845b214961b3c4432d3fc356d8', 'e9fcaf25bb7e4954ba725288edd530b4', '065300', null, '�󳧻���������', '28', '�󳧻���������', '131028', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0a3839740ecb408d8f4aa38a917096c0', 'e9fcaf25bb7e4954ba725288edd530b4', '065700', null, '������', '81', '������', '131081', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f0831172cfcc420f80769b814bd4c897', 'e9fcaf25bb7e4954ba725288edd530b4', '065200', null, '������', '82', '������', '131082', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f4f739c168a845a3867400b52141d0f4', '23a701cb7a9441e7b601f2b4522d90ff', '053000', null, '��Ͻ��', '01', '��Ͻ��', '131101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b5b094ae6105461e847f4c1ac3e29952', '23a701cb7a9441e7b601f2b4522d90ff', '053000', null, '�ҳ���', '02', '�ҳ���', '131102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7396d0dec8dd42cda34373f27dfdb8d2', '23a701cb7a9441e7b601f2b4522d90ff', '053100', null, '��ǿ��', '21', '��ǿ��', '131121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cc76d2e171534331a652904319b6322a', '23a701cb7a9441e7b601f2b4522d90ff', '053400', null, '������', '22', '������', '131122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bcdb3c7bf3e34eedbf76b8b0920dff4e', '23a701cb7a9441e7b601f2b4522d90ff', '053300', null, '��ǿ��', '23', '��ǿ��', '131123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4e004ec38e044768819d34836556d349', '23a701cb7a9441e7b601f2b4522d90ff', '053900', null, '������', '24', '������', '131124', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('814c43c1122241f0b0d69c4ee117de27', '23a701cb7a9441e7b601f2b4522d90ff', '053600', null, '��ƽ��', '25', '��ƽ��', '131125', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9a27d6cbdcb94c89afbebf7f6c81c090', '23a701cb7a9441e7b601f2b4522d90ff', '053800', null, '�ʳ���', '26', '�ʳ���', '131126', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('028554b83f554f1cafe6a77c43cac260', '23a701cb7a9441e7b601f2b4522d90ff', '053500', null, '����', '27', '����', '131127', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ad97b63b38e2419ca7286770755e40e6', '23a701cb7a9441e7b601f2b4522d90ff', '053700', null, '������', '28', '������', '131128', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b92d5fa34a554cecae0de33ac0535586', '23a701cb7a9441e7b601f2b4522d90ff', '053200', null, '������', '81', '������', '131181', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('43c88e27032c4d3bba9535795e0fcd9a', '23a701cb7a9441e7b601f2b4522d90ff', '052800', null, '������', '82', '������', '131182', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('36b8183e8d1b4210aa6a50788325d6dd', 'b7f87c79f8344d97accf425e966da23a', '030000', null, '��Ͻ��', '01', '��Ͻ��', '140101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('78ddeb74c3b844aca8803f4baf973b06', 'b7f87c79f8344d97accf425e966da23a', '030000', null, 'С����', '05', 'С����', '140105', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c9639da553b74ddc90ab38a4fb3c63ed', 'b7f87c79f8344d97accf425e966da23a', '030000', null, 'ӭ����', '06', 'ӭ����', '140106', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('df0ff2bba89a4b699a9edcc180ce5dec', 'b7f87c79f8344d97accf425e966da23a', '030000', null, '�ӻ�����', '07', '�ӻ�����', '140107', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5c497c2f988647928aba431f74e05544', 'b7f87c79f8344d97accf425e966da23a', '030000', null, '���ƺ��', '08', '���ƺ��', '140108', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('697a4e164f4043df8f52cbffb8bf98fd', 'b7f87c79f8344d97accf425e966da23a', '030000', null, '�������', '09', '�������', '140109', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7b624a8289ad4cfa85e372dfdd7dc52f', 'b7f87c79f8344d97accf425e966da23a', '030000', null, '��Դ��', '10', '��Դ��', '140110', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7921a166551d4aa4888c06574bf87a72', 'b7f87c79f8344d97accf425e966da23a', '030400', null, '������', '21', '������', '140121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b4f37722c021459dadc250b62dfe990a', 'b7f87c79f8344d97accf425e966da23a', '030100', null, '������', '22', '������', '140122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c6b185a1f2f34c60a42c98f7214e757b', 'b7f87c79f8344d97accf425e966da23a', '030300', null, '¦����', '23', '¦����', '140123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e7ff00b21abe47f4b7d3a714cd2b0ab8', 'b7f87c79f8344d97accf425e966da23a', '030200', null, '�Ž���', '81', '�Ž���', '140181', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ac3a060698644032a5aadd4b32a26a74', '6bdf07875675456cad494b60cf611d83', '037000', null, '��Ͻ��', '01', '��Ͻ��', '140201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ddf1cd710de44a8eaca282613a06659b', '6bdf07875675456cad494b60cf611d83', '037000', null, '����', '02', '����', '140202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cabb9ca3150248bd9fa9505400b67765', '6bdf07875675456cad494b60cf611d83', '037000', null, '����', '03', '����', '140203', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bb244668c35949ea8a3b440cc0369e4e', '6bdf07875675456cad494b60cf611d83', '037000', null, '�Ͻ���', '11', '�Ͻ���', '140211', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('98790b5f795b49508dfff2b66f18d309', '6bdf07875675456cad494b60cf611d83', '037000', null, '������', '12', '������', '140212', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b9059e286c89475e9c18931bf61f8d8c', '6bdf07875675456cad494b60cf611d83', '038100', null, '������', '21', '������', '140221', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1e6d5a92661a435e9c7899cc470e84db', '6bdf07875675456cad494b60cf611d83', '038200', null, '������', '22', '������', '140222', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8440e350e5bf4aaa904734952e55228f', '6bdf07875675456cad494b60cf611d83', '037500', null, '������', '23', '������', '140223', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9c9ac548c3cb4e16ae3fb04ef86077ec', '6bdf07875675456cad494b60cf611d83', '034400', null, '������', '24', '������', '140224', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('187a8f27a6564cc8969d2f0ca9c023a0', '6bdf07875675456cad494b60cf611d83', '037400', null, '��Դ��', '25', '��Դ��', '140225', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6b072780236e4cf69842c5acbff5cb22', '6bdf07875675456cad494b60cf611d83', '037100', null, '������', '26', '������', '140226', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4be3d0940a304cadb570aecb46b79a93', '264018e21b854c78866dd48622f2d326', '036000', null, '������', '25', '������', '140925', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f8d1e6c013844db3bcea93befea75c9b', '2ba645b8f3634fa8b493153e4a5ed8f6', '137400', null, '����������', '01', '����������', '152201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('236791bfabf94d3eb4b5bcf706d3f107', '2ba645b8f3634fa8b493153e4a5ed8f6', '137400', null, '����ɽ��', '02', '����ɽ��', '152202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('42a8101f4e0f495d819be34eaf1d109b', '2ba645b8f3634fa8b493153e4a5ed8f6', '137400', null, '�ƶ�������ǰ��', '21', '�ƶ�������ǰ��', '152221', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8fd8a66c7c5342d5a62dff8c70be0952', '2ba645b8f3634fa8b493153e4a5ed8f6', '029400', null, '�ƶ�����������', '22', '�ƶ�����������', '152222', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7f2cbbf9cbb84b4e9eee634aeabd395e', '2ba645b8f3634fa8b493153e4a5ed8f6', '137600', null, '��������', '23', '��������', '152223', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7e87d88abd2b43a6ab24dd043adf512f', '2ba645b8f3634fa8b493153e4a5ed8f6', '137500', null, 'ͻȪ��', '24', 'ͻȪ��', '152224', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0eb7d15aff1b45be8e836f2228061ea4', '8e3b599f883a4795a8ebede218cfa3c5', '012600', null, '����������', '01', '����������', '152501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f936d86cceb248b99ed44daae3ccf070', '8e3b599f883a4795a8ebede218cfa3c5', '026000', null, '���ֺ�����', '02', '���ֺ�����', '152502', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fe4e8e5533a64267b8604b41b6e66f71', '8e3b599f883a4795a8ebede218cfa3c5', '011400', null, '���͸���', '22', '���͸���', '152522', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('66db023f517e43c18d1597b7e569c686', '8e3b599f883a4795a8ebede218cfa3c5', '011300', null, '����������', '23', '����������', '152523', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b93e78005cdd4ac5be137e7db21c20b6', '8e3b599f883a4795a8ebede218cfa3c5', '011200', null, '����������', '24', '����������', '152524', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f3e3c5dcf3ac41b8b9ef43d1fb78d473', '8e3b599f883a4795a8ebede218cfa3c5', '026300', null, '������������', '25', '������������', '152525', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2be5d87ef7514d4a8a57c814146820bd', '8e3b599f883a4795a8ebede218cfa3c5', '026200', null, '������������', '26', '������������', '152526', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1076f83beea04bf6a7aebb20ba66f1db', '8e3b599f883a4795a8ebede218cfa3c5', '027000', null, '̫������', '27', '̫������', '152527', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('14a099f2af7a4fd3911dd8a1a0d7c42a', '8e3b599f883a4795a8ebede218cfa3c5', '013250', null, '�����', '28', '�����', '152528', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0acd793a028b4bfb8dc0dc83d37f2aba', '8e3b599f883a4795a8ebede218cfa3c5', '013800', null, '�������', '29', '�������', '152529', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8bc7a9ad977a40daba97d965bd0ce2cd', '8e3b599f883a4795a8ebede218cfa3c5', '027200', null, '������', '30', '������', '152530', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c8c05bdf8a624875b903fa7a6703e57e', '8e3b599f883a4795a8ebede218cfa3c5', '027300', null, '������', '31', '������', '152531', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d4faeedba8ef417789a72a492c0a3c55', '750ccb3644114acbaf7cb9f3979fa14e', '750300', null, '����������', '21', '����������', '152921', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a14a6e1ed2714fe0b8d81b8bb288b6c6', '750ccb3644114acbaf7cb9f3979fa14e', '737300', null, '����������', '22', '����������', '152922', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('998f6551f0244747aaff9750467de4cd', '750ccb3644114acbaf7cb9f3979fa14e', '735400', null, '�������', '23', '�������', '152923', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4471a8975e814ae89bb91aa7d5d10502', 'd4dc7fd748b54bfd85730b31208b8983', '110000', null, '��Ͻ��', '01', '��Ͻ��', '210101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d72e1439b93b4213a3aed3e76c5083c0', 'd4dc7fd748b54bfd85730b31208b8983', '110000', null, '��ƽ��', '02', '��ƽ��', '210102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('001dafd32e8a4ecd82e652865de7dbea', 'd4dc7fd748b54bfd85730b31208b8983', '110000', null, '�����', '03', '�����', '210103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3a9493ca7cf94adb952ef381b62d4af1', 'd4dc7fd748b54bfd85730b31208b8983', '110000', null, '����', '04', '����', '210104', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('726e4f338fdd4fbdafbe899a5f30e611', 'd4dc7fd748b54bfd85730b31208b8983', '110000', null, '�ʹ���', '05', '�ʹ���', '210105', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c55f8220c482415e9b3aa13552141351', 'd4dc7fd748b54bfd85730b31208b8983', '110020', null, '������', '06', '������', '210106', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('647b6681eb3f4c66be105f029392966a', 'd4dc7fd748b54bfd85730b31208b8983', '110100', null, '�ռ�����', '11', '�ռ�����', '210111', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('71a37896265e41af92d84c90e85889d2', 'd4dc7fd748b54bfd85730b31208b8983', '110000', null, '������', '12', '������', '210112', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7f6243a133a44736b022fbba10eadb81', 'd4dc7fd748b54bfd85730b31208b8983', '110000', null, '������', '13', '������', '210113', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4f9ce31880a64d09838d802a4b0c7bd4', 'd4dc7fd748b54bfd85730b31208b8983', '110000', null, '�ں���', '14', '�ں���', '210114', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f79e9b0b098746eabf907ffd731f24e8', 'd4dc7fd748b54bfd85730b31208b8983', '110200', null, '������', '22', '������', '210122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('47751d6f5fe447db8f5c81caa61e5a95', 'd4dc7fd748b54bfd85730b31208b8983', '110500', null, '��ƽ��', '23', '��ƽ��', '210123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f0214cbda19442e4972d2353fb927ddb', 'd4dc7fd748b54bfd85730b31208b8983', '110400', null, '������', '24', '������', '210124', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cd0a1d8a0b2847eeadf6abf773f12aa1', 'd4dc7fd748b54bfd85730b31208b8983', '110300', null, '������', '81', '������', '210181', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7fcb966057e5495ebe93fac22d0a781d', '5246279f38814f9a80d551d0c4ec88cb', '116000', null, '��Ͻ��', '01', '��Ͻ��', '210201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cb1d1e713fb04275a8696cfc612600fc', '5246279f38814f9a80d551d0c4ec88cb', '116000', null, '��ɽ��', '02', '��ɽ��', '210202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('366f3eb6fb5b47b3aaec4cae8e53437f', '5246279f38814f9a80d551d0c4ec88cb', '116000', null, '������', '03', '������', '210203', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4af02700330c4a0686ec4898b95a349f', '5246279f38814f9a80d551d0c4ec88cb', '116000', null, 'ɳ�ӿ���', '04', 'ɳ�ӿ���', '210204', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c4354df141eb4bdd9f0676c90710db82', '5246279f38814f9a80d551d0c4ec88cb', '116000', null, '�ʾ�����', '11', '�ʾ�����', '210211', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('583d7f4c422f4b508365b37f45c0380f', '5246279f38814f9a80d551d0c4ec88cb', '116000', null, '��˳����', '12', '��˳����', '210212', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a5a7fddef50d403ebcabdb383ce8912c', '5246279f38814f9a80d551d0c4ec88cb', '116000', null, '������', '13', '������', '210213', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('96bee19aa46a4e37b3e20b57890967f3', '5246279f38814f9a80d551d0c4ec88cb', '116500', null, '������', '24', '������', '210224', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fe75dca2b0594b85b99adf3ae2ad2319', '5246279f38814f9a80d551d0c4ec88cb', '116300', null, '�߷�����', '81', '�߷�����', '210281', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7de3c2f25fc34951b9b07d605da9d4af', '5246279f38814f9a80d551d0c4ec88cb', '116200', null, '��������', '82', '��������', '210282', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dfe8ecf75ef645b28f46b80cf7c06d65', '5246279f38814f9a80d551d0c4ec88cb', '116400', null, 'ׯ����', '83', 'ׯ����', '210283', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d366036d09ce411abef1ff570cc66c1c', '77697bfbce0b42e3a0ab3a35f66cc591', '114000', null, '��Ͻ��', '01', '��Ͻ��', '210301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a1f4764972d449a083c39aab2802468e', '77697bfbce0b42e3a0ab3a35f66cc591', '114000', null, '������', '02', '������', '210302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fbccacee11d44a18a684017bab029414', '77697bfbce0b42e3a0ab3a35f66cc591', '114000', null, '������', '03', '������', '210303', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a1625cb3ec0e4a94af4c0cd8120c527c', '77697bfbce0b42e3a0ab3a35f66cc591', '114000', null, '��ɽ��', '04', '��ɽ��', '210304', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('69dde36cc41b44b4aeddaff243490d4c', '77697bfbce0b42e3a0ab3a35f66cc591', '114000', null, 'ǧɽ��', '11', 'ǧɽ��', '210311', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('20c38e7233b74dd1b48c18f670331ddb', '77697bfbce0b42e3a0ab3a35f66cc591', '114100', null, '̨����', '21', '̨����', '210321', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aba3b12505104acfa4cdb6af4d648690', '77697bfbce0b42e3a0ab3a35f66cc591', '118400', null, '�������������', '23', '�������������', '210323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('68873cf573d44696a2a5008656671469', '77697bfbce0b42e3a0ab3a35f66cc591', '114200', null, '������', '81', '������', '210381', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0bbb4cc512a5466996ee19d9b205d943', '2dae9d3b1ff147988878f48b30ca29e6', '113000', null, '��Ͻ��', '01', '��Ͻ��', '210401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5a15221dbd294e53946ec5ef06a13c5d', '2dae9d3b1ff147988878f48b30ca29e6', '113000', null, '�¸���', '02', '�¸���', '210402', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('401d3a67b24b420890c0edc2d42b2dee', '2dae9d3b1ff147988878f48b30ca29e6', '113000', null, '������', '03', '������', '210403', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6b74447cb08847e9ad91a52202ef3b8a', '2dae9d3b1ff147988878f48b30ca29e6', '113000', null, '������', '04', '������', '210404', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b2fd5b367dfd48be905b77becf86c499', '2dae9d3b1ff147988878f48b30ca29e6', '113000', null, '˳����', '11', '˳����', '210411', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('80e689f0393943c390657015b12ca1c4', '2dae9d3b1ff147988878f48b30ca29e6', '113100', null, '��˳��', '21', '��˳��', '210421', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c3714eba38154ed1ba73d60f9499c5ad', '2dae9d3b1ff147988878f48b30ca29e6', '113200', null, '�±�����������', '22', '�±�����������', '210422', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e347f21647f545d39da6197b364a5e14', '2dae9d3b1ff147988878f48b30ca29e6', '113300', null, '��ԭ����������', '23', '��ԭ����������', '210423', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6e4a7bf4261542d895cf70026784c936', '133d81bc3b4c48e7803bde263c887d8f', '117000', null, '��Ͻ��', '01', '��Ͻ��', '210501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a931713a606b4c92b9279967858d62e1', 'd3b62fa78aa843649485428da6b36015', null, null, '������', '03', '������', '520300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('413d723baf1e4e9a89d84bd05d36ff6c', 'd3b62fa78aa843649485428da6b36015', null, null, '��˳��', '04', '��˳��', '520400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6af6ff6ad30d464c8ab0883b16557eb7', 'd3b62fa78aa843649485428da6b36015', null, null, 'ͭ�ʵ���', '22', 'ͭ�ʵ���', '522200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e6c957a7e6764d25ae824740397d1146', 'd3b62fa78aa843649485428da6b36015', null, null, 'ǭ���ϲ���������������', '23', 'ǭ���ϲ���������������', '522300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3bc2acb5a13f41f68fd1926c57a97d79', 'd3b62fa78aa843649485428da6b36015', null, null, '�Ͻڵ���', '24', '�Ͻڵ���', '522400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('eb15b91b9ab84251bc78527a6bc9f98a', 'd3b62fa78aa843649485428da6b36015', null, null, 'ǭ�������嶱��������', '26', 'ǭ�������嶱��������', '522600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('61b919f7685248a3bb577513bd98786c', 'd3b62fa78aa843649485428da6b36015', null, null, 'ǭ�ϲ���������������', '27', 'ǭ�ϲ���������������', '522700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5ab6b67e085546caa372b13ec883e55d', '876e69ae2bfa4251883a30c66df81480', null, null, '������', '01', '������', '530100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('32f1279ae35c4dd4bc2aff7a08755344', '876e69ae2bfa4251883a30c66df81480', null, null, '������', '03', '������', '530300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b32eb2d28ea84cacb3190db9a12588db', '876e69ae2bfa4251883a30c66df81480', null, null, '��Ϫ��', '04', '��Ϫ��', '530400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7f5abf200b664ad595bc1a8c7098b9af', '876e69ae2bfa4251883a30c66df81480', null, null, '��ɽ��', '05', '��ɽ��', '530500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('71565bd32ec9492b81bb4ab47436ddf0', '876e69ae2bfa4251883a30c66df81480', null, null, '��ͨ��', '06', '��ͨ��', '530600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6b376cbe3f17409599ef29acccd2d5f0', '876e69ae2bfa4251883a30c66df81480', null, null, '������', '07', '������', '530700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6343ca91165140d1bcc25205a1235335', '876e69ae2bfa4251883a30c66df81480', null, null, '˼é��', '08', '˼é��', '530800', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d3ac67eb93544b9da681781f1f27fa5d', '876e69ae2bfa4251883a30c66df81480', null, null, '�ٲ���', '09', '�ٲ���', '530900', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f9f4657fe4aa48bb8d324167750c9b1c', '876e69ae2bfa4251883a30c66df81480', null, null, '��������������', '23', '��������������', '532300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4ac9c25b5e964e4cae428ca3b28fd2c5', '876e69ae2bfa4251883a30c66df81480', null, null, '��ӹ���������������', '25', '��ӹ���������������', '532500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0a7b3b59379a4bb2a99de96b44f607df', '876e69ae2bfa4251883a30c66df81480', null, null, '��ɽ׳������������', '26', '��ɽ׳������������', '532600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fe36a09e2995441791c4e76473137fd8', '876e69ae2bfa4251883a30c66df81480', null, null, '��˫���ɴ���������', '28', '��˫���ɴ���������', '532800', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7f890338eab6453e9b4015b33fd8abfc', '876e69ae2bfa4251883a30c66df81480', null, null, '��������������', '29', '��������������', '532900', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('45023fee4aae4edfb6a91ea17a0b368f', '876e69ae2bfa4251883a30c66df81480', null, null, '�º���徰����������', '31', '�º���徰����������', '533100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7009390a34474afe8e3122be8006dd75', '876e69ae2bfa4251883a30c66df81480', null, null, 'ŭ��������������', '33', 'ŭ��������������', '533300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d87d78ea6435473faf023cc21eabb452', '876e69ae2bfa4251883a30c66df81480', null, null, '�������������', '34', '�������������', '533400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c5b38e0ab90d494dad25c3d85b510cee', 'a7aed62816bb4066ab1be9188bfd8c35', null, null, '������', '01', '������', '540100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0f59a76aeef144489eaaf8edd763f507', 'a7aed62816bb4066ab1be9188bfd8c35', null, null, '��������', '21', '��������', '542100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0127cc2437fb4b1aab1311ed373da4d5', 'a7aed62816bb4066ab1be9188bfd8c35', null, null, 'ɽ�ϵ���', '22', 'ɽ�ϵ���', '542200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f146c88e9f61487e8187bcdc280e2897', 'a7aed62816bb4066ab1be9188bfd8c35', null, null, '�տ������', '23', '�տ������', '542300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ad1a12715fab4d57bf0bfdda2eb9db6a', 'a7aed62816bb4066ab1be9188bfd8c35', null, null, '��������', '24', '��������', '542400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('be59c52820d04d2c9b5c91d7f4dae34f', 'a7aed62816bb4066ab1be9188bfd8c35', null, null, '�������', '25', '�������', '542500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('83bc76056d074e4c94c5824d81e94650', 'a7aed62816bb4066ab1be9188bfd8c35', null, null, '��֥����', '26', '��֥����', '542600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('66d88b8406a8414580256a8811834fea', 'e6540d12ad4047daa20ff64b371161d6', null, null, '������', '01', '������', '610100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dc7a159373914f47badac80b9cf2017c', 'e6540d12ad4047daa20ff64b371161d6', null, null, 'ͭ����', '02', 'ͭ����', '610200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('82170bdd8f344d218a4c5ac36f7ecba1', 'e6540d12ad4047daa20ff64b371161d6', null, null, '������', '03', '������', '610300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a32c87dbe139417e9453b255f5e8245e', 'e6540d12ad4047daa20ff64b371161d6', null, null, '������', '04', '������', '610400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1c33dae1d58144d9b2fa688784875771', 'e6540d12ad4047daa20ff64b371161d6', null, null, 'μ����', '05', 'μ����', '610500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a5ab6a40f75e4885a2aa4e6137d9d84d', 'e6540d12ad4047daa20ff64b371161d6', null, null, '�Ӱ���', '06', '�Ӱ���', '610600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('626c4abdd9e94c4c86bc68b3da343b20', 'e6540d12ad4047daa20ff64b371161d6', null, null, '������', '07', '������', '610700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('288c789701ac4bcc8096dd8d85085239', 'e6540d12ad4047daa20ff64b371161d6', null, null, '������', '08', '������', '610800', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b39593a949c4411ebe181f0f397daf3d', 'e6540d12ad4047daa20ff64b371161d6', null, null, '������', '09', '������', '610900', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('96efc0d8bf4b4484adde54865b3e7c63', 'e6540d12ad4047daa20ff64b371161d6', null, null, '������', '10', '������', '611000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('53d713790c804ce1b1c4d65acd66b7a0', '247ea530058f4ff9a212f3b5649dc552', null, null, '������', '01', '������', '620100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d3d08413da874670810bc7cb5e0ca7de', '247ea530058f4ff9a212f3b5649dc552', null, null, '��������', '02', '��������', '620200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c4e09846f7cc4ed69c3e6564e1c4d4ad', '247ea530058f4ff9a212f3b5649dc552', null, null, '�����', '03', '�����', '620300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('072c6981f53d4706a27a9d05225eafa2', '247ea530058f4ff9a212f3b5649dc552', null, null, '������', '04', '������', '620400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2388cbcd7b30404292f8e01ad88e6f8d', '247ea530058f4ff9a212f3b5649dc552', null, null, '��ˮ��', '05', '��ˮ��', '620500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fb52b2008d774fd2b47b94f855cd16ec', '247ea530058f4ff9a212f3b5649dc552', null, null, '������', '06', '������', '620600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1a27e665108d48c09c043c88ff56c67d', '247ea530058f4ff9a212f3b5649dc552', null, null, '��Ҵ��', '07', '��Ҵ��', '620700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0e2ee1874f68474c938f9443a89fcfb1', '247ea530058f4ff9a212f3b5649dc552', null, null, 'ƽ����', '08', 'ƽ����', '620800', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3f166d4baf12434eb102caddab35da47', '247ea530058f4ff9a212f3b5649dc552', null, null, '��Ȫ��', '09', '��Ȫ��', '620900', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1c075e3ccaa749859d5b0cecef73e511', '247ea530058f4ff9a212f3b5649dc552', null, null, '������', '10', '������', '621000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('624e4398f3b2409cbbda688316142235', '247ea530058f4ff9a212f3b5649dc552', null, null, '������', '11', '������', '621100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4032edf9271c4bb8868553b88b39f0a8', '247ea530058f4ff9a212f3b5649dc552', null, null, '¤����', '12', '¤����', '621200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2c655ecb0f154555be84a4fbea473151', '247ea530058f4ff9a212f3b5649dc552', null, null, '���Ļ���������', '29', '���Ļ���������', '622900', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1357e5dfbbf14671863b4ab27f3a49c5', '247ea530058f4ff9a212f3b5649dc552', null, null, '���ϲ���������', '30', '���ϲ���������', '623000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('08f84a76863048f9becb8841b144612a', '76aa51345f1441c7993b0f2a0f7f0acd', null, null, '������', '01', '������', '630100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ad6f5e8260eb417b92b45279a5eb6541', '76aa51345f1441c7993b0f2a0f7f0acd', null, null, '��������', '21', '��������', '632100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a74335d4de524feb84872d0b37ad5a91', '76aa51345f1441c7993b0f2a0f7f0acd', null, null, '��������������', '22', '��������������', '632200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ee978f1e33d6487199681886cb542a85', '76aa51345f1441c7993b0f2a0f7f0acd', null, null, '���ϲ���������', '23', '���ϲ���������', '632300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9ade5d5a68fb4caca8f084aee72998ce', '76aa51345f1441c7993b0f2a0f7f0acd', null, null, '���ϲ���������', '25', '���ϲ���������', '632500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7a81f15f33a24260be9be6bd5c899858', '76aa51345f1441c7993b0f2a0f7f0acd', null, null, '�������������', '26', '�������������', '632600', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dc53af9d04744ee0a57c7d0bbdf69f22', '76aa51345f1441c7993b0f2a0f7f0acd', null, null, '��������������', '27', '��������������', '632700', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dcd38da0a0c847798551ec50c280dab0', '76aa51345f1441c7993b0f2a0f7f0acd', null, null, '�����ɹ������������', '28', '�����ɹ������������', '632800', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('69256a8474b549efa757e0a17618ed51', '1884978b76d6469c871140b620617381', '314000', null, '������', '11', '������', '330411', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0da634a617854436bfdb79a93fe20187', null, null, null, '�����', '12', '�����', '120000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3d4bd283cbd04c1c85722c6865f1b772', null, null, null, '�ӱ�ʡ', '13', '�ӱ�ʡ', '130000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5f8761dec97040dfb75f459d1657a84b', null, null, null, 'ɽ��ʡ', '14', 'ɽ��ʡ', '140000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d5795820975447d090bdbd135c4e4edc', null, null, null, '���ɹ�', '15', '���ɹ�', '150000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2478110f8a104a189fb6f4a0eb4bde1c', null, null, null, '����ʡ', '21', '����ʡ', '210000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fd50361298cb470087d56f69e7734640', '31f843473a4d4710a5d8cb462ee84cd1', null, null, '������', '03', '������', '350300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('26697abbf2fb49239b261c010a69a343', null, null, null, '����ʡ', '22', '����ʡ', '220000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('909b15dd3d9c4c4b9aeee646b5ffc8a8', null, null, null, '������ʡ', '23', '������ʡ', '230000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3b6cce816ea04ff7b3dc68abeca243dd', null, null, null, '�Ϻ���', '31', '�Ϻ���', '310000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('02c8619f11814155982eb5b1873353bd', null, null, null, '����ʡ', '32', '����ʡ', '320000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ea63e61e420c4134be3db192220422f9', null, null, null, '�㽭ʡ', '33', '�㽭ʡ', '330000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('82f82898ec5d42a797e25a8039a7c1da', null, null, null, '����ʡ', '34', '����ʡ', '340000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('31f843473a4d4710a5d8cb462ee84cd1', null, null, null, '����ʡ', '35', '����ʡ', '350000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('89af740e57c443e8bf70af185481d025', null, null, null, '����ʡ', '36', '����ʡ', '360000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('43e6e48fca984902b2da31dc7babd4f5', null, null, null, 'ɽ��ʡ', '37', 'ɽ��ʡ', '370000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8c9c2e275ecb461fa3bd1039d130a011', null, null, null, '����ʡ', '41', '����ʡ', '410000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3cda75cb85e3483881ba373b0ef35585', null, null, null, '����ʡ', '42', '����ʡ', '420000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('33bc3bb1a9dc4533b9de70de59c1ec1c', null, null, null, '����ʡ', '43', '����ʡ', '430000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b51c615743e24581bf67975def934d06', null, null, null, '�㶫ʡ', '44', '�㶫ʡ', '440000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('47cf09ecda50413398cdf4afa27101e8', null, null, null, '����', '45', '����', '450000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c87dbdbabac04801a83a8f7c8070235e', null, null, null, '����ʡ', '46', '����ʡ', '460000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('005b3f1def644d8aae1231bb6c7499d5', null, null, null, '������', '50', '������', '500000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('79c341fcd1fe4b91a5d97cbb1a070068', null, null, null, '�Ĵ�ʡ', '51', '�Ĵ�ʡ', '510000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d3b62fa78aa843649485428da6b36015', null, null, null, '����ʡ', '52', '����ʡ', '520000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('876e69ae2bfa4251883a30c66df81480', null, null, null, '����ʡ', '53', '����ʡ', '530000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a7aed62816bb4066ab1be9188bfd8c35', null, null, null, '����������', '54', '����������', '540000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e6540d12ad4047daa20ff64b371161d6', null, null, null, '����ʡ', '61', '����ʡ', '610000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('247ea530058f4ff9a212f3b5649dc552', null, null, null, '����ʡ', '62', '����ʡ', '620000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('76aa51345f1441c7993b0f2a0f7f0acd', null, null, null, '�ຣʡ', '63', '�ຣʡ', '630000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('862442793c45460da8884117d171c83d', null, null, null, '���Ļ���������', '64', '���Ļ���������', '640000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2f37fad27ab344108f4da87d9d21e767', null, null, null, '�½�ά���������', '65', '�½�ά���������', '650000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3c96b28c5ada4a2f825530c2b6759f57', null, null, null, '���', '81', '���', '810000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fcbe56f302194a42a7f51066cadc4577', null, null, null, '����', '82', '����', '820000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1e0a610de3b241058546ad6c891fcfb4', null, null, null, '̨��', '71', '̨��', '710000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bb2c98b3923c431da6a1566bc808c10g', 'b7aaf275ffe04792adcf47668fc7f7ba', null, null, '������', '01', '������', '110100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bb2c98b3923c431da6a1566bc808c10f', '0da634a617854436bfdb79a93fe20187', null, null, '�����', '01', '�����', '120100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cbf57834e6314708abbbb577e400e698', '3d4bd283cbd04c1c85722c6865f1b772', null, null, 'ʯ��ׯ��', '01', 'ʯ��ׯ��', '130100', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('eb9674438d3b4d568aa8119ea8881a91', '3d4bd283cbd04c1c85722c6865f1b772', null, null, '��ɽ��', '02', '��ɽ��', '130200', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0c198b04fac8436fbd64f504af86ed74', '3d4bd283cbd04c1c85722c6865f1b772', null, null, '�ػʵ���', '03', '�ػʵ���', '130300', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c6db83b3a6b24136bffb63610712f5de', '3d4bd283cbd04c1c85722c6865f1b772', null, null, '������', '04', '������', '130400', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b1661da6929548f58fc4ac41d14d2b89', '3d4bd283cbd04c1c85722c6865f1b772', null, null, '��̨��', '05', '��̨��', '130500', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ba486a249618479f92af7c2f6b1f7c12', 'c85fe163313c4fd78e007d8ab1d9a9c7', '222000', null, '������', '05', '������', '320705', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('92ed57654fcc4880adeb1b5d7929eb79', 'c85fe163313c4fd78e007d8ab1d9a9c7', '222000', null, '������', '06', '������', '320706', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5211809c31bd4f649c2a63b27f5f285c', 'c85fe163313c4fd78e007d8ab1d9a9c7', '222100', null, '������', '21', '������', '320721', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c57da7fbefa74da3876cc04e0210e9ec', 'c85fe163313c4fd78e007d8ab1d9a9c7', '222300', null, '������', '22', '������', '320722', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b38039cef91d4aa487db9e56b965f2cf', 'c85fe163313c4fd78e007d8ab1d9a9c7', '222200', null, '������', '23', '������', '320723', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e32092cd4b5745d087156dbb6c6e3310', 'c85fe163313c4fd78e007d8ab1d9a9c7', '223500', null, '������', '24', '������', '320724', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('725b7f33d6a14bf499f089df3316826b', '83fcca5dad594e5da9b2c4e7fe0c6ba8', '223000', null, '��Ͻ��', '01', '��Ͻ��', '320801', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8cba31fcc0264e4bb8deee1fd7bff628', '83fcca5dad594e5da9b2c4e7fe0c6ba8', '223001', null, '�����', '02', '�����', '320802', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('259abc9454f94100a81cb51d81b61d2d', '83fcca5dad594e5da9b2c4e7fe0c6ba8', '223200', null, '������', '03', '������', '320803', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e94cadfb8ff24d1aa574bb8ab173ff11', '83fcca5dad594e5da9b2c4e7fe0c6ba8', '223300', null, '������', '04', '������', '320804', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b96205cf03c546fa935493193778d0fe', '83fcca5dad594e5da9b2c4e7fe0c6ba8', '223001', null, '������', '11', '������', '320811', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2e160060ced44fe08facd0de9da5a2dd', '83fcca5dad594e5da9b2c4e7fe0c6ba8', '223400', null, '��ˮ��', '26', '��ˮ��', '320826', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5718b689b7ec4067857dcf03d0ea3f6c', '83fcca5dad594e5da9b2c4e7fe0c6ba8', '223100', null, '������', '29', '������', '320829', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d51cca695b6d43b5a4726eb7e734e81e', '83fcca5dad594e5da9b2c4e7fe0c6ba8', '211700', null, '������', '30', '������', '320830', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ecfc1d5a3ae54cc292ca2e025331b7fb', '83fcca5dad594e5da9b2c4e7fe0c6ba8', '211600', null, '�����', '31', '�����', '320831', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('525740b0641947cbad72159c8d01525e', '789b2371daed489babc5ce0b82cf9bec', '224000', null, '��Ͻ��', '01', '��Ͻ��', '320901', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5e619456f34c4f2eb643b00d2ba0e8a3', '789b2371daed489babc5ce0b82cf9bec', '224000', null, 'ͤ����', '02', 'ͤ����', '320902', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1f2124a5beda40a594e6807689d9bf28', '789b2371daed489babc5ce0b82cf9bec', '224000', null, '�ζ���', '03', '�ζ���', '320903', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1dd8a44a4dbb4e78a9e93ada13f828e6', '789b2371daed489babc5ce0b82cf9bec', '224600', null, '��ˮ��', '21', '��ˮ��', '320921', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a51db186566247cca8aa312bd746f41f', '789b2371daed489babc5ce0b82cf9bec', '224000', null, '������', '22', '������', '320922', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1ad561cdc321463fba927b445ca7c275', '789b2371daed489babc5ce0b82cf9bec', '224400', null, '������', '23', '������', '320923', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2ca15e927d3b49bca5d0b04fbea24666', '789b2371daed489babc5ce0b82cf9bec', '224300', null, '������', '24', '������', '320924', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e5314c25d3ab43eb88ca40f213a03a05', '789b2371daed489babc5ce0b82cf9bec', '224700', null, '������', '25', '������', '320925', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('411d10ca617a4b9bbffa29a2f3ee32af', '789b2371daed489babc5ce0b82cf9bec', '224200', null, '��̨��', '81', '��̨��', '320981', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('13de32c1839f47bc83412d585eba8770', '789b2371daed489babc5ce0b82cf9bec', '224100', null, '�����', '82', '�����', '320982', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7fb1c1006c4e4decb2a3909067087fcb', 'a9330bf8add64d57927ab4faae098a13', '225000', null, '��Ͻ��', '01', '��Ͻ��', '321001', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('054ecc93f49e4424859a52fe2bf21c96', 'a9330bf8add64d57927ab4faae098a13', '225000', null, '������', '02', '������', '321002', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5a83c9b6ae7443e9b84171664eed0d88', 'a9330bf8add64d57927ab4faae098a13', '225100', null, '������', '03', '������', '321003', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d8d1d1377b46421cb05c73c18a3aa116', 'a9330bf8add64d57927ab4faae098a13', '225200', null, '����', '11', '����', '321011', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4344b8efb04b40a3903639aafc525f2a', 'a9330bf8add64d57927ab4faae098a13', '225800', null, '��Ӧ��', '23', '��Ӧ��', '321023', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a6a58dc9a64c46568e0d9e2c630da148', 'a9330bf8add64d57927ab4faae098a13', '211400', null, '������', '81', '������', '321081', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6cff6d7484af4247a0e15c18bd855e9f', 'a9330bf8add64d57927ab4faae098a13', '225600', null, '������', '84', '������', '321084', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('72fad3a4342b4272b9204c24569934ff', 'a9330bf8add64d57927ab4faae098a13', '225200', null, '������', '88', '������', '321088', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7a107ef6f3a84cd486c386f50a92b7d4', 'b34159526635417eac1df82dd9c7859b', '272600', null, '��ɽ��', '32', '��ɽ��', '370832', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6d9442998a6f4c04957abac513ceed24', '405b1da4465e49088c798360db70c15b', '623500', null, '��ˮ��', '28', '��ˮ��', '513228', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ea1b0c79a53746a9be38269d4fe30767', '405b1da4465e49088c798360db70c15b', '624000', null, '��������', '29', '��������', '513229', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('042daaa6b1c74e6cbf709bd62294cfa5', '405b1da4465e49088c798360db70c15b', '624300', null, '������', '30', '������', '513230', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('48a4e3ad7b7c4aef99e16d094c63e6f8', '405b1da4465e49088c798360db70c15b', '624600', null, '������', '31', '������', '513231', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2b5b80feff514d2a96219f989fe8a9f7', '405b1da4465e49088c798360db70c15b', '624500', null, '��������', '32', '��������', '513232', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('57d742d03fc4496caa5397527fed6189', '405b1da4465e49088c798360db70c15b', '624400', null, '��ԭ��', '33', '��ԭ��', '513233', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1745371df9c34ed188f308c83b123033', 'e2b4b10999ff41bbafb98f60bb00c0ae', '626000', null, '������', '21', '������', '513321', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('43c161b5514e41ac8fcedba8137b13e7', 'e2b4b10999ff41bbafb98f60bb00c0ae', '626100', null, '����', '22', '����', '513322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2d9f076ec6054c7e85a1b9e5d8647130', 'e2b4b10999ff41bbafb98f60bb00c0ae', '626300', null, '������', '23', '������', '513323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('44021d2c808a4fc79a8d97315ad69416', 'e2b4b10999ff41bbafb98f60bb00c0ae', '616200', null, '������', '24', '������', '513324', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('92658aeba461438a84cf2b43310d704d', 'e2b4b10999ff41bbafb98f60bb00c0ae', '627450', null, '�Ž���', '25', '�Ž���', '513325', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0a6a33a30df048cb8f365399d4de2150', 'e2b4b10999ff41bbafb98f60bb00c0ae', '626400', null, '������', '26', '������', '513326', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('79bdb0c88df7448b999f7d52cf24f996', 'e2b4b10999ff41bbafb98f60bb00c0ae', '626500', null, '¯����', '27', '¯����', '513327', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('eeb949e483384aa38887452ac4bb4a4a', 'e2b4b10999ff41bbafb98f60bb00c0ae', '626700', null, '������', '28', '������', '513328', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6d3019ca3cff48dcb8de803f7721b332', 'e2b4b10999ff41bbafb98f60bb00c0ae', '626800', null, '������', '29', '������', '513329', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e31b95c4e9da443ca425080b5d29eeba', 'e2b4b10999ff41bbafb98f60bb00c0ae', '627250', null, '�¸���', '30', '�¸���', '513330', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('99b79d41b2f242b4b029a205990fcb49', 'e2b4b10999ff41bbafb98f60bb00c0ae', '627150', null, '������', '31', '������', '513331', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0e954e02b0784e50a247a540f436b183', 'e2b4b10999ff41bbafb98f60bb00c0ae', '627350', null, 'ʯ����', '32', 'ʯ����', '513332', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ccf32b11307d409eb6afd172cba958eb', 'e2b4b10999ff41bbafb98f60bb00c0ae', '626600', null, 'ɫ����', '33', 'ɫ����', '513333', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('726310cdf3f7450daab08b4f5f22e883', 'e2b4b10999ff41bbafb98f60bb00c0ae', '624300', null, '������', '34', '������', '513334', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('602ea94a388a45b08124ac76e3aecba6', 'e2b4b10999ff41bbafb98f60bb00c0ae', '627650', null, '������', '35', '������', '513335', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('356698d25e9349a0b1bd4e216f40021b', 'e2b4b10999ff41bbafb98f60bb00c0ae', '627850', null, '�����', '36', '�����', '513336', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('acb83656d5d841e5932f16431fdb4522', 'e2b4b10999ff41bbafb98f60bb00c0ae', '627750', null, '������', '37', '������', '513337', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f9dcba4160304242bdb5596673c47787', 'e2b4b10999ff41bbafb98f60bb00c0ae', '627950', null, '������', '38', '������', '513338', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e2e565250da6481ea615b72505fed785', 'd6b0f77426fb44c690e4059d93094127', '615000', null, '������', '01', '������', '513401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('50ab07b6e7824a488f44b97ff4547014', 'd6b0f77426fb44c690e4059d93094127', '615800', null, 'ľ�����������', '22', 'ľ�����������', '513422', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3e2cbd4f64f34c4d87f2057f49c9cb72', 'd6b0f77426fb44c690e4059d93094127', '615700', null, '��Դ��', '23', '��Դ��', '513423', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dc70db69786f4b76adc4eaa1fccf2f12', 'd6b0f77426fb44c690e4059d93094127', '615500', null, '�²���', '24', '�²���', '513424', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d9671e49f7384983ab769788353f36c3', 'd6b0f77426fb44c690e4059d93094127', '615100', null, '������', '25', '������', '513425', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('74497faff4ed46d58bd0741eb0bf29d8', 'd6b0f77426fb44c690e4059d93094127', '615200', null, '�ᶫ��', '26', '�ᶫ��', '513426', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e5c70a853a31485d82f970624939f199', 'd6b0f77426fb44c690e4059d93094127', '615400', null, '������', '27', '������', '513427', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('34c93656dbb044f8949f8947f9ab815a', 'd6b0f77426fb44c690e4059d93094127', '615300', null, '�ո���', '28', '�ո���', '513428', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c4d2193ca96e48fb99f77dd790f3c374', 'd6b0f77426fb44c690e4059d93094127', '615350', null, '������', '29', '������', '513429', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1c01397a6f394c878a69ac4e0c08faf5', 'd6b0f77426fb44c690e4059d93094127', '616250', null, '������', '30', '������', '513430', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('085a48cb84f04b7987e217408562202d', 'd6b0f77426fb44c690e4059d93094127', '616150', null, '�Ѿ���', '31', '�Ѿ���', '513431', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('389f5ae4c4684c9aa04f6df58940eb5b', 'd6b0f77426fb44c690e4059d93094127', '616750', null, 'ϲ����', '32', 'ϲ����', '513432', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8e864b49e998492c9f28a4ba7bba6a99', 'd6b0f77426fb44c690e4059d93094127', '615600', null, '������', '33', '������', '513433', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f7ca6189a14343b4ad2376d6703a6885', 'd6b0f77426fb44c690e4059d93094127', '616650', null, 'Խ����', '34', 'Խ����', '513434', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1c91b7c85b1045a780fb421fd9602cb6', 'd6b0f77426fb44c690e4059d93094127', '616850', null, '������', '35', '������', '513435', null);
commit;
prompt 1000 records committed...
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f193e12564f54bb38445478efad16cb1', 'd6b0f77426fb44c690e4059d93094127', '616450', null, '������', '36', '������', '513436', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('94fd0e6a9a6d470cb980c1aa7cae3621', 'd6b0f77426fb44c690e4059d93094127', '616550', null, '�ײ���', '37', '�ײ���', '513437', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7441d72350e3452ea47ecba693712a4b', 'f8bee6d70b4d4aaebf536852686432c1', '550000', null, '��Ͻ��', '01', '��Ͻ��', '520101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2336f16c3d384e4d9b7d99b782b5ac4e', 'f8bee6d70b4d4aaebf536852686432c1', '550000', null, '������', '02', '������', '520102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d355100a65d248c493bce3c69036c3ac', 'f8bee6d70b4d4aaebf536852686432c1', '550000', null, '������', '03', '������', '520103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('021bd3d5f2b34160939622ad87e42a0a', 'f8bee6d70b4d4aaebf536852686432c1', '550000', null, '��Ϫ��', '11', '��Ϫ��', '520111', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('032be45221e74de1a668e086370fd328', 'f8bee6d70b4d4aaebf536852686432c1', '550000', null, '�ڵ���', '12', '�ڵ���', '520112', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('544e596e692a44f0b9c91602446b346d', 'f8bee6d70b4d4aaebf536852686432c1', '550000', null, '������', '13', '������', '520113', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8fa99d1af30e48a696f3b51acf7340cd', 'f8bee6d70b4d4aaebf536852686432c1', '550000', null, 'С����', '14', 'С����', '520114', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d276d9abef214e09ab0cd122b7e5fdb4', 'f8bee6d70b4d4aaebf536852686432c1', '550300', null, '������', '21', '������', '520121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1956900f137640eea25d2c6906088b5f', 'f8bee6d70b4d4aaebf536852686432c1', '551100', null, 'Ϣ����', '22', 'Ϣ����', '520122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dc3719e3c28d4caa92d9304780744697', 'f8bee6d70b4d4aaebf536852686432c1', '550200', null, '������', '23', '������', '520123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('995497df634e4fa1b2faaa034f926e8b', 'f8bee6d70b4d4aaebf536852686432c1', '551400', null, '������', '81', '������', '520181', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4d07e54dcd5442d5ac094f88d4f9082a', 'f027f86e80f547bd898e22f4695c1146', '553000', null, '��ɽ��', '01', '��ɽ��', '520201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('82b7e4d7ff14417882405ee52703e962', 'f027f86e80f547bd898e22f4695c1146', '553400', null, '��֦����', '03', '��֦����', '520203', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ee4e9d7b98bf47458e883c25f732c133', 'f027f86e80f547bd898e22f4695c1146', '553000', null, 'ˮ����', '21', 'ˮ����', '520221', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('24a75134e51c43a3a5e41a1fd8279392', 'f027f86e80f547bd898e22f4695c1146', '561600', null, '����', '22', '����', '520222', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6b17e463b17d4170add96034d5762835', 'a931713a606b4c92b9279967858d62e1', '563000', null, '��Ͻ��', '01', '��Ͻ��', '520301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('94502a33c2a5436b8154e1990a547c4a', 'a931713a606b4c92b9279967858d62e1', '563000', null, '�컨����', '02', '�컨����', '520302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('eb44f4efc81f4c999a11a4d7568c5fc4', '97727b55303e4938afa1eb316b3d58cb', '572300', null, '��ͤ��������������', '35', '��ͤ��������������', '469035', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ffffe5f24b7f4b23ad3c873ea46c3e90', '97727b55303e4938afa1eb316b3d58cb', '572900', null, '������������������', '36', '������������������', '469036', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ad3f30c1a86d43b3bd49dd6d9e4e3f68', '97727b55303e4938afa1eb316b3d58cb', '572000', null, '��ɳȺ��', '37', '��ɳȺ��', '469037', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('10d02bbd86ab478397276c2c3e6bc1fb', '97727b55303e4938afa1eb316b3d58cb', '572000', null, '��ɳȺ��', '38', '��ɳȺ��', '469038', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f957a689d42541d2b8ddb6bf06abeeb9', '97727b55303e4938afa1eb316b3d58cb', '572000', null, '��ɳȺ���ĵ������亣��', '39', '��ɳȺ���ĵ������亣��', '469039', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('072d6624adbb4a2090e7ec66be56e9f1', 'b980cca7c3e04419a36d045d0745ad97', '404100', null, '������', '01', '������', '500101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('98c9a9f0067447b493d50ee64293a9c2', '5944b21cd62d4404bab999965db12d4a', '533100', null, '������', '27', '������', '451027', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1a5de9b3761e4b01816258b257ba300b', 'e416c82c26c848c1a9532943280f7738', '617000', null, '����', '02', '����', '510402', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b8160ab2a6aa4528a143c38c2b262a38', 'e416c82c26c848c1a9532943280f7738', '617000', null, '����', '03', '����', '510403', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('94d56d47973f4296930fab55a1a824f0', 'e416c82c26c848c1a9532943280f7738', '617000', null, '�ʺ���', '11', '�ʺ���', '510411', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c487e3058e9e48028d28d494cb70303d', 'e416c82c26c848c1a9532943280f7738', '617200', null, '������', '21', '������', '510421', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('036ce523ef374af0bc770a2770c39af2', 'e416c82c26c848c1a9532943280f7738', '617100', null, '�α���', '22', '�α���', '510422', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8655b2a2bb3a418c8536736eb76e9e8a', '988dcd4876d041d190834258b0f2a8d4', '646000', null, '��Ͻ��', '01', '��Ͻ��', '510501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1e79b1ca506d4536ac3d2896d4cc7004', '988dcd4876d041d190834258b0f2a8d4', '646000', null, '������', '02', '������', '510502', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c4fd569521c54c46b2aab70472a842be', '988dcd4876d041d190834258b0f2a8d4', '646300', null, '��Ϫ��', '03', '��Ϫ��', '510503', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0ee22ed3eb804a829711171d26be82b0', '988dcd4876d041d190834258b0f2a8d4', '646000', null, '����̶��', '04', '����̶��', '510504', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f9a4f31fe50c4fa3be83b439bd7f32a1', '988dcd4876d041d190834258b0f2a8d4', '646100', null, '����', '21', '����', '510521', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d0109492c4354c6392ef341ce1570184', '988dcd4876d041d190834258b0f2a8d4', '646200', null, '�Ͻ���', '22', '�Ͻ���', '510522', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('15f6d1c3e92145379a5077296392e80a', '988dcd4876d041d190834258b0f2a8d4', '646400', null, '������', '24', '������', '510524', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0c11f69f2bc342208d5ec89744b1bb5b', '988dcd4876d041d190834258b0f2a8d4', '646500', null, '������', '25', '������', '510525', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0eacb23d6ee44a81a84ee68f06814142', 'c87b68c64f854490888b630fbcbd0b15', '618000', null, '��Ͻ��', '01', '��Ͻ��', '510601', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('14c000f26ae6428e90e333dbf17941e2', 'c87b68c64f854490888b630fbcbd0b15', '618000', null, '�����', '03', '�����', '510603', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a4765259b1e74ea0839f7fc6ecf315d1', 'c87b68c64f854490888b630fbcbd0b15', '618300', null, '�н���', '23', '�н���', '510623', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e2101dbda660468da86f499d55554c7d', 'c87b68c64f854490888b630fbcbd0b15', '618500', null, '�޽���', '26', '�޽���', '510626', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d4ffa145da704aff9bdb57ff29c35ea2', 'c87b68c64f854490888b630fbcbd0b15', '618300', null, '�㺺��', '81', '�㺺��', '510681', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0953dabc18c74a7cb26377c132d491fe', 'c87b68c64f854490888b630fbcbd0b15', '618400', null, 'ʲ����', '82', 'ʲ����', '510682', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('eb42b27ab7f24e89bb04b417de99bcd9', 'c87b68c64f854490888b630fbcbd0b15', '618200', null, '������', '83', '������', '510683', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bfc1785f90874e1bbae4443d92cd8ac9', '48fb275bc9004d74aeb5ff42510ade11', '621000', null, '��Ͻ��', '01', '��Ͻ��', '510701', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('425f27f955d04a1e8eeeeb84a301689d', '48fb275bc9004d74aeb5ff42510ade11', '621000', null, '������', '03', '������', '510703', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5bdfdcb11dbb47abbd7cad901bca2242', '48fb275bc9004d74aeb5ff42510ade11', '621000', null, '������', '04', '������', '510704', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('33ded9bd3c394f41b54a71f94f44fc69', '48fb275bc9004d74aeb5ff42510ade11', '621100', null, '��̨��', '22', '��̨��', '510722', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('65a8a8eae0414a1788e1d6ae1a158f56', '48fb275bc9004d74aeb5ff42510ade11', '621600', null, '��ͤ��', '23', '��ͤ��', '510723', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('85e053a732fe414c9bfd66e0c191e6c4', '48fb275bc9004d74aeb5ff42510ade11', '622650', null, '����', '24', '����', '510724', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a3cd65f20d4e4d509db74e18922d99ed', '48fb275bc9004d74aeb5ff42510ade11', '622150', null, '������', '25', '������', '510725', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('70bc5dce85e14a8ebad5a6ff829e1b3c', '48fb275bc9004d74aeb5ff42510ade11', '622550', null, '����Ǽ��������', '26', '����Ǽ��������', '510726', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ff5e9a081ce143df8cf7991afa8c98bc', '48fb275bc9004d74aeb5ff42510ade11', '622550', null, 'ƽ����', '27', 'ƽ����', '510727', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e510ae334e594b8bb8cce07cf044a96d', '48fb275bc9004d74aeb5ff42510ade11', '621700', null, '������', '81', '������', '510781', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8b70110e6dbf4bccba6bd72af06e12e6', '9d9c7883ebbe4190abfa32a3b2e001d6', '628000', null, '��Ͻ��', '01', '��Ͻ��', '510801', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f7f56c8b8e504b2a9c48eb8e072bc7cf', '9d9c7883ebbe4190abfa32a3b2e001d6', '628000', null, '������', '02', '������', '510802', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('565cc01b577e4be99a5269af3f9bfbe8', '9d9c7883ebbe4190abfa32a3b2e001d6', '628000', null, 'Ԫ����', '11', 'Ԫ����', '510811', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('03ff5136e55245c989ca13a982d1d280', '9d9c7883ebbe4190abfa32a3b2e001d6', '628000', null, '������', '12', '������', '510812', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b7b169ae33794e36b78e6674a1be87e1', '9d9c7883ebbe4190abfa32a3b2e001d6', '628200', null, '������', '21', '������', '510821', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('56fc8afbf78e4eab9f466877322da2dc', '9d9c7883ebbe4190abfa32a3b2e001d6', '628100', null, '�ന��', '22', '�ന��', '510822', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('662c44ba743a41068f928f3ff27d280d', '9d9c7883ebbe4190abfa32a3b2e001d6', '628300', null, '������', '23', '������', '510823', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1c3e5aec3cbd4043b0d936b5a50ac86b', '9d9c7883ebbe4190abfa32a3b2e001d6', '628400', null, '��Ϫ��', '24', '��Ϫ��', '510824', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7bc315dce4044c9aa8562e8fb14261ec', 'aded03dddeff43778b7b5b6f0c070a56', '629000', null, '��Ͻ��', '01', '��Ͻ��', '510901', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0ba16625829348d989c8d66f62db6f42', 'aded03dddeff43778b7b5b6f0c070a56', '629000', null, '��ɽ��', '03', '��ɽ��', '510903', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c844f3170ac44bf690a1270af3aa9f7a', '0dc3aad6447a4a92818dc72d59bb1a44', '251200', null, '������', '82', '������', '371482', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b04cadc76785429d9499e8575094cc35', '54d95647b0ee4c219e70c84a2a74b396', '252000', null, '��Ͻ��', '01', '��Ͻ��', '371501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f4ee7612cf974ca0a22bbd0d12287449', '54d95647b0ee4c219e70c84a2a74b396', '252000', null, '��������', '02', '��������', '371502', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('379e0a9de504445fa59908eea8bf297d', '54d95647b0ee4c219e70c84a2a74b396', '252300', null, '������', '21', '������', '371521', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('85d1279d2ae44094bee6fba5255a836c', '54d95647b0ee4c219e70c84a2a74b396', '252400', null, 'ݷ��', '22', 'ݷ��', '371522', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cc8c8e0550b34b4b84ecd9d7ef811c16', '54d95647b0ee4c219e70c84a2a74b396', '252100', null, '��ƽ��', '23', '��ƽ��', '371523', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dbbc9414a6444451862ea87c9250f372', '54d95647b0ee4c219e70c84a2a74b396', '252200', null, '������', '24', '������', '371524', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8df5e1a82ba1460eadb3c5d4ed41940b', '54d95647b0ee4c219e70c84a2a74b396', '252500', null, '����', '25', '����', '371525', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e9efc3f3ba944469a18d5caf4b5d427d', '54d95647b0ee4c219e70c84a2a74b396', '252800', null, '������', '26', '������', '371526', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('af74dad0a0b740df899ccd0cc88e57f6', '54d95647b0ee4c219e70c84a2a74b396', '252600', null, '������', '81', '������', '371581', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5f913555484d48f2b9d962a3811f4c12', '18d0a9122294487eb4aa8d5d6849ec93', '256600', null, '��Ͻ��', '01', '��Ͻ��', '371601', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1e274bbe6c6d48bcafc72775c5145558', '18d0a9122294487eb4aa8d5d6849ec93', '256600', null, '������', '02', '������', '371602', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f40059e9c66d4921a5196ca35cae8805', '55b987a4d8ed49d99701fc0c6ad87720', '366200', null, '������', '25', '������', '350825', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c0c95d892d1049d5b28b474ba4ada54a', '55b987a4d8ed49d99701fc0c6ad87720', '364400', null, '��ƽ��', '81', '��ƽ��', '350881', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6205987226e440c6ab7d3bfe0e547888', '6fd17ced4f124cc4881a50ad26321855', '352000', null, '��Ͻ��', '01', '��Ͻ��', '350901', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fa30b9c5258840869a16ccb72d65239f', '6fd17ced4f124cc4881a50ad26321855', '352000', null, '������', '02', '������', '350902', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('200f03f97b5d46b882a46a39fc1b0344', '6fd17ced4f124cc4881a50ad26321855', '355100', null, 'ϼ����', '21', 'ϼ����', '350921', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('170cb3ee7be248db87b8fceb9f27cabc', '6fd17ced4f124cc4881a50ad26321855', '352200', null, '������', '22', '������', '350922', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d08d5950f83a4c7eaa9b44a3440f6262', '6fd17ced4f124cc4881a50ad26321855', '352300', null, '������', '23', '������', '350923', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('eff8ad44500b4a07969a3b0db262e787', 'fd50361298cb470087d56f69e7734640', '351100', null, '������', '02', '������', '350302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6304f8a3a03f422c8cd7922cb671a372', 'fd50361298cb470087d56f69e7734640', '351100', null, '������', '03', '������', '350303', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('161e97a2dbf5448399ab6efe6a50296a', 'fd50361298cb470087d56f69e7734640', '351100', null, '�����', '04', '�����', '350304', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('239eba3995ec4a7c937286ff57b02323', 'fd50361298cb470087d56f69e7734640', '351100', null, '������', '05', '������', '350305', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2482599d88ee4f53a2b1a97416a8157d', 'fd50361298cb470087d56f69e7734640', '351200', null, '������', '22', '������', '350322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f27f6c898cf3451d91199de25d311954', '75b39c1f74b445319410ef34beb28c3b', '365000', null, '��Ͻ��', '01', '��Ͻ��', '350401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e25c6236f58e4671a0deecefeb252d22', '75b39c1f74b445319410ef34beb28c3b', '365000', null, '÷����', '02', '÷����', '350402', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f2b1d04534e44cf39b2c0b056468a77a', '75b39c1f74b445319410ef34beb28c3b', '365000', null, '��Ԫ��', '03', '��Ԫ��', '350403', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('230fea32e84249a59b8c98c7c3a5ccd9', '75b39c1f74b445319410ef34beb28c3b', '365300', null, '��Ϫ��', '21', '��Ϫ��', '350421', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8d218bec5bec49c984b95c565618b3c9', '75b39c1f74b445319410ef34beb28c3b', '365300', null, '������', '23', '������', '350423', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('62c732f17abc4ed7bd566a876f25f784', '75b39c1f74b445319410ef34beb28c3b', '365400', null, '������', '24', '������', '350424', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('884198b9f36d4c6a93158517ef626289', '75b39c1f74b445319410ef34beb28c3b', '366100', null, '������', '25', '������', '350425', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f36658c4b1d440c7ad33615c130c0e97', '75b39c1f74b445319410ef34beb28c3b', '365100', null, '��Ϫ��', '26', '��Ϫ��', '350426', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('acb95346e78b476fab566a31006090c6', '75b39c1f74b445319410ef34beb28c3b', '365500', null, 'ɳ��', '27', 'ɳ��', '350427', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('06130c2886e0492d914011b607e97e0e', '75b39c1f74b445319410ef34beb28c3b', '353300', null, '������', '28', '������', '350428', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4d8f048128d54b239c1f8f05cb35829b', '75b39c1f74b445319410ef34beb28c3b', '354400', null, '̩����', '29', '̩����', '350429', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('431f9557ae9f475d884b58025c7bd55c', '75b39c1f74b445319410ef34beb28c3b', '354500', null, '������', '30', '������', '350430', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bb0c269fa6c54e52ad884ee208498833', '75b39c1f74b445319410ef34beb28c3b', '366000', null, '������', '81', '������', '350481', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c3b97c19725f41dab1b7155e28302cd7', '60da25ef271a45e88a92c016ef7e469c', '362000', null, '��Ͻ��', '01', '��Ͻ��', '350501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6068847d8edd42cc84d04e290c5b79aa', '60da25ef271a45e88a92c016ef7e469c', '362000', null, '�����', '02', '�����', '350502', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b8bba3d665124bda83996697970a9b50', '60da25ef271a45e88a92c016ef7e469c', '362000', null, '������', '03', '������', '350503', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0ff6addd6dec488fa0398b54b65b24ee', '60da25ef271a45e88a92c016ef7e469c', '362000', null, '�彭��', '04', '�彭��', '350504', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('873afa1b97fc4194aa11037eb4b62d66', '60da25ef271a45e88a92c016ef7e469c', '362100', null, 'Ȫ����', '05', 'Ȫ����', '350505', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6540885201d448ddbae7f07e4b89607e', '60da25ef271a45e88a92c016ef7e469c', '362100', null, '�ݰ���', '21', '�ݰ���', '350521', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fe4b3c8e56134b2e9c2115e48314bf74', '60da25ef271a45e88a92c016ef7e469c', '362400', null, '��Ϫ��', '24', '��Ϫ��', '350524', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9e47a8aa7b4f4a49a37930d9c86aa3aa', '60da25ef271a45e88a92c016ef7e469c', '362600', null, '������', '25', '������', '350525', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ec3bf607f9684099967489179f93ae03', '60da25ef271a45e88a92c016ef7e469c', '362500', null, '�»���', '26', '�»���', '350526', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9e5cd703373c4aae8411143dd2eb8b0c', '60da25ef271a45e88a92c016ef7e469c', '362000', null, '������', '27', '������', '350527', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2ebfe750008d46fba694e7ec11990253', '60da25ef271a45e88a92c016ef7e469c', '362700', null, 'ʯʨ��', '81', 'ʯʨ��', '350581', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('777bf8c15abe41c98f8a2ab9050b9e92', '60da25ef271a45e88a92c016ef7e469c', '362200', null, '������', '82', '������', '350582', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1beac1da384f419cb66fec83adedba4e', '60da25ef271a45e88a92c016ef7e469c', '362300', null, '�ϰ���', '83', '�ϰ���', '350583', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c1596533139d4105a2e773cc59c5830b', '523109f4617c4f9394d802e616806354', '363000', null, '��Ͻ��', '01', '��Ͻ��', '350601', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0d97172992394666b9cdf32e4c1ee994', '523109f4617c4f9394d802e616806354', '363000', null, 'ܼ����', '02', 'ܼ����', '350602', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('df60c2d48ac749b9945cfb7f5de86545', '523109f4617c4f9394d802e616806354', '363000', null, '������', '03', '������', '350603', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dd4a3a6c95a64d8da4a4cc4ff0946d28', '523109f4617c4f9394d802e616806354', '363300', null, '������', '22', '������', '350622', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1c5d9c860ada4744b08207639684b798', '523109f4617c4f9394d802e616806354', '363200', null, '������', '23', '������', '350623', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4cbb7449b7194f69b083f2ccf1a6b2f3', '523109f4617c4f9394d802e616806354', '363500', null, 'گ����', '24', 'گ����', '350624', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e59ba2c5005a451bb9830205f96d1189', '523109f4617c4f9394d802e616806354', '363900', null, '��̩��', '25', '��̩��', '350625', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f37291697ddc489893fb7cea9a498b3a', '523109f4617c4f9394d802e616806354', '363400', null, '��ɽ��', '26', '��ɽ��', '350626', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('477442247f434c6d8e71af0ae27d7f1a', '523109f4617c4f9394d802e616806354', '363600', null, '�Ͼ���', '27', '�Ͼ���', '350627', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ca2fbfd531944c21bc44028cf730f4c5', '523109f4617c4f9394d802e616806354', '363700', null, 'ƽ����', '28', 'ƽ����', '350628', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('16049f668f2c4b138185296b396b5e2f', '523109f4617c4f9394d802e616806354', '363800', null, '������', '29', '������', '350629', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cb24ac8feb2a4a5cb152a4f958edff3e', '523109f4617c4f9394d802e616806354', '363100', null, '������', '81', '������', '350681', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('212d32bf814a48f5879f25c5499b49a4', '66c6fb2856b6486cad0f949d7c1045e3', '353000', null, '��Ͻ��', '01', '��Ͻ��', '350701', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('88489217393b4fefabb31e030ea948e3', '66c6fb2856b6486cad0f949d7c1045e3', '353000', null, '��ƽ��', '02', '��ƽ��', '350702', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6b9ca32a3a634a009d4fe0b9adf0e36b', '66c6fb2856b6486cad0f949d7c1045e3', '353200', null, '˳����', '21', '˳����', '350721', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0bae3ae2d64940e1b0edc33b5b61fa6c', '66c6fb2856b6486cad0f949d7c1045e3', '353400', null, '�ֳ���', '22', '�ֳ���', '350722', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d1aa081d36fe4ab0bff627cebb3aa15c', '66c6fb2856b6486cad0f949d7c1045e3', '354100', null, '������', '23', '������', '350723', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b030bd996a8e4d739e9bc4a284f37224', '66c6fb2856b6486cad0f949d7c1045e3', '353500', null, '��Ϫ��', '24', '��Ϫ��', '350724', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('340f6b359b944b9a84c44c4009fd3da4', '66c6fb2856b6486cad0f949d7c1045e3', '353600', null, '������', '25', '������', '350725', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('602f9a027a914d7dbd867ace964c41eb', '66c6fb2856b6486cad0f949d7c1045e3', '354000', null, '������', '81', '������', '350781', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('df5d0d4ca98b4573b0adfa96af2aced0', '66c6fb2856b6486cad0f949d7c1045e3', '354300', null, '����ɽ��', '82', '����ɽ��', '350782', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c4b86b7ee81e42bda12073886ac7cee0', '66c6fb2856b6486cad0f949d7c1045e3', '353100', null, '�����', '83', '�����', '350783', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('431ba40c7bda493ab42b6f18e30d2da3', '66c6fb2856b6486cad0f949d7c1045e3', '354200', null, '������', '84', '������', '350784', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8abdbe25a2404019a0d9fe0f0f14e14b', '55b987a4d8ed49d99701fc0c6ad87720', '364000', null, '��Ͻ��', '01', '��Ͻ��', '350801', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0d2d4bc01ff34e74b2e28eab4755c5e8', '55b987a4d8ed49d99701fc0c6ad87720', '364000', null, '������', '02', '������', '350802', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6a85a2612f234a659db7d816f4e01130', '55b987a4d8ed49d99701fc0c6ad87720', '366300', null, '��͡��', '21', '��͡��', '350821', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3fdb604cb88e4463975337e4d31e9d32', '55b987a4d8ed49d99701fc0c6ad87720', '364100', null, '������', '22', '������', '350822', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('301eb1f47e0f4809b17618e07707fa55', '55b987a4d8ed49d99701fc0c6ad87720', '364200', null, '�Ϻ���', '23', '�Ϻ���', '350823', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3aee2ea9f5244f7cb5c2b8b5e4184528', '55b987a4d8ed49d99701fc0c6ad87720', '364300', null, '��ƽ��', '24', '��ƽ��', '350824', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5d2171bade9c4c16b41c44f1ee63c7c5', 'b34159526635417eac1df82dd9c7859b', '272300', null, '��̨��', '27', '��̨��', '370827', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('68bd885c3a4941608fc7da9d5a7bbf76', 'a7a5db86046e4359b593d5bb51806516', '512200', null, '�ֲ���', '81', '�ֲ���', '440281', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1efdeca6f1084c1b886905145e91e4da', 'a7a5db86046e4359b593d5bb51806516', '512400', null, '������', '82', '������', '440282', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8d76cd748b8e4f418752fba9d8e7488d', '6a4a9d34b6904464805411c3d91a1313', '518000', null, '��Ͻ��', '01', '��Ͻ��', '440301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fb3a7593af454dac9c62b75e384f3542', '6a4a9d34b6904464805411c3d91a1313', '518000', null, '�޺���', '03', '�޺���', '440303', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('43296a0a150c4b7392dd69bdee0531a0', '6fd17ced4f124cc4881a50ad26321855', '355500', null, '������', '24', '������', '350924', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7c26dfdffbce47229f5b589b62873188', '8fdba2c226b74f689e9095d060a98cd2', '155800', null, '������', '22', '������', '230522', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3cf4b71d6e0a49eb8d05a2d68f72bad3', '1f444db499d740989434bb11e14f54a3', '212000', null, '������', '02', '������', '321102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('607e3dae9454489b8627fdf4f22a7e0a', '1f444db499d740989434bb11e14f54a3', '212000', null, '������', '11', '������', '321111', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('49506db6b05c4b0ca3c2e38da43b51e8', '1f444db499d740989434bb11e14f54a3', '212100', null, '��ͽ��', '12', '��ͽ��', '321112', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('00b4283f86ba49e7811ee5a6a96a3e33', '1f444db499d740989434bb11e14f54a3', '212300', null, '������', '81', '������', '321181', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c0e201198d504fa490757dc5273d7d6e', '1f444db499d740989434bb11e14f54a3', '212200', null, '������', '82', '������', '321182', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('be7c0c9c995745e3b8623f92d8c0d18f', '1f444db499d740989434bb11e14f54a3', '212400', null, '������', '83', '������', '321183', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('86152881188c473188e1cc0ea8b096f7', '835c9ac0e6824b37a4af2b626524362f', '225300', null, '��Ͻ��', '01', '��Ͻ��', '321201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aaaf5c46bb904915925635ff5564ba97', '835c9ac0e6824b37a4af2b626524362f', '225300', null, '������', '02', '������', '321202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('03cab6a7a0d4432c892f515775a83bf9', '835c9ac0e6824b37a4af2b626524362f', '225300', null, '�߸���', '03', '�߸���', '321203', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('27d733bcf1ec49ea88c63a6bb83f42ce', '835c9ac0e6824b37a4af2b626524362f', '225700', null, '�˻���', '81', '�˻���', '321281', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d550dc89cc994b798b9bc208375d781d', '835c9ac0e6824b37a4af2b626524362f', '214500', null, '������', '82', '������', '321282', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c39f7f5123a2412f8d5b5d18a118ece1', '835c9ac0e6824b37a4af2b626524362f', '225400', null, '̩����', '83', '̩����', '321283', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3d2093255266431382238b91895cbd6b', '835c9ac0e6824b37a4af2b626524362f', '225500', null, '������', '84', '������', '321284', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('80d9ffe755054696869cbbf5af0bfd63', 'd6fe0afb4aec42bc8cb70ad349bfb31a', '223800', null, '��Ͻ��', '01', '��Ͻ��', '321301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fd20d19413a2400896a4ffae6b238e8b', 'd6fe0afb4aec42bc8cb70ad349bfb31a', '223800', null, '�޳���', '02', '�޳���', '321302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('07fc17040570474ba03ed63c309af86e', 'd6fe0afb4aec42bc8cb70ad349bfb31a', '223800', null, '��ԥ��', '11', '��ԥ��', '321311', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7f35e49d1ed04fd5b4fc0da53742ea71', 'd6fe0afb4aec42bc8cb70ad349bfb31a', '223600', null, '������', '22', '������', '321322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('00ac7c4f83884f9d9d07d6a872bc8d9b', 'd6fe0afb4aec42bc8cb70ad349bfb31a', '223700', null, '������', '23', '������', '321323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('11cdee7dbfd34738a83b76de8d13aca1', 'd6fe0afb4aec42bc8cb70ad349bfb31a', '223900', null, '������', '24', '������', '321324', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e10669f3c215465390156a14ff9da81f', '948adc095d0448cbbc8a28a43b126cd7', '310000', null, '��Ͻ��', '01', '��Ͻ��', '330101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5736c6f7f28e4823ac6330d2a375634b', '948adc095d0448cbbc8a28a43b126cd7', '310000', null, '�ϳ���', '02', '�ϳ���', '330102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2717334f9bfd40e8a2fdcdac868ac5ef', '948adc095d0448cbbc8a28a43b126cd7', '310000', null, '�³���', '03', '�³���', '330103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cc15cba827d24a71999a7d4d7676155c', '948adc095d0448cbbc8a28a43b126cd7', '310000', null, '������', '04', '������', '330104', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('93c1db8a519f4e3d8454966efada6d15', '948adc095d0448cbbc8a28a43b126cd7', '310000', null, '������', '05', '������', '330105', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b09f7f7e5c824d918c03240e196fe5c9', '948adc095d0448cbbc8a28a43b126cd7', '310000', null, '������', '06', '������', '330106', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('47ac918e7354454c9791063dc6487161', '948adc095d0448cbbc8a28a43b126cd7', '310000', null, '������', '08', '������', '330108', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8c04b1a09f3a455c92dbd0fcfe42e0c3', '948adc095d0448cbbc8a28a43b126cd7', '311200', null, '��ɽ��', '09', '��ɽ��', '330109', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d97ec727dfe5429eae4a450ecc307de9', '948adc095d0448cbbc8a28a43b126cd7', '311100', null, '�ຼ��', '10', '�ຼ��', '330110', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('098bbcfff34c418ea5a898f12d724573', '948adc095d0448cbbc8a28a43b126cd7', '311500', null, 'ͩ®��', '22', 'ͩ®��', '330122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('930b9c431bcc4ef4a044a827e35f12d1', '948adc095d0448cbbc8a28a43b126cd7', '311700', null, '������', '27', '������', '330127', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('baa02708f55a4de3a73adc25ad628690', '948adc095d0448cbbc8a28a43b126cd7', '311600', null, '������', '82', '������', '330182', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b50ecade13a649a5a75e6ca46def5a0c', '948adc095d0448cbbc8a28a43b126cd7', '311400', null, '������', '83', '������', '330183', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8701edc398484fa8a23bb94cbcd75b3a', '948adc095d0448cbbc8a28a43b126cd7', '311300', null, '�ٰ���', '85', '�ٰ���', '330185', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('587aa0d79b024f72bf93023d5ca8d848', 'b579455d7ae4445fb895e24f98caa299', '315000', null, '��Ͻ��', '01', '��Ͻ��', '330201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5e8025c7892c48f196c8a81e00ae426b', 'b579455d7ae4445fb895e24f98caa299', '315000', null, '������', '03', '������', '330203', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0516bdd3f0e6454ab3b5c24df403387b', 'b579455d7ae4445fb895e24f98caa299', '315000', null, '������', '04', '������', '330204', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a7f74552c2ff44bbbb0f66e59d6afa74', 'b579455d7ae4445fb895e24f98caa299', '315000', null, '������', '05', '������', '330205', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('956a6b84ef034366804640a448ca67fe', 'b579455d7ae4445fb895e24f98caa299', '315800', null, '������', '06', '������', '330206', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ceda6b11fb7b41cfabd4635220baa5b6', 'b579455d7ae4445fb895e24f98caa299', '315200', null, '����', '11', '����', '330211', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6095626675d84a6281b2cbc23a89e1f5', 'b579455d7ae4445fb895e24f98caa299', '315100', null, '۴����', '12', '۴����', '330212', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3d499631212f44a6b9a7ffbde5a58f54', 'b579455d7ae4445fb895e24f98caa299', '315700', null, '��ɽ��', '25', '��ɽ��', '330225', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('571cf67b0f49434cb051904cfe9a8227', 'b579455d7ae4445fb895e24f98caa299', '315600', null, '������', '26', '������', '330226', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('acf540d2771c4af7bca198527488d4b1', 'b579455d7ae4445fb895e24f98caa299', '315400', null, '��Ҧ��', '81', '��Ҧ��', '330281', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2519da1f494442b094fa373d520c0203', 'b579455d7ae4445fb895e24f98caa299', '315300', null, '��Ϫ��', '82', '��Ϫ��', '330282', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bb5a188670354224ad45f1d64adf2161', 'b579455d7ae4445fb895e24f98caa299', '315500', null, '���', '83', '���', '330283', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e144ac66fd4541b8965dc3e8c1c87cfd', '725d382f33c54a83a7db831c3da82665', '325000', null, '��Ͻ��', '01', '��Ͻ��', '330301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aa37eee93c2f438884daf9951adbbddc', '725d382f33c54a83a7db831c3da82665', '325000', null, '¹����', '02', '¹����', '330302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f2dcbee7ad0c45d3ac7f048cd166046d', '725d382f33c54a83a7db831c3da82665', '325000', null, '������', '03', '������', '330303', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('000a23afb49842958342afa196c056e8', '725d382f33c54a83a7db831c3da82665', '325000', null, '걺���', '04', '걺���', '330304', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('72b01ef3704d4a79b60f3c0dceb5d156', '725d382f33c54a83a7db831c3da82665', '325700', null, '��ͷ��', '22', '��ͷ��', '330322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8b692bdfb1be4af7bd2224f1557aafa4', '725d382f33c54a83a7db831c3da82665', '325100', null, '������', '24', '������', '330324', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0c990642780b477a9389311cc7cf5897', '725d382f33c54a83a7db831c3da82665', '325400', null, 'ƽ����', '26', 'ƽ����', '330326', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('28eb844182d74fa1bf153d6a2c228cc8', '725d382f33c54a83a7db831c3da82665', '325800', null, '������', '27', '������', '330327', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('99609fc8c205403bad849580b2dd6bf5', '725d382f33c54a83a7db831c3da82665', '325300', null, '�ĳ���', '28', '�ĳ���', '330328', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f39c62562d5c480d8b9794a00ce68807', '725d382f33c54a83a7db831c3da82665', '325500', null, '̩˳��', '29', '̩˳��', '330329', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('efcef8b9f2214295999e10fc98dcb211', '725d382f33c54a83a7db831c3da82665', '325200', null, '����', '81', '����', '330381', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('adb37e5246504cd299d95e8c72e64c1b', '725d382f33c54a83a7db831c3da82665', '325600', null, '������', '82', '������', '330382', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('70e535782003463c8aedf8cbbe6909af', '1884978b76d6469c871140b620617381', '314000', null, '��Ͻ��', '01', '��Ͻ��', '330401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('da64ba27cb4c4230a498f8956c638c3d', '1884978b76d6469c871140b620617381', '314000', null, '�Ϻ���', '02', '�Ϻ���', '330402', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('01c2ed127f874d32bdafdd97c591530c', 'a7a5db86046e4359b593d5bb51806516', '512600', null, '��Դ����������', '32', '��Դ����������', '440232', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('581a09e9b20e403bb3de1ae32f7260d0', '1884978b76d6469c871140b620617381', '314100', null, '������', '21', '������', '330421', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c434ea5b2e474095b649ce4a77181d46', 'b34159526635417eac1df82dd9c7859b', '272200', null, '������', '28', '������', '370828', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('15d95077412b45cf9420bbb2b07c5fdd', 'b34159526635417eac1df82dd9c7859b', '272400', null, '������', '29', '������', '370829', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b3280d60acf24c90b0380bdf719313f2', 'b34159526635417eac1df82dd9c7859b', '272500', null, '������', '30', '������', '370830', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1fa8b9f960a04264acfcc359f0fbde38', 'b34159526635417eac1df82dd9c7859b', '273200', null, '��ˮ��', '31', '��ˮ��', '370831', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e4890c92cdf34a609e06cbff33d152a0', '77e868a833554971a102184336921723', '313000', null, '��Ͻ��', '01', '��Ͻ��', '330501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('26a0d0fd62cd4f239e224227f375e9f4', '21f243e300a74fb4abb0138b942daf00', '118000', null, '����', '04', '����', '210604', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fa1eeeec40db4e9f9e1e69631b502487', '21f243e300a74fb4abb0138b942daf00', '118200', null, '��������������', '24', '��������������', '210624', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('01e25759bf9642829b708ff1d2863cff', '21f243e300a74fb4abb0138b942daf00', '118300', null, '������', '81', '������', '210681', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('185d7981be64419c93dab5b4208187fc', '21f243e300a74fb4abb0138b942daf00', '118100', null, '�����', '82', '�����', '210682', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e3c679951c0d46f3a32fed303df16a1d', '6cd8d97a762c424985e64493c40c3dd1', '121000', null, '��Ͻ��', '01', '��Ͻ��', '210701', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c6fe52c63bed47138364ad2f5e75ea95', '6cd8d97a762c424985e64493c40c3dd1', '121000', null, '������', '02', '������', '210702', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aa77c5a9a46646b48334c7ff4ec895a1', '6cd8d97a762c424985e64493c40c3dd1', '121000', null, '�����', '03', '�����', '210703', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0acf54cf67034f0eac3d5522d4e6abf3', '6cd8d97a762c424985e64493c40c3dd1', '121000', null, '̫����', '11', '̫����', '210711', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f4fffee7b2da4fc0803bdf3316d461fb', '6cd8d97a762c424985e64493c40c3dd1', '121400', null, '��ɽ��', '26', '��ɽ��', '210726', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1b5c9c8950ac4fd6a6d24969a9746346', '6cd8d97a762c424985e64493c40c3dd1', '121100', null, '����', '27', '����', '210727', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ec3f265f5ab34fedaa4296edda1453f3', '6cd8d97a762c424985e64493c40c3dd1', '121200', null, '�躣��', '81', '�躣��', '210781', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c31313e76df8422dbcabf38e927e3874', '6cd8d97a762c424985e64493c40c3dd1', '121300', null, '������', '82', '������', '210782', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dd26d804325a4dd0b45cadef323a3bff', '2395708e898b4fb59076a40fba93436a', '115000', null, '��Ͻ��', '01', '��Ͻ��', '210801', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f3f870129c4440959a4a1818b5a2e61f', '2395708e898b4fb59076a40fba93436a', '115000', null, 'վǰ��', '02', 'վǰ��', '210802', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2f98d5004ab84f5eb1cde9deefd0fd99', '2395708e898b4fb59076a40fba93436a', '115000', null, '������', '03', '������', '210803', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b86016aee1eb4f6f959979f734c34adb', '2395708e898b4fb59076a40fba93436a', '115000', null, '����Ȧ��', '04', '����Ȧ��', '210804', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7eeef746dfa94224b99b494d0cf5bd02', '2395708e898b4fb59076a40fba93436a', '115000', null, '�ϱ���', '11', '�ϱ���', '210811', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cc033d4d5e314fd1931322f0ccc4be18', '2395708e898b4fb59076a40fba93436a', '115200', null, '������', '81', '������', '210881', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8e9d8c0353564435bcc951966d25db9f', '2395708e898b4fb59076a40fba93436a', '115100', null, '��ʯ����', '82', '��ʯ����', '210882', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0d9b09b6d1a641a08720b24653ae430c', '575d6ce067aa4da9a890efc1ee49b8d7', '123000', null, '��Ͻ��', '01', '��Ͻ��', '210901', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3f757a611e7a438fad958500162de255', '575d6ce067aa4da9a890efc1ee49b8d7', '123000', null, '������', '02', '������', '210902', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e8f47d594acd439883575aaef5a81c30', '575d6ce067aa4da9a890efc1ee49b8d7', '123000', null, '������', '03', '������', '210903', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9ecb53023b964d819722a578aee13019', '575d6ce067aa4da9a890efc1ee49b8d7', '123000', null, '̫ƽ��', '04', '̫ƽ��', '210904', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c76530a1a7c3451d9190dbc7dce6d023', '575d6ce067aa4da9a890efc1ee49b8d7', '123000', null, '�������', '05', '�������', '210905', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fbe3c43173ed4302a77167b5a76ece4e', '575d6ce067aa4da9a890efc1ee49b8d7', '123000', null, 'ϸ����', '11', 'ϸ����', '210911', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d76bda0003e841688728fc4f8ea32971', '575d6ce067aa4da9a890efc1ee49b8d7', '123100', null, '�����ɹ���������', '21', '�����ɹ���������', '210921', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d21b287e8fc2408091128bb2846580a9', '575d6ce067aa4da9a890efc1ee49b8d7', '123200', null, '������', '22', '������', '210922', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('650a3901705d443791e71b47fcc4ce7c', 'fb07ee3fe7964963bc75133a6263f5f2', '111000', null, '��Ͻ��', '01', '��Ͻ��', '211001', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6cc6242ee76d4c70ae9a68ed87ada043', 'fb07ee3fe7964963bc75133a6263f5f2', '111000', null, '������', '02', '������', '211002', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c6609649716b47528c93d8beb782c4f0', 'fb07ee3fe7964963bc75133a6263f5f2', '111000', null, '��ʥ��', '03', '��ʥ��', '211003', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1ab4498273f6445c8488e6084d01160d', 'fb07ee3fe7964963bc75133a6263f5f2', '111000', null, '��ΰ��', '04', '��ΰ��', '211004', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ad277dc72af14913bb08e3d5e8b14436', 'fb07ee3fe7964963bc75133a6263f5f2', '111000', null, '��������', '05', '��������', '211005', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('df884bc59bc44c2f8d011ec7169d8e03', 'fb07ee3fe7964963bc75133a6263f5f2', '111000', null, '̫�Ӻ���', '11', '̫�Ӻ���', '211011', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('318c39f75c2d490f90bf6b023c09fbca', 'fb07ee3fe7964963bc75133a6263f5f2', '111200', null, '������', '21', '������', '211021', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bba40d3f437d44f9ac42b12c8ff3f8e5', 'fb07ee3fe7964963bc75133a6263f5f2', '111300', null, '������', '81', '������', '211081', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2233e44aa96e48468e443776860745a0', 'bccb2f3371c549638fb5e083d7badfbf', '124000', null, '��Ͻ��', '01', '��Ͻ��', '211101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e165b1224b944e7aa15af1813429904f', 'bccb2f3371c549638fb5e083d7badfbf', '124000', null, '˫̨����', '02', '˫̨����', '211102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('144b53ca5ac043f0a7b0893993f6a474', 'bccb2f3371c549638fb5e083d7badfbf', '124000', null, '��¡̨��', '03', '��¡̨��', '211103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('27767901efb54a6eabf487ec1458c113', 'bccb2f3371c549638fb5e083d7badfbf', '124200', null, '������', '21', '������', '211121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b1ee3cbb839e4f36ad2f54399984c5ca', 'bccb2f3371c549638fb5e083d7badfbf', '124100', null, '��ɽ��', '22', '��ɽ��', '211122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('27a983718f054f729ccf0c375f37437d', '0e4703c44e4540d6b28fba9380400e92', '112000', null, '��Ͻ��', '01', '��Ͻ��', '211201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b8c67b5789524cadbc72456bf4a3f86a', '0e4703c44e4540d6b28fba9380400e92', '112000', null, '������', '02', '������', '211202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('85e2e3a4edbd4ef18cbaaf467caf676f', '0e4703c44e4540d6b28fba9380400e92', '112000', null, '�����', '04', '�����', '211204', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b72c03c58f35495886de41e4c2eb3e8f', '0e4703c44e4540d6b28fba9380400e92', '112600', null, '������', '21', '������', '211221', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e13181596cc8453a94934ebcc45a38a1', '0e4703c44e4540d6b28fba9380400e92', '112400', null, '������', '23', '������', '211223', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6bc02373df724699bf5dfe533b0a44f0', '0e4703c44e4540d6b28fba9380400e92', '112500', null, '��ͼ��', '24', '��ͼ��', '211224', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c84877df5d3743feb046b27ae5a4f20f', '0e4703c44e4540d6b28fba9380400e92', '112700', null, '����ɽ��', '81', '����ɽ��', '211281', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ce83a32ade514101a81c496df7176667', '0e4703c44e4540d6b28fba9380400e92', '112300', null, '��ԭ��', '82', '��ԭ��', '211282', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e144745c2f02419ea51fb06cf9f2dce5', '15afb64f4c90488f9b38836c2eb77d18', '122000', null, '��Ͻ��', '01', '��Ͻ��', '211301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fcee618e3fdf407bad9e3755b87aaf63', '15afb64f4c90488f9b38836c2eb77d18', '122000', null, '˫����', '02', '˫����', '211302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('41139bba5c7f4ca9976caea52d523182', '15afb64f4c90488f9b38836c2eb77d18', '122000', null, '������', '03', '������', '211303', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('244472bcc158478887218051902cf043', '15afb64f4c90488f9b38836c2eb77d18', '122000', null, '������', '21', '������', '211321', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('10b71f97cf9446148758241492f425d2', '15afb64f4c90488f9b38836c2eb77d18', '122400', null, '��ƽ��', '22', '��ƽ��', '211322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c2199f912b0d4f709d266c0258f205df', '15afb64f4c90488f9b38836c2eb77d18', '122300', null, '�����������ɹ���������', '24', '�����������ɹ���������', '211324', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('92a51226807249a0b5b0dbf7fe4883a2', '15afb64f4c90488f9b38836c2eb77d18', '122100', null, '��Ʊ��', '81', '��Ʊ��', '211381', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5a70f37ea1cb49aca3849f4888bea683', '15afb64f4c90488f9b38836c2eb77d18', '122500', null, '��Դ��', '82', '��Դ��', '211382', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4bad214e20bc4c2b8990f4596517b2a8', 'f09d74ecd8c34b269879aa4c409cafcf', '125000', null, '��Ͻ��', '01', '��Ͻ��', '211401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('351e47b8b4ce446c8fe3d43d9657e220', 'f09d74ecd8c34b269879aa4c409cafcf', '125000', null, '��ɽ��', '02', '��ɽ��', '211402', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('60177abd2721459bbe65872895867d10', 'f09d74ecd8c34b269879aa4c409cafcf', '125000', null, '������', '03', '������', '211403', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dc54bebdee5649da9aca85db30f030b0', 'f09d74ecd8c34b269879aa4c409cafcf', '125000', null, '��Ʊ��', '04', '��Ʊ��', '211404', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fbcb06ef432c4e3ba0d9e35df6b02c38', 'f09d74ecd8c34b269879aa4c409cafcf', '125200', null, '������', '21', '������', '211421', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('91b020b2073148bbae98de4e182921af', 'f09d74ecd8c34b269879aa4c409cafcf', '125300', null, '������', '22', '������', '211422', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('449a60f98f3b4bf59a3810ca58eec7e8', 'f09d74ecd8c34b269879aa4c409cafcf', '125100', null, '�˳���', '81', '�˳���', '211481', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('66507853a0524b4baacc873d0c585d3a', '3a3f1ba7751f47dabb57b457b678be60', '130000', null, '��Ͻ��', '01', '��Ͻ��', '220101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('86009786ef59423481ccf729c85ad646', '3a3f1ba7751f47dabb57b457b678be60', '130000', null, '�Ϲ���', '02', '�Ϲ���', '220102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c545b5e61c89405e9858d471c7aa8447', 'e3a4a4ba5c56445c92c6a4d932ae463e', '241200', null, '������', '22', '������', '340222', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a418fad399bb450ea6222f9ca130d456', 'e3a4a4ba5c56445c92c6a4d932ae463e', '242400', null, '������', '23', '������', '340223', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e035074f5c19450680841b568ea62cd0', 'a2c7a37f793c4880a33dd7ca60198d51', '233000', null, '��Ͻ��', '01', '��Ͻ��', '340301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cc72ff17e03d4339baba18d2da58078c', 'a2c7a37f793c4880a33dd7ca60198d51', '233000', null, '���Ӻ���', '02', '���Ӻ���', '340302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3897d1c35164405b87e1db88a665ba28', 'a2c7a37f793c4880a33dd7ca60198d51', '233000', null, '��ɽ��', '03', '��ɽ��', '340303', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('34fad95836b74aa19652473ea791321b', 'a2c7a37f793c4880a33dd7ca60198d51', '233000', null, '������', '04', '������', '340304', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4d0489d289924516a2a17be40cc9c799', 'a2c7a37f793c4880a33dd7ca60198d51', '233000', null, '������', '11', '������', '340311', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6daad8ef8cee48988795e504dbbba0ab', 'a2c7a37f793c4880a33dd7ca60198d51', '233400', null, '��Զ��', '21', '��Զ��', '340321', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('77c088176d3a46a8bdcb4e9ad7bf3725', 'a2c7a37f793c4880a33dd7ca60198d51', '233300', null, '�����', '22', '�����', '340322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('40a7c907cc844c6f9b540f63858c3152', 'a2c7a37f793c4880a33dd7ca60198d51', '233700', null, '������', '23', '������', '340323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8f53a2e0a7de476e912447dc7e9c9e6b', 'eebd1b2f46a7490ead76d682d03caa3d', '232000', null, '��Ͻ��', '01', '��Ͻ��', '340401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0d48423755a649d881c47491a708c803', 'eebd1b2f46a7490ead76d682d03caa3d', '232000', null, '��ͨ��', '02', '��ͨ��', '340402', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f7c6ba2e0aae4667aecc9c83e624443f', 'eebd1b2f46a7490ead76d682d03caa3d', '232000', null, '�������', '03', '�������', '340403', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1126cebb418c444b88fb3e0956ac589b', 'eebd1b2f46a7490ead76d682d03caa3d', '232000', null, 'л�Ҽ���', '04', 'л�Ҽ���', '340404', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('914a70461cc0482c9359640276302bcc', 'eebd1b2f46a7490ead76d682d03caa3d', '232000', null, '�˹�ɽ��', '05', '�˹�ɽ��', '340405', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('55b006317acf45758da699bc559fb9ee', 'eebd1b2f46a7490ead76d682d03caa3d', '232000', null, '�˼���', '06', '�˼���', '340406', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('efd67f0ad9f24d178a4932d8c8e42b25', 'eebd1b2f46a7490ead76d682d03caa3d', '232100', null, '��̨��', '21', '��̨��', '340421', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('68055edede11471cb283aa2d15619a38', '0d19c7615d3e4c3e882056f94b5aecae', '243000', null, '��Ͻ��', '01', '��Ͻ��', '340501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('880d1cce85424195bd28a8ec2ec0685d', '0d19c7615d3e4c3e882056f94b5aecae', '243000', null, '���ׯ��', '02', '���ׯ��', '340502', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('69aa0530100a4515b5102df00e3c478b', '0d19c7615d3e4c3e882056f94b5aecae', '243000', null, '��ɽ��', '03', '��ɽ��', '340503', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b80ee31fcf7c4283b986729747a934bf', '0d19c7615d3e4c3e882056f94b5aecae', '243000', null, '��ɽ��', '04', '��ɽ��', '340504', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('26f2274b4847491fa6a8b9617713b74a', '0d19c7615d3e4c3e882056f94b5aecae', '243100', null, '��Ϳ��', '21', '��Ϳ��', '340521', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d1e1d5655b914aab8fb16f888a48a343', '99b8d897937b424c98d2d8f6a66e15bc', '235000', null, '��Ͻ��', '01', '��Ͻ��', '340601', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0379dfe5ccdb4f948ada91a70c97e78c', '99b8d897937b424c98d2d8f6a66e15bc', '235000', null, '�ż���', '02', '�ż���', '340602', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('eb78c14ab7d04c07a165a1e3bc867afd', '99b8d897937b424c98d2d8f6a66e15bc', '235000', null, '��ɽ��', '03', '��ɽ��', '340603', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7c6500e4bd024417a49bf8cae710b34d', '99b8d897937b424c98d2d8f6a66e15bc', '235000', null, '��ɽ��', '04', '��ɽ��', '340604', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5555db112d9e4c17b7eb6a4f9f9e5ab9', '99b8d897937b424c98d2d8f6a66e15bc', '235100', null, '�Ϫ��', '21', '�Ϫ��', '340621', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dd07fec1df0340ab9b2e11c7c585994d', 'db87ace1e601499894596f7ad2f1ec8a', '244000', null, '��Ͻ��', '01', '��Ͻ��', '340701', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('846dc05914404999bf7f037864c37d5a', 'db87ace1e601499894596f7ad2f1ec8a', '244000', null, 'ͭ��ɽ��', '02', 'ͭ��ɽ��', '340702', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e1b30a56af914e23a793028efcb0d94a', 'db87ace1e601499894596f7ad2f1ec8a', '244000', null, 'ʨ��ɽ��', '03', 'ʨ��ɽ��', '340703', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('184a16576f4042cd8944f644628dbf96', 'db87ace1e601499894596f7ad2f1ec8a', '244000', null, '����', '11', '����', '340711', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6d77b363e56344a092dcf6fb5fa45e15', 'db87ace1e601499894596f7ad2f1ec8a', '244100', null, 'ͭ����', '21', 'ͭ����', '340721', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('69388ea3791c49a4b0fef572f9d6f677', 'b7a3e50258a6477c986d9617cb2da1ca', '246000', null, '��Ͻ��', '01', '��Ͻ��', '340801', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9459e56dcefe4ae294cdabfa2920614b', 'b7a3e50258a6477c986d9617cb2da1ca', '246000', null, 'ӭ����', '02', 'ӭ����', '340802', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('18d78fcf10f843e68ea6525d7d619089', 'b7a3e50258a6477c986d9617cb2da1ca', '246000', null, '�����', '03', '�����', '340803', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('66b91ce34d1244d08c4bf16320acdac1', 'b7a3e50258a6477c986d9617cb2da1ca', '246000', null, '����', '11', '����', '340811', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2bf210ebac2c4f27a4d8b5c0ea0fdf64', 'b7a3e50258a6477c986d9617cb2da1ca', '246100', null, '������', '22', '������', '340822', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aac23674cbba4fd89cee1a89ab84a246', 'b7a3e50258a6477c986d9617cb2da1ca', '246700', null, '������', '23', '������', '340823', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('83283312543f4fc79967b8f934653a57', 'b7a3e50258a6477c986d9617cb2da1ca', '246300', null, 'Ǳɽ��', '24', 'Ǳɽ��', '340824', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a5d44d79ca5c4b1ca46ec4ac4cbc2cf6', 'b7a3e50258a6477c986d9617cb2da1ca', '246400', null, '̫����', '25', '̫����', '340825', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0350f440b9e6463da1a2ae092c94643c', 'b7a3e50258a6477c986d9617cb2da1ca', '246500', null, '������', '26', '������', '340826', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('70855c92d92e438d89029a97f72dfbb5', 'b7a3e50258a6477c986d9617cb2da1ca', '246200', null, '������', '27', '������', '340827', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('13fe7694f7114159bd6c049bac25bf9f', 'b7a3e50258a6477c986d9617cb2da1ca', '246600', null, '������', '28', '������', '340828', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('27af3fc8473c4232950c8def14a9b315', 'b7a3e50258a6477c986d9617cb2da1ca', '231400', null, 'ͩ����', '81', 'ͩ����', '340881', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e81edb87b8aa4a17b7fec187ce2ad04a', 'aeb93b0d0bad495ebf128dfccc009d5a', '245000', null, '��Ͻ��', '01', '��Ͻ��', '341001', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9770117d4f1f49fd929250c5ef39a0ec', 'aeb93b0d0bad495ebf128dfccc009d5a', '245000', null, '��Ϫ��', '02', '��Ϫ��', '341002', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b3a355459e354e8ea6f7d202e950f51c', 'aeb93b0d0bad495ebf128dfccc009d5a', '245000', null, '��ɽ��', '03', '��ɽ��', '341003', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4061ef821a0c452e94eb55d8a1be7892', 'aeb93b0d0bad495ebf128dfccc009d5a', '245000', null, '������', '04', '������', '341004', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a1bfb9bbe5cc43878c4bc81132fe1c67', 'aeb93b0d0bad495ebf128dfccc009d5a', '245200', null, '���', '21', '���', '341021', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b8785c97e71e4335999894d6e7bec1e6', 'aeb93b0d0bad495ebf128dfccc009d5a', '245400', null, '������', '22', '������', '341022', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d33ff72333e1406c8d43688457b7d728', 'aeb93b0d0bad495ebf128dfccc009d5a', '245500', null, '����', '23', '����', '341023', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c9723d96f57e445a9c60b42541472f57', 'aeb93b0d0bad495ebf128dfccc009d5a', '245600', null, '������', '24', '������', '341024', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4de82fdfe82e46e1b6469531f67e5eb4', '56f6e70756b24b2b9b6819c3263e4c19', '239000', null, '��Ͻ��', '01', '��Ͻ��', '341101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('28d4f616115e409d94a81cc2a2ae920c', '56f6e70756b24b2b9b6819c3263e4c19', '239000', null, '������', '02', '������', '341102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b2ef922320224fff91598b8b03a81f07', '56f6e70756b24b2b9b6819c3263e4c19', '239000', null, '������', '03', '������', '341103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('61b89cf7c9554f9288c6814d473bdfb4', '56f6e70756b24b2b9b6819c3263e4c19', '239200', null, '������', '22', '������', '341122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bb2c72b4108f4810903a45b36f2d002b', '56f6e70756b24b2b9b6819c3263e4c19', '239500', null, 'ȫ����', '24', 'ȫ����', '341124', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ebb75cce1c714ca9bab3d2542d6b1da2', '56f6e70756b24b2b9b6819c3263e4c19', '233200', null, '��Զ��', '25', '��Զ��', '341125', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('952ba3fbde9e43208b9b7652ce1d94bc', '56f6e70756b24b2b9b6819c3263e4c19', '233100', null, '������', '26', '������', '341126', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('db9e1859769c48bc93aecabb4c645c29', '56f6e70756b24b2b9b6819c3263e4c19', '239300', null, '�쳤��', '81', '�쳤��', '341181', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6545b2fb4e7c4cf8ab155a1061716205', '56f6e70756b24b2b9b6819c3263e4c19', '239400', null, '������', '82', '������', '341182', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8a3737f115e0457fa0d2c6f418b19d7d', '5afe0ab733f946d2be079f263536d1e4', '236000', null, '��Ͻ��', '01', '��Ͻ��', '341201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8311b658e753469f814eac8b6c695f95', '5afe0ab733f946d2be079f263536d1e4', '236000', null, '�����', '02', '�����', '341202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('16225389c9954837836eadb757bb3409', '5afe0ab733f946d2be079f263536d1e4', '236000', null, '򣶫��', '03', '򣶫��', '341203', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bca4060b0850431c9834dfbb0a6a3dee', '5afe0ab733f946d2be079f263536d1e4', '236000', null, '�Ȫ��', '04', '�Ȫ��', '341204', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ebaba78a95c64d619be32cc75990d40e', '1f444db499d740989434bb11e14f54a3', '212000', null, '��Ͻ��', '01', '��Ͻ��', '321101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4aae268baa484480a2faa0d91f08b848', '8fdba2c226b74f689e9095d060a98cd2', '155600', null, '������', '23', '������', '230523', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e5a81627e1b24b7682b23a1e1274cda1', '8fdba2c226b74f689e9095d060a98cd2', '155700', null, '�ĺ���', '24', '�ĺ���', '230524', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8fc871b844cc46e79a3b43a60040f5ea', 'b4f698e29e7e44a5b58a6d86e8783e36', '163000', null, '��Ͻ��', '01', '��Ͻ��', '230601', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('781f608c12e745adb93252e93d0f8db9', 'b4f698e29e7e44a5b58a6d86e8783e36', '163000', null, '����ͼ��', '02', '����ͼ��', '230602', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8c0b757dc12a45beb0d09321a288de6a', 'b4f698e29e7e44a5b58a6d86e8783e36', '163000', null, '������', '03', '������', '230603', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1172b7ee4995491e9ad0bd6720f953d9', 'b4f698e29e7e44a5b58a6d86e8783e36', '163000', null, '�ú�·��', '04', '�ú�·��', '230604', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d38d2e1f0ee543b48e05b656b41894b1', 'b4f698e29e7e44a5b58a6d86e8783e36', '163000', null, '�����', '05', '�����', '230605', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3e3c2f1b76e74aa9b6516995fc152d3f', 'b4f698e29e7e44a5b58a6d86e8783e36', '163000', null, '��ͬ��', '06', '��ͬ��', '230606', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a6facd5cf4964f308c90c4270d68f88a', 'b4f698e29e7e44a5b58a6d86e8783e36', '166400', null, '������', '21', '������', '230621', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e3ca9fc304c14cbcaf079c86394d36ea', 'b4f698e29e7e44a5b58a6d86e8783e36', '166500', null, '��Դ��', '22', '��Դ��', '230622', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ee0fcd37b06948b6b04ef8f003e873c2', 'b4f698e29e7e44a5b58a6d86e8783e36', '166300', null, '�ֵ���', '23', '�ֵ���', '230623', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('432193b4b22847f0a6e28ae706d7c415', 'b4f698e29e7e44a5b58a6d86e8783e36', '166200', null, '�Ŷ������ɹ���������', '24', '�Ŷ������ɹ���������', '230624', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ebb8ce5f9aa242dbb9899032bc062714', '31a1d04a3c394bf3bf77d67153cdc2c6', '153000', null, '��Ͻ��', '01', '��Ͻ��', '230701', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2a7fa21cb5064a2792abd3c544e8872b', '31a1d04a3c394bf3bf77d67153cdc2c6', '153000', null, '������', '02', '������', '230702', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('498f631ba9954ea3a9a9cf5758756787', '31a1d04a3c394bf3bf77d67153cdc2c6', '153000', null, '�ϲ���', '03', '�ϲ���', '230703', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4a0121873b424b71a63bfc791cc4ba7d', '31a1d04a3c394bf3bf77d67153cdc2c6', '153000', null, '�Ѻ���', '04', '�Ѻ���', '230704', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('82557908d6b546b9b267b0f8cb196f94', '31a1d04a3c394bf3bf77d67153cdc2c6', '153000', null, '������', '05', '������', '230705', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('58463741eeda4cfcb2f4fd02ef9f4b1c', '31a1d04a3c394bf3bf77d67153cdc2c6', '153000', null, '������', '06', '������', '230706', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b56444055f3149ef862188a8735fe041', '31a1d04a3c394bf3bf77d67153cdc2c6', '153000', null, '������', '07', '������', '230707', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b47aa47f9ae4450dba5c41fc883d71c7', '31a1d04a3c394bf3bf77d67153cdc2c6', '153000', null, '��Ϫ��', '08', '��Ϫ��', '230708', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a0973c4359054282901d5167b3c1b0c7', '31a1d04a3c394bf3bf77d67153cdc2c6', '153000', null, '��ɽ����', '09', '��ɽ����', '230709', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5bdc31bdafda4f95b73d0a0500f87556', '31a1d04a3c394bf3bf77d67153cdc2c6', '153000', null, '��Ӫ��', '10', '��Ӫ��', '230710', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d6752f4054514b6790239a7f3653eee3', '31a1d04a3c394bf3bf77d67153cdc2c6', '153000', null, '��������', '11', '��������', '230711', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('16332424a58d40a09b4b3b1f332b8735', '31a1d04a3c394bf3bf77d67153cdc2c6', '153000', null, '��������', '12', '��������', '230712', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b763607e167c43dd92fb3e2d61baf152', '31a1d04a3c394bf3bf77d67153cdc2c6', '153000', null, '������', '13', '������', '230713', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c2051debd3ff4b3eba0f9a58ad12c94d', '31a1d04a3c394bf3bf77d67153cdc2c6', '153000', null, '��������', '14', '��������', '230714', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('879dfdaa23a34b72ad9ed52ba66942bd', '31a1d04a3c394bf3bf77d67153cdc2c6', '153000', null, '������', '15', '������', '230715', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1bca219e170f46639a18b9699d3f0bd6', '31a1d04a3c394bf3bf77d67153cdc2c6', '153000', null, '�ϸ�����', '16', '�ϸ�����', '230716', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d6d6f1afed2049f8b93f63a80aa3304d', '31a1d04a3c394bf3bf77d67153cdc2c6', '153200', null, '������', '22', '������', '230722', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('23ab35817ad34f4d9c9a77d7d403b8ac', '31a1d04a3c394bf3bf77d67153cdc2c6', '152500', null, '������', '81', '������', '230781', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('30742d4a022240cc8c6cf4d36d2f9b81', '0625ff8646c740ae83931ea039f1d8ba', '154000', null, '��Ͻ��', '01', '��Ͻ��', '230801', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('282e33532408422091b54737336100d0', '0625ff8646c740ae83931ea039f1d8ba', '154000', null, '������', '02', '������', '230802', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('55f4b3480440442c9708165ae089028e', '0625ff8646c740ae83931ea039f1d8ba', '154000', null, '������', '03', '������', '230803', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('572885326711474291314d17022b4483', '0625ff8646c740ae83931ea039f1d8ba', '154000', null, 'ǰ����', '04', 'ǰ����', '230804', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('75ce21ae53664933b2afdc09b192808c', '0625ff8646c740ae83931ea039f1d8ba', '154000', null, '������', '05', '������', '230805', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5fadbfc8ab3848568e8b27d63c0957e0', '0625ff8646c740ae83931ea039f1d8ba', '154000', null, '����', '11', '����', '230811', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a22f4ed9b223450e8b353a8a4c81e85b', '0625ff8646c740ae83931ea039f1d8ba', '154400', null, '������', '22', '������', '230822', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fdcba05183044ff9b7a8daf8ad198678', '0625ff8646c740ae83931ea039f1d8ba', '154300', null, '�봨��', '26', '�봨��', '230826', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ab4f30aaeabb448c9dc5a9e75e8bd7dd', '0625ff8646c740ae83931ea039f1d8ba', '154700', null, '��ԭ��', '28', '��ԭ��', '230828', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5941f42fd2f3488c897d212c17c73153', '0625ff8646c740ae83931ea039f1d8ba', '156500', null, '��Զ��', '33', '��Զ��', '230833', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('32111dc57b3449f2ac5bfd3ad18e2d1b', '0625ff8646c740ae83931ea039f1d8ba', '156400', null, 'ͬ����', '81', 'ͬ����', '230881', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bcd75377461c49ccad8b7e280f85b8b7', '0625ff8646c740ae83931ea039f1d8ba', '156100', null, '������', '82', '������', '230882', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('58fc684160df4b4f97dadd6353143132', '7d964b3b2e26428b8f12284ddf2654c4', '154600', null, '��Ͻ��', '01', '��Ͻ��', '230901', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ae33b1d9babb4bee9daff7b75c4439b0', '7d964b3b2e26428b8f12284ddf2654c4', '154600', null, '������', '02', '������', '230902', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('df1689565313495e98e6f4f64eb1d86f', '7d964b3b2e26428b8f12284ddf2654c4', '154600', null, '��ɽ��', '03', '��ɽ��', '230903', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6b73e354998e49dabc0ad4d75b929a2d', '7d964b3b2e26428b8f12284ddf2654c4', '154600', null, '���Ӻ���', '04', '���Ӻ���', '230904', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('093d946d75f44180b65afe6db2f6ab38', '7d964b3b2e26428b8f12284ddf2654c4', '154500', null, '������', '21', '������', '230921', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f659b2e2e95445bd87e7779310fb5882', 'a850022c32f047a39f070ecdb64c10cf', '157000', null, '��Ͻ��', '01', '��Ͻ��', '231001', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('977ee4a1bb1f44c4a324c3dfbc18406f', 'a850022c32f047a39f070ecdb64c10cf', '157000', null, '������', '02', '������', '231002', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('35bcffe8f9394dd1b173eebd557d40f1', 'a850022c32f047a39f070ecdb64c10cf', '157000', null, '������', '03', '������', '231003', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6b6e219ea6384e0d9e52054429760b62', 'a850022c32f047a39f070ecdb64c10cf', '157000', null, '������', '04', '������', '231004', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5ed1b5856b93448188b80cf9d1042cce', 'a850022c32f047a39f070ecdb64c10cf', '157000', null, '������', '05', '������', '231005', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d614668b3f784d8085a04171454ff4bf', 'a850022c32f047a39f070ecdb64c10cf', '157200', null, '������', '24', '������', '231024', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f46a81ccce4547eda36fb11152bc5cb2', 'a850022c32f047a39f070ecdb64c10cf', '157600', null, '�ֿ���', '25', '�ֿ���', '231025', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('10049a1cbbec410d89fdf803e31ffd79', 'a850022c32f047a39f070ecdb64c10cf', '157300', null, '��Һ���', '81', '��Һ���', '231081', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('efc401cd8f034585b1241d28b897090d', 'a850022c32f047a39f070ecdb64c10cf', '157100', null, '������', '83', '������', '231083', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('04211cbbe33d4d48b012601c0a0554af', 'a850022c32f047a39f070ecdb64c10cf', '157400', null, '������', '84', '������', '231084', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5a38a5ccfe964d15a2282059dfc4fbbb', 'a850022c32f047a39f070ecdb64c10cf', '157500', null, '������', '85', '������', '231085', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('31a9972d3faf4e5f87a9e72271539d85', 'e94124d72ea049559b68d29c33d5e9f6', '164300', null, '��Ͻ��', '01', '��Ͻ��', '231101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b7b6ce62f4484ad69b3e75cf5d0b8d26', 'e94124d72ea049559b68d29c33d5e9f6', '164300', null, '������', '02', '������', '231102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('33058f790b7e40b2905b3ebc781607fb', 'e94124d72ea049559b68d29c33d5e9f6', '161400', null, '�۽���', '21', '�۽���', '231121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bfa6e45f36c443edae6f22c0f0b2b5d5', 'e94124d72ea049559b68d29c33d5e9f6', '164400', null, 'ѷ����', '23', 'ѷ����', '231123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f8190800e96748a5ba4fc3bff8977597', 'e94124d72ea049559b68d29c33d5e9f6', '164200', null, '������', '24', '������', '231124', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('200f05ac43bf4724993d6eb0ec3f1c37', 'e94124d72ea049559b68d29c33d5e9f6', '164000', null, '������', '81', '������', '231181', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4348feb984564409bbe7fe14df0fb7f0', 'e94124d72ea049559b68d29c33d5e9f6', '164100', null, '���������', '82', '���������', '231182', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('09ae4d4ff7164d27b23e80d47f0723bf', '0700bce39eff4bbaad9989a4e5283ea5', '152000', null, '��Ͻ��', '01', '��Ͻ��', '231201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ad5f12bc958249e6b382f4517d403ceb', '5afe0ab733f946d2be079f263536d1e4', '236400', null, '��Ȫ��', '21', '��Ȫ��', '341221', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3446e5f50f6b4c38ba28a24459b3a2b6', '5afe0ab733f946d2be079f263536d1e4', '236600', null, '̫����', '22', '̫����', '341222', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('64073cab574c49e186553d9a1d7bfef8', '5afe0ab733f946d2be079f263536d1e4', '236300', null, '������', '25', '������', '341225', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3310a693169d4b9dbda53fb3dab3b3fa', '5afe0ab733f946d2be079f263536d1e4', '236200', null, '�����', '26', '�����', '341226', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ebeece7cbde3497693d45b3aee28cffc', '5afe0ab733f946d2be079f263536d1e4', '236500', null, '������', '82', '������', '341282', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('803432fb639640c5a451108c30307528', 'cc3bd80ea79c413ea58d4d69cb283e70', '234000', null, '��Ͻ��', '01', '��Ͻ��', '341301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('77273e6cf4314ae8bc8545e8c0c63361', 'cc3bd80ea79c413ea58d4d69cb283e70', '234000', null, '������', '02', '������', '341302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bf884db13686483993e698cd66a02cdd', 'cc3bd80ea79c413ea58d4d69cb283e70', '235300', null, '��ɽ��', '21', '��ɽ��', '341321', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('473d139842e14a108a1051e415a3be4b', 'cc3bd80ea79c413ea58d4d69cb283e70', '235200', null, '����', '22', '����', '341322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f979b11a7cd64916aaf26001dcdd78b0', 'cc3bd80ea79c413ea58d4d69cb283e70', '234200', null, '�����', '23', '�����', '341323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('23fd3ef07a974fcea71217132ee5a92a', 'cc3bd80ea79c413ea58d4d69cb283e70', '234300', null, '����', '24', '����', '341324', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ac3e68541bfa417c8ccf77f7da14367f', '3777ebad7996423cb39c8c8a5eef1038', '238000', null, '��Ͻ��', '01', '��Ͻ��', '341401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('83bcd68856c849389bfa8f8105f2cee2', '3777ebad7996423cb39c8c8a5eef1038', '238000', null, '�ӳ���', '02', '�ӳ���', '341402', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('52aba24e84b34a2ba991ab4e4528157d', '3777ebad7996423cb39c8c8a5eef1038', '231500', null, '®����', '21', '®����', '341421', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('72445728f72548e68d4fa67b13967246', '3777ebad7996423cb39c8c8a5eef1038', '238300', null, '��Ϊ��', '22', '��Ϊ��', '341422', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2291f4d66462437bbff20eee81813853', '3777ebad7996423cb39c8c8a5eef1038', '238100', null, '��ɽ��', '23', '��ɽ��', '341423', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('88075f9ee8db466b9615af091e24bed5', '3777ebad7996423cb39c8c8a5eef1038', '238200', null, '����', '24', '����', '341424', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('38c616a339d341628d380f40d05650ae', 'aabcff251e8d49538a041fd8a1992561', '237000', null, '��Ͻ��', '01', '��Ͻ��', '341501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2c4999c0def34510a9edf05fc7173e61', 'aabcff251e8d49538a041fd8a1992561', '237000', null, '����', '02', '����', '341502', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d782133b494248aeb1649c93fbe11fb6', 'aabcff251e8d49538a041fd8a1992561', '237000', null, 'ԣ����', '03', 'ԣ����', '341503', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ddf924e8456441f1a3f307ed4b84bd5c', 'aabcff251e8d49538a041fd8a1992561', '232200', null, '����', '21', '����', '341521', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b793670d765c4d44bdadeb143e36e430', 'aabcff251e8d49538a041fd8a1992561', '237400', null, '������', '22', '������', '341522', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a016b72304324e389caa47f3048b588a', 'aabcff251e8d49538a041fd8a1992561', '231300', null, '�����', '23', '�����', '341523', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('25ced09498db461a89010931b73b7e22', 'aabcff251e8d49538a041fd8a1992561', '237300', null, '��կ��', '24', '��կ��', '341524', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6bd8496ea3a443dd8e1dab1d52110e27', 'aabcff251e8d49538a041fd8a1992561', '237200', null, '��ɽ��', '25', '��ɽ��', '341525', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fe8cd4d025ab4c4cbf561b183df5c28b', 'e78a8a06ed6b4b469f72d85bcf4331af', '236000', null, '��Ͻ��', '01', '��Ͻ��', '341601', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1ff27b20457d4697ae0cd4f8ab66dcbf', 'e78a8a06ed6b4b469f72d85bcf4331af', '236800', null, '�۳���', '02', '�۳���', '341602', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('59d54c4d675343f6bcb08a896b284778', 'e78a8a06ed6b4b469f72d85bcf4331af', '233600', null, '������', '21', '������', '341621', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aff9a208b35a4c4494f24ebca295ce8b', 'e78a8a06ed6b4b469f72d85bcf4331af', '233500', null, '�ɳ���', '22', '�ɳ���', '341622', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('00f66539cb264b73893af57b369338ee', 'e78a8a06ed6b4b469f72d85bcf4331af', '236700', null, '������', '23', '������', '341623', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a061810064b448479092bda909f963ab', '77f0da77ecc84e2c9cdd0bf884cb7437', '247100', null, '��Ͻ��', '01', '��Ͻ��', '341701', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bdb18f5db4344a4f868de64d9612db0f', '77f0da77ecc84e2c9cdd0bf884cb7437', '247100', null, '�����', '02', '�����', '341702', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d8b68adacd0943c09f84983467f8b8cc', '77f0da77ecc84e2c9cdd0bf884cb7437', '247200', null, '������', '21', '������', '341721', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('44cfaaedfc664c7ebfcbb2ab61faf7df', '77f0da77ecc84e2c9cdd0bf884cb7437', '245100', null, 'ʯ̨��', '22', 'ʯ̨��', '341722', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('527dc0823bf34a289df763f46ba356b2', '77f0da77ecc84e2c9cdd0bf884cb7437', '242800', null, '������', '23', '������', '341723', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ce685c44dedc43e08f4a67a3d197b496', '915283302a1448d8a6a5f4c3fe3811b7', '242000', null, '��Ͻ��', '01', '��Ͻ��', '341801', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('df505a68e00b4528bdafbe494e3bd629', '915283302a1448d8a6a5f4c3fe3811b7', '242000', null, '������', '02', '������', '341802', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b25e725af9aa486f9a8edf2937b088d2', '915283302a1448d8a6a5f4c3fe3811b7', '242100', null, '��Ϫ��', '21', '��Ϫ��', '341821', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ba73766db08d498493b7a6f4a2a23655', '915283302a1448d8a6a5f4c3fe3811b7', '242200', null, '�����', '22', '�����', '341822', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7716baa88da94b4fbd2f663707d822f5', '915283302a1448d8a6a5f4c3fe3811b7', '242500', null, '����', '23', '����', '341823', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f92ccc83cd864b6cb7396a801a3d271d', '915283302a1448d8a6a5f4c3fe3811b7', '245300', null, '��Ϫ��', '24', '��Ϫ��', '341824', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ac95ec24a2c5497981a4bda87d6f7127', '915283302a1448d8a6a5f4c3fe3811b7', '242600', null, '캵���', '25', '캵���', '341825', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a514d8b93fca470ebeeacac4040ee3c3', '915283302a1448d8a6a5f4c3fe3811b7', '242300', null, '������', '81', '������', '341881', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ca637844836a4be4a3cba03bb143e209', '61188ed689124e8693ad2da355082375', '350000', null, '��Ͻ��', '01', '��Ͻ��', '350101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c8fa8e0ed451473f83b9426a003470ed', '61188ed689124e8693ad2da355082375', '350000', null, '��¥��', '02', '��¥��', '350102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d3a2b86ed09a4dd5aec08f95f4dd3f7a', '61188ed689124e8693ad2da355082375', '350000', null, '̨����', '03', '̨����', '350103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aa1f2cebe7cd4a999ae5a94929fdd2e8', '61188ed689124e8693ad2da355082375', '350000', null, '��ɽ��', '04', '��ɽ��', '350104', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8465a29adc154eee81364eae69e470c9', '61188ed689124e8693ad2da355082375', '350000', null, '��β��', '05', '��β��', '350105', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5f6a5a3f2a714522bf8f953a69ae8a75', '61188ed689124e8693ad2da355082375', '350000', null, '������', '11', '������', '350111', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2d300ae0b0744d00925d61c9eb1dec63', '61188ed689124e8693ad2da355082375', '350100', null, '������', '21', '������', '350121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b07f27b7294d47c281c85c45e70b781c', '61188ed689124e8693ad2da355082375', '350500', null, '������', '22', '������', '350122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d59fdd5c93df4dabbced5c90be580a8d', '61188ed689124e8693ad2da355082375', '350600', null, '��Դ��', '23', '��Դ��', '350123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3ebf4dc2c5264f45a265411023a289d3', '61188ed689124e8693ad2da355082375', '350800', null, '������', '24', '������', '350124', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8a2f937b8f13454d873619b6e826b1c7', '61188ed689124e8693ad2da355082375', '350700', null, '��̩��', '25', '��̩��', '350125', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c25f6e5a154d48c0a672ca9e00f9ba14', '61188ed689124e8693ad2da355082375', '350400', null, 'ƽ̶��', '28', 'ƽ̶��', '350128', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('69ed063169e14a04a6367432d8954829', '61188ed689124e8693ad2da355082375', '350300', null, '������', '81', '������', '350181', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ffd0fcb91e2d49a7981468487f136c72', '61188ed689124e8693ad2da355082375', '350200', null, '������', '82', '������', '350182', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d06a85e71c6b4a9ea191eed13701ac8b', 'c600d641b3d3489399d3557c9bc5e188', '361000', null, '��Ͻ��', '01', '��Ͻ��', '350201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dba5294e365e4589909d620c132afecf', 'c600d641b3d3489399d3557c9bc5e188', '361000', null, '˼����', '03', '˼����', '350203', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aebd90dd4174405f99b957c349b7888e', 'c600d641b3d3489399d3557c9bc5e188', '361000', null, '������', '05', '������', '350205', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('65d7445f2b45477a9439dd08d389b616', 'c600d641b3d3489399d3557c9bc5e188', '361000', null, '������', '06', '������', '350206', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fd0ecf95f4d14f99a702c99eb258103d', 'c600d641b3d3489399d3557c9bc5e188', '361000', null, '������', '11', '������', '350211', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('11a93981e87e4435b7edebb2b3c560d9', 'c600d641b3d3489399d3557c9bc5e188', '361100', null, 'ͬ����', '12', 'ͬ����', '350212', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('89df18fe42c84676a709b5560cf448b7', 'c600d641b3d3489399d3557c9bc5e188', '361100', null, '�谲��', '13', '�谲��', '350213', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('52dcab395e094cd3b92eebba8c47b867', 'fd50361298cb470087d56f69e7734640', '351100', null, '��Ͻ��', '01', '��Ͻ��', '350301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('de7f6d3a4e164c2b9462cdb56fce70b7', '02f726a87a1e43ddb4fac108dd68b46b', '133400', null, '������', '05', '������', '222405', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5f749828e3ac4e3e9419235c75ebcbef', 'b34159526635417eac1df82dd9c7859b', '273100', null, '������', '81', '������', '370881', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9233f51b822b4101bee09103e8632b49', '77e868a833554971a102184336921723', '313000', null, '������', '02', '������', '330502', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('676cdbb1fbad4598a2d4958f5294a2e9', '77e868a833554971a102184336921723', '313000', null, '�����', '03', '�����', '330503', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e61d1d4e963945ee8379867e337a53ce', '77e868a833554971a102184336921723', '313200', null, '������', '21', '������', '330521', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c0305ad197b846f7a1d55c7fa7daffb8', '77e868a833554971a102184336921723', '313100', null, '������', '22', '������', '330522', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fecdfd4f36bd4321935ae821c2203a9e', '77e868a833554971a102184336921723', '313300', null, '������', '23', '������', '330523', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ed354b5991d04bd5813fb60e38b04590', '962cb7e4670547ffb73d8ee3b44532dd', '312000', null, '��Ͻ��', '01', '��Ͻ��', '330601', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('138a4619e4ec4293b98665c4411d9da2', '962cb7e4670547ffb73d8ee3b44532dd', '312000', null, 'Խ����', '02', 'Խ����', '330602', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a24dda2385b046b4aca2739997af8f78', '962cb7e4670547ffb73d8ee3b44532dd', '312000', null, '������', '21', '������', '330621', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('42cd4a6788ef4f40a7bd7c6152f0b79d', '962cb7e4670547ffb73d8ee3b44532dd', '312500', null, '�²���', '24', '�²���', '330624', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('832b8a02206240ebba4288c84c36c018', '962cb7e4670547ffb73d8ee3b44532dd', '311800', null, '������', '81', '������', '330681', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('458f1f899151416ca205c1c60b83b572', '962cb7e4670547ffb73d8ee3b44532dd', '312300', null, '������', '82', '������', '330682', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('667338a7ae4f494ba7f3910e6bcab7e5', '962cb7e4670547ffb73d8ee3b44532dd', '312400', null, '������', '83', '������', '330683', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a6598dd6d7b44b39ab0b620d4d36a4c8', '8df5d2d1bf024ffa92c29fe60871da10', '321000', null, '��Ͻ��', '01', '��Ͻ��', '330701', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dafbc314067a40a895f1a62734961fc4', '8df5d2d1bf024ffa92c29fe60871da10', '321000', null, '�ĳ���', '02', '�ĳ���', '330702', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('efbdb3eccb28465bac798ff3205b1089', '8df5d2d1bf024ffa92c29fe60871da10', '321000', null, '����', '03', '����', '330703', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('18c6551881e244fab90f9bfc7ed80813', '8df5d2d1bf024ffa92c29fe60871da10', '321200', null, '������', '23', '������', '330723', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3086b26b9c7242e481bf66eca46d8e63', '8df5d2d1bf024ffa92c29fe60871da10', '322200', null, '�ֽ���', '26', '�ֽ���', '330726', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1989dc4ef2c340f49ae85cda4c1d058c', '8df5d2d1bf024ffa92c29fe60871da10', '322300', null, '�Ͱ���', '27', '�Ͱ���', '330727', null);
commit;
prompt 1500 records committed...
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ee49f859be664dae9c6882c08f7ce3ba', '8df5d2d1bf024ffa92c29fe60871da10', '321100', null, '��Ϫ��', '81', '��Ϫ��', '330781', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b867d149d940477ebe6c96a3ef9f0bca', '8df5d2d1bf024ffa92c29fe60871da10', '322000', null, '������', '82', '������', '330782', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('be1bf4380e744150bca32e680a2a119c', '8df5d2d1bf024ffa92c29fe60871da10', '322100', null, '������', '83', '������', '330783', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9b09ca4b3d974faea3f35e37a6d10253', '8df5d2d1bf024ffa92c29fe60871da10', '321300', null, '������', '84', '������', '330784', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a5a1282e8378455b909640837c14fdb6', '39ef5e82145c4f11a07dc5a6503d596b', '324000', null, '��Ͻ��', '01', '��Ͻ��', '330801', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6472dce828bb467b9de56564722dede6', '39ef5e82145c4f11a07dc5a6503d596b', '324000', null, '�³���', '02', '�³���', '330802', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dd2da6f3e0ee436895a2cec8ac2f9c21', '39ef5e82145c4f11a07dc5a6503d596b', '324000', null, '�齭��', '03', '�齭��', '330803', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9d3db3c095ad41b9890f9aaaee3432ba', '39ef5e82145c4f11a07dc5a6503d596b', '324200', null, '��ɽ��', '22', '��ɽ��', '330822', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5cc10015767249658ea521933dba4f20', '39ef5e82145c4f11a07dc5a6503d596b', '324300', null, '������', '24', '������', '330824', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('caccb0628b45498f903e2c394894e154', '39ef5e82145c4f11a07dc5a6503d596b', '324400', null, '������', '25', '������', '330825', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b9a3f97c62c04d209aa8f0657caa6f0a', '39ef5e82145c4f11a07dc5a6503d596b', '324100', null, '��ɽ��', '81', '��ɽ��', '330881', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cef21f8444e946b7b1fd9fb39aa790d8', 'c56cf7ba5aec447eaa0588684c1347f8', '316000', null, '��Ͻ��', '01', '��Ͻ��', '330901', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c9a471f99c5949628083ac024c7af88d', 'c56cf7ba5aec447eaa0588684c1347f8', '316000', null, '������', '02', '������', '330902', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9395a0718ef440a98df085d1f092e066', 'c56cf7ba5aec447eaa0588684c1347f8', '316100', null, '������', '03', '������', '330903', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a7557e93274241a29c93bf09e5cbe18d', 'c56cf7ba5aec447eaa0588684c1347f8', '316200', null, '�ɽ��', '21', '�ɽ��', '330921', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('911fe4687c134071a43d13146c4a4031', 'c56cf7ba5aec447eaa0588684c1347f8', '202450', null, '������', '22', '������', '330922', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5b5f5a0c836848ab8ced09a23c2a1782', '1901b2c30bc24b45a4abca1c5b03b4ba', '317000', null, '��Ͻ��', '01', '��Ͻ��', '331001', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e512385d092e481a8237541f55f27194', '1901b2c30bc24b45a4abca1c5b03b4ba', '317700', null, '������', '02', '������', '331002', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f46032ae5f8c4302a7108116904ce5c9', '1901b2c30bc24b45a4abca1c5b03b4ba', '318020', null, '������', '03', '������', '331003', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('40dd8b06a16442b18f758f9920e4fc85', '1901b2c30bc24b45a4abca1c5b03b4ba', '318000', null, '·����', '04', '·����', '331004', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c86942ea1c4940d695f9744e80f0e36c', '1901b2c30bc24b45a4abca1c5b03b4ba', '317600', null, '����', '21', '����', '331021', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1ef9fe0417624d6daf0a8ed2296c5c8a', '1901b2c30bc24b45a4abca1c5b03b4ba', '317100', null, '������', '22', '������', '331022', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('788d7ded9b6c4794a8ac8249beabf216', '1901b2c30bc24b45a4abca1c5b03b4ba', '317200', null, '��̨��', '23', '��̨��', '331023', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a8988abf0e814635b9aceb5eb35bd8ba', '1901b2c30bc24b45a4abca1c5b03b4ba', '317300', null, '�ɾ���', '24', '�ɾ���', '331024', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2202d662f503492baf84d29ea35f6d14', '1901b2c30bc24b45a4abca1c5b03b4ba', '317500', null, '������', '81', '������', '331081', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fdb9659c65cd44a98a56cf262631bc32', '1901b2c30bc24b45a4abca1c5b03b4ba', '317000', null, '�ٺ���', '82', '�ٺ���', '331082', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1e17eb81c10449be8daf75ab3ea58d1c', 'e2d22a554b0141759f6ffc200a5a7c5a', '323000', null, '��Ͻ��', '01', '��Ͻ��', '331101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bc58e08b7564446795689b8f05f9e41f', 'e2d22a554b0141759f6ffc200a5a7c5a', '323000', null, '������', '02', '������', '331102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2c05a14546f9494ca7f392c5e0702f44', 'e2d22a554b0141759f6ffc200a5a7c5a', '323900', null, '������', '21', '������', '331121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cffd9bc839c44e2f93a7808bd0768347', 'e2d22a554b0141759f6ffc200a5a7c5a', '321400', null, '������', '22', '������', '331122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a754d0a4fb3a494787073dd399a425e8', 'e2d22a554b0141759f6ffc200a5a7c5a', '323300', null, '�����', '23', '�����', '331123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a393ca0e68cf473e9df6f6745072b12a', 'e2d22a554b0141759f6ffc200a5a7c5a', '323400', null, '������', '24', '������', '331124', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('feac5c6cdffa412ea411c80573a5c044', 'e2d22a554b0141759f6ffc200a5a7c5a', '323600', null, '�ƺ���', '25', '�ƺ���', '331125', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8d2b6057ad3e45c58ecae53193fb838a', 'e2d22a554b0141759f6ffc200a5a7c5a', '323800', null, '��Ԫ��', '26', '��Ԫ��', '331126', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('230751ce7a524c7dbc0b764f37cfecbf', 'e2d22a554b0141759f6ffc200a5a7c5a', '323500', null, '�������������', '27', '�������������', '331127', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2df3626d499c45b1b9ba9caaf7e98e87', 'e2d22a554b0141759f6ffc200a5a7c5a', '323700', null, '��Ȫ��', '81', '��Ȫ��', '331181', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ae3aea0845ba48bba488a8010cac15d6', 'db9ba52f83f943c3bb606a0d52ad769e', '230000', null, '��Ͻ��', '01', '��Ͻ��', '340101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0152e4ed63ff44fea062ae7f73d6f6ff', 'db9ba52f83f943c3bb606a0d52ad769e', '230000', null, '������', '02', '������', '340102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ec8ab547db284ed58ca4936d40cf0a7e', 'db9ba52f83f943c3bb606a0d52ad769e', '230000', null, '®����', '03', '®����', '340103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('19ce7ca9718a44009de33264e13b9df9', 'db9ba52f83f943c3bb606a0d52ad769e', '230000', null, '��ɽ��', '04', '��ɽ��', '340104', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d0c1c12b09a1467eb31e18b0c7d35773', 'db9ba52f83f943c3bb606a0d52ad769e', '230000', null, '������', '11', '������', '340111', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('009d87f35b154a50a32a970051fc9218', 'db9ba52f83f943c3bb606a0d52ad769e', '231100', null, '������', '21', '������', '340121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('acfd7410487d45d0a99893dac2a0556f', 'db9ba52f83f943c3bb606a0d52ad769e', '230000', null, '�ʶ���', '22', '�ʶ���', '340122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('adacf4d4a4f64649a9a5b70e8c267317', '02f726a87a1e43ddb4fac108dd68b46b', '133000', null, '�Ӽ���', '01', '�Ӽ���', '222401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4ecd9935ecb946f7b3f51c7d3d143b97', '02f726a87a1e43ddb4fac108dd68b46b', '133100', null, 'ͼ����', '02', 'ͼ����', '222402', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fd1a0de4d86941618e097de85f9c337b', '02f726a87a1e43ddb4fac108dd68b46b', '133700', null, '�ػ���', '03', '�ػ���', '222403', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c051af65f41a434d98872602c74a3cb3', '02f726a87a1e43ddb4fac108dd68b46b', '133300', null, '������', '04', '������', '222404', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('06aecaf2036340b1bdb06b012979e3e3', 'adf505a23af74179b00c02a084407ca8', '266000', null, '�����', '13', '�����', '370213', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('397746d3cb014b7593abf716439ea311', 'adf505a23af74179b00c02a084407ca8', '266000', null, '������', '14', '������', '370214', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d5f4939649464289968d8285f9751207', 'adf505a23af74179b00c02a084407ca8', '266300', null, '������', '81', '������', '370281', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('321cef4621b44638bceb0cb5dd0e2975', 'adf505a23af74179b00c02a084407ca8', '266200', null, '��ī��', '82', '��ī��', '370282', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('35b4bc87bc0a47af807d1365a7a1557c', 'adf505a23af74179b00c02a084407ca8', '266700', null, 'ƽ����', '83', 'ƽ����', '370283', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('81b8ec0374eb4365b8330dd9396d6f1e', 'adf505a23af74179b00c02a084407ca8', '266400', null, '������', '84', '������', '370284', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5443e5d664ab4b7ba91c484a8800e4a4', 'adf505a23af74179b00c02a084407ca8', '266600', null, '������', '85', '������', '370285', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b071c77de8c7487a90af9408fc624cb9', '85fb1b8433304911b85e1cd6cdb26d9e', '255100', null, '��Ͻ��', '01', '��Ͻ��', '370301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c489c555698e4009b146d247781e786a', '85fb1b8433304911b85e1cd6cdb26d9e', '255100', null, '�ʹ���', '02', '�ʹ���', '370302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7fa53bb580c0440b8d8435efd8999254', '85fb1b8433304911b85e1cd6cdb26d9e', '255000', null, '�ŵ���', '03', '�ŵ���', '370303', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('79d496dd158d4a3dab2e45e7a64d6cca', '85fb1b8433304911b85e1cd6cdb26d9e', '255200', null, '��ɽ��', '04', '��ɽ��', '370304', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0b479c2f63f044b2be4f9f40ca7d498b', '85fb1b8433304911b85e1cd6cdb26d9e', '255400', null, '������', '05', '������', '370305', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6d0dd7ff24344b20a2cb2f89c2ee66c7', '85fb1b8433304911b85e1cd6cdb26d9e', '255300', null, '�ܴ���', '06', '�ܴ���', '370306', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('afe53048148a48e09a8e1d7ab2fa4d5e', '85fb1b8433304911b85e1cd6cdb26d9e', '256400', null, '��̨��', '21', '��̨��', '370321', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('41e924aada3742048fa5a3cb1579c06b', '85fb1b8433304911b85e1cd6cdb26d9e', '256300', null, '������', '22', '������', '370322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('15c40b6d757f410e828d84c2ecab332a', '85fb1b8433304911b85e1cd6cdb26d9e', '256100', null, '��Դ��', '23', '��Դ��', '370323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fe9dd6cc4a1c458f8c65cef60feb8ced', '7ceca56d1b8146d48e88aabf26741991', '277000', null, '��Ͻ��', '01', '��Ͻ��', '370401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('83a4e9c45ce04ecdafe7e5a2a50df034', '7ceca56d1b8146d48e88aabf26741991', '277000', null, '������', '02', '������', '370402', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b891325559ab48adb162a27ba9d5d5d4', '7ceca56d1b8146d48e88aabf26741991', '277000', null, 'Ѧ����', '03', 'Ѧ����', '370403', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('401a9c285a6c4a2eb4552f4dc6fedd9c', '7ceca56d1b8146d48e88aabf26741991', '277300', null, 'ỳ���', '04', 'ỳ���', '370404', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5a84bd647abf42d6889d7f63468d0bbb', '7ceca56d1b8146d48e88aabf26741991', '277400', null, '̨��ׯ��', '05', '̨��ׯ��', '370405', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b941418fe8f0408681ebe89a79a2f546', '7ceca56d1b8146d48e88aabf26741991', '277200', null, 'ɽͤ��', '06', 'ɽͤ��', '370406', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d989259651874411a89487b58cafbe34', '7ceca56d1b8146d48e88aabf26741991', '277500', null, '������', '81', '������', '370481', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1bec1c19f305467ca9dd6b682adf121c', 'f2eb632af2014a9289fe6a736c9c12ac', '257000', null, '��Ͻ��', '01', '��Ͻ��', '370501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('998532892d4a41719af4a4e5c12f8782', 'f2eb632af2014a9289fe6a736c9c12ac', '257100', null, '��Ӫ��', '02', '��Ӫ��', '370502', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('de5da57b3325430089ad866550acfff2', 'f2eb632af2014a9289fe6a736c9c12ac', '257200', null, '�ӿ���', '03', '�ӿ���', '370503', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cb83347aa91249db904d3278758b204c', 'f2eb632af2014a9289fe6a736c9c12ac', '257500', null, '������', '21', '������', '370521', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e6d48c41dd0b44c49ae65413b5f78876', 'f2eb632af2014a9289fe6a736c9c12ac', '257400', null, '������', '22', '������', '370522', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f0b853f8bc9247a0b50c1b25d7f6dd2c', 'f2eb632af2014a9289fe6a736c9c12ac', '257300', null, '������', '23', '������', '370523', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c2e25c35b3a741da9cb19cebe0cdcada', '5dc548802c6c40a3803e58511ec474bf', '264000', null, '��Ͻ��', '01', '��Ͻ��', '370601', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c545dada32cc413fa3c4c775a7915a86', '5dc548802c6c40a3803e58511ec474bf', '264000', null, '֥���', '02', '֥���', '370602', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7573eefac70b493c9ad8069eb045b745', '5dc548802c6c40a3803e58511ec474bf', '265500', null, '��ɽ��', '11', '��ɽ��', '370611', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fceed8a1fab94a6cb396e425feb71dcd', '5dc548802c6c40a3803e58511ec474bf', '264100', null, 'Ĳƽ��', '12', 'Ĳƽ��', '370612', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4be369e2509b43e7b385ecc54617067a', '5dc548802c6c40a3803e58511ec474bf', '264000', null, '��ɽ��', '13', '��ɽ��', '370613', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8bc11e5a05fc4065b87799871f3d8a9d', '5dc548802c6c40a3803e58511ec474bf', '265800', null, '������', '34', '������', '370634', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f5829194565f4d87b26bc84a7ce41c01', '5dc548802c6c40a3803e58511ec474bf', '265700', null, '������', '81', '������', '370681', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('be8af47afbab4e59b6048bbd8ec21296', '5dc548802c6c40a3803e58511ec474bf', '265200', null, '������', '82', '������', '370682', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dadf9c70a9384835bfc50f4f0398885e', '5dc548802c6c40a3803e58511ec474bf', '261400', null, '������', '83', '������', '370683', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('01960042378b4114861e8f6a62dd1f31', '5dc548802c6c40a3803e58511ec474bf', '265600', null, '������', '84', '������', '370684', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5fca5aeb106a4be09088b83cc8f474b4', '5dc548802c6c40a3803e58511ec474bf', '265400', null, '��Զ��', '85', '��Զ��', '370685', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('55d7c1707ab747d184be2c5804f59514', '5dc548802c6c40a3803e58511ec474bf', '265300', null, '��ϼ��', '86', '��ϼ��', '370686', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c003732b042d4cdd821fac058b13dc36', '5dc548802c6c40a3803e58511ec474bf', '265100', null, '������', '87', '������', '370687', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2a420179bb91480b9ec2079dd433d3e0', '4ae4336f1dab45c0a465fae057c383fe', '261000', null, '��Ͻ��', '01', '��Ͻ��', '370701', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9342651407ce43b9a3759a445be03090', '4ae4336f1dab45c0a465fae057c383fe', '261000', null, 'Ϋ����', '02', 'Ϋ����', '370702', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ea73e028ab584961bd877c3e64b5ab14', '4ae4336f1dab45c0a465fae057c383fe', '261100', null, '��ͤ��', '03', '��ͤ��', '370703', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9e54d3b2945242539e580872918eb0ab', '4ae4336f1dab45c0a465fae057c383fe', '261200', null, '������', '04', '������', '370704', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6a64e9a7a35449e3adba942a55c0bc29', '4ae4336f1dab45c0a465fae057c383fe', '261000', null, '������', '05', '������', '370705', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('78f8d78d9eab41daaab96fd0298db3fc', '4ae4336f1dab45c0a465fae057c383fe', '262600', null, '������', '24', '������', '370724', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('211c3aa893c3421da85d0dc66c9dd212', '4ae4336f1dab45c0a465fae057c383fe', '262400', null, '������', '25', '������', '370725', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('83ccdaa80a4842dcadc4a8230fb88df3', '4ae4336f1dab45c0a465fae057c383fe', '262500', null, '������', '81', '������', '370781', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a6f0a0dd3b8f4cedb6a0ed1d473ffbbb', '4ae4336f1dab45c0a465fae057c383fe', '262200', null, '�����', '82', '�����', '370782', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7f2bf1802dfa45fbae3c59ba9da5c635', '4ae4336f1dab45c0a465fae057c383fe', '262700', null, '�ٹ���', '83', '�ٹ���', '370783', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d8222ed0a36c46258df39ba2f9033b40', '4ae4336f1dab45c0a465fae057c383fe', '262100', null, '������', '84', '������', '370784', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7e4b4d137e4c45069d8b024c26e1b05e', '4ae4336f1dab45c0a465fae057c383fe', '261500', null, '������', '85', '������', '370785', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2f55cc2d2c2549208a9a87bbbca6f323', '4ae4336f1dab45c0a465fae057c383fe', '261300', null, '������', '86', '������', '370786', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1cae85fb2cec45e288300c7874239ee8', 'b34159526635417eac1df82dd9c7859b', '272000', null, '��Ͻ��', '01', '��Ͻ��', '370801', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('43255b5fccdc472db2021b48a371229a', 'b34159526635417eac1df82dd9c7859b', '272000', null, '������', '02', '������', '370802', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('527a8a08e5c04ab88830663e56901918', 'b34159526635417eac1df82dd9c7859b', '272000', null, '�γ���', '11', '�γ���', '370811', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4afaa9556fa6419d9f0a4b0eca8020b0', 'b34159526635417eac1df82dd9c7859b', '277600', null, '΢ɽ��', '26', '΢ɽ��', '370826', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d28cf284300c4d0bb64658cd5f747896', '133d81bc3b4c48e7803bde263c887d8f', '117000', null, 'ƽɽ��', '02', 'ƽɽ��', '210502', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f2c3af40cc28484daf1fc380efac48ae', '02fd4185ea2343ffaae15ca2c5caf2b3', '221000', null, 'Ȫɽ��', '11', 'Ȫɽ��', '320311', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fb42d926b0cd49539365212bf05b3974', '02fd4185ea2343ffaae15ca2c5caf2b3', '221700', null, '����', '21', '����', '320321', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ac832d134ee84973a40c5be4822905f0', '02fd4185ea2343ffaae15ca2c5caf2b3', '221600', null, '����', '22', '����', '320322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d875181a563b4668a5e058184be6381b', '02fd4185ea2343ffaae15ca2c5caf2b3', '221100', null, 'ͭɽ��', '23', 'ͭɽ��', '320323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1f1a4d18eac54a94b8cfb8e33397fdcd', '02fd4185ea2343ffaae15ca2c5caf2b3', '221200', null, '�����', '24', '�����', '320324', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9bd5a2ef96b347ce9fbd0342909b95e2', '02fd4185ea2343ffaae15ca2c5caf2b3', '221400', null, '������', '81', '������', '320381', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a1be1687afcf42b1be5234187e97ddb5', '02f726a87a1e43ddb4fac108dd68b46b', '133500', null, '������', '06', '������', '222406', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1fff79f0dc70409eae0f3d5b008bdc33', '02f726a87a1e43ddb4fac108dd68b46b', '133200', null, '������', '24', '������', '222424', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e1460e9d1f324d7d957b7e4e0befd6b7', '02f726a87a1e43ddb4fac108dd68b46b', '133600', null, '��ͼ��', '26', '��ͼ��', '222426', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('01e4fdb14be24c81b626677f58905fb1', '18f439ed744a48ae89ca7f469af554fd', '150000', null, '������', '02', '������', '230102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fb2c31bbbdc7429bb23ef70d3b81702b', '18f439ed744a48ae89ca7f469af554fd', '150000', null, '�ϸ���', '03', '�ϸ���', '230103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('20d77cd85a6b4b52a6c8b8a0aeb7dc85', '18f439ed744a48ae89ca7f469af554fd', '150000', null, '������', '04', '������', '230104', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a3e19b8c6d6e4fbbba2b60c9a0fbec5a', '18f439ed744a48ae89ca7f469af554fd', '150000', null, '�㷻��', '06', '�㷻��', '230106', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3132717b7ce94343a29ceadb1ec0f087', '18f439ed744a48ae89ca7f469af554fd', '150000', null, '������', '07', '������', '230107', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('87f5fac814194148aeef13d97dded20b', '18f439ed744a48ae89ca7f469af554fd', '150000', null, 'ƽ����', '08', 'ƽ����', '230108', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('756abb854b614870b57c488f682ebffb', '18f439ed744a48ae89ca7f469af554fd', '150000', null, '�ɱ���', '09', '�ɱ���', '230109', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f3216df4d66d452ca0364d1e79b0751a', '18f439ed744a48ae89ca7f469af554fd', '150500', null, '������', '11', '������', '230111', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6fbd878b2ddc4cb8980e24b7bae89c92', '18f439ed744a48ae89ca7f469af554fd', '154800', null, '������', '23', '������', '230123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('007c9c1d79414946b1747cc982390f5a', '18f439ed744a48ae89ca7f469af554fd', '150800', null, '������', '24', '������', '230124', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('57310cf7c8ca4747bbecb787cb4e2f94', '18f439ed744a48ae89ca7f469af554fd', '150400', null, '����', '25', '����', '230125', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e3845db0918c4cc593f6d294daa34d17', '18f439ed744a48ae89ca7f469af554fd', '151800', null, '������', '26', '������', '230126', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a9d6d17c1c304a0ba2c18a3d604aad57', '18f439ed744a48ae89ca7f469af554fd', '151900', null, 'ľ����', '27', 'ľ����', '230127', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('84f6e8767dcf4643b32ff80e9f0f9fef', '18f439ed744a48ae89ca7f469af554fd', '150900', null, 'ͨ����', '28', 'ͨ����', '230128', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('728081d029024f9088b780b9d5aacf8e', '18f439ed744a48ae89ca7f469af554fd', '150700', null, '������', '29', '������', '230129', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f4746a091b824a7bbcd8293252ca9ef6', '18f439ed744a48ae89ca7f469af554fd', '150300', null, '������', '81', '������', '230181', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('23e4078f6c5640eca3aa1ce3cf692f3d', '18f439ed744a48ae89ca7f469af554fd', '150100', null, '˫����', '82', '˫����', '230182', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bdbd90cf0c2e4616ac163474a097f932', '18f439ed744a48ae89ca7f469af554fd', '150600', null, '��־��', '83', '��־��', '230183', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d354efc5d2934807a4a38a01fb0bc340', '18f439ed744a48ae89ca7f469af554fd', '150200', null, '�峣��', '84', '�峣��', '230184', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1613e0892bc34d65adc5c1f43afc0c0e', '4f42860a0c8e4a6ab50d18f9e73d84a4', '161000', null, '��Ͻ��', '01', '��Ͻ��', '230201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3af68c92402f46798259c39cc700d3fd', '4f42860a0c8e4a6ab50d18f9e73d84a4', '161000', null, '��ɳ��', '02', '��ɳ��', '230202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d44a005e93ef4085aac2d25e6208b953', '4f42860a0c8e4a6ab50d18f9e73d84a4', '161000', null, '������', '03', '������', '230203', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8df48513eb03423e99953ce41443d942', '4f42860a0c8e4a6ab50d18f9e73d84a4', '161000', null, '������', '04', '������', '230204', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('375268fc29604036a7b4a9d0f31bc8a1', '4f42860a0c8e4a6ab50d18f9e73d84a4', '161000', null, '����Ϫ��', '05', '����Ϫ��', '230205', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2a34ac8b34174f5d8fb4d0c043d1d564', '4f42860a0c8e4a6ab50d18f9e73d84a4', '161000', null, '����������', '06', '����������', '230206', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d85df224a8f149588b90e731001aa0df', '4f42860a0c8e4a6ab50d18f9e73d84a4', '161000', null, '����ɽ��', '07', '����ɽ��', '230207', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f9488ca41f4d40dd944bf3e751f71486', '4f42860a0c8e4a6ab50d18f9e73d84a4', '161000', null, '÷��˹���Ӷ�����', '08', '÷��˹���Ӷ�����', '230208', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ff66f4ea7e4f41f28fd954001bc8b0dc', '4f42860a0c8e4a6ab50d18f9e73d84a4', '161100', null, '������', '21', '������', '230221', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1180462b832b4f9caa476159e5f54981', '4f42860a0c8e4a6ab50d18f9e73d84a4', '161500', null, '������', '23', '������', '230223', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('13b8878a46d54cb7ad7b15d5d6673e1e', '4f42860a0c8e4a6ab50d18f9e73d84a4', '162400', null, '̩����', '24', '̩����', '230224', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7d8d489e03c547feafcada3b3a58e677', '4f42860a0c8e4a6ab50d18f9e73d84a4', '162100', null, '������', '25', '������', '230225', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('03c8bc212a4449f6b973f8c8306d4cf3', '4f42860a0c8e4a6ab50d18f9e73d84a4', '161200', null, '��ԣ��', '27', '��ԣ��', '230227', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9fb06ebda0064e88823017d3a43eefab', '4f42860a0c8e4a6ab50d18f9e73d84a4', '161600', null, '��ɽ��', '29', '��ɽ��', '230229', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('855a6c0994674931935a32ae2e06eb68', '4f42860a0c8e4a6ab50d18f9e73d84a4', '164800', null, '�˶���', '30', '�˶���', '230230', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d9ec848e1bec4e5caa607276ddbbb764', '4f42860a0c8e4a6ab50d18f9e73d84a4', '164700', null, '��Ȫ��', '31', '��Ȫ��', '230231', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0837662e4c164fe69794421863206334', '4f42860a0c8e4a6ab50d18f9e73d84a4', '161300', null, 'ګ����', '81', 'ګ����', '230281', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6b5eb4d1c3c54532864645490392b78e', 'c87e34444f484566923db62964bf3fc0', '158100', null, '��Ͻ��', '01', '��Ͻ��', '230301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0a2068f8daae4220b628876abc41e4a6', 'c87e34444f484566923db62964bf3fc0', '158100', null, '������', '02', '������', '230302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5dd3217b78a94b87b03f18dd1098f9a2', 'c87e34444f484566923db62964bf3fc0', '158100', null, '��ɽ��', '03', '��ɽ��', '230303', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3df01abb0b6a407e9ebe9e81a8ba6b1b', 'c87e34444f484566923db62964bf3fc0', '158100', null, '�ε���', '04', '�ε���', '230304', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('38d1465696824954bdb893c70567ce12', 'c87e34444f484566923db62964bf3fc0', '158100', null, '������', '05', '������', '230305', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4636164109484c498340c98ec1572034', 'c87e34444f484566923db62964bf3fc0', '158100', null, '���Ӻ���', '06', '���Ӻ���', '230306', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c688882a56e44b02b4bf437d03a69599', 'c87e34444f484566923db62964bf3fc0', '158100', null, '��ɽ��', '07', '��ɽ��', '230307', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('44386cd1bac8484e9920bd3edead06b6', 'c87e34444f484566923db62964bf3fc0', '158200', null, '������', '21', '������', '230321', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d190bf371a6647dd9cbefc84ef21d0fc', 'c87e34444f484566923db62964bf3fc0', '158400', null, '������', '81', '������', '230381', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f880d5bd2e7a4445830d9eed3452b26c', 'c87e34444f484566923db62964bf3fc0', '158300', null, '��ɽ��', '82', '��ɽ��', '230382', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e391a0a899f64e16825930f6308af3ca', '23fc3577d5b5453eae1ae4af187d0c86', '154000', null, '��Ͻ��', '01', '��Ͻ��', '230401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('52161cdeb052425aac929c94b9710740', '23fc3577d5b5453eae1ae4af187d0c86', '154000', null, '������', '02', '������', '230402', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('48104261876443769fef0a1639c25897', '23fc3577d5b5453eae1ae4af187d0c86', '154100', null, '��ũ��', '03', '��ũ��', '230403', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2f4b3f8c8ef546299fca95d9a5d44115', '23fc3577d5b5453eae1ae4af187d0c86', '154100', null, '��ɽ��', '04', '��ɽ��', '230404', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ff09cdc66a7645a58c1a6ca8393e4b98', '23fc3577d5b5453eae1ae4af187d0c86', '154100', null, '�˰���', '05', '�˰���', '230405', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('19c77b1e011c496cb13c48c9876817e0', '23fc3577d5b5453eae1ae4af187d0c86', '154100', null, '��ɽ��', '06', '��ɽ��', '230406', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8b824ade92f44aaa88aa2100d1f83763', '23fc3577d5b5453eae1ae4af187d0c86', '154100', null, '��ɽ��', '07', '��ɽ��', '230407', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a1b6af7605014abba5ec42bd06d00d7a', '23fc3577d5b5453eae1ae4af187d0c86', '154200', null, '�ܱ���', '21', '�ܱ���', '230421', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('26009d89413b4dcf9cbdf11c5227a231', '23fc3577d5b5453eae1ae4af187d0c86', '156200', null, '�����', '22', '�����', '230422', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a35100d871524a909e299e136bad223f', '8fdba2c226b74f689e9095d060a98cd2', '155100', null, '��Ͻ��', '01', '��Ͻ��', '230501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('49df869b54314a7d84a5fbf25cf71ba3', '8fdba2c226b74f689e9095d060a98cd2', '155100', null, '��ɽ��', '02', '��ɽ��', '230502', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d7326db7f84c4cb7892093cf4b8a9858', '8fdba2c226b74f689e9095d060a98cd2', '155100', null, '�붫��', '03', '�붫��', '230503', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bada5acfd8c74884b95143ccfa23f843', '8fdba2c226b74f689e9095d060a98cd2', '155100', null, '�ķ�̨��', '05', '�ķ�̨��', '230505', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('34e74fadcac3458e9bda4c5cad7c4062', '8fdba2c226b74f689e9095d060a98cd2', '155100', null, '��ɽ��', '06', '��ɽ��', '230506', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('eeb833be8b124b63b35f2c1464f64150', '8fdba2c226b74f689e9095d060a98cd2', '155900', null, '������', '21', '������', '230521', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bcc5cddb7aa1443a8cddaa8da26b8f23', '6fd17ced4f124cc4881a50ad26321855', '355400', null, '������', '25', '������', '350925', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4c5c6f28a7eb4d268b0c5cebf3c79740', '04639f4c045a44258ebff7fc94fec3e5', '343100', null, '������', '21', '������', '360821', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5cace8bf1dd74c7d8b1918891d225ae1', '04639f4c045a44258ebff7fc94fec3e5', '331600', null, '��ˮ��', '22', '��ˮ��', '360822', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c9b9733c0f0e4c61adb3f7c9f95768cc', '04639f4c045a44258ebff7fc94fec3e5', '331400', null, 'Ͽ����', '23', 'Ͽ����', '360823', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1b3f94cc60564df69fa976264addcc8a', '04639f4c045a44258ebff7fc94fec3e5', '331300', null, '�¸���', '24', '�¸���', '360824', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d0316083227440e7a2a2c259c4bf5615', '04639f4c045a44258ebff7fc94fec3e5', '331500', null, '������', '25', '������', '360825', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('79271d4a150e46a8bdea568cf2fad9de', '04639f4c045a44258ebff7fc94fec3e5', '343700', null, '̩����', '26', '̩����', '360826', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6691f042545740549255104add2c8aec', '04639f4c045a44258ebff7fc94fec3e5', '343900', null, '�촨��', '27', '�촨��', '360827', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7b7c168f32fc422aabbbd43dbcedc842', '04639f4c045a44258ebff7fc94fec3e5', '343800', null, '����', '28', '����', '360828', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dd3b4bab17f74702be818c71d90bb416', '04639f4c045a44258ebff7fc94fec3e5', '343200', null, '������', '29', '������', '360829', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2fa3406cfebd4644b813ceb340c2daac', '04639f4c045a44258ebff7fc94fec3e5', '343400', null, '������', '30', '������', '360830', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dffd772e409943c4853b0b89ab054fc6', '04639f4c045a44258ebff7fc94fec3e5', '343600', null, '����ɽ��', '81', '����ɽ��', '360881', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('71795fe5302d4316b7c35b106f9117c5', 'ba92d925173c48ebac5841355f105db5', '336000', null, '��Ͻ��', '01', '��Ͻ��', '360901', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4b80293ecf6641dea835f120b73df705', 'ba92d925173c48ebac5841355f105db5', '336000', null, 'Ԭ����', '02', 'Ԭ����', '360902', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e2a97fea457641098b30878648bb12c7', 'ba92d925173c48ebac5841355f105db5', '330700', null, '������', '21', '������', '360921', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0e874ade65f447e3a1962b87d4fc4628', 'ba92d925173c48ebac5841355f105db5', '336100', null, '������', '22', '������', '360922', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d66f74c31eb44f1e8d741a757500cea8', 'ba92d925173c48ebac5841355f105db5', '336400', null, '�ϸ���', '23', '�ϸ���', '360923', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('068d06fd83384560a428bed55bff7e42', 'ba92d925173c48ebac5841355f105db5', '336300', null, '�˷���', '24', '�˷���', '360924', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('93c9fa4137654aa08f5a29a2825ef96e', 'ba92d925173c48ebac5841355f105db5', '330600', null, '������', '25', '������', '360925', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c475852930b642ea9f184d3d1f6facc8', 'ba92d925173c48ebac5841355f105db5', '336200', null, 'ͭ����', '26', 'ͭ����', '360926', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a1ed745a5453470eb95081d404b99865', 'ba92d925173c48ebac5841355f105db5', '331100', null, '�����', '81', '�����', '360981', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d9c5619aaee2473ea9da343b8b511a87', 'ba92d925173c48ebac5841355f105db5', '331200', null, '������', '82', '������', '360982', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9ed2a213abfe4d42aa28d594ee2258e2', 'ba92d925173c48ebac5841355f105db5', '330800', null, '�߰���', '83', '�߰���', '360983', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('435af59152de4fc2996cf16026885e0b', 'bfcd8a0a0e484b12ac10a25b91a97e68', '344000', null, '��Ͻ��', '01', '��Ͻ��', '361001', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b2b20d0a649f424ab02f3a99400556f7', 'bfcd8a0a0e484b12ac10a25b91a97e68', '344100', null, '�ٴ���', '02', '�ٴ���', '361002', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d52c2bdbe79f4fa289658f7252677d46', 'bfcd8a0a0e484b12ac10a25b91a97e68', '344700', null, '�ϳ���', '21', '�ϳ���', '361021', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fccf274e36d84b0eae384609b013a93b', 'bfcd8a0a0e484b12ac10a25b91a97e68', '344600', null, '�质��', '22', '�质��', '361022', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0ba9a6859821475dbf43157ae86f3fc3', 'bfcd8a0a0e484b12ac10a25b91a97e68', '344500', null, '�Ϸ���', '23', '�Ϸ���', '361023', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e035299d42de478e8a9a3b83e08b8e47', 'bfcd8a0a0e484b12ac10a25b91a97e68', '344200', null, '������', '24', '������', '361024', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('57f2f45307564841a3f515e183ed9539', 'bfcd8a0a0e484b12ac10a25b91a97e68', '344300', null, '�ְ���', '25', '�ְ���', '361025', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('240e6c22e5234c74aaceb56c8585da6c', 'bfcd8a0a0e484b12ac10a25b91a97e68', '344400', null, '�˻���', '26', '�˻���', '361026', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d2003c5a93764f6a9da3bb0eec2f89e9', 'bfcd8a0a0e484b12ac10a25b91a97e68', '344800', null, '��Ϫ��', '27', '��Ϫ��', '361027', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ad1a66a182f2486281fb6622a1dba322', 'bfcd8a0a0e484b12ac10a25b91a97e68', '335300', null, '��Ϫ��', '28', '��Ϫ��', '361028', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('835ee9d632314f098fcebc569d409055', 'bfcd8a0a0e484b12ac10a25b91a97e68', '331800', null, '������', '29', '������', '361029', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2c393c587eb84c10b6219a199bdfaf94', 'bfcd8a0a0e484b12ac10a25b91a97e68', '344900', null, '�����', '30', '�����', '361030', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a92bd246996043569231306d5011ccd6', 'ae3e3d63e8b541b3a63dd96ec498e5d0', '334000', null, '��Ͻ��', '01', '��Ͻ��', '361101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2d93e2f499584d4da845e1feded5a0cf', 'ae3e3d63e8b541b3a63dd96ec498e5d0', '334000', null, '������', '02', '������', '361102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cc65f4939fbe4346a370b15f025310e9', 'ae3e3d63e8b541b3a63dd96ec498e5d0', '334100', null, '������', '21', '������', '361121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2487788534894ea6a1ec96db2aa0a3ee', 'ae3e3d63e8b541b3a63dd96ec498e5d0', '334600', null, '�����', '22', '�����', '361122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ee50fc2bc6744abbb43d296a0c77f23c', 'ae3e3d63e8b541b3a63dd96ec498e5d0', '334700', null, '��ɽ��', '23', '��ɽ��', '361123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aa180784ffbf4d7f9b23e03f37ed5173', 'ae3e3d63e8b541b3a63dd96ec498e5d0', '334500', null, 'Ǧɽ��', '24', 'Ǧɽ��', '361124', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6acae4e912ca4a56b34ec318d996bb10', 'ae3e3d63e8b541b3a63dd96ec498e5d0', '334300', null, '�����', '25', '�����', '361125', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('173609568caa4410a68de6a5211360f6', 'ae3e3d63e8b541b3a63dd96ec498e5d0', '334400', null, '߮����', '26', '߮����', '361126', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e9de32842e7642ad9d4a68bdbdf9fcef', 'ae3e3d63e8b541b3a63dd96ec498e5d0', '335100', null, '�����', '27', '�����', '361127', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ce54906e0d334a81820ee0aa29f0a390', 'ae3e3d63e8b541b3a63dd96ec498e5d0', '333100', null, '۶����', '28', '۶����', '361128', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4026a6c82ba84b7099cdfa7e51e45cbe', 'ae3e3d63e8b541b3a63dd96ec498e5d0', '335500', null, '������', '29', '������', '361129', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bb1b78bfc2b84ea6a8c4a48f46558a40', 'ae3e3d63e8b541b3a63dd96ec498e5d0', '333200', null, '��Դ��', '30', '��Դ��', '361130', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('18e873b471594d44a47dbf7d24b596cf', 'ae3e3d63e8b541b3a63dd96ec498e5d0', '334200', null, '������', '81', '������', '361181', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2eb6fc53baec41238e2d60dd9c990443', 'f1f171efba3d4ecb9a3489ae544cdd47', '250000', null, '��Ͻ��', '01', '��Ͻ��', '370101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1007a3cd7ed14088b78eed9ed6991d67', 'f1f171efba3d4ecb9a3489ae544cdd47', '250000', null, '������', '02', '������', '370102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('25badd8088794d358b2c7c02ee5aa7a1', 'f1f171efba3d4ecb9a3489ae544cdd47', '250000', null, '������', '03', '������', '370103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('24ec59902e644f42a8d66ced0779a533', 'f1f171efba3d4ecb9a3489ae544cdd47', '250000', null, '������', '04', '������', '370104', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('20c78ee57293430f8e23a60b92f3f921', 'f1f171efba3d4ecb9a3489ae544cdd47', '250000', null, '������', '05', '������', '370105', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('082a8d29e8c84ffd9375c5a3cb2ecd1a', 'f1f171efba3d4ecb9a3489ae544cdd47', '250100', null, '������', '12', '������', '370112', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('de76c5f078fa4fdca4761290c50f689e', 'f1f171efba3d4ecb9a3489ae544cdd47', '250300', null, '������', '13', '������', '370113', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3e822ea6ed54462dbca3274b4c11b407', 'f1f171efba3d4ecb9a3489ae544cdd47', '250400', null, 'ƽ����', '24', 'ƽ����', '370124', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bd98544190f3420db4da0c5ebdfd2f33', 'f1f171efba3d4ecb9a3489ae544cdd47', '251400', null, '������', '25', '������', '370125', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f32ab8d458d0461c83b9f67128995046', 'f1f171efba3d4ecb9a3489ae544cdd47', '251600', null, '�̺���', '26', '�̺���', '370126', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6029394416fd444686e1c2b278e24883', 'f1f171efba3d4ecb9a3489ae544cdd47', '250200', null, '������', '81', '������', '370181', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f02f8635f28b4493b93e6b32dc45d8ac', '133d81bc3b4c48e7803bde263c887d8f', '117000', null, 'Ϫ����', '03', 'Ϫ����', '210503', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('417ea7d4ba7f4f1bb047a606de81bfd5', '133d81bc3b4c48e7803bde263c887d8f', '117000', null, '��ɽ��', '04', '��ɽ��', '210504', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('71e1be4ea550415a8ce6eb69f4f3472c', '133d81bc3b4c48e7803bde263c887d8f', '117000', null, '�Ϸ���', '05', '�Ϸ���', '210505', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5f8f6099d1f6458bab141d53b6c68ef6', '133d81bc3b4c48e7803bde263c887d8f', '117100', null, '��Ϫ����������', '21', '��Ϫ����������', '210521', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e0cad8bde1eb4796addd0b17b0045c15', '133d81bc3b4c48e7803bde263c887d8f', '117200', null, '��������������', '22', '��������������', '210522', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e394ea8db31f4f8e89c18e89cf789874', '21f243e300a74fb4abb0138b942daf00', '118000', null, '��Ͻ��', '01', '��Ͻ��', '210601', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3765470d0ae243268f0f464b857b85ba', '21f243e300a74fb4abb0138b942daf00', '118000', null, 'Ԫ����', '02', 'Ԫ����', '210602', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('44b89db44e254231b5f7764a26519978', '21f243e300a74fb4abb0138b942daf00', '118000', null, '������', '03', '������', '210603', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('57fd69813a554ae190f161ebb4f7cc0f', '6fd17ced4f124cc4881a50ad26321855', '355300', null, '������', '26', '������', '350926', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('94bdd1e7bc6645bebe643ed5259d7e31', '6fd17ced4f124cc4881a50ad26321855', '355000', null, '������', '81', '������', '350981', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b3d2fd156ec044c1922cf910eeac018b', '6fd17ced4f124cc4881a50ad26321855', '355200', null, '������', '82', '������', '350982', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ddef5cfe355949df859e1544fd9b02bb', '4f7002a7097045428cafa1878f18556a', '330000', null, '��Ͻ��', '01', '��Ͻ��', '360101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b1aedf4674b948dda7cec2bdd730b536', '4f7002a7097045428cafa1878f18556a', '330000', null, '������', '02', '������', '360102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8804728c72944ec3bc9809fd197541dc', '4f7002a7097045428cafa1878f18556a', '330000', null, '������', '03', '������', '360103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e11a5c76d7d146eaa97760ab02ea7c77', '4f7002a7097045428cafa1878f18556a', '330000', null, '��������', '04', '��������', '360104', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('46c7012a5bee4fcc9fa8ee271f4adc2f', '4f7002a7097045428cafa1878f18556a', '330000', null, '������', '05', '������', '360105', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b04fd3904d98448ba79f24e0e9d54bdb', '4f7002a7097045428cafa1878f18556a', '330000', null, '��ɽ����', '11', '��ɽ����', '360111', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('35d094bda33b4c6cac5e5ca4684ac76a', '4f7002a7097045428cafa1878f18556a', '330200', null, '�ϲ���', '21', '�ϲ���', '360121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fb9c6c844d1f4fd8a2189f74f5db5422', '4f7002a7097045428cafa1878f18556a', '330100', null, '�½���', '22', '�½���', '360122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d2118420d9bd4339a29ec6770ee9b73d', '4f7002a7097045428cafa1878f18556a', '330500', null, '������', '23', '������', '360123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1edbde17777c4458a4e2ddb25d01dd42', '4f7002a7097045428cafa1878f18556a', '331700', null, '������', '24', '������', '360124', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ef753eebd9c54aa0816c9cbcc62064e1', '647fb0abceaf4c5d9ea5b24cdf2de227', '333000', null, '��Ͻ��', '01', '��Ͻ��', '360201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8b9877a5f81242e9bda1ca8488dcd1a3', '647fb0abceaf4c5d9ea5b24cdf2de227', '333000', null, '������', '02', '������', '360202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6070d27ecc814aeea453a003fdeff4ff', '647fb0abceaf4c5d9ea5b24cdf2de227', '333000', null, '��ɽ��', '03', '��ɽ��', '360203', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ff149057bcba4821821cbeb278e57251', '647fb0abceaf4c5d9ea5b24cdf2de227', '333400', null, '������', '22', '������', '360222', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bec0b6e547c74a74a6c71b1d6feb9b2e', '647fb0abceaf4c5d9ea5b24cdf2de227', '333300', null, '��ƽ��', '81', '��ƽ��', '360281', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f3bd48aa05f54760b001a63ee7fd1035', '63879d4cb0b9445c86e98966d15f22d6', '337000', null, '��Ͻ��', '01', '��Ͻ��', '360301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('484ae2d52e17496c946978449925baf5', '63879d4cb0b9445c86e98966d15f22d6', '337000', null, '��Դ��', '02', '��Դ��', '360302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bb62f0bfe796443b89944beb0ad7a8be', '63879d4cb0b9445c86e98966d15f22d6', '337000', null, '�涫��', '13', '�涫��', '360313', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c52a2e661f01433cb3b04a1e03cf00b2', '63879d4cb0b9445c86e98966d15f22d6', '337100', null, '������', '21', '������', '360321', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('977e0d2540254c1bad0f4aba98168fb6', '63879d4cb0b9445c86e98966d15f22d6', '337000', null, '������', '22', '������', '360322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8c0515ee2f044a3ea4b398d3cf06c5f6', '63879d4cb0b9445c86e98966d15f22d6', '337000', null, '«Ϫ��', '23', '«Ϫ��', '360323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6c663f82ab9e436dbc87722eb4798b37', '160289800e4949a18ce764e082fa4bd0', '332000', null, '��Ͻ��', '01', '��Ͻ��', '360401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('03c084eecfdd429ea41301c2f8361dd1', '160289800e4949a18ce764e082fa4bd0', '332900', null, '®ɽ��', '02', '®ɽ��', '360402', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e7ae478236444b60858c10f5cfd8da49', '160289800e4949a18ce764e082fa4bd0', '332000', null, '�����', '03', '�����', '360403', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('79a8d79d86414c6f9e7296a2f6652509', '160289800e4949a18ce764e082fa4bd0', '332100', null, '�Ž���', '21', '�Ž���', '360421', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('070b4f005e084afb9573831e2c7099ae', '160289800e4949a18ce764e082fa4bd0', '332300', null, '������', '23', '������', '360423', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('08cc1765c07144aeb764eb524308a7ba', '160289800e4949a18ce764e082fa4bd0', '332400', null, '��ˮ��', '24', '��ˮ��', '360424', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a76149756b5b4824ac7ee13aa659cc55', '160289800e4949a18ce764e082fa4bd0', '330300', null, '������', '25', '������', '360425', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5209305e24894ea18e152ea47174d267', '160289800e4949a18ce764e082fa4bd0', '330400', null, '�°���', '26', '�°���', '360426', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cfe93c259bae4836b6548ec86843bdd3', '160289800e4949a18ce764e082fa4bd0', '332800', null, '������', '27', '������', '360427', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f8dd88a464ef4d319914125463f0899e', '160289800e4949a18ce764e082fa4bd0', '332600', null, '������', '28', '������', '360428', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f07d7d08350e4b309163dd742f081307', '160289800e4949a18ce764e082fa4bd0', '332500', null, '������', '29', '������', '360429', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f085b991746e4845a6b7f94c9b9d5cd5', '160289800e4949a18ce764e082fa4bd0', '332700', null, '������', '30', '������', '360430', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('620924e675e34f1093efd76a7bd55fd3', '160289800e4949a18ce764e082fa4bd0', '332200', null, '�����', '81', '�����', '360481', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8a3d7466420b450d8ce40fc7ec5177fe', 'e776f1045e3441a3bf1a91a222e248eb', '336500', null, '��Ͻ��', '01', '��Ͻ��', '360501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5f2f094a1c184ea0892f1ca789345364', 'e776f1045e3441a3bf1a91a222e248eb', '336500', null, '��ˮ��', '02', '��ˮ��', '360502', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('af49bfd91bf4449e926996f2c9ed7b85', 'e776f1045e3441a3bf1a91a222e248eb', '336600', null, '������', '21', '������', '360521', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cace220f015c4987815a6e1fe5e1a1f2', 'f48fd507cbb24c9d85e8c2a2181c9115', '335000', null, '��Ͻ��', '01', '��Ͻ��', '360601', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e2357ff187d14650bc76fa2f7e0f8817', 'f48fd507cbb24c9d85e8c2a2181c9115', '335000', null, '�º���', '02', '�º���', '360602', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('78e1cdf05dbe4908aa1e38770cfd73a2', 'f48fd507cbb24c9d85e8c2a2181c9115', '335200', null, '�཭��', '22', '�཭��', '360622', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f649c1e29dab4ad495ceba49e5129a0b', 'f48fd507cbb24c9d85e8c2a2181c9115', '335400', null, '��Ϫ��', '81', '��Ϫ��', '360681', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c994000ff132474d8d06b27003519d42', '0a3c848e26ed49d396eb5b00ecce7de0', '341000', null, '��Ͻ��', '01', '��Ͻ��', '360701', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bfb7f97083a74af08263a64f358bb841', '0a3c848e26ed49d396eb5b00ecce7de0', '341000', null, '�¹���', '02', '�¹���', '360702', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e64b02c12146490ea6a5b5ac5f9c7dae', '0a3c848e26ed49d396eb5b00ecce7de0', '341100', null, '����', '21', '����', '360721', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('077acda2097f47ca999ab16e93da6671', '0a3c848e26ed49d396eb5b00ecce7de0', '341600', null, '�ŷ���', '22', '�ŷ���', '360722', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6a41aa3da3804c8f8a768e9244c1f903', '0a3c848e26ed49d396eb5b00ecce7de0', '341500', null, '������', '23', '������', '360723', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ab5f75a28947470ca6500beae06559a4', '0a3c848e26ed49d396eb5b00ecce7de0', '341200', null, '������', '24', '������', '360724', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fa367fa728e74beba58062eff1c73cce', '0a3c848e26ed49d396eb5b00ecce7de0', '341300', null, '������', '25', '������', '360725', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f02b9d89c2504d8ebc4633f1fe5daf30', '0a3c848e26ed49d396eb5b00ecce7de0', '342100', null, '��Զ��', '26', '��Զ��', '360726', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('905f3d9ec51246dc9cc8d5b618f06d0b', '0a3c848e26ed49d396eb5b00ecce7de0', '341700', null, '������', '27', '������', '360727', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ecad4a47227340b89c8d03a9df604103', '0a3c848e26ed49d396eb5b00ecce7de0', '341900', null, '������', '28', '������', '360728', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6fb02bc640a046dbad1be91e2f95dfd9', '0a3c848e26ed49d396eb5b00ecce7de0', '341800', null, 'ȫ����', '29', 'ȫ����', '360729', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('63c64b11f66543479f67736cc7f73515', '0a3c848e26ed49d396eb5b00ecce7de0', '342800', null, '������', '30', '������', '360730', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('03b56018ce35467292294b6ea3444192', '0a3c848e26ed49d396eb5b00ecce7de0', '342300', null, '�ڶ���', '31', '�ڶ���', '360731', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0ba16338a51f4498a221523d0b2041ea', '0a3c848e26ed49d396eb5b00ecce7de0', '342400', null, '�˹���', '32', '�˹���', '360732', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('eb20ac0af8c0495888cfa3d2a6567e81', '0a3c848e26ed49d396eb5b00ecce7de0', '342600', null, '�����', '33', '�����', '360733', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('77b5f0506b704ad9a0dc04ad31f9b906', '0a3c848e26ed49d396eb5b00ecce7de0', '342200', null, 'Ѱ����', '34', 'Ѱ����', '360734', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3d3d4b1ab8de4692a70cfe8fbba17f2e', '0a3c848e26ed49d396eb5b00ecce7de0', '342700', null, 'ʯ����', '35', 'ʯ����', '360735', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('842e944b9ad94338af27b3de1bd0bd79', '0a3c848e26ed49d396eb5b00ecce7de0', '342500', null, '�����', '81', '�����', '360781', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d205e009c37340a8aa7aa4780490c55a', '0a3c848e26ed49d396eb5b00ecce7de0', '341400', null, '�Ͽ���', '82', '�Ͽ���', '360782', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('14e8e725c8594fc1b795adcfa24e6c84', '04639f4c045a44258ebff7fc94fec3e5', '343000', null, '��Ͻ��', '01', '��Ͻ��', '360801', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('452de1eca70948d89e2d2f660f430124', '04639f4c045a44258ebff7fc94fec3e5', '343000', null, '������', '02', '������', '360802', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('84a695d5b0634a85a2dca02241fb0319', '04639f4c045a44258ebff7fc94fec3e5', '343000', null, '��ԭ��', '03', '��ԭ��', '360803', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e5ef3e58326f4b868767767ccc943dec', '3a3f1ba7751f47dabb57b457b678be60', '130000', null, '������', '03', '������', '220103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bdffc1307cc843a0938d23e41a9f9ae3', '3a3f1ba7751f47dabb57b457b678be60', '130000', null, '������', '04', '������', '220104', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b8af6e8f8efa4be38186a2543c296f43', '3a3f1ba7751f47dabb57b457b678be60', '130000', null, '������', '05', '������', '220105', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ccef5307e4a7470188f3ab7bef0ee461', '3a3f1ba7751f47dabb57b457b678be60', '130000', null, '��԰��', '06', '��԰��', '220106', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('46004a4d1eef4e5ea88c8f53873e0ce9', '3a3f1ba7751f47dabb57b457b678be60', '130600', null, '˫����', '12', '˫����', '220112', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('842feca2b01b4ad9bdd3539b619b4ad5', '3a3f1ba7751f47dabb57b457b678be60', '130200', null, 'ũ����', '22', 'ũ����', '220122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7c24597807ba4d70ae9fc37bb2c66056', '3a3f1ba7751f47dabb57b457b678be60', '130500', null, '��̨��', '81', '��̨��', '220181', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('944e55738e074e92a22e3c30e9fd7976', '3a3f1ba7751f47dabb57b457b678be60', '130400', null, '������', '82', '������', '220182', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('674f926e61ea4d67a3c1242a07c2966f', '3a3f1ba7751f47dabb57b457b678be60', '130300', null, '�»���', '83', '�»���', '220183', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('64431ff7e3dc410aa14059b62550b74c', '7774e0ff0e204d1e8f1c239d87344aa2', '132000', null, '��Ͻ��', '01', '��Ͻ��', '220201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d5ef1713d2144f93ae450848b5f6b4fb', '7774e0ff0e204d1e8f1c239d87344aa2', '132000', null, '������', '02', '������', '220202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a573934eedc641d7aa4e03efee946f6a', '7774e0ff0e204d1e8f1c239d87344aa2', '132000', null, '��̶��', '03', '��̶��', '220203', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('24a3686cfb004912801759ec943bff74', '7774e0ff0e204d1e8f1c239d87344aa2', '132000', null, '��Ӫ��', '04', '��Ӫ��', '220204', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d3fd38c6c6ba4019a39f20bdc76e1edb', '7774e0ff0e204d1e8f1c239d87344aa2', '132000', null, '������', '11', '������', '220211', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8784730772d843e99549a923e322a72f', '7774e0ff0e204d1e8f1c239d87344aa2', '132100', null, '������', '21', '������', '220221', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('95c2720f6aac49999d7af0ed743df73d', '7774e0ff0e204d1e8f1c239d87344aa2', '132500', null, '�Ժ���', '81', '�Ժ���', '220281', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('41ea709324a34155b8d076023d7fa84d', '7774e0ff0e204d1e8f1c239d87344aa2', '132400', null, '�����', '82', '�����', '220282', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('069efe93e6be4d7b89c087a158ddb0dd', '7774e0ff0e204d1e8f1c239d87344aa2', '132600', null, '������', '83', '������', '220283', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cfa8365fad8a472d917a2fa04ec1edb5', '7774e0ff0e204d1e8f1c239d87344aa2', '132300', null, '��ʯ��', '84', '��ʯ��', '220284', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('97b8500058664f389025a000366b6dc3', '00d948ff21b342779471d96e783f9822', '136000', null, '��Ͻ��', '01', '��Ͻ��', '220301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('01d0a7faf81e4efcb4e034bb22710181', '00d948ff21b342779471d96e783f9822', '136000', null, '������', '02', '������', '220302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dd5e707e74334130ab1bafd80883656a', '00d948ff21b342779471d96e783f9822', '136000', null, '������', '03', '������', '220303', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('248fde75c1d644be94d4dc59f3d1acac', '00d948ff21b342779471d96e783f9822', '136500', null, '������', '22', '������', '220322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4fc86b76aceb4e2fa5ddb1fabf255218', '00d948ff21b342779471d96e783f9822', '130700', null, '��ͨ����������', '23', '��ͨ����������', '220323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fcc8c7d6c4b1425e87fe645591b0751f', '00d948ff21b342779471d96e783f9822', '136100', null, '��������', '81', '��������', '220381', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6dc89fd557ec48fcb501374dd1aab123', '00d948ff21b342779471d96e783f9822', '136400', null, '˫����', '82', '˫����', '220382', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8560872e81034fc9a3825eb218364e41', '8ce5a37dedc84f23832b14be8e0c601e', '136200', null, '��Ͻ��', '01', '��Ͻ��', '220401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5a069b17a23346c0b8333717c2a65188', '8ce5a37dedc84f23832b14be8e0c601e', '136200', null, '��ɽ��', '02', '��ɽ��', '220402', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('13d6ed5f8c9d455f818e02b21cb611b6', '8ce5a37dedc84f23832b14be8e0c601e', '136200', null, '������', '03', '������', '220403', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e3e33324665f4921bfd4df61c64f4658', '8ce5a37dedc84f23832b14be8e0c601e', '136300', null, '������', '21', '������', '220421', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('48079fbed33944189213ad44e4aec260', '8ce5a37dedc84f23832b14be8e0c601e', '136600', null, '������', '22', '������', '220422', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0b378ff8d5fa40fb8a5837c88f6d3df4', 'c2cf2135b6be4b99b8fd891bbd43f686', '134000', null, '��Ͻ��', '01', '��Ͻ��', '220501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2d0ff7e82290422fb364b247057d4975', 'c2cf2135b6be4b99b8fd891bbd43f686', '134000', null, '������', '02', '������', '220502', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f00a3f42718847869cca06e74c5d3990', 'c2cf2135b6be4b99b8fd891bbd43f686', '134000', null, '��������', '03', '��������', '220503', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0d65473a4dda457db1a8ecc5d346f8ab', 'c2cf2135b6be4b99b8fd891bbd43f686', '134100', null, 'ͨ����', '21', 'ͨ����', '220521', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d20439e20e9648b1aaef1589867091cb', 'c2cf2135b6be4b99b8fd891bbd43f686', '135100', null, '������', '23', '������', '220523', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('544d0d2e40e14f888b11e67c99a301aa', 'c2cf2135b6be4b99b8fd891bbd43f686', '135300', null, '������', '24', '������', '220524', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c42dedaae0b143b081ef1c954ab0cb1b', 'c2cf2135b6be4b99b8fd891bbd43f686', '135000', null, '÷�ӿ���', '81', '÷�ӿ���', '220581', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b8fec82934b24acab60d7509df038b46', 'c2cf2135b6be4b99b8fd891bbd43f686', '134200', null, '������', '82', '������', '220582', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2dcb0f2436f54ae0a105d54f0cb8eee4', '3551b526f5e7417fae31cf0d2edb4d9d', '134300', null, '��Ͻ��', '01', '��Ͻ��', '220601', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('99bbbca0b38e4393aa2eaef3bf67a219', '3551b526f5e7417fae31cf0d2edb4d9d', '134300', null, '�˵�����', '02', '�˵�����', '220602', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5115978cb0924624adeadbbe1c10f190', '3551b526f5e7417fae31cf0d2edb4d9d', '134500', null, '������', '21', '������', '220621', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('515b7a16579d443d8707a53359fba6bb', '3551b526f5e7417fae31cf0d2edb4d9d', '135200', null, '������', '22', '������', '220622', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d4cde58e7c244edbbf981cdd4b2e46ff', '3551b526f5e7417fae31cf0d2edb4d9d', '134400', null, '���׳�����������', '23', '���׳�����������', '220623', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('09ba0626d4b44b9a97dad5f70bf5b7ed', '3551b526f5e7417fae31cf0d2edb4d9d', '134700', null, '��Դ��', '25', '��Դ��', '220625', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5c943ad9a78d44a0950037119edd3ed0', '3551b526f5e7417fae31cf0d2edb4d9d', '134600', null, '�ٽ���', '81', '�ٽ���', '220681', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('69c173cc08bb43afae3a02245c303b88', 'a0ee1a7af5be4b53b88627407200545f', '138000', null, '��Ͻ��', '01', '��Ͻ��', '220701', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a09681f177b4472c8b0fd431fa25b878', 'a0ee1a7af5be4b53b88627407200545f', '138000', null, '������', '02', '������', '220702', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7ed7eab38e8747ac938eba076e5dfe10', 'a0ee1a7af5be4b53b88627407200545f', '131100', null, 'ǰ������˹�ɹ���������', '21', 'ǰ������˹�ɹ���������', '220721', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2f47130ae9e942a7bf503c016c5e2d58', 'a0ee1a7af5be4b53b88627407200545f', '131500', null, '������', '22', '������', '220722', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('597e1ad0cded48a48eadeca8ea67e98d', 'a0ee1a7af5be4b53b88627407200545f', '131400', null, 'Ǭ����', '23', 'Ǭ����', '220723', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a8ae88a9a4e54568b7712292873f26bd', 'a0ee1a7af5be4b53b88627407200545f', '131200', null, '������', '24', '������', '220724', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('02f07cb01edd4194a51e953dabf94325', 'bd2d77f833b2472d8a828a90f402f861', '137000', null, '��Ͻ��', '01', '��Ͻ��', '220801', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('08586b0523584edf89475a2955f1a48d', 'bd2d77f833b2472d8a828a90f402f861', '137000', null, '䬱���', '02', '䬱���', '220802', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5242f6457c4f46e1878df16a820bc03c', 'bd2d77f833b2472d8a828a90f402f861', '137300', null, '������', '21', '������', '220821', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('75f144842bc4458fa864d602526f5881', 'bd2d77f833b2472d8a828a90f402f861', '137200', null, 'ͨ����', '22', 'ͨ����', '220822', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('70119777667a42e2b9a7ebcf51571f0f', 'bd2d77f833b2472d8a828a90f402f861', '137100', null, '�����', '81', '�����', '220881', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('80f17c3ae12d4c6ba7fa7534b9e4e6d8', '1d21c167dbe048d7942a636ec4ce172c', '472300', null, '������', '81', '������', '411281', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('83f4aa9bbf3043cb8f9b2f9330527173', 'db9ba52f83f943c3bb606a0d52ad769e', '231200', null, '������', '23', '������', '340123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('edc01ef8f1314c58b9cc1e220ac49b64', 'e3a4a4ba5c56445c92c6a4d932ae463e', '241000', null, '��Ͻ��', '01', '��Ͻ��', '340201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e68c232d9c294bd2b62bc8cb91e99bf1', 'e3a4a4ba5c56445c92c6a4d932ae463e', '241000', null, '������', '02', '������', '340202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('96951cd3367343ce8fdc61e5f60b09a8', 'e3a4a4ba5c56445c92c6a4d932ae463e', '241000', null, '������', '03', '������', '340203', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('98e41b79cb024c609b927474d2d14c90', 'e3a4a4ba5c56445c92c6a4d932ae463e', '241000', null, '������', '04', '������', '340204', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6c4d3914a8624862a8e71dcfc41c3052', 'e3a4a4ba5c56445c92c6a4d932ae463e', '241000', null, '𯽭��', '07', '𯽭��', '340207', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7e5254d8b4ef4e72b226f7253fc12f3b', 'e3a4a4ba5c56445c92c6a4d932ae463e', '241100', null, '�ߺ���', '21', '�ߺ���', '340221', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e1ae0535385d4171b0722412d981c000', '0700bce39eff4bbaad9989a4e5283ea5', '152000', null, '������', '02', '������', '231202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cab2bd32bfd9429daef07c667dc999ee', '0700bce39eff4bbaad9989a4e5283ea5', '152100', null, '������', '21', '������', '231221', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6e90a1db12194fe0880fad61a2ee9a7a', '0700bce39eff4bbaad9989a4e5283ea5', '151500', null, '������', '22', '������', '231222', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0f7c2ac27e3b4986b25328110ba6f218', '0700bce39eff4bbaad9989a4e5283ea5', '151600', null, '�����', '23', '�����', '231223', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('62a8ab9dc84941089c2b4c6c16bcecc1', '0700bce39eff4bbaad9989a4e5283ea5', '152400', null, '�찲��', '24', '�찲��', '231224', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5e4a58c7341f4e75a04e67e815970dcf', '0700bce39eff4bbaad9989a4e5283ea5', '151700', null, '��ˮ��', '25', '��ˮ��', '231225', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2059b0e5bbb84b52aa81eb6e8f657b68', '0700bce39eff4bbaad9989a4e5283ea5', '152200', null, '������', '26', '������', '231226', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e14fd9d655f446c696a53d907aff2d65', '0700bce39eff4bbaad9989a4e5283ea5', '151400', null, '������', '81', '������', '231281', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6ec62519ae3541c695f2fcc6fbea550b', '0700bce39eff4bbaad9989a4e5283ea5', '151100', null, '�ض���', '82', '�ض���', '231282', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3ff4b70de31d4d31b0eab9f1af5d2384', '0700bce39eff4bbaad9989a4e5283ea5', '152300', null, '������', '83', '������', '231283', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('412ddca1d6a84e19841ca2604db6c503', 'ba25a32ee5ea4cbaae42084151c8cd4c', '165100', null, '������', '21', '������', '232721', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('77b97e47716d44989e21ab4373ee6212', 'ba25a32ee5ea4cbaae42084151c8cd4c', '165200', null, '������', '22', '������', '232722', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7f5c02b9f7c047a89d1b31b3fd87e6b4', 'ba25a32ee5ea4cbaae42084151c8cd4c', '165300', null, 'Į����', '23', 'Į����', '232723', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('413a888279364b00817856cee772e30c', '9cf48c1bd1494b26b7dfeb12ea3238d8', '200000', null, '������', '01', '������', '310101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('acbde2c70e5c466aa2041de131125641', '9cf48c1bd1494b26b7dfeb12ea3238d8', '200000', null, '¬����', '03', '¬����', '310103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('08702d03833f45efaf35b1226bae8ee1', '9cf48c1bd1494b26b7dfeb12ea3238d8', '200000', null, '�����', '04', '�����', '310104', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3992790d3bdc466c9bc363c5b9f0b20d', '9cf48c1bd1494b26b7dfeb12ea3238d8', '200000', null, '������', '05', '������', '310105', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7be3568d19f443e58f213cf479a11553', '9cf48c1bd1494b26b7dfeb12ea3238d8', '200000', null, '������', '06', '������', '310106', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cdc5e540166a42c09b6a7ef944dca501', '9cf48c1bd1494b26b7dfeb12ea3238d8', '200000', null, '������', '07', '������', '310107', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c5a5796aeb60418485a07b73c73ecdb0', '9cf48c1bd1494b26b7dfeb12ea3238d8', '200000', null, 'բ����', '08', 'բ����', '310108', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('65ce1fc6882442d3bef3648c12084922', '9cf48c1bd1494b26b7dfeb12ea3238d8', '200000', null, '�����', '09', '�����', '310109', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e7edc3e13a2545b79dd4fee37cbab926', '9cf48c1bd1494b26b7dfeb12ea3238d8', '200000', null, '������', '10', '������', '310110', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('61d98065c43d44299a842af349e7d8e6', '9cf48c1bd1494b26b7dfeb12ea3238d8', '201100', null, '������', '12', '������', '310112', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f0b575ad559c4fd588b103110b527a3d', '9cf48c1bd1494b26b7dfeb12ea3238d8', '201900', null, '��ɽ��', '13', '��ɽ��', '310113', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d88676cd8f6c4d4eaf5fb42637902c20', '9cf48c1bd1494b26b7dfeb12ea3238d8', '201800', null, '�ζ���', '14', '�ζ���', '310114', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e8985210b5b2495cb7aa0661771acdef', '9cf48c1bd1494b26b7dfeb12ea3238d8', '200120', null, '�ֶ�����', '15', '�ֶ�����', '310115', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3cd0f15149d24105b6d94ffbf9b45bb2', '9cf48c1bd1494b26b7dfeb12ea3238d8', '201500', null, '��ɽ��', '16', '��ɽ��', '310116', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('df1621f2212845f0b8d35a38e51ed81f', '9cf48c1bd1494b26b7dfeb12ea3238d8', '201600', null, '�ɽ���', '17', '�ɽ���', '310117', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7870c5d0aaea469498e84f2dc50f9f82', '9cf48c1bd1494b26b7dfeb12ea3238d8', '201700', null, '������', '18', '������', '310118', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a59800b72239400496be522ce44d94d1', '9cf48c1bd1494b26b7dfeb12ea3238d8', '201300', null, '�ϻ���', '19', '�ϻ���', '310119', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8c7cad8a9bab4547adb0afb9fcfe7c39', '9cf48c1bd1494b26b7dfeb12ea3238d8', '201400', null, '������', '20', '������', '310120', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6aab3a95115b42cca2de325b16b464cd', '9cf48c1bd1494b26b7dfeb12ea3238d8', '202150', null, '������', '30', '������', '310230', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8a72926d51524807bdba69004899dd5d', 'e15988d61b834780bec69b7dac6c283a', '210000', null, '��Ͻ��', '01', '��Ͻ��', '320101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3302343f401b44bb8e7707d70153d585', 'e15988d61b834780bec69b7dac6c283a', '210000', null, '������', '02', '������', '320102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bea67e2add404a4db49a728661c02e0d', 'e15988d61b834780bec69b7dac6c283a', '210000', null, '������', '03', '������', '320103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b610e8c359674cf2bb56dc332709f757', 'e15988d61b834780bec69b7dac6c283a', '210000', null, '�ػ���', '04', '�ػ���', '320104', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('55ec3ca837174e22abbdb5cec5688ac4', 'e15988d61b834780bec69b7dac6c283a', '210000', null, '������', '05', '������', '320105', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5ebf0d8369924e8abe369ae261c53a99', 'e15988d61b834780bec69b7dac6c283a', '210000', null, '��¥��', '06', '��¥��', '320106', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2cc938949e944bbd9cd9e32776e0bdc3', 'e15988d61b834780bec69b7dac6c283a', '210000', null, '�¹���', '07', '�¹���', '320107', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('18cfcb00bd644914833e0678dcd49b58', 'e15988d61b834780bec69b7dac6c283a', '210000', null, '�ֿ���', '11', '�ֿ���', '320111', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('335afa07c57045d2976e55abdcc58e2d', 'e15988d61b834780bec69b7dac6c283a', '210000', null, '��ϼ��', '13', '��ϼ��', '320113', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ad697b2753ec48d69c77fd609d20c669', 'e15988d61b834780bec69b7dac6c283a', '210000', null, '�껨̨��', '14', '�껨̨��', '320114', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0c542275d44145bf8de459445f048316', 'e15988d61b834780bec69b7dac6c283a', '211100', null, '������', '15', '������', '320115', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e3abf9a4410c4d0eb62bba155f902f0e', 'e15988d61b834780bec69b7dac6c283a', '211500', null, '������', '16', '������', '320116', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('50e87c6315464d709ff17380c5422c1b', 'e15988d61b834780bec69b7dac6c283a', '211200', null, '��ˮ��', '24', '��ˮ��', '320124', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('072f0b4f06284fad936afe0e0d0e6189', 'e15988d61b834780bec69b7dac6c283a', '211300', null, '�ߴ���', '25', '�ߴ���', '320125', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bbb2cb95c1e2450092fc1c3fa67b8081', 'b02f8deca60a49b98e7ed5aa0428e5e6', '214000', null, '��Ͻ��', '01', '��Ͻ��', '320201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('afb23849574346fb910f7e31298c0f1d', 'b02f8deca60a49b98e7ed5aa0428e5e6', '214000', null, '�簲��', '02', '�簲��', '320202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('32fe10222ada4489bdedcbcf71c8f6de', 'b02f8deca60a49b98e7ed5aa0428e5e6', '214000', null, '�ϳ���', '03', '�ϳ���', '320203', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b66646ec0d194251b1f29792db71062c', 'b02f8deca60a49b98e7ed5aa0428e5e6', '214000', null, '������', '04', '������', '320204', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ee081b763fa845ddaf05d38d27a5708a', 'b02f8deca60a49b98e7ed5aa0428e5e6', '214000', null, '��ɽ��', '05', '��ɽ��', '320205', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d03c9b71727e4dfcac8fc8bfb281bb9e', 'b02f8deca60a49b98e7ed5aa0428e5e6', '214000', null, '��ɽ��', '06', '��ɽ��', '320206', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0f97de95f5cd49ecb232e653e1e586ef', 'b02f8deca60a49b98e7ed5aa0428e5e6', '214000', null, '������', '11', '������', '320211', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('482a4d4211ea4e54af1fffe146c865d4', 'b02f8deca60a49b98e7ed5aa0428e5e6', '214400', null, '������', '81', '������', '320281', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c3a171d014774d19be4bcb7a622fb2e3', 'b02f8deca60a49b98e7ed5aa0428e5e6', '214200', null, '������', '82', '������', '320282', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a362825afaa64316a5b0e7394b1f115a', '02fd4185ea2343ffaae15ca2c5caf2b3', '221000', null, '��Ͻ��', '01', '��Ͻ��', '320301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('47818cdd94a6496a8f2132e693ff1dda', '02fd4185ea2343ffaae15ca2c5caf2b3', '221000', null, '��¥��', '02', '��¥��', '320302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6e499b8ea85d4f85bed95324681452a9', '02fd4185ea2343ffaae15ca2c5caf2b3', '221000', null, '������', '03', '������', '320303', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1ac1102d45f941159c3b8e73846cd4cf', '02fd4185ea2343ffaae15ca2c5caf2b3', '221000', null, '������', '04', '������', '320304', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9e39d462851e41988fcc056fa344165c', '02fd4185ea2343ffaae15ca2c5caf2b3', '221000', null, '������', '05', '������', '320305', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('581dbbc4c5a648a1a51f01f1a895227b', 'adf505a23af74179b00c02a084407ca8', '266000', null, '��Ͻ��', '01', '��Ͻ��', '370201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7edba3a11e504eb19f9dc78fb146cdbb', 'adf505a23af74179b00c02a084407ca8', '266000', null, '������', '02', '������', '370202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e4ddb821ccd145fdbfe321f8ea544d31', 'adf505a23af74179b00c02a084407ca8', '266000', null, '�б���', '03', '�б���', '370203', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0bb372c0466249a790302c0e63ec33d1', 'adf505a23af74179b00c02a084407ca8', '266000', null, '�ķ���', '05', '�ķ���', '370205', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c7463e9e8da4471e81de2be43ce624bc', 'adf505a23af74179b00c02a084407ca8', '266000', null, '�Ƶ���', '11', '�Ƶ���', '370211', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('926d102728534f35973b3790de030ba4', 'adf505a23af74179b00c02a084407ca8', '266100', null, '��ɽ��', '12', '��ɽ��', '370212', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('63ea2527dc5741309489c5fd1a2e150a', '02fd4185ea2343ffaae15ca2c5caf2b3', '221300', null, '������', '82', '������', '320382', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2e51ac7159e94b30a4e01b2b45581f21', '0c09b62ba8c74384bc7edf68979f3caf', '213000', null, '��Ͻ��', '01', '��Ͻ��', '320401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('55e6cbcc4dae41bb924e955a647fa9e0', '0c09b62ba8c74384bc7edf68979f3caf', '213000', null, '������', '02', '������', '320402', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d9014da174d9476ca331a5f97312a4d2', '0c09b62ba8c74384bc7edf68979f3caf', '213000', null, '��¥��', '04', '��¥��', '320404', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('26f6793bc2584acdb01762caacfd4f31', '0c09b62ba8c74384bc7edf68979f3caf', '213000', null, '��������', '05', '��������', '320405', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1e007c9863c34072bbe29ed06d897771', '0c09b62ba8c74384bc7edf68979f3caf', '213000', null, '�±���', '11', '�±���', '320411', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bdaa54fee7c1405ea671621ea1aaf4a0', '0c09b62ba8c74384bc7edf68979f3caf', '213100', null, '�����', '12', '�����', '320412', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6efde1669e99445eb687e822a083b5d6', '0c09b62ba8c74384bc7edf68979f3caf', '213300', null, '������', '81', '������', '320481', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f2488a5d03d4412aa8ced57bec72941d', '0c09b62ba8c74384bc7edf68979f3caf', '213200', null, '��̳��', '82', '��̳��', '320482', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('547fc56376d4459592d1b83aafdb8779', '09e191fc259b41d49524f1ffa57f641f', '215000', null, '��Ͻ��', '01', '��Ͻ��', '320501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c582932c754f462cb849f4676e38cae6', '09e191fc259b41d49524f1ffa57f641f', '215000', null, '������', '02', '������', '320502', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aff7914ffbb04e7c9f4870675538f9cc', '09e191fc259b41d49524f1ffa57f641f', '215000', null, 'ƽ����', '03', 'ƽ����', '320503', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6863bf97c95a44428d3fa20906c05bcf', '09e191fc259b41d49524f1ffa57f641f', '215000', null, '������', '04', '������', '320504', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('746e11bfdf724828ba64dbf2e46a3a81', '09e191fc259b41d49524f1ffa57f641f', '215000', null, '������', '05', '������', '320505', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('351bfa47a8fb4bc49d27366fff57e41f', '09e191fc259b41d49524f1ffa57f641f', '215100', null, '������', '06', '������', '320506', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d819efc8a2c240e49743578cb7a00b8f', '09e191fc259b41d49524f1ffa57f641f', '215100', null, '�����', '07', '�����', '320507', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('08ee5cfd5a0b486f85ba6196d4fdf02d', '09e191fc259b41d49524f1ffa57f641f', '215500', null, '������', '81', '������', '320581', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c7cb9edc3b834b2489f248550c373b87', '09e191fc259b41d49524f1ffa57f641f', '215600', null, '�żҸ���', '82', '�żҸ���', '320582', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('94e7e116b4f345a78e154ce85cae4969', '09e191fc259b41d49524f1ffa57f641f', '215300', null, '��ɽ��', '83', '��ɽ��', '320583', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e9c32e4cbddd44e19f5b2634ab92b213', '09e191fc259b41d49524f1ffa57f641f', '215200', null, '�⽭��', '84', '�⽭��', '320584', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('223544a6f09d476a8a79b3ae22613669', '09e191fc259b41d49524f1ffa57f641f', '215400', null, '̫����', '85', '̫����', '320585', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('99df5e1435bd4dbbb9ebceeba2afd4ae', 'b98b1f8bf0b94b8785ca162c17011d3f', '226000', null, '��Ͻ��', '01', '��Ͻ��', '320601', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('85e38fa47c014b239dc388be49376fbc', 'b98b1f8bf0b94b8785ca162c17011d3f', '226000', null, '�紨��', '02', '�紨��', '320602', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fc887be5f650494ea0cce188b371c5a8', 'b98b1f8bf0b94b8785ca162c17011d3f', '226000', null, '��բ��', '11', '��բ��', '320611', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('558cfb58373341f992fe2375ef55e0e1', 'b98b1f8bf0b94b8785ca162c17011d3f', '226600', null, '������', '21', '������', '320621', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('616e40587b5b4db69dfe2b492cbce9b6', 'b98b1f8bf0b94b8785ca162c17011d3f', '226400', null, '�綫��', '23', '�綫��', '320623', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5896ef95a27e4e318425307f527125ac', 'b98b1f8bf0b94b8785ca162c17011d3f', '226200', null, '������', '81', '������', '320681', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ca1d1903946c4ff7aa11f402f695f44e', 'b98b1f8bf0b94b8785ca162c17011d3f', '226500', null, '�����', '82', '�����', '320682', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ea0de6f90c8947faa912d9b5fbe8a023', 'b98b1f8bf0b94b8785ca162c17011d3f', '226300', null, 'ͨ����', '83', 'ͨ����', '320683', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('75a9a778b96c4f7393138b5122a8f37a', 'b98b1f8bf0b94b8785ca162c17011d3f', '226100', null, '������', '84', '������', '320684', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9c53e2ac0e2349c3a78bd3b871e542ef', 'c85fe163313c4fd78e007d8ab1d9a9c7', '222000', null, '��Ͻ��', '01', '��Ͻ��', '320701', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d5a1fae397f344d8b0345b9564294bbe', 'c85fe163313c4fd78e007d8ab1d9a9c7', '222000', null, '������', '03', '������', '320703', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d94a770db89d4342a0c93300d4bfb1c7', '08cb7a128990421a99d0528514ebe24c', '611600', null, '�ѽ���', '31', '�ѽ���', '510131', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7894580499244da0822a317f5c283249', '08cb7a128990421a99d0528514ebe24c', '611400', null, '�½���', '32', '�½���', '510132', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('38071602dd184fc198277eca81000232', '08cb7a128990421a99d0528514ebe24c', '611800', null, '��������', '81', '��������', '510181', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('211aca7a0de5400089baf1b46b0fb794', '08cb7a128990421a99d0528514ebe24c', '610000', null, '������', '82', '������', '510182', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ff467d11c9c74a2b8f0159e5ec4c9251', '08cb7a128990421a99d0528514ebe24c', '611500', null, '������', '83', '������', '510183', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('860390f90c4148c793dd1ede0805d1a3', '08cb7a128990421a99d0528514ebe24c', '611200', null, '������', '84', '������', '510184', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3ce0f1c7048446a9bffacc651593d725', 'c119b8ebe0464e6e8535bca0598e13eb', '643000', null, '��Ͻ��', '01', '��Ͻ��', '510301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1c800c5485704623903fa449e1199beb', 'c119b8ebe0464e6e8535bca0598e13eb', '643000', null, '��������', '02', '��������', '510302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0c52398ab83942b48c2dda87b209dcdb', 'c119b8ebe0464e6e8535bca0598e13eb', '643020', null, '������', '03', '������', '510303', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3b34ec44d6dc47529952c826e6a1ce1a', 'c119b8ebe0464e6e8535bca0598e13eb', '643010', null, '����', '04', '����', '510304', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d0a933632ca04a878f17f5ad06c11c6a', 'c119b8ebe0464e6e8535bca0598e13eb', '643030', null, '��̲��', '11', '��̲��', '510311', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('84324997bd6742f4a484cd11cd646b52', 'c119b8ebe0464e6e8535bca0598e13eb', '643100', null, '����', '21', '����', '510321', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7cd9bec3d89e4cdfbc4da67e9052b5c0', 'c119b8ebe0464e6e8535bca0598e13eb', '643200', null, '��˳��', '22', '��˳��', '510322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8a3c409f8de341ef803d67e25aa4542d', 'e416c82c26c848c1a9532943280f7738', '617000', null, '��Ͻ��', '01', '��Ͻ��', '510401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6815f0fdbd8f4a8b93347c3f1381747a', '8ebfb0cbf62d4b6597fd742963815e4d', '431400', null, '������', '17', '������', '420117', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('678c759721a147089945612eb1291e39', 'f0236aa5f8f246bfaa238b0f756c17d4', '435000', null, '��Ͻ��', '01', '��Ͻ��', '420201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('31e85afa3929414e8e95c8ef288b381c', 'f0236aa5f8f246bfaa238b0f756c17d4', '435000', null, '��ʯ����', '02', '��ʯ����', '420202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e83feaeede4342cea4e9ec636f099261', 'f0236aa5f8f246bfaa238b0f756c17d4', '435000', null, '����ɽ��', '03', '����ɽ��', '420203', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('baae26e11db246089dbc1d42a725f19a', 'f0236aa5f8f246bfaa238b0f756c17d4', '435000', null, '��½��', '04', '��½��', '420204', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b2283de412bd49538693cc5a6999bf2e', 'f0236aa5f8f246bfaa238b0f756c17d4', '435000', null, '��ɽ��', '05', '��ɽ��', '420205', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('77a2f2fd31f74852b413dda8f3490be3', 'f0236aa5f8f246bfaa238b0f756c17d4', '435200', null, '������', '22', '������', '420222', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9d6f5d86c63241efb39012c947643d88', 'f0236aa5f8f246bfaa238b0f756c17d4', '435100', null, '��ұ��', '81', '��ұ��', '420281', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e60f037eeea441b08098a015eeb4c2fc', 'd3a817abfa1a4d2f80ce7ca14cb04134', '442000', null, '��Ͻ��', '01', '��Ͻ��', '420301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('027068bce3714f79ace559d61c93bbec', 'd3a817abfa1a4d2f80ce7ca14cb04134', '442000', null, 'é����', '02', 'é����', '420302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7f4d1d3edfbe41fe88787b2d582e2d02', 'd3a817abfa1a4d2f80ce7ca14cb04134', '442000', null, '������', '03', '������', '420303', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('34b47c9481aa49d4b4102cfdf063d831', 'd3a817abfa1a4d2f80ce7ca14cb04134', '442500', null, '����', '21', '����', '420321', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('39ad6eb1206748a7a929ba341914c434', 'd3a817abfa1a4d2f80ce7ca14cb04134', '442600', null, '������', '22', '������', '420322', null);
commit;
prompt 2000 records committed...
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ceb67fd1148a454d81ca22a79fcacc0e', 'd3a817abfa1a4d2f80ce7ca14cb04134', '442200', null, '��ɽ��', '23', '��ɽ��', '420323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('62789c50cdd8432697fdd7b62452e45b', 'd3a817abfa1a4d2f80ce7ca14cb04134', '442300', null, '��Ϫ��', '24', '��Ϫ��', '420324', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('17cad7d69d02484485a3f7ca9f5e4b17', 'd3a817abfa1a4d2f80ce7ca14cb04134', '442100', null, '����', '25', '����', '420325', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0a6d43ffb3b141f286108eafa66a2e0a', 'd3a817abfa1a4d2f80ce7ca14cb04134', '442700', null, '��������', '81', '��������', '420381', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c80457ffa3754abe89722702b777a84c', 'e3d5484dc1394cbb81b162633c379be5', '443000', null, '��Ͻ��', '01', '��Ͻ��', '420501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0a250abdf4374d4784279015e22c3dc6', 'e3d5484dc1394cbb81b162633c379be5', '443000', null, '������', '02', '������', '420502', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('80943748861442efb9cb0a64652d7560', 'e3d5484dc1394cbb81b162633c379be5', '443000', null, '��Ҹ���', '03', '��Ҹ���', '420503', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d4ca08ceab214659bbde44dd14afe640', 'e3d5484dc1394cbb81b162633c379be5', '443000', null, '�����', '04', '�����', '420504', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('58ddcecad76c4507b4dcb2bb4bd0f362', 'e3d5484dc1394cbb81b162633c379be5', '443000', null, '�Vͤ��', '05', '�Vͤ��', '420505', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2c08bf63ef844f90b31adc5ef13c82e2', 'e3d5484dc1394cbb81b162633c379be5', '443100', null, '������', '06', '������', '420506', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6054583d4be64c1d88f248dbe51ab0a7', 'e3d5484dc1394cbb81b162633c379be5', '444200', null, 'Զ����', '25', 'Զ����', '420525', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('88b116b127fe4507b576a23770a264b2', 'e3d5484dc1394cbb81b162633c379be5', '443700', null, '��ɽ��', '26', '��ɽ��', '420526', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d6e7162de8b64630a770e1669d900a02', 'e3d5484dc1394cbb81b162633c379be5', '443600', null, '������', '27', '������', '420527', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f424360a599e498fbb0f4dbb648c95ba', 'e3d5484dc1394cbb81b162633c379be5', '443500', null, '����������������', '28', '����������������', '420528', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('696012069a124c7387f1b380a5321c78', 'e3d5484dc1394cbb81b162633c379be5', '443400', null, '���������������', '29', '���������������', '420529', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('12fccd2d67d8472188eb7ca3fa93c612', 'e3d5484dc1394cbb81b162633c379be5', '443000', null, '�˶���', '81', '�˶���', '420581', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('232824df575e4a249c26bb79210de207', 'e3d5484dc1394cbb81b162633c379be5', '444100', null, '������', '82', '������', '420582', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('441311e624954bc08af3bb5363a133be', 'e3d5484dc1394cbb81b162633c379be5', '443200', null, '֦����', '83', '֦����', '420583', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4034b10bbc144321be20c24f40473283', 'cd4e23e0d23048a3837e85d24ad2783a', '441000', null, '��Ͻ��', '01', '��Ͻ��', '420601', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c3f63adc09ee4ff49f598f4e84983d3a', 'cd4e23e0d23048a3837e85d24ad2783a', '441000', null, '�����', '02', '�����', '420602', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f0ef3716f28041a897d65b565f83ddcb', 'cd4e23e0d23048a3837e85d24ad2783a', '441000', null, '������', '06', '������', '420606', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4e7dcf1fe2f146bcb76caf9f05cb4252', 'cd4e23e0d23048a3837e85d24ad2783a', '441100', null, '������', '07', '������', '420607', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('39e1decacb1c4d9592e11e7f1ea35ac9', 'cd4e23e0d23048a3837e85d24ad2783a', '441500', null, '������', '24', '������', '420624', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2c3057d3bb40454db986ef56189e9265', 'cd4e23e0d23048a3837e85d24ad2783a', '441700', null, '�ȳ���', '25', '�ȳ���', '420625', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1505ba79216d4caf9162750301a4a5cc', 'cd4e23e0d23048a3837e85d24ad2783a', '441600', null, '������', '26', '������', '420626', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('57034e64786445fdb001ec802a97c6d5', 'cd4e23e0d23048a3837e85d24ad2783a', '441800', null, '�Ϻӿ���', '82', '�Ϻӿ���', '420682', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3d79dbe03d114b858ed103062a23fd1c', 'cd4e23e0d23048a3837e85d24ad2783a', '441200', null, '������', '83', '������', '420683', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('93d0d671e85c40faa5f87bc903f17fd9', 'cd4e23e0d23048a3837e85d24ad2783a', '441400', null, '�˳���', '84', '�˳���', '420684', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a09eb1235a5e4c8b99379a0ad1a4b4b5', '41054de8248f40979f122baf019dda58', '436000', null, '��Ͻ��', '01', '��Ͻ��', '420701', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c676e06d41574cf799f63377452de68e', '41054de8248f40979f122baf019dda58', '436000', null, '���Ӻ���', '02', '���Ӻ���', '420702', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('de46b22a5ff44c0e964519350615bb7d', '41054de8248f40979f122baf019dda58', '436000', null, '������', '03', '������', '420703', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1ba657cc6c5040a9a3e87412bf08a13a', '41054de8248f40979f122baf019dda58', '436000', null, '������', '04', '������', '420704', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b782ba32c0d449c883c47f2efe1ffa28', '3ed095ba7eea4793a022a5b3b2e21ce6', '448000', null, '��Ͻ��', '01', '��Ͻ��', '420801', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b8ef68a808124428821e320323392921', '3ed095ba7eea4793a022a5b3b2e21ce6', '448000', null, '������', '02', '������', '420802', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('86e646068e24416680e01ef75e727a61', '3ed095ba7eea4793a022a5b3b2e21ce6', '448000', null, '�޵���', '04', '�޵���', '420804', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('26279cf67cc541b99d395d09e8f54e7f', '3ed095ba7eea4793a022a5b3b2e21ce6', '431800', null, '��ɽ��', '21', '��ɽ��', '420821', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8c8cc377eda14b638b73f31368295ddd', '3ed095ba7eea4793a022a5b3b2e21ce6', '448200', null, 'ɳ����', '22', 'ɳ����', '420822', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('45a1d06faa9a4461860feb2e2b55e4c1', '3ed095ba7eea4793a022a5b3b2e21ce6', '431900', null, '������', '81', '������', '420881', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ed691dbd86c84c7e988f4fcfb9ad6600', 'a9623076ba7f459882a4de744213da9f', '432000', null, '��Ͻ��', '01', '��Ͻ��', '420901', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7cc717ffc7584949ae4e2530ad938288', 'a9623076ba7f459882a4de744213da9f', '432100', null, 'Т����', '02', 'Т����', '420902', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8ca606779a79487cb3deca027c1ded84', 'a9623076ba7f459882a4de744213da9f', '432900', null, 'Т����', '21', 'Т����', '420921', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4bad278fff4947a9994302cdac630fd3', 'a9623076ba7f459882a4de744213da9f', '432800', null, '������', '22', '������', '420922', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9471c6e9ba5642f7a8305ad2e7f8ed09', 'a9623076ba7f459882a4de744213da9f', '432500', null, '������', '23', '������', '420923', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('93211676c1fe4aa99a05d71c59af00df', 'a9623076ba7f459882a4de744213da9f', '432400', null, 'Ӧ����', '81', 'Ӧ����', '420981', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cdf515b2124f4598aa29a047bec03c54', 'a9623076ba7f459882a4de744213da9f', '432600', null, '��½��', '82', '��½��', '420982', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b0ef981d34944d83a7c62e4603dcbe0f', 'a9623076ba7f459882a4de744213da9f', '432300', null, '������', '84', '������', '420984', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bfb00644959a4f1a9f922b5f3da2f3b3', '4a6dbf97ae1346a1ae058aada593c83b', '434000', null, '��Ͻ��', '01', '��Ͻ��', '421001', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c699fa41b14442be83760e8cf964e7be', '4a6dbf97ae1346a1ae058aada593c83b', '434000', null, 'ɳ����', '02', 'ɳ����', '421002', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('88b98cc961ac4dd1bcac24039a1b419a', '4a6dbf97ae1346a1ae058aada593c83b', '434020', null, '������', '03', '������', '421003', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aa2437df156f484a8cae0b5aef92bf87', '4a6dbf97ae1346a1ae058aada593c83b', '434300', null, '������', '22', '������', '421022', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8c8a32642b894b539b91a42438eb71a8', '4a6dbf97ae1346a1ae058aada593c83b', '433300', null, '������', '23', '������', '421023', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b0834353e411404d9458d56942ce9371', '4a6dbf97ae1346a1ae058aada593c83b', '434100', null, '������', '24', '������', '421024', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8570cc39278f40d58df3fc24ebf067d5', '4a6dbf97ae1346a1ae058aada593c83b', '434400', null, 'ʯ����', '81', 'ʯ����', '421081', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b1ba2c3e8c5a4ad38b64d4174442771d', '3a8faee14d6a473493ddf5b00fe9eab7', '545400', null, '�ڰ���', '24', '�ڰ���', '450224', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a61fe11060a44816bd0e99e2ed20337f', '3a8faee14d6a473493ddf5b00fe9eab7', '545300', null, '��ˮ����������', '25', '��ˮ����������', '450225', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bd5273463bf948e987b6b5e802705175', '3a8faee14d6a473493ddf5b00fe9eab7', '545500', null, '��������������', '26', '��������������', '450226', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5ea84de435f84297874ca3d9703ba970', '87571143e8fd43b8b3aff17c93e3041b', '541000', null, '��Ͻ��', '01', '��Ͻ��', '450301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7296550dcb804d4b93d09717c1a32840', '87571143e8fd43b8b3aff17c93e3041b', '541000', null, '�����', '02', '�����', '450302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6a840bad134243e6970145b97d348d53', '87571143e8fd43b8b3aff17c93e3041b', '541000', null, '������', '03', '������', '450303', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7ce384c9fb364e19b61fea8148527712', '87571143e8fd43b8b3aff17c93e3041b', '541000', null, '��ɽ��', '04', '��ɽ��', '450304', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('504cb53235f6435087f182ef852af4f1', '87571143e8fd43b8b3aff17c93e3041b', '541000', null, '������', '05', '������', '450305', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c292bde822a6400dbdc06c9ca5917884', '87571143e8fd43b8b3aff17c93e3041b', '541000', null, '��ɽ��', '11', '��ɽ��', '450311', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fa741794a29e46aabf9a3cea04060916', '87571143e8fd43b8b3aff17c93e3041b', '541900', null, '��˷��', '21', '��˷��', '450321', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('be7b210c69074810bb1dcf072f43e7c3', '87571143e8fd43b8b3aff17c93e3041b', '541100', null, '�ٹ���', '22', '�ٹ���', '450322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('675fcf5e6a4d42b5868b08d492bb7ded', '87571143e8fd43b8b3aff17c93e3041b', '541200', null, '�鴨��', '23', '�鴨��', '450323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('07bcb34d79aa4bf79f6c821cec93c70f', '87571143e8fd43b8b3aff17c93e3041b', '541500', null, 'ȫ����', '24', 'ȫ����', '450324', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aef9f025b89b42d6855d987c3e2dc9e1', '87571143e8fd43b8b3aff17c93e3041b', '541300', null, '�˰���', '25', '�˰���', '450325', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2d6b9eb76a6a4c0d8030eb4aef9f9058', '87571143e8fd43b8b3aff17c93e3041b', '541800', null, '������', '26', '������', '450326', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('30f5649b16b6468cac95a01a1715336c', '87571143e8fd43b8b3aff17c93e3041b', '541600', null, '������', '27', '������', '450327', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('83a8b527f7e943639512a48028bef2f0', '87571143e8fd43b8b3aff17c93e3041b', '541700', null, '��ʤ����������', '28', '��ʤ����������', '450328', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fb434f0be438495eb3469d8135309fc2', '87571143e8fd43b8b3aff17c93e3041b', '541400', null, '��Դ��', '29', '��Դ��', '450329', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5796565c480c4c02b0022b9cb377bd0d', '87571143e8fd43b8b3aff17c93e3041b', '542400', null, 'ƽ����', '30', 'ƽ����', '450330', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f10e8a313cb04f0fb53731132f9c63ad', '87571143e8fd43b8b3aff17c93e3041b', '546600', null, '������', '31', '������', '450331', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ddb47fc20e114f70be5562f164c0e872', '87571143e8fd43b8b3aff17c93e3041b', '542500', null, '��������������', '32', '��������������', '450332', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('91cad00c346a426ca09a565f976cdc19', 'a9dc7c04401b43fc92297dfe7d331f81', '543000', null, '��Ͻ��', '01', '��Ͻ��', '450401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e8192aee54f0473c8963cab48db5dda8', 'a9dc7c04401b43fc92297dfe7d331f81', '543000', null, '������', '03', '������', '450403', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0be842c30cfc492a8190f360a7da70a7', 'a9dc7c04401b43fc92297dfe7d331f81', '543000', null, '��ɽ��', '04', '��ɽ��', '450404', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e6affc3e69fe4aa59d538255130efcdc', 'a9dc7c04401b43fc92297dfe7d331f81', '543000', null, '������', '05', '������', '450405', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5a41c23e399e4e2b809871c4b40edff5', 'a9dc7c04401b43fc92297dfe7d331f81', '543100', null, '������', '21', '������', '450421', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('99ec6de1383a45e0abcb60c0cd4c3183', 'a9dc7c04401b43fc92297dfe7d331f81', '543300', null, '����', '22', '����', '450422', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1fbc4b6e2a2f4821aab19be42c8c68fe', 'a9dc7c04401b43fc92297dfe7d331f81', '546700', null, '��ɽ��', '23', '��ɽ��', '450423', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4d0d1874a81e430c947830f8f243b64b', 'a9dc7c04401b43fc92297dfe7d331f81', '543200', null, '�Ϫ��', '81', '�Ϫ��', '450481', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f58dcfd5568c4dce9bc0b2d224715f71', '1f022fdf2fe84a96acc2b187e208d31b', '536000', null, '��Ͻ��', '01', '��Ͻ��', '450501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('56a21223c0e1498cbd62fb22c08b2854', '1f022fdf2fe84a96acc2b187e208d31b', '536000', null, '������', '02', '������', '450502', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9b8f9c49f4494813994319ed8eda8f34', '1f022fdf2fe84a96acc2b187e208d31b', '536000', null, '������', '03', '������', '450503', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3bf384340b9f48fb8940afbe03377252', '1f022fdf2fe84a96acc2b187e208d31b', '536000', null, '��ɽ����', '12', '��ɽ����', '450512', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6833789bb6f54090b996be58e2affb33', '1f022fdf2fe84a96acc2b187e208d31b', '536100', null, '������', '21', '������', '450521', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('17a8e7214901467d8b91e4886f5dadc1', '3ea02e362c824c1680a222ff06d7611b', '538000', null, '��Ͻ��', '01', '��Ͻ��', '450601', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0536fa1223494e80ba561e53423e3298', '3ea02e362c824c1680a222ff06d7611b', '538000', null, '�ۿ���', '02', '�ۿ���', '450602', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a5101c90734142068b9b2dbd43370e76', '3ea02e362c824c1680a222ff06d7611b', '538000', null, '������', '03', '������', '450603', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e315b31c74c544678a6fa1de06256ec0', '3ea02e362c824c1680a222ff06d7611b', '535500', null, '��˼��', '21', '��˼��', '450621', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8c224cc85bcc40238239a7100c027262', '3ea02e362c824c1680a222ff06d7611b', '538100', null, '������', '81', '������', '450681', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0676af19d5fd4c14971f706e3f968ed9', '330e2c8f1a1441dd8bee96e040e1ef7f', '535000', null, '��Ͻ��', '01', '��Ͻ��', '450701', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5b544d0cf00046c8a8449dca2b1caa09', '330e2c8f1a1441dd8bee96e040e1ef7f', '535000', null, '������', '02', '������', '450702', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b4d47e1734934050b176564454c29e3a', '330e2c8f1a1441dd8bee96e040e1ef7f', '535000', null, '�ձ���', '03', '�ձ���', '450703', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3012f48050a547a0a1578e7ee907ff0a', '330e2c8f1a1441dd8bee96e040e1ef7f', '535400', null, '��ɽ��', '21', '��ɽ��', '450721', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ef8afeb65ffe4475b67cc707a8cba560', '330e2c8f1a1441dd8bee96e040e1ef7f', '535300', null, '�ֱ���', '22', '�ֱ���', '450722', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('215a0642abb94cbcbb8f8dc77e5dffe9', 'c4e897a6a2174240baf29d7bfad235ba', '537100', null, '��Ͻ��', '01', '��Ͻ��', '450801', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3e33af56a28d4293932640e72c4a2f70', 'c4e897a6a2174240baf29d7bfad235ba', '537100', null, '�۱���', '02', '�۱���', '450802', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f92f6f004ce04ce599797c811570062c', 'c4e897a6a2174240baf29d7bfad235ba', '537100', null, '������', '03', '������', '450803', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('29ad3ab039f948cabfec4442d72180b2', 'c4e897a6a2174240baf29d7bfad235ba', '537100', null, '������', '04', '������', '450804', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('21d9228004554cb6a5f9ebcfcd1ad60a', 'c4e897a6a2174240baf29d7bfad235ba', '537300', null, 'ƽ����', '21', 'ƽ����', '450821', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('638cf07bf981416c871811d9a5624de0', 'c4e897a6a2174240baf29d7bfad235ba', '537200', null, '��ƽ��', '81', '��ƽ��', '450881', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f9d858743d1047078ba466c2f356030e', 'e420d3d39d12490ab67e5091b54bcdab', '537000', null, '��Ͻ��', '01', '��Ͻ��', '450901', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('86c1ef8896ef4ecc995f5f39d707dc53', 'e420d3d39d12490ab67e5091b54bcdab', '537000', null, '������', '02', '������', '450902', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('295fef733ed545ef8b9bfbdd5b8939ff', 'e420d3d39d12490ab67e5091b54bcdab', '537500', null, '����', '21', '����', '450921', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bc42aa1f876a4623a4281c7bc74fc638', 'e420d3d39d12490ab67e5091b54bcdab', '537700', null, '½����', '22', '½����', '450922', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3cfc24db3af546e2b907fa63253ab16d', 'e420d3d39d12490ab67e5091b54bcdab', '537600', null, '������', '23', '������', '450923', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('53c85c442791464c81d9b7b81895fa6d', 'e420d3d39d12490ab67e5091b54bcdab', '537800', null, '��ҵ��', '24', '��ҵ��', '450924', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bb023a6490f547b59ba625208a0c9223', 'e420d3d39d12490ab67e5091b54bcdab', '537400', null, '������', '81', '������', '450981', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('22b9465e33d54ed5a5991eb6955d75b5', '5944b21cd62d4404bab999965db12d4a', '533000', null, '��Ͻ��', '01', '��Ͻ��', '451001', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('120caccbb0534c4ba1db08c3dd737451', '1d21c167dbe048d7942a636ec4ce172c', '472500', null, '�鱦��', '82', '�鱦��', '411282', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cd8ad955f7354fd695d6f445ec49ed9e', 'e16c41e4d0b5480786a44c95e8fc7a5c', '473000', null, '��Ͻ��', '01', '��Ͻ��', '411301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5199156c5af14cecb4ef9b4e8c6d7b8b', 'e16c41e4d0b5480786a44c95e8fc7a5c', '473000', null, '�����', '02', '�����', '411302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('089613aed97146d7b60fa6a50ddec409', 'e16c41e4d0b5480786a44c95e8fc7a5c', '473000', null, '������', '03', '������', '411303', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e9f649e07db74fbe8791974439c74a45', 'fd915aadafda49b68d1cf622418a9f82', '425000', null, '֥ɽ��', '02', '֥ɽ��', '431102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('26a341e22eab46a4b9d4df018245332a', 'fd915aadafda49b68d1cf622418a9f82', '425000', null, '��ˮ̲��', '03', '��ˮ̲��', '431103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c39b8dcbc27b45b2bbef219e10b44409', 'fd915aadafda49b68d1cf622418a9f82', '421700', null, '������', '21', '������', '431121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2e50732c0f2f42cebf197de378c84750', 'fd915aadafda49b68d1cf622418a9f82', '425900', null, '������', '22', '������', '431122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9c3b32b9f0de4eb6ac1ffc8142c04aba', 'fd915aadafda49b68d1cf622418a9f82', '425200', null, '˫����', '23', '˫����', '431123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('91d91d9cffb2490187eeb56535a0068f', 'fd915aadafda49b68d1cf622418a9f82', '425300', null, '����', '24', '����', '431124', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('39b66d6301ad4de591bee5c5081cd2c3', 'fd915aadafda49b68d1cf622418a9f82', '425400', null, '������', '25', '������', '431125', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('afdc325e04e4497c9706d7c355d927d8', 'fd915aadafda49b68d1cf622418a9f82', '425600', null, '��Զ��', '26', '��Զ��', '431126', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('78ca9c5ed22f470a9d69727351d5838d', 'fd915aadafda49b68d1cf622418a9f82', '425800', null, '��ɽ��', '27', '��ɽ��', '431127', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8442e4e9651a4876b1341eebf643c3bc', 'fd915aadafda49b68d1cf622418a9f82', '425700', null, '������', '28', '������', '431128', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7b6746212df64601b1190306350a7d23', 'fd915aadafda49b68d1cf622418a9f82', '425500', null, '��������������', '29', '��������������', '431129', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c1e758b4897444adb3241cea5d9bc710', '7b81bf0743fd447ebdf08758d3308330', '418000', null, '��Ͻ��', '01', '��Ͻ��', '431201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('442f1db5243248f7868be3e55de11ff7', '7b81bf0743fd447ebdf08758d3308330', '418000', null, '�׳���', '02', '�׳���', '431202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b5f5b0528f424d04b0d34ee3c7941d96', '7b81bf0743fd447ebdf08758d3308330', '418000', null, '�з���', '21', '�з���', '431221', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aaa4328ffae04c6a8f9f8dbd2f710553', '7b81bf0743fd447ebdf08758d3308330', '419600', null, '������', '22', '������', '431222', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5f03d2cce1bb42c2ae2c082c4430b5ee', '7b81bf0743fd447ebdf08758d3308330', '419500', null, '��Ϫ��', '23', '��Ϫ��', '431223', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ecd633af46114223a267fc1309e46d6f', '7b81bf0743fd447ebdf08758d3308330', '419300', null, '������', '24', '������', '431224', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('40932c4b796a4a619f3ac29e09d798b4', '7b81bf0743fd447ebdf08758d3308330', '418300', null, '��ͬ��', '25', '��ͬ��', '431225', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('df77802a27ab4a1286cb72b98d8a90de', '7b81bf0743fd447ebdf08758d3308330', '419400', null, '��������������', '26', '��������������', '431226', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4460b4eaa8d241579173fb6bb5d0a47c', '7b81bf0743fd447ebdf08758d3308330', '419200', null, '�»ζ���������', '27', '�»ζ���������', '431227', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ff3d4cae484c4b53a291b4220791daab', '7b81bf0743fd447ebdf08758d3308330', '419100', null, '�ƽ�����������', '28', '�ƽ�����������', '431228', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1974478b11194872905dbe1001c2eecc', '7b81bf0743fd447ebdf08758d3308330', '418400', null, '�������嶱��������', '29', '�������嶱��������', '431229', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b25b2a65984344f29751500224c18b5c', '7b81bf0743fd447ebdf08758d3308330', '418500', null, 'ͨ������������', '30', 'ͨ������������', '431230', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b121f444debd49d5b3fcbce085109531', '7b81bf0743fd447ebdf08758d3308330', '418200', null, '�齭��', '81', '�齭��', '431281', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cb4a166d54e64771b70ec519417b04a7', 'adcc2c768e274f2d89f802ea109c3235', '417000', null, '��Ͻ��', '01', '��Ͻ��', '431301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ada0eeee014945debaa690c3e1d20f3f', 'adcc2c768e274f2d89f802ea109c3235', '417000', null, '¦����', '02', '¦����', '431302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0b25320d0f734eeb9702adaa2c3966c5', 'adcc2c768e274f2d89f802ea109c3235', '417700', null, '˫����', '21', '˫����', '431321', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6f80873cffb542679c09a7950bdb4cfb', 'adcc2c768e274f2d89f802ea109c3235', '417600', null, '�»���', '22', '�»���', '431322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f54d9722637a4257a02f6227ed7d491a', 'adcc2c768e274f2d89f802ea109c3235', '417500', null, '��ˮ����', '81', '��ˮ����', '431381', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0d8c46b4c0fd48a597917bfb8facda43', 'adcc2c768e274f2d89f802ea109c3235', '417100', null, '��Դ��', '82', '��Դ��', '431382', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e4ee18c1e27246be8c1202702ef2ec67', '0f23a4dca3524daabdae8f9e25604b96', '416000', null, '������', '01', '������', '433101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ed515fbb74e14c38b751269b390af973', '0f23a4dca3524daabdae8f9e25604b96', '416100', null, '��Ϫ��', '22', '��Ϫ��', '433122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('184c83e184974de1b5afe7b4936ab7cf', '0f23a4dca3524daabdae8f9e25604b96', '416200', null, '�����', '23', '�����', '433123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6196a58a3b9b4c4a9f2ea562561df633', '0f23a4dca3524daabdae8f9e25604b96', '416400', null, '��ԫ��', '24', '��ԫ��', '433124', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('23e21a8b5e5840faaec710190a3643ef', '0f23a4dca3524daabdae8f9e25604b96', '416500', null, '������', '25', '������', '433125', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a746c136422f41e492ab3da3cbee5176', '0f23a4dca3524daabdae8f9e25604b96', '416300', null, '������', '26', '������', '433126', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ea9b134444404b61a83a9abb4bcf0a19', '0f23a4dca3524daabdae8f9e25604b96', '416700', null, '��˳��', '27', '��˳��', '433127', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e08cc20b57bd4dbebfc4b2eba9438c38', '0f23a4dca3524daabdae8f9e25604b96', '416800', null, '��ɽ��', '30', '��ɽ��', '433130', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8c85be79596f443b8ecfafc10ac33e9b', '74c34515a15e469e92d05c95aaca78ab', '510000', null, '��Ͻ��', '01', '��Ͻ��', '440101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('15bdb3e9fa5c4a4b8f952c123c078972', '74c34515a15e469e92d05c95aaca78ab', '510000', null, '��ɽ��', '02', '��ɽ��', '440102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8b7d916b1f31431c8c8ef404a340d0fd', '74c34515a15e469e92d05c95aaca78ab', '510000', null, '������', '03', '������', '440103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('91f3498b9509454d82462f774e66e6eb', '74c34515a15e469e92d05c95aaca78ab', '510000', null, 'Խ����', '04', 'Խ����', '440104', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3d46ce16798b40d68f453993ef113f7a', '74c34515a15e469e92d05c95aaca78ab', '510000', null, '������', '05', '������', '440105', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5e57ffb5a4b54ac6912efa45c9505fd6', '74c34515a15e469e92d05c95aaca78ab', '510000', null, '�����', '06', '�����', '440106', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1ee604a06cdf428ab838469d0769a035', '74c34515a15e469e92d05c95aaca78ab', '510000', null, '������', '07', '������', '440107', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d3d0c51fee8e448a9e838ce34008919a', '74c34515a15e469e92d05c95aaca78ab', '510000', null, '������', '11', '������', '440111', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6e06110b62774d9da87b4ed9722147a1', '74c34515a15e469e92d05c95aaca78ab', '510700', null, '������', '12', '������', '440112', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f0e69246acac43519a85443ddbc72210', '74c34515a15e469e92d05c95aaca78ab', '511400', null, '��خ��', '13', '��خ��', '440113', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1e0e54e1b2414fc6b929867f8228b0d0', '74c34515a15e469e92d05c95aaca78ab', '510800', null, '������', '14', '������', '440114', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('068830fbb6f64c7dadc8778b72ccd1c2', '74c34515a15e469e92d05c95aaca78ab', '511300', null, '������', '83', '������', '440183', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9f247dedde274fbbb74100af13173d89', '74c34515a15e469e92d05c95aaca78ab', '510900', null, '�ӻ���', '84', '�ӻ���', '440184', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ca6feb417a3a46af928965fa606e3b8e', 'a7a5db86046e4359b593d5bb51806516', '512000', null, '��Ͻ��', '01', '��Ͻ��', '440201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0cb7e9715d6f48d4bcc91a598f0aa6ee', 'a7a5db86046e4359b593d5bb51806516', '512000', null, '�佭��', '03', '�佭��', '440203', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('97cb64468eff415bb0163483f511b874', 'a7a5db86046e4359b593d5bb51806516', '512000', null, '䥽���', '04', '䥽���', '440204', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('124e2f41ba2647bb9c77122d92274676', 'a7a5db86046e4359b593d5bb51806516', '512100', null, '������', '05', '������', '440205', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9b242e90f11141099f7051c3a4ae3e0e', 'a7a5db86046e4359b593d5bb51806516', '512500', null, 'ʼ����', '22', 'ʼ����', '440222', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e1870f7b881a45c385b7d4a9643f1bf1', 'a7a5db86046e4359b593d5bb51806516', '512300', null, '�ʻ���', '24', '�ʻ���', '440224', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7bd10b36fd5a43cba0a1162683c6bc46', 'a7a5db86046e4359b593d5bb51806516', '512600', null, '��Դ��', '29', '��Դ��', '440229', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0b1d396f757642d5aa1668f7a84d5256', '3f166d4baf12434eb102caddab35da47', '736300', null, '�౱�ɹ���������', '23', '�౱�ɹ���������', '620923', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e9987bc667664807beb65c858de9d353', 'a7a5db86046e4359b593d5bb51806516', '511100', null, '�·���', '33', '�·���', '440233', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2a139cf30ef04307899e2bc32427f68a', '405b1da4465e49088c798360db70c15b', '623100', null, '����', '22', '����', '513222', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8f94c76092de4dfaafb12255b6cb8657', '405b1da4465e49088c798360db70c15b', '623200', null, 'ï��', '23', 'ï��', '513223', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3374b765c4a64f8480b2350d48b3d9d0', '405b1da4465e49088c798360db70c15b', '623300', null, '������', '24', '������', '513224', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b120914bcdda41d6862da362f74c17a2', '405b1da4465e49088c798360db70c15b', '623400', null, '��կ����', '25', '��կ����', '513225', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('eba3f286110b4058b653df8eacb248ff', '6a4a9d34b6904464805411c3d91a1313', '518000', null, '������', '04', '������', '440304', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7f3552a876de48679e585293a00c6f93', '5944b21cd62d4404bab999965db12d4a', '533000', null, '�ҽ���', '02', '�ҽ���', '451002', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('91f93a14f745494f841a5f713de1a9e4', '5944b21cd62d4404bab999965db12d4a', '533600', null, '������', '21', '������', '451021', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('87e3a5d99365402dab271b3663767b70', '5944b21cd62d4404bab999965db12d4a', '531500', null, '�ﶫ��', '22', '�ﶫ��', '451022', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('50b580c0deef40e791a2bb3fe87f64ab', '5944b21cd62d4404bab999965db12d4a', '531400', null, 'ƽ����', '23', 'ƽ����', '451023', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('16c8c98af8ce4af78ed432e85b1b74c6', '5944b21cd62d4404bab999965db12d4a', '533700', null, '�±���', '24', '�±���', '451024', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('68460e63b2984d4482d60ac1511d0ad1', '5944b21cd62d4404bab999965db12d4a', '533800', null, '������', '25', '������', '451025', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6043f3b59be540ed944c84ac11447deb', '5944b21cd62d4404bab999965db12d4a', '533900', null, '������', '26', '������', '451026', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('45a0b886480d416180817a38b8e14514', 'e16c41e4d0b5480786a44c95e8fc7a5c', '474650', null, '������', '21', '������', '411321', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('23da20fc8a3a40b3b2a916724f41ac86', '405b1da4465e49088c798360db70c15b', '624200', null, 'С����', '27', 'С����', '513227', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2fe7b44639204031a4cee141c625c189', '6a4a9d34b6904464805411c3d91a1313', '518000', null, '��ɽ��', '05', '��ɽ��', '440305', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('17b5e47c93a74400a555dfb980136447', '6a4a9d34b6904464805411c3d91a1313', '518100', null, '������', '06', '������', '440306', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7ede6ba37aa7428da57dcd32797b0e98', '6a4a9d34b6904464805411c3d91a1313', '518100', null, '������', '07', '������', '440307', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('970a70c2838a41f09625922fd05a2e87', '6a4a9d34b6904464805411c3d91a1313', '518000', null, '������', '08', '������', '440308', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ee51983b2981421e90a280fc383b13c8', 'fc52a64b6b3748229bd8f9afab749793', '519000', null, '��Ͻ��', '01', '��Ͻ��', '440401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('25f72693580a40139a3af639b58842d1', 'fc52a64b6b3748229bd8f9afab749793', '519000', null, '������', '02', '������', '440402', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ff1e4101f79c497ab2f156e5ca1ad258', 'fc52a64b6b3748229bd8f9afab749793', '519100', null, '������', '03', '������', '440403', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('902e00401aab478984ed8ff1f9873c0e', 'fc52a64b6b3748229bd8f9afab749793', '519090', null, '������', '04', '������', '440404', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('479802e5c6ee41dd85fd3d2fd68fc048', '5fe5cbee4e394883b75404f0b700c38f', '515000', null, '��Ͻ��', '01', '��Ͻ��', '440501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('422a68dc9e7645fc923b8fa7a3fd54b8', '5fe5cbee4e394883b75404f0b700c38f', '515000', null, '������', '07', '������', '440507', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0493300120584d1ba9c86f1e5bd88dbc', '5fe5cbee4e394883b75404f0b700c38f', '515000', null, '��ƽ��', '11', '��ƽ��', '440511', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('308c6a6603c54c108bab37987d6923ed', '5fe5cbee4e394883b75404f0b700c38f', '515000', null, '婽���', '12', '婽���', '440512', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('049ab5ab620641a096de62f2a59ba41e', '5fe5cbee4e394883b75404f0b700c38f', '515100', null, '������', '13', '������', '440513', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('143b5d01ced94ead9119703027ccdf9e', '5fe5cbee4e394883b75404f0b700c38f', '515100', null, '������', '14', '������', '440514', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('270460c56c554f99ba15a10b3fcf11e4', '5fe5cbee4e394883b75404f0b700c38f', '515800', null, '�κ���', '15', '�κ���', '440515', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6dee25bb1ddc4026ba7edb8ece761adf', '5fe5cbee4e394883b75404f0b700c38f', '515900', null, '�ϰ���', '23', '�ϰ���', '440523', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('055a75197de9416fafc04451cf6851a7', 'a12ecc146129443e9fd9fa29cdf3f4ec', '528000', null, '��Ͻ��', '01', '��Ͻ��', '440601', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6640e7f6896b41e897cd640ada6202ba', 'a12ecc146129443e9fd9fa29cdf3f4ec', '528000', null, '������', '04', '������', '440604', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ef739aaf6a9f48b4b0e634cd09945b68', 'a12ecc146129443e9fd9fa29cdf3f4ec', '528200', null, '�Ϻ���', '05', '�Ϻ���', '440605', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2d2529866d184c6f9a3dd08bfe153732', 'a12ecc146129443e9fd9fa29cdf3f4ec', '528000', null, '˳����', '06', '˳����', '440606', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('addc9604334446b993d58448d527c1cb', 'a12ecc146129443e9fd9fa29cdf3f4ec', '528100', null, '��ˮ��', '07', '��ˮ��', '440607', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0a2b0cba86534ffebfcefc1bdb04f2ed', 'a12ecc146129443e9fd9fa29cdf3f4ec', '528500', null, '������', '08', '������', '440608', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5bb917dc45c842e88eff184385051f28', 'aa148f66edba4d79a319608c3110200c', '529000', null, '��Ͻ��', '01', '��Ͻ��', '440701', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a53e049b97a8414e8e9e0f8902376188', 'aa148f66edba4d79a319608c3110200c', '529000', null, '���', '03', '���', '440703', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c4964403b60b482fa362c9ffbe605353', 'aa148f66edba4d79a319608c3110200c', '529000', null, '������', '04', '������', '440704', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3a1f254f99b04c378a955446e87fee8d', 'aa148f66edba4d79a319608c3110200c', '529100', null, '�»���', '05', '�»���', '440705', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f01d9001825646cdb73f34c322bfc93c', 'aa148f66edba4d79a319608c3110200c', '529200', null, '̨ɽ��', '81', '̨ɽ��', '440781', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d130f0fdfd4245dab05d91d4c20356ce', 'aa148f66edba4d79a319608c3110200c', '529300', null, '��ƽ��', '83', '��ƽ��', '440783', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bc680a2987bd42afa8511726f5c5829d', 'aa148f66edba4d79a319608c3110200c', '529700', null, '��ɽ��', '84', '��ɽ��', '440784', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2bcfe26bf5f54a8aad0722726b6086dd', 'aa148f66edba4d79a319608c3110200c', '529400', null, '��ƽ��', '85', '��ƽ��', '440785', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7df4f22e623642a6a1afa908d866daab', '3a654d6fa00b41afbd9e9eaa5d123224', '524000', null, '��Ͻ��', '01', '��Ͻ��', '440801', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dc508881cdee40d2a8525a3547d91ed7', '3a654d6fa00b41afbd9e9eaa5d123224', '524000', null, '�࿲��', '02', '�࿲��', '440802', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('436116350dea476cb69862da668607ba', '3a654d6fa00b41afbd9e9eaa5d123224', '524000', null, 'ϼɽ��', '03', 'ϼɽ��', '440803', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('eebc9fd889de48f59c21808d280a899c', '3a654d6fa00b41afbd9e9eaa5d123224', '524000', null, '��ͷ��', '04', '��ͷ��', '440804', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5139f398f307426cafccd4ba99ba6f8f', '3a654d6fa00b41afbd9e9eaa5d123224', '524000', null, '������', '11', '������', '440811', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('eaa45447bbbd47eabb21a2cbd9f06c95', '3a654d6fa00b41afbd9e9eaa5d123224', '524300', null, '��Ϫ��', '23', '��Ϫ��', '440823', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d8ef20442436456ca678d9ef813a1105', '3a654d6fa00b41afbd9e9eaa5d123224', '524100', null, '������', '25', '������', '440825', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1e698c6d798d46ee8bf1399363dce837', '3a654d6fa00b41afbd9e9eaa5d123224', '524400', null, '������', '81', '������', '440881', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e916cba9640641b9babe5db615be1a8f', '3a654d6fa00b41afbd9e9eaa5d123224', '524200', null, '������', '82', '������', '440882', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('06aea979192649fe84426f225fb1b4fa', '3a654d6fa00b41afbd9e9eaa5d123224', '524500', null, '�⴨��', '83', '�⴨��', '440883', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0bd3fd3d005b434798eb4a3faecbd8db', 'af7ede8649de428e81c513d1ba57420d', '525000', null, '��Ͻ��', '01', '��Ͻ��', '440901', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('152fa8aab8f54ce5a7c2fdac94c50770', 'af7ede8649de428e81c513d1ba57420d', '525000', null, 'ï����', '02', 'ï����', '440902', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f78424fadcfb439780b9ee62edd3d989', 'af7ede8649de428e81c513d1ba57420d', '525000', null, 'ï����', '03', 'ï����', '440903', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d1b455ad34064abb86c72e10acaff57b', 'af7ede8649de428e81c513d1ba57420d', '525400', null, '�����', '23', '�����', '440923', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('269933afe4ec43abac6fa277ec99f070', 'af7ede8649de428e81c513d1ba57420d', '525200', null, '������', '81', '������', '440981', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ccd44361c1bc44deac4a4005e8aadf0f', 'af7ede8649de428e81c513d1ba57420d', '525100', null, '������', '82', '������', '440982', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('75c874bd1e224f25bf868fcf21a94bc6', 'af7ede8649de428e81c513d1ba57420d', '525300', null, '������', '83', '������', '440983', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6392abc3b52d4039a97a2416daef7896', '6fed53cde91a4336b0b2023c9c7da06b', '526000', null, '��Ͻ��', '01', '��Ͻ��', '441201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ef5afa14740f40eb893fdc7bcc16c714', '6fed53cde91a4336b0b2023c9c7da06b', '526000', null, '������', '02', '������', '441202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a0dc6b6a7804416ab37bbd870a6bafd0', '6fed53cde91a4336b0b2023c9c7da06b', '526000', null, '������', '03', '������', '441203', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('27f12de495374ee3b4708f8cb29f542b', '6fed53cde91a4336b0b2023c9c7da06b', '526300', null, '������', '23', '������', '441223', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7ced0823c5dd427d98832447c387328b', '6fed53cde91a4336b0b2023c9c7da06b', '526400', null, '������', '24', '������', '441224', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9e52bb9d2be84b2f842d8f965fe5840d', '6fed53cde91a4336b0b2023c9c7da06b', '526500', null, '�⿪��', '25', '�⿪��', '441225', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('85f753799e0644d199873724cb80641d', '6fed53cde91a4336b0b2023c9c7da06b', '526600', null, '������', '26', '������', '441226', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b2ee511cc1c344eda2da283792ab8b03', '6fed53cde91a4336b0b2023c9c7da06b', '526100', null, '��Ҫ��', '83', '��Ҫ��', '441283', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dfbb2608e2cd4fa1bcd6266ab4f253e1', '6fed53cde91a4336b0b2023c9c7da06b', '526200', null, '�Ļ���', '84', '�Ļ���', '441284', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('233cf6302f9f4a0b8b9e87c8c17f8cae', '42ed28a2d2cf4a0ab128fdba1486ba90', '516000', null, '��Ͻ��', '01', '��Ͻ��', '441301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8024824cdf9046fe80a9fde7b7ee4a59', '42ed28a2d2cf4a0ab128fdba1486ba90', '516000', null, '�ݳ���', '02', '�ݳ���', '441302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b986afe63a9b47eca551688304b5d5dc', '42ed28a2d2cf4a0ab128fdba1486ba90', '516200', null, '������', '03', '������', '441303', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6735aa16da0f46aba2ab158b1cb43d69', '42ed28a2d2cf4a0ab128fdba1486ba90', '516100', null, '������', '22', '������', '441322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ae88513131ba47d799cc8c6233a43fc9', '42ed28a2d2cf4a0ab128fdba1486ba90', '516300', null, '�ݶ���', '23', '�ݶ���', '441323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('96f53c2d2a4b4511b8559436219ec9a9', 'aded03dddeff43778b7b5b6f0c070a56', '629000', null, '������', '04', '������', '510904', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('12775de9877f4b9db66341c71df5625e', 'aded03dddeff43778b7b5b6f0c070a56', '629100', null, '��Ϫ��', '21', '��Ϫ��', '510921', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('07ff20bb7bab473fbe4caa7413203a10', 'aded03dddeff43778b7b5b6f0c070a56', '629200', null, '�����', '22', '�����', '510922', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bf4a3637bb3d4703b20fbdfcbf3f7d13', 'aded03dddeff43778b7b5b6f0c070a56', '629300', null, '��Ӣ��', '23', '��Ӣ��', '510923', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8325407a5fbd464aa38fe196e8d3d061', '20cbbf557ea1420a960c6e9ce04de1e3', '628000', null, '��Ͻ��', '01', '��Ͻ��', '511001', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('883d3a15891f4295a1392e21823c4c69', '20cbbf557ea1420a960c6e9ce04de1e3', '641000', null, '������', '02', '������', '511002', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('79b24d21ea8845b1aae899d7fdc65787', '20cbbf557ea1420a960c6e9ce04de1e3', '641100', null, '������', '11', '������', '511011', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('68e813baffd84beab1467ea335dac951', '20cbbf557ea1420a960c6e9ce04de1e3', '642450', null, '��Զ��', '24', '��Զ��', '511024', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6f9fd5bed10c432e9cc4ee7347d37a69', '20cbbf557ea1420a960c6e9ce04de1e3', '641200', null, '������', '25', '������', '511025', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0fce11632b394f7ba7ef8b8c60212886', '20cbbf557ea1420a960c6e9ce04de1e3', '642150', null, '¡����', '28', '¡����', '511028', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('da8a422f05e54177bcdab8269989ebfe', '69bd3ecc0d324699ba96fe24b0356639', '614000', null, '��Ͻ��', '01', '��Ͻ��', '511101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ff6aa734f4f14cb5b29fb8d542fd05c9', '69bd3ecc0d324699ba96fe24b0356639', '614000', null, '������', '02', '������', '511102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('945333057e714cd8bb0ca6ae2a7f2227', '69bd3ecc0d324699ba96fe24b0356639', '614900', null, 'ɳ����', '11', 'ɳ����', '511111', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('866620bf5ea346f1a697a5a5a5a2c147', '69bd3ecc0d324699ba96fe24b0356639', '614800', null, '��ͨ����', '12', '��ͨ����', '511112', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b74222b576134120b729e339374ed460', '69bd3ecc0d324699ba96fe24b0356639', '614700', null, '��ں���', '13', '��ں���', '511113', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('de7e9d59e1e440e9a3821cf70200ed4e', '69bd3ecc0d324699ba96fe24b0356639', '614400', null, '��Ϊ��', '23', '��Ϊ��', '511123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c0c9d25e3e884516b7fe27a2f238a553', '69bd3ecc0d324699ba96fe24b0356639', '613100', null, '������', '24', '������', '511124', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5922d3cfccc4416497bbed897dd8b733', '69bd3ecc0d324699ba96fe24b0356639', '614100', null, '�н���', '26', '�н���', '511126', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('be93ea0a6f5b4c879523624a91570c1d', '18d0a9122294487eb4aa8d5d6849ec93', '251800', null, '������', '22', '������', '371622', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('eae251b67baa46e0b2038f93c3ad5b96', '18d0a9122294487eb4aa8d5d6849ec93', '251900', null, '�����', '23', '�����', '371623', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6481fe9c60e847f7b39e3360a3db6a61', '18d0a9122294487eb4aa8d5d6849ec93', '256800', null, 'մ����', '24', 'մ����', '371624', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('49637a466bd644b695eebc25432832ab', '18d0a9122294487eb4aa8d5d6849ec93', '256500', null, '������', '25', '������', '371625', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c33af573b8f24220b001632f47a7522e', '18d0a9122294487eb4aa8d5d6849ec93', '256200', null, '��ƽ��', '26', '��ƽ��', '371626', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0a5e4247a9ec403ea9a33e23959f4555', '3d9e443c24e1403caf4ab9e0e6fd05ed', '274000', null, '��Ͻ��', '01', '��Ͻ��', '371701', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2f412c801e3c4b4ebc133b15892dfa4c', '3d9e443c24e1403caf4ab9e0e6fd05ed', '274000', null, 'ĵ����', '02', 'ĵ����', '371702', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3cd179ce972e4a829a71d6dfc98b06f6', '3d9e443c24e1403caf4ab9e0e6fd05ed', '274400', null, '����', '21', '����', '371721', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0d45fa519d094b28930aa7fa97d8682f', 'ab33919c65c7421e95b62e88c00a809e', '514100', null, '������', '27', '������', '441427', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aededd076498403f9b15c813afec163e', 'ab33919c65c7421e95b62e88c00a809e', '514500', null, '������', '81', '������', '441481', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c91e1af92da74243974e64265c0d67cf', 'bcc33574fca64b2c9936de8b2e69fa53', '516600', null, '��Ͻ��', '01', '��Ͻ��', '441501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('976178d30a764c2ba81e47b68a16f034', 'bcc33574fca64b2c9936de8b2e69fa53', '516600', null, '����', '02', '����', '441502', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7508b5753345474a9500b26c632cc557', 'bcc33574fca64b2c9936de8b2e69fa53', '516400', null, '������', '21', '������', '441521', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('846547059e184db8939861da5ca16d42', 'bcc33574fca64b2c9936de8b2e69fa53', '516700', null, '½����', '23', '½����', '441523', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3a8c48801291486cbad1997805d450a0', 'bcc33574fca64b2c9936de8b2e69fa53', '516500', null, '½����', '81', '½����', '441581', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3fba18166f8b4ffbad5e4a7a69c939ae', '9ab8b8c02064408fb354679975276d42', '517000', null, '��Ͻ��', '01', '��Ͻ��', '441601', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e7ba7d60c1014324ac2d8bd3690879fb', '9ab8b8c02064408fb354679975276d42', '517000', null, 'Դ����', '02', 'Դ����', '441602', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('54ceb3bd497e41c382d69beeb51cc3f0', '9ab8b8c02064408fb354679975276d42', '517400', null, '�Ͻ���', '21', '�Ͻ���', '441621', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('30f23eb909794ecc969a9f1f96620617', '9ab8b8c02064408fb354679975276d42', '517300', null, '������', '22', '������', '441622', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b75edb7091334683923eae15752746cc', '9ab8b8c02064408fb354679975276d42', '517100', null, '��ƽ��', '23', '��ƽ��', '441623', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2a49a354c2c24d75a3e19281737e624d', '9ab8b8c02064408fb354679975276d42', '517200', null, '��ƽ��', '24', '��ƽ��', '441624', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3b113f8d9b96401e9abc729a0674fd2f', '9ab8b8c02064408fb354679975276d42', '517500', null, '��Դ��', '25', '��Դ��', '441625', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5ee6044d2ed44c189034c63474e9d81a', '2bd37a3d909f4620b756803efe2fa5da', '529500', null, '��Ͻ��', '01', '��Ͻ��', '441701', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aaa2675abd344a76a03d8a0f97aa90a7', '2bd37a3d909f4620b756803efe2fa5da', '529500', null, '������', '02', '������', '441702', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8c805581935f4536b07ffbb9f8204328', '2bd37a3d909f4620b756803efe2fa5da', '529800', null, '������', '21', '������', '441721', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9de26af84e214779ab03e867d41321ae', '2bd37a3d909f4620b756803efe2fa5da', '529900', null, '������', '23', '������', '441723', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('186633c6e592494bbb5dacfab7a3d577', '2bd37a3d909f4620b756803efe2fa5da', '529600', null, '������', '81', '������', '441781', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b88227dc06f24c52912b9d072ffdfb29', 'b14f71ca712e4aea9fdb427384026a75', '511500', null, '��Ͻ��', '01', '��Ͻ��', '441801', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dd520cdbe35848c9983c1ab5b3e5e012', 'b14f71ca712e4aea9fdb427384026a75', '511500', null, '�����', '02', '�����', '441802', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8ada70f1576841188e7d91a323f94535', 'b14f71ca712e4aea9fdb427384026a75', '511600', null, '�����', '21', '�����', '441821', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('24a054271f4145b099a6995e16b46c30', 'b14f71ca712e4aea9fdb427384026a75', '513100', null, '��ɽ��', '23', '��ɽ��', '441823', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('37cbe84afe7948a2aa384d1a2614bc74', 'b14f71ca712e4aea9fdb427384026a75', '513200', null, '��ɽ׳������������', '25', '��ɽ׳������������', '441825', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9286c00911924fc1a2bec48bcb96503c', 'b14f71ca712e4aea9fdb427384026a75', '513300', null, '��������������', '26', '��������������', '441826', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1e7c25fbaaf944e9b4f03deb094b8a38', 'b14f71ca712e4aea9fdb427384026a75', '511800', null, '������', '27', '������', '441827', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bde2fbffc4814dc1b2083f816e2b157f', 'b14f71ca712e4aea9fdb427384026a75', '513000', null, 'Ӣ����', '81', 'Ӣ����', '441881', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9cdd8527d60747e3990df8ddbcfffa46', 'b14f71ca712e4aea9fdb427384026a75', '513400', null, '������', '82', '������', '441882', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e12a16496b934d43a4edebddb6f67512', '131cd1bc6ab44780924b517a43ebccbb', '521000', null, '��Ͻ��', '01', '��Ͻ��', '445101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8b50078dbb6b44bfa875ff7ed21f0bb3', '131cd1bc6ab44780924b517a43ebccbb', '521000', null, '������', '02', '������', '445102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('77652ad233284c0cab079bb0965f1a10', '131cd1bc6ab44780924b517a43ebccbb', '515600', null, '������', '21', '������', '445121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6598cbc171ff4ea7b245ffbcad107742', '131cd1bc6ab44780924b517a43ebccbb', '515700', null, '��ƽ��', '22', '��ƽ��', '445122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1fb6b6b9747c46419aeed480d74d9b47', '03a5d1e6c2c24023ba09c41ba51b7b94', '522000', null, '��Ͻ��', '01', '��Ͻ��', '445201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c18e1bd5b1e74dffb661e90074605d21', '03a5d1e6c2c24023ba09c41ba51b7b94', '522000', null, '�ų���', '02', '�ų���', '445202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5c468c13e83d4c1598a340b33fb74948', '03a5d1e6c2c24023ba09c41ba51b7b94', '515500', null, '�Ҷ���', '21', '�Ҷ���', '445221', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dec626c4c15e42b0a05f0c54b783c6b6', '03a5d1e6c2c24023ba09c41ba51b7b94', '515400', null, '������', '22', '������', '445222', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ed2cccade2a048588575cd19138fe68e', '03a5d1e6c2c24023ba09c41ba51b7b94', '515200', null, '������', '24', '������', '445224', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bed84614e302406abe4d12935b3c5bb0', '03a5d1e6c2c24023ba09c41ba51b7b94', '515300', null, '������', '81', '������', '445281', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('371e05d7767542a3babe94be4f7b2851', '42db031cba074ff2a68b68b2eb36e043', '527300', null, '��Ͻ��', '01', '��Ͻ��', '445301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('620f4a78176b4b43b61f08f5b0d88c5a', '42db031cba074ff2a68b68b2eb36e043', '527300', null, '�Ƴ���', '02', '�Ƴ���', '445302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3d05d6ffb7d248718c4279ee2b3a0685', '42db031cba074ff2a68b68b2eb36e043', '527400', null, '������', '21', '������', '445321', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('80809e6ab4a042c2ade86bad0b94f46b', '42db031cba074ff2a68b68b2eb36e043', '527100', null, '������', '22', '������', '445322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('20a1ea083499403b9178ae4e34bc250d', '42db031cba074ff2a68b68b2eb36e043', '527500', null, '�ư���', '23', '�ư���', '445323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e96a97700a054654a7bbc7fa54008905', '42db031cba074ff2a68b68b2eb36e043', '527200', null, '�޶���', '81', '�޶���', '445381', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('75d8561fc96b45f2a912e3638149f9a1', '9e986231263c41118f58c81e9d4cab3b', '530000', null, '��Ͻ��', '01', '��Ͻ��', '450101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4717db6ad9254a81915a0cafb0ed22ca', '9e986231263c41118f58c81e9d4cab3b', '530000', null, '������', '02', '������', '450102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('87b09583ad4741618b50d3d3df0724cc', '9e986231263c41118f58c81e9d4cab3b', '530000', null, '������', '03', '������', '450103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f737f53b2a6343859a65aeec942a3dd5', '9e986231263c41118f58c81e9d4cab3b', '530000', null, '������', '05', '������', '450105', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('24c6d373f5064e909a445a2783c98a9a', '9e986231263c41118f58c81e9d4cab3b', '530000', null, '��������', '07', '��������', '450107', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('872713fcca1a49f389f327e1ef4fd00d', '9e986231263c41118f58c81e9d4cab3b', '530200', null, '������', '08', '������', '450108', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ee5755d99fed47e8b6372f4a91ff8b85', '9e986231263c41118f58c81e9d4cab3b', '530200', null, '������', '09', '������', '450109', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f170426379c144e7a20a93804dc66b52', '9e986231263c41118f58c81e9d4cab3b', '530100', null, '������', '22', '������', '450122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('458a5ad4b3ee472d89ccfbbdc2c40e4d', '9e986231263c41118f58c81e9d4cab3b', '532700', null, '¡����', '23', '¡����', '450123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4783c7b230394b4292b24afb079988a1', '9e986231263c41118f58c81e9d4cab3b', '530600', null, '��ɽ��', '24', '��ɽ��', '450124', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ae77acae787847b5b0106732939a8aca', '9e986231263c41118f58c81e9d4cab3b', '530500', null, '������', '25', '������', '450125', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bec93cde268748f99a5ffadea0070fc0', '9e986231263c41118f58c81e9d4cab3b', '530400', null, '������', '26', '������', '450126', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('20d43724d97d400f8f5349247ce943ce', '9e986231263c41118f58c81e9d4cab3b', '530300', null, '����', '27', '����', '450127', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1c215e5fde5d4e8696717e68e1daf79c', '3a8faee14d6a473493ddf5b00fe9eab7', '545000', null, '��Ͻ��', '01', '��Ͻ��', '450201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8cdd65dd562649828843e094f5c704b4', '3a8faee14d6a473493ddf5b00fe9eab7', '545000', null, '������', '02', '������', '450202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('216fb0c1d591457dac8a1ca703a7b169', '3a8faee14d6a473493ddf5b00fe9eab7', '545000', null, '�����', '03', '�����', '450203', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c67333c30c17480c85f04a330c1645c8', '3a8faee14d6a473493ddf5b00fe9eab7', '545000', null, '������', '04', '������', '450204', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6a09411d8c8245569987a97c49dd59ea', '3a8faee14d6a473493ddf5b00fe9eab7', '545000', null, '������', '05', '������', '450205', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9e2f14fb9fa64614b571484f62f1cda5', '3a8faee14d6a473493ddf5b00fe9eab7', '545100', null, '������', '21', '������', '450221', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('390523f350064019a715f8bafc307b15', '3a8faee14d6a473493ddf5b00fe9eab7', '545200', null, '������', '22', '������', '450222', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5282407c4e5547cfbf95e2f52341ff86', '3a8faee14d6a473493ddf5b00fe9eab7', '545600', null, '¹կ��', '23', '¹կ��', '450223', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b3e9a3481c8e4bd18ea1beef2840736a', 'fd915aadafda49b68d1cf622418a9f82', '425000', null, '��Ͻ��', '01', '��Ͻ��', '431101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('379431aef2814ac08b4d2eee5b67d2bf', '5944b21cd62d4404bab999965db12d4a', '533200', null, '��ҵ��', '28', '��ҵ��', '451028', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('db0b902763a247dfb3bd0ee5935c3af6', '5944b21cd62d4404bab999965db12d4a', '533300', null, '������', '29', '������', '451029', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f2ca34a85a7d4d2d94df0c1683663cb2', '5944b21cd62d4404bab999965db12d4a', '533500', null, '������', '30', '������', '451030', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('59b9e0e2cfcf4c7d9f5db8d65b512bd0', '5944b21cd62d4404bab999965db12d4a', '533500', null, '¡�ָ���������', '31', '¡�ָ���������', '451031', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6c26b3a0932e438bb40354eb76cae1a6', '94e911567a7647aebcaf864e4d2e0e59', '542800', null, '��Ͻ��', '01', '��Ͻ��', '451101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8f88d8be39684316b405ebd27aa2936f', '94e911567a7647aebcaf864e4d2e0e59', '552106', null, '�˲���', '02', '�˲���', '451102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cfddaa6cafed4d788078d75636c171b7', '94e911567a7647aebcaf864e4d2e0e59', '546800', null, '��ƽ��', '21', '��ƽ��', '451121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('18809d5959f941bf9e16d0b2743aed18', '94e911567a7647aebcaf864e4d2e0e59', '542600', null, '��ɽ��', '22', '��ɽ��', '451122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('edf135134eed431c8860a99ae3456bb6', '94e911567a7647aebcaf864e4d2e0e59', '542700', null, '��������������', '23', '��������������', '451123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d27c9d6100f94849a70a817b62870ae4', '241206b6912b4472bc71573bab0a2b1b', '547000', null, '��Ͻ��', '01', '��Ͻ��', '451201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f0921c8a8e0e4961a4599d598e92e824', '241206b6912b4472bc71573bab0a2b1b', '547000', null, '��ǽ���', '02', '��ǽ���', '451202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('01ed9b8d313e490a990dc09939c7b1a4', '241206b6912b4472bc71573bab0a2b1b', '547200', null, '�ϵ���', '21', '�ϵ���', '451221', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e79f3594df3e41d7add2206e4f057131', '241206b6912b4472bc71573bab0a2b1b', '547300', null, '�����', '22', '�����', '451222', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('89df73fa287b4928bc7a919e511eb7ea', '241206b6912b4472bc71573bab0a2b1b', '547600', null, '��ɽ��', '23', '��ɽ��', '451223', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('17c89edba77343228add744b70ad7c80', '241206b6912b4472bc71573bab0a2b1b', '547400', null, '������', '24', '������', '451224', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('08d4715d9a06431ab2d92a4eb7be4d1b', '241206b6912b4472bc71573bab0a2b1b', '546400', null, '�޳�������������', '25', '�޳�������������', '451225', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('17e4ac0925f0475dad446eca659039c2', '241206b6912b4472bc71573bab0a2b1b', '547100', null, '����ë����������', '26', '����ë����������', '451226', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('03f1dbe30a354a7abe79218c55742b95', '241206b6912b4472bc71573bab0a2b1b', '547500', null, '��������������', '27', '��������������', '451227', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('48e39d2595f84afcb611aaf2270f44e6', '241206b6912b4472bc71573bab0a2b1b', '530700', null, '��������������', '28', '��������������', '451228', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('de6578cb0fa44c40972ddd19e428cdd2', '241206b6912b4472bc71573bab0a2b1b', '530800', null, '������������', '29', '������������', '451229', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1e1e768a696b4323b99c37548146c055', '241206b6912b4472bc71573bab0a2b1b', '546300', null, '������', '81', '������', '451281', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e006bb67324e456da3b4662f677fc6ca', 'a3b03952a36b474dbb441c665fab6913', '546100', null, '��Ͻ��', '01', '��Ͻ��', '451301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8f4afbb4e79f416da94ec904e5dbc68a', 'a3b03952a36b474dbb441c665fab6913', '546100', null, '�˱���', '02', '�˱���', '451302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f420b9a8d23c4592ad1afed559e92f01', 'a3b03952a36b474dbb441c665fab6913', '546200', null, '�ó���', '21', '�ó���', '451321', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('471c26f7da1740ae874556d9a21e770d', 'a3b03952a36b474dbb441c665fab6913', '545800', null, '������', '22', '������', '451322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4dfa78b373004f57b7004283e3d56cfc', 'a3b03952a36b474dbb441c665fab6913', '545900', null, '������', '23', '������', '451323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('edb47d77b45141a3969bc5f2c69f754c', 'a3b03952a36b474dbb441c665fab6913', '545700', null, '��������������', '24', '��������������', '451324', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('475370faf40d4fdcb8377c1f29629a34', 'a3b03952a36b474dbb441c665fab6913', '546500', null, '��ɽ��', '81', '��ɽ��', '451381', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bfce033853174939a4ff3b250c74616f', '2e26f89fa8a54d59b4d85abfc42b1ebe', '532200', null, '��Ͻ��', '01', '��Ͻ��', '451401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ba1e209c4f074e91982cdf1316a94a41', '2e26f89fa8a54d59b4d85abfc42b1ebe', '532200', null, '������', '02', '������', '451402', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c47eaa4dfab244d98d13d5f78061ac90', '2e26f89fa8a54d59b4d85abfc42b1ebe', '532100', null, '������', '21', '������', '451421', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b2e19dcc26ab4fe988a41d63f58806fc', '2e26f89fa8a54d59b4d85abfc42b1ebe', '532500', null, '������', '22', '������', '451422', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3dcbd74f0c754541a6b173f1c533d98b', '2e26f89fa8a54d59b4d85abfc42b1ebe', '532400', null, '������', '23', '������', '451423', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b8fd09633d4c451c970eae43522b7cf5', '2e26f89fa8a54d59b4d85abfc42b1ebe', '532300', null, '������', '24', '������', '451424', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('421a3e6680834524bb906d03e6541121', '2e26f89fa8a54d59b4d85abfc42b1ebe', '532800', null, '�����', '25', '�����', '451425', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('72ac42893ac24221952b9fdda32f94f8', '2e26f89fa8a54d59b4d85abfc42b1ebe', '532600', null, 'ƾ����', '81', 'ƾ����', '451481', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e7954db66ef245068d0c392353f5cef9', '64a868e81b664b20b3f818bff115cf71', '570100', null, '��Ͻ��', '01', '��Ͻ��', '460101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('01d930595bd34354b34fe45274926eeb', '64a868e81b664b20b3f818bff115cf71', '570100', null, '��Ӣ��', '05', '��Ӣ��', '460105', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fbde6c2f455a4a35a74602a96947b978', '64a868e81b664b20b3f818bff115cf71', '570100', null, '������', '06', '������', '460106', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e91bb5ddc1244ae9af0fc1ffae7b7bb1', '64a868e81b664b20b3f818bff115cf71', '571100', null, '��ɽ��', '07', '��ɽ��', '460107', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9e121ba679324c8c8e3f00810e535cd4', '64a868e81b664b20b3f818bff115cf71', '570100', null, '������', '08', '������', '460108', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8d6b27c1aae64e2c8bd9b337f621813d', 'e123506da0ff4e6fa019c8fceece5a0e', '572000', null, '������', '01', '������', '460201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('614239e6d46e468295fa4561662a4d91', '97727b55303e4938afa1eb316b3d58cb', '572200', null, '��ָɽ��', '01', '��ָɽ��', '469001', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('933d62384f9f459ead7b387f5ac25ee2', '97727b55303e4938afa1eb316b3d58cb', '571400', null, '������', '02', '������', '469002', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0cc52ab587d14154809c18d6dc4ece27', '97727b55303e4938afa1eb316b3d58cb', '571700', null, '������', '03', '������', '469003', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3ce1c581ab31417495a2f050ce536da3', '97727b55303e4938afa1eb316b3d58cb', '571300', null, '�Ĳ���', '05', '�Ĳ���', '469005', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('13f98fcea86c4e32a4c9765b293b0c60', '97727b55303e4938afa1eb316b3d58cb', '571500', null, '������', '06', '������', '469006', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c3e493cf874e40f58abe00f7022aebd0', '97727b55303e4938afa1eb316b3d58cb', '572600', null, '������', '07', '������', '469007', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('228da0d084684842b799ac2a6c745c14', '97727b55303e4938afa1eb316b3d58cb', '571200', null, '������', '25', '������', '469025', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('415e105942994ab3b2ada4b77fdfb869', '97727b55303e4938afa1eb316b3d58cb', '571600', null, '�Ͳ���', '26', '�Ͳ���', '469026', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4e4a7ac8d8e342f3a34da5436acb3ce3', '97727b55303e4938afa1eb316b3d58cb', '571900', null, '������', '27', '������', '469027', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('09176e6455a943a2ae361b9fda3ae929', '97727b55303e4938afa1eb316b3d58cb', '571800', null, '�ٸ���', '28', '�ٸ���', '469028', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('20e303cecd03405cb39ad5fc64167719', '97727b55303e4938afa1eb316b3d58cb', '572800', null, '��ɳ����������', '30', '��ɳ����������', '469030', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3d46c73dc0a64db0b381894c4a85d80d', '97727b55303e4938afa1eb316b3d58cb', '572700', null, '��������������', '31', '��������������', '469031', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5860e1dc56b94fbebcfce79c5b88e92b', '97727b55303e4938afa1eb316b3d58cb', '572500', null, '�ֶ�����������', '33', '�ֶ�����������', '469033', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('59a8afedbdd74ff6b58570c156434429', '97727b55303e4938afa1eb316b3d58cb', '572400', null, '��ˮ����������', '34', '��ˮ����������', '469034', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1a0603f987fa447a9848fbb2861eae96', '405b1da4465e49088c798360db70c15b', '623000', null, '�봨��', '21', '�봨��', '513221', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('223af1674d984927917cdbcd9c5cf8a3', '6343ca91165140d1bcc25205a1235335', '665800', null, '������������������������', '27', '������������������������', '530827', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('70c71090d8de4319ae543498ba778ebd', '6343ca91165140d1bcc25205a1235335', '665600', null, '����������������', '28', '����������������', '530828', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8fed233292c44e6c8cad265120fed39e', '6343ca91165140d1bcc25205a1235335', '665700', null, '��������������', '29', '��������������', '530829', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5ac6900177d94650877298044fa9a3d5', 'd3ac67eb93544b9da681781f1f27fa5d', '677000', null, '��Ͻ��', '01', '��Ͻ��', '530901', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dbcc63bf30b246b189c2c99509b628af', 'd3ac67eb93544b9da681781f1f27fa5d', '677000', null, '������', '02', '������', '530902', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6e5dcca95af841e0b05ceac33299780c', 'b980cca7c3e04419a36d045d0745ad97', '408000', null, '������', '02', '������', '500102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f8506e918a64434e817dc8f2f3f239b1', '8ebfb0cbf62d4b6597fd742963815e4d', '432200', null, '������', '16', '������', '420116', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0d1d61c4a72e4ac6a033a076e521b01b', 'b980cca7c3e04419a36d045d0745ad97', '400000', null, '��ɿ���', '04', '��ɿ���', '500104', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('96781fbda78942c2a7fc256a56f01a87', 'b980cca7c3e04419a36d045d0745ad97', '400000', null, '������', '05', '������', '500105', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5f63198d59c74dc19efeba9d1645ca1d', 'b980cca7c3e04419a36d045d0745ad97', '400000', null, 'ɳƺ����', '06', 'ɳƺ����', '500106', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5c00f477f3864de796ed4dbb62a4dd6d', 'b980cca7c3e04419a36d045d0745ad97', '400000', null, '��������', '07', '��������', '500107', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('64908b2828874bfea69669f49f648a7b', 'b980cca7c3e04419a36d045d0745ad97', '400000', null, '�ϰ���', '08', '�ϰ���', '500108', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2ef8a387318c4d389d636838a2fa969c', 'b980cca7c3e04419a36d045d0745ad97', '400700', null, '������', '09', '������', '500109', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('829b0077665e4abb818e6329c5f63101', 'b980cca7c3e04419a36d045d0745ad97', '400800', null, '��ʢ��', '10', '��ʢ��', '500110', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6291a65e9b954a5e924732ae4a5636bb', 'b980cca7c3e04419a36d045d0745ad97', '400900', null, '˫����', '11', '˫����', '500111', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('de71fc8ac9fa4b97b30996d62a8be934', 'b980cca7c3e04419a36d045d0745ad97', '401120', null, '�山��', '12', '�山��', '500112', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c28a903c172d41ec9b5b0526cec05ea7', 'b980cca7c3e04419a36d045d0745ad97', '401320', null, '������', '13', '������', '500113', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('250a7ac7496745aeb456ebaa912aeb2b', 'b980cca7c3e04419a36d045d0745ad97', '409000', null, 'ǭ����', '14', 'ǭ����', '500114', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a6703083cead4f47917199a421dedd1e', 'b980cca7c3e04419a36d045d0745ad97', '401220', null, '������', '15', '������', '500115', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c508d23e03f944d0be50e19cae121a1d', 'b980cca7c3e04419a36d045d0745ad97', '401420', null, '�뽭��', '22', '�뽭��', '500222', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('68f8ada610404b458e62faad27c0d3d9', 'b980cca7c3e04419a36d045d0745ad97', '402660', null, '������', '23', '������', '500223', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b86209f930b54ec9a998a64b6cf7d376', 'b980cca7c3e04419a36d045d0745ad97', '402560', null, 'ͭ����', '24', 'ͭ����', '500224', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('72fb4e3e2bba42e596682a5fe9fe3c10', 'b980cca7c3e04419a36d045d0745ad97', '402360', null, '������', '25', '������', '500225', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('147d6ecec09941089f33a6e1459943f4', 'b980cca7c3e04419a36d045d0745ad97', '402460', null, '�ٲ���', '26', '�ٲ���', '500226', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('07881cead92747c498bf38f3ef95e18c', 'b980cca7c3e04419a36d045d0745ad97', '402760', null, '�ɽ��', '27', '�ɽ��', '500227', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a83d1613ae544411b9faca8a4a05c42b', 'b980cca7c3e04419a36d045d0745ad97', '405200', null, '��ƽ��', '28', '��ƽ��', '500228', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e45670a12f0646e29456643ef71a9144', 'b980cca7c3e04419a36d045d0745ad97', '405900', null, '�ǿ���', '29', '�ǿ���', '500229', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ac78924d9c1046e69808ac86e65b8fa9', 'b980cca7c3e04419a36d045d0745ad97', '408200', null, '�ᶼ��', '30', '�ᶼ��', '500230', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cd7b3d36c8ce42a586f077c10a9ff4f8', 'b980cca7c3e04419a36d045d0745ad97', '408300', null, '�潭��', '31', '�潭��', '500231', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0a30d446fed142fdb02a5be8f94dd32b', 'b980cca7c3e04419a36d045d0745ad97', '408500', null, '��¡��', '32', '��¡��', '500232', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4c6079c6d00640fe8b6837f423736344', 'b980cca7c3e04419a36d045d0745ad97', '404300', null, '����', '33', '����', '500233', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('24c24eaa409d49d2b43835dcfbdcdf98', 'b980cca7c3e04419a36d045d0745ad97', '405400', null, '����', '34', '����', '500234', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('839186fe69d540a2a251d25f4a235fcc', 'b980cca7c3e04419a36d045d0745ad97', '404500', null, '������', '35', '������', '500235', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('892098d73a8f4ebbb23b1cd6803603d6', 'b980cca7c3e04419a36d045d0745ad97', '404600', null, '�����', '36', '�����', '500236', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cc7cc24e5ee640bd8c7054655a53be82', 'b980cca7c3e04419a36d045d0745ad97', '404700', null, '��ɽ��', '37', '��ɽ��', '500237', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7e73fd0788384342af6e32a2dd6a6b28', 'b980cca7c3e04419a36d045d0745ad97', '405800', null, '��Ϫ��', '38', '��Ϫ��', '500238', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('955fa50491bd44458ed5548a637194a3', 'b980cca7c3e04419a36d045d0745ad97', '409100', null, 'ʯ��������������', '40', 'ʯ��������������', '500240', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('715cb10d34f34b26b0dea7e32e912df3', 'b980cca7c3e04419a36d045d0745ad97', '409900', null, '��ɽ����������������', '41', '��ɽ����������������', '500241', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3b10892be7a2408da4772f31f4faae8f', 'b980cca7c3e04419a36d045d0745ad97', '409800', null, '��������������������', '42', '��������������������', '500242', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8f752387790d47d78f0e974896275367', 'b980cca7c3e04419a36d045d0745ad97', '409600', null, '��ˮ����������������', '43', '��ˮ����������������', '500243', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c42bd5f596144aa697561b8ad84be828', 'b980cca7c3e04419a36d045d0745ad97', '402260', null, '������', '81', '������', '500381', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('42d9d9fee114439298e5dbd205d1e779', 'b980cca7c3e04419a36d045d0745ad97', '401520', null, '�ϴ���', '82', '�ϴ���', '500382', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0514a6d514c14db09415ef78abe43d15', 'b980cca7c3e04419a36d045d0745ad97', '402160', null, '������', '83', '������', '500383', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5927acb9875f4e2cad054729a569f5e1', 'b980cca7c3e04419a36d045d0745ad97', '408400', null, '�ϴ���', '84', '�ϴ���', '500384', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('20adf85bf7624d2fbdecfc167bc76992', '08cb7a128990421a99d0528514ebe24c', '610000', null, '��Ͻ��', '01', '��Ͻ��', '510101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3abd262e40564bce851bde7c2b91bebb', '08cb7a128990421a99d0528514ebe24c', '610000', null, '������', '04', '������', '510104', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a9b9f2b928454d07bf76c1041695bb32', '08cb7a128990421a99d0528514ebe24c', '610000', null, '������', '05', '������', '510105', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('680eebd4053c44e19f6e333ebbf3d935', '08cb7a128990421a99d0528514ebe24c', '610000', null, '��ţ��', '06', '��ţ��', '510106', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b9dd5622f1a040329bdbd0a0697a612a', '08cb7a128990421a99d0528514ebe24c', '610000', null, '�����', '07', '�����', '510107', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('00efdd3c568740b2918f10e1e52e34d3', '08cb7a128990421a99d0528514ebe24c', '610000', null, '�ɻ���', '08', '�ɻ���', '510108', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1059c980d5cb4402959848e975bf0e5e', '08cb7a128990421a99d0528514ebe24c', '610100', null, '��Ȫ����', '12', '��Ȫ����', '510112', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7aaca4f6ede54bea8549b5c54acf7c5a', '08cb7a128990421a99d0528514ebe24c', '610300', null, '��׽���', '13', '��׽���', '510113', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('52973be04a5b4a90adba519761b180dd', '08cb7a128990421a99d0528514ebe24c', '610500', null, '�¶���', '14', '�¶���', '510114', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('88c11c48570040cda325f727786c7427', '08cb7a128990421a99d0528514ebe24c', '611100', null, '�½���', '15', '�½���', '510115', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('396ffd1aba3a486ba7c8f17eff89ae25', '08cb7a128990421a99d0528514ebe24c', '610400', null, '������', '21', '������', '510121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d3e4663927ff4f1da4ca40d19fcac174', '08cb7a128990421a99d0528514ebe24c', '610200', null, '˫����', '22', '˫����', '510122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bc0a17f3c53a46569ae4c98a456df6a0', '08cb7a128990421a99d0528514ebe24c', '611700', null, 'ۯ��', '24', 'ۯ��', '510124', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('72802af3468844a4b098d8dc1fde8a1d', '08cb7a128990421a99d0528514ebe24c', '611300', null, '������', '29', '������', '510129', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('eb90679961514883b51c5fd86a46505a', '7652787b72664f18bbfd327f363fb188', '637100', null, '��ƺ��', '03', '��ƺ��', '511303', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5f307da3fb9f47e3b8743fd014760282', '7652787b72664f18bbfd327f363fb188', '637500', null, '������', '04', '������', '511304', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6c61c64f94654977a55dc6a30b4d62d3', '7652787b72664f18bbfd327f363fb188', '637300', null, '�ϲ���', '21', '�ϲ���', '511321', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6e85d8a3a84b4b5cb9a8f9396abc1591', '7652787b72664f18bbfd327f363fb188', '637700', null, 'Ӫɽ��', '22', 'Ӫɽ��', '511322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('155a677cce4146059c00ad9e228161b5', '7652787b72664f18bbfd327f363fb188', '637800', null, '���', '23', '���', '511323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0d2623e8cad2483f90fbd0bd994058f0', '7652787b72664f18bbfd327f363fb188', '637600', null, '��¤��', '24', '��¤��', '511324', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ae11b4cef01446fc9a993f056e107522', '7652787b72664f18bbfd327f363fb188', '637200', null, '������', '25', '������', '511325', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5f7fa8f90fa04bacbb59418ef89371e2', '7652787b72664f18bbfd327f363fb188', '637400', null, '������', '81', '������', '511381', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9656ffba20d34f648bb698cbb645f9a9', '8800e3a2c9d24c758e8ef1eea71aa8a4', '620000', null, '��Ͻ��', '01', '��Ͻ��', '511401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a18b664a920945979d80725c50cf9142', '8800e3a2c9d24c758e8ef1eea71aa8a4', '620000', null, '������', '02', '������', '511402', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3d8f1acd325d4428b958a2600a0ba703', '8800e3a2c9d24c758e8ef1eea71aa8a4', '620500', null, '������', '21', '������', '511421', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c675c2838a284141b6bea60342a0e54a', '8800e3a2c9d24c758e8ef1eea71aa8a4', '620800', null, '��ɽ��', '22', '��ɽ��', '511422', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cf33d58e614147c29c3548a32aef0780', '8800e3a2c9d24c758e8ef1eea71aa8a4', '620300', null, '������', '23', '������', '511423', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('95575d7ae2ee41dfa9e06a5cf4343dd7', '8800e3a2c9d24c758e8ef1eea71aa8a4', '620200', null, '������', '24', '������', '511424', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('132b94b80d4d4cbf885c4a6f27ca23ee', '8800e3a2c9d24c758e8ef1eea71aa8a4', '620400', null, '������', '25', '������', '511425', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6a2d24271186443da848d18e1eb7a93c', '199ea73f863b4550982e14d759a1ad27', '644000', null, '��Ͻ��', '01', '��Ͻ��', '511501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bed60141c87a4a52a1c2540b94e7faee', '199ea73f863b4550982e14d759a1ad27', '644000', null, '������', '02', '������', '511502', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6e1a570b46774dc7918be289e6060142', '199ea73f863b4550982e14d759a1ad27', '644600', null, '�˱���', '21', '�˱���', '511521', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e119f8f2bc8640b180822bff8bc04b5f', '199ea73f863b4550982e14d759a1ad27', '644100', null, '��Ϫ��', '22', '��Ϫ��', '511522', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6d2bfecda1544ad1a577ba9da8f8de30', '199ea73f863b4550982e14d759a1ad27', '644200', null, '������', '23', '������', '511523', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6060d6e18c70485ea46cd1dc9c33298a', '199ea73f863b4550982e14d759a1ad27', '644300', null, '������', '24', '������', '511524', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a04a75d3a033495396df4a0aa8b89363', '199ea73f863b4550982e14d759a1ad27', '645150', null, '����', '25', '����', '511525', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('09ce4d69a88b49af80a2294aaa271d5a', '199ea73f863b4550982e14d759a1ad27', '644500', null, '����', '26', '����', '511526', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d0f3abe3552e4ad6b4baa9782231dcf3', '199ea73f863b4550982e14d759a1ad27', '645250', null, '������', '27', '������', '511527', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c2013ba711ee46fbb42286b88f03d328', '199ea73f863b4550982e14d759a1ad27', '644400', null, '������', '28', '������', '511528', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b14e9a6404604540bfb90ef56670baee', '199ea73f863b4550982e14d759a1ad27', '645350', null, '��ɽ��', '29', '��ɽ��', '511529', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('08ea9d56ff644fc2a3014dba14e19590', 'bbf2f2bcf0ec4c41b319bffbcfb51941', '638550', null, '��Ͻ��', '01', '��Ͻ��', '511601', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9cb1f8eba8414bbd805940c4c2585fd7', 'bbf2f2bcf0ec4c41b319bffbcfb51941', '638550', null, '�㰲��', '02', '�㰲��', '511602', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bf7da56ef9ae4cd6a2f4f682dfda011f', 'bbf2f2bcf0ec4c41b319bffbcfb51941', '638300', null, '������', '21', '������', '511621', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('82e53e4759654699baea53c97ce12238', 'bbf2f2bcf0ec4c41b319bffbcfb51941', '638400', null, '��ʤ��', '22', '��ʤ��', '511622', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c861e50a5e7442eea02ec43b603a1fc8', 'bbf2f2bcf0ec4c41b319bffbcfb51941', '638500', null, '��ˮ��', '23', '��ˮ��', '511623', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4b228be59a334996a42e44f7fe53bd8a', 'bbf2f2bcf0ec4c41b319bffbcfb51941', '638600', null, '������', '81', '������', '511681', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f4f37a48fef147d0b0b104d4879619d5', 'c29af6ded1b64cbea4cd6a79fa153e07', '635000', null, '��Ͻ��', '01', '��Ͻ��', '511701', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('284941911e0349da9f25987b38f8ff66', 'c29af6ded1b64cbea4cd6a79fa153e07', '635000', null, 'ͨ����', '02', 'ͨ����', '511702', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5e3f885306c5447f9e62117a4b314c63', 'c29af6ded1b64cbea4cd6a79fa153e07', '635000', null, '����', '21', '����', '511721', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1e954f5acaaf424e9eb6fd121c078cfe', 'c29af6ded1b64cbea4cd6a79fa153e07', '636150', null, '������', '22', '������', '511722', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('05841e7239234c4aa82899b5b3905a69', 'c29af6ded1b64cbea4cd6a79fa153e07', '636250', null, '������', '23', '������', '511723', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('90cb3e43eea9491c88c0c0eaf5bdbd33', 'c29af6ded1b64cbea4cd6a79fa153e07', '635100', null, '������', '24', '������', '511724', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c5d3f52b52f544a4aef575268561cbd1', 'c29af6ded1b64cbea4cd6a79fa153e07', '635200', null, '����', '25', '����', '511725', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('755695c3f2b04e3799cf03a041297a32', 'c29af6ded1b64cbea4cd6a79fa153e07', '636350', null, '��Դ��', '81', '��Դ��', '511781', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7216496d56964d4083775dfe1ec59246', '87081bc7c2b44b4db666df033cb09e88', '625000', null, '��Ͻ��', '01', '��Ͻ��', '511801', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('83e655138a6641d9b07e7985402f1971', '87081bc7c2b44b4db666df033cb09e88', '625000', null, '�����', '02', '�����', '511802', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('94482c78c5f746949583c250091ef1f3', '87081bc7c2b44b4db666df033cb09e88', '625100', null, '��ɽ��', '21', '��ɽ��', '511821', null);
commit;
prompt 2500 records committed...
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('43d3eabeb7a8425e9b2b5a41ce6345b7', '87081bc7c2b44b4db666df033cb09e88', '625200', null, '������', '22', '������', '511822', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7c8be5d840214f159598fb059d8e4ee9', '87081bc7c2b44b4db666df033cb09e88', '625300', null, '��Դ��', '23', '��Դ��', '511823', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8bf5e45b2c1b4de98db7ad0f61a684d1', '87081bc7c2b44b4db666df033cb09e88', '625400', null, 'ʯ����', '24', 'ʯ����', '511824', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c98b6868892c46f7bec7370a7cb21832', '87081bc7c2b44b4db666df033cb09e88', '625500', null, '��ȫ��', '25', '��ȫ��', '511825', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('256770ceaf584b7585688dcb78883592', '87081bc7c2b44b4db666df033cb09e88', '625600', null, '«ɽ��', '26', '«ɽ��', '511826', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c81d7786366d49589fe24bcba050c8e3', '87081bc7c2b44b4db666df033cb09e88', '625700', null, '������', '27', '������', '511827', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aba54eb00fa74fbb920ad9260ff33200', 'b6f3e780164d4fd8b64fbe128d7ca50d', '636600', null, '��Ͻ��', '01', '��Ͻ��', '511901', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3617c49a52ee4021ab6135a6233cd8cf', 'b6f3e780164d4fd8b64fbe128d7ca50d', '636600', null, '������', '02', '������', '511902', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5c2213441a874111b102b2fd5f69a5cc', 'b6f3e780164d4fd8b64fbe128d7ca50d', '636700', null, 'ͨ����', '21', 'ͨ����', '511921', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9f04b95264af47699df6e1783db37401', 'b6f3e780164d4fd8b64fbe128d7ca50d', '635600', null, '�Ͻ���', '22', '�Ͻ���', '511922', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('07373b16e9e943e9a52354fd683230e5', 'b6f3e780164d4fd8b64fbe128d7ca50d', '636400', null, 'ƽ����', '23', 'ƽ����', '511923', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5175c879865e4adeb3a05d5bab63c72d', 'b467acdde0f64c6ba0739ba8b386215d', '641300', null, '��Ͻ��', '01', '��Ͻ��', '512001', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c5c015770b87466d8e363f811a93b17a', 'b467acdde0f64c6ba0739ba8b386215d', '641300', null, '�㽭��', '02', '�㽭��', '512002', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b75cef598c6f4adeb5c9292467d2fb8d', 'b467acdde0f64c6ba0739ba8b386215d', '642350', null, '������', '21', '������', '512021', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('098ac4636844454d80cead48dc531ad2', 'b467acdde0f64c6ba0739ba8b386215d', '641500', null, '������', '22', '������', '512022', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5cf5091b52484a229371ffde7cf8d3f5', 'b467acdde0f64c6ba0739ba8b386215d', '641400', null, '������', '81', '������', '512081', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0b0cceb3448342aba110c47c4611b880', '18d0a9122294487eb4aa8d5d6849ec93', '251700', null, '������', '21', '������', '371621', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('538cdf5712464360b4ce517e3ba0ab3e', '1366c663bb614b20b4d433b637b286ed', '411300', null, '��ɽ��', '82', '��ɽ��', '430382', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2d29a29ec0ba4875ab4a1e5bf09afa29', '4d775ed4147c4a29896d82e845939e8b', '421000', null, '��Ͻ��', '01', '��Ͻ��', '430401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('75ee9a07320d48d1a7f7e4d7ff67528f', '4d775ed4147c4a29896d82e845939e8b', '421000', null, '������', '05', '������', '430405', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8b69865be39e430b845e3d8fc812725e', '4d775ed4147c4a29896d82e845939e8b', '421000', null, '�����', '06', '�����', '430406', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('69ee31c96f8b4a17aa7ec26293875889', '4d775ed4147c4a29896d82e845939e8b', '421000', null, 'ʯ����', '07', 'ʯ����', '430407', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a21180b5a4aa4fdfbcf719627c9ba7d3', '4d775ed4147c4a29896d82e845939e8b', '421000', null, '������', '08', '������', '430408', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('312baba5578e4c7e8dbb47c7cbb28e11', '3d9e443c24e1403caf4ab9e0e6fd05ed', '274300', null, '����', '22', '����', '371722', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6c97b82db9054fcb838c6792e1ae57c0', '3d9e443c24e1403caf4ab9e0e6fd05ed', '274200', null, '������', '23', '������', '371723', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('02736e604469407f91107eb562938a4d', '3d9e443c24e1403caf4ab9e0e6fd05ed', '274900', null, '��Ұ��', '24', '��Ұ��', '371724', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1635c3f8bdf14cee8a1d7b46af640710', '3d9e443c24e1403caf4ab9e0e6fd05ed', '274700', null, '۩����', '25', '۩����', '371725', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('44dbace568954f91a8aad84e9d328267', '3d9e443c24e1403caf4ab9e0e6fd05ed', '274600', null, '۲����', '26', '۲����', '371726', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('874726fd1887404fb24d9a58eeb254db', '3d9e443c24e1403caf4ab9e0e6fd05ed', '274100', null, '������', '27', '������', '371727', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0080511c13f54d3aa1d049ee617d5264', '3d9e443c24e1403caf4ab9e0e6fd05ed', '274500', null, '������', '28', '������', '371728', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c2a7d83cb5ef49b39a5fcf5602b16de1', '849e41d6e28c41eea4e73753ec790c9b', '450000', null, '��Ͻ��', '01', '��Ͻ��', '410101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8f5b09a730414d2c9ccf53d9cd097dfb', '849e41d6e28c41eea4e73753ec790c9b', '450000', null, '��ԭ��', '02', '��ԭ��', '410102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4d1c18e63f4b4f3c8768932d75be6a27', '849e41d6e28c41eea4e73753ec790c9b', '450000', null, '������', '03', '������', '410103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9b0df6b1d2214e49b8c7b053d1263e31', '849e41d6e28c41eea4e73753ec790c9b', '450000', null, '�ܳǻ�����', '04', '�ܳǻ�����', '410104', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('91579189005245f2803bf68309892289', '849e41d6e28c41eea4e73753ec790c9b', '450000', null, '��ˮ��', '05', '��ˮ��', '410105', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f3c65899433d48bfa78f39fd39c51df2', '849e41d6e28c41eea4e73753ec790c9b', '450041', null, '�Ͻ���', '06', '�Ͻ���', '410106', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c2e9db56ffc9490c9f416bb5200664e0', '849e41d6e28c41eea4e73753ec790c9b', '450000', null, '��ɽ��', '08', '��ɽ��', '410108', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9e748fd5a10a4821b81f0623d712d2b6', '849e41d6e28c41eea4e73753ec790c9b', '451450', null, '��Ĳ��', '22', '��Ĳ��', '410122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('755e81b832ed429da095890cd1a7cc50', '849e41d6e28c41eea4e73753ec790c9b', '452100', null, '������', '81', '������', '410181', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f0abcedac6384e5c81c04c6f0dd01467', '849e41d6e28c41eea4e73753ec790c9b', '450100', null, '������', '82', '������', '410182', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5cfa8222098846a4a1aa771e376144e4', '849e41d6e28c41eea4e73753ec790c9b', '452370', null, '������', '83', '������', '410183', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c4fb5abbe345448888f84fc99bff750f', '849e41d6e28c41eea4e73753ec790c9b', '451100', null, '��֣��', '84', '��֣��', '410184', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6c6f7a5af2e142149d58082261b0d31c', '849e41d6e28c41eea4e73753ec790c9b', '452470', null, '�Ƿ���', '85', '�Ƿ���', '410185', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bd965cac5c614810895612d93802cf08', '4dbc4026e4944f11bae16adcae5b5e26', '475000', null, '��Ͻ��', '01', '��Ͻ��', '410201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fa50d1f997cf41b7b6485712ba73460b', '4dbc4026e4944f11bae16adcae5b5e26', '475000', null, '��ͤ��', '02', '��ͤ��', '410202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d20215e258964fdaa98ebfc96073d4a6', '4dbc4026e4944f11bae16adcae5b5e26', '475000', null, '˳�ӻ�����', '03', '˳�ӻ�����', '410203', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f06a2f03ee7d41089c655ca666e0942f', '4dbc4026e4944f11bae16adcae5b5e26', '475000', null, '��¥��', '04', '��¥��', '410204', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c887084caf3042468c1db31b57c009b9', '4dbc4026e4944f11bae16adcae5b5e26', '475000', null, '����̨��', '05', '����̨��', '410205', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9aab3fbe3b81430cab7980ee84f04e5d', '4dbc4026e4944f11bae16adcae5b5e26', '475000', null, '����', '11', '����', '410211', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9b2898dd5c9d4257ae7ec72a0b7181fb', '4dbc4026e4944f11bae16adcae5b5e26', '475200', null, '���', '21', '���', '410221', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ac240726fee74e2697dce860c2879932', '4dbc4026e4944f11bae16adcae5b5e26', '452200', null, 'ͨ����', '22', 'ͨ����', '410222', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fe940ce9a6ae4eb5aff0da5bd0792bd2', '4dbc4026e4944f11bae16adcae5b5e26', '452100', null, 'ξ����', '23', 'ξ����', '410223', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ac5fe03e871b41a1b37c4dd0d408f17f', '4dbc4026e4944f11bae16adcae5b5e26', '475100', null, '������', '24', '������', '410224', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0424e15dbe1d404b9d356ce826e17652', '4dbc4026e4944f11bae16adcae5b5e26', '475300', null, '������', '25', '������', '410225', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('37c88e73a77e4536b6678a7828117d59', 'b62c1ee0eef24fee8b6941a8b36c5a40', '471000', null, '��Ͻ��', '01', '��Ͻ��', '410301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('670d229ff0ec452b823fdaa6e9081ecc', 'b62c1ee0eef24fee8b6941a8b36c5a40', '471000', null, '�ϳ���', '02', '�ϳ���', '410302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e6be1ae2fc0448fba19881304aaa8fd1', 'b62c1ee0eef24fee8b6941a8b36c5a40', '471000', null, '������', '03', '������', '410303', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('569ba0f0c137471b9de02d3b21d8c7cc', 'b62c1ee0eef24fee8b6941a8b36c5a40', '471000', null, '�ܺӻ�����', '04', '�ܺӻ�����', '410304', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a6b652262b4b4d858bd832d5ec8ae836', 'b62c1ee0eef24fee8b6941a8b36c5a40', '471000', null, '������', '05', '������', '410305', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f4da426078d3470fac3b273877ca4fb9', 'b62c1ee0eef24fee8b6941a8b36c5a40', '471000', null, '������', '06', '������', '410306', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e0f6f29cad2c4087a3e3240a93a5ed38', 'b62c1ee0eef24fee8b6941a8b36c5a40', '471000', null, '������', '07', '������', '410307', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7ad84c6bd7644bcd8c020b4e5be6420f', 'b62c1ee0eef24fee8b6941a8b36c5a40', '471100', null, '�Ͻ���', '22', '�Ͻ���', '410322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('933411424296414b8fbb65dcc6968883', 'b62c1ee0eef24fee8b6941a8b36c5a40', '471800', null, '�°���', '23', '�°���', '410323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('44c89197663a45f4ac78f91c0c6ab403', 'b62c1ee0eef24fee8b6941a8b36c5a40', '471500', null, '�ﴨ��', '24', '�ﴨ��', '410324', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('74f197d1537c4ef1bb19217bd6092ac7', 'b62c1ee0eef24fee8b6941a8b36c5a40', '471400', null, '����', '25', '����', '410325', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f473d74c9a90499ebe1649dbd328000e', 'b62c1ee0eef24fee8b6941a8b36c5a40', '471200', null, '������', '26', '������', '410326', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2dd656df64ad4c2e838ff7bb625dcd90', 'b62c1ee0eef24fee8b6941a8b36c5a40', '471600', null, '������', '27', '������', '410327', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dcfbd3a4b1be4a9f8c89b8429ffe2b22', 'b62c1ee0eef24fee8b6941a8b36c5a40', '471700', null, '������', '28', '������', '410328', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('585c5fbe30464b5c89793b83273c0993', 'b62c1ee0eef24fee8b6941a8b36c5a40', '471300', null, '������', '29', '������', '410329', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7925aa0697734516bc2290333af045f5', 'b62c1ee0eef24fee8b6941a8b36c5a40', '471900', null, '��ʦ��', '81', '��ʦ��', '410381', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d9af27507f264ffca87bb96202e9e197', '37804a77ddc4428f948d66acea1d5229', '467000', null, '��Ͻ��', '01', '��Ͻ��', '410401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4cc7e98c7b4b4d78a70ad10beebc8377', '37804a77ddc4428f948d66acea1d5229', '467000', null, '�»���', '02', '�»���', '410402', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('312adee9bab54dea831632f43f4cafcb', '37804a77ddc4428f948d66acea1d5229', '467000', null, '������', '03', '������', '410403', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a016924a05fd45c68afdc788912b0e4d', '37804a77ddc4428f948d66acea1d5229', '467000', null, 'ʯ����', '04', 'ʯ����', '410404', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('04a9cb36c9694da2a7676a80c36f5b24', '37804a77ddc4428f948d66acea1d5229', '467000', null, 'տ����', '11', 'տ����', '410411', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('031b2019d55948d6ae0c6441a87244db', '37804a77ddc4428f948d66acea1d5229', '467400', null, '������', '21', '������', '410421', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cc6f1a9dcd2b4a7cb92207de47fded79', '37804a77ddc4428f948d66acea1d5229', '467200', null, 'Ҷ��', '22', 'Ҷ��', '410422', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c38743421c2241ac943fdbd9a13ffddc', '37804a77ddc4428f948d66acea1d5229', '467300', null, '³ɽ��', '23', '³ɽ��', '410423', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ce15748a0d2e42c08fbdb87df8ea1a98', '37804a77ddc4428f948d66acea1d5229', '467100', null, 'ۣ��', '25', 'ۣ��', '410425', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bf3fb5acfb4b4575adfb4ec1d544d8cc', '37804a77ddc4428f948d66acea1d5229', '462500', null, '�����', '81', '�����', '410481', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('706f086de9614e04a4ea84de3e25aead', '37804a77ddc4428f948d66acea1d5229', '467500', null, '������', '82', '������', '410482', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1271d34bfa5f44d3a7e25b566f7e001d', '521eaa766608414e800f68a916592753', '455000', null, '��Ͻ��', '01', '��Ͻ��', '410501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('263fe265c11e4b788f2d28009eebca4e', '521eaa766608414e800f68a916592753', '455000', null, '�ķ���', '02', '�ķ���', '410502', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d3ff7dee01a64f7a867c5d5b63a059c6', '521eaa766608414e800f68a916592753', '455000', null, '������', '03', '������', '410503', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a3caef56f575459f8c264d1644016d64', '521eaa766608414e800f68a916592753', '455000', null, '����', '05', '����', '410505', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('37139031029f405080ec05c9c434e50f', '521eaa766608414e800f68a916592753', '455000', null, '������', '06', '������', '410506', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0cc212c2e8b64574abdefd86f0f7ec48', '521eaa766608414e800f68a916592753', '455100', null, '������', '22', '������', '410522', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0b7c876c53a74140a8d7c1f1f0b74cd9', '521eaa766608414e800f68a916592753', '456150', null, '������', '23', '������', '410523', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ec74cd6cc08140859e11e7ef617795a9', '521eaa766608414e800f68a916592753', '456400', null, '����', '26', '����', '410526', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c9e1d521c7ae46f8a1338e702a087dfa', '521eaa766608414e800f68a916592753', '456300', null, '�ڻ���', '27', '�ڻ���', '410527', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2098aebeaad14a3c89fdf234de2c4c00', '521eaa766608414e800f68a916592753', '456500', null, '������', '81', '������', '410581', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('283815eef22d4d28a607fe4cf715a594', '1b78854ef6cb4ca9bed5b981062bc3e5', '458000', null, '��Ͻ��', '01', '��Ͻ��', '410601', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('64199bd664f042a593f9d28688117ffa', '1b78854ef6cb4ca9bed5b981062bc3e5', '458000', null, '��ɽ��', '02', '��ɽ��', '410602', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ba25fe4522914aad80a4e5e2f736e67b', '1b78854ef6cb4ca9bed5b981062bc3e5', '458000', null, 'ɽ����', '03', 'ɽ����', '410603', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a186e3d4301244b29190873e6d28418e', '1b78854ef6cb4ca9bed5b981062bc3e5', '458000', null, '俱���', '11', '俱���', '410611', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4fc7b75872e34fd8a302a5c83ee7e789', '1b78854ef6cb4ca9bed5b981062bc3e5', '456250', null, '����', '21', '����', '410621', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('512e0819f6834fad9f0fd49ff062c375', '1b78854ef6cb4ca9bed5b981062bc3e5', '456750', null, '���', '22', '���', '410622', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7fb2ff8d3a6143f1a345066ace3a1c1c', '495e8497aed747bf8741de8bd42abaf7', '422500', null, '�ǲ�����������', '29', '�ǲ�����������', '430529', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c3d94cdbd95b4b40944a4e7af8ef267d', '495e8497aed747bf8741de8bd42abaf7', '422400', null, '�����', '81', '�����', '430581', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('03b3d0a451304f279b25453de0d51d72', '59e5792ffdab44c184a9d653b6be2928', '414000', null, '��Ͻ��', '01', '��Ͻ��', '430601', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5c35a0c05fc2495baaa973d69b45da2c', '59e5792ffdab44c184a9d653b6be2928', '414000', null, '����¥��', '02', '����¥��', '430602', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('23c634ec1ecd4401b67ed7c620f33624', '59e5792ffdab44c184a9d653b6be2928', '414000', null, '��Ϫ��', '03', '��Ϫ��', '430603', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dde95b98b5544bc38a7e1be8a099aa38', '59e5792ffdab44c184a9d653b6be2928', '414000', null, '��ɽ��', '11', '��ɽ��', '430611', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cd23408b555b449195e49b7450772a4c', '59e5792ffdab44c184a9d653b6be2928', '414100', null, '������', '21', '������', '430621', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('60e85b208ae14c568e527a28b0569d85', '59e5792ffdab44c184a9d653b6be2928', '414200', null, '������', '23', '������', '430623', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ebc7ddfa3c614b90a052b5288e0bdedf', '59e5792ffdab44c184a9d653b6be2928', '410500', null, '������', '24', '������', '430624', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4a2fdffd95124281a09a193d28d10f1f', '59e5792ffdab44c184a9d653b6be2928', '410400', null, 'ƽ����', '26', 'ƽ����', '430626', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3505c1c06bcd4c68bee494f19c706d9a', '59e5792ffdab44c184a9d653b6be2928', '414400', null, '������', '81', '������', '430681', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a31206f6102e473a82613e594c5a7122', '59e5792ffdab44c184a9d653b6be2928', '414300', null, '������', '82', '������', '430682', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a37178ab338b457db03ce41cf800c3c4', '3d3589d39ff2453ba5f0cfc69cb129e5', '415000', null, '��Ͻ��', '01', '��Ͻ��', '430701', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4dd2a023760a4b10a71a8ee009e0f368', '3d3589d39ff2453ba5f0cfc69cb129e5', '415000', null, '������', '02', '������', '430702', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('444eb73a01ed46dbaab4debdbee0ed5f', '3d3589d39ff2453ba5f0cfc69cb129e5', '415100', null, '������', '03', '������', '430703', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('96652bfca27b49168ce02524824aa9e4', '3d3589d39ff2453ba5f0cfc69cb129e5', '415600', null, '������', '21', '������', '430721', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c1ec170b4b144c0594e5e388aef60432', '3d3589d39ff2453ba5f0cfc69cb129e5', '415900', null, '������', '22', '������', '430722', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5faaa5e9813c4623ab12def5047ba66d', '3d3589d39ff2453ba5f0cfc69cb129e5', '415500', null, '���', '23', '���', '430723', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b18cf74914e94616ad309b3dabf949fd', '3d3589d39ff2453ba5f0cfc69cb129e5', '415200', null, '�����', '24', '�����', '430724', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('01a10dfd67cc47c682d47a67c19b0d10', '3d3589d39ff2453ba5f0cfc69cb129e5', '415700', null, '��Դ��', '25', '��Դ��', '430725', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7258c671e29d41a1ac52010fab8a4700', '3d3589d39ff2453ba5f0cfc69cb129e5', '415300', null, 'ʯ����', '26', 'ʯ����', '430726', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6721acb12c6343538cb7cb12b3768730', '3d3589d39ff2453ba5f0cfc69cb129e5', '415400', null, '������', '81', '������', '430781', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('42ec076f0dca4b10a5952d1df151f7f8', '0f4a3711d5f4424aabfb4f4be55050da', '427000', null, '��Ͻ��', '01', '��Ͻ��', '430801', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('91a892dca08d4735a35204fe2defbce6', '0f4a3711d5f4424aabfb4f4be55050da', '427000', null, '������', '02', '������', '430802', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('530b1a25b8a64988971dcc98008a279e', '0f4a3711d5f4424aabfb4f4be55050da', '427400', null, '����Դ��', '11', '����Դ��', '430811', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d5cc67161c87418385f16f50b6b7261d', '0f4a3711d5f4424aabfb4f4be55050da', '427200', null, '������', '21', '������', '430821', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aefb63bd86f94d079dcf94b764d1c719', '0f4a3711d5f4424aabfb4f4be55050da', '427100', null, 'ɣֲ��', '22', 'ɣֲ��', '430822', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('12ed7b69eeab49f993d40889d2df2b8b', '4ebefadf49c442bb86cd3047a05b1859', '413000', null, '��Ͻ��', '01', '��Ͻ��', '430901', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('15976c0fbfcd4021bafa1a140a012a4f', '4ebefadf49c442bb86cd3047a05b1859', '413000', null, '������', '02', '������', '430902', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1cfdbabc9bac4b92b83f71ba33549a14', '4ebefadf49c442bb86cd3047a05b1859', '413000', null, '��ɽ��', '03', '��ɽ��', '430903', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0486c9748b6e4b3aa4e51edf20f5e8ff', '4ebefadf49c442bb86cd3047a05b1859', '413200', null, '����', '21', '����', '430921', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a21aaf13aa8a4d129e964614f99c5b67', '4ebefadf49c442bb86cd3047a05b1859', '413400', null, '�ҽ���', '22', '�ҽ���', '430922', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a8cc00950427406cae5ebf8dd36a1f78', '4ebefadf49c442bb86cd3047a05b1859', '413500', null, '������', '23', '������', '430923', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('138edd2ceeca471987345ea912cd2b41', '4ebefadf49c442bb86cd3047a05b1859', '413100', null, '�佭��', '81', '�佭��', '430981', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e228467a2d624ebea81dee8c0b3be8c3', '73cb965eec364b56bd1f37da06615a45', '423000', null, '��Ͻ��', '01', '��Ͻ��', '431001', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('131429f0572145c7abed6b0262190019', '73cb965eec364b56bd1f37da06615a45', '423000', null, '������', '02', '������', '431002', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('502e74582dd549e88dab443bb2384c6a', '73cb965eec364b56bd1f37da06615a45', '423000', null, '������', '03', '������', '431003', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5e90e3f358194d2684684dfc5ed967d1', '73cb965eec364b56bd1f37da06615a45', '424400', null, '������', '21', '������', '431021', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('38296517a2134486afa3156926c706e0', '73cb965eec364b56bd1f37da06615a45', '424200', null, '������', '22', '������', '431022', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('58c6b0ed0d0b4e0286ff171ab5c599ea', '73cb965eec364b56bd1f37da06615a45', '423300', null, '������', '23', '������', '431023', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('48f09f6180e9485f9275041ba6895df4', '73cb965eec364b56bd1f37da06615a45', '424500', null, '�κ���', '24', '�κ���', '431024', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('edd5d19c4d114fd683554d11b7452e77', '73cb965eec364b56bd1f37da06615a45', '424300', null, '������', '25', '������', '431025', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2303a6c2043541e4ab3701693b61b01b', '73cb965eec364b56bd1f37da06615a45', '424100', null, '�����', '26', '�����', '431026', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2afb795ae7d4458ea6a06b473f3fae2d', '73cb965eec364b56bd1f37da06615a45', '423500', null, '����', '27', '����', '431027', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ee5d0607ecff4f8498a155e693826412', '73cb965eec364b56bd1f37da06615a45', '423600', null, '������', '28', '������', '431028', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d936c19c049d41e2ad9d6578d134e61b', '73cb965eec364b56bd1f37da06615a45', '423400', null, '������', '81', '������', '431081', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2952dbccedec40a7be58df6cbb174831', '405b1da4465e49088c798360db70c15b', '624100', null, '����', '26', '����', '513226', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c4d97d245fa04875a8afc58cab8929e9', '82170bdd8f344d218a4c5ac36f7ecba1', '721200', null, '¤��', '27', '¤��', '610327', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d7fe88a69adc423eb7a640cee492272f', '82170bdd8f344d218a4c5ac36f7ecba1', '721100', null, 'ǧ����', '28', 'ǧ����', '610328', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('eb6e957fb3d945838fe188f5fa3c68ce', '82170bdd8f344d218a4c5ac36f7ecba1', '721500', null, '������', '29', '������', '610329', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('370dab2663ea46a18a1b4a80bcf60c88', '82170bdd8f344d218a4c5ac36f7ecba1', '721700', null, '����', '30', '����', '610330', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('87277a45425f49b7978f9712b7315c8f', '82170bdd8f344d218a4c5ac36f7ecba1', '721600', null, '̫����', '31', '̫����', '610331', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3057303699cb4dc2ba30839e23ba8b32', 'a32c87dbe139417e9453b255f5e8245e', '712000', null, '��Ͻ��', '01', '��Ͻ��', '610401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bd36689fbe944c48ae0bf8d38763fa0b', 'a32c87dbe139417e9453b255f5e8245e', '712000', null, '�ض���', '02', '�ض���', '610402', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('52399af1578c46d7a2a6109e9e15b072', 'a32c87dbe139417e9453b255f5e8245e', '712000', null, '������', '03', '������', '610403', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('093b0dd2133b45048e31e54365db5b0b', 'a32c87dbe139417e9453b255f5e8245e', '712000', null, 'μ����', '04', 'μ����', '610404', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f18459a7a7d2440ea27c8910e8433c2d', 'a32c87dbe139417e9453b255f5e8245e', '713800', null, '��ԭ��', '22', '��ԭ��', '610422', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('66991039c6f441e5ab26ba36c2411769', 'a32c87dbe139417e9453b255f5e8245e', '713700', null, '������', '23', '������', '610423', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('29253a814c27478eb905b85fe5ed93d4', 'a32c87dbe139417e9453b255f5e8245e', '713300', null, 'Ǭ��', '24', 'Ǭ��', '610424', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f9e73b5270a04492bff11733cfa0cf55', 'a32c87dbe139417e9453b255f5e8245e', '713200', null, '��Ȫ��', '25', '��Ȫ��', '610425', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('869687060a3c425fbc49e885a37a7459', 'a32c87dbe139417e9453b255f5e8245e', '713400', null, '������', '26', '������', '610426', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('179eb8c378b647209f3be7f0149ee22b', 'a32c87dbe139417e9453b255f5e8245e', '713500', null, '����', '27', '����', '610427', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7573f965eaf24ceaa550b8be7f4b9d4c', 'a32c87dbe139417e9453b255f5e8245e', '713600', null, '������', '28', '������', '610428', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d939ad8d911149de930ee9566a44cd84', 'a32c87dbe139417e9453b255f5e8245e', '711300', null, 'Ѯ����', '29', 'Ѯ����', '610429', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d0b608cffd5243d2ae019f98fc77552c', 'a32c87dbe139417e9453b255f5e8245e', '711200', null, '������', '30', '������', '610430', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a1f23be08c92409a8b00cb37ed357663', 'a32c87dbe139417e9453b255f5e8245e', '712200', null, '�书��', '31', '�书��', '610431', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('eacf6b46ffcb48468c2e71cff101848d', 'a32c87dbe139417e9453b255f5e8245e', '713100', null, '��ƽ��', '81', '��ƽ��', '610481', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('299de3ac762345ab9a65f21e9f917ea8', '1c33dae1d58144d9b2fa688784875771', '714000', null, '��Ͻ��', '01', '��Ͻ��', '610501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('546b3f9f08394750a273040296bcdb55', '1c33dae1d58144d9b2fa688784875771', '714000', null, '��μ��', '02', '��μ��', '610502', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3070cbe345ab45e685db5f6cc29b4ab5', '1c33dae1d58144d9b2fa688784875771', '714100', null, '����', '21', '����', '610521', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('baefd8c799fb496ca047168f6f339df1', '9ade5d5a68fb4caca8f084aee72998ce', '813300', null, '�˺���', '24', '�˺���', '632524', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('19900fac175e42fd8a592e14e4a135b3', '9ade5d5a68fb4caca8f084aee72998ce', '813100', null, '������', '25', '������', '632525', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('138b579c0ac84049b070ac7dc8037596', '7a81f15f33a24260be9be6bd5c899858', '814000', null, '������', '21', '������', '632621', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a997e3d434c743a7be9ab582a9651f1e', '7a81f15f33a24260be9be6bd5c899858', '814300', null, '������', '22', '������', '632622', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9f7ef8043a734592b5c22cd6180da407', '7a81f15f33a24260be9be6bd5c899858', '814100', null, '�ʵ���', '23', '�ʵ���', '632623', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f37e762ef19f487e9af6d589cbe201d4', '7a81f15f33a24260be9be6bd5c899858', '814200', null, '������', '24', '������', '632624', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('be279b8b30dd4ad58b17aacaa4b71c60', '7a81f15f33a24260be9be6bd5c899858', '624700', null, '������', '25', '������', '632625', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('26c7afa1232b497cbd3f7af049f7ee22', '7a81f15f33a24260be9be6bd5c899858', '813500', null, '�����', '26', '�����', '632626', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('03ea1b303c4f4e1097816c14db56cf1e', 'dc53af9d04744ee0a57c7d0bbdf69f22', '815000', null, '������', '21', '������', '632721', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('72f0721e7a8c4e608d7e7196d4cf2154', 'dc53af9d04744ee0a57c7d0bbdf69f22', '815300', null, '�Ӷ���', '22', '�Ӷ���', '632722', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2a00b37a5bc44a9bbabb9fd9c51fa395', 'dc53af9d04744ee0a57c7d0bbdf69f22', '815100', null, '�ƶ���', '23', '�ƶ���', '632723', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('db5bec182cd04939a9b5ac6d8eb181aa', 'dc53af9d04744ee0a57c7d0bbdf69f22', '815400', null, '�ζ���', '24', '�ζ���', '632724', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c8743ca7a4d44b579b1ecb7ff0224394', 'dc53af9d04744ee0a57c7d0bbdf69f22', '815200', null, '��ǫ��', '25', '��ǫ��', '632725', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('599600d515dd40fe8332ee3c2e37efae', 'dc53af9d04744ee0a57c7d0bbdf69f22', '815500', null, '��������', '26', '��������', '632726', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e562ae8efbac405e9611ccad35f1cab0', 'dcd38da0a0c847798551ec50c280dab0', '816000', null, '���ľ��', '01', '���ľ��', '632801', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9686408239fc4129aa92462dccd2b3fe', 'dcd38da0a0c847798551ec50c280dab0', '817000', null, '�������', '02', '�������', '632802', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('04c587f680a14669b87320adcd511bfe', 'dcd38da0a0c847798551ec50c280dab0', '817100', null, '������', '21', '������', '632821', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a6a39d85883449fc81fc4ea95039bdf2', 'dcd38da0a0c847798551ec50c280dab0', '816100', null, '������', '22', '������', '632822', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c5935a8ad67b42b3ac8de2c55d7b064d', 'dcd38da0a0c847798551ec50c280dab0', '817200', null, '�����', '23', '�����', '632823', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f6cc39d3140b4c928d3adb482c22ab24', '70c6e0c0b2e0451f99b6b8986f13ca07', '750000', null, '��Ͻ��', '01', '��Ͻ��', '640101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('910218ffdce945c4b3190cb9ac509887', '70c6e0c0b2e0451f99b6b8986f13ca07', '750000', null, '������', '04', '������', '640104', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c661610a8d1a491d82a29fd5a238c221', '70c6e0c0b2e0451f99b6b8986f13ca07', '750000', null, '������', '05', '������', '640105', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d479d0862e334ecba4472420f6e0ec6a', '70c6e0c0b2e0451f99b6b8986f13ca07', '750000', null, '�����', '06', '�����', '640106', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a5752d7f300848a282f6c35766da1855', '70c6e0c0b2e0451f99b6b8986f13ca07', '750100', null, '������', '21', '������', '640121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('75e872944871493dbd3833a4ce1541ac', '70c6e0c0b2e0451f99b6b8986f13ca07', '750200', null, '������', '22', '������', '640122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b393e4665f3f46bb9d91c84bd47a3d95', '70c6e0c0b2e0451f99b6b8986f13ca07', '751400', null, '������', '81', '������', '640181', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c78daf531ae8482db39055e722550a54', 'd211795dd1e6415294416b8d85e1ca3d', '753000', null, '��Ͻ��', '01', '��Ͻ��', '640201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ca9542ffebf6460685613745898fb7ca', 'd211795dd1e6415294416b8d85e1ca3d', '753000', null, '�������', '02', '�������', '640202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5e690f55e4e441988e0f04963c3926f3', 'd211795dd1e6415294416b8d85e1ca3d', '753600', null, '��ũ��', '05', '��ũ��', '640205', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('239706a272724112b5175121439c3802', 'd211795dd1e6415294416b8d85e1ca3d', '753400', null, 'ƽ����', '21', 'ƽ����', '640221', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('348da6768fb143d1ac7b6afd52ec8939', 'c12b062dc20e422ca8b66e9578738b74', '751100', null, '��Ͻ��', '01', '��Ͻ��', '640301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('01308dcce5824a90b66898b2937f6972', 'c12b062dc20e422ca8b66e9578738b74', '751100', null, '��ͨ��', '02', '��ͨ��', '640302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8fb2c3e61fcc43f3b82876d32d9ca70f', 'c12b062dc20e422ca8b66e9578738b74', '751500', null, '�γ���', '23', '�γ���', '640323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ecc0f38403e042e484399400ca6e56d3', 'c12b062dc20e422ca8b66e9578738b74', '751300', null, 'ͬ����', '24', 'ͬ����', '640324', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('28947a68d06044a5ad771ef502dda3c5', 'c12b062dc20e422ca8b66e9578738b74', '751600', null, '��ͭϿ��', '81', '��ͭϿ��', '640381', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('21b2e738721e4a1c954bf62970b5f123', '48a58b66b9394f1aa364c352ba1da9d3', '756000', null, '��Ͻ��', '01', '��Ͻ��', '640401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('efdec66b2c474637bf5298236d8b8ac2', '48a58b66b9394f1aa364c352ba1da9d3', '756000', null, 'ԭ����', '02', 'ԭ����', '640402', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bb15e44c0755497b97a05859d2f10f5f', '48a58b66b9394f1aa364c352ba1da9d3', '756200', null, '������', '22', '������', '640422', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8d5a5d5ceaff45bcbfd2e19cecacb4b8', '48a58b66b9394f1aa364c352ba1da9d3', '756300', null, '¡����', '23', '¡����', '640423', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9c240d2ef2f8488cbd8520884eee9e26', '3f166d4baf12434eb102caddab35da47', '735000', null, '��Ͻ��', '01', '��Ͻ��', '620901', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8cb42acacc1043a5a10a2bc38af76e34', '3f166d4baf12434eb102caddab35da47', '735000', null, '������', '02', '������', '620902', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('eb215ca9273e4eacb29bcf047a09383d', '3f166d4baf12434eb102caddab35da47', '735300', null, '������', '21', '������', '620921', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('56d428fb84ea469db217106caf502981', '3f166d4baf12434eb102caddab35da47', '736100', null, '������', '22', '������', '620922', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9cbabc3391154396a36f036a1654e748', '3f166d4baf12434eb102caddab35da47', '736400', null, '��������������������', '24', '��������������������', '620924', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('22b0a4b302864a4dadda6ac7d8a9b935', '3f166d4baf12434eb102caddab35da47', '735200', null, '������', '81', '������', '620981', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3220baceed8c44c8b236ef0af13bb24c', '3f166d4baf12434eb102caddab35da47', '736200', null, '�ػ���', '82', '�ػ���', '620982', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('311d354f87834f3e98c8f31d71d537bf', '1c075e3ccaa749859d5b0cecef73e511', '745000', null, '��Ͻ��', '01', '��Ͻ��', '621001', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a32a410d86d44464be8862c35d7dc8a1', '1c075e3ccaa749859d5b0cecef73e511', '745000', null, '������', '02', '������', '621002', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1856831e97c048558c9283477e4ce325', '1c075e3ccaa749859d5b0cecef73e511', '745000', null, '�����', '21', '�����', '621021', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0127a908afab481eb25900ebf797dfe9', '1c075e3ccaa749859d5b0cecef73e511', '745700', null, '����', '22', '����', '621022', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d89948a85fa042609c6bac0fe448ed26', '1c075e3ccaa749859d5b0cecef73e511', '745600', null, '������', '23', '������', '621023', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3b5cf6d4295f404780b5c768a42d0b8b', '1c075e3ccaa749859d5b0cecef73e511', '745400', null, '��ˮ��', '24', '��ˮ��', '621024', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('00695854797846d9950e3b5a6997e3b3', '1c075e3ccaa749859d5b0cecef73e511', '745300', null, '������', '25', '������', '621025', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d42875f0180c487fbaa1a386d264d036', '1c075e3ccaa749859d5b0cecef73e511', '745200', null, '����', '26', '����', '621026', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9d74f731d471488692952f33a066fa45', '1c075e3ccaa749859d5b0cecef73e511', '744500', null, '��ԭ��', '27', '��ԭ��', '621027', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1b5d8797ea4043278b64270c6679620d', '624e4398f3b2409cbbda688316142235', '743000', null, '��Ͻ��', '01', '��Ͻ��', '621101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9c626dd30da84052b7f4abf76b908f2e', '624e4398f3b2409cbbda688316142235', '743000', null, '������', '02', '������', '621102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cad2e01eaf864803b17375bc84367ca0', '624e4398f3b2409cbbda688316142235', '743300', null, 'ͨμ��', '21', 'ͨμ��', '621121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('40c21d6cb65c4de693a2f18577787e10', '624e4398f3b2409cbbda688316142235', '748100', null, '¤����', '22', '¤����', '621122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('971f2a3057c645ea8c3342a69923075c', '624e4398f3b2409cbbda688316142235', '748200', null, 'μԴ��', '23', 'μԴ��', '621123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9d7af5d80f15422ea0edd09dc7484547', '624e4398f3b2409cbbda688316142235', '730500', null, '�����', '24', '�����', '621124', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ab4fa889f7014fb1aea6798236453c38', '624e4398f3b2409cbbda688316142235', '748300', null, '����', '25', '����', '621125', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('26446177ee6a4e5d90c34bb959eafab9', '624e4398f3b2409cbbda688316142235', '748400', null, '���', '26', '���', '621126', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('42f02db10c3543e2a2975fd69e40fc6c', '4032edf9271c4bb8868553b88b39f0a8', '746000', null, '��Ͻ��', '01', '��Ͻ��', '621201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0ea3602d0a04497e9d75b18dff91f920', '4032edf9271c4bb8868553b88b39f0a8', '746000', null, '�䶼��', '02', '�䶼��', '621202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('505cb761b9224ef0b8ce936c9659fa6d', '4032edf9271c4bb8868553b88b39f0a8', '742500', null, '����', '21', '����', '621221', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('256465abcec9432899e3105202ef1c5e', '4032edf9271c4bb8868553b88b39f0a8', '746400', null, '����', '22', '����', '621222', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('72e7bb3fa34d4cb5a67a7f5a3debd5c9', '4032edf9271c4bb8868553b88b39f0a8', '748500', null, '崲���', '23', '崲���', '621223', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f93aebab28e549f9bfb038ad9f27abf1', '4032edf9271c4bb8868553b88b39f0a8', '746500', null, '����', '24', '����', '621224', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('758df5fe692848e7880c208c3295704e', '4032edf9271c4bb8868553b88b39f0a8', '742100', null, '������', '25', '������', '621225', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('07ae07189da24553b2e416c8b3ba1103', '1c33dae1d58144d9b2fa688784875771', '714300', null, '������', '22', '������', '610522', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c573fbb3163e41d1bcb7fedfac4ba34d', '1c33dae1d58144d9b2fa688784875771', '715100', null, '������', '23', '������', '610523', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a4ae2bf113dc484dbbd77a549dac68b5', '1c33dae1d58144d9b2fa688784875771', '715300', null, '������', '24', '������', '610524', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('84806c627fa04f119dd891759aa1123b', '1c33dae1d58144d9b2fa688784875771', '715200', null, '�γ���', '25', '�γ���', '610525', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a2c023433c8f45f484c157528874b11b', '1c33dae1d58144d9b2fa688784875771', '715500', null, '�ѳ���', '26', '�ѳ���', '610526', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d68d23c9c07d43e9b5db5f85ca23236a', '1c33dae1d58144d9b2fa688784875771', '715600', null, '��ˮ��', '27', '��ˮ��', '610527', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fd5bdde24c7644fa943a724a72b0845b', '1c33dae1d58144d9b2fa688784875771', '711700', null, '��ƽ��', '28', '��ƽ��', '610528', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1e622c17c09b47959de9547f8a20e3df', '1c33dae1d58144d9b2fa688784875771', '715400', null, '������', '81', '������', '610581', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dc88dfcbfefe41a5b5fb5f1ea046dd69', '1c33dae1d58144d9b2fa688784875771', '714200', null, '������', '82', '������', '610582', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5999f244872c424a94beeab40981539e', 'a5ab6a40f75e4885a2aa4e6137d9d84d', '716000', null, '��Ͻ��', '01', '��Ͻ��', '610601', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('620c86977ef34145973994d96d7610b5', 'a5ab6a40f75e4885a2aa4e6137d9d84d', '716000', null, '������', '02', '������', '610602', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fd2dacdb1bce4b58a2d74326e261bc1d', 'a5ab6a40f75e4885a2aa4e6137d9d84d', '717100', null, '�ӳ���', '21', '�ӳ���', '610621', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6d7f0f2e46e447a7b17feb4a58d760d0', 'a5ab6a40f75e4885a2aa4e6137d9d84d', '717200', null, '�Ӵ���', '22', '�Ӵ���', '610622', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b69af745fcaf43c0a45cecfdaa8800e7', 'a5ab6a40f75e4885a2aa4e6137d9d84d', '717300', null, '�ӳ���', '23', '�ӳ���', '610623', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d3f0e6983e9f49519ff5b2e33988e565', 'a5ab6a40f75e4885a2aa4e6137d9d84d', '717400', null, '������', '24', '������', '610624', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d2c77ea443f249f6b9015570fd939f9d', 'a5ab6a40f75e4885a2aa4e6137d9d84d', '717500', null, '־����', '25', '־����', '610625', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b824dc02163040468a337e2fdeba2038', 'a5ab6a40f75e4885a2aa4e6137d9d84d', '717600', null, '������', '26', '������', '610626', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('89bab0b08e354aacbbf0b7f6ad8de59f', 'a5ab6a40f75e4885a2aa4e6137d9d84d', '716100', null, '��Ȫ��', '27', '��Ȫ��', '610627', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cabf20b58e28438998dd4654cb497abe', 'a5ab6a40f75e4885a2aa4e6137d9d84d', '727500', null, '����', '28', '����', '610628', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('777ae6d088574e68bc86f5109ea870af', 'a5ab6a40f75e4885a2aa4e6137d9d84d', '727400', null, '�崨��', '29', '�崨��', '610629', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2e69dab521e44379ae6eaf4701d3ad81', 'a5ab6a40f75e4885a2aa4e6137d9d84d', '716200', null, '�˴���', '30', '�˴���', '610630', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f9256bc9ba704f4ba32ac7243da40448', 'a5ab6a40f75e4885a2aa4e6137d9d84d', '715700', null, '������', '31', '������', '610631', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8774828819024a9791cc4fb507c7ba5c', 'a5ab6a40f75e4885a2aa4e6137d9d84d', '727300', null, '������', '32', '������', '610632', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('72d6d06a857f444cb9976957710081b3', '626c4abdd9e94c4c86bc68b3da343b20', '723000', null, '��Ͻ��', '01', '��Ͻ��', '610701', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cfdb1d0633364f6eb37aa4c67f809ded', '626c4abdd9e94c4c86bc68b3da343b20', '723000', null, '��̨��', '02', '��̨��', '610702', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5f18fbb210fa4b23a7a32ab60ab02a94', '626c4abdd9e94c4c86bc68b3da343b20', '723100', null, '��֣��', '21', '��֣��', '610721', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f5dbee4ae52d4e668c1ee9df39f9d3bf', '626c4abdd9e94c4c86bc68b3da343b20', '723200', null, '�ǹ���', '22', '�ǹ���', '610722', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7112c55c8d454b5cb3683ca6943b0e39', '626c4abdd9e94c4c86bc68b3da343b20', '723300', null, '����', '23', '����', '610723', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('70fa44aa28b3459e85949881339cf772', '626c4abdd9e94c4c86bc68b3da343b20', '723500', null, '������', '24', '������', '610724', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6989c5b76c6e4971bf46be6b395cfcc1', '626c4abdd9e94c4c86bc68b3da343b20', '724200', null, '����', '25', '����', '610725', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('edb1d18ee040451297316c901f816b85', '626c4abdd9e94c4c86bc68b3da343b20', '724400', null, '��ǿ��', '26', '��ǿ��', '610726', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bb08f627b8f24adaa5323e395f37f52a', '626c4abdd9e94c4c86bc68b3da343b20', '724300', null, '������', '27', '������', '610727', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('438033a585df428a9e8418074eb63b59', '626c4abdd9e94c4c86bc68b3da343b20', '723600', null, '�����', '28', '�����', '610728', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e7380d794cb9476788dfccd91c27c511', '626c4abdd9e94c4c86bc68b3da343b20', '724100', null, '������', '29', '������', '610729', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('507ca86e0b90463194c9bece1c7680f3', '626c4abdd9e94c4c86bc68b3da343b20', '723400', null, '��ƺ��', '30', '��ƺ��', '610730', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2aa62780e1b34cd680db1f1755945c98', '288c789701ac4bcc8096dd8d85085239', '719000', null, '��Ͻ��', '01', '��Ͻ��', '610801', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('06bf79f9e4a24d0fb0d6cfaae6b1a3c0', '288c789701ac4bcc8096dd8d85085239', '719000', null, '������', '02', '������', '610802', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('74c0fb0fd3de407eae5618e9dab79b67', '288c789701ac4bcc8096dd8d85085239', '719300', null, '��ľ��', '21', '��ľ��', '610821', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1e163f445cd04229b48eee1f029bf03e', '288c789701ac4bcc8096dd8d85085239', '719400', null, '������', '22', '������', '610822', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('520742bd5e3e4c08b55f379cab93cba6', '288c789701ac4bcc8096dd8d85085239', '719200', null, '��ɽ��', '23', '��ɽ��', '610823', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e8256e93ee0d45cfa06fc23f1841ca24', '288c789701ac4bcc8096dd8d85085239', '718500', null, '������', '24', '������', '610824', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e26e0af3d6f84034875ea945b3db649b', '288c789701ac4bcc8096dd8d85085239', '718600', null, '������', '25', '������', '610825', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dfb378e7ebdc460ba017ae424bf3ae4b', '288c789701ac4bcc8096dd8d85085239', '718000', null, '�����', '26', '�����', '610826', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('03a98a896b474fa0997198e7965077ce', '1357e5dfbbf14671863b4ab27f3a49c5', '747100', null, '�ĺ���', '27', '�ĺ���', '623027', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1fb79781085a417c9c228223c7cb78bc', '08f84a76863048f9becb8841b144612a', '810000', null, '��Ͻ��', '01', '��Ͻ��', '630101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f8de63ce72aa4c8b818c2864f2196cfc', '08f84a76863048f9becb8841b144612a', '810000', null, '�Ƕ���', '02', '�Ƕ���', '630102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e2085093da1b4d40a0d13f295e3a94f9', '08f84a76863048f9becb8841b144612a', '810000', null, '������', '03', '������', '630103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aa75037e861645c3affdec0baa452c4d', '08f84a76863048f9becb8841b144612a', '810000', null, '������', '04', '������', '630104', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('217011b76ac74429b7df5d136cb72138', '08f84a76863048f9becb8841b144612a', '810000', null, '�Ǳ���', '05', '�Ǳ���', '630105', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b4b54a5cc9124bcd8c837d08640d8568', '08f84a76863048f9becb8841b144612a', '810100', null, '��ͨ��������������', '21', '��ͨ��������������', '630121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('45ac839d6fdb491e9b4aedff73d2990f', '08f84a76863048f9becb8841b144612a', '811600', null, '������', '22', '������', '630122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2c0fda959a99430c9c8d88f732f989c8', '08f84a76863048f9becb8841b144612a', '812100', null, '��Դ��', '23', '��Դ��', '630123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('68b9bb4fc0b541a5bf6707f3cdce53ea', 'ad6f5e8260eb417b92b45279a5eb6541', '810600', null, 'ƽ����', '21', 'ƽ����', '632121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b54e40e257c8402e9208015e46968a60', 'ad6f5e8260eb417b92b45279a5eb6541', '810800', null, '��ͻ�������������', '22', '��ͻ�������������', '632122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2366c5b945e84fdaa3d1029d86bcb4ee', 'ad6f5e8260eb417b92b45279a5eb6541', '810700', null, '�ֶ���', '23', '�ֶ���', '632123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fd8837e0461543c1bdce0a5387dc87bf', 'ad6f5e8260eb417b92b45279a5eb6541', '810500', null, '��������������', '26', '��������������', '632126', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8d1cb4b4e3a448639da6826ff99176fe', 'ad6f5e8260eb417b92b45279a5eb6541', '810900', null, '��¡����������', '27', '��¡����������', '632127', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1dc841ed61aa4e2589b6c22487a08443', 'ad6f5e8260eb417b92b45279a5eb6541', '811100', null, 'ѭ��������������', '28', 'ѭ��������������', '632128', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cea57ed9968e412d9b4854b56279eeaf', 'a74335d4de524feb84872d0b37ad5a91', '810300', null, '��Դ����������', '21', '��Դ����������', '632221', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2a81d43961f84ec7bbb2c4c643e767bd', 'a74335d4de524feb84872d0b37ad5a91', '810400', null, '������', '22', '������', '632222', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2937afa9b9794fb19ad63bf7c1c03d6f', 'a74335d4de524feb84872d0b37ad5a91', '812200', null, '������', '23', '������', '632223', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('33dbfb2dc9d8436c9fa65ebc60b5a369', 'a74335d4de524feb84872d0b37ad5a91', '812300', null, '�ղ���', '24', '�ղ���', '632224', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('044db706406247eb966b7e22e157b1b9', 'ee978f1e33d6487199681886cb542a85', '811300', null, 'ͬ����', '21', 'ͬ����', '632321', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('232d784c759041b8b4b812485b479fdb', 'ee978f1e33d6487199681886cb542a85', '811200', null, '������', '22', '������', '632322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fff2cad321c44b2791ebfe938c88d068', 'ee978f1e33d6487199681886cb542a85', '811400', null, '�����', '23', '�����', '632323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9ddf07f4973c48d5a8971fad40765b57', 'ee978f1e33d6487199681886cb542a85', '811500', null, '�����ɹ���������', '24', '�����ɹ���������', '632324', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e207724805e84846b144291ace28f492', '9ade5d5a68fb4caca8f084aee72998ce', '813000', null, '������', '21', '������', '632521', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f9943aa6a1944bcd97db9895335446fd', '9ade5d5a68fb4caca8f084aee72998ce', '813200', null, 'ͬ����', '22', 'ͬ����', '632522', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ebee5cec893549f9826c6055c5f45a2b', '9ade5d5a68fb4caca8f084aee72998ce', '811700', null, '�����', '23', '�����', '632523', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('627fe9850a9e454b84b091cd28f1ebc0', '0685a404d9eb48e69f442f0fecd81238', '453000', null, '��Ͻ��', '01', '��Ͻ��', '410701', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cc46dfc69c7c40109ba1eb0939b78900', '0685a404d9eb48e69f442f0fecd81238', '453000', null, '������', '02', '������', '410702', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7ccc5bad0c1a44cfa05e134345bc976b', '0685a404d9eb48e69f442f0fecd81238', '453000', null, '������', '03', '������', '410703', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c67364f999db49829b778e042b932dda', '0685a404d9eb48e69f442f0fecd81238', '453000', null, '��Ȫ��', '04', '��Ȫ��', '410704', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('abcef94beab84801917ca67e9db40196', '0685a404d9eb48e69f442f0fecd81238', '453000', null, '��Ұ��', '11', '��Ұ��', '410711', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6bf8183489034839878e018d437461d7', '0685a404d9eb48e69f442f0fecd81238', '453700', null, '������', '21', '������', '410721', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f71456b5cb15456881fb693db22c1f0c', '0685a404d9eb48e69f442f0fecd81238', '453800', null, '�����', '24', '�����', '410724', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a6e0669420a347fcb60da9302087d994', '0685a404d9eb48e69f442f0fecd81238', '453500', null, 'ԭ����', '25', 'ԭ����', '410725', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b37759a6223a44828140744086c086ef', '0685a404d9eb48e69f442f0fecd81238', '453200', null, '�ӽ���', '26', '�ӽ���', '410726', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f8e36a92cf274fc78cf56b65392f8a5b', '0685a404d9eb48e69f442f0fecd81238', '453300', null, '������', '27', '������', '410727', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e715ead78bc54f6a9697d65fbc4adb02', '0685a404d9eb48e69f442f0fecd81238', '453400', null, '��ԫ��', '28', '��ԫ��', '410728', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('43d5332bac1e4ba0a13cce286336ae89', '0685a404d9eb48e69f442f0fecd81238', '453100', null, '������', '81', '������', '410781', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('daa43bc0bea24ab8b0cff382021fc3cc', '0685a404d9eb48e69f442f0fecd81238', '453600', null, '������', '82', '������', '410782', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9eaa547b08394f60bc844a1c4f96c27b', '2c262f32b8254b56b1e1468a5812ad71', '454150', null, '��Ͻ��', '01', '��Ͻ��', '410801', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0eacb09317b54481b460d8f237df919b', '2c262f32b8254b56b1e1468a5812ad71', '454150', null, '�����', '02', '�����', '410802', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('12f7140819bf4a7695ea63a58cafcb7b', '2c262f32b8254b56b1e1468a5812ad71', '454150', null, '��վ��', '03', '��վ��', '410803', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3c2ccb4614514eb9b4311510f77d3a21', '2c262f32b8254b56b1e1468a5812ad71', '454150', null, '������', '04', '������', '410804', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('52222fb880b4497491782da448a3a4ea', '2c262f32b8254b56b1e1468a5812ad71', '454150', null, 'ɽ����', '11', 'ɽ����', '410811', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a378879ec6f546a5bb81822da53494d0', '2c262f32b8254b56b1e1468a5812ad71', '454350', null, '������', '21', '������', '410821', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dd4539b6324647938984b70772a16805', '2c262f32b8254b56b1e1468a5812ad71', '454450', null, '������', '22', '������', '410822', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9aad1fc520734dfeade07230a5521012', '2c262f32b8254b56b1e1468a5812ad71', '454950', null, '������', '23', '������', '410823', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cad9da9918644880bd8f2d7e33e150cc', '2c262f32b8254b56b1e1468a5812ad71', '454850', null, '����', '25', '����', '410825', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('21782114652b40ce8a8e472521807d74', '2c262f32b8254b56b1e1468a5812ad71', '454650', null, '��Դ��', '81', '��Դ��', '410881', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5b8226c91f2a49af964f39ccb9bf0306', '2c262f32b8254b56b1e1468a5812ad71', '454550', null, '������', '82', '������', '410882', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cd5304e4f8394996988db3ad5b4bc139', '2c262f32b8254b56b1e1468a5812ad71', '454750', null, '������', '83', '������', '410883', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ae57b4b4fc7740b3a31b3c0c339a8119', '620533741dd14610b937e008ac996c69', '457000', null, '��Ͻ��', '01', '��Ͻ��', '410901', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3e6b484117c24bb4ab7742d36e39a853', '620533741dd14610b937e008ac996c69', '457000', null, '������', '02', '������', '410902', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('79b3f9489f43479fa99da95ac09cce16', '620533741dd14610b937e008ac996c69', '457300', null, '�����', '22', '�����', '410922', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2e38dd02adc043e7a972ebe9c0ee5fc5', '620533741dd14610b937e008ac996c69', '457400', null, '������', '23', '������', '410923', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e7f1ab4bc01642418d4327ac462063db', '620533741dd14610b937e008ac996c69', '457500', null, '����', '26', '����', '410926', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3e21b1e4be264dbc89abebb66c16ae8f', '620533741dd14610b937e008ac996c69', '457600', null, '̨ǰ��', '27', '̨ǰ��', '410927', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('15230f6c4d9249cfb643fc027e55f6e9', '620533741dd14610b937e008ac996c69', '457100', null, '�����', '28', '�����', '410928', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('defa811077ed4d15bfbced9c1c7c922d', 'a1c30fcc46124323a7742e62970e5e79', '461000', null, '��Ͻ��', '01', '��Ͻ��', '411001', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7ff8a0c617f9411095261660f9337dfc', 'a1c30fcc46124323a7742e62970e5e79', '461000', null, 'κ����', '02', 'κ����', '411002', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fc0236a7113a4cb4a9735f8af3cdfa69', 'a1c30fcc46124323a7742e62970e5e79', '461100', null, '������', '23', '������', '411023', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4e15ab7845144801ae962166acb0b445', 'a1c30fcc46124323a7742e62970e5e79', '461200', null, '۳����', '24', '۳����', '411024', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a552d661f5f94165b7dda941d8ceac2f', 'a1c30fcc46124323a7742e62970e5e79', '452670', null, '�����', '25', '�����', '411025', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ada70f2ecd204fe9b4c082282e1ca603', 'a1c30fcc46124323a7742e62970e5e79', '452570', null, '������', '81', '������', '411081', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('79a2e4c53db1477695fd108e468820b3', 'a1c30fcc46124323a7742e62970e5e79', '461500', null, '������', '82', '������', '411082', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bba096d481114274b425854b9d18b956', '318e394e38bd4bd38a60562a064fac5d', '462000', null, '��Ͻ��', '01', '��Ͻ��', '411101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ad5a4f58413f4b6a846c43c67eec8408', '318e394e38bd4bd38a60562a064fac5d', '462000', null, 'Դ����', '02', 'Դ����', '411102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b02a7b5359bf4bd0bbb781e2d32288b0', '318e394e38bd4bd38a60562a064fac5d', '462300', null, '۱����', '03', '۱����', '411103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a69eca3092d84095b4450a5bce2a832c', '318e394e38bd4bd38a60562a064fac5d', '462300', null, '������', '04', '������', '411104', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d3e390e5f35e47f1aa017cbd618841da', '318e394e38bd4bd38a60562a064fac5d', '462400', null, '������', '21', '������', '411121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d1794da7fe9741468fe5be119c040a60', '318e394e38bd4bd38a60562a064fac5d', '462600', null, '�����', '22', '�����', '411122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('59750b3c878a4e4ab42466bef9db8ed2', '1d21c167dbe048d7942a636ec4ce172c', '472000', null, '��Ͻ��', '01', '��Ͻ��', '411201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('82866d9cbf08443fa0ee69ea0d7aa58f', '1d21c167dbe048d7942a636ec4ce172c', '472000', null, '������', '02', '������', '411202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5007ea79f0aa49f8b5e2fcd3e63f42f2', '1d21c167dbe048d7942a636ec4ce172c', '472400', null, '�ų���', '21', '�ų���', '411221', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('927b23ea3d6f4ee68262a5748774b874', '1d21c167dbe048d7942a636ec4ce172c', '472100', null, '����', '22', '����', '411222', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('51787a5dc3e24b29b6d113026be770e4', '1d21c167dbe048d7942a636ec4ce172c', '472200', null, '¬����', '24', '¬����', '411224', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('efd151bf7419434a86263e3c5b378f1d', '42ed28a2d2cf4a0ab128fdba1486ba90', '516800', null, '������', '24', '������', '441324', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cd11bda891ee449782078244bfe3390c', 'ab33919c65c7421e95b62e88c00a809e', '514000', null, '��Ͻ��', '01', '��Ͻ��', '441401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b523577fc12d441da50791f6b0b587ac', 'ab33919c65c7421e95b62e88c00a809e', '514000', null, '÷����', '02', '÷����', '441402', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3049b8ea346b4c7eaa93bfaf21c732e0', 'ab33919c65c7421e95b62e88c00a809e', '514700', null, '÷��', '21', '÷��', '441421', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('230acb8648e74633bff10551cc323b21', 'ab33919c65c7421e95b62e88c00a809e', '514200', null, '������', '22', '������', '441422', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fd6ec3535f2b4c79b1e0b49e07471e14', 'ab33919c65c7421e95b62e88c00a809e', '514300', null, '��˳��', '23', '��˳��', '441423', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b9c1b5a33c634b66a41d957d448e3b0d', 'ab33919c65c7421e95b62e88c00a809e', '514400', null, '�廪��', '24', '�廪��', '441424', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('40cd67310d3849e7a30fa425c9fd7d10', 'ab33919c65c7421e95b62e88c00a809e', '514600', null, 'ƽԶ��', '26', 'ƽԶ��', '441426', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0a991977c0134d8e805b9a881f2fa651', 'e16c41e4d0b5480786a44c95e8fc7a5c', '473200', null, '������', '22', '������', '411322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d1afb5a8b7664c46b1ee0cbb81f4dd50', 'e16c41e4d0b5480786a44c95e8fc7a5c', '474550', null, '��Ͽ��', '23', '��Ͽ��', '411323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('67a20c140f034572a07f49d64eb1db23', 'e16c41e4d0b5480786a44c95e8fc7a5c', '474250', null, '��ƽ��', '24', '��ƽ��', '411324', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5d6f32efd081471d80b6f708b5456b9d', 'e16c41e4d0b5480786a44c95e8fc7a5c', '474350', null, '������', '25', '������', '411325', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3c852d5611b64704ad146504e9756bc3', 'e16c41e4d0b5480786a44c95e8fc7a5c', '474450', null, '������', '26', '������', '411326', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a20ba80813094c9fba76ffd912a04443', 'e16c41e4d0b5480786a44c95e8fc7a5c', '473300', null, '������', '27', '������', '411327', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('06f6842f262d4a30a0d2f54e4bae0fe2', 'e16c41e4d0b5480786a44c95e8fc7a5c', '473400', null, '�ƺ���', '28', '�ƺ���', '411328', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d5d5628d742a4576830ce6081348f599', 'e16c41e4d0b5480786a44c95e8fc7a5c', '473500', null, '��Ұ��', '29', '��Ұ��', '411329', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3cde7daccc964542b5930bde9e9b78ff', 'e16c41e4d0b5480786a44c95e8fc7a5c', '474750', null, 'ͩ����', '30', 'ͩ����', '411330', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('11c4330b93404e728a1431ee160a2292', 'e16c41e4d0b5480786a44c95e8fc7a5c', '474150', null, '������', '81', '������', '411381', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e831ea3c03a741718d0309aa36525c36', '815767f8485d4636acbaa4eae0b5234c', '476000', null, '��Ͻ��', '01', '��Ͻ��', '411401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bb9b31da120e48818e9b2f407332d0b3', '815767f8485d4636acbaa4eae0b5234c', '476000', null, '��԰��', '02', '��԰��', '411402', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c510bd2c6bc94b2a92e837b178496c42', '815767f8485d4636acbaa4eae0b5234c', '476000', null, '�����', '03', '�����', '411403', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c171b2c802c542eaa367d85344a16c37', '815767f8485d4636acbaa4eae0b5234c', '476800', null, '��Ȩ��', '21', '��Ȩ��', '411421', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('874a01cd196d483c95d60443735c1ab1', '815767f8485d4636acbaa4eae0b5234c', '476900', null, '���', '22', '���', '411422', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3d7077cb35114c01982ff6df92e94c8c', '815767f8485d4636acbaa4eae0b5234c', '476700', null, '������', '23', '������', '411423', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('692a90b6c42a4ed591428efa28cc5c4e', '815767f8485d4636acbaa4eae0b5234c', '476200', null, '�ϳ���', '24', '�ϳ���', '411424', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('66c16a07d55a4f869538f469365bd769', '815767f8485d4636acbaa4eae0b5234c', '476300', null, '�ݳ���', '25', '�ݳ���', '411425', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5d117edc799b4823a7e6e279d048cb41', '815767f8485d4636acbaa4eae0b5234c', '476400', null, '������', '26', '������', '411426', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cd14213e5a904adf910fc5ea73efb372', '815767f8485d4636acbaa4eae0b5234c', '476600', null, '������', '81', '������', '411481', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6d0d4a2d8370449f8385a0c59f7970d1', '380f7b3b49a44eae82cfa793b89dbb92', '464000', null, '��Ͻ��', '01', '��Ͻ��', '411501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2cac7b6d987241a0b0e8a2b37bc7d913', '380f7b3b49a44eae82cfa793b89dbb92', '464000', null, '������', '02', '������', '411502', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c83362172fad4a3b8b61413b0352a40e', '380f7b3b49a44eae82cfa793b89dbb92', '464000', null, 'ƽ����', '03', 'ƽ����', '411503', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9da6e36704fe41e4aa7937489f9f313b', '380f7b3b49a44eae82cfa793b89dbb92', '464200', null, '��ɽ��', '21', '��ɽ��', '411521', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7d7cbb09313f479da1b52c26e8b01c92', '380f7b3b49a44eae82cfa793b89dbb92', '465450', null, '��ɽ��', '22', '��ɽ��', '411522', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3585c8ecd9f44cafb7a30e29737f5db3', '380f7b3b49a44eae82cfa793b89dbb92', '465500', null, '����', '23', '����', '411523', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('eb5ad59d88394b6f8fe16b4407ffd175', '380f7b3b49a44eae82cfa793b89dbb92', '465350', null, '�̳���', '24', '�̳���', '411524', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fdc243529d5d4a4f9dfed588297d3644', '380f7b3b49a44eae82cfa793b89dbb92', '465200', null, '��ʼ��', '25', '��ʼ��', '411525', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8b1095bb0eb7489bae13755ee0ed6446', '380f7b3b49a44eae82cfa793b89dbb92', '465150', null, '�괨��', '26', '�괨��', '411526', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5538bdb7e5154b92843f7ff31c0ae25f', '380f7b3b49a44eae82cfa793b89dbb92', '464400', null, '������', '27', '������', '411527', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ad9e362c8a9645d8bc208b7d6e33a83a', '380f7b3b49a44eae82cfa793b89dbb92', '464300', null, 'Ϣ��', '28', 'Ϣ��', '411528', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2a50082f2f274fa695b3ed19322ed210', '78e3f9f0494b42a789f858385a476d44', '466000', null, '��Ͻ��', '01', '��Ͻ��', '411601', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('36bd86d061fa4dd0a86bd3dde506b232', '78e3f9f0494b42a789f858385a476d44', '466000', null, '������', '02', '������', '411602', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('674b2662846d481e90b51ef443a7ad3b', '78e3f9f0494b42a789f858385a476d44', '461300', null, '������', '21', '������', '411621', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ddda97e4d22d43a9aa98ad5d23b9a5b3', '78e3f9f0494b42a789f858385a476d44', '466600', null, '������', '22', '������', '411622', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('64aeada02ee3430ab560c3c094c55d24', '78e3f9f0494b42a789f858385a476d44', '466100', null, '��ˮ��', '23', '��ˮ��', '411623', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9b4200044a75420a89728754a21631ef', '78e3f9f0494b42a789f858385a476d44', '466300', null, '������', '24', '������', '411624', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4b4acb1b308e42eba11182837eca44bc', '78e3f9f0494b42a789f858385a476d44', '477150', null, '������', '25', '������', '411625', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3d62f7d22dc748cd82cf7e62b3c120f0', '78e3f9f0494b42a789f858385a476d44', '466700', null, '������', '26', '������', '411626', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2ed6e775d6044ee980c07685728011b4', '78e3f9f0494b42a789f858385a476d44', '475400', null, '̫����', '27', '̫����', '411627', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('32daeda3f9aa4feca8f02f2b7905ad5f', '78e3f9f0494b42a789f858385a476d44', '477200', null, '¹����', '28', '¹����', '411628', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2ea83d1928e04e2a8d4d714085add868', '78e3f9f0494b42a789f858385a476d44', '466200', null, '�����', '81', '�����', '411681', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6c500a16fcc54051af9c2ec7c80ab1a4', '7d42b8147aa24128ae7315db8d920b90', '463000', null, '��Ͻ��', '01', '��Ͻ��', '411701', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b45b54bbf14a438898bdf3878341364f', '7d42b8147aa24128ae7315db8d920b90', '463000', null, '�����', '02', '�����', '411702', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d2c35ee42b62486cb2ab72aa18786a52', '7d42b8147aa24128ae7315db8d920b90', '463900', null, '��ƽ��', '21', '��ƽ��', '411721', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a5bcaafefc0e4ea8bd313b7a3def08c9', '7d42b8147aa24128ae7315db8d920b90', '463800', null, '�ϲ���', '22', '�ϲ���', '411722', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cc4ed21705db42708ee6a5e7a57a7d24', '7d42b8147aa24128ae7315db8d920b90', '463400', null, 'ƽ����', '23', 'ƽ����', '411723', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bde7a69d58e146df8ac57f10009740a7', '7d42b8147aa24128ae7315db8d920b90', '463600', null, '������', '24', '������', '411724', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4e6a9bf2a3ab4ec68411c93c3f395c82', '7d42b8147aa24128ae7315db8d920b90', '463200', null, 'ȷɽ��', '25', 'ȷɽ��', '411725', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a1ef1f8299154528bdd66251a56de8f0', '7d42b8147aa24128ae7315db8d920b90', '463700', null, '������', '26', '������', '411726', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('93b22266400940819ff8cf3ca2937fe3', '7d42b8147aa24128ae7315db8d920b90', '463300', null, '������', '27', '������', '411727', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7bebd11cb31d47cc8c424c55c3b0f021', '7d42b8147aa24128ae7315db8d920b90', '463100', null, '��ƽ��', '28', '��ƽ��', '411728', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('209fc2b73d2b4ffd8b2b52f82c48e49a', '7d42b8147aa24128ae7315db8d920b90', '463500', null, '�²���', '29', '�²���', '411729', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6ecb154d043f443988ebcc11c96d6b63', '8ebfb0cbf62d4b6597fd742963815e4d', '430000', null, '��Ͻ��', '01', '��Ͻ��', '420101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f41ff795d550492d85192a21e34d73f5', '8ebfb0cbf62d4b6597fd742963815e4d', '430014', null, '������', '02', '������', '420102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a73e4206e74c46479194132cf7f1da18', '8ebfb0cbf62d4b6597fd742963815e4d', '430000', null, '������', '03', '������', '420103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('65c6c0fced6d4fd38e6b7f47dff5a07a', '8ebfb0cbf62d4b6597fd742963815e4d', '430000', null, '�ǿ���', '04', '�ǿ���', '420104', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fe3efa5bd1c944ad9e3dfd95ef96869b', '8ebfb0cbf62d4b6597fd742963815e4d', '430050', null, '������', '05', '������', '420105', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fc0ea42771b84394acd36ed9db5bb692', '8ebfb0cbf62d4b6597fd742963815e4d', '430000', null, '�����', '06', '�����', '420106', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('62a0f0800b4c472ebce75acf4fdb8bd2', '8ebfb0cbf62d4b6597fd742963815e4d', '430080', null, '��ɽ��', '07', '��ɽ��', '420107', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5f498a1f2e0442d88bc4b242a515d208', '8ebfb0cbf62d4b6597fd742963815e4d', '430070', null, '��ɽ��', '11', '��ɽ��', '420111', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b2bd97484c7349df867ceca46d333972', '8ebfb0cbf62d4b6597fd742963815e4d', '430040', null, '��������', '12', '��������', '420112', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0f334a4ecdde4a758aa7245a3098d06a', '8ebfb0cbf62d4b6597fd742963815e4d', '430090', null, '������', '13', '������', '420113', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('203cd64b409a4350a9044097a7d6195a', '8ebfb0cbf62d4b6597fd742963815e4d', '430100', null, '�̵���', '14', '�̵���', '420114', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8645987ac0b84312b27768248a8a3171', '8ebfb0cbf62d4b6597fd742963815e4d', '430200', null, '������', '15', '������', '420115', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('347d1446472a44a78b109a11f88f02f6', 'b980cca7c3e04419a36d045d0745ad97', '400000', null, '������', '03', '������', '500103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4ff76e7fd2714e37a3d7c2d560f5d258', '4a6dbf97ae1346a1ae058aada593c83b', '433200', null, '�����', '83', '�����', '421083', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('313f07f205b748fa8e45b52a5e58dc66', '4a6dbf97ae1346a1ae058aada593c83b', '434200', null, '������', '87', '������', '421087', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0d6eb51d3523468d845200c0c588b0d4', '88495c56c828443ca445e66c6d99e988', '438000', null, '��Ͻ��', '01', '��Ͻ��', '421101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('06ffccaeb4ab4a74a5eb2ec96019cecc', '88495c56c828443ca445e66c6d99e988', '438000', null, '������', '02', '������', '421102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6764cdf9849446caade8be656b270c5e', '88495c56c828443ca445e66c6d99e988', '438000', null, '�ŷ���', '21', '�ŷ���', '421121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('16b5572e23184a99ae67f15e8de6e496', '88495c56c828443ca445e66c6d99e988', '438400', null, '�찲��', '22', '�찲��', '421122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('61b094d50ff74ffcb3672dff4429fd39', '88495c56c828443ca445e66c6d99e988', '438600', null, '������', '23', '������', '421123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9fc6214afea74352a40e54d31adf44fe', '88495c56c828443ca445e66c6d99e988', '438700', null, 'Ӣɽ��', '24', 'Ӣɽ��', '421124', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('64a63e7fd4bf4abc8417ae7ef3b9d2e1', '88495c56c828443ca445e66c6d99e988', '438200', null, '�ˮ��', '25', '�ˮ��', '421125', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('56398f4d372942febde954f4c9d8ed7d', '88495c56c828443ca445e66c6d99e988', '435300', null, 'ޭ����', '26', 'ޭ����', '421126', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cba22fcd9aee47fc9187c66ce44b6b7c', '88495c56c828443ca445e66c6d99e988', '435500', null, '��÷��', '27', '��÷��', '421127', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('251d64f7b5fc4f9c89afc09e8bc492d3', '88495c56c828443ca445e66c6d99e988', '438300', null, '�����', '81', '�����', '421181', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b5a38e86a4c44d59afcefd8d17030b28', '88495c56c828443ca445e66c6d99e988', '435400', null, '��Ѩ��', '82', '��Ѩ��', '421182', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0bcd47c95f4a4c089642852847e0adc8', '5cbfd640dd1e427e93dee73568bc7f41', '437000', null, '��Ͻ��', '01', '��Ͻ��', '421201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bc65d98dbcb946b3b5863cbdc933e5a7', '5cbfd640dd1e427e93dee73568bc7f41', '437000', null, '�̰���', '02', '�̰���', '421202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4dcfbe31425d4acf812e33705381ed90', '5cbfd640dd1e427e93dee73568bc7f41', '437200', null, '������', '21', '������', '421221', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('991c767d38d04cc1bcd9b2157f967f78', '5cbfd640dd1e427e93dee73568bc7f41', '437400', null, 'ͨ����', '22', 'ͨ����', '421222', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('db30bfdbbb1c4217a9decb1c867eddb1', '5cbfd640dd1e427e93dee73568bc7f41', '437500', null, '������', '23', '������', '421223', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ec82203eb0894a608a5e63c1b93c2456', '5cbfd640dd1e427e93dee73568bc7f41', '437600', null, 'ͨɽ��', '24', 'ͨɽ��', '421224', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cd77ac7bc87a43769f001d5a3fbb18d9', '5cbfd640dd1e427e93dee73568bc7f41', '437300', null, '�����', '81', '�����', '421281', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f76dc456d6eb4ee18c4b6da982f9896b', 'a2c47feb005a467e83020bce64d6a551', '441300', null, '��Ͻ��', '01', '��Ͻ��', '421301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d6da48a16fa74dc7a33626528f72dc18', 'a2c47feb005a467e83020bce64d6a551', '441300', null, '������', '02', '������', '421302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9bad45a7821c49938e0650a8492abcac', 'a2c47feb005a467e83020bce64d6a551', '432700', null, '��ˮ��', '81', '��ˮ��', '421381', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('25ae2c48713c468daa3ebbd10b25642a', 'ede3533eebc24764a993c9c8b694c26b', '445000', null, '��ʩ��', '01', '��ʩ��', '422801', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ae84e8ef67e4477689dc93b154889c72', 'ede3533eebc24764a993c9c8b694c26b', '445400', null, '������', '02', '������', '422802', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d4b29db13a1049c29ff66919dbf1c386', 'ede3533eebc24764a993c9c8b694c26b', '445300', null, '��ʼ��', '22', '��ʼ��', '422822', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b648e2f598bc4fce8076320b673db1c5', 'ede3533eebc24764a993c9c8b694c26b', '444300', null, '�Ͷ���', '23', '�Ͷ���', '422823', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('34ed77a968f043e8986d37904af5653b', 'ede3533eebc24764a993c9c8b694c26b', '445500', null, '������', '25', '������', '422825', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('14fd2eddc3fb45f185c75fa944828eab', 'ede3533eebc24764a993c9c8b694c26b', '445600', null, '�̷���', '26', '�̷���', '422826', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b032a195f0944a2a84db80194162ced2', 'ede3533eebc24764a993c9c8b694c26b', '445700', null, '������', '27', '������', '422827', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('37426d7ea7584f6e9b250ccdb24cdd4f', 'ede3533eebc24764a993c9c8b694c26b', '445800', null, '�׷���', '28', '�׷���', '422828', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9689b5f941bd49d186ce5bed8f622ab9', '368a7e6a7a6f409e8af26a6793354867', '433000', null, '������', '04', '������', '429004', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('907553f05e1e41e1819bd09cc1cf7500', '368a7e6a7a6f409e8af26a6793354867', '433100', null, 'Ǳ����', '05', 'Ǳ����', '429005', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0dfb32969d804c3fba2ef950364b2b9c', '368a7e6a7a6f409e8af26a6793354867', '431700', null, '������', '06', '������', '429006', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('801699780424488cb58f9a06c424832c', '368a7e6a7a6f409e8af26a6793354867', '442400', null, '��ũ������', '21', '��ũ������', '429021', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fab2b235d58b4a3c8a9d85a70e3d3e95', '1e44bd32e5c84c3c852e00f22f6fda95', '410000', null, '��Ͻ��', '01', '��Ͻ��', '430101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a61d8982562b419787903edd39fa5cbf', '1e44bd32e5c84c3c852e00f22f6fda95', '410000', null, 'ܽ����', '02', 'ܽ����', '430102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b69a5af3ab9b43f485496d5204ea05b7', '1e44bd32e5c84c3c852e00f22f6fda95', '410000', null, '������', '03', '������', '430103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('32ec7a5d09d840d98ff959cba36367fb', '1e44bd32e5c84c3c852e00f22f6fda95', '410000', null, '��´��', '04', '��´��', '430104', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0cb4f00ce41c42b38187107fb380894f', '1e44bd32e5c84c3c852e00f22f6fda95', '410000', null, '������', '05', '������', '430105', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a8ac689984f742f28cb2ef1270f05de1', '1e44bd32e5c84c3c852e00f22f6fda95', '410000', null, '�껨��', '11', '�껨��', '430111', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dcf30abcae89450cb0a169eaa41a1b34', '1e44bd32e5c84c3c852e00f22f6fda95', '410100', null, '��ɳ��', '21', '��ɳ��', '430121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('55019f6d327c47e48e128e072484c562', '1e44bd32e5c84c3c852e00f22f6fda95', '410200', null, '������', '22', '������', '430122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('732ca780a88f409fbda73b6632e6493a', '1e44bd32e5c84c3c852e00f22f6fda95', '410600', null, '������', '24', '������', '430124', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6c824b15994447d284eba60cac9d1ce3', '1e44bd32e5c84c3c852e00f22f6fda95', '410300', null, '�����', '81', '�����', '430181', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('daa97d7fe5974e61b6235a2c072a2f61', '1cb06b4200264436a52680229df355c0', '412000', null, '��Ͻ��', '01', '��Ͻ��', '430201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2e859e8efaf845a08bf495437fbe14ec', '1cb06b4200264436a52680229df355c0', '412000', null, '������', '02', '������', '430202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8f7cce3da17f4ac29f9892d94da90fc1', '1cb06b4200264436a52680229df355c0', '412000', null, '«����', '03', '«����', '430203', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('76ee4292dc2743539544c960ddc567a4', '1cb06b4200264436a52680229df355c0', '412000', null, 'ʯ����', '04', 'ʯ����', '430204', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1e4ee90794fa4ab78152a91fe7727eb5', '1cb06b4200264436a52680229df355c0', '412000', null, '��Ԫ��', '11', '��Ԫ��', '430211', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a2a302736eda481caca464013867e5a3', '1cb06b4200264436a52680229df355c0', '412000', null, '������', '21', '������', '430221', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('895120fc425a496cb80ea3ad6d98bbca', '1cb06b4200264436a52680229df355c0', '412300', null, '����', '23', '����', '430223', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('de692d8095e3458abeeb382a1720ab32', '1cb06b4200264436a52680229df355c0', '412400', null, '������', '24', '������', '430224', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b696f491e217442a8ffbf7200984b446', '1cb06b4200264436a52680229df355c0', '412500', null, '������', '25', '������', '430225', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('457c00962b4f484b8667c0259389c337', '1cb06b4200264436a52680229df355c0', '412200', null, '������', '81', '������', '430281', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fab6c80092e04911870d8f0a5915b9a8', '1366c663bb614b20b4d433b637b286ed', '411100', null, '��Ͻ��', '01', '��Ͻ��', '430301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f8ad253c6ef74b7e9ff84d05fe8b8517', '1366c663bb614b20b4d433b637b286ed', '411100', null, '�����', '02', '�����', '430302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2762f74ca8364e67a525ce6e19ff8f8a', '1366c663bb614b20b4d433b637b286ed', '411100', null, '������', '04', '������', '430304', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('53a98ca1bdff430886697a7eb578df40', '1366c663bb614b20b4d433b637b286ed', '411200', null, '��̶��', '21', '��̶��', '430321', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7774676822b14b5aae61637532cc1ac1', '1366c663bb614b20b4d433b637b286ed', '411400', null, '������', '81', '������', '430381', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f5d8460abcf749aab2705df7e5981756', '69bd3ecc0d324699ba96fe24b0356639', '614500', null, '�崨��', '29', '�崨��', '511129', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d6c7ba71c5814395be78d2ebea673527', '69bd3ecc0d324699ba96fe24b0356639', '614300', null, '�������������', '32', '�������������', '511132', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('16f90f94c8b34142957b4ef79a9acbe3', '69bd3ecc0d324699ba96fe24b0356639', '614600', null, '��������������', '33', '��������������', '511133', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f4d2bff8f7d24d7b8a3d28b0094aefcf', '69bd3ecc0d324699ba96fe24b0356639', '614200', null, '��üɽ��', '81', '��üɽ��', '511181', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('128307d4e7624654adbf91c9a3b842c6', '7652787b72664f18bbfd327f363fb188', '637000', null, '��Ͻ��', '01', '��Ͻ��', '511301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('84cb45a9d9bf4bbe812da6f5938d9029', '7652787b72664f18bbfd327f363fb188', '637000', null, '˳����', '02', '˳����', '511302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8f2cf005b2e745629219ba1dede69364', '4d775ed4147c4a29896d82e845939e8b', '421000', null, '������', '12', '������', '430412', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3d98901ebf1b44c9b73467e6b9142c15', '4d775ed4147c4a29896d82e845939e8b', '421200', null, '������', '21', '������', '430421', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('96ff142b3aab4e4ab849f87ae40f5e79', '4d775ed4147c4a29896d82e845939e8b', '421100', null, '������', '22', '������', '430422', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('23b4513fe0814a5995d8fae1e4b50061', '4d775ed4147c4a29896d82e845939e8b', '421300', null, '��ɽ��', '23', '��ɽ��', '430423', null);
commit;
prompt 3000 records committed...
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7839c9559b374c2cbdc380767d55d29b', '4d775ed4147c4a29896d82e845939e8b', '421400', null, '�ⶫ��', '24', '�ⶫ��', '430424', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3714a9056815462594129ac95c5e43c8', '4d775ed4147c4a29896d82e845939e8b', '421600', null, '���', '26', '���', '430426', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bb6d72c560df480d8b9cc6af7e2a8673', '4d775ed4147c4a29896d82e845939e8b', '421800', null, '������', '81', '������', '430481', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2b3fc4d7807c4d18810555e7119335e4', '4d775ed4147c4a29896d82e845939e8b', '421500', null, '������', '82', '������', '430482', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8add0e8aec9e4b339971a9d1c4d7dd0a', '495e8497aed747bf8741de8bd42abaf7', '422000', null, '��Ͻ��', '01', '��Ͻ��', '430501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ad17e0593ed1421193d95fbaaed9de48', '495e8497aed747bf8741de8bd42abaf7', '422000', null, '˫����', '02', '˫����', '430502', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1317ca22b90f42eaa68481240bb30f2d', '495e8497aed747bf8741de8bd42abaf7', '422000', null, '������', '03', '������', '430503', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1f9e5d60fbbd4e5882385b42f7d2c7ca', '495e8497aed747bf8741de8bd42abaf7', '422000', null, '������', '11', '������', '430511', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4b5fd4e3ea924a22b41578b173ef1ec9', '495e8497aed747bf8741de8bd42abaf7', '422800', null, '�۶���', '21', '�۶���', '430521', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e12cdaeb6f6f42bbb85a7e0541f7df85', '495e8497aed747bf8741de8bd42abaf7', '422900', null, '������', '22', '������', '430522', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8288678fd1ec4e7d81358e69f75b18ae', '495e8497aed747bf8741de8bd42abaf7', '422100', null, '������', '23', '������', '430523', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9b1f56457efa483bb125bc63edd61ba3', '495e8497aed747bf8741de8bd42abaf7', '422200', null, '¡����', '24', '¡����', '430524', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e4bda772979c43d3a06f3ef0bd8cd62c', '495e8497aed747bf8741de8bd42abaf7', '422300', null, '������', '25', '������', '430525', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bce19130fa2e41dc95f7a079a911fa68', '495e8497aed747bf8741de8bd42abaf7', '422600', null, '������', '27', '������', '430527', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c6dc033017d840f580adc26616b8f583', '495e8497aed747bf8741de8bd42abaf7', '422700', null, '������', '28', '������', '430528', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bdaba7a149c34779b889abf62b570bab', 'b9b5bc2ccede4027a1f74f80e6341da6', '841800', null, '��Ǽ��', '24', '��Ǽ��', '652824', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0e5e9945dc354017b1456e0f47e7c1e7', 'b9b5bc2ccede4027a1f74f80e6341da6', '841900', null, '��ĩ��', '25', '��ĩ��', '652825', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c69c85e516d64a6fb3e3641cd7b27e71', 'b9b5bc2ccede4027a1f74f80e6341da6', '841100', null, '���Ȼ���������', '26', '���Ȼ���������', '652826', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('904e9239d77849b7adfcdbfba0fa4200', 'b9b5bc2ccede4027a1f74f80e6341da6', '841300', null, '�;���', '27', '�;���', '652827', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b5f7c3eb43c04aea8a1fa5e304f87c92', 'b9b5bc2ccede4027a1f74f80e6341da6', '841200', null, '��˶��', '28', '��˶��', '652828', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8f80388a189841be863ce640890e2841', 'b9b5bc2ccede4027a1f74f80e6341da6', '841400', null, '������', '29', '������', '652829', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('158e177f9cf948c68d1cd54269165a6b', '2ff89e0bff1b460db144ac09b148128d', '843000', null, '��������', '01', '��������', '652901', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d3037105b90a466489a2bd2fbddf7d9b', '2ff89e0bff1b460db144ac09b148128d', '843100', null, '������', '22', '������', '652922', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('894f40b28308499e8a13090a3e6df8e5', '2ff89e0bff1b460db144ac09b148128d', '842000', null, '�⳵��', '23', '�⳵��', '652923', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6f0f8a1b38ca4f81b0084e2ccef7d18d', '2ff89e0bff1b460db144ac09b148128d', '842200', null, 'ɳ����', '24', 'ɳ����', '652924', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a499ef9e6d2846cb8cfd2094eb5fa690', '2ff89e0bff1b460db144ac09b148128d', '842100', null, '�º���', '25', '�º���', '652925', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4bd484010dfa4310bfc9f1fd40ee6c1a', '2ff89e0bff1b460db144ac09b148128d', '842300', null, '�ݳ���', '26', '�ݳ���', '652926', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('805637c33c1946d081d4e40c2dbd4cd8', '2ff89e0bff1b460db144ac09b148128d', '843400', null, '��ʲ��', '27', '��ʲ��', '652927', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('049647d3dd154b1ca89ddee09c8cf251', '2ff89e0bff1b460db144ac09b148128d', '843200', null, '��������', '28', '��������', '652928', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0689d63857b746219b2ad1c273ebac2a', '2ff89e0bff1b460db144ac09b148128d', '845350', null, '��ƺ��', '29', '��ƺ��', '652929', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7e9723a259864c989d70a82251608387', '39d4b2c4532c408c8f5c415bca3646d0', '845350', null, '��ͼʲ��', '01', '��ͼʲ��', '653001', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4d3d063bd29047278fcc3614f4945deb', '39d4b2c4532c408c8f5c415bca3646d0', '845550', null, '��������', '22', '��������', '653022', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7bd2acf10b89447fbc0f2d83642c8372', '39d4b2c4532c408c8f5c415bca3646d0', '843500', null, '��������', '23', '��������', '653023', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8ec4d9ad907d4eb09b27ae110b5cc28a', '39d4b2c4532c408c8f5c415bca3646d0', '845450', null, '��ǡ��', '24', '��ǡ��', '653024', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e576a6290f644efb98be31bf30d08a1b', 'ab1026be2a6249d6970816590fc19145', '844000', null, '��ʲ��', '01', '��ʲ��', '653101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('55d3b85aef8045e78305e19df02cf772', 'ab1026be2a6249d6970816590fc19145', '844100', null, '�踽��', '21', '�踽��', '653121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c0ebbf8e3a5a452ba24fcd886ed0daf4', 'ab1026be2a6249d6970816590fc19145', '844200', null, '������', '22', '������', '653122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8d6ab25414514f2684749cdae0d7148c', 'ab1026be2a6249d6970816590fc19145', '844500', null, 'Ӣ��ɳ��', '23', 'Ӣ��ɳ��', '653123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8d95a75487b746de92c71d66f20e9ff7', 'ab1026be2a6249d6970816590fc19145', '844800', null, '������', '24', '������', '653124', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8b07afacbdd9467b8ea2103f3a5f8f98', 'ab1026be2a6249d6970816590fc19145', '844700', null, 'ɯ����', '25', 'ɯ����', '653125', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c86d2b8e444c44729b54832ecd98c2c3', '288c789701ac4bcc8096dd8d85085239', '718100', null, '��֬��', '27', '��֬��', '610827', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b55b8df7fd134bb99558a82c07534ced', '288c789701ac4bcc8096dd8d85085239', '719200', null, '����', '28', '����', '610828', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('81b2434bf4824426b575d70612bef2bb', '288c789701ac4bcc8096dd8d85085239', '718200', null, '�Ɽ��', '29', '�Ɽ��', '610829', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('db9c50b71eb84774a0139241573e5444', '288c789701ac4bcc8096dd8d85085239', '718300', null, '�彧��', '30', '�彧��', '610830', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3beae26427a74a07bb573b9a958be43f', '288c789701ac4bcc8096dd8d85085239', '718400', null, '������', '31', '������', '610831', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fabdd02537674f97b2a9d0d40239bf11', 'b39593a949c4411ebe181f0f397daf3d', '725000', null, '��Ͻ��', '01', '��Ͻ��', '610901', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1f0af52c89f240508bc9e173a6d46cbb', 'b39593a949c4411ebe181f0f397daf3d', '725000', null, '������', '02', '������', '610902', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('be381628ba5e4aa3808848de43d967c6', 'b39593a949c4411ebe181f0f397daf3d', '725100', null, '������', '21', '������', '610921', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0f0f75a028ec4cfa82ce158d673fd2be', 'b39593a949c4411ebe181f0f397daf3d', '725200', null, 'ʯȪ��', '22', 'ʯȪ��', '610922', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2aa1c3ee20cb4703a8b7934ca827cc0d', 'b39593a949c4411ebe181f0f397daf3d', '711600', null, '������', '23', '������', '610923', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d60f3271c6a84d3d8d0266f7a2822f31', 'b39593a949c4411ebe181f0f397daf3d', '725300', null, '������', '24', '������', '610924', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d3ae2ca189e8416a9bd83ece56931fe4', 'b39593a949c4411ebe181f0f397daf3d', '725400', null, '᰸���', '25', '᰸���', '610925', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('245f1ebe33664d2396c2fd39c75672a3', 'b39593a949c4411ebe181f0f397daf3d', '725500', null, 'ƽ����', '26', 'ƽ����', '610926', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('64aa7d84d8c84e1c9dc4c8068c462a49', 'b39593a949c4411ebe181f0f397daf3d', '725600', null, '��ƺ��', '27', '��ƺ��', '610927', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('69d9e02f48fb43058be3c5282feba6d8', 'b39593a949c4411ebe181f0f397daf3d', '725700', null, 'Ѯ����', '28', 'Ѯ����', '610928', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b3663abccc2b486ab9b5ab116e366bb1', 'b39593a949c4411ebe181f0f397daf3d', '725800', null, '�׺���', '29', '�׺���', '610929', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('581e81093a954ffbb4b900802bfd8705', '96efc0d8bf4b4484adde54865b3e7c63', '726000', null, '��Ͻ��', '01', '��Ͻ��', '611001', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c9a2c686a0c5438e9c43e69ed429f548', '96efc0d8bf4b4484adde54865b3e7c63', '726000', null, '������', '02', '������', '611002', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3323ae95bc7644f9aa2f567adc398a2a', '96efc0d8bf4b4484adde54865b3e7c63', '726100', null, '������', '21', '������', '611021', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fdef9f443c304fe28520d31180f0a6a2', '96efc0d8bf4b4484adde54865b3e7c63', '726200', null, '������', '22', '������', '611022', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ef02b88149ba46d38bdfad4de478c095', '96efc0d8bf4b4484adde54865b3e7c63', '726300', null, '������', '23', '������', '611023', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('839ebbb1f9ce4d408c6e841edbffe60d', '96efc0d8bf4b4484adde54865b3e7c63', '726400', null, 'ɽ����', '24', 'ɽ����', '611024', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('88ef59d7e975407f8c2374ce6d3bb4df', '96efc0d8bf4b4484adde54865b3e7c63', '711500', null, '����', '25', '����', '611025', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('038ea5e6e4684b4793a771d08c1a6a8a', '96efc0d8bf4b4484adde54865b3e7c63', '711400', null, '��ˮ��', '26', '��ˮ��', '611026', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c665d75b1eca4591882820b50cf12bdc', '5ab6b67e085546caa372b13ec883e55d', '652200', null, 'ʯ������������', '26', 'ʯ������������', '530126', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f8ffa8815c1947cc9910e94ddcc12819', '5ab6b67e085546caa372b13ec883e55d', '651700', null, '������', '27', '������', '530127', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8740a5b3fc7f471db8ed62746716b71c', '5ab6b67e085546caa372b13ec883e55d', '651500', null, '»Ȱ��������������', '28', '»Ȱ��������������', '530128', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4b3d566df18c49f9ae612061ca8a97d3', '5ab6b67e085546caa372b13ec883e55d', '655200', null, 'Ѱ���������������', '29', 'Ѱ���������������', '530129', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f40fb9d917944cf6b69ec5a01eb52837', '5ab6b67e085546caa372b13ec883e55d', '650300', null, '������', '81', '������', '530181', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('59fdc3fbd4c842f18f5021fe254124f3', '32f1279ae35c4dd4bc2aff7a08755344', '655000', null, '��Ͻ��', '01', '��Ͻ��', '530301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9461936d94e94a18b29834b319ace862', '32f1279ae35c4dd4bc2aff7a08755344', '655000', null, '������', '02', '������', '530302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('58ac17a73f784d8c9e919ff87b9b24cb', '32f1279ae35c4dd4bc2aff7a08755344', '655100', null, '������', '21', '������', '530321', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f4cd7117c39c49cfa72d5334ae526f60', '32f1279ae35c4dd4bc2aff7a08755344', '655600', null, '½����', '22', '½����', '530322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1e2893cc6c9444f699bd455cd9e9cff5', '32f1279ae35c4dd4bc2aff7a08755344', '655700', null, 'ʦ����', '23', 'ʦ����', '530323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('89b6aaec462e40c89416ea03b62838be', '32f1279ae35c4dd4bc2aff7a08755344', '655800', null, '��ƽ��', '24', '��ƽ��', '530324', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4f4a96631013483fa1128a16fd40efba', '32f1279ae35c4dd4bc2aff7a08755344', '655500', null, '��Դ��', '25', '��Դ��', '530325', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2beaab65ffd945d6a07353eda5799a62', '32f1279ae35c4dd4bc2aff7a08755344', '654200', null, '������', '26', '������', '530326', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('27309802063f4a229b9ea934053145b6', '32f1279ae35c4dd4bc2aff7a08755344', '655500', null, 'մ����', '28', 'մ����', '530328', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e18d9ea8c5f043f3a5e36c5d3f626f14', '32f1279ae35c4dd4bc2aff7a08755344', '655400', null, '������', '81', '������', '530381', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('17741d3ea987489f918925caf5fbdfb3', 'b32eb2d28ea84cacb3190db9a12588db', '653100', null, '��Ͻ��', '01', '��Ͻ��', '530401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d9f78b97af1b4d8a849f0666fa3a67e1', 'b32eb2d28ea84cacb3190db9a12588db', '653100', null, '������', '02', '������', '530402', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('04ede894b1b24088b746089d0e847bd4', 'b32eb2d28ea84cacb3190db9a12588db', '652600', null, '������', '21', '������', '530421', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('caedf81be71b4dedbb5fde29128471f0', 'b32eb2d28ea84cacb3190db9a12588db', '652500', null, '�ν���', '22', '�ν���', '530422', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('92b5a7fa65d34f7baa875ba4e3a3f379', 'b32eb2d28ea84cacb3190db9a12588db', '652700', null, 'ͨ����', '23', 'ͨ����', '530423', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('48ade82add7d4ee3958a367acbc2e533', 'b32eb2d28ea84cacb3190db9a12588db', '652800', null, '������', '24', '������', '530424', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c55a3deaf0e84d3794cdae7b50724bb1', 'b32eb2d28ea84cacb3190db9a12588db', '651100', null, '������', '25', '������', '530425', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1e67922bbcaa4dd3b8bf0975b154ac8d', 'b32eb2d28ea84cacb3190db9a12588db', '653200', null, '��ɽ����������', '26', '��ɽ����������', '530426', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7d28e3de037d4b949d23e0d158cfde02', 'b32eb2d28ea84cacb3190db9a12588db', '653400', null, '��ƽ�������������', '27', '��ƽ�������������', '530427', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('755a6c47a50b477e8ddf4e090f66b136', 'b32eb2d28ea84cacb3190db9a12588db', '653300', null, 'Ԫ���������������������', '28', 'Ԫ���������������������', '530428', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('db2e5e905c91461e925f1f71c8b787b8', '7f5abf200b664ad595bc1a8c7098b9af', '678000', null, '��Ͻ��', '01', '��Ͻ��', '530501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2c153802743540798e2cce148e9bae4f', '7f5abf200b664ad595bc1a8c7098b9af', '678000', null, '¡����', '02', '¡����', '530502', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('acfda7dfd3b9458b9304a7323e39da49', '7f5abf200b664ad595bc1a8c7098b9af', '678200', null, 'ʩ����', '21', 'ʩ����', '530521', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cca0b6144f58439ab24d93ef012d2606', '7f5abf200b664ad595bc1a8c7098b9af', '679100', null, '�ڳ���', '22', '�ڳ���', '530522', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9552488597c9421dabcc3dee842174c3', '7f5abf200b664ad595bc1a8c7098b9af', '678300', null, '������', '23', '������', '530523', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('94bfb986b5b94f61b2fd9079ef796bb1', '7f5abf200b664ad595bc1a8c7098b9af', '678100', null, '������', '24', '������', '530524', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fbd31f52655d43239b53785720f75e0b', '71565bd32ec9492b81bb4ab47436ddf0', '657000', null, '��Ͻ��', '01', '��Ͻ��', '530601', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8fc6bfe0f8f6417c83e33be47ab4c84a', '71565bd32ec9492b81bb4ab47436ddf0', '657000', null, '������', '02', '������', '530602', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('71205e8e237043a5bdb45da1a2e7de2c', '71565bd32ec9492b81bb4ab47436ddf0', '657100', null, '³����', '21', '³����', '530621', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0daa299acc7a4b0daea7913d14990821', '71565bd32ec9492b81bb4ab47436ddf0', '654600', null, '�ɼ���', '22', '�ɼ���', '530622', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('090b27880442413bbd60d80c2ad07745', '71565bd32ec9492b81bb4ab47436ddf0', '657500', null, '�ν���', '23', '�ν���', '530623', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b30093972839499387d29ae7d24ac167', '71565bd32ec9492b81bb4ab47436ddf0', '657400', null, '�����', '24', '�����', '530624', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e7e13cb63e7c4258b0b2292049944b16', '71565bd32ec9492b81bb4ab47436ddf0', '657300', null, '������', '25', '������', '530625', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('79c8059a297549bb9ce29e6e2556b1e2', '71565bd32ec9492b81bb4ab47436ddf0', '657700', null, '�罭��', '26', '�罭��', '530626', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('146fefbca81442239f023a5aa8966628', '71565bd32ec9492b81bb4ab47436ddf0', '657200', null, '������', '27', '������', '530627', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('195da86a51aa4881bc884919a173c0fa', '71565bd32ec9492b81bb4ab47436ddf0', '657600', null, '������', '28', '������', '530628', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('066a751cda4643ea9379f84280a6d436', '6343ca91165140d1bcc25205a1235335', '666500', null, '�������������������������', '25', '�������������������������', '530825', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('892dec6f1fb14b18b11f11e84ce8febc', '6343ca91165140d1bcc25205a1235335', '665900', null, '���ǹ���������������', '26', '���ǹ���������������', '530826', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f1bd896275c04d7e83d46a368f898974', '53d713790c804ce1b1c4d65acd66b7a0', '730000', null, '��Ͻ��', '01', '��Ͻ��', '620101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('93d34d1b142e457b970b74dcbf35a7f0', '53d713790c804ce1b1c4d65acd66b7a0', '730030', null, '�ǹ���', '02', '�ǹ���', '620102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e6aebbc39ba94c519fe445e3dad67cc9', '53d713790c804ce1b1c4d65acd66b7a0', '730050', null, '�������', '03', '�������', '620103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3eabf5c6728944188ec67f011c2939b9', '53d713790c804ce1b1c4d65acd66b7a0', '730060', null, '������', '04', '������', '620104', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d598b60993de473f8efd21c0d986d062', '53d713790c804ce1b1c4d65acd66b7a0', '730070', null, '������', '05', '������', '620105', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('668e17d1b79647e3969fce1316c43ed9', '53d713790c804ce1b1c4d65acd66b7a0', '730080', null, '�����', '11', '�����', '620111', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0b5f0e6def3541cf8213eae68e450f4b', 'd3ac67eb93544b9da681781f1f27fa5d', '675900', null, '������', '21', '������', '530921', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('89c8b075821447729db26a4f23650584', 'd3ac67eb93544b9da681781f1f27fa5d', '675800', null, '����', '22', '����', '530922', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('50cfaa464cad428a949794eff12cb461', 'd3ac67eb93544b9da681781f1f27fa5d', '677600', null, '������', '23', '������', '530923', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('16962118778446c3b289667b82205086', 'd3ac67eb93544b9da681781f1f27fa5d', '677700', null, '����', '24', '����', '530924', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7783ae846b344629b451e09b371a7913', 'd3ac67eb93544b9da681781f1f27fa5d', '677300', null, '˫�����������岼�������������', '25', '˫�����������岼�������������', '530925', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('caae44f8ea524f2ba6a8705040d1fdd3', 'd3ac67eb93544b9da681781f1f27fa5d', '677500', null, '������������������', '26', '������������������', '530926', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('be728ba47c114977bcf630c5a58dab7f', 'd3ac67eb93544b9da681781f1f27fa5d', '677400', null, '��Դ����������', '27', '��Դ����������', '530927', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d678d0c09788492285d5553a56f4a1d3', 'f9f4657fe4aa48bb8d324167750c9b1c', '675000', null, '������', '01', '������', '532301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4d72b0fed23245eeae578512de26178f', 'f9f4657fe4aa48bb8d324167750c9b1c', '675100', null, '˫����', '22', '˫����', '532322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f95b4377d3024bd5a0a7ea3723a5ccd9', 'f9f4657fe4aa48bb8d324167750c9b1c', '675500', null, 'Ĳ����', '23', 'Ĳ����', '532323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9accfe75c7ae4fd8b463b7eee85dfc8f', 'f9f4657fe4aa48bb8d324167750c9b1c', '675200', null, '�ϻ���', '24', '�ϻ���', '532324', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('586a4c51c45d42308a51377db2d1c0e5', 'f9f4657fe4aa48bb8d324167750c9b1c', '675300', null, 'Ҧ����', '25', 'Ҧ����', '532325', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ccaa5b61db9b4f149dfd3c8dc4fbc176', 'f9f4657fe4aa48bb8d324167750c9b1c', '675400', null, '��Ҧ��', '26', '��Ҧ��', '532326', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('966cf6d155454bfea8a05aea666a9744', 'f9f4657fe4aa48bb8d324167750c9b1c', '651400', null, '������', '27', '������', '532327', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f2de8d486869455696e7afed6f71e7d7', 'f9f4657fe4aa48bb8d324167750c9b1c', '651300', null, 'Ԫı��', '28', 'Ԫı��', '532328', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('75b6ab87f7bd4f5da8247759a4fd33aa', 'f9f4657fe4aa48bb8d324167750c9b1c', '651600', null, '�䶨��', '29', '�䶨��', '532329', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('95c223a44e0844f0a5bd652cb6bdf5e6', 'ab1026be2a6249d6970816590fc19145', '844900', null, 'Ҷ����', '26', 'Ҷ����', '653126', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9535daad003d481294cfea461d8a3c73', 'ab1026be2a6249d6970816590fc19145', '844600', null, '�������', '27', '�������', '653127', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('69dfe4b7a1f1409391c7d4b7d08c5e42', 'ab1026be2a6249d6970816590fc19145', '844400', null, '���պ���', '28', '���պ���', '653128', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7e87b436e94e4560a27fff6339862465', 'ab1026be2a6249d6970816590fc19145', '844300', null, '٤ʦ��', '29', '٤ʦ��', '653129', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2de012549b504e6c9f3d0f53e8e17d84', 'ab1026be2a6249d6970816590fc19145', '843800', null, '�ͳ���', '30', '�ͳ���', '653130', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d237242fd70d43a9af0c2187f0beba35', 'ab1026be2a6249d6970816590fc19145', '845250', null, '��ʲ�����������������', '31', '��ʲ�����������������', '653131', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('db73b61c959f4dffb238755c53c04e77', 'bb8405b610ee45ee9b7aac1f46c2fee3', '848000', null, '������', '01', '������', '653201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4c0a1f0e074c4cd9a480c2ae2f7a86b3', 'bb8405b610ee45ee9b7aac1f46c2fee3', '848000', null, '������', '21', '������', '653221', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('93b457219d5d42b29fea3f0a93871dc8', 'bb8405b610ee45ee9b7aac1f46c2fee3', '848100', null, 'ī����', '22', 'ī����', '653222', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('592972bebf74401e9b1ddca27e110c5c', 'bb8405b610ee45ee9b7aac1f46c2fee3', '845150', null, 'Ƥɽ��', '23', 'Ƥɽ��', '653223', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('93c8476f44974e5cbe601a9fce12ad5d', 'bb8405b610ee45ee9b7aac1f46c2fee3', '848200', null, '������', '24', '������', '653224', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('80b9464d2d554b899032c6710e6504ce', 'bb8405b610ee45ee9b7aac1f46c2fee3', '848300', null, '������', '25', '������', '653225', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f4140757e7ad4a89b3fc2d6076b4663a', 'bb8405b610ee45ee9b7aac1f46c2fee3', '848400', null, '������', '26', '������', '653226', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fd131ae78cbd4368a9bcadfe1a642f4e', 'bb8405b610ee45ee9b7aac1f46c2fee3', '848500', null, '�����', '27', '�����', '653227', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('68279dd44b904aa19127dcf86470863a', '5af36941bec94e909e006b557150a1d4', '835000', null, '������', '02', '������', '654002', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cf9c778a402648c0ac42455cd3cfe03a', '5af36941bec94e909e006b557150a1d4', '833200', null, '������', '03', '������', '654003', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1f5caf4a96b345b988ad11193dddc56d', '5af36941bec94e909e006b557150a1d4', '835100', null, '������', '21', '������', '654021', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b9c2cf8c543e4714aacdaf32c7d3eccf', '5af36941bec94e909e006b557150a1d4', '835300', null, '�첼�������������', '22', '�첼�������������', '654022', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('347e1d142ca7449d95cb04b328cf7be1', '5af36941bec94e909e006b557150a1d4', '835200', null, '������', '23', '������', '654023', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9f8ccbfc408f427592a037cf9b39d9dc', '5af36941bec94e909e006b557150a1d4', '835400', null, '������', '24', '������', '654024', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('43661bda7e0a49f180eb84beb5f2c54c', '5af36941bec94e909e006b557150a1d4', '835800', null, '��Դ��', '25', '��Դ��', '654025', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ac3848264433469186322be6f147735a', '5af36941bec94e909e006b557150a1d4', '835600', null, '������', '26', '������', '654026', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4906f7f75fbe4728aedd5d0d096e3ede', '7009390a34474afe8e3122be8006dd75', '671400', null, '��ƺ����������������', '25', '��ƺ����������������', '533325', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5773f03380c3450eaa4dcb863f017663', 'd87d78ea6435473faf023cc21eabb452', '674400', null, '���������', '21', '���������', '533421', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5da385fcc3e5455981c9cf956b11b97a', 'd87d78ea6435473faf023cc21eabb452', '674500', null, '������', '22', '������', '533422', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cad02a60b34e4c09a2122f67c7f1a47c', 'd87d78ea6435473faf023cc21eabb452', '674600', null, 'ά��������������', '23', 'ά��������������', '533423', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('70508936e4614fc3b06e01ee28c52a39', 'c5b38e0ab90d494dad25c3d85b510cee', '850000', null, '��Ͻ��', '01', '��Ͻ��', '540101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e305a86b1529447bb226742be71c5cb4', 'c5b38e0ab90d494dad25c3d85b510cee', '850000', null, '�ǹ���', '02', '�ǹ���', '540102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6fede8a2d4e043b2aeb8c2a114e2e6f3', 'c5b38e0ab90d494dad25c3d85b510cee', '851600', null, '������', '21', '������', '540121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fc372c2b07004565a34ad4cdb1b79fc9', 'c5b38e0ab90d494dad25c3d85b510cee', '851500', null, '������', '22', '������', '540122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('24801f3b5e2b4ffe92841e4b4800360f', 'c5b38e0ab90d494dad25c3d85b510cee', '851600', null, '��ľ��', '23', '��ľ��', '540123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('862c578888c54d98a60335ce88dd2c55', 'c5b38e0ab90d494dad25c3d85b510cee', '850600', null, '��ˮ��', '24', '��ˮ��', '540124', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aae6de878e8b40309e495cf668ee7a8f', 'c5b38e0ab90d494dad25c3d85b510cee', '851400', null, '����������', '25', '����������', '540125', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ec6bb6b0fd2040498010415b1d0ca1ab', 'c5b38e0ab90d494dad25c3d85b510cee', '850100', null, '������', '26', '������', '540126', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6dd2ab12a5e64a9388d07075fdc4e623', 'c5b38e0ab90d494dad25c3d85b510cee', '850200', null, 'ī�񹤿���', '27', 'ī�񹤿���', '540127', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1b1a6b3fc3db4d22b840d9efc98c2883', '0f59a76aeef144489eaaf8edd763f507', '854000', null, '������', '21', '������', '542121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a833eb0a66254f758bc6f5cca53ca724', '0f59a76aeef144489eaaf8edd763f507', '854100', null, '������', '22', '������', '542122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c2482cef4fea4f2abf13450de95e6695', '0f59a76aeef144489eaaf8edd763f507', '854200', null, '������', '23', '������', '542123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('314ae484b5b44c74a0f49274659cb1b4', '0f59a76aeef144489eaaf8edd763f507', '855600', null, '��������', '24', '��������', '542124', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('76fa23e2ee664269aad532678bc14bdb', '0f59a76aeef144489eaaf8edd763f507', '855700', null, '������', '25', '������', '542125', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6f05ab8557eb42cf84b98462d739eeb4', '0f59a76aeef144489eaaf8edd763f507', '854300', null, '������', '26', '������', '542126', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('227c097be7b748bc9e938e9ce9822358', '0f59a76aeef144489eaaf8edd763f507', '854600', null, '������', '27', '������', '542127', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('85d5aa7212ea425184b76c0acb9df85a', '0f59a76aeef144489eaaf8edd763f507', '854400', null, '����', '28', '����', '542128', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f16d39d9e14e4e639782f5b86dbe63ed', '0f59a76aeef144489eaaf8edd763f507', '854500', null, 'â����', '29', 'â����', '542129', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d1050d4e98104085a96759bdd2df5a8d', '0f59a76aeef144489eaaf8edd763f507', '855400', null, '��¡��', '32', '��¡��', '542132', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dcd7d09c931e41d29950e6d92a4c87e7', '0f59a76aeef144489eaaf8edd763f507', '855500', null, '�߰���', '33', '�߰���', '542133', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('808387bc15a24dc58e1a6008c6c3c2a4', '0127cc2437fb4b1aab1311ed373da4d5', '856100', null, '�˶���', '21', '�˶���', '542221', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('934350f0c6714964a3afb507dc15b83d', '0127cc2437fb4b1aab1311ed373da4d5', '850800', null, '������', '22', '������', '542222', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e41c735811164bd1ad33da0fb948cf97', '0127cc2437fb4b1aab1311ed373da4d5', '850700', null, '������', '23', '������', '542223', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d2b46dadc37b4ad982db87b2e4dd21da', '0127cc2437fb4b1aab1311ed373da4d5', '856200', null, 'ɣ����', '24', 'ɣ����', '542224', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c91686ecdd67425b9ba35601c1dba7fe', '0127cc2437fb4b1aab1311ed373da4d5', '856800', null, '������', '25', '������', '542225', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6832e5bde5af437aaa8de5115809b251', '0127cc2437fb4b1aab1311ed373da4d5', '856300', null, '������', '26', '������', '542226', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c8d8aa78f730469cb2a9800cb3a4186a', '0127cc2437fb4b1aab1311ed373da4d5', '856900', null, '������', '27', '������', '542227', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('48b320707ca34a89b6183e6c4a2da82d', '0127cc2437fb4b1aab1311ed373da4d5', '851200', null, '������', '28', '������', '542228', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3d783790cd194d35a866cfe2056b48b1', '0127cc2437fb4b1aab1311ed373da4d5', '856400', null, '�Ӳ���', '29', '�Ӳ���', '542229', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2d092e0db5fd46e5aaadf15daa619d96', '0127cc2437fb4b1aab1311ed373da4d5', '856600', null, '¡����', '31', '¡����', '542231', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0f2b974fbc3447f9867751d6e7ba4fd6', '0127cc2437fb4b1aab1311ed373da4d5', '856700', null, '������', '32', '������', '542232', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5fb92e60389343dc8b2dbb49a5902193', '0127cc2437fb4b1aab1311ed373da4d5', '851100', null, '�˿�����', '33', '�˿�����', '542233', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a13540742ed844a69a8273391ff5065c', 'f146c88e9f61487e8187bcdc280e2897', '857000', null, '�տ�����', '01', '�տ�����', '542301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('231324dfd4f24bef850bac96165f28b4', 'f146c88e9f61487e8187bcdc280e2897', '857100', null, '��ľ����', '22', '��ľ����', '542322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('253d844ed4414bcf84ab4bcd652d1d57', 'f146c88e9f61487e8187bcdc280e2897', '857400', null, '������', '23', '������', '542323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aa9a28e2b0af4354bccc19087a28d135', 'f146c88e9f61487e8187bcdc280e2897', '858200', null, '������', '24', '������', '542324', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1487ef3c449a4273a696a428d3b97e91', 'f146c88e9f61487e8187bcdc280e2897', '857800', null, '������', '25', '������', '542325', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('524d7853aa634bd780cba60432e57a5d', 'f146c88e9f61487e8187bcdc280e2897', '858100', null, '������', '26', '������', '542326', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1024592593314c7c8b3a62aeac4694b7', 'f146c88e9f61487e8187bcdc280e2897', '858500', null, '������', '27', '������', '542327', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('184f799832e2445785f05b4d2c262899', 'f146c88e9f61487e8187bcdc280e2897', '858900', null, 'лͨ����', '28', 'лͨ����', '542328', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f070a1bbf21f4b7484337879b377d878', 'f146c88e9f61487e8187bcdc280e2897', '857300', null, '������', '29', '������', '542329', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5b3f24a0e9bc447c88a641327924d5f6', 'f146c88e9f61487e8187bcdc280e2897', '857200', null, '�ʲ���', '30', '�ʲ���', '542330', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e2255637084a44ca94365cc54f4b1112', 'f146c88e9f61487e8187bcdc280e2897', '857500', null, '������', '31', '������', '542331', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('23a86128413f492ea5b4d3c272145482', 'f146c88e9f61487e8187bcdc280e2897', '857900', null, '������', '32', '������', '542332', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('78029642557c4953b1efbdec4f4ba8ba', 'f146c88e9f61487e8187bcdc280e2897', '858800', null, '�ٰ���', '33', '�ٰ���', '542333', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('12863c4279264cc98aad4de6f2abeb09', 'f146c88e9f61487e8187bcdc280e2897', '857600', null, '�Ƕ���', '34', '�Ƕ���', '542334', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c635dc17ebcf4ed98d9748a27515f746', 'f146c88e9f61487e8187bcdc280e2897', '858700', null, '��¡��', '35', '��¡��', '542335', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b9af35d5df214d578c5ea98ebba38cec', 'f146c88e9f61487e8187bcdc280e2897', '858300', null, '����ľ��', '36', '����ľ��', '542336', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('53a6d69f64f8465dab9c49c9fb87b7a1', 'f146c88e9f61487e8187bcdc280e2897', '858600', null, '������', '37', '������', '542337', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bf4f9a14576a4103a7515435690c1ed1', 'f146c88e9f61487e8187bcdc280e2897', '857700', null, '�ڰ���', '38', '�ڰ���', '542338', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8ec37055e562421f8a07e912a9031db1', 'ad1a12715fab4d57bf0bfdda2eb9db6a', '852000', null, '������', '21', '������', '542421', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b78efa79290e4bdb93a8e67e541b6449', 'ad1a12715fab4d57bf0bfdda2eb9db6a', '852400', null, '������', '22', '������', '542422', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('31b861c9723b431e89eff1134b8a68dd', 'ad1a12715fab4d57bf0bfdda2eb9db6a', '852300', null, '������', '23', '������', '542423', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0464e05d0fb0421eb1958a3ab8c6c5af', 'ad1a12715fab4d57bf0bfdda2eb9db6a', '853500', null, '������', '24', '������', '542424', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('684e4e8c0fd64574af4eefcb73fdc2ec', 'ad1a12715fab4d57bf0bfdda2eb9db6a', '853400', null, '������', '25', '������', '542425', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ecb73a612d5a4a6fafabd1c3a322cf32', 'ad1a12715fab4d57bf0bfdda2eb9db6a', '853100', null, '������', '26', '������', '542426', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d1eee42f6b6847848aad4fad37c389f1', 'ad1a12715fab4d57bf0bfdda2eb9db6a', '852200', null, '����', '27', '����', '542427', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('49248203a3fa49d0831d8dcbc2152450', 'ad1a12715fab4d57bf0bfdda2eb9db6a', '852500', null, '�����', '28', '�����', '542428', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('26f2d73333e94bcda4259a3dcbc22855', 'ad1a12715fab4d57bf0bfdda2eb9db6a', '852100', null, '������', '29', '������', '542429', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('473b1ae212f84407a23a87d061716f43', 'ad1a12715fab4d57bf0bfdda2eb9db6a', '853200', null, '������', '30', '������', '542430', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7b4f6a507b3b478ba1ceca368e8c2946', 'be59c52820d04d2c9b5c91d7f4dae34f', '859500', null, '������', '21', '������', '542521', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('81ea929060be41d1a036af571654cf55', 'be59c52820d04d2c9b5c91d7f4dae34f', '859600', null, '������', '22', '������', '542522', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0b8808b11e8342abb6a2637be89e2f52', 'be59c52820d04d2c9b5c91d7f4dae34f', '859000', null, '������', '23', '������', '542523', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8cbbcfd180f14089ba8010e0d08ce85d', 'be59c52820d04d2c9b5c91d7f4dae34f', '859700', null, '������', '24', '������', '542524', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('99d4165b29064be780c058b22988c49b', 'be59c52820d04d2c9b5c91d7f4dae34f', '859100', null, '�Ｊ��', '25', '�Ｊ��', '542525', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4a011b5b21ec405ba4b8f3886548e53b', '5af36941bec94e909e006b557150a1d4', '835500', null, '�ؿ�˹��', '27', '�ؿ�˹��', '654027', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a141733d764845bb8ad409b7fcd99882', '5af36941bec94e909e006b557150a1d4', '835700', null, '���տ���', '28', '���տ���', '654028', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('573e96be1a774aeda72f79dd2569abd8', '9e7badeaaf6e4b81947d64eee34d696e', '834300', null, '������', '01', '������', '654201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('14538ed0781e4d028dc2cda2061e8166', '9e7badeaaf6e4b81947d64eee34d696e', '833300', null, '������', '02', '������', '654202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d7515f3477674737aa36f243495cd275', '9e7badeaaf6e4b81947d64eee34d696e', '834600', null, '������', '21', '������', '654221', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3b659d844ac94ea793a16ac4310c871f', '9e7badeaaf6e4b81947d64eee34d696e', '832100', null, 'ɳ����', '23', 'ɳ����', '654223', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('32c35eb70bfa45b2af72fe21b2b46982', '9e7badeaaf6e4b81947d64eee34d696e', '834500', null, '������', '24', '������', '654224', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4903ee23ddcc4eeab4b1e515c8eee469', '9e7badeaaf6e4b81947d64eee34d696e', '834800', null, 'ԣ����', '25', 'ԣ����', '654225', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('77590293e3674a689e170be1e137f1fe', '9e7badeaaf6e4b81947d64eee34d696e', '834400', null, '�Ͳ��������ɹ�������', '26', '�Ͳ��������ɹ�������', '654226', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0bc05a7eaa80411e87717df87a281889', '9e4de59c90544481a5c5163355474dee', '836500', null, '����̩��', '01', '����̩��', '654301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2600315a9d8d41539f6dd08f9bce6dc4', '9e4de59c90544481a5c5163355474dee', '836600', null, '��������', '21', '��������', '654321', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e5c7f9c348ae4ddab0dd5a78d998860f', '9e4de59c90544481a5c5163355474dee', '836100', null, '������', '22', '������', '654322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('52fb237f20cc45fe8fc9502eb653151c', '9e4de59c90544481a5c5163355474dee', '836400', null, '������', '23', '������', '654323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4a6be741380a4b31b179dab74184e6d0', '9e4de59c90544481a5c5163355474dee', '836700', null, '���ͺ���', '24', '���ͺ���', '654324', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6305d4cea62148cc897194219766500d', '9e4de59c90544481a5c5163355474dee', '836200', null, '�����', '25', '�����', '654325', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('65771bcdcec84028b267f6366c8bb921', '9e4de59c90544481a5c5163355474dee', '836800', null, '��ľ����', '26', '��ľ����', '654326', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a0431faa2d304e83be350dd7ca7d7f2a', '25a0ad7d58b1411ca8fd5fe2a73bdb6f', '832000', null, 'ʯ������', '01', 'ʯ������', '659001', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bacb8cc8ba264a81af380833b44e791d', '25a0ad7d58b1411ca8fd5fe2a73bdb6f', '843300', null, '��������', '02', '��������', '659002', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1abdf95ad28845ce84f54a27cf15b4a9', '25a0ad7d58b1411ca8fd5fe2a73bdb6f', '844000', null, 'ͼľ�����', '03', 'ͼľ�����', '659003', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('11708d402f5e47b2991f2eb3387e9be8', '25a0ad7d58b1411ca8fd5fe2a73bdb6f', '831300', null, '�������', '04', '�������', '659004', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('177e1110346a402b9a881ab5cb61c707', '0d92c2dd3d8b4d568f5e4213b994f731', '999077', null, '���', '81', '���', '810000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('acd2a0e132cb4c10b6281d6f10190128', 'b071cf5bb5b14629a19e2929bc219029', '999078', null, '����', '82', '����', '820000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e637742cf7484f85a0bdeccc5505a2a2', 'f46010d30af84774a1f8385753b28a93', '999079', null, '̨��', '71', '̨��', '710000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ccbcefce2e894bf28f1a080a796fb8c4', 'a931713a606b4c92b9279967858d62e1', '563000', null, '�㴨��', '03', '�㴨��', '520303', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('829ef755a6574012b5abe4032fa44260', 'a931713a606b4c92b9279967858d62e1', '563100', null, '������', '21', '������', '520321', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('322169770c904314b2e52d5fa67b8fa0', 'a931713a606b4c92b9279967858d62e1', '563200', null, 'ͩ����', '22', 'ͩ����', '520322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fc62262c0b30415b8914c553d9824e58', 'a931713a606b4c92b9279967858d62e1', '563300', null, '������', '23', '������', '520323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9cc01190bf3a46b08cc38b7449fa34aa', 'a931713a606b4c92b9279967858d62e1', '563400', null, '������', '24', '������', '520324', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('af1d79d747694362b03fc6d6d652b6f5', 'a931713a606b4c92b9279967858d62e1', '563500', null, '��������������������', '25', '��������������������', '520325', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e15266720cbc4b73bfec329f31850ae6', 'a931713a606b4c92b9279967858d62e1', '564300', null, '������������������', '26', '������������������', '520326', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e5a67cd2a87b48aa98dfdb65e96ab5c4', 'a931713a606b4c92b9279967858d62e1', '564200', null, '�����', '27', '�����', '520327', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fa6ec5f4e42e425f9125f3a770a347a6', 'a931713a606b4c92b9279967858d62e1', '564100', null, '��̶��', '28', '��̶��', '520328', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6685a2f585734062adf29a6eedef281f', 'a931713a606b4c92b9279967858d62e1', '564400', null, '������', '29', '������', '520329', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2ef5e8a0dbc04b31b1c7301dcf36950f', 'a931713a606b4c92b9279967858d62e1', '564600', null, 'ϰˮ��', '30', 'ϰˮ��', '520330', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7bb37e4bc9a3463c91482497368821b6', 'a931713a606b4c92b9279967858d62e1', '564700', null, '��ˮ��', '81', '��ˮ��', '520381', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c13cd5ba184a41c3bf8ea94b4eb2955c', 'a931713a606b4c92b9279967858d62e1', '564500', null, '�ʻ���', '82', '�ʻ���', '520382', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aa1b6931a8854d9bb335ff0052598267', '413d723baf1e4e9a89d84bd05d36ff6c', '561000', null, '��Ͻ��', '01', '��Ͻ��', '520401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('79476f8301dc4b91959c2990d6ac1cba', '413d723baf1e4e9a89d84bd05d36ff6c', '561000', null, '������', '02', '������', '520402', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3384e56bdb014e66bca7d1530af80042', '413d723baf1e4e9a89d84bd05d36ff6c', '561100', null, 'ƽ����', '21', 'ƽ����', '520421', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6ed00c12c5764352bd720e31a1e38506', '413d723baf1e4e9a89d84bd05d36ff6c', '562100', null, '�ն���', '22', '�ն���', '520422', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2f49abc6720a4fa184198e474de2c656', '413d723baf1e4e9a89d84bd05d36ff6c', '561200', null, '��������������������', '23', '��������������������', '520423', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('57e65d70f0064083bf0ad11b6f3124ff', '413d723baf1e4e9a89d84bd05d36ff6c', '561300', null, '���벼��������������', '24', '���벼��������������', '520424', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('96025766989e423183fa40f18722c636', '53d713790c804ce1b1c4d65acd66b7a0', '730300', null, '������', '21', '������', '620121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2537659f9a6b4073ad1ba7c4be531c59', '53d713790c804ce1b1c4d65acd66b7a0', '730200', null, '������', '22', '������', '620122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dbaa446b4ebd475387f6b987e4062b43', '53d713790c804ce1b1c4d65acd66b7a0', '730100', null, '������', '23', '������', '620123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8cf27c10da084fbbb9070b22d4498bed', 'd3d08413da874670810bc7cb5e0ca7de', '737100', null, '��Ͻ��', '01', '��Ͻ��', '620201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9ec8c0fed52244acb36db7a2098c28bf', 'c4e09846f7cc4ed69c3e6564e1c4d4ad', '737100', null, '��Ͻ��', '01', '��Ͻ��', '620301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0fd4073889814fab9c41ea293331c88d', 'c4e09846f7cc4ed69c3e6564e1c4d4ad', '737100', null, '����', '02', '����', '620302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bda412e6e6f04bdbbc8435e5e54d6106', 'c4e09846f7cc4ed69c3e6564e1c4d4ad', '737200', null, '������', '21', '������', '620321', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2c37c9165c794057b8798136d18fd771', '072c6981f53d4706a27a9d05225eafa2', '730900', null, '��Ͻ��', '01', '��Ͻ��', '620401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ffbfb37221b249098097f9f121cf8da2', '072c6981f53d4706a27a9d05225eafa2', '730900', null, '������', '02', '������', '620402', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2404b7ffe4f6442babc55ebdd0039abc', '072c6981f53d4706a27a9d05225eafa2', '730900', null, 'ƽ����', '03', 'ƽ����', '620403', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('417263fe1396420e9b94748cc9b6f3ad', '072c6981f53d4706a27a9d05225eafa2', '730600', null, '��Զ��', '21', '��Զ��', '620421', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('72bc09d35c8641f3af4cbc977403d539', '072c6981f53d4706a27a9d05225eafa2', '730700', null, '������', '22', '������', '620422', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('da79d9f63c51470cac52780bed19c121', '072c6981f53d4706a27a9d05225eafa2', '730400', null, '��̩��', '23', '��̩��', '620423', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('47796f51ff314da9903afc8d261b8085', '2388cbcd7b30404292f8e01ad88e6f8d', '741000', null, '��Ͻ��', '01', '��Ͻ��', '620501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e9ad4ff1afee4122b150379a88cddf71', '2388cbcd7b30404292f8e01ad88e6f8d', '741000', null, '�س���', '02', '�س���', '620502', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ebdbec376453496095008e64f9764433', '2388cbcd7b30404292f8e01ad88e6f8d', '741000', null, '������', '03', '������', '620503', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('519fc33524114de2a6ba6db7402de69c', '2388cbcd7b30404292f8e01ad88e6f8d', '741400', null, '��ˮ��', '21', '��ˮ��', '620521', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('442de322f06e461a900d59330b42f3af', '2388cbcd7b30404292f8e01ad88e6f8d', '741600', null, '�ذ���', '22', '�ذ���', '620522', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4ebc6de183d94b829cc03ba40d9ebf70', '2388cbcd7b30404292f8e01ad88e6f8d', '741200', null, '�ʹ���', '23', '�ʹ���', '620523', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('652f22a89d1e4fa3b63fdb5d9fcf8656', '2388cbcd7b30404292f8e01ad88e6f8d', '741300', null, '��ɽ��', '24', '��ɽ��', '620524', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b14f71ca712e4aea9fdb427384026a76', 'b51c615743e24581bf67975def934d06', null, null, '��ݸ��', '19', '��ݸ��', '441900', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b14f71ca712e4aea9fdb427384026a77', 'b51c615743e24581bf67975def934d06', null, null, '��ɽ��', '20', '��ɽ��', '442000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2c90928a31ec4f3101337c105d12011d', 'b14f71ca712e4aea9fdb427384026a76', '523000', null, '��ݸ', '01', '��ݸ��', '441901', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2c90928a31ec4f3101337c13f2610123', 'b14f71ca712e4aea9fdb427384026a77', '527000', null, '��ɽ��', '01', '��ɽ��', '442001', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a7923b87579546928acbc9367f8e9177', '7009390a34474afe8e3122be8006dd75', '673400', null, '������', '23', '������', '533323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('25884e9fb9f1409388c320b2434a37ed', '7009390a34474afe8e3122be8006dd75', '673500', null, '��ɽ������ŭ��������', '24', '��ɽ������ŭ��������', '533324', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2678891bd0534816ba17535d349f1f44', '4032edf9271c4bb8868553b88b39f0a8', '742200', null, '����', '26', '����', '621226', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4e602bcd7f1241d7acc2e641d6d783e0', '4032edf9271c4bb8868553b88b39f0a8', '742300', null, '����', '27', '����', '621227', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('951ddcaf51114ad7974ceb36383400c0', '4032edf9271c4bb8868553b88b39f0a8', '742400', null, '������', '28', '������', '621228', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5531a6c852f74467b5d3b818a0d60484', '2c655ecb0f154555be84a4fbea473151', '731100', null, '������', '01', '������', '622901', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8b4d6e241a0e45669b31ede581e9cb19', '2c655ecb0f154555be84a4fbea473151', '731800', null, '������', '21', '������', '622921', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9284849bb3cf45d7a2434a26e7e46b1d', '2c655ecb0f154555be84a4fbea473151', '731500', null, '������', '22', '������', '622922', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3ead8bc3c0cf4b2c93c217f2956dbbae', '2c655ecb0f154555be84a4fbea473151', '731600', null, '������', '23', '������', '622923', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('70bbc476ea4c453e84bffdb9824a8e76', '2c655ecb0f154555be84a4fbea473151', '731300', null, '�����', '24', '�����', '622924', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('53519cd84be04d51bd53dd5466658aba', '2c655ecb0f154555be84a4fbea473151', '731200', null, '������', '25', '������', '622925', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3c77a053c69e42519ec52eb61a0128cd', '2c655ecb0f154555be84a4fbea473151', '731400', null, '������������', '26', '������������', '622926', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('048ed1a93e1942c3972b86bdd7293358', '2c655ecb0f154555be84a4fbea473151', '731700', null, '��ʯɽ�����嶫����������������', '27', '��ʯɽ�����嶫����������������', '622927', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a4d9a489c92e4bee95ff0de97e3cd859', '1357e5dfbbf14671863b4ab27f3a49c5', '747000', null, '������', '01', '������', '623001', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9dbeacb489444579854baed7baac5866', '1357e5dfbbf14671863b4ab27f3a49c5', '747500', null, '��̶��', '21', '��̶��', '623021', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b7e9f66737194b148c9d4986684675e9', '1357e5dfbbf14671863b4ab27f3a49c5', '747600', null, '׿����', '22', '׿����', '623022', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1d09265ba6524f74a700895309c67140', '1357e5dfbbf14671863b4ab27f3a49c5', '746300', null, '������', '23', '������', '623023', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d33b467a19c94237a25ad335ab19fbb8', '1357e5dfbbf14671863b4ab27f3a49c5', '747400', null, '������', '24', '������', '623024', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d55903f58e55431789fc9826db836d2a', '1357e5dfbbf14671863b4ab27f3a49c5', '747300', null, '������', '25', '������', '623025', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4b813711713a48588dc09601560f8a65', '1357e5dfbbf14671863b4ab27f3a49c5', '747200', null, 'µ����', '26', 'µ����', '623026', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('07850b24a24a4d31a101a9fb03c71f17', '413d723baf1e4e9a89d84bd05d36ff6c', '550800', null, '�������岼����������', '25', '�������岼����������', '520425', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fcfdc2f3b98f44859252f20f701ef71c', '6af6ff6ad30d464c8ab0883b16557eb7', '554300', null, 'ͭ����', '01', 'ͭ����', '522201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1dd2871f431b44beb6cbfca2c3661f29', '6af6ff6ad30d464c8ab0883b16557eb7', '554400', null, '������', '22', '������', '522222', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1890c3f1dc514717a451bc31ab64441a', '6af6ff6ad30d464c8ab0883b16557eb7', '554000', null, '��������������', '23', '��������������', '522223', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3144126ae1224eaf961404e869e76c61', '6af6ff6ad30d464c8ab0883b16557eb7', '555100', null, 'ʯ����', '24', 'ʯ����', '522224', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7afdf50faceb4455ae16ed67075ca994', '6af6ff6ad30d464c8ab0883b16557eb7', '565100', null, '˼����', '25', '˼����', '522225', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b4c89f328c5e41fab383da50f2da0729', '6af6ff6ad30d464c8ab0883b16557eb7', '555200', null, 'ӡ������������������', '26', 'ӡ������������������', '522226', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('485d22eda4d24c289e7a3d5ecdc9c056', '6af6ff6ad30d464c8ab0883b16557eb7', '565200', null, '�½���', '27', '�½���', '522227', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('853ec301d33a4dcdab98cb6076ab7f04', '6af6ff6ad30d464c8ab0883b16557eb7', '565300', null, '�غ�������������', '28', '�غ�������������', '522228', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b50ae9fb2f844605a00e0080d41f6413', '6af6ff6ad30d464c8ab0883b16557eb7', '554100', null, '��������������', '29', '��������������', '522229', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('21110d5b3114485184139fc732ea6c20', '6af6ff6ad30d464c8ab0883b16557eb7', '554200', null, '��ɽ����', '30', '��ɽ����', '522230', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('df846445e8644411b8905a6c6a51dc9a', 'e6c957a7e6764d25ae824740397d1146', '562400', null, '������', '01', '������', '522301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('81e5592014f74724ba3e404cebb774bc', 'e6c957a7e6764d25ae824740397d1146', '562300', null, '������', '22', '������', '522322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('85ab4de244ac48089f9190f62064b7e0', 'e6c957a7e6764d25ae824740397d1146', '561500', null, '�հ���', '23', '�հ���', '522323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d95777a9497843d58f1448e64b71bf64', 'e6c957a7e6764d25ae824740397d1146', '561400', null, '��¡��', '24', '��¡��', '522324', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('360ba49d2c084982becddedcbe431952', 'e6c957a7e6764d25ae824740397d1146', '562200', null, '�����', '25', '�����', '522325', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('314e625877ca40e2aec11393e8e8a7fc', 'e6c957a7e6764d25ae824740397d1146', '552300', null, '������', '26', '������', '522326', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('70845bbe7ce94c72b8e4edddb5b8375e', 'e6c957a7e6764d25ae824740397d1146', '552200', null, '�����', '27', '�����', '522327', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7ccdb84f92654bf69697997655f504a5', 'e6c957a7e6764d25ae824740397d1146', '552400', null, '������', '28', '������', '522328', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('35e0e679166c43fabf070fcbc8377863', '3bc2acb5a13f41f68fd1926c57a97d79', '551700', null, '�Ͻ���', '01', '�Ͻ���', '522401', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f4d8674a21bb4f6f9bda450d995f91f4', '3bc2acb5a13f41f68fd1926c57a97d79', '551600', null, '����', '22', '����', '522422', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('eb89007f10334107aae3d99562d2351c', '3bc2acb5a13f41f68fd1926c57a97d79', '551500', null, 'ǭ����', '23', 'ǭ����', '522423', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('27bb4e3175c84dd9a875f0a8c28bf951', '3bc2acb5a13f41f68fd1926c57a97d79', '551800', null, '��ɳ��', '24', '��ɳ��', '522424', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dfd99b8fd37f4f9399a14def9c4c58c7', '3bc2acb5a13f41f68fd1926c57a97d79', '552100', null, '֯����', '25', '֯����', '522425', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f85b80f333c9430aa52b8ae6ff660727', '3bc2acb5a13f41f68fd1926c57a97d79', '553300', null, '��Ӻ��', '26', '��Ӻ��', '522426', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2d617d31c93247dab438828f2725a7c4', '3bc2acb5a13f41f68fd1926c57a97d79', '553100', null, '���������������������', '27', '���������������������', '522427', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6e6c537279314fe38d84cf56735004e1', '3bc2acb5a13f41f68fd1926c57a97d79', '553200', null, '������', '28', '������', '522428', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8e6b1ccf14524654aa85e1954c7a001e', 'eb15b91b9ab84251bc78527a6bc9f98a', '556000', null, '������', '01', '������', '522601', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('48b5bee049584323bd33a73448b86e5f', 'eb15b91b9ab84251bc78527a6bc9f98a', '556100', null, '��ƽ��', '22', '��ƽ��', '522622', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ab12a392b67b497ca4754ed3516b40f1', 'eb15b91b9ab84251bc78527a6bc9f98a', '556200', null, 'ʩ����', '23', 'ʩ����', '522623', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b850acc10d414b749003518564fa1976', 'eb15b91b9ab84251bc78527a6bc9f98a', '556500', null, '������', '24', '������', '522624', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fa822f2dbf3f4c7498af85aa7bc3a1ef', 'eb15b91b9ab84251bc78527a6bc9f98a', '557700', null, '��Զ��', '25', '��Զ��', '522625', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1f9bbc3906b243a98efc1a5f04c12275', 'eb15b91b9ab84251bc78527a6bc9f98a', '557800', null, '᯹���', '26', '᯹���', '522626', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d4f1f94022b14986aed2dd8fd5a6eba3', 'eb15b91b9ab84251bc78527a6bc9f98a', '556600', null, '������', '27', '������', '522627', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('13ac4a6735074f58b47c425c6b91bc77', 'eb15b91b9ab84251bc78527a6bc9f98a', '556700', null, '������', '28', '������', '522628', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1601fa6b767a40ba9fc3fe377888bb83', 'eb15b91b9ab84251bc78527a6bc9f98a', '556400', null, '������', '29', '������', '522629', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('056fb37934f64a35b42d3414beaccfc5', 'eb15b91b9ab84251bc78527a6bc9f98a', '556300', null, '̨����', '30', '̨����', '522630', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f10df2c0262648c19544b04b46a1ca2e', 'eb15b91b9ab84251bc78527a6bc9f98a', '557300', null, '��ƽ��', '31', '��ƽ��', '522631', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('53dd2dffd7bd41b58e283d7c27959d49', 'eb15b91b9ab84251bc78527a6bc9f98a', '557200', null, '�Ž���', '32', '�Ž���', '522632', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f9d3e2ddf13d45e5a0677b659c7413fb', 'eb15b91b9ab84251bc78527a6bc9f98a', '557400', null, '�ӽ���', '33', '�ӽ���', '522633', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b999fec263d14ec8ad4100f454f12f54', 'eb15b91b9ab84251bc78527a6bc9f98a', '557100', null, '��ɽ��', '34', '��ɽ��', '522634', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2c9092e430c133300130ca397f7f00a1', 'bb2c98b3923c431da6a1566bc808c10f', '300000', null, '��������', '16', '��������', '120116', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a5d9ff249d034524a90315f472247034', 'eb15b91b9ab84251bc78527a6bc9f98a', '557600', null, '�齭��', '35', '�齭��', '522635', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('85e176dc8d384e228da1f95b286271bb', 'eb15b91b9ab84251bc78527a6bc9f98a', '557500', null, '��կ��', '36', '��կ��', '522636', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('017a1365ad3c4bbe83f1435d336281dc', '61b919f7685248a3bb577513bd98786c', '558000', null, '������', '01', '������', '522701', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0df30ce828b147e4927e1432aeb76657', '61b919f7685248a3bb577513bd98786c', '550500', null, '��Ȫ��', '02', '��Ȫ��', '522702', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3b9b33fc9b1e468ea94e90ef24115c9d', '61b919f7685248a3bb577513bd98786c', '558400', null, '����', '22', '����', '522722', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ba154b993fb249c08259f389b4143975', '61b919f7685248a3bb577513bd98786c', '551300', null, '����', '23', '����', '522723', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('74d2300e4a5c4eb383d5875c34eace04', '61b919f7685248a3bb577513bd98786c', '550400', null, '�Ͱ���', '25', '�Ͱ���', '522725', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dd5419289e97478fa63f4912850accf5', '61b919f7685248a3bb577513bd98786c', '558200', null, '��ɽ��', '26', '��ɽ��', '522726', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('06ef5cae668e4d7498ea1508b2a6d541', '61b919f7685248a3bb577513bd98786c', '558300', null, 'ƽ����', '27', 'ƽ����', '522727', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('46beb79766904213b1b05b6f147f3f36', '61b919f7685248a3bb577513bd98786c', '550100', null, '�޵���', '28', '�޵���', '522728', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5b89669f6c104646a9d5194e4f720bda', '61b919f7685248a3bb577513bd98786c', '550700', null, '��˳��', '29', '��˳��', '522729', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6c0bcafad32e4c41b76f8be115ffb0b6', '61b919f7685248a3bb577513bd98786c', '551200', null, '������', '30', '������', '522730', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5d4152e338d3445bbefaac9eb136374d', '61b919f7685248a3bb577513bd98786c', '550600', null, '��ˮ��', '31', '��ˮ��', '522731', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8c9cdda1b23842fcb28f2b274f0b62b6', '61b919f7685248a3bb577513bd98786c', '558100', null, '����ˮ��������', '32', '����ˮ��������', '522732', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bb43c05e9b1d4aa586b7c1ce58dd7186', '5ab6b67e085546caa372b13ec883e55d', '650000', null, '��Ͻ��', '01', '��Ͻ��', '530101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b5a38637b18e4290a58a881c0122fc49', '5ab6b67e085546caa372b13ec883e55d', '650000', null, '�廪��', '02', '�廪��', '530102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1afe5616f1a84f87bd69c7faf4533916', '5ab6b67e085546caa372b13ec883e55d', '650000', null, '������', '03', '������', '530103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5e5b350c3f2b4bc8a0ae04ef86fc57e5', '5ab6b67e085546caa372b13ec883e55d', '650200', null, '�ٶ���', '11', '�ٶ���', '530111', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('21cac1a6f7b04849a9423ff1c1a29ee9', '5ab6b67e085546caa372b13ec883e55d', '650100', null, '��ɽ��', '12', '��ɽ��', '530112', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bceeee2d723845ada2b43ecfca662097', '5ab6b67e085546caa372b13ec883e55d', '654100', null, '������', '13', '������', '530113', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('15aba932ca1441d5bf7cc4adf360114a', '5ab6b67e085546caa372b13ec883e55d', '650500', null, '�ʹ���', '21', '�ʹ���', '530121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('28066d2178784bbd833a060e23c49ae8', '5ab6b67e085546caa372b13ec883e55d', '650600', null, '������', '22', '������', '530122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8f1c32f72ba44cdda785aad267490351', '5ab6b67e085546caa372b13ec883e55d', '650400', null, '������', '24', '������', '530124', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7a1a0058f44d4489a99067fd79942677', '5ab6b67e085546caa372b13ec883e55d', '652100', null, '������', '25', '������', '530125', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2c90928a3127bca0013189a8f9003f95', '18f439ed744a48ae89ca7f469af554fd', '150300', null, '������', '12', '������', '230112', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c966215efee4442387a2bda983f2255f', '71565bd32ec9492b81bb4ab47436ddf0', '657900', null, '������', '29', '������', '530629', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('343949dd531740d6b7f28b0accbb7df9', '71565bd32ec9492b81bb4ab47436ddf0', '657800', null, 'ˮ����', '30', 'ˮ����', '530630', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('48a0db09f5404ffeb06f0d87e5901969', '6b376cbe3f17409599ef29acccd2d5f0', '674100', null, '��Ͻ��', '01', '��Ͻ��', '530701', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d83e656e190d4a8e90d6f31a2802a2b8', '6b376cbe3f17409599ef29acccd2d5f0', '674100', null, '�ų���', '02', '�ų���', '530702', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f50d36f83f6a4895814622eea822dd5f', '6b376cbe3f17409599ef29acccd2d5f0', '674100', null, '����������������', '21', '����������������', '530721', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b5d07676f473495bb58a507b469dd7c5', '6b376cbe3f17409599ef29acccd2d5f0', '674200', null, '��ʤ��', '22', '��ʤ��', '530722', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6624c0a2ad7b4be79877b8d26312c0b0', '6b376cbe3f17409599ef29acccd2d5f0', '674800', null, '��ƺ��', '23', '��ƺ��', '530723', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('455e8ef374a14e799b429143d40539bd', '6b376cbe3f17409599ef29acccd2d5f0', '674300', null, '��������������', '24', '��������������', '530724', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('587279fe026a477ba53d4d2d009f4d40', '6343ca91165140d1bcc25205a1235335', '665000', null, '��Ͻ��', '01', '��Ͻ��', '530801', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b77a7f9942314661a212378fe54a44f1', '6343ca91165140d1bcc25205a1235335', '665000', null, '������', '02', '������', '530802', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('56cdd6158c7740f8b8d1cdf8de709bf4', '6343ca91165140d1bcc25205a1235335', '665100', null, '�ն�����������������', '21', '�ն�����������������', '530821', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('92eb8d2e251148369dd9784a6f954158', '6343ca91165140d1bcc25205a1235335', '654800', null, 'ī��������������', '22', 'ī��������������', '530822', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ed31f1801fbf42b38a9c287e26a1fd85', '6343ca91165140d1bcc25205a1235335', '676200', null, '��������������', '23', '��������������', '530823', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ccbb047c951d45f68f640f14d6fa9a63', '6343ca91165140d1bcc25205a1235335', '666400', null, '���ȴ�������������', '24', '���ȴ�������������', '530824', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2b4b4f58b2b743a3bb56ffc49360a186', 'f9f4657fe4aa48bb8d324167750c9b1c', '651200', null, '»����', '31', '»����', '532331', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('41b307a0950a43cba144dde837763a7a', '4ac9c25b5e964e4cae428ca3b28fd2c5', '661000', null, '������', '01', '������', '532501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('20d482618bb4453a8e0006aec885fbbd', '4ac9c25b5e964e4cae428ca3b28fd2c5', '661600', null, '��Զ��', '02', '��Զ��', '532502', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('637033d6bdb044f08fbdf6bc3fd322a5', '4ac9c25b5e964e4cae428ca3b28fd2c5', '661100', null, '������', '22', '������', '532522', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('87e6bcbbdabd466984fa06b8e28f72c5', '4ac9c25b5e964e4cae428ca3b28fd2c5', '661200', null, '��������������', '23', '��������������', '532523', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('acf0bd09432948ec9c9d21820efc2614', '4ac9c25b5e964e4cae428ca3b28fd2c5', '654300', null, '��ˮ��', '24', '��ˮ��', '532524', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bc956477e6bc442799ee47a9a84b3420', '4ac9c25b5e964e4cae428ca3b28fd2c5', '662200', null, 'ʯ����', '25', 'ʯ����', '532525', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3af6216f43c2455492fa0f637e70e3f2', '4ac9c25b5e964e4cae428ca3b28fd2c5', '652300', null, '������', '26', '������', '532526', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6a0c34c5d9c540b0b94d329adf013810', '4ac9c25b5e964e4cae428ca3b28fd2c5', '652400', null, '������', '27', '������', '532527', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('326f7face5e2470cb5298ff520443e22', '4ac9c25b5e964e4cae428ca3b28fd2c5', '662400', null, 'Ԫ����', '28', 'Ԫ����', '532528', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e67d0d64c17746648f5c988cc581f21c', '4ac9c25b5e964e4cae428ca3b28fd2c5', '654400', null, '�����', '29', '�����', '532529', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ea093b6405404465a58dbabf5998f08c', '4ac9c25b5e964e4cae428ca3b28fd2c5', '661500', null, '��ƽ�����������������', '30', '��ƽ�����������������', '532530', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8f25a6e4230d49eba04e9db3c2714b9e', '4ac9c25b5e964e4cae428ca3b28fd2c5', '662500', null, '�̴���', '31', '�̴���', '532531', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('78c3c7b6453148bbb4cf8d81a81687f8', '4ac9c25b5e964e4cae428ca3b28fd2c5', '661300', null, '�ӿ�����������', '32', '�ӿ�����������', '532532', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1de1b9ac298f4f5c8f32a50d0c89975f', '0a7b3b59379a4bb2a99de96b44f607df', '663000', null, '��ɽ��', '21', '��ɽ��', '532621', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b8cb8562f4b3490fa7bc90e48990f71d', '0a7b3b59379a4bb2a99de96b44f607df', '663100', null, '��ɽ��', '22', '��ɽ��', '532622', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4b21dd8c6c94467eabfe5eeb68171450', '0a7b3b59379a4bb2a99de96b44f607df', '663500', null, '������', '23', '������', '532623', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4c1f5007c02441a5b83bb434c4a28e3e', '0a7b3b59379a4bb2a99de96b44f607df', '663600', null, '��������', '24', '��������', '532624', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4723c374d1b0430b8d4f89c29dd4d747', '0a7b3b59379a4bb2a99de96b44f607df', '663700', null, '������', '25', '������', '532625', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b55fb5e1964d42ce887b5fe47ec85767', '0a7b3b59379a4bb2a99de96b44f607df', '663200', null, '����', '26', '����', '532626', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('019bb392f8774673a562763aff19c2da', '0a7b3b59379a4bb2a99de96b44f607df', '663300', null, '������', '27', '������', '532627', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e6e356eeaefd467aaf596cba3e482272', '0a7b3b59379a4bb2a99de96b44f607df', '663400', null, '������', '28', '������', '532628', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c5f1b1cf902540fd95f7a6a9a7ef02b8', 'fe36a09e2995441791c4e76473137fd8', '666100', null, '������', '01', '������', '532801', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('628ca26b3e4447c6a7d72c0008c55ee0', 'fe36a09e2995441791c4e76473137fd8', '666200', null, '�º���', '22', '�º���', '532822', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('724c0ecfd0db416aacac393f709f515f', 'fe36a09e2995441791c4e76473137fd8', '666300', null, '������', '23', '������', '532823', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d1a92a97ffd045c384b755d20ef2c2fe', '7f890338eab6453e9b4015b33fd8abfc', '671000', null, '������', '01', '������', '532901', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('520f899c30ef43ab9088bd87d6baab7c', '7f890338eab6453e9b4015b33fd8abfc', '672500', null, '�������������', '22', '�������������', '532922', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b3774e7674b54c50aa317e6c032b1632', '7f890338eab6453e9b4015b33fd8abfc', '672100', null, '������', '23', '������', '532923', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e7812fab970f47b485a8ee25273dc85d', '7f890338eab6453e9b4015b33fd8abfc', '671600', null, '������', '24', '������', '532924', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('979f061b958b475186d7efc89ab55d73', '7f890338eab6453e9b4015b33fd8abfc', '675600', null, '�ֶ���', '25', '�ֶ���', '532925', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0e3fd978ff034a22a2e4b0c2ce46b748', '7f890338eab6453e9b4015b33fd8abfc', '675700', null, '�Ͻ�����������', '26', '�Ͻ�����������', '532926', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8ba8fe9f40d742a8bbb870f4ee216117', '7f890338eab6453e9b4015b33fd8abfc', '672400', null, 'Ρɽ�������������', '27', 'Ρɽ�������������', '532927', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('159ee8ecf1344557bc3b0e3a9928b1e1', '7f890338eab6453e9b4015b33fd8abfc', '672600', null, '��ƽ��', '28', '��ƽ��', '532928', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('41f833259b1e4d808c8b941ccabb4f88', '7f890338eab6453e9b4015b33fd8abfc', '672700', null, '������', '29', '������', '532929', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dfa70cf952b248f8adbcc1b319eb6141', '7f890338eab6453e9b4015b33fd8abfc', '671200', null, '��Դ��', '30', '��Դ��', '532930', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aad9b0f04a8c45f8ac29afae9075dd98', '7f890338eab6453e9b4015b33fd8abfc', '671300', null, '������', '31', '������', '532931', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3f07917cd59440cc9a788300fb52df0f', '7f890338eab6453e9b4015b33fd8abfc', '671500', null, '������', '32', '������', '532932', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('eaa9b46b1acf465d95ca915f816ebb5b', '45023fee4aae4edfb6a91ea17a0b368f', '678600', null, '������', '02', '������', '533102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1370bcb990884cc181d157a6769eda30', '45023fee4aae4edfb6a91ea17a0b368f', '678400', null, 'º����', '03', 'º����', '533103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('90f054fdbccd474cbe78096c8081de26', '45023fee4aae4edfb6a91ea17a0b368f', '679200', null, '������', '22', '������', '533122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6d02bcd565644d15ae59bd31eefcdd78', '45023fee4aae4edfb6a91ea17a0b368f', '679300', null, 'ӯ����', '23', 'ӯ����', '533123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2aae41ca98754a82b27869c4c03bce3c', '45023fee4aae4edfb6a91ea17a0b368f', '678700', null, '¤����', '24', '¤����', '533124', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4456138cb87e4c5a9963187d0b59c8e5', '7009390a34474afe8e3122be8006dd75', '673200', null, '��ˮ��', '21', '��ˮ��', '533321', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('39fa946c040d4082b268b6c2e63f612b', '2388cbcd7b30404292f8e01ad88e6f8d', '733000', null, '�żҴ�����������', '25', '�żҴ�����������', '620525', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('96f70d51df7a49d6a5ad76c44dbee9e1', 'fb52b2008d774fd2b47b94f855cd16ec', '733000', null, '��Ͻ��', '01', '��Ͻ��', '620601', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9b81bb70a6634c36ba5abf8bc8dff51c', 'fb52b2008d774fd2b47b94f855cd16ec', '733000', null, '������', '02', '������', '620602', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ca45e521a5d84e9283d6eef67fd705b6', 'fb52b2008d774fd2b47b94f855cd16ec', '733300', null, '������', '21', '������', '620621', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('81eb3dcc74f04b7c8dedc4f4251368bd', 'fb52b2008d774fd2b47b94f855cd16ec', '733100', null, '������', '22', '������', '620622', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('87fa13be80ee4280ba1b3dea2e7156fe', 'fb52b2008d774fd2b47b94f855cd16ec', '733200', null, '��ף����������', '23', '��ף����������', '620623', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f930f4298b1a474aa14af0841c71e200', '1a27e665108d48c09c043c88ff56c67d', '734000', null, '��Ͻ��', '01', '��Ͻ��', '620701', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f031b4827c7b4c66929ba86fd1854306', '1a27e665108d48c09c043c88ff56c67d', '734000', null, '������', '02', '������', '620702', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('781094b312a248f3ac9914d53d45bb19', '1a27e665108d48c09c043c88ff56c67d', '734400', null, '����ԣ����������', '21', '����ԣ����������', '620721', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1963d023dcbc4106ac3ebf4e6c3ea656', '1a27e665108d48c09c043c88ff56c67d', '734500', null, '������', '22', '������', '620722', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b7c38fc5da2441b2863517b7d549e980', '1a27e665108d48c09c043c88ff56c67d', '734200', null, '������', '23', '������', '620723', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7f2729c5b20b401191eb3e5309d73057', '1a27e665108d48c09c043c88ff56c67d', '734300', null, '��̨��', '24', '��̨��', '620724', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a9413a38cfaa46198d2c1b2dbe5b0f9e', '1a27e665108d48c09c043c88ff56c67d', '734100', null, 'ɽ����', '25', 'ɽ����', '620725', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3386c8ef99c24167a3a09f4f5d309a45', '0e2ee1874f68474c938f9443a89fcfb1', '744000', null, '��Ͻ��', '01', '��Ͻ��', '620801', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b129b81cd30441418e0355595f55e03a', '0e2ee1874f68474c938f9443a89fcfb1', '744000', null, '�����', '02', '�����', '620802', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e654f56573a84a9a8eaae3c6b640d28e', '0e2ee1874f68474c938f9443a89fcfb1', '744300', null, '������', '21', '������', '620821', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f3ee8949dc124f288a13ff8954ee6220', '0e2ee1874f68474c938f9443a89fcfb1', '744400', null, '��̨��', '22', '��̨��', '620822', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('01cf8f3fe85849eaa0c7edcd58b86798', '0e2ee1874f68474c938f9443a89fcfb1', '744200', null, '������', '23', '������', '620823', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('33ddbf1bc03f4e6e9c0828643210daf6', '0e2ee1874f68474c938f9443a89fcfb1', '744100', null, '��ͤ��', '24', '��ͤ��', '620824', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3920dc68d68f4005b31f9459f0794078', '0e2ee1874f68474c938f9443a89fcfb1', '744600', null, 'ׯ����', '25', 'ׯ����', '620825', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c41e2ddf4fde4a70934609fa5fb9c4e5', '0e2ee1874f68474c938f9443a89fcfb1', '743400', null, '������', '26', '������', '620826', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('6f239b0884c243aa98409bf4ff86aeff', '48a58b66b9394f1aa364c352ba1da9d3', '756400', null, '��Դ��', '24', '��Դ��', '640424', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b1d6bb4ec4e14491ae1a838edd4052cb', '48a58b66b9394f1aa364c352ba1da9d3', '756500', null, '������', '25', '������', '640425', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d520a3f88bad47c3b4ef77796f5c8e4e', '462784740efd4b7787f04c3ca0411c64', '751700', null, '��Ͻ��', '01', '��Ͻ��', '640501', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('463a5c1ffeda482c94f784c9968f4e65', '462784740efd4b7787f04c3ca0411c64', '751700', null, 'ɳ��ͷ��', '02', 'ɳ��ͷ��', '640502', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('fcac9a59209c4c3e9e5a0f9507646a1c', '462784740efd4b7787f04c3ca0411c64', '751200', null, '������', '21', '������', '640521', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1e7ffed91f0a43a595b483b4bbd3b8a5', '462784740efd4b7787f04c3ca0411c64', '751800', null, '��ԭ��', '22', '��ԭ��', '640522', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ada960d4919b4e31974f98e60ab6ad6b', '1bcb916fa3f64e599569392aaaf05b7a', '830000', null, '��Ͻ��', '01', '��Ͻ��', '650101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('9af9ab5574544abd98b7aae9283cfdd2', '1bcb916fa3f64e599569392aaaf05b7a', '830000', null, '��ɽ��', '02', '��ɽ��', '650102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('041b5eab73b74e61b491fd95b012db0b', '1bcb916fa3f64e599569392aaaf05b7a', '830000', null, 'ɳ���Ϳ���', '03', 'ɳ���Ϳ���', '650103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5ae346d18e43486baa026944a9699d6b', '1bcb916fa3f64e599569392aaaf05b7a', '830000', null, '������', '04', '������', '650104', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('e9971b02ae9e4d7894d31fcff4302885', '1bcb916fa3f64e599569392aaaf05b7a', '830000', null, 'ˮĥ����', '05', 'ˮĥ����', '650105', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('275c8d1669ba4d76804bb53660baaaff', '1bcb916fa3f64e599569392aaaf05b7a', '830000', null, 'ͷ�ͺ���', '06', 'ͷ�ͺ���', '650106', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('42ec3d7675f44219814836b51b8253ee', '1bcb916fa3f64e599569392aaaf05b7a', '830000', null, '�������', '07', '�������', '650107', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a024de9be7004b6a8061949e8734a012', '1bcb916fa3f64e599569392aaaf05b7a', '830000', null, '��ɽ��', '08', '��ɽ��', '650108', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2539b660310a4c02a3deb3a302dd4d96', '1bcb916fa3f64e599569392aaaf05b7a', '830000', null, '��³ľ����', '21', '��³ľ����', '650121', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1281da9e93ca4774a8970251a5761f56', '1a1c0daee49a410abe7540db0be7088a', '834000', null, '��Ͻ��', '01', '��Ͻ��', '650201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ebe60ff05c8549a4aed73757f894b616', '1a1c0daee49a410abe7540db0be7088a', '838600', null, '��ɽ����', '02', '��ɽ����', '650202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('661f61a3323a423ca0eab0e3c6bb0470', '1a1c0daee49a410abe7540db0be7088a', '834000', null, '����������', '03', '����������', '650203', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('da63785835e04485b8dd1b0a0c25522f', '1a1c0daee49a410abe7540db0be7088a', '834000', null, '�׼�̲��', '04', '�׼�̲��', '650204', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d5aedba7451045779a87169da7071ebd', '1a1c0daee49a410abe7540db0be7088a', '834000', null, '�ڶ�����', '05', '�ڶ�����', '650205', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('bc9dd0e0b59b430586fdeb27ede90f4d', '3d7c967cb651462cbf8ebf9e1d44676e', '838000', null, '��³����', '01', '��³����', '652101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b78254842d554e2fa27edfd059811071', '3d7c967cb651462cbf8ebf9e1d44676e', '838200', null, '۷����', '22', '۷����', '652122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5372a22653704b4b9188c133c245aa61', '3d7c967cb651462cbf8ebf9e1d44676e', '838100', null, '�п�ѷ��', '23', '�п�ѷ��', '652123', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0cbb7a7eb89444b4bcbcd34e220b9aec', '722028de91e449f9a753de307cdb0d97', '839000', null, '������', '01', '������', '652201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7c2492f86e6d471a9ae2648d31188446', '722028de91e449f9a753de307cdb0d97', '839200', null, '������������������', '22', '������������������', '652222', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d94ba1ff460345a5b502a231b4b5900b', '722028de91e449f9a753de307cdb0d97', '839300', null, '������', '23', '������', '652223', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('026b52173dc740818c362b8bc48e3266', 'abb7d66db87e4d2996304371f67e37a6', '831100', null, '������', '01', '������', '652301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('78653fe0a4284ccc9ca7dc68c5ad31ed', 'abb7d66db87e4d2996304371f67e37a6', '831500', null, '������', '02', '������', '652302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('7d35ee0bdfb14100bc96cab4160bdd8e', 'abb7d66db87e4d2996304371f67e37a6', '831400', null, '��Ȫ��', '03', '��Ȫ��', '652303', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0340c7bc82014a03a12bb4ebd540cf97', 'abb7d66db87e4d2996304371f67e37a6', '831200', null, '��ͼ����', '23', '��ͼ����', '652323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d5884599b7784ade9758891fd25f96d4', 'abb7d66db87e4d2996304371f67e37a6', '832200', null, '����˹��', '24', '����˹��', '652324', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('33863c6a7d9845c8940aae756965eaad', 'abb7d66db87e4d2996304371f67e37a6', '831800', null, '��̨��', '25', '��̨��', '652325', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a8e27352a79f4853bccf2e253b0bc19e', 'abb7d66db87e4d2996304371f67e37a6', '831700', null, '��ľ������', '27', '��ľ������', '652327', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('53574431208a48bf88dec6b8bf8d85ff', 'abb7d66db87e4d2996304371f67e37a6', '831900', null, 'ľ�ݹ�����������', '28', 'ľ�ݹ�����������', '652328', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('d60a9706eb7b442b948b61c1f693ce4b', 'dfcf7241f1744ba1a5102d7ac628aee8', '833400', null, '������', '01', '������', '652701', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4c637a30133b4eb5943c4d67d8e590ef', 'dfcf7241f1744ba1a5102d7ac628aee8', '833300', null, '������', '22', '������', '652722', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('262f7ff4fbd54483bea49eb1538a7e6c', 'dfcf7241f1744ba1a5102d7ac628aee8', '833500', null, '��Ȫ��', '23', '��Ȫ��', '652723', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4af475aace064f4bbce38c8643f2a413', 'b9b5bc2ccede4027a1f74f80e6341da6', '841000', null, '�������', '01', '�������', '652801', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1f9a78ac9d234b26abe58ee20ed07a4d', 'b9b5bc2ccede4027a1f74f80e6341da6', '841600', null, '��̨��', '22', '��̨��', '652822', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5435477e53a24216986930a2ae1f7942', 'b9b5bc2ccede4027a1f74f80e6341da6', '841500', null, 'ξ����', '23', 'ξ����', '652823', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ded1842c986e497995dc66af0d87398a', 'be59c52820d04d2c9b5c91d7f4dae34f', '859200', null, '������', '26', '������', '542526', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b37746d1475b4bd6845f29bd04a2b7bc', 'be59c52820d04d2c9b5c91d7f4dae34f', '859300', null, '������', '27', '������', '542527', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('449e32cd89a941e4aa49def22c13ca3b', '83bc76056d074e4c94c5824d81e94650', '860100', null, '��֥��', '21', '��֥��', '542621', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5fb2bd63dec944e08e2534cc93c6e1ce', '83bc76056d074e4c94c5824d81e94650', '860200', null, '����������', '22', '����������', '542622', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8eceeee425ce425daa640b8dd096a217', '83bc76056d074e4c94c5824d81e94650', '860500', null, '������', '23', '������', '542623', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('dc4c956a438e4107a1bfe0c0433dde73', '83bc76056d074e4c94c5824d81e94650', '860700', null, 'ī����', '24', 'ī����', '542624', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('364b773c899a4a848e5c4e35ea6b4af5', '83bc76056d074e4c94c5824d81e94650', '860300', null, '������', '25', '������', '542625', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('353d6c75aea9465189a16ca7bcd3e775', '83bc76056d074e4c94c5824d81e94650', '860600', null, '������', '26', '������', '542626', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8fbf0edbedc14bf8ac3400151c142638', '83bc76056d074e4c94c5824d81e94650', '860400', null, '����', '27', '����', '542627', null);
commit;
prompt 3500 records committed...
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a9b12c094d2449aa89c02f6d7e046b58', '66d88b8406a8414580256a8811834fea', '710000', null, '��Ͻ��', '01', '��Ͻ��', '610101', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('8dc4f38786954e4f9a8d0e90464825a8', '66d88b8406a8414580256a8811834fea', '710000', null, '�³���', '02', '�³���', '610102', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('16c5ce728e1e4c19b92bbef316315ed0', '66d88b8406a8414580256a8811834fea', '710000', null, '������', '03', '������', '610103', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('3fd112e4114041d59c68ce11c329425a', '66d88b8406a8414580256a8811834fea', '710000', null, '������', '04', '������', '610104', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('0f527782d868482485c021c421106109', '66d88b8406a8414580256a8811834fea', '710000', null, '�����', '11', '�����', '610111', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('791d8a1398aa481aa154e55131ee0d82', '66d88b8406a8414580256a8811834fea', '710000', null, 'δ����', '12', 'δ����', '610112', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('aae9f4db0d44496195ab8d6bfe4ca8bf', '66d88b8406a8414580256a8811834fea', '710000', null, '������', '13', '������', '610113', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('a817344864464a01ad1c0265276e76e9', '66d88b8406a8414580256a8811834fea', '710000', null, '������', '14', '������', '610114', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('c98912768a2648d7b6a8603a393acb28', '66d88b8406a8414580256a8811834fea', '710600', null, '������', '15', '������', '610115', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('1f09605d94f74f59b121db8401fc0d10', '66d88b8406a8414580256a8811834fea', '710100', null, '������', '16', '������', '610116', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('f556dad019e646a48d427b8c5788bb14', '66d88b8406a8414580256a8811834fea', '710500', null, '������', '22', '������', '610122', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5d699224632b4acbba2f6230ae8b2596', '66d88b8406a8414580256a8811834fea', '710400', null, '������', '24', '������', '610124', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('49b0a79a81f34b4fb0641dc2b6020847', '66d88b8406a8414580256a8811834fea', '710300', null, '����', '25', '����', '610125', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('ba2de7ed62674c80b1cd07e83fce55ff', '66d88b8406a8414580256a8811834fea', '710200', null, '������', '26', '������', '610126', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('10744049cae14d3cab9f5ef1308b6743', 'dc7a159373914f47badac80b9cf2017c', '727000', null, '��Ͻ��', '01', '��Ͻ��', '610201', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('54fe4530b2ac4293beb6891e14aea915', 'dc7a159373914f47badac80b9cf2017c', '727000', null, '������', '02', '������', '610202', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cfa1b6672a224757b45ca2189cb63033', 'dc7a159373914f47badac80b9cf2017c', '727000', null, 'ӡ̨��', '03', 'ӡ̨��', '610203', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('49204d6bda5c4199894cbf6b4233e191', 'dc7a159373914f47badac80b9cf2017c', '727100', null, 'ҫ����', '04', 'ҫ����', '610204', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('688189766a1b4688ab1e5e19c35070ea', 'dc7a159373914f47badac80b9cf2017c', '727200', null, '�˾���', '22', '�˾���', '610222', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('4636d819875a42ad8199ddae8b5095ee', '82170bdd8f344d218a4c5ac36f7ecba1', '721000', null, '��Ͻ��', '01', '��Ͻ��', '610301', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b60831fc3e8749798345067aa041a895', '82170bdd8f344d218a4c5ac36f7ecba1', '721000', null, 'μ����', '02', 'μ����', '610302', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('761e129703584c21b3a9d8de8a95e5ee', '82170bdd8f344d218a4c5ac36f7ecba1', '721000', null, '��̨��', '03', '��̨��', '610303', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('5fca70d0160f498d824e96d33f51dcee', '82170bdd8f344d218a4c5ac36f7ecba1', '721300', null, '�²���', '04', '�²���', '610304', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('cebd313ae9404e0aabf84b2df2abc210', '82170bdd8f344d218a4c5ac36f7ecba1', '721400', null, '������', '22', '������', '610322', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('b085bbbd9fe34803b002946c7d0a3da6', '82170bdd8f344d218a4c5ac36f7ecba1', '722400', null, '�ɽ��', '23', '�ɽ��', '610323', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('91964c8a8a3c4c7eb85797f71ec3878e', '82170bdd8f344d218a4c5ac36f7ecba1', '722200', null, '������', '24', '������', '610324', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('62309e52cae742e38703dacfe629a941', '82170bdd8f344d218a4c5ac36f7ecba1', '722300', null, 'ü��', '26', 'ü��', '610326', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2c9092e430e90bb50130e9c90e010167', '2c9092e430e90bb50130e9c865110162', 'δ֪', 'δ֪��', 'δ֪��', '00', 'δ֪��', '000000', null);
insert into BASIC_DISTRICT (ID, PARENTID, POSTALCODE, REMARK, NAME, CODE, FULLNAME, IDCARDCODE, TYPE)
values ('2c9092e43263a8df013266254ddf389c', '2388cbcd7b30404292f8e01ad88e6f8d', '741000', null, '������', '02', '������', '620502', null);
commit;
prompt "webdemoģ��:��ʼ����������  end..." 
prompt "webdemoģ��:��ʼ����������  start..." 
INSERT INTO OPER_OPERATOR(ID,LOGINNAME,PASSWORD,USERNAME)
	values('123456','admin','admin','admin');
INSERT INTO OPER_OPERATOR(ID,LOGINNAME,PASSWORD,USERNAME)
	values('123456001','yr','yr','yr');
INSERT INTO OPER_OPERATOR(ID,LOGINNAME,PASSWORD,USERNAME)
	values('123456002','pqy','pqy','pqy');
commit;--TRUNCATE TABLE OPER_ORGANIZATION;
insert into OPER_ORGANIZATION(PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE)
  values(null,'��Ա','���Ź�˾','���Ź�˾','1000000','1000000');
insert into OPER_ORGANIZATION(PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE)
  values('1000000','��Ա','ϵͳ������','���Ź�˾_ϵͳ������','1100000001','1100000001');
insert into OPER_ORGANIZATION(PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE)
  values('1000000','��Ա','������Դ��','���Ź�˾_ϵͳ������','1100000002','1100000002');
insert into OPER_ORGANIZATION(PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE)
  values('1000000','��Ա','��Ʋ�','���Ź�˾_ϵͳ������','1100000003','1100000003');
insert into OPER_ORGANIZATION(PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE)
  values('1000000','��Ա','�г���','���Ź�˾_�г���','1100000004','1100000004');
insert into OPER_ORGANIZATION(PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE)
  values('1100000004','��Ա','����һ��','���Ź�˾_�г���_����һ��','1100000004001','1100000004001');
insert into OPER_ORGANIZATION(PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE)
  values('1100000004','��Ա','���۶���','���Ź�˾_�г���_���۶���','1100000004002','1100000004002');
insert into OPER_ORGANIZATION(PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE)
  values('1100000004001','��Ա','����һ��','���Ź�˾_�г���_����һ��','1100000004003','1100000004003');
insert into OPER_ORGANIZATION(PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE)
  values('1000000','��Ա','�ֹ�˾A','�ֹ�˾A','1000001','1000001');
insert into OPER_ORGANIZATION(PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE)
  values('1000000','��Ա','�ֹ�˾B','�ֹ�˾B','1000002','1000002');
insert into OPER_ORGANIZATION(PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE)
  values('1000001','��Ա','�ֹ�˾����A','�ֹ�˾����A','1000001001','1000001001');
insert into OPER_ORGANIZATION(PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE)
  values('1000001','��Ա','�ֹ�˾����B','�ֹ�˾����B','1000001002','1000001002');
insert into OPER_ORGANIZATION(PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE)
  values(null,'��Ա','��������˾A','��������˾A','2000000','2000000');
insert into OPER_ORGANIZATION(PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE)
  values(null,'��Ա','��������˾B','��������˾B','3000000','3000000');
insert into OPER_ORGANIZATION(PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE)
  values(2000000,'��Ա','��������˾B���´�A','��������˾B���´�A','2000001','2000001');
commit;
insert into OPER_POST(id,parentId,name,organizationId,code,remark)
	values('1000000301',null,'���ž���','1000000',null,'���Ź�˾ϵͳ���������ž���');
insert into OPER_POST(id,parentId,name,organizationId,code,remark)
	values('100000030101','1000000301','��Ŀ����','1000000',null,'���Ź�˾ϵͳ��������Ŀ����');
insert into OPER_POST(id,parentId,name,organizationId,code,remark)
	values('100000030102','1000000301','SE','1000000',null,'���Ź�˾ϵͳ������SE');
insert into OPER_POST(id,parentId,name,organizationId,code,remark)
	values('10000003010101','100000030101','�߼���������ʦ','1000000',null,'���Ź�˾ϵͳ�������߼���������ʦ');
insert into OPER_POST(id,parentId,name,organizationId,code,remark)
	values('10000003010102','100000030101','����ʦ','1000000',null,'���Ź�˾ϵͳ����������ʦ');
insert into OPER_POST(id,parentId,name,organizationId,code,remark)
	values('10000003010103','100000030101','��������ʦ','1000000',null,'���Ź�˾ϵͳ��������������ʦ');
commit;prompt "webdemoģ��:��ʼ����������  end..." 
prompt "webdemoģ��:��������job  end..."
prompt "webdemoģ��:��������job  end..."