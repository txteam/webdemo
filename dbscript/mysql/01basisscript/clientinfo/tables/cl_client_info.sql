/*****************************************************************************
-- CL_CLIENT_INFO : 
*****************************************************************************/
drop table if exists CL_CLIENT_INFO;
create table CL_CLIENT_INFO(
	id varchar(64) not null,
	vcid varchar(64) not null,
	type varchar(64) not null,
	code varchar(64) not null,
	username varchar(64) not null,
	password varchar(64) not null,
	name varchar(64),
	status varchar(64),
	valid bit not null default 1,
	invalidDate datetime(6),
	locked bit not null default 0,
	usernameChangeAble bit not null default 1,
	usernameChangeCount integer not null default 0,
	hisPwd varchar(64),
	pwdErrCount integer not null default 0,
	pwdUpdateDate datetime(6),
	pwdLastErrDate datetime(6),
	mobileNumber varchar(64),
	mobileBinding bit not null default 0,
	mobileLoginEnable bit not null default 0,
	email varchar(64),
	emailBinding bit not null default 0,
	idCardNumber varchar(64),
	idCardType varchar(64),
	idCardExpiryDate datetime(6) ,
	realNameAuthenticated bit not null default 0,
	realNameErrCount integer not null default 0,
	realNameLastErrDate datetime(6),
	creditInfoId varchar(64),
	creditInfoBinding bit not null default 0,
	payPassword varchar(64) ,
	hisPayPwd varchar(64) ,
	payPwdErrCount integer ,
	payPwdUpdateDate datetime(6),
	payPwdLastErrDate datetime(6),
	promotionChannelId varchar(64) ,
	clientSourceId varchar(64) ,
	referralCode varchar(64) ,
	lastUpdateDate datetime(6) not null default now(6),
	createDate datetime(6) not null default now(6),
	primary key(id)
);
create unique index IDX_CLIENT_INFO_00 on CL_CLIENT_INFO(username);
create unique index IDX_CLIENT_INFO_01 on CL_CLIENT_INFO(code);
create index IDX_CLIENT_INFO_02 on CL_CLIENT_INFO(createDate);

