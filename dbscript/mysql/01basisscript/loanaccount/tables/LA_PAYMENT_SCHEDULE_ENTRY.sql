drop table if exists LA_PAYMENT_SCHEDULE_ENTRY;
create table LA_PAYMENT_SCHEDULE_ENTRY(
	id varchar(64) not null,
	loanAccountId varchar(64) not null,
	paymentScheduleId varchar(64) not null,
	scheduleType varchar(64) not null,
	prePeriod varchar(64),
	period varchar(64) not null,
	nextPeriod varchar(64),
	feeItem varchar(64) not null,
	receivableAmount decimal(20,6),
	actualReceivedAmount decimal(20,6),
	exemptAmount decimal(20,6),
	primary key(id)
);
create unique index IDX_PAY_SCHEDULE_ENTRY_00 on LA_PAYMENT_SCHEDULE_ENTRY(loanAccountId,scheduleType,period,feeItem);
create unique index IDX_PAY_SCHEDULE_ENTRY_01 on LA_PAYMENT_SCHEDULE_ENTRY(paymentScheduleId,feeItem);
