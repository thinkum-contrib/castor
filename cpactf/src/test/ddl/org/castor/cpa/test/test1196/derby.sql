CREATE TABLE TEST1196_COUNTRY (
    OID VARCHAR(8) NOT NULL,
    NAME VARCHAR(60) NOT NULL,
    PRIMARY KEY (OID)
);

CREATE TABLE TEST1196_STATE (
    OID VARCHAR(8) NOT NULL,
    NAME VARCHAR(60) NOT NULL,
    COUNTRY VARCHAR(8) NOT NULL,
    PRIMARY KEY (OID)
);

ALTER TABLE TEST1196_STATE
ADD FOREIGN KEY (COUNTRY)
REFERENCES TEST1196_COUNTRY (OID);


CREATE TABLE TEST1196_CAR (
    OID VARCHAR(8) NOT NULL,
    NAME VARCHAR(60) NOT NULL,
    DRIVER VARCHAR(8),
    PRIMARY KEY (OID)
);

CREATE TABLE TEST1196_DRIVER (
    OID VARCHAR(8) NOT NULL,
    NAME VARCHAR(60) NOT NULL,
    CAR VARCHAR(8),
    PRIMARY KEY (OID)
);

ALTER TABLE TEST1196_CAR
ADD CONSTRAINT TEST1196_DRIVER_FK FOREIGN KEY (DRIVER)
REFERENCES TEST1196_DRIVER (OID);

ALTER TABLE TEST1196_DRIVER
ADD CONSTRAINT TEST1196_CAR_FK FOREIGN KEY (CAR)
REFERENCES TEST1196_CAR (OID);


CREATE TABLE TEST1196_ORDER (
    OID VARCHAR(8) NOT NULL,
    NUM INTEGER NOT NULL,
    PRIMARY KEY (OID)
);

CREATE TABLE TEST1196_PRODUCT (
    OID VARCHAR(8) NOT NULL,
    NAME VARCHAR(60) NOT NULL,
    PRIMARY KEY (OID)
);

CREATE TABLE TEST1196_ORDERITEM (
    OID VARCHAR(8) NOT NULL,
    QUANTITY INTEGER NOT NULL,
    PRODUCT VARCHAR(8),
    PARENT VARCHAR(8) NOT NULL,
    PRIMARY KEY (OID)
);

ALTER TABLE TEST1196_ORDERITEM
ADD FOREIGN KEY (PRODUCT)
REFERENCES TEST1196_PRODUCT (OID);

ALTER TABLE TEST1196_ORDERITEM
ADD FOREIGN KEY (PARENT)
REFERENCES TEST1196_ORDER (OID);

CREATE TABLE TEST1196_COMPUTER (
    OID VARCHAR(8) NOT NULL,
    NUM VARCHAR(20) NOT NULL,
    ORDERITEM VARCHAR(8),
    PRIMARY KEY (OID)
);

ALTER TABLE TEST1196_COMPUTER
ADD FOREIGN KEY (OID)
REFERENCES TEST1196_PRODUCT (OID);

ALTER TABLE TEST1196_COMPUTER
ADD FOREIGN KEY (ORDERITEM)
REFERENCES TEST1196_ORDERITEM (OID);
