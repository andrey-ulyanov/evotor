-- MySQL 

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `ID` int NOT NULL,
  `VER` int DEFAULT NULL,
  `TM` datetime DEFAULT NULL,
  `ACTIVE_AFTER` datetime DEFAULT NULL,
  `ACTIVE_UNTIL` datetime DEFAULT NULL,
  `LOGIN` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `LOGIN_UNIQUE` (`LOGIN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customer_account`
--

CREATE TABLE IF NOT EXISTS `customer_account` (
  `ID` int NOT NULL,
  `VER` int DEFAULT NULL,
  `TM` datetime DEFAULT NULL,
  `CURRENCY` char(3) DEFAULT NULL,
  `BALANCE` int DEFAULT NULL,
  `ID_CUSTOMER` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ID_CUSTOMER_idx` (`ID_CUSTOMER`),
  CONSTRAINT `FK_ID_CUSTOMER` FOREIGN KEY (`ID_CUSTOMER`) REFERENCES `customer` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

