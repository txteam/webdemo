create table t_auth_authitem
(
  id varchar(64) not null,	
  parentId varchar(64),	
  name varchar(256),	
  description varchar(1024),		
  authType varchar(64) not null, 
  isViewAble integer default 1,	
  isEditAble integer default 1,	
  isValid integer default 1,		
  primary key(id)
);
create table t_auth_authref
(
  authid varchar(128) not null,
  refid  varchar(64) not null,
  authreftype varchar(64) not null,
  createdate date default sysdate not null,
  enddate date,
  createoperid varchar(64) not null,
  primary key(authid,refid,authreftype)
);
create index idx_auth_authref_02 on t_auth_authref(refid,authreftype);
create index idx_auth_authref_03 on t_auth_authref(enddate);

create table t_auth_role
(
  id varchar(64) not null,
  name varchar(64) not null,
  creatOperId varchar(64) not null,
  createdate date default sysdate not null,
  updatedate date,
  updateOperId varchar(64),
  description varchar(200),
  isDefault varchar(2) default 0,
  primary key (id)
);
create unique index idx_auth_role_01 on t_auth_role(name);
create index idx_auth_role_02 on t_auth_role (creatOperId);

