CREATE TABLE TEST1217_PERSON (
    OID VARCHAR(8) NOT NULL,
    NAME VARCHAR(60) NOT NULL,
    PRIMARY KEY (OID)
)
/

CREATE TABLE TEST1217_BASE (
    OID VARCHAR(8) NOT NULL,
    NAME VARCHAR(60) NOT NULL,
    PRIMARY KEY (OID)
)
/

CREATE TABLE TEST1217_EXTENDED (
    OID VARCHAR(8) NOT NULL,
    EXNUM VARCHAR(20) NOT NULL,
    PRIMARY KEY (OID)
)
/
ALTER TABLE TEST1217_EXTENDED
ADD FOREIGN KEY (OID)
REFERENCES TEST1217_BASE (OID)
/

CREATE TABLE TEST1217_PRODUCT (
    OID VARCHAR(8) NOT NULL,
    CODE VARCHAR(12) NOT NULL,
    PRICE NUMERIC(12,2) NOT NULL,
    COMPANY VARCHAR(8) NOT NULL,
    PART VARCHAR(8) NOT NULL,
    PRIMARY KEY (OID)
)
/
ALTER TABLE TEST1217_PRODUCT
ADD FOREIGN KEY (COMPANY)
REFERENCES TEST1217_PERSON (OID)
/
ALTER TABLE TEST1217_PRODUCT
ADD FOREIGN KEY (PART)
REFERENCES TEST1217_BASE (OID)
/
