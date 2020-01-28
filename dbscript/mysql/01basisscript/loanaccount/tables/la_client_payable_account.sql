drop table if exists la_client_payable_account;
create table la_client_payable_account(
	id varchar(64) not null,
	creditInfoId varchar(64) not null,
	clientId varchar(64),
	clientAccountId varchar(64),
	idCardType varchar(64),
	idCardNumber varchar(64),
	completedAmount decimal(16,2),
	completedCount integer,
	creditEffictiveDate datetime,
	creditExpiredDate datetime,
	creditBanlanceSum decimal(16,2),
	creditLineSum decimal(16,2),
	creditUsedSum decimal(16,2),
	loaningAmount decimal(16,2),
	loaningCount integer,
	totalAmount decimal(16,2),
	totalCount integer,
	totalOverdueAmount decimal(16,2),
	totalOverdueCount integer,
	totalPrincipalBalance decimal(16,2),
	lastUpdateDate datetime not null default now(),
	createDate datetime not null default now(),
	primary key(id)
);
create unique index idx_la_cl_payable_account_00 on la_client_payable_account(clientId);
create index idx_la_cl_payable_account_01 on la_client_payable_account(creditInfoId);
create index idx_la_cl_payable_account_02 on la_client_payable_account(clientAccountId);
create index idx_la_cl_payable_account_03 on la_client_payable_account(idCardNumber,idCardType);
