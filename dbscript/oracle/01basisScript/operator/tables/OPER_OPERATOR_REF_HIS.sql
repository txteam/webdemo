--****************************************************************************
-- 人员引用表：负责处理人员关联角色，关联分组，等关联关系：OPER_OPERATOR
--****************************************************************************
DROP TABLE OPER_OPERATOR_REF_HIS;
CREATE TABLE OPER_OPERATOR_REF_HIS(
	OPERATORID VARCHAR2(64) NOT NULL,
	REFID VARCHAR2(64) NOT NULL,
	REFTYPE VARCHAR2(64) NOT NULL,
	CREATEDATE DATE DEFAULT SYSDATE NOT NULL,
	ENDDATE DATE DEFAULT SYSDATE NOT NULL
);
