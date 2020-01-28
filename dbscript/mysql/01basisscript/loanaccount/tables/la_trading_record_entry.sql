drop table if exists la_trading_record_entry;
create table la_trading_record_entry(
	id varchar(64) not null,
	loanAccountId varchar(64) not null,
	tradingRecordId varchar(64) not null,
	debitCreditType varchar(64) not null,
	accountTitleCompanyCode varchar(64) not null,
	accountTitleCode varchar(64) not null,
	amount decimal(16,2) not null,
	primary key(id)
);
create index idx_la_trading_entry_01 on la_trading_record_entry(tradingRecordId);
create index idx_la_trading_entry_02 on la_trading_record_entry(loanAccountId);
