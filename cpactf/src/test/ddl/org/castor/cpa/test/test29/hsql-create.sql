create table test29_depend1 (
  id          int not null primary key
);

create table test29_depend_master (
  id          int not null primary key,
  depend1_id  int,
  foreign key ( depend1_id ) references test29_depend1 ( id )
);

create table test29_depend2 (
  id          int not null primary key,
  master_id   int,
  foreign key ( master_id ) references test29_depend_master ( id )
);