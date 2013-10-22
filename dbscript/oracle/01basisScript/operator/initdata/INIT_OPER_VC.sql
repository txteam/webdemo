INSERT INTO OPER_VC(ID,PARENTID,NAME,REMARK)
	values('001',null,'集团公司','集团公司');
INSERT INTO OPER_VC(ID,PARENTID,NAME,REMARK)
	values('00101','001','分公司一','分公司一');
INSERT INTO OPER_VC(ID,PARENTID,NAME,REMARK)
	values('00102','001','分公司二','分公司二');
INSERT INTO OPER_VC(ID,PARENTID,NAME,REMARK)
	values('002','001','合作方公司A','合作方公司A');
INSERT INTO OPER_VC(ID,PARENTID,NAME,REMARK)
	values('003','001','合作方公司B','合作方公司B');
commit;
