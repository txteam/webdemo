alter table CL_CLIENT_INFO add column
	invalidDate datetime(6) ;
alter table CL_CLIENT_INFO add column
	mobileLoginEnable bit not null default 0;