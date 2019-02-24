drop table if exists oper_role;
create table oper_role(
	id varchar(64) not null,		-- ����
	vcid varchar(64) not null,		-- ������
	name varchar(64) not null,		-- ��ɫ��
	roleKey varchar(64),	-- ��Ӧ�Ľ�ɫö��
	roleType varchar(64),			-- ��ɫ����
	valid bit default 1 not null,	-- �Ƿ���Ч
	editAble bit default 1 not null,-- �Ƿ�ɱ༭
	remark varchar(2000),			-- ��ע
	code varchar(64),				-- ����
	primary key(ID)
);
create unique index idx_oper_role_01 on oper_role(vcid,roleType,name);
