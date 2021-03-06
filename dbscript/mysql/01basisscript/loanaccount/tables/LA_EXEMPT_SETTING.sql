drop table if exists la_exempt_setting;
create table la_exempt_setting(
	id varchar(64) not null,
	createOperatorId varchar(64) not null,
	applyOperatorId varchar(64) not null,
	exemptType varchar(64) not null,
	status varchar(64) not null,
	loanAccountId varchar(64) not null,
	paymentScheduleEntryId varchar(64) null,
	operatorId varchar(64) not null,
	operatorName varchar(64) not null,
	lastUpdateOperatorId varchar(64) not null,
	effective bit,
	effectiveDate datetime,
	relaterefType varchar(64),
	relateRefId varchar(64),
	period varchar(64),
	feeItem varchar(64),
	vcid varchar(64),
	organizationId varchar(64),
	repayDate datetime,
	processDate datetime,
	approveDate datetime,
	createDate datetime not null,
	sourceAmount decimal(16,2) not null,
	applyAmount decimal(16,2) not null,
	approveAgreeAmount decimal(16,2),
	effictiveAmount decimal(16,2),
	targetamount decimal(16,2) not null,
	primary key(ID)
);
create index IDX_EXEMPT_SETTING_00 on LA_EXEMPT_SETTING(LOANACCOUNTID);
create index IDX_EXEMPT_SETTING_01 on LA_EXEMPT_SETTING(RELATEREFID);
