--****************************************************************************
-- 组织信息表历史表：OPER_ORGANIZATION_HIS
--****************************************************************************
create table OPER_ORGANIZATION_HIS(
  id varchar2(64) not null,
  parentId varchar2(64),
  name varchar2(64) not null,
  fullName varchar2(255) not null,
  code varchar2(64) not null,
  type varchar2(64),
  alise varchar2(255),
  address varchar2(255),
  fullAddress varchar2(255),
  remark varchar2(2000),
  districtId varchar2(64),
  chiefType varchar2(64),
  chiefId varchar2(64),
  primary key(ID)
);
