-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: springwebapp
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(1000) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Điện thoại thông minh','https://sc04.alicdn.com/kf/Hacec76210d9a448ca5dd2fd46b9411c0X.jpg','Smartphone'),(2,'Máy tính xách tay','https://cellphones.com.vn/media/catalog/product/l/a/laptop-asus-vivobook-r564ja-uh31t-1.jpg','Laptop'),(3,'Đồng hồ thông minh','https://www.mytrendyphone.eu/images/Haylou-LS02-Waterproof-Smartwatch-with-Heart-Rate-Black-6971664930443-11092020-01-p.jpg','Smartwatch');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  `product_detail_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  KEY `FKr9faxumx2401v6n081g0ju9u` (`product_detail_id`),
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKr9faxumx2401v6n081g0ju9u` FOREIGN KEY (`product_detail_id`) REFERENCES `product_detail` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'https://kddi-h.assetsadobe3.com/is/image/content/dam/au-com/mobile/mb_img_25.jpg','iPhone 13 Pro Max',1,NULL),(2,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Lrx0EpNuFhrbFsL3k0EDdPMHBVYuIWD_FA&usqp=CAU','Samsung Z Fold 3',1,NULL),(3,'https://cdn.hoanghamobile.com/i/productlist/dsp/Uploads/2021/01/15/5g1.png','Galaxy S21 Ultra',1,NULL),(4,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS52omitrDrzrnZ7I_q3Y7Rx08TlzaQCGSEbg&usqp=CAU','Zenbook 14',2,NULL),(5,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSSc0ly51A9nP8s4vUaP8W8mhfViK-uWyMi2Q&usqp=CAU','Galaxy Watch',3,NULL),(6,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR40-z7DLizAwASwVDT2XFXbfPvn4WeLTPrMg&usqp=CAU','Macbook Air M1',2,NULL),(7,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTzlIX8rTFxx1kc8_7LXLeYZSN2gpS3kLD-XQ&usqp=CAU','Watch Fit',3,NULL),(8,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR1bkGUj1NYZA1bNBlm-nSe41unyy8EIgAGoQ&usqp=CAU','Mi Band 6',3,NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_detail`
--

DROP TABLE IF EXISTS `product_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `brand` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_detail`
--

LOCK TABLES `product_detail` WRITE;
/*!40000 ALTER TABLE `product_detail` DISABLE KEYS */;
INSERT INTO `product_detail` VALUES (1,'Apple','Blue',1200,50),(2,'Samsung','Black',1200,23),(3,'Samsung','Black',1000,12),(4,'Asus','Red',900,15),(5,'Samsung','Red',300,50),(6,'Apple','Red',1200,45),(7,'Huawei','White',250,20),(8,'Xiaomi','Black',30,100);
/*!40000 ALTER TABLE `product_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_shop`
--

DROP TABLE IF EXISTS `product_shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_shop` (
  `product_id` bigint NOT NULL,
  `shop_id` bigint NOT NULL,
  KEY `FKh03olttoo3p4wmi3b023pfh2u` (`shop_id`),
  KEY `FK7yop6fyxkpkv0ru4j0cgf2w7j` (`product_id`),
  CONSTRAINT `FK7yop6fyxkpkv0ru4j0cgf2w7j` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKh03olttoo3p4wmi3b023pfh2u` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_shop`
--

LOCK TABLES `product_shop` WRITE;
/*!40000 ALTER TABLE `product_shop` DISABLE KEYS */;
INSERT INTO `product_shop` VALUES (3,2),(2,2),(1,1),(1,2),(4,2),(5,4),(6,2),(6,4),(7,1),(7,4),(8,2),(8,4);
/*!40000 ALTER TABLE `product_shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop`
--

DROP TABLE IF EXISTS `shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(20) DEFAULT NULL,
  `image` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` VALUES (1,'Nguyễn Trãi','https://fpt.ai/sites/default/files/styles/cs_1/public/2019-11/logo-fpt-shop.jpg','FPT Shop','0912345678'),(2,'Thái Hà','https://cdn.tgdd.vn/hoi-dap/651567/y-nghia-logo-cua-the-gioi-di-dong-la-gi2-800x450.jpg','TGDĐ','0988888888'),(4,'Long Biên','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6azptkbGArn0MgASjiZtVh5KHUTw2x7BaDA&usqp=CAU','Điện máy xanh','0968686868');
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-11  1:15:45
