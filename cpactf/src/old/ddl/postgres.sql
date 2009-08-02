-- tc0x TESTS

drop table tc0x_sample;

create table tc0x_sample (
  id      int           not null,
  value1  varchar(200)  not null,
  value2  varchar(200)
);

create unique index tc0x_sample_pk on tc0x_sample ( id );

drop table tc0x_race;

create table tc0x_race (
  id      int          not null,
  value1  int          not null
);

create unique index tc0x_race_pk on tc0x_race ( id );

-- UDEFINED TESTS

drop table tc7x_table;

create table tc7x_table (
  id      int           not null,
  value1  varchar(200)  not null,
  value2  varchar(200)
);

create unique index tc7x_table_pk
   on tc7x_table ( id );


drop table tc7x_group_person;

create table tc7x_group_person (
  gid int         not null,
  pid int        not null
);

create index tc7x_group_person_p_pk on tc7x_group_person ( pid );

create index tc7x_group_person_g_pk on tc7x_group_person ( gid );


drop table tc7x_many_group;

create table tc7x_many_group (
  gid       int           not null,
  value1    varchar(100)  not null
);

create unique index tc7x_many_group_pk on tc7x_many_group ( gid );


drop table tc7x_many_person;

create table tc7x_many_person (
   pid      int          not null,
   value1   varchar(100) not null,
   helloworld varchar(100) null,
   sthelse varchar(100) null
);

create unique index tc7x_many_person_pk on tc7x_many_person ( pid );


drop table list_types;

create table list_types (
  o_char  CHAR(100)         null,
  o_nchar NATIONAL CHAR(100)   null,
  o_varchar VARCHAR(20) null,
  o_varchar2 VARCHAR(20) null,
  o_clob text null,
  o_long oid null,
  o_number NUMERIC null,
  o_int   INT null,
  o_date timestamp without time zone null,
  o_raw   oid     null,
  o_blob  oid         null,
  o_bfile oid  null
);

drop sequence test_keygen_seq;

create sequence test_keygen_seq;

drop table test_oqltag;

create table test_oqltag (
  id1   integer         not null,
  id2   integer         not null
);

create index test_oqltag_fk1 on test_oqltag( id1 );
create index test_oqltag_fk2 on test_oqltag( id2 );

drop table tc8x_pks_person;

create table tc8x_pks_person (
  fname varchar(15)    not null,
  lname varchar(15)    not null,
  bday  timestamp without time zone null
);

create unique index tc8x_pks_person_pk on tc8x_pks_person( fname, lname );


drop table tc8x_pks_employee;

create table tc8x_pks_employee (
  fname varchar(15)    not null,
  lname varchar(15)    not null,
  start_date timestamp without time zone null
);

create unique index tc8x_pks_person_employee_pk on tc8x_pks_employee( fname, lname );


drop table tc8x_pks_project;

create table tc8x_pks_project (
  fname varchar(100)    not null,
  lname varchar(100)    not null,
  id    int             not null,
  name  varchar(100)
);

create unique index tc8x_pks_project_pk on tc8x_pks_project( id );

drop table tc8x_pks_payroll;

create table tc8x_pks_payroll (
  fname varchar(15)    not null,
  lname varchar(15)    not null,
  id int               not null,
  holiday int,
  hourly_rate int
);

create unique index tc8x_pks_payroll_fk on tc8x_pks_payroll( fname, lname );

create unique index tc8x_pks_payroll_pk on tc8x_pks_payroll( id );


drop table tc8x_pks_address;

create table tc8x_pks_address (
  fname varchar(15)    not null,
  lname varchar(15)    not null,
  id int               not null,
  street varchar(30)    null,
  city  varchar(30)    null,
  state varchar(2)     null,
  zip varchar(6)       null
);

create unique index tc8x_pks_address_pk on tc8x_pks_address( id );


drop table tc8x_pks_contract;

create table tc8x_pks_contract (
  fname varchar(15)    not null,
  lname varchar(15)    not null,
  policy_no int        not null,
  contract_no int      not null,
  c_comment varchar(90)  null
);

create unique index tc8x_pks_contract_fk on tc8x_pks_contract( fname, lname );

