drop table if exists la_account_2_bill;
create table la_account_2_bill(
	id varchar(64) not null,
	loanAccountId varchar(64) not null,
	loanProductId varchar(64),
	loanProductName varchar(64),
	loanBillId varchar(64),
	loanBillNumber varchar(128),
	loanType varchar(64),
	primary key(id)
);
create index idx_la_account_2_bill_0 on la_account_2_bill(loanAccountId);
