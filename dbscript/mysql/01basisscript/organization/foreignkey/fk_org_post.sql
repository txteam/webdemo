ALTER TABLE ORG_POST ADD CONSTRAINT FK_POST_01 FOREIGN KEY(parentId) REFERENCES ORG_POST(id);
ALTER TABLE ORG_POST ADD CONSTRAINT FK_POST_02 FOREIGN KEY(organizationId) REFERENCES ORG_ORGANIZATION(id);
ALTER TABLE ORG_POST ADD CONSTRAINT FK_POST_03 FOREIGN KEY(vcid) REFERENCES VC_VIRTUAL_CENTER(id);
