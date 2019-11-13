drop table if exists oper_role_his;
create table oper_role_his(
	id varchar(64) not null,
	code varchar(64) ,
	name varchar(64) ,
	vcid varchar(64) ,
	valid bit ,
	modifyAble bit ,
	remark varchar(512) ,
	primary key(id)
);
