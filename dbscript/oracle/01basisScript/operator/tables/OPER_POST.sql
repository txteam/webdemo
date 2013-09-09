--****************************************************************************
-- 职位信息表：OPER_POST
--****************************************************************************
create table OPER_POST(
	id varchar2(64 char) not null,
	parentId varchar2(64 char),
	valid number(1,0) default 1 not null,
	remark varchar2(2000 char),
	name varchar2(64 char),
	organizationId varchar2(64 char),
	code varchar2(64 char),
	fullName varchar2(64 char),
	primary key(ID)
);
create index idx_oper_post_00 on OPER_POST(parentId);
create index idx_oper_post_01 on OPER_POST(code);
create index idx_oper_post_02 on OPER_POST(organizationId);

comment on table OPER_POST is '职位信息表';
comment on column OPER_POST.valid is '是否有效，1：有效 0：无效 默认有效';
