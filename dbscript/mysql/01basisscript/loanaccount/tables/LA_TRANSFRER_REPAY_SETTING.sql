drop table if exists LA_TRANSFRER_REPAY_SETTING;
create table LA_TRANSFRER_REPAY_SETTING(
    id varchar(64),
	autoTransferAccountDate datetime not null,
	operatorId varchar(64),
	period varchar(255) not null,
	earlyRepayAmount decimal(16,2),
	lastUpdateDate datetime not null,
	operatorName varchar(64),
	autoTransferAmount decimal(16,2) not null,
	monthlyRepayAmount decimal(16,2),
	repaymentDate datetime,
	tradingType varchar(255),
	loanAccountId varchar(64) not null,
	receivableAmount decimal(16,2),
	createDate datetime not null,
	feeItemType varchar(255) not null,
	success bit,
	urgent bit default 0,
	effective bit default 0,
	primary key(ID)
);
create index idx_TRANSFRER_REPAY_SETTING_00 on LA_TRANSFRER_REPAY_SETTING(LOANACCOUNTID);
