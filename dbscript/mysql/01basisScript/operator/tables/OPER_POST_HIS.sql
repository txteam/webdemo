drop table OPER_POST_HIS;
create table OPER_POST_HIS(
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