create unique index tc8x_pks_contract_pk on tc8x_pks_contract( policy_no, contract_no );


drop table tc8x_pks_category_contract;

create table tc8x_pks_category_contract (
  policy_no int        not null,
  contract_no int      not null,
  cate_id int          not null
);


drop table tc8x_pks_category;

create table tc8x_pks_category (
  id  int              not null,
  name varchar(20)     not null
);

create unique index tc8x_pks_category_pk on tc8x_pks_category( id );


drop table tc8x_nton_a;
create table tc8x_nton_a (
  id         varchar(20)    not null,
  status     integer        not null
);

drop table tc8x_nton_b;
create table tc8x_nton_b (
  id         varchar(20)    not null,
  status     integer        not null
);


drop table tc7x_depend2;
drop table tc7x_master;
drop table tc7x_depend1;

create table tc7x_depend1(
  id int not null primary key
);

create table tc7x_master(
  depend1_id int ,
  id int not null primary key
);

create table tc7x_depend2(
  master_id int,
  id int not null primary key
);

alter table tc7x_master
    add constraint fk_master_depend1
    foreign key (depend1_id) references tc7x_depend1(id);

alter table tc7x_depend2
    add constraint fk_depend2_master
    foreign key (master_id) references tc7x_master(id);

drop sequence tc8x_circbrother_seq;
drop sequence tc8x_circsister_seq;

drop table tc8x_circbrother;
drop table tc8x_circsister;

create sequence tc8x_circbrother_seq;
create sequence tc8x_circsister_seq;

create table tc8x_circbrother (
    brother_id int not null,
    brother_sibling int,
    primary key (brother_id));

create table tc8x_circsister (
    sister_id int not null,
    sister_sibling int,
    primary key (sister_id));

drop table tc8x_enum_prod;
create table tc8x_enum_prod (
  id        int not null,
  name      varchar(200) not null,
  kind      varchar(200) not null
);

-- test objects for TestTransientAttribute 

drop table tc8x_trans_master;
create table tc8x_trans_master (
  id        int not null,
  name      varchar(200) not null,
  propty1    int,
  propty2    int,
  propty3    int,
  ent2        int
);

drop table tc8x_trans_child1;
create table tc8x_trans_child1 (
  id        int not null,
  descr     varchar(200) not null
);

drop table tc8x_trans_child2;
create table tc8x_trans_child2 (
  id        int not null,
  descr     varchar(200) not null
);

insert into tc8x_trans_master (id, name, propty1, propty2, ent2) values (1, 'entity1', 1, 2, 1);
insert into tc8x_trans_child1 (id, descr) values (1, 'description1');
insert into tc8x_trans_child2 (id, descr) values (1, 'description1');
insert into tc8x_trans_child2 (id, descr) values (2, 'description2');
insert into tc8x_trans_child2 (id, descr) values (3, 'description3');

-- tc9x TESTS

drop table tc9x_poly_ordr;
create table tc9x_poly_ordr (
  id int not null,
  name varchar (20) not null
);

drop table tc9x_poly_detail;
create table tc9x_poly_detail (
  id int not null,
  category varchar (20) not null,
  location varchar (20) not null
);

drop table tc9x_poly_owner;
create table tc9x_poly_owner (
  id int not null,
  name varchar (20) not null,
  product int not null
);

drop table tc9x_poly_prod;
create table tc9x_poly_prod (
  id        int not null,
  name      varchar(200) not null,
  detail    int not null,
  owner        int
);

drop table tc9x_poly_computer;
create table tc9x_poly_computer (
  id   int not null,
  cpu  varchar(200) not null
);

drop table tc9x_poly_laptop;
create table tc9x_poly_laptop (
  id   int not null,
  weight  int not null,
  resolution varchar(19) not null
);

drop table tc9x_poly_server;
create table tc9x_poly_server (
  id   int not null,
  numberOfCPUs  int not null,
  support int not null
);

drop table tc9x_poly_car;
create table tc9x_poly_car (
  id   int not null,
  kw   int not null,
  make  varchar(200) not null
);

drop table tc9x_poly_truck;
create table tc9x_poly_truck (
  id   int not null,
  max_weight   int not null
);

