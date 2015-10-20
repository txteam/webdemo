create table OPER_VC(
	id varchar(64) not null,  	-- 虚中心id
	parentId varchar(64),		-- 上级虚中心id
	code varchar(64),			-- 虚中心code
	name varchar(64) not null,	-- 虚中心名称
	remark varchar(2000),		-- 虚中心备注
	primary key(ID)
);
create unique index IDX_OPER_VC_01 on OPER_VC(name);
create unique index IDX_OPER_VC_02 on OPER_VC(code);
