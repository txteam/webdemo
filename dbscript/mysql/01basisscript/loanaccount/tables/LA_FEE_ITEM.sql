drop table if exists LA_FEE_ITEM;
create table LA_FEE_ITEM(
	id varchar(64) not null,
	loanAccountId varchar(64) not null,
	feeItem varchar(64) not null,
	value varchar(500) not null,
	primary key(id)
);
create unique index IDX_LA_FEE_ITEM_00 on LA_FEE_ITEM(loanAccountId,feeItem);
