CREATE DATABASE  IF NOT EXISTS `chesstime` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `chesstime`;
-- MySQL dump 10.13  Distrib 8.0.13, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: chesstime
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `BoardState`
--

DROP TABLE IF EXISTS `BoardState`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `BoardState` (
  `idBoardState` int(11) NOT NULL AUTO_INCREMENT,
  `idGame` int(11) DEFAULT NULL,
  `1A` varchar(45) DEFAULT '1R',
  `1B` varchar(45) DEFAULT '1Kt',
  `1C` varchar(45) DEFAULT '1B',
  `1D` varchar(45) DEFAULT '1Q',
  `1E` varchar(45) DEFAULT '1K',
  `1F` varchar(45) DEFAULT '1B',
  `1G` varchar(45) DEFAULT '1Kt',
  `1H` varchar(45) DEFAULT '1R',
  `2A` varchar(45) DEFAULT '1P',
  `2B` varchar(45) DEFAULT '1P',
  `2C` varchar(45) DEFAULT '1P',
  `2D` varchar(45) DEFAULT '1P',
  `2E` varchar(45) DEFAULT '1P',
  `2F` varchar(45) DEFAULT '1P',
  `2G` varchar(45) DEFAULT '1P',
  `2H` varchar(45) DEFAULT '1P',
  `3A` varchar(45) DEFAULT NULL,
  `3B` varchar(45) DEFAULT NULL,
  `3C` varchar(45) DEFAULT NULL,
  `3D` varchar(45) DEFAULT NULL,
  `3E` varchar(45) DEFAULT NULL,
  `3F` varchar(45) DEFAULT NULL,
  `3G` varchar(45) DEFAULT NULL,
  `3H` varchar(45) DEFAULT NULL,
  `4A` varchar(45) DEFAULT NULL,
  `4B` varchar(45) DEFAULT NULL,
  `4C` varchar(45) DEFAULT NULL,
  `4D` varchar(45) DEFAULT NULL,
  `4E` varchar(45) DEFAULT NULL,
  `4F` varchar(45) DEFAULT NULL,
  `4G` varchar(45) DEFAULT NULL,
  `4H` varchar(45) DEFAULT NULL,
  `5A` varchar(45) DEFAULT NULL,
  `5B` varchar(45) DEFAULT NULL,
  `5C` varchar(45) DEFAULT NULL,
  `5D` varchar(45) DEFAULT NULL,
  `5E` varchar(45) DEFAULT NULL,
  `5F` varchar(45) DEFAULT NULL,
  `5G` varchar(45) DEFAULT NULL,
  `5H` varchar(45) DEFAULT NULL,
  `6A` varchar(45) DEFAULT NULL,
  `6B` varchar(45) DEFAULT NULL,
  `6C` varchar(45) DEFAULT NULL,
  `6D` varchar(45) DEFAULT NULL,
  `6E` varchar(45) DEFAULT NULL,
  `6F` varchar(45) DEFAULT NULL,
  `6G` varchar(45) DEFAULT NULL,
  `6H` varchar(45) DEFAULT NULL,
  `7A` varchar(45) DEFAULT '2P',
  `7B` varchar(45) DEFAULT '2P',
  `7C` varchar(45) DEFAULT '2P',
  `7D` varchar(45) DEFAULT '2P',
  `7E` varchar(45) DEFAULT '2P',
  `7F` varchar(45) DEFAULT '2P',
  `7G` varchar(45) DEFAULT '2P',
  `7H` varchar(45) DEFAULT '2P',
  `8A` varchar(45) DEFAULT '2R',
  `8B` varchar(45) DEFAULT '2Kt',
  `8C` varchar(45) DEFAULT '2B',
  `8D` varchar(45) DEFAULT '2Q',
  `8E` varchar(45) DEFAULT '2K',
  `8F` varchar(45) DEFAULT '2B',
  `8G` varchar(45) DEFAULT '2Kt',
  `8H` varchar(45) DEFAULT '2R',
  PRIMARY KEY (`idBoardState`),
  KEY `idGame_idx` (`idGame`),
  CONSTRAINT `idGame` FOREIGN KEY (`idGame`) REFERENCES `game` (`idGame`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BoardState`
--

LOCK TABLES `BoardState` WRITE;
/*!40000 ALTER TABLE `BoardState` DISABLE KEYS */;
/*!40000 ALTER TABLE `BoardState` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Game`
--

DROP TABLE IF EXISTS `Game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Game` (
  `idGame` int(11) NOT NULL AUTO_INCREMENT,
  `player_white` varchar(500) DEFAULT NULL,
  `player_black` varchar(500) DEFAULT NULL,
  `status` varchar(45) DEFAULT 'laufend',
  PRIMARY KEY (`idGame`),
  KEY `player_white_idx` (`player_white`),
  KEY `player_black_idx` (`player_black`),
  CONSTRAINT `player_black` FOREIGN KEY (`player_black`) REFERENCES `user` (`password_token`),
  CONSTRAINT `player_white` FOREIGN KEY (`player_white`) REFERENCES `user` (`password_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Game`
--

LOCK TABLES `Game` WRITE;
/*!40000 ALTER TABLE `Game` DISABLE KEYS */;
/*!40000 ALTER TABLE `Game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SearchingUsers`
--

DROP TABLE IF EXISTS `SearchingUsers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `SearchingUsers` (
  `password_token` varchar(500) NOT NULL,
  PRIMARY KEY (`password_token`),
  KEY `idUser_idx` (`password_token`),
  CONSTRAINT `password_token` FOREIGN KEY (`password_token`) REFERENCES `user` (`password_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SearchingUsers`
--

LOCK TABLES `SearchingUsers` WRITE;
/*!40000 ALTER TABLE `SearchingUsers` DISABLE KEYS */;
/*!40000 ALTER TABLE `SearchingUsers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `User` (
  `password_token` varchar(500) NOT NULL,
  `name` varchar(45) NOT NULL,
  `elo` int(11) NOT NULL DEFAULT '250',
  `firebase_token` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`password_token`),
  UNIQUE KEY `password_token_UNIQUE` (`password_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-01 12:21:44
