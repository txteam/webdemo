drop table if exists mainframe_login_log;
CREATE TABLE mainframe_login_log(
	id varchar(64)  NOT NULL,
	vcid varchar(64), 								-- 虚中心
	createDate datetime NOT NULL DEFAULT sysdate(),	-- 创建时间
	loginType TINYINT(1) NOT NULL,
	clientIpAddress varchar(255),
	systemId varchar(64),
	organizationId varchar(64),
	message varchar(255),
	operatorId varchar(64),
	operatorName varchar(64),
	operatorLoginName varchar(64),
	primary key(id)
);

