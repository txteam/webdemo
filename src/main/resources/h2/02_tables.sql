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

--comment on table t_auth_authref is '权限关联项信息表';
--comment on column t_auth_authref.authid is '权限id';
--comment on column t_auth_authref.refId is '关联项id可以为角色id,用户id等';
--comment on column t_auth_authref.authreftype is '权限关联项类型 AUTHREFTYPE_OPERATOR AUTHREFTYPE_OPERATOR_TEMP AUTHREFTYPE_ROLE';
--comment on column t_auth_authref.createdate is '权限关联项创建时间';
--comment on column t_auth_authref.enddate is '权限关联项目失效时间';
--comment on column t_auth_authref.createoperid is '权限授予人';

--****************************************************************************
-- 角色信息表
--****************************************************************************
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

--comment on table t_auth_role is '角色信息表';
--comment on column t_auth_role.id is '角色id';
--comment on column t_auth_role.name is '角色名';
--comment on column t_auth_role.createOperId is '角色创建者';
--comment on column t_auth_role.updatedate is '角色创建时间';
--comment on column t_auth_role.updateOperId is '角色最后修改者';
--comment on column t_auth_role.updatedate is '角色最后修改时间';
--comment on column t_auth_role.description is '角色描述';

