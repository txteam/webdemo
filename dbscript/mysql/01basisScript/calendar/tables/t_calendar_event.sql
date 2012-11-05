--start***********************************************************************
--****************************************************************************
-- 行事历
--****************************************************************************
create table t_calendar_event
(
  id           varchar(32) not null primary key,
  title		   varchar(100),
  description  varchar(2000),
  allday       smallint(2) not null,
  startDate    DATE not null,
  endDate      DATE not null,
  createOper   INT(10) not null,
  createDate   DATE not null,
  eventType    smallint(2) not null,
  days         smallint(100),
  updateOper   int(10) not null,
  updateDate   DATE not null
);
create index idx_t_calendar_event_01 on t_calendar_event (createOper);
create index idx_t_calendar_event_02 on t_calendar_event (startDate);
create index idx_t_calendar_event_03 on t_calendar_event (endDate);
