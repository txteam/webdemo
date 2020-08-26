-- tableInitializers: 
-- tables
-- ----------bd_config_context---------- 

-- ----------bd_config_context_his---------- 


-- initdata

-- tables
-- ----------table:bd_plugin_instance---------- 


-- initdata

-- tables
-- ----------table:QXB_ENT_BRANCHE---------- 

-- ----------table:QXB_ENT_CHANGE_RECORD---------- 

-- ----------table:QXB_ENT_CONTACT---------- 

-- ----------table:QXB_ENT_WEBSITE---------- 

-- ----------table:INS_SUMMARY_INFO---------- 

-- ----------table:QXB_ENT_PAR_REAL_CAPI_ITEM---------- 

-- ----------table:QXB_ENT_ABNORMAL_ITEM---------- 

-- ----------table:QXB_ENT_EMPLOYEE---------- 

-- ----------table:QXB_ENT_PARTNER---------- 

-- ----------table:QXB_ENTERPRISE---------- 

-- ----------table:QXB_ENT_PAR_SHOULD_CAPI_ITEM---------- 


-- initdata

-- tables

-- initdata

-- tables
-- ----------table:bd_data_dict---------- 


-- initdata

-- tables
-- ----------table:table_sec_auth_type---------- 

-- ----------table:sec_auth---------- 

-- ----------table:sec_authref---------- 

-- ----------table:table_sec_authref_his---------- 


-- initdata

-- tables
-- ----------table:LA_REQUEST_LOG---------- 

-- ----------table:CL_LOGIN_LOG---------- 

-- ----------table:oper_sec_operate_log---------- 

-- ----------table:CL_SEC_OPERATE_LOG---------- 

-- ----------table:OPER_LOGIN_LOG---------- 


-- initdata

-- tables
-- ----------table:table_sec_role_type---------- 

-- ----------table:table_sec_role---------- 

-- ----------table:table_sec_roleref---------- 

-- ----------table:table_sec_roleref_his---------- 


-- initdata

-- tables
-- ----------table:sec_jwt_signing_key---------- 


-- initdata

-- tables
drop table if exists bd_bankinfo;
create table bd_bankinfo(
	id varchar(64) not null,
	code varchar(64) not null,
	valid bit not null default 1,
	modifyAble bit not null default 1,
	name varchar(64) not null,
	aliases varchar(256) not null,
	logoFileId varchar(64),
	logoUrl varchar(256),
	personalLoginUrl varchar(256),
	institutionLoginUrl varchar(256),
	createDate datetime not null default now(),
	lastUpdateDate datetime not null default now(),
	remark varchar(2000),
	primary key(id)
);
create index idx_bd_bankinfo_00 on bd_bankinfo(code);

drop table if exists bd_district;
create table bd_district(
	id varchar(64) not null,
	parentId varchar(64),
	level integer not null default 0,
	code varchar(64) not null,
	type varchar(64) not null,
	name varchar(64) not null,
	pinyin varchar(64),
	py varchar(64),
	zipCode varchar(64) not null,
	provinceId varchar(64),
	cityId varchar(64),
	countyId varchar(64),
	valid bit not null default 1, 
	modifyAble bit not null default 1,
	fullName varchar(64),
	remark varchar(512),
	lastUpdateDate datetime not null default now(),
	createDate datetime not null default now(),
	attributes varchar(512),
	primary key(id)
);
create unique index idx_bd_district_00 on bd_district(code);
create index idx_bd_district_01 on bd_district(zipCode);
create index idx_bd_district_02 on bd_district(provinceId);
create index idx_bd_district_03 on bd_district(cityId);
create index idx_bd_district_04 on bd_district(countyId);
create index idx_bd_district_05 on bd_district(pinyin);
create index idx_bd_district_06 on bd_district(py);

/*****************************************************************************
			表：bd_industry
*****************************************************************************/
drop table if exists bd_industry;
create table bd_industry(
	id varchar(64) not null,
	parentId varchar(64),
	level integer not null default 0,
	code varchar(64) not null,
	name varchar(64) not null,
	valid bit not null default 1, 
	modifyAble bit not null default 1,
	fullName varchar(64),
	remark varchar(512),
	lastUpdateDate datetime not null default now(),
	createDate datetime not null default now(),
	primary key(id)
);
create unique index idx_bd_industry_00 on bd_industry(code);

/*****************************************************************************
			表：bd_ipaddress_reposity
*****************************************************************************/
drop table if exists bd_ipaddress_reposity;
create table bd_ipaddress_reposity(
	id varchar(64) not null,
	code varchar(64) not null,
	name varchar(64),
	valid bit not null default 1,
	modifyAble bit not null default 1,
	districtId varchar(64) not null,
	continentName varchar(64),
	nationName varchar(64),
	provinceName varchar(64),
	cityName varchar(64),
	countyName varchar(64),
	en varchar(255),
	enName varchar(64),
	telecomOperatorId varchar(64),
	remark varchar(500),
	latitude decimal(32,16),
	longitude decimal(32,16),
	lastUpdateDate datetime not null default now(),
	createDate datetime not null default now(),
	primary key(id)
);
create index idx_ipaddress_reposity_00 on bd_ipaddress_reposity(code);
/*****************************************************************************
-- CAL_CALENDAR_EVENT : 
*****************************************************************************/
drop table if exists CAL_CALENDAR_EVENT;
create table CAL_CALENDAR_EVENT(
	id varchar(64) not null,
	vcid varchar(64) not null,
	catalogId varchar(64),
	type varchar(64) not null,
	topicType varchar(64) not null,
	topicId varchar(64),
	title varchar(100) not null,
	daysOfWeek varchar(64),
	start datetime(6),
	end datetime(6),
	url varchar(128),
	editable bit not null default 0,
	allDay bit not null default 1,
	repeated bit not null default 0,
	overlap bit not null default 1,
	rrule varchar(1024),	
	duration varchar(32),
	className varchar(64),
	attributes varchar(2000),
	remark varchar(100),
	creator varchar(64),
	createDate datetime(6) not null,
	createUserId varchar(64),
	lastUpdateDate datetime(6) not null,
	lastUpdateUserId varchar(64),
	primary key(id)
);
create index IDX_CALENDAR_EVENT_00 on CAL_CALENDAR_EVENT(vcid);
create index IDX_CALENDAR_EVENT_01 on CAL_CALENDAR_EVENT(topicId,topicType);

/*****************************************************************************
-- CAL_EVENT_CATALOG : 
*****************************************************************************/
drop table if exists CAL_EVENT_CATALOG;
create table CAL_EVENT_CATALOG(
	id varchar(64) not null,
	type varchar(64) not null,
	vcid varchar(64) not null,
	code varchar(64) not null,
	name varchar(100) not null,
	remark varchar(500),
	topicType varchar(64) not null,
	valid bit not null default 1,
	modifyAble bit not null default 1,
	lastUpdateDate datetime(6) not null,
	lastUpdateUserId varchar(64) ,
	createDate datetime(6) not null,
	createUserId varchar(64),
	primary key(id)
);
create unique index idx_event_catalog_01 on CAL_EVENT_CATALOG(code,vcid);

/*****************************************************************************
-- CLI_CLIENT_ROLE : 
*****************************************************************************/
drop table if exists CLI_CLIENT_ROLE;
create table CLI_CLIENT_ROLE(
	id varchar(64) not null,
	code varchar(64) not null,
	lastUpdateDate datetime(6) not null,
	name varchar(64) not null,
	valid bit not null,
	createDate datetime(6) not null,
	modifyAble bit not null,
	remark varchar(512) ,
	primary key(id)
);

/*****************************************************************************
-- CLI_CLIENT_SOURCE : 
*****************************************************************************/
drop table if exists CLI_CLIENT_SOURCE;
create table CLI_CLIENT_SOURCE(
	id varchar(64) not null,
	code varchar(64) not null,
	name varchar(64) not null,
	remark varchar(512),
	valid bit not null default 1,
	modifyAble bit not null default 1,
	lastUpdateDate datetime(6) not null default now(6),
	createDate datetime(6) not null default now(6),
	primary key(id)
);

/*****************************************************************************
-- CLI_PROMOTION_CHANNEL : 
*****************************************************************************/
drop table if exists CLI_PROMOTION_CHANNEL;
create table CLI_PROMOTION_CHANNEL(
	id varchar(64) not null,
	code varchar(64) not null,
	lastUpdateDate datetime(6) not null,
	name varchar(64) not null,
	valid bit not null,
	createDate datetime(6) not null,
	modifyAble bit not null,
	remark varchar(512) ,
	primary key(id)
);

/*****************************************************************************
-- CLI_CLIENT_SOURCE : 
*****************************************************************************/
drop table if exists CLI_CLIENT_SOURCE;
create table CLI_CLIENT_SOURCE(
	id varchar(64) not null,
	code varchar(64) not null,
	lastUpdateDate datetime(6) not null,
	name varchar(64) not null,
	valid bit not null,
	createDate datetime(6) not null,
	modifyAble bit not null,
	remark varchar(512) ,
	primary key(id)
);

/*****************************************************************************
-- CLI_PROMOTION_CHANNEL : 
*****************************************************************************/
drop table if exists CLI_PROMOTION_CHANNEL;
create table CLI_PROMOTION_CHANNEL(
	id varchar(64) not null,
	code varchar(64) not null,
	lastUpdateDate datetime(6) not null,
	name varchar(64) not null,
	valid bit not null,
	createDate datetime(6) not null,
	modifyAble bit not null,
	remark varchar(512) ,
	primary key(id)
);