drop table tc9x_poly_prod_multi;
create table tc9x_poly_prod_multi (
  id1        int not null,
  id2        int not null,
  name      varchar(200) not null
);

drop table tc9x_poly_computer_multi;
create table tc9x_poly_computer_multi (
  id1   int not null,
  id2        int not null,
  cpu  varchar(200) not null
);

drop table tc9x_poly_laptop_multi;
create table tc9x_poly_laptop_multi (
  id1   int not null,
  id2        int not null,
  weight  int not null,
  resolution varchar(19) not null
);

drop table tc9x_poly_server_multi;
create table tc9x_poly_server_multi (
  id1   int not null,
  id2        int not null,
  numberOfCPUs  int not null,
  support int not null
);

drop table tc9x_poly_order_product;
create table tc9x_poly_order_product (
  order_id    int not null,
  product_id int not null
);

drop table tc9x_poly_table_m;
create table tc9x_poly_table_m (
  id    int not null,
  name    varchar(20) not null
);

drop table tc9x_poly_table_n;
create table tc9x_poly_table_n (
  id    int not null,
  name    varchar(20) not null
);

drop table tc9x_poly_m_n;
create table tc9x_poly_m_n (
  m_id    int not null,
  n_id int not null
);


insert into tc9x_poly_detail (id, category, location) values (1, 'category 1', 'location 1');
insert into tc9x_poly_detail (id, category, location) values (2, 'category 2', 'location 2');
insert into tc9x_poly_detail (id, category, location) values (3, 'category 3', 'location 3');
insert into tc9x_poly_detail (id, category, location) values (4, 'category 4', 'location 4');
insert into tc9x_poly_detail (id, category, location) values (5, 'category 5', 'location 5');

insert into tc9x_poly_prod (id, name, detail, owner) values (1, 'laptop 1', 1, 1);
insert into tc9x_poly_computer (id, cpu) values (1, 'centrino');
insert into tc9x_poly_laptop (id, weight, resolution) values (1, 2800, '1280');

insert into tc9x_poly_prod (id, name, detail, owner) values (2, 'laptop 2', 2, 2);
insert into tc9x_poly_computer (id, cpu) values (2, 'centrino');
insert into tc9x_poly_laptop (id, weight, resolution) values (2, 2700, '1024');

insert into tc9x_poly_prod (id, name, detail, owner) values (3, 'server 3', 3, 3);
insert into tc9x_poly_computer (id, cpu) values (3, 'pentium 4');
insert into tc9x_poly_server (id, numberOfCPUs, support) values (3, 4, 3);

insert into tc9x_poly_prod (id, name, detail, owner) values (4, 'server 4', 4, 4);
insert into tc9x_poly_computer (id, cpu) values (4, 'pentium 4');
insert into tc9x_poly_server (id, numberOfCPUs, support) values (4, 16,5);

insert into tc9x_poly_prod (id, name, detail, owner) values (5, 'truck 5', 5, 5);
insert into tc9x_poly_car (id, kw, make) values (5, 60, 'make 5');
insert into tc9x_poly_truck (id, max_weight) values (5, 4);

insert into tc9x_poly_prod_multi (id1, id2, name) values (1, 1, 'laptop 1');
insert into tc9x_poly_computer_multi (id1, id2, cpu) values (1, 1, 'centrino');
insert into tc9x_poly_laptop_multi (id1, id2, weight, resolution) values (1, 1, 2800, '1280');

insert into tc9x_poly_prod_multi (id1, id2, name) values (2, 2, 'laptop 2');
insert into tc9x_poly_computer_multi (id1, id2, cpu) values (2, 2, 'centrino');
insert into tc9x_poly_laptop_multi (id1, id2, weight, resolution) values (2, 2, 2700, '1024');

insert into tc9x_poly_prod_multi (id1, id2, name) values (3, 3, 'server 3');
insert into tc9x_poly_computer_multi (id1, id2, cpu) values (3, 3, 'pentium 4');
insert into tc9x_poly_server_multi (id1,  id2, numberOfCPUs, support) values (3, 3, 4, 3);

