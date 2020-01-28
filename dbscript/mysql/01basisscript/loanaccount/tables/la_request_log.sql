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
