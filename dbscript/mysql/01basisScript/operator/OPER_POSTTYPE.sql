/*****************************************************************************
			表：OPER_POSTTYPE
*****************************************************************************/
create table OPER_POSTTYPE(
	id varchar(64),
	valid bit,
	remark varchar(2000),
	name varchar(64),
	code varchar(64),
	createDate datetime,
	lastUpdateDate datetime,
	primary key(ID)
);
create unique index idx_oper_posttype_00 on OPER_POSTTYPE(code);
create unique index idx_oper_posttype_01 on OPER_POSTTYPE(name);

