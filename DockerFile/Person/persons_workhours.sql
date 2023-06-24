-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: persons
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `workhours`
--

DROP TABLE IF EXISTS `workhours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workhours` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `data` date NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `workhours_ibfk_1` (`user_id`),
  CONSTRAINT `workhours_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `workers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workhours`
--

LOCK TABLES `workhours` WRITE;
/*!40000 ALTER TABLE `workhours` DISABLE KEYS */;
INSERT INTO `workhours` VALUES (1,'To jest bardzo dlygi address ktory moze sie nie zmiescic','2023-05-20','09:00:00','16:00:00','test comment',1),(2,'test','2023-05-12','02:11:00','10:31:00','test dodawania danych do bazy',1),(3,'test111','2023-01-01','10:22:00','21:17:00','test comment',2),(4,'test','2023-05-25','03:10:00','11:15:00','test',1),(5,'test','2023-05-20','01:10:00','10:29:00','gsdgsdgsd',1),(6,'test','2023-05-27','03:16:00','11:42:00','gsdfgsdafgs',1),(7,'test','2023-05-26','03:15:00','11:16:00','asdasda',1),(8,'test','2023-06-09','09:20:00','11:50:00','gsdfgsdfg',1),(9,'dasdas','2023-05-22','07:00:00','15:00:00','sdfasfas',1),(10,'address','2023-01-01','07:00:00','15:00:00','example comment',1),(11,'test','2023-05-20','02:10:00','09:13:00','asdfgasgas',1),(12,'test','2023-06-09','02:12:00','10:35:00','asdgfasdfa',1),(13,'test','2023-06-15','07:15:00','09:14:00','asdgfaga',1),(14,'test','2023-06-25','03:01:00','09:14:00','asdfasda',1),(15,'test','2023-06-17','02:10:00','10:02:00','dafasdas',1);
/*!40000 ALTER TABLE `workhours` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-20 20:23:11
