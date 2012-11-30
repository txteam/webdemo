prompt EXECUTE 02.创建用户.orasql...

-- 创建用户
CREATE USER fnimsadmin IDENTIFIED BY fnimsadmin
  DEFAULT TABLESPACE FNIMS_DAT
  TEMPORARY TABLESPACE FNIMS_TEMP;

GRANT DBA TO fnimsadmin;

--授权
GRANT ALL PRIVILEGE TO fnimsadmin;
ALTER USER fnimsadmin DEFAULT ROLE ALL;

prompt EXECUTE 02.创建用户.orasql...DONE.

EXIT;

