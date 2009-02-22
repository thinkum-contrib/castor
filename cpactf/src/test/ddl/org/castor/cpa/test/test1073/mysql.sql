drop table if exists test1073_duration;

create table test1073_duration (
  id                 int not null,
  long_duration      bigint,
  string_duration    varchar(100)
);

drop table if exists test1073_time;

create table test1073_time (
  id                 int not null,
  long_time_local    bigint,
  long_time_utc      bigint,
  string_time_local  varchar(100),
  string_time_utc    varchar(100)
);
