drop table if exists la_deduct_fail_record;
create table la_deduct_fail_record(
	id varchar(64) not null,
	duductRecordId varchar(64) not null,
	tradingRecordId varchar(64) not null,
	loanAccountId varchar(64) not null,
	bankAccountType varchar(64) not null,
	loanAmount decimal(16,2),
	deductFailAmount decimal(16,2),
	period varchar(64),
	feeItem varchar(64),
	amount decimal(20,6),
	recordDate datetime not null,
	remark varchar(2000),
	lastUpdateDate datetime not null default now(),
	createDate datetime not null default now(),
	revoked bit not null default 0,
	revokeTradingRecordId varchar(64),
	revokeDate datetime,
	primary key(id)
);
create index idx_deduct_fail_record_00 on la_deduct_fail_record(loanAccountId);
create index idx_deduct_fail_record_01 on la_deduct_fail_record(tradingRecordId);
create index idx_deduct_fail_record_02 on la_deduct_fail_record(duductRecordId);
create index idx_deduct_fail_record_03 on la_deduct_fail_record(revokeTradingRecordId);

drop table if exists la_deduct_fail_record_his;
create table la_deduct_fail_record_his(
	id varchar(64) not null,
	duductRecordId varchar(64) not null,
	tradingRecordId varchar(64) not null,
	loanAccountId varchar(64) not null,
	bankAccountType varchar(64) not null,
	loanAmount decimal(16,2),
	deductFailAmount decimal(16,2),
	period varchar(64),
	feeItem varchar(64),
	amount decimal(20,6),
	recordDate datetime not null,
	remark varchar(2000),
	lastUpdateDate datetime not null default now(),
	createDate datetime not null default now(),
	revoked bit not null default 0,
	revokeTradingRecordId varchar(64),
	revokeDate datetime,
	primary key(id)
);