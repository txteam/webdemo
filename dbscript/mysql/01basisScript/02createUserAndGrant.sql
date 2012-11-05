--****************************************************************************
--create user and grant
--****************************************************************************
GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,ALTER ON 
	db_webdemo.* TO db_webdemo@localhost IDENTIFIED BY 'db_webdemo';
SET PASSWORD FOR 'db_webdemo'@'localhost' = OLD_PASSWORD('db_webdemo');