insert into tc9x_poly_prod_multi (id1, id2, name) values (4, 4, 'server 4');
insert into tc9x_poly_computer_multi (id1, id2, cpu) values (4, 4, 'pentium 4');
insert into tc9x_poly_server_multi (id1, id2, numberOfCPUs, support) values (4, 4, 16,5);

insert into tc9x_poly_owner (id, name, product) values (1, 'owner 1', 1);
insert into tc9x_poly_owner (id, name, product) values (2, 'owner 2', 2);
insert into tc9x_poly_owner (id, name, product) values (3, 'owner 3', 3);
insert into tc9x_poly_owner (id, name, product) values (4, 'owner 4', 4);
insert into tc9x_poly_owner (id, name, product) values (5, 'owner 5', 5);

insert into tc9x_poly_ordr (id, name) values (1, 'order 1');

insert into tc9x_poly_order_product (order_id, product_id) values (1, 1);
insert into tc9x_poly_order_product (order_id, product_id) values (1, 2);

insert into tc9x_poly_m_n (m_id, n_id) values (1, 1);
insert into tc9x_poly_m_n (m_id, n_id) values (1, 2);

insert into tc9x_poly_table_m (id, name) values (1, 'm1');
insert into tc9x_poly_table_m (id, name) values (2, 'm2');

insert into tc9x_poly_table_n (id, name) values (1, 'n1');
insert into tc9x_poly_table_n (id, name) values (2, 'n2');

drop table tc9x_poly_base;
create table tc9x_poly_base (
  id varchar(64) not null,
  color varchar(64) null,
  primary key  (ID)
) ;

insert into tc9x_poly_base values ('100','red');

drop table tc9x_poly_derived;
create table tc9x_poly_derived (
  id varchar(64) not null,
  scent varchar(64) null,
  primary key  (ID)
);
insert into tc9x_poly_derived values ('100','vanilla');


drop table tc9x_poly_container;
create table tc9x_poly_container (
  id varchar(64) not null,
  reference varchar(64) null,
  primary key  (ID)
);
insert into tc9x_poly_container values ('200','100');

drop table tc9x_poly_Product;
create table tc9x_poly_Product(
  IdProd int primary key,
  NameProd   varchar(30) null,
  DescProd   varchar(30) null
);

drop table tc9x_poly_ActProduct;
create table tc9x_poly_ActProduct(
  IdAct int primary key,
  BestSeason varchar(30) null
);


drop table tc9x_poly_ComposedOffer;
create table tc9x_poly_ComposedOffer(
  IdCOffer numeric(10) primary key,
  NameCO   varchar(30) null,
  DescCO   varchar(30) null
);

drop table tc9x_poly_OfferComposition;
create table tc9x_poly_OfferComposition(
  Offer numeric(10),
  Product numeric(10), 
  constraint unique_rel unique (Offer, Product) 
);

-- tables required for TestPolymorphismDependedndObject

DROP TABLE tc9x_poly_extend_object;
CREATE TABLE tc9x_poly_extend_object (
  id            int NOT NULL ,
  description2  varchar(50) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO tc9x_poly_extend_object VALUES (1, 'This is the extended object.');

DROP TABLE tc9x_poly_base_object;
CREATE TABLE tc9x_poly_base_object (
  id           int NOT NULL ,
  description  varchar(50) NOT NULL,
  saved        char(1),
  PRIMARY KEY (id)
);

INSERT INTO tc9x_poly_base_object VALUES (1, 'This is the test object.', '0');

DROP TABLE tc9x_poly_depend_object;
CREATE TABLE tc9x_poly_depend_object (
  id           int NOT NULL,
  parentId     int NOT NULL,
  description  varchar(50) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO tc9x_poly_depend_object VALUES(1, 1, 'This is a description');

-- TC20x - self-referential relations 
 
drop table tc200_self_relation_folder;
create table tc200_self_relation_folder (
  id          int                 not null,  
  name        varchar(255)        not null,
  parent_id      int                null
  
);

drop table tc200_self_relation_parent;
create table tc200_self_relation_parent (
  id          int                 not null,  
  name        varchar(255)        not null
);

drop table tc200_self_relation_extend;
create table tc200_self_relation_extend (
  id          int                 not null,  
  parent_id      int                null
);
    
