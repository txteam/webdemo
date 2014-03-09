/*****************************************************************************
			表：OPER_POSTTYPE
*****************************************************************************/
create table OPER_POSTTYPE(
	id varchar2(64 char),
	valid number(1,0),
	remark varchar2(2000 char),
	name varchar2(64 char),
	code varchar2(64 char),
	createDate timestamp,
	lastUpdateDate timestamp,
	primary key(ID)
);
create unique index idx_oper_posttype_00 on OPER_POSTTYPE(code);
create unique index idx_oper_posttype_01 on OPER_POSTTYPE(name);
