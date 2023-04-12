SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

CREATE DATABASE IF NOT EXISTS `asta`;
USE `asta`;

-- tabella categorie

CREATE TABLE `categorie` (
  `CategoriaID` int(1) NOT NULL,
  `NomeCategoria` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`CategoriaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- tabella oggetti

CREATE TABLE `oggetti` (
  `OggettoID` int(2) NOT NULL,
  `NomeOggetto` varchar(9) DEFAULT NULL,
  `CategoriaID` int(1) DEFAULT NULL,
  `Quantita` int(1) DEFAULT NULL,
  `BaseAsta` float(1) DEFAULT NULL,
  `IpMulticast` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`OggettoID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `oggetti`
  ADD CONSTRAINT `FK1_oggetti` FOREIGN KEY (`CategoriaID`) REFERENCES `categorie` (`CategoriaID`);

-- tabella clienti

CREATE TABLE `clienti` (
  `ClienteID` int(2) NOT NULL,
  `Nome` varchar(9) DEFAULT NULL,
  `Cognome` varchar(9) DEFAULT NULL,
  `NumeroTelefonico` varchar(9) DEFAULT NULL,
  `OggettoID` int(1) NOT NULL,
  PRIMARY KEY (`ClienteID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `clienti`
  ADD CONSTRAINT `FK1_clienti` FOREIGN KEY (`OggettoID`) REFERENCES `oggetti` (`OggettoID`);

COMMIT;


INSERT INTO categorie(CategoriaID, NomeCategoria)
VALUES
(0, "Auto"),
(1, "Telefoni"),
(2, "Computer"),
(3, "Libri");