/*****************************************************************************
-- CL_CLIENT_INFO : 
*****************************************************************************/
drop table if exists CL_CLIENT_INFO;
create table CL_CLIENT_INFO(
	id varchar(64) not null,
	vcid varchar(64) not null,
	type varchar(64) not null,
	code varchar(64) not null,
	username varchar(64) not null,
	password varchar(64) not null,
	name varchar(64),
	status varchar(64),
	valid bit not null default 1,
	invalidDate datetime(6),
	locked bit not null default 0,
	usernameChangeAble bit not null default 1,
	usernameChangeCount integer not null default 0,
	hisPwd varchar(64),
	pwdErrCount integer not null default 0,
	pwdUpdateDate datetime(6),
	pwdLastErrDate datetime(6),
	mobileNumber varchar(64),
	mobileBinding bit not null default 0,
	mobileLoginEnable bit not null default 0,
	email varchar(64),
	emailBinding bit not null default 0,
	idCardNumber varchar(64),
	idCardType varchar(64),
	idCardExpiryDate datetime(6) ,
	realNameAuthenticated bit not null default 0,
	realNameErrCount integer not null default 0,
	realNameLastErrDate datetime(6),
	creditInfoId varchar(64),
	creditInfoBinding bit not null default 0,
	payPassword varchar(64) ,
	hisPayPwd varchar(64) ,
	payPwdErrCount integer ,
	payPwdUpdateDate datetime(6),
	payPwdLastErrDate datetime(6),
	promotionChannelId varchar(64) ,
	clientSourceId varchar(64) ,
	referralCode varchar(64) ,
	lastUpdateDate datetime(6) not null default now(6),
	createDate datetime(6) not null default now(6),
	primary key(id)
);
create unique index IDX_CLIENT_INFO_00 on CL_CLIENT_INFO(username);
create unique index IDX_CLIENT_INFO_01 on CL_CLIENT_INFO(code);
create index IDX_CLIENT_INFO_02 on CL_CLIENT_INFO(createDate);


/*****************************************************************************
-- CL_SOCIAL_ACCOUNT : 
*****************************************************************************/
drop table if exists CL_SOCIAL_ACCOUNT;
create table CL_SOCIAL_ACCOUNT(
	id varchar(64) not null,
	type varchar(64) not null,
	clientId varchar(64) not null,
	uniqueId varchar(64) not null,
	username varchar(64),
	headImgUrl varchar(255) ,
	attributes varchar(500) ,
	lastUpdateDate datetime(6) ,
	createDate datetime(6) ,
	primary key(id)
);
create unique index IDX_CL_SOCIAL_ACCOUNT_00 on CL_SOCIAL_ACCOUNT(uniqueId,type);
create unique index IDX_CL_SOCIAL_ACCOUNT_01 on CL_SOCIAL_ACCOUNT(clientId,type);

/*****************************************************************************
-- CI_CONTENT_CATEGORY : 
*****************************************************************************/
drop table if exists CI_CONTENT_CATEGORY;
create table CI_CONTENT_CATEGORY(
	id varchar(64) not null,
	code varchar(64) ,
	lastUpdateDate datetime(6) ,
	lastUpdateOperatorId varchar(64) ,
	name varchar(64) ,
	typeCode varchar(64) ,
	level integer ,
	orderIndex integer ,
	valid bit ,
	createDate datetime(6) ,
	createOperatorId varchar(64) ,
	modifyAble bit ,
	parentId varchar(64) ,
	remark varchar(512) ,
	primary key(id)
);

/*****************************************************************************
-- CI_CONTENT_INFO : 
*****************************************************************************/
drop table if exists CI_CONTENT_INFO;
create table CI_CONTENT_INFO(
	id varchar(64) not null,
	vcid varchar(64) not null,
	type varchar(64) not null,
	categoryId varchar(64) not null,
	levelId varchar(64) not null,
	keywords varchar(256),
	name varchar(128),
	title varchar(128),
	content varchar(1000),
	attachment varchar(1000),
	attributes varchar(1000),
	author varchar(64),
	publish bit not null default 1,
	publishDate datetime not null default now(),
	top bit not null default 0,
	topDate datetime not null default now(),
	remark varchar(256),
	effectiveDate datetime not null default now(),
	expiryDate datetime,
	valid bit not null default 1,
	orders integer not null default 0,
	lastUpdateDate datetime not null default now(),
	createDate datetime not null default now(),
	lastUpdateOperatorId varchar(64),
	createOperatorId varchar(64),
	primary key(id)
);
create index idx_content_info_00 on CI_CONTENT_INFO(categoryId);
create index idx_content_info_01 on CI_CONTENT_INFO(levelId);
create index idx_content_info_02 on CI_CONTENT_INFO(type);
create index idx_content_info_03 on CI_CONTENT_INFO(title);
create index idx_content_info_04 on CI_CONTENT_INFO(name);
create index idx_content_info_05 on CI_CONTENT_INFO(createDate);

/*****************************************************************************
-- CI_CONTENT_LEVEL : 
*****************************************************************************/
drop table if exists CI_CONTENT_LEVEL;
create table CI_CONTENT_LEVEL(
	id varchar(64) not null,
	code varchar(64) ,
	lastUpdateDate datetime(6) ,
	lastUpdateOperatorId varchar(64) ,
	name varchar(64) ,
	valid bit ,
	createDate datetime(6) ,
	createOperatorId varchar(64) ,
	modifyAble bit ,
	remark varchar(512) ,
	categoryCode varchar(64) ,
	primary key(id)
);

/*****************************************************************************
-- CI_CONTENT_TYPE : 
*****************************************************************************/
drop table if exists CI_CONTENT_TYPE;
create table CI_CONTENT_TYPE(
	id varchar(64) not null,
	code varchar(64) ,
	lastUpdateDate datetime(6) ,
	lastUpdateOperatorId varchar(64) ,
	name varchar(64) ,
	valid bit ,
	createDate datetime(6) ,
	createOperatorId varchar(64) ,
	modifyAble bit ,
	remark varchar(512) ,
	primary key(id)
);

/*****************************************************************************
-- CLI_CREDIT_INFO : 
*****************************************************************************/
drop table if exists CLI_CREDIT_INFO;
create table CLI_CREDIT_INFO(
	id varchar(64) not null,
	clientId varchar(64) not null,
	version integer not null,
	type varchar(64) not null,
	idCardNumber varchar(64),
	idCardType varchar(64),
	baseVersion integer not null,
	lastUpdateDate datetime(6) not null,
	lastUpdateUserId varchar(64),
	createDate datetime(6) not null,
	createUserId varchar(64) ,
	locked bit not null,
	primary key(id)
);
create unique index idx_CLI_CREDIT_INFO_00 on CLI_CREDIT_INFO(clientId,version,type);
create index idx_CLI_CREDIT_INFO_01 on CLI_CREDIT_INFO(idCardNumber,idCardType);
create index idx_CLI_CREDIT_INFO_02 on CLI_CREDIT_INFO(idCardNumber,idCardType);
/*****************************************************************************
-- TEST_DEMO : 
*****************************************************************************/
drop table if exists TEST_DEMO;
create table TEST_DEMO(
	id varchar(64) not null,
	code varchar(32) not null,
	lastUpdateDate datetime(6) not null,
	lastUpdateOperatorId varchar(64) ,
	name varchar(32) not null,
	testInt integer ,
	testLong bigint ,
	testInteger integer ,
	testBigDecimal decimal(32,8) ,
	type varchar(64) ,
	superInt integer ,
	superBoolean bit ,
	superDemoCode varchar(64) ,
	superBigDeceimal decimal(32,8) ,
	superIntegerObject integer ,
	superIsBooleanObject bit ,
	valid bit not null,
	createDate datetime(6) not null,
	createOperatorId varchar(64) ,
	expiryDate datetime(6) ,
	modifyAble bit ,
	parentId varchar(64) ,
	remark varchar(512) ,
	nested1Id varchar(64) ,
	nested2Code varchar(64) ,
	success bit ,
	effictiveDate datetime(6) ,
	attributes varchar(255) ,
	description varchar(512) ,
	primary key(id)
);

/*****************************************************************************
-- INS_INSTITUTION : 
*****************************************************************************/
drop table if exists INS_INSTITUTION;
create table INS_INSTITUTION(
	id varchar(64) not null,
	type varchar(64) not null,
	vcid varchar(64) not null,
	clientId varchar(64) not null,
	modifyAble bit not null default 1,
	name varchar(64),
	linkName varchar(64) ,
	linkMobileNumber varchar(64) ,
	provinceId varchar(64) ,
	cityId varchar(64) ,
	countyId varchar(64) ,
	districtId varchar(64) ,
	address varchar(256) ,
	fullAddress varchar(256),
	postcode varchar(64) ,
	remark varchar(500) ,
	lastUpdateDate datetime(6) not null,
	lastUpdateUserId varchar(64) ,
	createDate datetime(6) not null,
	createUserId varchar(64) ,
	primary key(id)
);
create unique index idx_ins_institution_00 on INS_INSTITUTION(clientId);

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

drop table if exists LA_CHARGE_RECORD;
create table LA_CHARGE_RECORD(
	id varchar(64) not null,
	loanAccountId varchar(64) not null,
	tradingRecordId varchar(64) not null,
	paymentScheduleId varchar(64) not null,
	scheduleType varchar(64) not null,
	period varchar(64) not null,
	revoked bit not null default 0,
	revokeDate datetime,
	sourceSum decimal(20,6),
	sum decimal(20,6),
	targetSum decimal(20,6),
	lastUpdateDate datetime not null default now(),
	createDate datetime not null default now(),
	primary key(id)
);
create index IDX_LA_CHARGE_RECORD_00 on LA_CHARGE_RECORD(tradingRecordId);
create index IDX_LA_CHARGE_RECORD_01 on LA_CHARGE_RECORD(loanAccountId);
create index IDX_LA_CHARGE_RECORD_02 on LA_CHARGE_RECORD(createDate);
drop table if exists LA_CHARGE_RECORD_ENTRY;
create table LA_CHARGE_RECORD_ENTRY(
	id varchar(64) not null,
	loanAccountId varchar(64) not null,
	tradingRecordId varchar(64) not null,
	chargeRecordId varchar(64) not null,
	paymentScheduleEntryId varchar(64) not null,
	scheduleType varchar(64) not null,
	period varchar(64) not null,
	feeItem varchar(64) not null,
	revokeDate datetime,
	revoked bit,
	sourceAmount decimal(20,6) not null,
	amount decimal(20,6) not null,
	targetAmount decimal(20,6) not null,
	createDate datetime,
	lastUpdateDate datetime,
	primary key(id)
);
create index IDX_CHARGE_RECORD_ENTRY_00 on LA_CHARGE_RECORD_ENTRY(tradingRecordId);
create index IDX_CHARGE_RECORD_ENTRY_01 on LA_CHARGE_RECORD_ENTRY(loanAccountId);
create index IDX_CHARGE_RECORD_ENTRY_02 on LA_CHARGE_RECORD_ENTRY(createDate);

