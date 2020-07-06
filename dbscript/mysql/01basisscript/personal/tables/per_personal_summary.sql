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
