--****************************************************************************
-- 职位信息表历史表：OPER_POST
--****************************************************************************
drop table OPER_POST_HIS;
create table OPER_POST_HIS(
	id varchar2(64) not null,
	parentId varchar2(64),
	remark varchar2(2000),
	name varchar2(64),
	organizationId varchar2(64),
	code varchar2(64),
	fullName varchar2(255),
	primary key(ID)
);
