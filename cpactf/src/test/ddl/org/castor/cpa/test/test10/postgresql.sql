drop table test10_handling;

create table test10_handling (
  id              numeric(10,0)                  not null,
  int_val         integer                        null,
  float_val       float                          null,
  real_val        real                           null,
  long_val        bigint                         null,
  char_val        char(1)                        null,
  bool_val        char(1)                        null,
  bool_is_method  char(1)                        null,
  int_date        integer                        null,
  str_time        varchar(40)                    null,
  num_date        numeric(17,0)                  null
);

create unique index test10_handling_pk on test10_handling ( id );
