--****************************************************************************
-- 组织信息表历史表：OPER_ORGANIZATION_HIS
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
