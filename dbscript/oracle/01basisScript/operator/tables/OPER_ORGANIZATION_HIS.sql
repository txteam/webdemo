--****************************************************************************
-- 组织信息表历史表：OPER_ORGANIZATION_HIS
--****************************************************************************
DROP TABLE OPER_ORGANIZATION_HIS;
CREATE TABLE OPER_ORGANIZATION_HIS(
  vcid varchar2(64) not null,
  valid number(1,0) default 1 not null,
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
  fullName varchar2(64) not null,
  chiefId varchar2(64),
  primary key(ID)
);
