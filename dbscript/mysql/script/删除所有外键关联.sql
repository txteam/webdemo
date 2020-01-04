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
	) AS dropsql
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
SET FOREIGN_KEY_CHECKS = 1;