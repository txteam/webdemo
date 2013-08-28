--****************************************************************************
-- 表：OPER_POST
--****************************************************************************
create table OPER_POST(
	id varchar2(64 char),
	parentId varchar2(64 char),
	valid number(1,0),
	remark varchar2(2000 char),
	name varchar2(64 char),
	organizationId varchar2(64 char),
	code varchar2(64 char),
	fullName varchar2(64 char),
	primary key(ID)
);
--create index idx_xxxx_xxxx on OPER_POST(xxxx);
--create unique index idx_xxxx_xxxx on OPER_POST(xxxx);

--comment on table OPER_POST is 'demo信息表';
--comment on column OPER_POST.id is 'xxxx';
--comment on column OPER_POST.parentId is 'xxxx';
--comment on column OPER_POST.valid is 'xxxx';
--comment on column OPER_POST.remark is 'xxxx';
--comment on column OPER_POST.name is 'xxxx';
--comment on column OPER_POST.organizationId is 'xxxx';
--comment on column OPER_POST.code is 'xxxx';
--comment on column OPER_POST.fullName is 'xxxx';
