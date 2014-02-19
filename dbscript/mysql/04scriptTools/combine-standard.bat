del "mainframe" /f /s /q
del "operator" /f /s /q

del scriptInput /f /s /q
xcopy ..\01basisScript\. /s /y
mkdir scriptInput\functions
mkdir scriptInput\initdata
mkdir scriptInput\jobs
mkdir scriptInput\packages
mkdir scriptInput\procedures
mkdir scriptInput\sequences
mkdir scriptInput\tables
mkdir scriptInput\triggers
mkdir scriptInput\views

cd "mainframe"
  call standard-combine.bat
cd ..
cd "operator"
  call standard-combine.bat
cd ..

del mysqlStandard /f /s /q
mkdir mysqlStandard
type scriptInput\tables\*.sql     >mysqlStandard\02_tables.sql
type scriptInput\sequences\*.sql  >mysqlStandard\03_sequences.sql
type scriptInput\packages\*.sql   >mysqlStandard\04_packages.sql
type scriptInput\functions\*.sql  >mysqlStandard\05_functions.sql
type scriptInput\procedures\*.sql >mysqlStandard\06_procedures.sql
type scriptInput\triggers\*sql    >mysqlStandard\07_triggers.sql
type scriptInput\views\*.sql      >mysqlStandard\08_views.sql
type scriptInput\initdata\*.sql   >mysqlStandard\09_initdata.sql
type scriptInput\jobs\*.sql       >mysqlStandard\10_jobs.sql
del mysqlStandard.sql /f /s /q
type mysqlStandard\*.sql >mysqlStandard.sql

copy 01_create_database.sql   mysqlStandard\00_tablespace.sql
copy 02_create_user.sql   mysqlStandard\01_createuser.sql
pause