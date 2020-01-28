drop table if exists la_deduct_record;
create table la_deduct_record(
	id varchar(64) not null,
	loanAccountId varchar(64) not null,
	tradingRecordId varchar(64) not null,
	bankAccountType varchar(64) not null,
	bankAccountId varchar(64) not null,
	type varchar(64) not null,
	status varchar(64) not null,
	count integer,
	completeCount integer,
	completeSum decimal(16,2),
	completeDate datetime,
	sum decimal(16,2),
	failSum decimal(16,2),
	successSum decimal(16,2),
	lastUpdateDate datetime,
	createDate datetime not null default now(),
	primary key(id)
);
create index idx_la_deduct_record_00 on la_deduct_record(loanAccountId);
create index idx_la_deduct_record_01 on la_deduct_record(tradingRecordId);