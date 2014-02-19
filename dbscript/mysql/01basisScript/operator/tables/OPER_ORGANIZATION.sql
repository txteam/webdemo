DROP TABLE OPER_ORGANIZATION;
CREATE TABLE OPER_ORGANIZATION(
  vcid varchar(64) not null,
  valid smallint default 1 not null,
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
create unique index idx_oper_organ_01 on OPER_ORGANIZATION(code);
create index idx_oper_organ_02 on OPER_ORGANIZATION(vcid);
create index idx_oper_organ_03 on OPER_ORGANIZATION(parentId);
create index idx_oper_organ_04 on OPER_ORGANIZATION(chiefType,chiefId);
