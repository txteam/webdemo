DROP TABLE OPER_POST;
CREATE TABLE OPER_POST(
	id varchar(64) not null,
	valid smallint default 1 not null,
	parentId varchar(64),
	remark varchar(2000),
	name varchar(64),
	organizationId varchar(64),
	code varchar(64),
	fullName varchar(255),
	primary key(ID)
);
create index idx_oper_post_00 on OPER_POST(parentId);
create index idx_oper_post_01 on OPER_POST(code);
create index idx_oper_post_02 on OPER_POST(organizationId);
