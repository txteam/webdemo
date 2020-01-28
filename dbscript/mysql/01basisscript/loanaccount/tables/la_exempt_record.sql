drop table if exists la_exempt_record;
create table la_exempt_record(
	id varchar(64) not null,
	loanAccountId varchar(64) not null,
	tradingRecordId varchar(64) not null,
	paymentScheduleId varchar(64) not null,
	scheduleType varchar(64) not null,
	period varchar(64) not null,
	revokeDate datetime,
	revoked bit not null default 0,
	sourceSum decimal(20,6) not null,
	sum decimal(20,6) not null,
	targetSum decimal(20,6) not null,
	createDate datetime not null default now(),
	lastUpdateDate datetime not null default now(),
	primary key(id)
);
create index idx_exempt_record_00 on la_exempt_record(tradingRecordId);
create index idx_exempt_record_01 on la_exempt_record(loanAccountId);
create index idx_exempt_record_02 on la_exempt_record(paymentScheduleId);
