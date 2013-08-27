--****************************************************************************
-- 表：OPER_POST
--****************************************************************************
create table OPER_POST(
	id varchar(64),
	valid bit,
	organization varchar(64),
	name varchar(64),
	code varchar(64),
	primary key(ID)
);
--create index idx_xxxx_xxxx on OPER_POST(xxxx);
--create unique index idx_xxxx_xxxx on OPER_POST(xxxx);

--comment on table OPER_POST is 'demo信息表';
--comment on column OPER_POST.id is 'xxxx';
--comment on column OPER_POST.valid is 'xxxx';
--comment on column OPER_POST.organization is 'xxxx';
--comment on column OPER_POST.name is 'xxxx';
--comment on column OPER_POST.code is 'xxxx';
