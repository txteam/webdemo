--****************************************************************************
-- ±í£ºOPER_ORGANIZATION
--****************************************************************************
create table OPER_ORGANIZATION(
  fullAddress varchar2(255),
  remark varchar2(2000),
  code varchar2(64) not null,
  type varchar2(64),
  id varchar2(64) not null,
  parentId varchar2(64),
  districtId varchar2(64),
  chiefType varchar2(64),
  address varchar2(255),
  name varchar2(64) not null,
  alias varchar2(255),
  fullName varchar2(255) not null,
  chiefId varchar2(64),
  primary key(ID)
);
create unique index idx_oper_organ_01 on OPER_ORGANIZATION(code);
create index idx_oper_organ_02 on OPER_ORGANIZATION(parentId);
create index idx_oper_organ_03 on OPER_ORGANIZATION(chiefType,chiefId);
