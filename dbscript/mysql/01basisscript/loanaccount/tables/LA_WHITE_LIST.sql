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