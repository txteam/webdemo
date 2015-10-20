drop table if exists mainframe_sysope_log;
create table mainframe_sysope_log(
	id varchar(64) not null,
	clientIpAddress varchar(255),
	systemId varchar(64),
	function varchar(255),
	organizationId varchar(64),
	message varchar(255),
	createDate default now(),
	vcid varchar(64),
	operatorId varchar(64),
	operatorName varchar(64),
	operatorLoginName varchar(64),
	primary key(ID)
);
