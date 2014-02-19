DROP TABLE OPER_ORGANIZATION_HIS;
CREATE TABLE OPER_ORGANIZATION_HIS(
  vcid varchar(64),
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
