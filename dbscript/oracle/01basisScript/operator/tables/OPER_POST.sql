--****************************************************************************
-- 职位信息表：OPER_POST
--****************************************************************************
DROP TABLE OPER_POST;
CREATE TABLE OPER_POST(
	id varchar2(64) not null,
	parentId varchar2(64),
	remark varchar2(2000),
	name varchar2(64),
	organizationId varchar2(64),
	code varchar2(64),
	fullName varchar2(255),
	primary key(ID)
);
create index idx_oper_post_00 on OPER_POST(parentId);
create index idx_oper_post_01 on OPER_POST(code);
create index idx_oper_post_02 on OPER_POST(organizationId);

comment on table OPER_POST is '职位信息表';
comment on column OPER_POST.code is '职位编码';
