--****************************************************************************
-- 表：OPER_POST
--****************************************************************************
create table OPER_POST(
	id varchar(64),
	parentId varchar(64),
	valid boolean,
	remark varchar(2000),
	name varchar(64),
	organizationId varchar(64),
	code varchar(64),
	fullName varchar(64),
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
