-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: persons
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(45) NOT NULL,
  `active` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idaddress_UNIQUE` (`id`),
  UNIQUE KEY `address_UNIQUE` (`address`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (3,'Kolejny adres',1),(6,'test',1),(7,'nowy adres',0),(9,'testowy nowy adress',0);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workers`
--

DROP TABLE IF EXISTS `workers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `phoneNumber` int NOT NULL,
  `pesel` bigint NOT NULL,
  `SalaryPerHour` int NOT NULL,
  `admin` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `test_UNIQUE` (`pesel`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workers`
--

LOCK TABLES `workers` WRITE;
/*!40000 ALTER TABLE `workers` DISABLE KEYS */;
INSERT INTO `workers` VALUES (1,'test','test','Bob','Furen',111555999,78787878744,195,0),(2,'loginTest','899b6ea89c20314d457761ec64186c46','Rufus','Stain',111222333,11111122223,100,0),(3,'admin','098f6bcd4621d373cade4e832627b4f6','Admin','Root',999888666,11122233345,195,1),(9,'nowy','ca3d8a2b54354264bcecb78742d52916','nowy','nowy',555999777,55566688847,200,0),(11,'asd','7815696ecbf1c96e6894b779456d330e','ads','asd',257767567,77766655584,44,0);
/*!40000 ALTER TABLE `workers` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workhours`
--

LOCK TABLES `workhours` WRITE;
/*!40000 ALTER TABLE `workhours` DISABLE KEYS */;
INSERT INTO `workhours` VALUES (1,'To jest bardzo dlygi address ktory moze sie nie zmiescic','2023-05-20','09:00:00','16:00:00','test comment',1),(2,'test','2023-05-12','02:11:00','10:31:00','test dodawania danych do bazy',1),(4,'test','2023-05-25','03:10:00','11:15:00','test',1),(5,'test','2023-05-20','01:10:00','10:29:00','gsdgsdgsd',1),(6,'test','2023-05-27','03:16:00','11:42:00','gsdfgsdafgs',1),(7,'test','2023-05-26','03:15:00','11:16:00','asdasda',1),(8,'test','2023-06-09','09:20:00','11:50:00','gsdfgsdfg',1),(9,'dasdas','2023-05-22','07:00:00','15:00:00','sdfasfas',1),(10,'address','2023-01-01','07:00:00','15:00:00','example comment',1),(11,'test','2023-05-20','02:10:00','09:13:00','asdfgasgas',1),(12,'test','2023-06-09','02:12:00','10:35:00','asdgfasdfa',1),(13,'test','2023-06-15','07:15:00','09:14:00','asdgfaga',1),(14,'test','2023-06-25','03:01:00','09:14:00','asdfasda',1),(15,'test','2023-06-17','02:10:00','10:02:00','dafasdas',1),(16,'testowy adres','2023-07-03','01:00:00','04:45:00','test komentarza',1),(17,'Kolejny adres','2023-07-04','01:06:00','11:09:00','test czy zapisuje sie worktime',1),(18,'test','2023-07-04','11:01:00','12:09:00','fgdhfdghdfg',1),(19,'testowy adres','2023-07-05','03:03:00','09:02:00','sdfgfsdgsd',1),(20,'test','2023-07-05','02:03:00','11:08:00','asfasdfasfa',1),(21,'test','2023-07-05','00:01:00','10:13:00','lkjgiljyh',1),(22,'test','2023-07-06','01:09:00','05:11:00',' bngchfg',1),(23,'test','2023-07-06','02:06:00','07:08:00','ostatni test',1),(24,'test','2023-07-07','01:09:00','10:11:00','test #20',1),(25,'Kolejny adres','2023-07-01','03:01:00','10:11:00','test czy pokazuje nowe godziny pracy',1),(26,'test','2023-07-02','00:03:00','09:02:00','test nowego adresu',1),(27,'testowy adres','2023-07-08','03:03:00','10:11:00','asdfasdfas',1),(28,'test','2023-07-01','02:03:00','04:04:00','sfasfassfa',1),(29,'test','2023-07-15','02:03:00','11:22:00','hgjdfghsdfg',1),(30,'Kolejny adres','2023-05-20','02:10:00','10:13:00','tdhdfghdfg',3),(31,'testowy adres','2023-09-17','04:08:00','09:15:00','tgdfgsdgsd',3),(32,'Kolejny adres','2023-09-16','02:02:00','10:14:00','bxbxcbcx',3),(33,'test','2023-09-16','01:20:00','03:11:00','jhjklk',3),(34,'testowy adres','2023-09-16','00:03:00','11:26:00','gjhkgjhkgjhkkjl',3),(35,'test','2023-09-16','11:12:00','12:04:00','hgkllkkl',3),(36,'test','2023-09-16','01:03:00','11:17:00','gjhglkk',3),(37,'Kolejny adres','2023-09-22','02:02:00','04:11:00','jngfhjncjfdg',3),(38,'Kolejny adres','2023-09-23','02:16:00','12:10:00','nfdgncvb',3),(39,'testowy adres','2023-09-23','01:12:00','09:11:00','cgbnxcvnxvnx',3),(40,'test','2023-09-23','03:03:00','11:03:00','',3),(41,'Kolejny adres','2023-09-23','01:00:00','02:00:00','rgsdfgsdfgsd',3),(42,'testowy adres','2023-09-07','01:02:00','07:12:00','asfgasdfa',3),(43,'testowy adres','2023-09-12','02:10:00','02:32:00','test',3),(44,'testowy adres','2023-09-13','01:09:00','11:14:00','fasdfas',3),(45,'test','2023-09-11','01:14:00','10:10:00','fasdfas',3),(46,'testowy adres','2023-09-18','01:08:00','10:13:00','asdfasdfa',3),(48,'testowy adres','2023-09-17','02:09:00','05:15:00','asfasdfa',3),(49,'test','2023-10-14','02:03:00','10:16:00','hgfjfhg',3),(50,'Kolejny adres','2023-10-14','03:16:00','10:12:00','ghjvghb',3),(51,'testowy adres','2023-10-12','03:17:00','11:17:00','hgfjmfhg',3),(52,'testowy adres','2023-10-19','01:12:00','11:14:00','vhjgmvbn',3),(53,'test','2023-10-19','02:13:00','10:11:00','jhkb,ljhgk',3),(54,'testowy adres','2023-10-19','01:11:00','10:16:00','gnmvb',3),(55,'Kolejny adres','2023-10-13','01:03:00','11:13:00','gchcg',3),(56,'test','2023-10-13','01:02:00','03:04:00','vhm',3),(57,'testowy adres','2023-09-29','02:03:00','02:04:00','',3),(58,'test','2023-10-17','01:01:00','09:02:00','gfhjdhfgh',3),(59,'test','2023-10-24','01:05:00','09:12:00','bxcvbx',3),(60,'test','2023-10-19','01:02:00','03:09:00','afas',3),(61,'test','2023-10-13','02:03:00','08:09:00','asf',3),(62,'test','2023-10-04','02:03:00','02:11:00','asfas',3),(63,'test','2023-10-19','01:08:00','07:09:00','ghjgf',3),(64,'test','2023-10-18','01:03:00','10:08:00','das',3),(65,'test','2023-10-17','02:03:00','09:02:00','asds',3),(66,'Kolejny adres','2023-10-17','02:03:00','03:09:00','asdf',3),(67,'test','2023-10-17','01:01:00','03:02:00','asda',3),(68,'test','2023-10-16','02:02:00','10:03:00','jhjlk',3),(69,'test','2023-10-30','01:03:00','02:08:00','asdfa',3),(70,'testowy adres','2023-10-16','03:06:00','07:02:00','asdfa',3),(71,'test','2023-10-24','02:04:00','11:03:00','gsd',3),(72,'test','2023-10-16','01:05:00','08:10:00','xghdf',3),(73,'Kolejny adres','2023-10-24','01:03:00','08:03:00','asdfas',3),(74,'testowy adres','2023-10-04','02:08:00','06:02:00','adafda',3),(75,'test','2023-10-14','01:02:00','07:07:00','asdfas',3),(76,'Kolejny adres','2023-09-29','02:14:00','08:11:00','asdfa',3),(79,'Kolejny adres','2023-09-12','02:10:00','03:13:00','asdas',3),(80,'test','2023-09-07','01:12:00','09:07:00',' n cvbnmvf',3),(81,'test','2023-09-29','02:11:00','09:02:00','fdgh',3),(82,'Kolejny adres','2023-09-29','01:10:00','07:09:00','cxbvx',3),(83,'test','2023-10-18','01:09:00','10:07:00','dsfs',3),(87,'testowy adres','2023-11-10','01:03:00','10:03:00','test z dnia 11/11/23',3),(88,'testowy adres','2023-11-09','01:14:00','10:14:00','test nr2 z dnia 11/11/23',3),(89,'testowy adres','2023-11-10','01:14:00','11:02:00','test nr3 z dnia 11.11.23',3),(90,'test','2023-11-09','02:04:00','04:02:00','test nr 4 11/11/23',3),(91,'testowy adres','2023-11-10','01:12:00','11:12:00','test nr5 11/11/23',3),(92,'testowy adres','2023-11-10','03:15:00','11:11:00','test nr6 11/11/23',3),(93,'testowy adres','2023-11-09','03:02:00','03:11:00','test nr7 11/11/23',3),(94,'testowy adres','2023-11-09','02:04:00','09:13:00','test nr8 11/11/23',3),(95,'testowy adres','2023-11-09','03:12:00','11:11:00','test nr9 11/11/23',3),(96,'testowy adres','2023-11-08','02:14:00','12:03:00','test nr10 11/11/23',3),(97,'testowy adres','2023-11-07','01:02:00','09:11:00','test nr11 11/11/23',3),(98,'testowy adres','2023-11-07','01:11:00','10:11:00','test nr12 11/11/23',3),(101,'test','2023-09-30','02:07:00','10:13:00','Próba zmiany danych',3),(103,'Kolejny adres','2023-09-30','00:02:00','11:09:00','asda',3),(104,'testowy adres','2023-09-30','01:09:00','08:09:00','asdas',3),(106,'test','2023-09-30','02:12:00','10:10:00','test Button ',3),(111,'Kolejny adres','2023-05-05','01:07:00','09:12:00','nowy pracownik',9),(112,'Kolejny adres','2023-05-09','02:12:00','09:13:00','admin test',3),(114,'test','2023-12-16','01:02:00','02:03:00','dsfsdf',3),(115,'test','2023-12-15','02:04:00','10:01:00','fdghdfhd',3),(116,'Kolejny adres','2023-12-08','02:01:00','04:01:00','afsdfs',3),(118,'Kolejny adres','2023-11-30','02:03:00','07:11:00','asdas',3),(119,'Kolejny adres','2023-12-08','01:02:00','02:09:00','sdfgsdfg',3),(120,'test','2023-12-15','01:02:00','10:02:00','asdfasfd',3),(121,'Kolejny adres','2023-12-07','02:03:00','10:03:00','asdfas',3),(122,'Kolejny adres','2023-12-15','01:12:00','06:12:00','sfda',3),(123,'test','2023-12-07','01:02:00','03:03:00','adfa',3),(124,'Kolejny adres','2023-12-05','01:09:00','10:04:00','hfdghfd',3),(125,'test','2023-12-13','01:03:00','10:02:00','asdasd',3),(126,'Kolejny adres','2023-12-02','02:03:00','10:09:00','vbxbcv',3),(128,'test','2023-12-14','00:03:00','03:07:00','fdsafasd',3),(129,'Kolejny adres','2023-12-04','00:03:00','02:03:00','faas',3),(130,'Kolejny adres','2023-12-04','00:01:00','02:03:00','ryehdfg',3),(131,'Kolejny adres','2023-12-06','00:03:00','01:03:00','sdafasdf',3),(132,'Kolejny adres','2023-12-06','01:04:00','08:13:00','sdfa',3),(133,'Kolejny adres','2023-12-21','01:02:00','02:02:00','asdfa',3),(134,'Kolejny adres','2023-12-06','00:02:00','01:03:00','sdfgsdfg',3),(135,'Kolejny adres','2023-11-30','01:03:00','10:10:00','fxvzcxvx',3),(136,'test','2023-11-29','01:02:00','02:10:00','asddas',3),(137,'Kolejny adres','2023-12-07','03:04:00','09:02:00','csdvxz',3),(138,'Kolejny adres','2023-12-08','02:04:00','10:02:00','adfgasdf',3),(139,'test','2023-12-06','02:02:00','08:03:00','afasfas',3),(140,'Kolejny adres','2023-11-30','02:04:00','09:02:00','sdfgsdg',3),(141,'test','2023-11-28','02:03:00','10:03:00','sgfbsdvb',3),(142,'test','2023-12-05','01:04:00','07:06:00','vbzxvx',3),(143,'Kolejny adres','2023-12-05','02:02:00','23:03:00','jggfljhk',3),(145,'Kolejny adres','2023-11-28','01:02:00','11:03:00','zfxbvzcx',3),(146,'Kolejny adres','2023-11-30','01:10:00','12:10:00','fasdfasd',3),(147,'Kolejny adres','2023-12-07','02:03:00','03:01:00','dsfgsdfgsd',3),(148,'Kolejny adres','2023-12-01','00:03:00','02:03:00','asfasf',3),(149,'Kolejny adres','2023-12-04','01:13:00','09:12:00','asdasd',3),(150,'Kolejny adres','2023-12-06','02:12:00','08:02:00','adasd',3),(151,'Kolejny adres','2023-12-07','02:02:00','13:02:00','asdfas',3),(152,'test','2023-12-05','02:02:00','03:01:00','fasdfasdfa',3),(153,'test','2023-11-06','00:03:00','02:03:00','sfddd',3),(154,'test','2023-12-11','03:14:00','14:01:00','gdfgfdg',3),(155,'Kolejny adres','2023-12-25','03:14:00','10:03:00','asdfsdf',3),(156,'Kolejny adres','2023-12-30','03:11:00','12:10:00','adfsasdf',3),(157,'test','2023-12-26','01:04:00','11:03:00','afadsf',3),(158,'test','2023-12-27','02:11:00','09:11:00','asdasd',3),(159,'test','2023-12-01','01:03:00','03:02:00','dhdfgh',3),(160,'test','2023-11-29','02:09:00','11:03:00','zxvzxcv',3),(161,'test','2023-12-04','01:07:00','07:11:00','bxcbvxc',3),(162,'Kolejny adres','2023-12-26','02:11:00','10:16:00','afasdfa',3),(163,'Kolejny adres','2023-12-28','01:03:00','01:11:00','afsdasdfa',3),(164,'Kolejny adres','2023-12-27','03:12:00','12:01:00','asdasdas',3),(165,'Kolejny adres','2023-12-13','01:03:00','09:08:00','asdfasf',3),(166,'nowy adres','2023-12-13','00:02:00','03:02:00','ghfdg',3),(167,'testowy nowy adress','2023-12-13','01:04:00','14:08:00','bvdbx',3);
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

-- Dump completed on 2023-12-19  1:12:27
