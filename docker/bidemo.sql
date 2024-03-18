-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: bidemo
-- ------------------------------------------------------
-- Server version	8.3.0

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
-- Table structure for table `datacube`
--

DROP TABLE IF EXISTS `datacube`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datacube` (
  `dimension_one_id` int NOT NULL,
  `dimension_two_id` int NOT NULL,
  `dimension_three_id` int NOT NULL,
  `data` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`dimension_one_id`,`dimension_two_id`,`dimension_three_id`),
  KEY `fk_datacube_dimension_3_idx` (`dimension_three_id`),
  KEY `fk_datacube_dimension_2_idx` (`dimension_two_id`),
  KEY `fk_datacube_dimension_1_idx` (`dimension_one_id`),
  CONSTRAINT `fk_datacube_dimension_1` FOREIGN KEY (`dimension_one_id`) REFERENCES `dimension` (`dimension_id`),
  CONSTRAINT `fk_datacube_dimension_2` FOREIGN KEY (`dimension_two_id`) REFERENCES `dimension` (`dimension_id`),
  CONSTRAINT `fk_datacube_dimension_3` FOREIGN KEY (`dimension_three_id`) REFERENCES `dimension` (`dimension_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datacube`
--

LOCK TABLES `datacube` WRITE;
/*!40000 ALTER TABLE `datacube` DISABLE KEYS */;
INSERT INTO `datacube` VALUES (1,49,68,10000),(1,50,68,5000),(1,51,68,3000),(1,52,68,2000),(1,53,68,1000),(1,54,68,5000),(1,55,68,2000),(1,56,68,1500),(1,57,68,1000),(1,58,68,500),(1,59,68,200),(1,60,68,100),(1,61,68,100),(1,62,68,100),(1,63,68,500),(1,64,68,200),(1,65,68,100),(1,66,68,100),(1,67,68,100),(2,50,69,889),(2,51,69,2089),(2,52,69,342089),(2,53,69,31429),(3,50,69,530),(3,51,69,1530),(3,52,69,68530),(3,53,69,630),(4,50,69,98392),(4,51,69,992),(4,52,69,1292),(4,53,69,100),(5,50,69,233),(5,51,69,1033),(5,52,69,67033),(5,53,69,10383),(6,50,69,487005),(6,51,69,146),(6,52,69,5646),(7,51,69,9838),(7,52,69,5746),(20,49,68,100057680),(20,50,68,5023090),(21,50,69,574),(22,50,68,390);
/*!40000 ALTER TABLE `datacube` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dimension`
--

DROP TABLE IF EXISTS `dimension`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dimension` (
  `dimension_id` int NOT NULL AUTO_INCREMENT,
  `dimension_name` varchar(255) NOT NULL,
  `position` tinyint NOT NULL DEFAULT '0',
  `parent_id` int DEFAULT NULL,
  PRIMARY KEY (`dimension_id`),
  KEY `fk_dimension_parent_1_idx` (`parent_id`),
  CONSTRAINT `fk_dimension_parent_1` FOREIGN KEY (`parent_id`) REFERENCES `dimension` (`dimension_id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dimension`
--

LOCK TABLES `dimension` WRITE;
/*!40000 ALTER TABLE `dimension` DISABLE KEYS */;
INSERT INTO `dimension` VALUES (1,'Geographical',0,NULL),(2,'North America',0,1),(3,'South America',0,1),(4,'Europe',0,1),(5,'United States',0,2),(6,'California',0,5),(7,'Los Angeles',0,6),(8,'San Francisco',0,6),(9,'San Diego',0,6),(10,'Canada',0,2),(11,'Ontario',0,10),(12,'Toronto',0,11),(13,'Ottawa',0,11),(14,'Montreal',0,11),(15,'Mexico',0,2),(16,'Jalisco',0,15),(17,'Guadalajara',0,16),(18,'Puerto Vallarta',0,16),(19,'Cuba',0,2),(20,'Havana',0,19),(21,'Costa Rica',0,2),(22,'San Jose',0,21),(23,'Brazil',0,3),(24,'São Paulo',0,24),(25,'São Paulo',0,25),(26,'Campinas',0,25),(27,'Rio de Janeiro',0,25),(28,'Argentina',0,3),(29,'Buenos Aires',0,29),(30,'Buenos Aires',0,30),(31,'Córdoba',0,29),(32,'Chile',0,3),(33,'Santiago',0,33),(34,'United Kingdom',0,4),(35,'England',0,35),(36,'London',0,36),(37,'Manchester',0,36),(38,'Birmingham',0,36),(39,'France',0,4),(40,'Île-de-France',0,40),(41,'Paris',0,41),(42,'Provence-Alpes-Côte d\'Azur',0,40),(43,'Marseille',0,43),(44,'Germany',0,4),(45,'Bavaria',0,45),(46,'Munich',0,46),(47,'Berlin',0,45),(49,'All Articles',1,NULL),(50,'Bikes',1,49),(51,'Mountain Bikes',1,50),(52,'Road Bikes',1,50),(53,'Hybrid Bikes',1,50),(54,'Accessories',1,49),(55,'Helmets',1,54),(56,'Lights',1,54),(57,'Locks',1,54),(58,'Apparel',1,54),(59,'Jackets',1,58),(60,'Gloves',1,58),(61,'Pants',1,58),(62,'Shirts',1,58),(63,'Components',1,54),(64,'Wheels',1,63),(65,'Pedals',1,63),(66,'Handlebars',1,63),(67,'Saddles',1,63),(68,'Actual',2,NULL),(69,'Units',2,68),(70,'Unit Price',2,68),(71,'Gross Revenue',2,68),(72,'Forecast',2,NULL),(73,'Units',2,72),(74,'Unit Price',2,72),(75,'Gross Revenue',2,72);
/*!40000 ALTER TABLE `dimension` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-18 10:17:34
