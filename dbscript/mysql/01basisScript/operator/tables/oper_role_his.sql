drop table if exists oper_role_his;
create table oper_role_his(
	id varchar(64) not null,
	name varchar(64) not null,
	vcid varchar(64),
	valid bit not null default 1,
	modifyAble bit not null default 1,
	remark varchar(512) ,
	primary key(id,vcid)
);
