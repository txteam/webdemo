-- create user
create table st(
	id varchar(64) not null,
	name varchar(64),
	lastUpdateDate datetime(6) not null default now(6),
	createDate datetime(6) not null default now(6),
	primary key(id)
);

create table c_st(
	id varchar(64) not null,
	st_Id varchar(64) not null,
	name varchar(64),
	lastUpdateDate datetime(6) not null default now(6),
	createDate datetime(6) not null default now(6),
	primary key(id)
);

create table c_c_st(
	id varchar(64) not null,
	st_Id varchar(64) not null,
	c_st_Id varchar(64) not null,
	name varchar(64),
	lastUpdateDate datetime(6) not null default now(6),
	createDate datetime(6) not null default now(6),
	primary key(id)
);

ALTER TABLE c_st ADD CONSTRAINT c_st_fk_01
FOREIGN KEY(st_Id) REFERENCES st (id);


ALTER TABLE c_c_st ADD CONSTRAINT c_c_st_fk_01
FOREIGN KEY(st_Id) REFERENCES st (id);
ALTER TABLE c_c_st ADD CONSTRAINT c_c_st_fk_02
FOREIGN KEY(c_st_Id) REFERENCES c_st (id);

insert into st(id,name) value('001','001');
insert into st(id,name) value('002','002');

insert into c_st(id,name,st_Id) value('002_01','002_01','002');
insert into c_st(id,name,st_Id) value('002_02','002_02','002');

insert into c_c_st(id,name,st_Id,c_st_Id) value('002_02_01','002_02_01','002','002_02');
insert into c_c_st(id,name,st_Id,c_st_Id) value('002_02_02','002_02_02','002','002_02');


SELECT
	R.*,
	C.TABLE_SCHEMA 拥有者,
	C.REFERENCED_TABLE_NAME 父表名称,
	C.REFERENCED_COLUMN_NAME 父表字段,
	C.TABLE_NAME 子表名称,
	C.COLUMN_NAME 子表字段,
	C.CONSTRAINT_NAME 约束名,
	T.TABLE_COMMENT 表注释,
	R.UPDATE_RULE 约束更新规则,
	R.DELETE_RULE 约束删除规则,
	CONCAT(
		'ALTER TABLE ',C.TABLE_NAME,
		' DROP foreign key ',
		C.CONSTRAINT_NAME,
		';'
	) AS dropsql,
	CONCAT(
		'ALTER TABLE ',
		C.TABLE_NAME,
		' ADD CONSTRAINT ',
		C.CONSTRAINT_NAME,
		' FOREIGN KEY( ',
		C.COLUMN_NAME,
		' ) REFERENCES ',
		C.REFERENCED_TABLE_NAME,
		' ( ',
		C.REFERENCED_COLUMN_NAME,
		' ); '
	) AS createsql,
	CONCAT(
		'ALTER TABLE ',
		C.TABLE_NAME,
		' ADD CONSTRAINT ',
		C.CONSTRAINT_NAME,
		' FOREIGN KEY( ',
		C.COLUMN_NAME,
		' ) REFERENCES ',
		C.REFERENCED_TABLE_NAME,
		' ( ',
		C.REFERENCED_COLUMN_NAME,
		' ) ON DELETE CASCADE ON UPDATE CASCADE; '
	) AS createsql_casecade
FROM
	INFORMATION_SCHEMA.KEY_COLUMN_USAGE C
JOIN INFORMATION_SCHEMA.TABLES T ON (T.TABLE_NAME = C.TABLE_NAME AND C.TABLE_SCHEMA = T.TABLE_SCHEMA)
JOIN INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS R ON (
	R.TABLE_NAME = C.TABLE_NAME 
	AND R.CONSTRAINT_SCHEMA = C.TABLE_SCHEMA
	AND R.CONSTRAINT_NAME = C.CONSTRAINT_NAME
	AND R.REFERENCED_TABLE_NAME = C.REFERENCED_TABLE_NAME
)
WHERE
	C.REFERENCED_TABLE_NAME IS NOT NULL
AND c.TABLE_SCHEMA = 'webdemo_new';、

SET FOREIGN_KEY_CHECKS = 0;
ALTER TABLE c_c_st DROP foreign key c_c_st_fk_02;
ALTER TABLE c_c_st DROP foreign key c_c_st_fk_01;
ALTER TABLE c_st DROP foreign key c_st_fk_01;
SET FOREIGN_KEY_CHECKS = 1;

SET FOREIGN_KEY_CHECKS = 0;
ALTER TABLE c_c_st ADD CONSTRAINT c_c_st_fk_02 FOREIGN KEY( c_st_Id ) REFERENCES c_st ( id ); 
ALTER TABLE c_c_st ADD CONSTRAINT c_c_st_fk_01 FOREIGN KEY( st_Id ) REFERENCES st ( id ); 
ALTER TABLE c_st ADD CONSTRAINT c_st_fk_01 FOREIGN KEY( st_Id ) REFERENCES st ( id ); 
SET FOREIGN_KEY_CHECKS = 1;

SET FOREIGN_KEY_CHECKS = 0;
ALTER TABLE c_c_st ADD CONSTRAINT c_c_st_fk_02 FOREIGN KEY( c_st_Id ) REFERENCES c_st ( id ) ON DELETE CASCADE; 
ALTER TABLE c_c_st ADD CONSTRAINT c_c_st_fk_01 FOREIGN KEY( st_Id ) REFERENCES st ( id ) ON DELETE CASCADE; 
ALTER TABLE c_st ADD CONSTRAINT c_st_fk_01 FOREIGN KEY( st_Id ) REFERENCES st ( id ) ON DELETE CASCADE; 
SET FOREIGN_KEY_CHECKS = 1;
