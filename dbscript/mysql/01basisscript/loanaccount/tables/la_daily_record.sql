drop table if exists la_daily_record;
create table la_daily_record(
	id varchar(64) not null,
	loanAccountId varchar(64) not null,
	creditInfoId varchar(64) not null,
	clientId varchar(64),
	clientAccountId varchar(64),
	clientIdCardType varchar(64) not null,
	clientIdCardNumber varchar(64) not null,
	clientName varchar(64) not null,
	accountStatus varchar(64) not null,
	collectionStatus varchar(64) not null,
	settleInterestStatus varchar(64) not null,
	died bit not null,
	legalProcedure bit not null,
	overdue bit not null,
	closed bit not null,
	overdueAmount decimal(16,2),
	overdueSum decimal(16,2),
	overdueDate datetime,
	principalBalance decimal(16,2),
	principalBalanceIrr decimal(16,2),
	recordDate datetime,
	createDate datetime,
	type varchar(255),
	primary key(id)
);
create unique index idx_la_daily_record_00 on la_daily_record(recordDate,type,loanAccountId);
create index idx_la_daily_record_01 on la_daily_record(loanAccountId);
create index idx_la_daily_record_02 on la_daily_record(createDate);
create index idx_la_daily_record_03 on la_daily_record(clientId);
create index idx_la_daily_record_04 on la_daily_record(creditInfoId);