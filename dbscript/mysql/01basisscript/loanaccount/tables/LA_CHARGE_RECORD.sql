drop table if exists LA_CHARGE_RECORD;
create table LA_CHARGE_RECORD(
	id varchar(64) not null,
	loanAccountId varchar(64) not null,
	tradingRecordId varchar(64) not null,
	paymentScheduleId varchar(64) not null,
	scheduleType varchar(64) not null,
	period varchar(64) not null,
	revoked bit not null default 0,
	revokeDate datetime,
	sourceSum decimal(20,6),
	sum decimal(20,6),
	targetSum decimal(20,6),
	lastUpdateDate datetime not null default now(),
	createDate datetime not null default now(),
	primary key(id)
);
create index IDX_LA_CHARGE_RECORD_00 on LA_CHARGE_RECORD(tradingRecordId);
create index IDX_LA_CHARGE_RECORD_01 on LA_CHARGE_RECORD(loanAccountId);
create index IDX_LA_CHARGE_RECORD_02 on LA_CHARGE_RECORD(createDate);