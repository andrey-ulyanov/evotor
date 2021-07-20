-- H2 

--
-- Table structure for table "customer"
--

CREATE TABLE IF NOT EXISTS "CUSTOMER" (
  "ID" int NOT NULL,
  "VER" int DEFAULT NULL,
  "TM" datetime DEFAULT NULL,
  "ACTIVE_AFTER" datetime DEFAULT NULL,
  "ACTIVE_UNTIL" datetime DEFAULT NULL,
  "LOGIN" varchar(255) DEFAULT NULL,
  "PASSWORD" varchar(255) DEFAULT NULL,
  PRIMARY KEY ("ID"),
  CONSTRAINT "LOGIN_UNIQUE" UNIQUE ("LOGIN")  
);


--
-- Table structure for table "customer_account"
--

CREATE TABLE IF NOT EXISTS "CUSTOMER_ACCOUNT" (
  "ID" int NOT NULL,
  "VER" int DEFAULT NULL,
  "TM" datetime DEFAULT NULL,
  "CURRENCY" char(3) DEFAULT NULL,
  "BALANCE" int DEFAULT NULL,
  "ID_CUSTOMER" int DEFAULT NULL,
  PRIMARY KEY ("ID"), 
  FOREIGN KEY ("ID_CUSTOMER") REFERENCES "CUSTOMER"("ID")   
);


