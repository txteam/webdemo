ALTER TABLE OPER_OPERATOR ADD CONSTRAINT FK_OPERATOR_01 FOREIGN KEY(vcid) REFERENCES VC_VIRTUAL_CENTER(id);
ALTER TABLE OPER_OPERATOR ADD CONSTRAINT FK_OPERATOR_02 FOREIGN KEY(organizationId) REFERENCES ORG_ORGANIZATION(id);
ALTER TABLE OPER_OPERATOR ADD CONSTRAINT FK_OPERATOR_03 FOREIGN KEY(mainPostId) REFERENCES ORG_POST(id);
