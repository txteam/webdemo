INSERT INTO OPER_OPERATOR(ID,ORGANIZATIONID,LOGINNAME,PASSWORD,USERNAME)
	values('123456','1000000','admin','admin','admin');
INSERT INTO OPER_OPERATOR(ID,ORGANIZATIONID,LOGINNAME,PASSWORD,USERNAME)
	values('123456001','1000000','yr','yr','yr');
INSERT INTO OPER_OPERATOR(ID,ORGANIZATIONID,LOGINNAME,PASSWORD,USERNAME)
	values('123456002','1000000','pqy','pqy','pqy');
update oper_operator t set t.createDate = now(),t.invalidDate = null,t.lastUpdateDate = now(),t.pwdUpdateDate = now();
commit;
