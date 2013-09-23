--****************************************************************************
-- 表：OPER_VC
--****************************************************************************
create table OPER_VC(
	id varchar2(64) not null,
	parentId varchar2(64),
	remark varchar2(2000),
	name varchar2(64) not null,
	primary key(ID)
);
create unique index idx_OPER_VC on OPER_VC(name);

comment on table OPER_VC is '虚中心信息表';
comment on column OPER_VC.name is '虚中心名';
