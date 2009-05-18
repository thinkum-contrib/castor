CREATE TABLE TEST1196_DRIVER(
	OID varchar(8) NOT NULL,
	NAME varchar(60) NOT NULL,
	CAR varchar(8),
PRIMARY KEY ( OID )
)
GO

CREATE TABLE TEST1196_CAR(
	OID varchar(8) NOT NULL,
	NAME varchar(60) NOT NULL,
	DRIVER varchar(8),
PRIMARY KEY ( OID ) 
) 
GO

CREATE TABLE TEST1196_ORDER(
	OID varchar(8) NOT NULL,
	ONUM int NOT NULL,
PRIMARY KEY ( OID )
)
GO

INSERT TEST1196_ORDER (OID, NUM) VALUES ('AAAAOR01', 1)
GO

CREATE TABLE TEST1196_COUNTRY(
	OID varchar(8) NOT NULL,
	NAME varchar(60) NOT NULL,
PRIMARY KEY ( OID )
) 
GO

CREATE TABLE TEST1196_PRODUCT(
	OID varchar(8) NOT NULL,
	NAME varchar(60) NOT NULL,
PRIMARY KEY ( OID ) 
)
GO

INSERT TEST1196_PRODUCT (OID, NAME) VALUES ('AAAACP01', 'COMPUTER 01')
GO
INSERT TEST1196_PRODUCT (OID, NAME) VALUES ('AAAACP02', 'COMPUTER 02')
GO

CREATE TABLE TEST1196_STATE(
	OID varchar(8) NOT NULL,
	NAME varchar(60) NOT NULL,
	COUNTRY varchar(8) NOT NULL,
PRIMARY KEY ( OID ) 
) 
GO

CREATE TABLE TEST1196_ORDERITEM(
	OID varchar(8) NOT NULL,
	QUANTITY int NOT NULL,
	PRODUCT varchar(8) NULL,
	PARENT varchar(8) NOT NULL,
PRIMARY KEY ( OID ) 
) 
GO

INSERT TEST1196_ORDERITEM (OID, QUANTITY, PRODUCT, PARENT) VALUES ('AAOR01I1', 1, 'AAAACP01', 'AAAAOR01')
GO

CREATE TABLE TEST1196_COMPUTER(
	OID varchar(8) NOT NULL,
	SNUM varchar(20) NOT NULL,
	ORDERITEM varchar(8),
PRIMARY KEY ( OID )
) 
GO

INSERT TEST1196_COMPUTER (OID, SNUM, ORDERITEM) VALUES ('AAAACP01', 'CP01', 'AAOR01I1')
GO
INSERT TEST1196_COMPUTER (OID, SNUM, ORDERITEM) VALUES ('AAAACP02', 'CP02', NULL)
GO

ALTER TABLE TEST1196_CAR ADD  CONSTRAINT TEST1196_DRIVER_FK FOREIGN KEY(DRIVER)
REFERENCES TEST1196_DRIVER (OID)
GO
ALTER TABLE TEST1196_CAR CHECK CONSTRAINT TEST1196_DRIVER_FK
GO
ALTER TABLE TEST1196_COMPUTER ADD FOREIGN KEY(ORDERITEM)
REFERENCES TEST1196_ORDERITEM (OID)
GO
ALTER TABLE TEST1196_COMPUTER ADD FOREIGN KEY(OID)
REFERENCES TEST1196_PRODUCT (OID)
GO
ALTER TABLE TEST1196_DRIVER ADD  CONSTRAINT TEST1196_CAR_FK FOREIGN KEY(CAR)
REFERENCES TEST1196_CAR (OID)
GO
ALTER TABLE TEST1196_DRIVER CHECK CONSTRAINT TEST1196_CAR_FK
GO
ALTER TABLE TEST1196_ORDERITEM ADD FOREIGN KEY(PARENT)
REFERENCES TEST1196_ORDER (OID)
GO
ALTER TABLE TEST1196_ORDERITEM ADD FOREIGN KEY(PRODUCT)
REFERENCES TEST1196_PRODUCT (OID)
GO
ALTER TABLE TEST1196_STATE ADD FOREIGN KEY(COUNTRY)
REFERENCES TEST1196_COUNTRY (OID)
GO
