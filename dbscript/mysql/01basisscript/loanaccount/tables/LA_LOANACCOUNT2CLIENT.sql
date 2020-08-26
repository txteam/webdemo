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
