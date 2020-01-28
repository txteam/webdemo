drop table if exists LA_CHARGE_RECORD_ENTRY;
create table LA_CHARGE_RECORD_ENTRY(
	id varchar(64) not null,
	loanAccountId varchar(64) not null,
	tradingRecordId varchar(64) not null,
	chargeRecordId varchar(64) not null,
	paymentScheduleEntryId varchar(64) not null,
	scheduleType varchar(64) not null,
	period varchar(64) not null,
	feeItem varchar(64) not null,
	revokeDate datetime,
	revoked bit,
	sourceAmount decimal(20,6) not null,
	amount decimal(20,6) not null,
	targetAmount decimal(20,6) not null,
	createDate datetime,
	lastUpdateDate datetime,
	primary key(id)
);
create index IDX_CHARGE_RECORD_ENTRY_00 on LA_CHARGE_RECORD_ENTRY(tradingRecordId);
create index IDX_CHARGE_RECORD_ENTRY_01 on LA_CHARGE_RECORD_ENTRY(loanAccountId);
create index IDX_CHARGE_RECORD_ENTRY_02 on LA_CHARGE_RECORD_ENTRY(createDate);
