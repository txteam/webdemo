--****************************************************************************
-- ±í£ºMAINFRAME_SYSOPE_LOG
--****************************************************************************
create table MAINFRAME_SYSOPE_LOG(
	CLIENTIPADDRESS varchar2(255 char),
	FUNCTION varchar2(255 char),
	SYSTEMID varchar2(64 char),
	ORGANIZATIONID varchar2(64 char),
	MESSAGE varchar2(255 char),
	CREATEDATE timestamp default sysdate not null,
	VCID varchar2(64 char),
	ID varchar2(64 char) not null,
	OPERATORID varchar2(64 char),
	OPERATORNAME varchar2(64 char),
	OPERATORLOGINNAME varchar2(64 char),
	primary key(ID)
);
