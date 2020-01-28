drop table if exists LA_PAYMENT_RECORD;
create table LA_PAYMENT_RECORD(
	id varchar(64) not null,
	loanAccountId varchar(64) not null,
	tradingRecordId varchar(64) not null,
	buildTradingRecordId varchar(64) not null,
	paymentScheduleId varchar(64) not null,
	scheduleType varchar(64),
	period varchar(64),
	expireDate datetime,
	repayDate datetime,
	revokeDate datetime,
	revoked bit not null default 0,
	receiveDate datetime,
	received bit not null default 0,
	sourceSum decimal(16,2) not null,
	sum decimal(16,2) not null,
	targetSum decimal(16,2) not null,
	principalBalance decimal(16,2),
	createDate datetime not null default now(),
	lastUpdateDate datetime not null default now(),
	primary key(id)
);
create index IDX_PAYMENT_RECORD_00 on LA_PAYMENT_RECORD(tradingRecordId);
create index IDX_PAYMENT_RECORD_01 on LA_PAYMENT_RECORD(createDate);
create index IDX_PAYMENT_RECORD_02 on LA_PAYMENT_RECORD(loanAccountId);
create index IDX_PAYMENT_RECORD_03 on LA_PAYMENT_RECORD(buildTradingRecordId);
