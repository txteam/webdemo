drop table if exists OPER_OPERATOR_HIS;
create table OPER_OPERATOR_HIS(
	id varchar(64) not null,
	vcid varchar(64) not null,
	username varchar(64) not null,
	usernameChangeCount int not null default 0,
	usernameChangeAble bit not null default 1,
	name varchar(64),
	organizationId varchar(64) not null,
	mainPostId varchar(64),
	password varchar(64) not null,
	historyPwd varchar(64),
	pwdErrCount integer not null default 0,
	pwdUpdateDate datetime(6),
	examinePwd varchar(64),
	locked bit not null default 0,
	modifyAble bit not null default 1,
	valid bit not null default 1,
	invalidDate datetime(6),
	lastUpdateDate datetime(6) not null default now(6),
	createDate datetime(6) not null default now(6),
	primary key(id)
);