drop table if exists la_client_loanbill_account;
create table la_client_loanbill_account(
	id varchar(64) not null,
	creditInfoId varchar(64) not null,
	idCardType varchar(64) not null,
	idCardNumber varchar(64) not null,
	clientId varchar(64),
	clientAccountId varchar(64),
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
create unique index idx_la_cl_loanbill_account_00 on la_client_loanbill_account(clientId);
create index idx_la_cl_loanbill_account_01 on la_client_loanbill_account(creditInfoId);
create index idx_la_cl_loanbill_account_02 on la_client_loanbill_account(clientAccountId);
create index idx_la_cl_loanbill_account_03 on la_client_loanbill_account(idCardNumber,idCardType);
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
drop table if exists la_deduct_fail_record;
create table la_deduct_fail_record(
	id varchar(64) not null,
	duductRecordId varchar(64) not null,
	tradingRecordId varchar(64) not null,
	loanAccountId varchar(64) not null,
	bankAccountType varchar(64) not null,
	loanAmount decimal(16,2),
	deductFailAmount decimal(16,2),
	period varchar(64),
	feeItem varchar(64),
	amount decimal(20,6),
	recordDate datetime not null,
	remark varchar(2000),
	lastUpdateDate datetime not null default now(),
	createDate datetime not null default now(),
	revoked bit not null default 0,
	revokeTradingRecordId varchar(64),
	revokeDate datetime,
	primary key(id)
);
create index idx_deduct_fail_record_00 on la_deduct_fail_record(loanAccountId);
create index idx_deduct_fail_record_01 on la_deduct_fail_record(tradingRecordId);
create index idx_deduct_fail_record_02 on la_deduct_fail_record(duductRecordId);
create index idx_deduct_fail_record_03 on la_deduct_fail_record(revokeTradingRecordId);

drop table if exists la_deduct_fail_record_his;
create table la_deduct_fail_record_his(
	id varchar(64) not null,
	duductRecordId varchar(64) not null,
	tradingRecordId varchar(64) not null,
	loanAccountId varchar(64) not null,
	bankAccountType varchar(64) not null,
	loanAmount decimal(16,2),
	deductFailAmount decimal(16,2),
	period varchar(64),
	feeItem varchar(64),
	amount decimal(20,6),
	recordDate datetime not null,
	remark varchar(2000),
	lastUpdateDate datetime not null default now(),
	createDate datetime not null default now(),
	revoked bit not null default 0,
	revokeTradingRecordId varchar(64),
	revokeDate datetime,
	primary key(id)
);
drop table if exists la_deduct_record;
create table la_deduct_record(
	id varchar(64) not null,
	loanAccountId varchar(64) not null,
	tradingRecordId varchar(64) not null,
	bankAccountType varchar(64) not null,
	bankAccountId varchar(64) not null,
	type varchar(64) not null,
	status varchar(64) not null,
	count integer,
	completeCount integer,
	completeSum decimal(16,2),
	completeDate datetime,
	sum decimal(16,2),
	failSum decimal(16,2),
	successSum decimal(16,2),
	lastUpdateDate datetime,
	createDate datetime not null default now(),
	primary key(id)
);
create index idx_la_deduct_record_00 on la_deduct_record(loanAccountId);
create index idx_la_deduct_record_01 on la_deduct_record(tradingRecordId);
drop table if exists la_exempt_record;
create table la_exempt_record(
	id varchar(64) not null,
	loanAccountId varchar(64) not null,
	tradingRecordId varchar(64) not null,
	paymentScheduleId varchar(64) not null,
	scheduleType varchar(64) not null,
	period varchar(64) not null,
	revokeDate datetime,
	revoked bit not null default 0,
	sourceSum decimal(20,6) not null,
	sum decimal(20,6) not null,
	targetSum decimal(20,6) not null,
	createDate datetime not null default now(),
	lastUpdateDate datetime not null default now(),
	primary key(id)
);
create index idx_exempt_record_00 on la_exempt_record(tradingRecordId);
create index idx_exempt_record_01 on la_exempt_record(loanAccountId);
create index idx_exempt_record_02 on la_exempt_record(paymentScheduleId);

drop table if exists la_exempt_record_entry;
create table la_exempt_record_entry(
	id varchar(64) not null,
	loanAccountId varchar(64) not null,
	tradingRecordId varchar(64) not null,
	paymentScheduleEntryId varchar(64) not null,
	exemptRecordId varchar(64) not null,
	scheduleType varchar(64) not null,
	period varchar(64) not null,
	feeItem varchar(64) not null,
	sourceAmount decimal(20,6) not null,
	amount decimal(20,6) not null,
	targetAmount decimal(20,6) not null,
	revokeDate datetime,
	revoked bit,
	lastUpdateDate datetime,
	createDate datetime,
	primary key(id)
);
create index idx_la_exempt_entry_00 on la_exempt_record_entry(tradingRecordId);
create index idx_la_exempt_entry_01 on la_exempt_record_entry(loanAccountId);
create index idx_la_exempt_entry_02 on la_exempt_record_entry(paymentScheduleEntryId);
create index idx_la_exempt_entry_03 on la_exempt_record_entry(exemptRecordId);

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

drop table if exists la_exempt_setting_his;
create table la_exempt_setting_his(
	id varchar(64) not null,
	createOperatorId varchar(64) not null,
	applyOperatorId varchar(64) not null,
	exemptType varchar(64) not null,
	status varchar(64) not null,
	loanAccountId varchar(64) not null,
	paymentScheduleEntryId varchar(64),
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

drop table if exists LA_FEE_ITEM;
create table LA_FEE_ITEM(
	id varchar(64) not null,
	loanAccountId varchar(64) not null,
	feeItem varchar(64) not null,
	value varchar(500) not null,
	primary key(id)
);
create unique index IDX_LA_FEE_ITEM_00 on LA_FEE_ITEM(loanAccountId,feeItem);

drop table if exists la_loanaccount2client;
CREATE TABLE la_loanaccount2client (
  id varchar(64) not null,
  loanAccountId varchar(64) not null,
  idCardType varchar(64) not null,
  idcardnumber varchar(32) not null,
  CLIENTID varchar(64) not null,
  CLIENTNAME varchar(64),
  CLIENTACCOUNTID varchar(64),
  CLIENTTYPE varchar(64),
  REFTYPE varchar(64),
  primary key(ID)
);
CREATE UNIQUE INDEX IDX_LOANACCOUNT2CLIENT_00 on LA_LOANACCOUNT2CLIENT(LOANACCOUNTID,IDCARDNUMBER);

drop table if exists LA_LOAN_ACCOUNT;
create table LA_LOAN_ACCOUNT(
	id varchar(64) not null comment '主键ID',
	category varchar(64) not null comment '分类',
	loanAccountType varchar(64) not null comment '贷款帐户类型',
	contractId varchar(64) comment '贷款合同ID',
	contractNumber varchar(64) comment '合同编号',
	effectiveDate datetime comment '生效时间/合同起始日期',
	expiryDate datetime comment '到期时间/合同结束日期',
	loanAmount decimal(16,2) not null comment '贷款金额',
	totalPeriod integer not null comment '总期数',
	paidPeriod decimal(16,8) not null comment '已付期数',
	interestDate datetime not null comment '计息日--通常==实际放款日',
	factLoanDate datetime not null comment '实际放款时间',
	factOutLoanAmount decimal(16,2) not null comment '实际放款金额',
	repayWay varchar(64) not null comment '还款方式',
	timeUnitType varchar(64) not null comment '时间单位',
	time integer not null comment '时间',
	clientId varchar(64) comment '对应客户ID',
	clientIdCardType varchar(64) not null comment '证件类型',
	clientIdCardNumber varchar(64) not null comment '证件号码',
	clientName varchar(64) not null comment '客户名称',
	clientAccountId varchar(64) comment '对应客户账户ID',
	creditInfoId varchar(64) not null comment '客户信用信息ID',
	creditInfoVersion varchar(64) comment '信用信息Tag版本',
	sourceLastTradingRecordId varchar(64) comment '原贷款账户最后一笔交易记录ID',
	sourceLoanAccountId varchar(64) comment '原贷贷款账户id',
	beforeDelayDays integer not null default 0 comment '贷前延期天数',
	afterDelayDays integer not null default 0 comment '贷后延期天数',
	accountStatus varchar(64) not null comment '帐户状态',
	collectionStatus varchar(64) not null comment '催收状态',
	closed bit not null default 0 comment '是否关闭XX',
	died bit not null default 0 comment '是否死亡DA',
	legalProcedure bit not null default 0 comment '是否法律程序LP AC/DQ/DA/LP/XX',
	settleInterestStatus varchar(64) not null comment '结息状态',
	locked bit not null default 0 comment '是否锁定',
	lastLockDate datetime comment '最后一次锁定时间',
	inHisTable bit comment '是否历史账户',
	existNotReceivedTrading bit not null default 0 comment '是否存在未到账交易',
	currentPeriod varchar(64) comment '当前期数',
	currentPeriodExpireDate datetime comment ' 当前期数到期日',
	firstRepayDate datetime comment '首期还款日期',
	monthlyRepayAmount decimal(16,2) comment '每月还款金额',
	monthlyRepayDay integer comment '每月还款日',
	graceDays integer comment '上次还款金额',
	lastRepayAmount decimal(16,2) comment '',
	lastRepayDate datetime comment '上次还款日',
	nextOverdueCheckDate datetime comment '最后逾期检测日 [保留字段] ',
	nextRepayDate datetime comment '下次还款日期',
	nextDeductDate datetime comment '下次还款日期',
	nextSettleInterestDate datetime comment '下次结息日',
	overRepayAmount decimal(16,2) comment '超额还款金额',
	outsource bit comment '委外是否发生过',
	outsourceHappend bit comment '是否正在发生委外',
	outsourceAssignRecordId varchar(64) comment '委外分配记录ID',
	outsourceAmount decimal(16,2) comment '委外金额',
	outsourcePercentage decimal(16,8) comment '委外佣金比率',
	overdue bit comment '是否逾期',
	overdueDate datetime comment '逾期日期',
	overdueAmount decimal(16,2) not null comment '逾期金额（在现有业务中实际是为：逾期的 本金+利息+管理费 之和）',
	overdueSum decimal(16,2) not null comment '逾期金额总额（除逾期金额之外的费用，包括逾期利息）',
	principalBalance decimal(16,2) not null comment '本金结余',
	principalBalanceIrr decimal(16,2) not null comment '本金结余递减',
	revoked bit not null default 0 comment '是否退回',
	revokeDate datetime comment '退回时间',
	revokeReason varchar(500) comment '退回原因',
	settle bit not null default 0 comment '是否结清',
	settleCurrentPeriod varchar(64) comment '结清时对应的当前期数',
	settleDate datetime comment '结清交易发生日期',
	settleRepayDate datetime comment ' 结清交易发生日期',
	settleRepayDatePeriod varchar(64) comment '结清还款日对应的期数',
	settleSL bit not null default 0 comment '是否SL结清',
	settleSLDate datetime comment 'SL结清交易发生日期,SL结清时',
	settleSLRepayDate datetime comment 'SL结清交易发生的还款日期',
	settleSLRepayDatePeriod varchar(64) comment 'SL结清还款日对应的期数',
	writeOff bit not null default 0 comment '是否注销',
	writeOffAmount decimal(16,2) comment '注销金额',
	writeOffDate datetime comment '注销日期',
	writeOffReason varchar(500) comment '注销原因',
	writeOffRepayAmount decimal(16,2) comment '注销后还款金额',
	lastUpdateDate datetime not null default now() comment '更新时间',
	createDate datetime not null default now() comment '创建时间',
	vcid varchar(64) comment '贷款账户所属虚中心ID',
	districtId varchar(64) comment '区ID',
	organizationId varchar(64) comment '贷款账户所属组织ID[保留字段]',
	primary key(id)
);
create unique index IDX_LOAN_ACCOUNT_00 on LA_LOAN_ACCOUNT(contractNumber);
create unique index IDX_LOAN_ACCOUNT_01 on LA_LOAN_ACCOUNT(contractId);
create index IDX_LOAN_ACCOUNT_02 on LA_LOAN_ACCOUNT(createDate);
create index IDX_LOAN_ACCOUNT_03 on LA_LOAN_ACCOUNT(clientId);
create index IDX_LOAN_ACCOUNT_04 on LA_LOAN_ACCOUNT(clientAccountId);
create index IDX_LOAN_ACCOUNT_05 on LA_LOAN_ACCOUNT(creditInfoId,creditInfoVersion);
create index IDX_LOAN_ACCOUNT_06 on LA_LOAN_ACCOUNT(clientIdCardNumber,clientIdCardType);

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
drop table if exists LA_PAYMENT_RECORD;
create table LA_PAYMENT_RECORD(
	id varchar(64) not null,
	loanAccountId varchar(64) not null,
	tradingRecordId varchar(64) not null,
	buildTradingRecordId varchar(64) not null,
	paymentScheduleId varchar(64) not null,
	scheduleType varchar(64),
	period varchar(64),
	expireDate datetime,
	repayDate datetime,
	revokeDate datetime,
	revoked bit not null default 0,
	receiveDate datetime,
	received bit not null default 0,
	sourceSum decimal(16,2) not null,
	sum decimal(16,2) not null,
	targetSum decimal(16,2) not null,
	principalBalance decimal(16,2),
	createDate datetime not null default now(),
	lastUpdateDate datetime not null default now(),
	primary key(id)
);
create index IDX_PAYMENT_RECORD_00 on LA_PAYMENT_RECORD(tradingRecordId);
create index IDX_PAYMENT_RECORD_01 on LA_PAYMENT_RECORD(createDate);
create index IDX_PAYMENT_RECORD_02 on LA_PAYMENT_RECORD(loanAccountId);
create index IDX_PAYMENT_RECORD_03 on LA_PAYMENT_RECORD(buildTradingRecordId);

drop table if exists la_payment_record_entry;
create table la_payment_record_entry(
	id varchar(64) not null,
	loanAccountId varchar(64) not null,
	buildTradingRecordId varchar(64) not null,
	tradingRecordId varchar(64) not null,
	paymentScheduleEntryId varchar(64) not null,
	paymentRecordId varchar(64) not null,
	scheduleType varchar(64) not null,
	period varchar(64) not null,
	feeItem varchar(64) not null,
	sourceAmount decimal(16,2) not null,
	amount decimal(16,2) not null,
	targetAmount decimal(16,2) not null,
	receiveDate datetime,
	received bit not null default 0,
	revokeDate datetime,
	revoked bit not null default 0,
	lastUpdateDate datetime not null default now(),
	createDate datetime not null default now(),
	primary key(id)
);
create index idx_pay_record_entry_00 on la_payment_record_entry(tradingrecordId,paymentRecordId,paymentScheduleEntryId);
create index idx_pay_record_entry_01 on la_payment_record_entry(loanAccountId);
create index idx_pay_record_entry_02 on la_payment_record_entry(paymentRecordId);
create index idx_pay_record_entry_03 on la_payment_record_entry(paymentScheduleEntryId);

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

drop table if exists la_refund_record;
create table la_refund_record(
	id varchar(64) not null,
	amount decimal(16,2) not null,
	
	loanAccountId varchar(64) not null,
	clientId varchar(64) not null,
	clientName varchar(64),
	clientIdCardType varchar(64) not null,
	clientIdCardNumber varchar(64) not null,
	
	
	status varchar(255),
	
	tradingRecordId varchar(64),
	
	bankAccountId varchar(64),
	contractNumber varchar(255),
	
	
	sourceBankAccountId varchar(255),
	sourceRequestSourceType varchar(255),
	
	createOperatorId varchar(64),
	createDate datetime,
	lastUpdateOperatorId varchar(64),
	lastUpdateDate datetime,
	primary key(ID)
);

drop table if exists la_request_log;
create table la_request_log(
	id varchar(64) not null,
	loanAccountId varchar(64) not null,
	requestId varchar(64) not null,
	loanAccountType varchar(64) not null,
    sourceType varchar(64),
    tradingCategory varchar(64),
    tradingRecordType varchar(64),
    remark varchar(500),
    message varchar(4000),
    createDate datetime not null default now(),
    clientIpAddress varchar(128),
    operatorId varchar(64),
    operatorName varchar(64),
    operatorLoginName varchar(64),
    organizationId varchar(64),
    vcid varchar(64),
    primary key(id)
);
create index idx_la_request_log_00 on la_request_log(loanAccountId);

drop table if exists la_trading_record;
create table la_trading_record(
	id varchar(64) not null,
	requestId varchar(64) not null,
	sourceType varchar(64),
	loanAccountId varchar(64) not null,
	relatedTradingRecordId varchar(64),
	category varchar(64) not null,
	type varchar(64) not null,
	viewAble bit not null default 1,
	adjust bit not null default 0,
	repaySum decimal(20,6) not null,
	sum decimal(20,6) not null,
	bankAccountId varchar(64),
	repayDate datetime,
	repayDatePeriod varchar(64),
	currentPeriod varchar(64) not null,
	expireDate datetime,
	received bit,
	receiveDate datetime,
	writeOff bit not null default 0,
	writeOffDate datetime,
	beforeAccountStatus varchar(64) not null,
	beforeCollectionStatus varchar(64) not null,
	beforeIsClose bit not null,
	beforeIsDie bit not null,
	beforeIsLegalProcedure bit not null,
	beforePrincipalBalance decimal(16,2) not null,
	beforePrincipalBalanceIrr decimal(16,2) not null,
	beforeSettleInterestStatus varchar(64) not null,
	beforeIsOverdue bit not null,
	beforeOverdueAmount decimal(16,2) not null,
	beforeOverdueSum decimal(16,2) not null,
	beforeOverdueDate datetime,
	afterAccountStatus varchar(64) not null,
	afterCollectionStatus varchar(64) not null,
	afterIsClose bit not null,
	afterIsDie bit not null,
	afterIsLegalProcedure bit not null,
	afterPrincipalBalance decimal(16,2) not null,
	afterPrincipalBalanceIrr decimal(16,2) not null,
	afterSettleInterestStatus varchar(64) not null,
	afterIsOverdue bit not null,
	afterOverdueAmount decimal(16,2) not null,
	afterOverdueSum decimal(16,2) not null,
	afterOverdueDate datetime,
	principalBalance decimal(16,2),
	principalBalanceIrr decimal(16,2),
	outsourceAssignRecordId varchar(64),
	outsourceRepay bit,
	revokeAble bit not null default 0,
	revoked bit not null default 0,
	revokeDate datetime,
	revokeResean varchar(500),
	revokeOperatorId varchar(64),
	summary varchar(500),
	attributes varchar(2000),
	remark varchar(2000),
	createDate datetime not null default now(),
	lastUpdateDate datetime not null default now(),
	lastUpdateOperatorId varchar(64),
	vcid varchar(64),
	operatorId varchar(64),
	organizationId varchar(64),
	primary key(id)
);
create index idx_la_trading_record_01 on la_trading_record(requestId);
create index idx_la_trading_record_02 on la_trading_record(loanAccountId);
create index idx_la_trading_record_03 on la_trading_record(relatedTradingRecordId);
create index idx_la_trading_record_04 on la_trading_record(outsourceAssignRecordId);

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

drop table if exists LA_TRANSFRER_REPAY_SETTING_HIS;
create table LA_TRANSFRER_REPAY_SETTING_HIS(
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
	feeItemType varchar(255),
	success bit,
	urgent bit default 0,
	effective bit default 0,
	primary key(ID)
);
create index idx_TRANSFRER_REPAY_SETTING_HIS_00 on LA_TRANSFRER_REPAY_SETTING_HIS(LOANACCOUNTID);

/*****************************************************************************
			��LA_WHITE_LIST
*****************************************************************************/
drop table if exists LA_WHITE_LIST;
create table LA_WHITE_LIST(
	PHONENUMBER varchar(255),
	CERTIFICATETYPE varchar(255),
	CLIENTNAME varchar(64),
	CERTIFICATENUMBER varchar(255),
	ADDRESS varchar(255),
	ID varchar(64),
	EXPORTOPERATORID varchar(64),
	EMAIL varchar(255),
	CARDNUMBER varchar(255),
	EXPORTDATE datetime,
	primary key(ID)
);
/*
create index idx_xxxx_xxxx on LA_WHITE_LIST(xxxx);
create unique index idx_xxxx_xxxx on LA_WHITE_LIST(xxxx);
*/
/*
comment on table LA_WHITE_LIST is 'LA_WHITE_LIST��Ϣ��';
comment on column LA_WHITE_LIST.PHONENUMBER is 'xxxx';
comment on column LA_WHITE_LIST.CERTIFICATETYPE is 'xxxx';
comment on column LA_WHITE_LIST.CLIENTNAME is 'xxxx';
comment on column LA_WHITE_LIST.CERTIFICATENUMBER is 'xxxx';
comment on column LA_WHITE_LIST.ADDRESS is 'xxxx';
comment on column LA_WHITE_LIST.ID is 'xxxx';
comment on column LA_WHITE_LIST.EXPORTOPERATORID is 'xxxx';
comment on column LA_WHITE_LIST.EMAIL is 'xxxx';
comment on column LA_WHITE_LIST.CARDNUMBER is 'xxxx';
comment on column LA_WHITE_LIST.EXPORTDATE is 'xxxx';
*/
/*****************************************************************************
-- D_DICT : 
*****************************************************************************/
drop table if exists D_DICT;
create table D_DICT(
	id varchar(64) not null,
	name varchar(64) ,
	pic1 varchar(255) ,
	pic2 varchar(255) ,
	primary key(id)
);

/*****************************************************************************
-- MSG_DIALOG_MESSAGE : 
*****************************************************************************/
drop table if exists MSG_DIALOG_MESSAGE;
create table MSG_DIALOG_MESSAGE(
	id varchar(64) not null,
	vcid varchar(64) not null,
	parentId varchar(64),
	type varchar(64) not null,
	title varchar(100) not null,
	content varchar(4000),
	userId varchar(64),
	userType varchar(64),
	topicId varchar(64),
	topicType varchar(64),
	lastUpdateDate datetime(6) not null,
	lastUpdateUserId varchar(64),
	createDate datetime(6) not null,
	createUserId varchar(64),
	primary key(id)
);
create index IDX_DIALOG_MESSAGE_01 on MSG_DIALOG_MESSAGE(userId,userType);
create index IDX_DIALOG_MESSAGE_02 on MSG_DIALOG_MESSAGE(topicId,topicType);
create index IDX_DIALOG_MESSAGE_03 on MSG_DIALOG_MESSAGE(createDate);
/*****************************************************************************
-- MSG_MESSAGE_ATTACHMENT : 
*****************************************************************************/
drop table if exists MSG_MESSAGE_ATTACHMENT;
create table MSG_MESSAGE_ATTACHMENT(
	id varchar(64) not null,
	vcid varchar(64) not null,
	messageType varchar(64) not null,
	messageId varchar(64) not null,
	attachmentId varchar(64) not null,
	attachmentUrl varchar(256) not null,
	lastUpdateDate datetime(6) not null,
	createDate datetime(6) not null,
	primary key(id)
);
create index idx_attachment_01 on MSG_MESSAGE_ATTACHMENT(messageId,messageType);
create index idx_attachment_02 on MSG_MESSAGE_ATTACHMENT(attachmentId);
/*****************************************************************************
-- MSG_PRIVATE_MESSAGE : 
*****************************************************************************/
drop table if exists MSG_PRIVATE_MESSAGE;
create table MSG_PRIVATE_MESSAGE(
	id varchar(64) not null,
	vcid varchar(64) not null,
	type varchar(64) not null,
	userId varchar(64) not null,
	username varchar(64) not null,
	userType varchar(64) not null,
	senderUserId varchar(64) not null,
	senderUsername varchar(64) not null,
	senderUserType varchar(64) not null,
	unread bit not null default 1,
	readDate datetime(6) ,
	title varchar(100) not null,
	content varchar(4000) ,
	lastUpdateDate datetime(6) not null,
	lastUpdateUserId varchar(64) ,
	createDate datetime(6) not null,
	createUserId varchar(64) ,
	primary key(id)
);
create index IDX_PRIVATE_MESSAGE_01 on MSG_PRIVATE_MESSAGE(userId,userType);
create index IDX_PRIVATE_MESSAGE_02 on MSG_PRIVATE_MESSAGE(senderUserId,senderUserType);
create index IDX_PRIVATE_MESSAGE_03 on MSG_PRIVATE_MESSAGE(createDate);

/*****************************************************************************
-- NOTE_NOTEPAD : 
*****************************************************************************/
drop table if exists NOTE_NOTEPAD;
create table NOTE_NOTEPAD(
	id varchar(64) not null,
	vcid varchar(64) not null,
	type varchar(64) not null,
	topicType varchar(64) not null,
	topicId varchar(64) not null,
	catalogId varchar(64) ,
	title varchar(100) not null,
	content varchar(4000) ,
	attributes varchar(4000) ,
	remark varchar(500) ,
	lastUpdateDate datetime(6) not null,
	lastUpdateUserId varchar(64) ,
	createDate datetime(6) not null,
	createUserId varchar(64) ,
	primary key(id)
);
create index IDX_NOTE_NOTEPAD_00 on NOTE_NOTEPAD(vcid);
create index IDX_NOTE_NOTEPAD_01 on NOTE_NOTEPAD(topicId,topicType);
create index IDX_NOTE_NOTEPAD_02 on NOTE_NOTEPAD(catalogId);

/*****************************************************************************
-- NOTE_NOTEPAD_CATALOG : 
*****************************************************************************/
drop table if exists NOTE_NOTEPAD_CATALOG;
create table NOTE_NOTEPAD_CATALOG(
	id varchar(64) not null,
	vcid varchar(64) not null,
	type varchar(64) not null,
	parentId varchar(64),
	topicType varchar(64) not null,
	topicId varchar(64) not null,
	name varchar(100) not null,
	remark varchar(500) ,
	lastUpdateDate datetime(6) not null,
	lastUpdateUserId varchar(64) ,
	createDate datetime(6) not null,
	createUserId varchar(64) ,
	primary key(id)
);
create index IDX_NOTE_NOTEPAD_CATALOG_00 on NOTE_NOTEPAD_CATALOG(vcid);
create index IDX_NOTE_NOTEPAD_CATALOG_01 on NOTE_NOTEPAD_CATALOG(topicId,topicType);

/*****************************************************************************
-- OPER_EMPLOYEE_INFO : 
*****************************************************************************/
drop table if exists OPER_EMPLOYEE_INFO;
create table OPER_EMPLOYEE_INFO(
	id varchar(64) not null,
	operatorId varchar(64) not null,
	number varchar(64) not null,
	idCardType varchar(64),
	idCardNumber varchar(64),
	realName varchar(64),
	mobileNumber varchar(64),
	email varchar(64),
	sex varchar(64),
	birthday datetime(6),
	entryDate datetime(6),
	official bit default 0,
	officialDate datetime(6),
	leaving bit default 0,
	leavingDate datetime(6),
	createDate datetime(6) not null default now(6),
	lastUpdateDate datetime(6) not null default now(6),
	primary key(id)
);
create unique index idx_employee_info_00 on OPER_EMPLOYEE_INFO(operatorId);
create unique index idx_employee_info_01 on OPER_EMPLOYEE_INFO(number);
drop table if exists OPER_EMPLOYEE_INFO_HIS;
CREATE TABLE OPER_EMPLOYEE_INFO_HIS(
	id varchar(64) not null,
	operatorId varchar(64) not null,
	number varchar(64) not null,
	idCardType varchar(64),
	idCardNumber varchar(64),
	realName varchar(64),
	mobileNumber varchar(64),
	email varchar(64),
	sex varchar(64),
	birthday datetime(6),
	entryDate datetime(6),
	official bit default 0,
	officialDate datetime(6),
	leaving bit default 0,
	leavingDate datetime(6),
	createDate datetime(6) not null default now(6),
	lastUpdateDate datetime(6) not null default now(6),
	primary key(id)
);


/*****************************************************************************
-- OPER_OPERATOR : 
*****************************************************************************/
drop table if exists OPER_OPERATOR;
create table OPER_OPERATOR(
	id varchar(64) not null,
	vcid varchar(64) not null,
	username varchar(64) not null,
	usernameChangeCount int not null default 0,
	usernameChangeAble bit not null default 1,
	name varchar(64),
	organizationId varchar(64) not null,
	mainPostId varchar(64),
	password varchar(64) not null,
	historyPwd varchar(64),
	pwdErrCount integer not null default 0,
	pwdUpdateDate datetime(6),
	examinePwd varchar(64),
	admin bit not null default 0,
	locked bit not null default 0,
	modifyAble bit not null default 1,
	valid bit not null default 1,
	invalidDate datetime(6),
	lastUpdateDate datetime(6) not null default now(6),
	createDate datetime(6) not null default now(6),
	primary key(id)
);
create unique index idx_oper_oper_00 on oper_operator(username);
create index idx_oper_oper_01 on oper_operator(vcid);
create index idx_oper_oper_02 on oper_operator(organizationId);
create index idx_oper_oper_03 on oper_operator(createDate);

drop table if exists OPER_OPERATOR_HIS;
create table OPER_OPERATOR_HIS(
	id varchar(64) not null,
	vcid varchar(64) not null,
	username varchar(64) not null,
	usernameChangeCount int not null default 0,
	usernameChangeAble bit not null default 1,
	name varchar(64),
	organizationId varchar(64) not null,
	mainPostId varchar(64),
	password varchar(64) not null,
	historyPwd varchar(64),
	pwdErrCount integer not null default 0,
	pwdUpdateDate datetime(6),
	examinePwd varchar(64),
	locked bit not null default 0,
	modifyAble bit not null default 1,
	valid bit not null default 1,
	invalidDate datetime(6),
	lastUpdateDate datetime(6) not null default now(6),
	createDate datetime(6) not null default now(6),
	primary key(id)
);

/*****************************************************************************
-- OPER_OPERATOR_REF : 
*****************************************************************************/
drop table if exists OPER_OPERATOR_REF;
create table OPER_OPERATOR_REF(
	id varchar(64) not null,
	operatorId varchar(64) not null,
	refId varchar(64) not null,
	refType varchar(64) not null,
	effectiveDate datetime(6) not null default now(6),
	expiryDate datetime(6) ,
	lastUpdateDate datetime(6) not null default now(6),
	lastUpdateOperatorId varchar(64) ,
	createDate datetime(6) not null default now(6),
	createOperatorId varchar(64) ,
	primary key(id)
);
CREATE UNIQUE INDEX idx_oper_operref_00 ON OPER_OPERATOR_REF(refId,refType,operatorId);
CREATE INDEX idx_oper_operref_01 ON OPER_OPERATOR_REF(operatorId);
CREATE INDEX idx_oper_operref_02 ON OPER_OPERATOR_REF(effectiveDate);
CREATE INDEX idx_oper_operref_03 ON OPER_OPERATOR_REF(expiryDate);
CREATE INDEX idx_oper_operref_04 ON OPER_OPERATOR_REF(createDate);

drop table if exists oper_operator_ref_his;
CREATE TABLE oper_operator_ref_his(
	id varchar(64) not null,
	operatorId varchar(64) not null,
	refId varchar(64) not null,
	refType varchar(64) not null,
	effectiveDate datetime(6) not null,
	expiryDate datetime(6) ,
	lastUpdateDate datetime(6) not null,
	lastUpdateOperatorId varchar(64) ,
	createDate datetime(6) not null,
	createOperatorId varchar(64) ,
	primary key(id)
);

/*****************************************************************************
-- OPER_ROLE : 
*****************************************************************************/
drop table if exists OPER_ROLE;
create table OPER_ROLE(
	id varchar(64) not null,
	name varchar(64) not null,
	vcid varchar(64) not null,
	parentId varchar(64),
	catalogId varchar(64) ,
	valid bit not null default 1,
	modifyAble bit not null default 1,
	remark varchar(512) ,
	createDate datetime(6) not null default now(6),
	lastUpdateDate datetime(6) not null default now(6),
	primary key(id)
);
create unique index idx_role_01 on OPER_ROLE(name,vcid);

/*****************************************************************************
-- OPER_ROLE_CATALOG : 
*****************************************************************************/
drop table if exists OPER_ROLE_CATALOG;
create table OPER_ROLE_CATALOG(
	id varchar(64) not null,
	name varchar(64) not null,
	vcid varchar(64) not null,
	valid bit not null default 1,
	modifyAble bit not null default 1,
	parentId varchar(64) ,
	remark varchar(512),
	lastUpdateDate datetime(6) not null default now(6),
	createDate datetime(6) not null default now(6),
	primary key(id)
);

drop table if exists OPER_ROLE_HIS;
create table OPER_ROLE_HIS(
	id varchar(64) not null,
	name varchar(64) not null,
	vcid varchar(64) not null,
	parentId varchar(64),
	catalogId varchar(64) ,
	valid bit not null default 1,
	modifyAble bit not null default 1,
	remark varchar(512) ,
	createDate datetime(6) not null default now(6),
	lastUpdateDate datetime(6) not null default now(6),
	primary key(id)
);

/*****************************************************************************
-- OPER_SECURITY_ACCOUNT : 
*****************************************************************************/
drop table if exists OPER_SECURITY_ACCOUNT;
create table OPER_SECURITY_ACCOUNT(
	id varchar(64) not null,
	operatorId varchar(64) not null,
	mobileNumber varchar(64),
	mobileBinding bit not null default 0,
	mobileLoginEnable bit not null default 0,
	email varchar(64),
	emailBinding bit not null default 0,
	idCardType varchar(64),
	idCardNumber varchar(64),
	idCardExpiryDate datetime(6),
	realNameAuthenticated bit not null default 0,
	realNameErrCount integer,
	realNameLastErrDate datetime(6),
	lastUpdateDate datetime(6) not null default now(6),
	createDate datetime(6) not null default now(6),
	primary key(id)
);
create unique index IDX_SECURITY_ACCOUNT_00 on OPER_SECURITY_ACCOUNT(operatorId);

/*****************************************************************************
-- OPER_SOCIAL_ACCOUNT : 
*****************************************************************************/
drop table if exists OPER_SOCIAL_ACCOUNT;
create table OPER_SOCIAL_ACCOUNT(
	id varchar(64) not null,
	operatorId varchar(64) not null,
	type varchar(64) not null,
	uniqueId varchar(64) not null,
	username varchar(64),
	headImgUrl varchar(256),
	attributes varchar(1000),
	lastUpdateDate datetime(6) not null default now(6),
	createDate datetime(6) not null default now(6),
	primary key(id)
);
create unique index IDX_SOCIAL_ACCOUNT_00 on OPER_SOCIAL_ACCOUNT(uniqueId,type);
create unique index IDX_SOCIAL_ACCOUNT_01 on OPER_SOCIAL_ACCOUNT(operatorId,type);
/*****************************************************************************
-- ORG_ORGANIZATION : 
*****************************************************************************/
drop table if exists ORG_ORGANIZATION;
create table ORG_ORGANIZATION(
	id varchar(64) not null,			
	vcid varchar(64) not null,			
	type varchar(64) not null,			
	code varchar(64) not null,		
	parentId varchar(64),
	companyId varchar(64),
	name varchar(64) not null,
	alias varchar(64),
	fullName varchar(256),
	districtId varchar(64),
	address varchar(64) ,
	fullAddress varchar(256) ,
	valid bit not null,
	remark varchar(512) ,
	createDate datetime(6) not null default now(6),
	lastUpdateDate datetime(6) not null default now(6),
	primary key(id)
);
create unique index idx_org_organization_01 on ORG_ORGANIZATION(code,vcid);
create index idx_org_organization_02 on ORG_ORGANIZATION(parentId);
create index idx_org_organization_03 on ORG_ORGANIZATION(companyId);
create index idx_org_organization_04 on ORG_ORGANIZATION(districtId);

/*****************************************************************************
-- ORG_ORGANIZATION : 
*****************************************************************************/
drop table if exists ORG_ORGANIZATION_HIS;
create table ORG_ORGANIZATION_HIS(
	id varchar(64) not null,			
	vcid varchar(64) not null,			
	type varchar(64) not null,			
	code varchar(64) not null,		
	parentId varchar(64),
	companyId varchar(64),
	name varchar(64) not null,
	alias varchar(64),
	fullName varchar(256),
	districtId varchar(64),
	address varchar(64) ,
	fullAddress varchar(256) ,
	valid bit not null,
	remark varchar(512) ,
	createDate datetime(6) not null default now(6),
	lastUpdateDate datetime(6) not null default now(6),
	primary key(id)
);

/*****************************************************************************
-- ORG_POST : 
*****************************************************************************/
drop table if exists ORG_POST;
create table ORG_POST(
	id varchar(64) not null,
	vcid varchar(64) not null,
	organizationId varchar(64) not null,
	parentId varchar(64),
	code varchar(64) not null,
	name varchar(64) not null,
	fullName varchar(256) ,
	remark varchar(512) ,
	valid bit not null default 1,
	createDate datetime(6) not null default now(6),
	lastUpdateDate datetime(6) not null default now(6),
	primary key(id)
);
create index idx_ORG_post_00 on ORG_POST(parentId);
create unique index idx_ORG_post_01 on ORG_POST(code,vcid);
/*****************************************************************************
-- ORG_POST : 
*****************************************************************************/
drop table if exists ORG_POST;
create table ORG_POST(
	id varchar(64) not null,
	vcid varchar(64) not null,
	organizationId varchar(64) not null,
	parentId varchar(64),
	code varchar(64) not null,
	name varchar(64) not null,
	fullName varchar(256) ,
	remark varchar(512) ,
	valid bit not null default 1,
	createDate datetime(6) not null default now(6),
	lastUpdateDate datetime(6) not null default now(6),
	primary key(id)
);

/*****************************************************************************
-- PER_CREDIT_INFO : 
*****************************************************************************/
drop table if exists PER_CREDIT_INFO;
create table PER_CREDIT_INFO(
	id varchar(64) not null,
	clientId varchar(64) not null,
	idCardType varchar(64) not null,
	idCardNumber varchar(64) not null,
	version integer not null,
	versionType varchar(64) not null,
	lastUpdateDate datetime(6) not null,
	lastUpdateUserId varchar(64) ,
	createDate datetime(6) not null,
	createUserId varchar(64) ,
	primary key(id)
);
create unique index idx_per_credit_info_00 on PER_CREDIT_INFO(clientId,version,versionType);
create unique index idx_per_credit_info_01 on PER_CREDIT_INFO(idCardNumber,idCardType,version,versionType);

/*****************************************************************************
-- PER_PERSONAL_INFO : 
*****************************************************************************/
drop table if exists PER_PERSONAL_INFO;
create table PER_PERSONAL_INFO(
	id varchar(64) not null,
	type varchar(64) not null,
	vcid varchar(64) not null,
	clientId varchar(64) not null,
	creditInfoBinding bit not null default 0,
	creditInfoId varchar(64),
	modifyAble bit not null default 1,
	name varchar(32),
	sex varchar(64),
	birthday datetime(6) ,
	fristName varchar(32),
	lastName varchar(32),
	provinceId varchar(64) ,
	cityId varchar(64) ,
	countyId varchar(64) ,
	districtId varchar(64) ,
	address varchar(256) ,
	fullAddress varchar(256) ,
	remark varchar(500),
	lastUpdateDate datetime(6) not null,
	lastUpdateUserId varchar(64) ,
	createDate datetime(6) not null,
	createUserId varchar(64) ,
	primary key(id)
);
create unique index idx_per_personal_info_00 on PER_PERSONAL_INFO(clientId);
create index idx_per_personal_info_01 on PER_PERSONAL_INFO(creditInfoId);

/*****************************************************************************
-- PER_PERSONAL_SUMMARY : 
*****************************************************************************/
drop table if exists PER_PERSONAL_SUMMARY;
create table PER_PERSONAL_SUMMARY(
	id varchar(64) not null,
	idCardDeadlineId varchar(64) ,
	idCardDistrictId varchar(64) ,
	idCardExpiredDate datetime(6) ,
	landArea decimal(32,8) ,
	liveStatusId varchar(64) ,
	vcid varchar(64) not null,
	frontOfIDCardUrl varchar(255) ,
	laborCount integer ,
	familyCount integer ,
	fatherName varchar(64) ,
	fatherAlive bit ,
	fatherMobileNumber varchar(64) ,
	motherName varchar(64) ,
	motherAlive bit ,
	motherMobileNumber varchar(64) ,
	nativePlaceId varchar(64) ,
	maritalStatusId varchar(64) ,
	reverseOfIDCardUrl varchar(255) ,
	identityStateId varchar(64) ,
	marriageDate datetime(6) ,
	personalId varchar(64) ,
	educationId varchar(64) ,
	primary key(id)
);

/*****************************************************************************
-- VC_VIRTUAL_CENTER : 
*****************************************************************************/
drop table if exists VC_VIRTUAL_CENTER;
create table VC_VIRTUAL_CENTER(
	id varchar(64) not null,
	code varchar(64) not null,
	name varchar(64) not null,
	valid bit not null,
	createDate datetime(6) not null,
	lastUpdateDate datetime(6) not null,
	modifyAble bit not null,
	parentId varchar(64) ,
	remark varchar(512) ,
	primary key(id)
);

-- views
-- functions
-- procedures
-- sequences
-- initdata
update bd_district set `level` = -1;
update bd_district set `level` = 1 where parentId is null or parentId = '';

update bd_district t0,bd_district t1 set t0.`level` = 2 where 
t0.parentId = t1.id and t1.`level` = 1;
update bd_district t0,bd_district t1 set t0.`level` = 3 where 
t0.parentId = t1.id and t1.`level` = 2;
update bd_district t0,bd_district t1 set t0.`level` = 4 where 
t0.parentId = t1.id and t1.`level` = 3;
update bd_district t0,bd_district t1 set t0.`level` = 5 where 
t0.parentId = t1.id and t1.`level` = 4;
update bd_district t0,bd_district t1 set t0.`level` = 6 where 
t0.parentId = t1.id and t1.`level` = 5;

select * from bd_district t where t.`name` = '重庆市';
select * from bd_district t where t.parentId = '500000000000';

select * from bd_district t where t.provinceId = '500000000000' and t.`level` = 5;
INSERT INTO OPER_OPERATOR(id,vcid,organizationId,username,password,modifyAble,name)
	values('123456','JT','1000000000','admin','E10ADC3949BA59ABBE56E057F20F883E',0,'超级管理员');
INSERT INTO OPER_OPERATOR(id,vcid,organizationId,username,password,modifyAble,name)
	values(REPLACE(UUID(),"-",""),'JT','1000000000','pqy','E10ADC3949BA59ABBE56E057F20F883E',1,'彭清杨');
INSERT INTO OPER_OPERATOR(id,vcid,organizationId,username,password,modifyAble,name)
	values(REPLACE(UUID(),"-",""),'JT','1000000000','zlk','E10ADC3949BA59ABBE56E057F20F883E',1,'张礼科');
INSERT INTO OPER_OPERATOR(id,vcid,organizationId,username,password,modifyAble,name)
	values(REPLACE(UUID(),"-",""),'JT','1000000000','zw','E10ADC3949BA59ABBE56E057F20F883E',1,'张威');
INSERT INTO OPER_OPERATOR(id,vcid,organizationId,username,password,modifyAble,name)
	values(REPLACE(UUID(),"-",""),'JT','1000000000','xrx','E10ADC3949BA59ABBE56E057F20F883E',1,'徐茹霞');
commit;


INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('1000000000', 'JT', 'GROUP_COMPANY', 'JT', NULL, '1000000000', '集团公司', '', '集团公司', NULL, NULL, NULL, '', '集团公司', '2019-11-10 22:47:34.364000', '2019-11-10 22:47:34.364000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6e63121c016e6366e2570000', 'JTZGS2', 'COMPANY', 'JTFGS2', '1000000000', '4028819d6e63121c016e6366e2570000', '集团子公司2', '', '集团子公司2', NULL, NULL, NULL, '', '', '2019-11-13 14:15:17.335000', '2019-11-13 14:15:17.335000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6e63121c016e636757e70001', 'JTZGS2', 'DEPARTMENT', 'JTFGS2_CW', '4028819d6e63121c016e6366e2570000', NULL, '财务部', '', '集团子公司2_财务部', NULL, NULL, NULL, '', '11111', '2019-11-13 14:15:47.431000', '2019-11-28 11:46:14.317000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6eb00d4d016eb01f53180002', 'JT', 'BRANCH_DEPARTMENT', 'JT_XTYF_CPGH', '40289f596e606bdd016e607079be0000', '1000000000', '产品规划部', '', '集团公司_系统研发部_产品规划部', NULL, NULL, NULL, '', '', '2019-11-28 11:47:53.240000', '2019-11-28 12:33:25.616000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e607079be0000', 'JT', 'DEPARTMENT', 'JT_XTYF', '1000000000', '1000000000', '系统研发部', '', '集团公司_系统研发部', NULL, NULL, NULL, '', '', '2019-11-13 00:26:54.270000', '2019-11-13 00:26:54.270000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e6073ba0a0001', 'JT', 'DEPARTMENT', 'JT_FXGL', '1000000000', '1000000000', '风险管理部', '', '集团公司_风险管理部', NULL, NULL, NULL, '', '', '2019-11-13 00:30:27.338000', '2019-11-13 00:30:27.338000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e6074e1de0002', 'JT', 'BRANCH_DEPARTMENT', 'JT_XTYF_CS', '40289f596e606bdd016e607079be0000', '1000000000', '测试部', '', '集团公司_系统研发部_测试部', NULL, NULL, NULL, '', '', '2019-11-13 00:31:43.070000', '2019-11-28 11:54:48.411000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e6075e9c00003', 'JT', 'DEPARTMENT', 'JT_CW', '1000000000', '1000000000', '财务部', '', '集团公司_财务部', NULL, NULL, NULL, '', '财务部', '2019-11-13 00:32:50.624000', '2019-11-28 01:58:25.016000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e6076c0ae0004', 'CSGS1', 'COMPANY', 'CS1', NULL, '40289f596e606bdd016e6076c0ae0004', '测试公司1', '', '测试公司1', NULL, NULL, NULL, '', '', '2019-11-13 00:33:45.646000', '2019-11-13 00:33:45.646000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e607709e10005', 'CSGS2', 'COMPANY', 'CS2', NULL, '40289f596e606bdd016e607709e10005', '测试公司2', '', '测试公司2', NULL, NULL, NULL, '', '', '2019-11-13 00:34:04.385000', '2019-11-13 00:34:04.385000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e6077c4af0006', 'JTZGS1', 'COMPANY', 'JTFGS1', '1000000000', '40289f596e606bdd016e6077c4af0006', '集团子公司1', '', '集团子公司1', NULL, NULL, NULL, '', '', '2019-11-13 00:34:52.207000', '2019-11-13 00:34:52.207000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e607843220007', 'JTZGS1', 'DEPARTMENT', 'JTFGS1_CW', '40289f596e606bdd016e6077c4af0006', '40289f596e606bdd016e6077c4af0006', '财务部', '', '集团子公司1_财务部', NULL, NULL, NULL, '', '', '2019-11-13 00:35:24.578000', '2019-11-13 00:35:24.578000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e6078b7f90008', 'CSGS2', 'DEPARTMENT', 'CS2_CW', '40289f596e606bdd016e607709e10005', '40289f596e606bdd016e607709e10005', '财务部', '', '测试公司2_财务部', NULL, NULL, NULL, '', '', '2019-11-13 00:35:54.489000', '2019-11-13 00:35:54.489000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e607940a90009', 'CSGS1', 'DEPARTMENT', 'CS1_CW', '40289f596e606bdd016e6076c0ae0004', '40289f596e606bdd016e6076c0ae0004', '财务部', '', '测试公司1_财务部', NULL, NULL, NULL, '', '', '2019-11-13 00:36:29.481000', '2019-11-13 00:36:29.481000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e6089f7e3000a', 'JT', 'DEPARTMENT', 'JT_PPYY', '1000000000', '1000000000', '品牌运营部', '', '集团公司_品牌运营部', NULL, NULL, NULL, '', '', '2019-11-13 00:54:44.963000', '2019-11-13 00:54:44.963000');
INSERT INTO org_organization (`id`, `vcid`, `type`, `code`, `parentId`, `companyId`, `name`, `alias`, `fullName`, `districtId`, `address`, `fullAddress`, `valid`, `remark`, `createDate`, `lastUpdateDate`) VALUES ('40289f596e606bdd016e608a7f86000b', 'JT', 'DEPARTMENT', 'JT_RLZY', '1000000000', '1000000000', '人力资源管理部', '', '集团公司_人力资源管理部', NULL, NULL, NULL, '', '', '2019-11-13 00:55:19.686000', '2019-11-13 00:55:19.686000');
commit;
INSERT INTO org_post (`id`, `vcid`, `organizationId`, `parentId`, `code`, `name`, `fullName`, `remark`, `valid`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6e7d6029016e7d61d0e10000', 'JT', '1000000000', '4028819d6eb0e54f016eb0fd69da0000', 'JT_CWZJ', '财务总监', NULL, '', '', '0000-00-00 00:00:00.000000', '2019-11-28 17:55:20.206000');
INSERT INTO org_post (`id`, `vcid`, `organizationId`, `parentId`, `code`, `name`, `fullName`, `remark`, `valid`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6e7d6029016e7d626e3c0001', 'JT', '1000000000', '4028819d6eb0e54f016eb0fd69da0000', 'JT_JSZJ', '技术总监', NULL, '', '', '0000-00-00 00:00:00.000000', '2019-11-28 17:55:30.277000');
INSERT INTO org_post (`id`, `vcid`, `organizationId`, `parentId`, `code`, `name`, `fullName`, `remark`, `valid`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6e7d6029016e7d635f870002', 'JT', '40289f596e606bdd016e6075e9c00003', '4028819d6e7d6029016e7d61d0e10000', 'JT_CW_JL', '财务部经理', NULL, '', '', '0000-00-00 00:00:00.000000', '0000-00-00 00:00:00.000000');
INSERT INTO org_post (`id`, `vcid`, `organizationId`, `parentId`, `code`, `name`, `fullName`, `remark`, `valid`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6eb0bf66016eb0c74c310005', 'JT', '40289f596e606bdd016e6075e9c00003', '4028819d6e7d6029016e7d635f870002', 'JT_CW_KJ', '会计', NULL, '', '', '2019-11-28 14:51:21.521000', '2019-11-28 14:51:21.521000');
INSERT INTO org_post (`id`, `vcid`, `organizationId`, `parentId`, `code`, `name`, `fullName`, `remark`, `valid`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6eb0e54f016eb0fd69da0000', 'JT', '1000000000', NULL, 'JT_DSZ', '董事长', NULL, '', '', '2019-11-28 15:50:28.057000', '2019-11-28 15:50:28.057000');
INSERT INTO org_post (`id`, `vcid`, `organizationId`, `parentId`, `code`, `name`, `fullName`, `remark`, `valid`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6eb1666d016eb1682a0e0000', 'JT', '40289f596e606bdd016e6075e9c00003', '4028819d6e7d6029016e7d635f870002', 'JT_CW_CN', '出纳', NULL, '', '', '2019-11-28 17:47:04.078000', '2019-11-28 17:47:04.078000');
INSERT INTO org_post (`id`, `vcid`, `organizationId`, `parentId`, `code`, `name`, `fullName`, `remark`, `valid`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6eb1666d016eb171d66b0001', 'JT', '1000000000', '4028819d6eb0e54f016eb0fd69da0000', 'JT_YYZJ', '运营总监', NULL, '', '', '2019-11-28 17:57:38.027000', '2019-11-28 17:57:38.027000');
INSERT INTO org_post (`id`, `vcid`, `organizationId`, `parentId`, `code`, `name`, `fullName`, `remark`, `valid`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6eb1666d016eb1726fa50002', 'JT', '1000000000', '4028819d6eb0e54f016eb0fd69da0000', 'JT_FXZJ', '风险总监', NULL, '', '', '2019-11-28 17:58:17.253000', '2019-11-28 17:58:17.253000');
INSERT INTO org_post (`id`, `vcid`, `organizationId`, `parentId`, `code`, `name`, `fullName`, `remark`, `valid`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6eb1666d016eb174eff90003', 'JT', '40289f596e606bdd016e607079be0000', '4028819d6e7d6029016e7d626e3c0001', 'JT_YF_JL', '研发部经理', NULL, '', '', '2019-11-28 18:01:01.177000', '2019-11-28 18:01:01.177000');
INSERT INTO org_post (`id`, `vcid`, `organizationId`, `parentId`, `code`, `name`, `fullName`, `remark`, `valid`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6eb1666d016eb175b8840004', 'JT', '40289f596e606bdd016e608a7f86000b', '4028819d6eb1666d016eb1726fa50002', 'JT_RL_JL', '人力资源经理', NULL, '', '', '2019-11-28 18:01:52.516000', '2019-11-28 18:02:00.645000');
INSERT INTO org_post (`id`, `vcid`, `organizationId`, `parentId`, `code`, `name`, `fullName`, `remark`, `valid`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6eb1666d016eb17664980005', 'JT', '40289f596e606bdd016e6074e1de0002', '4028819d6eb1666d016eb174eff90003', 'JT_YF_CS_BZ', '测试部部长', NULL, '', '', '2019-11-28 18:02:36.568000', '2019-11-28 18:09:47.664000');
INSERT INTO org_post (`id`, `vcid`, `organizationId`, `parentId`, `code`, `name`, `fullName`, `remark`, `valid`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6eb1666d016eb176f6830006', 'JT', '40289f596e606bdd016e607079be0000', '4028819d6eb1666d016eb174eff90003', 'JT_YF_XMJL', '项目经理', NULL, '', '', '2019-11-28 18:03:13.923000', '2019-11-28 18:04:25.530000');
INSERT INTO org_post (`id`, `vcid`, `organizationId`, `parentId`, `code`, `name`, `fullName`, `remark`, `valid`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6eb1666d016eb177ecb30007', 'JT', '4028819d6eb00d4d016eb01f53180002', '4028819d6eb1666d016eb174eff90003', 'JT_YF_CPJL', '产品经理', NULL, '', '', '2019-11-28 18:04:16.947000', '2019-11-28 18:11:09.008000');
INSERT INTO org_post (`id`, `vcid`, `organizationId`, `parentId`, `code`, `name`, `fullName`, `remark`, `valid`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6eb1666d016eb1787fdd0008', 'JT', '40289f596e606bdd016e607079be0000', '4028819d6eb1666d016eb176f6830006', 'JT_YF_XTGCS', '系统工程师', NULL, '', '', '2019-11-28 18:04:54.621000', '2019-11-28 18:04:54.621000');
INSERT INTO org_post (`id`, `vcid`, `organizationId`, `parentId`, `code`, `name`, `fullName`, `remark`, `valid`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6eb1666d016eb17912bd0009', 'JT', '40289f596e606bdd016e607079be0000', '4028819d6eb1666d016eb176f6830006', 'JT_YF_CJXTGCS', '初级系统工程师', NULL, '', '', '2019-11-28 18:05:32.221000', '2019-11-28 18:05:32.221000');
INSERT INTO org_post (`id`, `vcid`, `organizationId`, `parentId`, `code`, `name`, `fullName`, `remark`, `valid`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6eb1666d016eb17ae50c000a', 'JT', '40289f596e606bdd016e6073ba0a0001', '4028819d6eb1666d016eb1726fa50002', 'JT_FX_JL', '风险管理部经理', NULL, '', '', '2019-11-28 18:07:31.596000', '2019-11-28 18:07:31.596000');
INSERT INTO org_post (`id`, `vcid`, `organizationId`, `parentId`, `code`, `name`, `fullName`, `remark`, `valid`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6eb1666d016eb17b5837000b', 'JT', '40289f596e606bdd016e6073ba0a0001', '4028819d6eb1666d016eb17ae50c000a', 'JT_FX_ZY', '风险专员', NULL, '', '', '2019-11-28 18:08:01.079000', '2019-11-28 18:08:01.079000');
INSERT INTO org_post (`id`, `vcid`, `organizationId`, `parentId`, `code`, `name`, `fullName`, `remark`, `valid`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6eb1666d016eb17dc509000d', 'JT', '40289f596e606bdd016e6074e1de0002', '4028819d6eb1666d016eb17664980005', 'JT_YF_CS_GCS', '测试工程师', NULL, '', '', '2019-11-28 18:10:40.009000', '2019-11-28 18:10:40.009000');
INSERT INTO org_post (`id`, `vcid`, `organizationId`, `parentId`, `code`, `name`, `fullName`, `remark`, `valid`, `createDate`, `lastUpdateDate`) VALUES ('4028819d6eb1666d016eb17ece32000e', 'JT', '40289f596e606bdd016e6089f7e3000a', '4028819d6eb1666d016eb171d66b0001', 'JT_PP_JJ', '品牌运营经理', NULL, '', '', '2019-11-28 18:11:47.890000', '2019-11-28 18:12:47.180000');
commit;
INSERT INTO vc_virtual_center (`id`, `code`, `name`, `valid`, `createDate`, `lastUpdateDate`, `modifyAble`, `parentId`, `remark`) VALUES ('JT', 'JT', '集团公司', '', '2019-11-10 11:43:42.580000', '2019-11-10 11:43:42.580000', '\0', NULL, NULL);
INSERT INTO vc_virtual_center (`id`, `code`, `name`, `valid`, `createDate`, `lastUpdateDate`, `modifyAble`, `parentId`, `remark`) VALUES ('JTZGS1', 'JTZGS1', '集团子公司1', '', '2019-11-10 11:43:42.754000', '2019-11-10 11:43:43.111000', '\0', 'JT', NULL);
INSERT INTO vc_virtual_center (`id`, `code`, `name`, `valid`, `createDate`, `lastUpdateDate`, `modifyAble`, `parentId`, `remark`) VALUES ('JTZGS2', 'JTZGS2', '集团子公司2', '', '2019-11-10 11:43:42.841000', '2019-11-10 11:43:43.246000', '\0', 'JT', NULL);
INSERT INTO vc_virtual_center (`id`, `code`, `name`, `valid`, `createDate`, `lastUpdateDate`, `modifyAble`, `parentId`, `remark`) VALUES ('CSGS1', 'CSGS1', '测试公司1', '', '2019-11-10 11:43:42.931000', '2019-11-10 11:43:42.931000', '\0', NULL, NULL);
INSERT INTO vc_virtual_center (`id`, `code`, `name`, `valid`, `createDate`, `lastUpdateDate`, `modifyAble`, `parentId`, `remark`) VALUES ('CSGS2', 'CSGS2', '测试公司2', '', '2019-11-10 11:43:43.021000', '2019-11-10 11:43:43.021000', '\0', NULL, NULL);
commit;
-- update oper_operator set vcid = 'JT' where vcid = '1000000000';
-- update oper_operator set vcid = 'JTZGS1' where vcid = '1100000000';
-- update oper_operator set vcid = 'JTZGS2' where vcid = '1200000000';
-- update oper_operator set vcid = 'CSGS1' where vcid = '5000000001';
-- update oper_operator set vcid = 'CSGS2' where vcid = '5000000002';
-- commit;

-- foreignkey
ALTER TABLE INS_INSTITUTION ADD CONSTRAINT FK_INS_INSTITUTION_01 FOREIGN KEY(clientId) REFERENCES CL_CLIENT_INFO(id);

ALTER TABLE OPER_EMPLOYEE_INFO ADD CONSTRAINT FK_EMPLOYEE_01 FOREIGN KEY(operatorId) REFERENCES OPER_OPERATOR(id);

ALTER TABLE OPER_OPERATOR ADD CONSTRAINT FK_OPERATOR_01 FOREIGN KEY(vcid) REFERENCES VC_VIRTUAL_CENTER(id);
ALTER TABLE OPER_OPERATOR ADD CONSTRAINT FK_OPERATOR_02 FOREIGN KEY(organizationId) REFERENCES ORG_ORGANIZATION(id);
ALTER TABLE OPER_OPERATOR ADD CONSTRAINT FK_OPERATOR_03 FOREIGN KEY(mainPostId) REFERENCES ORG_POST(id);

ALTER TABLE ORG_ORGANIZATION ADD CONSTRAINT FK_ORGANIZATION_01 FOREIGN KEY(parentId) REFERENCES ORG_ORGANIZATION(id);
ALTER TABLE ORG_ORGANIZATION ADD CONSTRAINT FK_ORGANIZATION_02 FOREIGN KEY(vcid) REFERENCES VC_VIRTUAL_CENTER(id);

ALTER TABLE ORG_POST ADD CONSTRAINT FK_POST_01 FOREIGN KEY(parentId) REFERENCES ORG_POST(id);
ALTER TABLE ORG_POST ADD CONSTRAINT FK_POST_02 FOREIGN KEY(organizationId) REFERENCES ORG_ORGANIZATION(id);
ALTER TABLE ORG_POST ADD CONSTRAINT FK_POST_03 FOREIGN KEY(vcid) REFERENCES VC_VIRTUAL_CENTER(id);

-- jobs
-- triggers
