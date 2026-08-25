create table BlogStats_BlogStatistic (
	blogStatisticId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	blogsEntryId LONG,
	viewCount LONG
);