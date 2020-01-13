INSERT INTO OPER_OPERATOR(id,vcid,organizationId,username,password,modifyAble,name)
	values('123456','JT','1000000000','admin','E10ADC3949BA59ABBE56E057F20F883E',0,'超级管理员');
INSERT INTO OPER_OPERATOR(id,vcid,organizationId,username,password,modifyAble,name)
	values(REPLACE(UUID(),"-",""),'JT','1000000000','pqy','E10ADC3949BA59ABBE56E057F20F883E',1,'彭清杨');
INSERT INTO OPER_OPERATOR(id,vcid,organizationId,username,password,modifyAble,name)
	values(REPLACE(UUID(),"-",""),'JT','1000000000','zlk','E10ADC3949BA59ABBE56E057F20F883E',1,'张礼科');
INSERT INTO OPER_OPERATOR(id,vcid,organizationId,username,password,modifyAble,name)
	values(REPLACE(UUID(),"-",""),'JT','1000000000','zw','E10ADC3949BA59ABBE56E057F20F883E',1,'张威');
INSERT INTO OPER_OPERATOR(id,vcid,organizationId,username,password,modifyAble,name)
	values(REPLACE(UUID(),"-",""),'JT','1000000000','xrx','E10ADC3949BA59ABBE56E057F20F883E',1,'徐茹霞');
commit;