drop table if exists oper_role_his;
create table oper_role_his(
	id varchar(64) not null,		-- 主键
	vcid varchar(64) not null,		-- 虚中心
	name varchar(64) not null,		-- 角色名
	roleKey varchar(64),	-- 对应的角色枚举
	roleType varchar(64),			-- 角色类型
	valid bit default 1 not null,	-- 是否有效
	editAble bit default 1 not null,-- 是否可编辑
	remark varchar(2000),			-- 备注
	code varchar(64),				-- 编码
	primary key(ID)
);
