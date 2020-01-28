drop table if exists LA_PAYMENT_SCHEDULE;
create table LA_PAYMENT_SCHEDULE(
	id varchar(64) not null,
	loanAccountId varchar(64) not null,
	scheduleType varchar(64) not null,
	prePeriod varchar(64),
	period varchar(64) not null,
	nextPeriod varchar(64),
	receivableSum decimal(20,6) not null,
	exemptSum decimal(20,6) not null,
	actualReceivedSum decimal(20,6) not null,
	principalBalance decimal(16,2),
	overdue bit not null default 0,
	overdueAmount decimal(16,2),
	overdueSum decimal(16,2),
	overduePrincipal decimal(16,2),
	lastRepayDate datetime,
	repaymentDate datetime,
	settle bit,
	settleProcessDate datetime,
	settleRepayDate datetime,
	primary key(id)
);
create unique index IDX_PAYMENT_SCHEDULE_00 on LA_PAYMENT_SCHEDULE(loanAccountId,scheduleType,period);
