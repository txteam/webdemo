--****************************************************************************
-- 职位信息表历史表：OPER_POST_HIS
--****************************************************************************
create table OPER_POST(
	id varchar(64) not null,
	parentId varchar(64),
	remark varchar(2000),
	name varchar(64),
	organizationId varchar(64),
	code varchar(64),
	fullName varchar(64),
	primary key(ID)
);
