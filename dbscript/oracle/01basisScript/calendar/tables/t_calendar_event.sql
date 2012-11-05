--start***********************************************************************
--****************************************************************************
-- 行事历
--****************************************************************************
create table 't_calendar_event'
(
  'id'           varchar(32) not null,
  'title'		 varchar(100),
  'description'  varchar(2000 CHAR),
  'allday'       NUMBER(2) not null,
  'startDate'    DATE not null,
  'endDate'      DATE not null,
  'createOper'   NUMBER(10) not null,
  'createDate'   DATE not null,
  'eventType'    NUMBER(2) not null,
  'notify'       NUMBER(2) not null,
  'notifyhours'  NUMBER(8) not null,
  'datetype'     NUMBER(2) not null,
  'days'         VARCHAR2(100),
  'updateOper'   NUMBER(10) not null,
  'updateDate'   DATE not null,
  'notifyaMount' INTEGER default (0) not null,
  'interval'     INTEGER default (0) not null,
  primary key(id)
);
create index idx_t_calendar_event_01 on t_calendar_event (createOper);
create index idx_t_calendar_event_02 on t_calendar_event (startDate);
create index idx_t_calendar_event_03 on t_calendar_event (endDate);
--end**************************************************************************
