drop table if exists LA_OVER_REPAY_RECORD;
create table LA_OVER_REPAY_RECORD(
	id varchar(64) not null,
	loanAccountId varchar(64) not null,
	tradingRecordId varchar(64) not null,
	period varchar(64),
	feeItem varchar(64) not null,
	amount decimal(16,2),
	createDate datetime not null default now(),
	received bit not null default 1,
	receiveDate datetime,
	revokeTradingRecordId varchar(64),
	revoked bit not null default 0,
	revokeDate datetime,
	primary key(id)
);
create index IDX_OVER_REPAY_RECORD_01 on LA_OVER_REPAY_RECORD(loanAccountId);
create index IDX_OVER_REPAY_RECORD_02 on LA_OVER_REPAY_RECORD(tradingRecordId);
create index IDX_OVER_REPAY_RECORD_03 on LA_OVER_REPAY_RECORD(createDate);

drop table if exists LA_OVER_REPAY_RECORD_HIS;
create table LA_OVER_REPAY_RECORD_HIS(
	id varchar(64) not null,
	loanAccountId varchar(64) not null,
	tradingRecordId varchar(64) not null,
	period varchar(64),
	feeItem varchar(64) not null,
	amount decimal(16,2),
	createDate datetime not null default now(),
	received bit not null default 1,
	receiveDate datetime,
	revokeTradingRecordId varchar(64),
	revoked bit not null default 0,
	revokeDate datetime,
	primary key(id)
);