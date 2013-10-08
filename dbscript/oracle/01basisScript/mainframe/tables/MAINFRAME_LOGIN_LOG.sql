--****************************************************************************
-- ±í£ºMAINFRAME_LOGIN_LOG
--****************************************************************************
create table MAINFRAME_LOGIN_LOG(
	CLIENTIPADDRESS varchar2(255),
	SYSTEMID varchar2(64),
	ORGANIZATIONID varchar2(64),
	MESSAGE varchar2(255),
	CREATEDATE date default sysdate not null,
	VCID varchar2(64),
	ID varchar2(64) not null,
	OPERATORID varchar2(64),
	loginType number(1,0) not null,
	primary key(ID)
);
