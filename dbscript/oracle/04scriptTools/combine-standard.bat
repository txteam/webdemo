call clearInput.bat

cd "00webdemo"
  call standard-combine.bat
cd ..

cd "01auth"
  call standard-combine.bat
cd ..

cd "02calendar"
  call standard-combine.bat
cd ..

cd "03notepad"
  call standard-combine.bat
cd ..

mkdir oracleStandard
type scriptInput\tables\*.sql     >oracleStandard\02_tables.sql

type scriptInput\sequences\*.sql  >oracleStandard\03_sequences.sql

type scriptInput\packages\*.sql   >oracleStandard\04_packages.sql

type scriptInput\functions\*.sql  >oracleStandard\05_functions.sql

type scriptInput\procedures\*.sql >oracleStandard\06_procedures.sql

type scriptInput\triggers\*sql    >oracleStandard\07_triggers.sql

type scriptInput\views\*.sql      >oracleStandard\08_views.sql

type scriptInput\initdata\*.sql   >oracleStandard\09_initdata.sql

type scriptInput\jobs\*.sql       >oracleStandard\10_jobs.sql

copy 01_create_database.sql   oracleStandard\00_tablespace.sql

copy 02_create_user.sql   oracleStandard\01_createuser.sql

type oracleStandard\*.sql >oracleStandard.sql

