/*****************************************************************************
-- CLI_PROMOTION_CHANNEL : 
*****************************************************************************/
drop table if exists CLI_PROMOTION_CHANNEL;
create table CLI_PROMOTION_CHANNEL(
	id varchar(64) not null,
	code varchar(64) not null,
	lastUpdateDate datetime(6) not null,
	name varchar(64) not null,
	valid bit not null,
	createDate datetime(6) not null,
	modifyAble bit not null,
	remark varchar(512) ,
	primary key(id)
);
