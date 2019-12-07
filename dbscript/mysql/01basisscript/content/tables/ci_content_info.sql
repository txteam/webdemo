/*****************************************************************************
-- CI_CONTENT_INFO : 
*****************************************************************************/
drop table if exists CI_CONTENT_INFO;
create table CI_CONTENT_INFO(
	id varchar(64) not null,
	vcid varchar(64) not null,
	type varchar(64) not null,
	categoryId varchar(64) not null,
	levelId varchar(64) not null,
	keywords varchar(256),
	name varchar(128),
	title varchar(128),
	content varchar(1000),
	attachment varchar(1000),
	attributes varchar(1000),
	author varchar(64),
	publish bit not null default 1,
	publishDate datetime not null default now(),
	top bit not null default 0,
	topDate datetime not null default now(),
	remark varchar(256),
	effectiveDate datetime not null default now(),
	expiryDate datetime,
	valid bit not null default 1,
	orders integer not null default 0,
	lastUpdateDate datetime not null default now(),
	createDate datetime not null default now(),
	lastUpdateOperatorId varchar(64),
	createOperatorId varchar(64),
	primary key(id)
);
create index idx_content_info_00 on CI_CONTENT_INFO(categoryId);
create index idx_content_info_01 on CI_CONTENT_INFO(levelId);
create index idx_content_info_02 on CI_CONTENT_INFO(type);
create index idx_content_info_03 on CI_CONTENT_INFO(title);
create index idx_content_info_04 on CI_CONTENT_INFO(name);
create index idx_content_info_05 on CI_CONTENT_INFO(createDate);
