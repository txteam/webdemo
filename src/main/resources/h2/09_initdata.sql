insert into OPER_OPERATOR(id,loginName,password,username)
	values('123456','admin','admin','admin');
insert into OPER_OPERATOR(id,loginName,password,username)
	values('123456001','yr','yr','yr');
insert into OPER_OPERATOR(id,loginName,password,username)
	values('123456002','pqy','pqy','pqy');
commit;
insert into OPER_OPERATOR(id,loginName,password,username)
	values('123456','admin','admin','admin');
insert into OPER_OPERATOR(id,loginName,password,username)
	values('123456001','yr','yr','yr');
insert into OPER_OPERATOR(id,loginName,password,username)
	values('123456002','pqy','pqy','pqy');
commit;
insert into OPER_ORGANIZATION(VALID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID)
	values(1,null,1,'集团公司','集团公司','1000000');
insert into OPER_ORGANIZATION(VALID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID)
	values(1,'1000000',1,'系统开发部','集团公司_系统开发部','1100000001');
insert into OPER_ORGANIZATION(VALID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID)
	values(1,'1000000',1,'人力资源部','集团公司_系统开发部','1100000002');
insert into OPER_ORGANIZATION(VALID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID)
	values(1,'1000000',1,'审计部','集团公司_系统开发部','1100000003');
insert into OPER_ORGANIZATION(VALID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID)
	values(1,'1000000',1,'市场部','集团公司_市场部','1100000004');
insert into OPER_ORGANIZATION(VALID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID)
	values(1,'1100000004',1,'销售一科','集团公司_市场部_销售一科','1100000004001');
insert into OPER_ORGANIZATION(VALID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID)
	values(1,'1100000004',1,'销售二科','集团公司_市场部_销售二科','1100000004002');
insert into OPER_ORGANIZATION(VALID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID)
	values(1,'1100000004001',1,'销售一组','集团公司_市场部_销售一组','110000000400101');
insert into OPER_ORGANIZATION(VALID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID)
	values(1,'1000000',1,'分公司A','分公司A','1000001');
insert into OPER_ORGANIZATION(VALID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID)
	values(1,'1000000',1,'分公司B','分公司B','1000002');
insert into OPER_ORGANIZATION(VALID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID)
	values(1,'1000001',1,'分公司网点A','分公司网点A','1000001001');
insert into OPER_ORGANIZATION(VALID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID)
	values(1,'1000001',1,'分公司网点B','分公司网点B','1000001002');
insert into OPER_ORGANIZATION(VALID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID)
	values(1,null,1,'合作方公司A','合作方公司A','2000000');
insert into OPER_ORGANIZATION(VALID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID)
	values(1,null,1,'合作方公司B','合作方公司B','3000000');
insert into OPER_ORGANIZATION(VALID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID)
	values(1,2000000,1,'合作方公司B办事处A','合作方公司B办事处A','2000001');
commit;
insert into OPER_POST(id,parentId,name,organizationId,code,valid,remark)
	values('10000001',null,'系统管理员','1000000',null,1,'集团公司系统管理员');
insert into OPER_POST(id,parentId,name,organizationId,code,valid,remark)
	values('10000002',null,'董事','1000000',null,1,'集团公司董事');
insert into OPER_POST(id,parentId,name,organizationId,code,valid,remark)
	values('10000003',null,'总经理','1000000',null,1,'集团公司董事');
insert into OPER_POST(id,parentId,name,organizationId,code,valid,remark)
	values('10000004','10000003','副总经理','1000000',null,1,'集团公司董事');

insert into OPER_POST(id,parentId,name,organizationId,code,valid,remark)
	values('1000000301','10000003','部门经理','1000000',null,1,'集团公司系统开发部部门经理');
insert into OPER_POST(id,parentId,name,organizationId,code,valid,remark)
	values('100000030101','1000000301','项目经理','1000000',null,1,'集团公司系统开发部项目经理');
insert into OPER_POST(id,parentId,name,organizationId,code,valid,remark)
	values('100000030101','1000000301','SE','1000000',null,1,'集团公司系统开发部SE');
insert into OPER_POST(id,parentId,name,organizationId,code,valid,remark)
	values('10000003010101','100000030101','高级软件工程师','1000000',null,1,'集团公司系统开发部高级软件工程师');
insert into OPER_POST(id,parentId,name,organizationId,code,valid,remark)
	values('10000003010102','100000030101','工程师','1000000',null,1,'集团公司系统开发部工程师');
insert into OPER_POST(id,parentId,name,organizationId,code,valid,remark)
	values('10000003010103','100000030101','助理工程师','1000000',null,1,'集团公司系统开发部助理工程师');
commit;
