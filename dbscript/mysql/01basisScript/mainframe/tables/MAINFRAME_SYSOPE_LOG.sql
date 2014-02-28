drop table MAINFRAME_SYSOPE_LOG;
create table MAINFRAME_SYSOPE_LOG(
	CLIENTIPADDRESS varchar(255),
	FUNCTION varchar(255),
	SYSTEMID varchar(64),
	ORGANIZATIONID varchar(64),
	MESSAGE varchar(255),
	CREATEDATE default now(),
	VCID varchar(64),
	ID varchar(64) not null,
	OPERATORID varchar(64),
	OPERATORNAME varchar(64),
	OPERATORLOGINNAME varchar(64),
	primary key(ID)
);
