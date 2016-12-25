-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: vehicledb
-- ------------------------------------------------------
-- Server version	5.7.15-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'Ali Alhady','alhady.a@husky.neu.edu'),(2,'Harshali Singh','singh.ha@husky.neu.edu'),(3,'Meha Verma','verma.me@husky.neu.edu'),(4,'Yifeng Yan','yan.yif@husky.neu.edu');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `dealer`
--

LOCK TABLES `dealer` WRITE;
/*!40000 ALTER TABLE `dealer` DISABLE KEYS */;
INSERT INTO `dealer` VALUES (1,'Boston Foreign Motor','Allston','523 Cambridge St','02134','42.360226, -71.137967',1,'6172546700',2),(2,'Herb Chambers','Boston','1198 Commonwealth Ave','02134','42.355184, -71.129105',1,'6177310100',1),(3,'Audi Brookline','Brrokline','308 Boylston St','02445','42.335407, -71.125653',1,'6177342020',2),(4,'Broadway Auto Sales','Somerville','150 W Broadway','02127','42.403018, -71.106391',0,'6172013396',2),(5,'Enterprise Car Sales','Westwood','340 Boston Providence Hwy','02090','42.221128, -71.180738',0,'7813264195',3);
/*!40000 ALTER TABLE `dealer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `dealerrating`
--

LOCK TABLES `dealerrating` WRITE;
/*!40000 ALTER TABLE `dealerrating` DISABLE KEYS */;
INSERT INTO `dealerrating` VALUES (1,2,3),(1,3,4),(2,1,4),(4,4,5);
/*!40000 ALTER TABLE `dealerrating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `generaluser`
--

LOCK TABLES `generaluser` WRITE;
/*!40000 ALTER TABLE `generaluser` DISABLE KEYS */;
INSERT INTO `generaluser` VALUES (1),(2),(3),(4),(5);
/*!40000 ALTER TABLE `generaluser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (1,1,5),(1,2,2),(4,5,1),(5,3,2),(6,5,1);
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `manufacturer`
--

LOCK TABLES `manufacturer` WRITE;
/*!40000 ALTER TABLE `manufacturer` DISABLE KEYS */;
INSERT INTO `manufacturer` VALUES (1,'BMW','Woodcliff Lake, NJ'),(2,'Audi','Herndon, VA'),(3,'Honda','Marysville, OH'),(4,'Chevrolet','San Ramon, CA'),(5,'Ford','Detroit, MI'),(6,'Toyota','Plano, TX'),(7,'Nissan','Canton, MS');
/*!40000 ALTER TABLE `manufacturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `prospectivebuyer`
--

LOCK TABLES `prospectivebuyer` WRITE;
/*!40000 ALTER TABLE `prospectivebuyer` DISABLE KEYS */;
INSERT INTO `prospectivebuyer` VALUES (8,'617654321');
/*!40000 ALTER TABLE `prospectivebuyer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Ken Smith','ken.smith@gmail.com',2),(2,'Rick Case','rick.case@gmail.com',1),(3,'Marsha Shields','marsha.shields@gmail.com',1),(4,'Sharon Bloom','sharon.bloom@gmail.com',4),(5,'Travis Holt','travis.holt@gmail.com',2),(6,'Jim Koons','jim.koons@gmail.com',3),(7,'Kurt Jones','kurt.jones@gmail.com',5),(8,'Fred Anderson','fred.anderson@gmail.com',4);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES (1,'BMW 2-Series','Coupe',2015,'Automatic','Full',4,'FWD',1,1),(2,'BMW 3-Series','Sedan',2015,'Automatic','Full',4,'FWD',1,1),(3,'BMW 6-Series','Sedan',2015,'Automatic','Full',4,'RWD',2,1),(4,'Honda Accord','Sedan',2014,'Automatic','Full',4,'FWD',2,3),(5,'Audi A3','Sedan',2015,'Automatic','Full',4,'FWD',2,2),(6,'Honda Civic','Coupe',2013,'Automatic','Full',4,'FWD',2,3),(7,'Chevrolet Camaro','Coupe',2015,'Automatic','Donut',4,'RWD',3,4),(8,'Toyota Highlander','SUV',2014,'Automatic','Full',8,'FWD',2,6);
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-28  0:22:45
