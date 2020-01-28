drop table if exists la_overdue_interest_record;
create table la_overdue_interest_record(
	id varchar(64) not null,
	loanAccountId varchar(64) not null,
	tradingRecordId varchar(64) not null,
	period varchar(64),
	feeItem varchar(64) not null,
	principalBalance decimal(16,2),
	overdueSum decimal(16,2),
	overdueAmount decimal(16,2),
	overdueInterestRate decimal(16,8),
	dayCount integer,
	amount decimal(20,6),
	overdueDate datetime not null,
	startDate datetime not null,
	recordDate datetime not null,
	currentPeriod varchar(64) not null,
	recordDatePeriod varchar(64) not null,
	remark varchar(500),
	lastUpdateDate datetime not null default now(),
	createDate datetime not null default now(),
	revokeTradingRecordId varchar(64),
	revokeDate datetime,
	revoked bit not null default 0,
	primary key(id)
);
create unique index idx_la_overdule_interest_00 on la_overdue_interest_record(loanAccountId,recordDate);
create index idx_la_overdule_interest_01 on la_overdue_interest_record(tradingRecordId);
create index idx_la_overdule_interest_02 on la_overdue_interest_record(recordDate);
create index idx_la_overdule_interest_03 on la_overdue_interest_record(overdueDate);

drop table if exists la_overdue_interest_record_his;
create table la_overdue_interest_record_his(
	id varchar(64) not null,
	loanAccountId varchar(64) not null,
	tradingRecordId varchar(64) not null,
	period varchar(64),
	feeItem varchar(64) not null,
	principalBalance decimal(16,2),
	overdueSum decimal(16,2),
	overdueAmount decimal(16,2),
	overdueInterestRate decimal(16,8),
	dayCount integer,
	amount decimal(20,6),
	overdueDate datetime not null,
	startDate datetime not null,
	recordDate datetime not null,
	currentPeriod varchar(64) not null,
	recordDatePeriod varchar(64) not null,
	remark varchar(500),
	lastUpdateDate datetime not null default now(),
	createDate datetime not null default now(),
	revokeTradingRecordId varchar(64),
	revokeDate datetime,
	revoked bit not null default 0,
	primary key(id)
);
