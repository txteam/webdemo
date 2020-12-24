/*****************************************************************************
-- OPER_ROLE : 
*****************************************************************************/
drop table if exists OPER_REMEMBER_ME_TOKEN;
create table OPER_REMEMBER_ME_TOKEN (
	id varchar(64) primary key,
	username varchar(64) not null,
	token varchar(2000) not null, 
	ipAddress varchar(64) not null, 
	lastUseDate timestamp not null
);
create index idx_oper_rem_me_logins_01 on OPER_REMEMBER_ME_TOKEN(username);