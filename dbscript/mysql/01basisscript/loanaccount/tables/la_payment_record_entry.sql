drop table if exists la_payment_record_entry;
create table la_payment_record_entry(
	id varchar(64) not null,
	loanAccountId varchar(64) not null,
	buildTradingRecordId varchar(64) not null,
	tradingRecordId varchar(64) not null,
	paymentScheduleEntryId varchar(64) not null,
	paymentRecordId varchar(64) not null,
	scheduleType varchar(64) not null,
	period varchar(64) not null,
	feeItem varchar(64) not null,
	sourceAmount decimal(16,2) not null,
	amount decimal(16,2) not null,
	targetAmount decimal(16,2) not null,
	receiveDate datetime,
	received bit not null default 0,
	revokeDate datetime,
	revoked bit not null default 0,
	lastUpdateDate datetime not null default now(),
	createDate datetime not null default now(),
	primary key(id)
);
create index idx_pay_record_entry_00 on la_payment_record_entry(tradingrecordId,paymentRecordId,paymentScheduleEntryId);
create index idx_pay_record_entry_01 on la_payment_record_entry(loanAccountId);
create index idx_pay_record_entry_02 on la_payment_record_entry(paymentRecordId);
create index idx_pay_record_entry_03 on la_payment_record_entry(paymentScheduleEntryId);
