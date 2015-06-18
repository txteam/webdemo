drop table MAINFRAME_LOGIN_LOG;
create table MAINFRAME_LOGIN_LOG(
	CLIENTIPADDRESS varchar(255),
	SYSTEMID varchar(64),
	ORGANIZATIONID varchar(64),
	MESSAGE varchar(255),
	CREATEDATE datetime default now(),
	VCID varchar(64),
	LOGINTYPE smallint not null,
	ID varchar(64)  not null,
	OPERATORID varchar(64),
	OPERATORNAME varchar(64),
	OPERATORLOGINNAME varchar(64),
	primary key(ID)
);
