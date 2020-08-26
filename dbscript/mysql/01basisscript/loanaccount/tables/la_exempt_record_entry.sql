drop table if exists la_exempt_record_entry;
create table la_exempt_record_entry(
	id varchar(64) not null,
	loanAccountId varchar(64) not null,
	tradingRecordId varchar(64) not null,
	paymentScheduleEntryId varchar(64) not null,
	exemptRecordId varchar(64) not null,
	scheduleType varchar(64) not null,
	period varchar(64) not null,
	feeItem varchar(64) not null,
	sourceAmount decimal(20,6) not null,
	amount decimal(20,6) not null,
	targetAmount decimal(20,6) not null,
	revokeDate datetime,
	revoked bit,
	lastUpdateDate datetime,
	createDate datetime,
	primary key(id)
);
create index idx_la_exempt_entry_00 on la_exempt_record_entry(tradingRecordId);
create index idx_la_exempt_entry_01 on la_exempt_record_entry(loanAccountId);
create index idx_la_exempt_entry_02 on la_exempt_record_entry(paymentScheduleEntryId);
create index idx_la_exempt_entry_03 on la_exempt_record_entry(exemptRecordId);
