insert into OPER_POST(id,parentId,name,organizationId,code,remark)
	values('1000000301',null,'部门经理','1000000',null,'集团公司系统开发部部门经理');
insert into OPER_POST(id,parentId,name,organizationId,code,remark)
	values('100000030101','1000000301','项目经理','1000000',null,'集团公司系统开发部项目经理');
insert into OPER_POST(id,parentId,name,organizationId,code,remark)
	values('100000030102','1000000301','SE','1000000',null,'集团公司系统开发部SE');
insert into OPER_POST(id,parentId,name,organizationId,code,remark)
	values('10000003010101','100000030101','高级软件工程师','1000000',null,'集团公司系统开发部高级软件工程师');
insert into OPER_POST(id,parentId,name,organizationId,code,remark)
	values('10000003010102','100000030101','工程师','1000000',null,'集团公司系统开发部工程师');
insert into OPER_POST(id,parentId,name,organizationId,code,remark)
	values('10000003010103','100000030101','助理工程师','1000000',null,'集团公司系统开发部助理工程师');
commit;
