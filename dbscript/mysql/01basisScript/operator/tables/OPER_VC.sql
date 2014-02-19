drop table OPER_VC;
create table OPER_VC(
	id varchar(64) not null,
	parentId varchar(64),
	remark varchar(2000),
	name varchar(64) not null,
	primary key(ID)
);
create unique index idx_OPER_VC on OPER_VC(name);
