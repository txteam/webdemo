--****************************************************************************
-- 人员引用表：负责处理人员关联角色，关联分组，等关联关系：OPER_OPERATOR
--****************************************************************************
DROP TABLE OPER_OPERATOR_REF_HIS;
CREATE TABLE OPER_OPERATOR_REF_HIS(
	EFFECTIVEDATE timestamp,
	CREATEDATE timestamp default sysdate not null,
	OPERATORID varchar2(64 char) not null,
	REFID varchar2(64 char) not null,
	ENDDATE timestamp default sysdate not null,
	REFTYPE varchar2(255 char) not null
);
CREATE INDEX IDX_OPER_OPERREF_HIS_00 ON OPER_OPERATOR_REF(OPERATORID,REFTYPE);
CREATE INDEX IDX_OPER_OPERREF_HIS_01 ON OPER_OPERATOR_REF(REFID,REFTYPE);
CREATE INDEX IDX_OPER_OPERREF_HIS_10 ON OPER_OPERATOR_REF(OPERATORID,REFID,REFTYPE);
