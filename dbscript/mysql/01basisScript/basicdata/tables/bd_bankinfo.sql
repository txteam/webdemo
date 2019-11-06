drop table if exists bd_bankinfo;
create table bd_bankinfo(
	id varchar(64) not null,
	code varchar(64) not null,
	valid bit not null default 1,
	modifyAble bit not null default 1,
	name varchar(64) not null,
	aliases varchar(256) not null,
	logoFileId varchar(64),
	logoUrl varchar(256),
	personalLoginUrl varchar(256),
	institutionLoginUrl varchar(256),
	createDate datetime not null default now(),
	lastUpdateDate datetime not null default now(),
	remark varchar(2000),
	primary key(id)
);
create index idx_bd_bankinfo_00 on bd_bankinfo(code);
