CREATE DATABASE  IF NOT EXISTS `booksach` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `booksach`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: booksach
-- ------------------------------------------------------
-- Server version	9.0.1

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `district_id` int DEFAULT NULL,
  `is_default` bit(1) DEFAULT NULL,
  `province_id` int DEFAULT NULL,
  `ward_id` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKda8tuywtf0gb6sedwk7la1pgi` (`user_id`),
  CONSTRAINT `FKda8tuywtf0gb6sedwk7la1pgi` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (2,'2025-05-04 14:59:09.657000','2025-05-04 14:59:09.657000',NULL,NULL,_binary '\0',NULL,NULL,11),(3,'2025-05-04 15:02:16.671000','2025-05-04 15:02:16.672000',NULL,NULL,_binary '\0',NULL,NULL,11),(4,'2025-05-04 15:02:16.671000','2025-05-04 15:02:16.672000',NULL,NULL,_binary '\0',NULL,NULL,11),(5,'2025-05-04 15:02:54.998000','2025-05-04 15:02:54.998000',NULL,NULL,_binary '\0',NULL,NULL,11),(6,'2025-05-04 15:05:02.457000','2025-05-04 15:05:02.457000',NULL,NULL,_binary '\0',NULL,NULL,11),(7,'2025-05-04 15:05:14.317000','2025-05-04 15:05:14.317000',NULL,NULL,_binary '\0',NULL,NULL,11),(8,'2025-05-04 15:07:23.181000','2025-05-04 15:07:23.181000',NULL,NULL,_binary '\0',NULL,NULL,11),(9,'2025-05-04 15:13:13.868000','2025-05-04 15:13:13.868000',NULL,NULL,_binary '\0',NULL,NULL,11),(10,'2025-05-04 15:15:45.504000','2025-05-04 15:15:45.504000',NULL,NULL,_binary '\0',NULL,NULL,11),(11,'2025-05-04 15:20:09.527000','2025-05-04 15:39:47.217000','123 Main Street',10,_binary '',1,'A',11),(12,'2025-05-04 15:24:41.461000','2025-05-04 15:39:47.220000','123 Main Street',10,_binary '\0',1,'A',11),(15,'2025-05-24 11:27:50.130000','2025-05-24 13:12:56.446000','aaa, Xã An Hòa, Huyện Châu Thành, Tỉnh An Giang',892,_binary '\0',89,'30592',12),(16,'2025-05-24 11:34:09.520000','2025-05-24 13:12:57.012000','hgfghdf, Xã An Nhứt, Huyện Long Điền, Tỉnh Bà Rịa - Vũng Tàu',752,_binary '\0',77,'26671',12),(17,'2025-05-24 13:02:59.655000','2025-05-24 13:12:57.009000','dfds, Xã Vĩnh Nhuận, Huyện Châu Thành, Tỉnh An Giang',892,_binary '',89,'30619',12);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `author` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `year` int DEFAULT NULL,
  `birth_day` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (41,NULL,NULL,'Nguyễn Nhật Ánh',NULL,'1955-05-07 00:00:00.000000'),(42,NULL,NULL,'Tô Hoài',NULL,'1920-09-22 00:00:00.000000'),(43,NULL,NULL,'Nam Cao',NULL,'1915-10-29 00:00:00.000000'),(44,NULL,NULL,'Bảo Ninh',NULL,'1952-01-01 00:00:00.000000'),(45,NULL,NULL,'Lê Minh Khuê',NULL,'1949-01-01 00:00:00.000000'),(46,NULL,NULL,'Vũ Trọng Phụng',NULL,'1912-09-20 00:00:00.000000'),(47,NULL,NULL,'Phạm Tiến Duật',NULL,'1941-09-14 00:00:00.000000'),(48,NULL,NULL,'Xuân Diệu',NULL,'1916-02-02 00:00:00.000000'),(49,NULL,NULL,'Hồ Xuân Hương',NULL,'1772-01-01 00:00:00.000000'),(50,NULL,NULL,'Nguyễn Du',NULL,'1765-01-01 00:00:00.000000');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `description` text,
  `discount` double DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pages` int DEFAULT NULL,
  `price` double NOT NULL,
  `public_id` varchar(255) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `year` int DEFAULT NULL,
  `publisher_id` bigint DEFAULT NULL,
  `code` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgtvt7p649s4x80y6f4842pnfq` (`publisher_id`),
  KEY `idx_book_name_publisher_id` (`name`,`publisher_id`),
  KEY `idx_book_created_at` (`created_at`),
  CONSTRAINT `FKgtvt7p649s4x80y6f4842pnfq` FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (41,'2025-05-25 02:00:53.084000','2025-05-25 02:00:53.084000','<p>dgdgf</p>',10,'http://res.cloudinary.com/dw9krx7ac/image/upload/bb4a84cc-6f4d-4883-bc9c-63a516f5b1c5_bia-sach2-9886.jpg','Dế mèn phiêu lưu ký',50,1000000,'bb4a84cc-6f4d-4883-bc9c-63a516f5b1c5','A4','de-men-phieu-luu-ky',2000,8,'M001'),(42,'2025-05-25 02:06:30.469000','2025-05-25 02:06:30.469000','',0,'http://res.cloudinary.com/dw9krx7ac/image/upload/ab187adb-86b8-40ed-8a76-6578d87b4a08_ngoi-khoc-tren-cay-1371723695_500x0-bb0af.jpg','Ngồi khóc trên cây',50,100000,'ab187adb-86b8-40ed-8a76-6578d87b4a08_ngoi-khoc-tren-cay-1371723695','A4','ngoi-khoc-tren-cay',2000,8,'M002'),(43,'2025-05-25 02:24:30.720000','2025-05-25 02:24:30.720000','',0,'http://res.cloudinary.com/dw9krx7ac/image/upload/0d6e490c-c481-43be-8fd6-03f3d0d7aee3_OIP%20%281%29.jpg','Gia Định Sài Gòn',50,100000,'0d6e490c-c481-43be-8fd6-03f3d0d7aee3','A4','gia-inh-sai-gon',2000,8,'M003'),(44,'2025-05-25 02:25:19.636000','2025-05-26 06:56:39.250000','',20,'http://res.cloudinary.com/dw9krx7ac/image/upload/92f95b06-fd45-49a9-91b6-c35bc80b9e2c_OIP%20%282%29.jpg','Trại Hoa Vàng',50,150000,'92f95b06-fd45-49a9-91b6-c35bc80b9e2c','A4','trai-hoa-vang',2000,8,'M004'),(45,'2025-05-25 02:28:04.210000','2025-05-25 02:28:04.210000','',0,'http://res.cloudinary.com/dw9krx7ac/image/upload/df7ee755-1070-488f-999f-a3cb9f66179f_OIP%20%283%29.jpg','Sách Địa Lí 10',50,150000,'df7ee755-1070-488f-999f-a3cb9f66179f','A4','sach-ia-li-10',2000,8,'M005'),(46,'2025-05-25 02:34:59.154000','2025-05-25 02:34:59.154000','',0,'http://res.cloudinary.com/dw9krx7ac/image/upload/06cef590-9c16-4bd5-99ce-e10cd2542bcd_OIP%20%284%29.jpg','Tin học 8',50,50000,'06cef590-9c16-4bd5-99ce-e10cd2542bcd','A4','tin-hoc-8',2000,8,'M006'),(47,'2025-05-25 02:37:25.354000','2025-05-25 05:29:10.273000','',40,'http://res.cloudinary.com/dw9krx7ac/image/upload/3bcd6eaa-981e-492b-a506-b1a1b8e27353_tranh-truyen-dan-gian-viet-nam-cay-khe.jpg','Cây Khế',20,20000,'3bcd6eaa-981e-492b-a506-b1a1b8e27353','A','cay-khe',200,9,'MA300'),(48,'2025-05-25 02:38:24.543000','2025-05-26 06:57:01.284000','',50,'http://res.cloudinary.com/dw9krx7ac/image/upload/eca053bc-4db5-45ea-a8b7-adae2374c84a_OIP%20%285%29.jpg','Tieng Anh 12',200,70000,'eca053bc-4db5-45ea-a8b7-adae2374c84a','A4','tieng-anh-12',2023,8,'M007'),(49,'2025-05-25 02:41:20.385000','2025-05-26 06:55:36.985000','',50,'http://res.cloudinary.com/dw9krx7ac/image/upload/01288616-6238-4c60-ad2a-fc9b02198329_OIP%20%286%29.jpg','Thạch Sach',50,20000,'01288616-6238-4c60-ad2a-fc9b02198329','A4','thach-sach',2000,12,'N001'),(50,'2025-05-25 02:42:11.610000','2025-05-26 06:55:24.190000','',17,'http://res.cloudinary.com/dw9krx7ac/image/upload/1193c69e-a8b9-4d05-a7b6-5be020bf295a_OIP%20%287%29.jpg','Cây chuối non đi giày xanh',200,200000,'1193c69e-a8b9-4d05-a7b6-5be020bf295a','A4','cay-chuoi-non-i-giay-xanh',2000,10,'N002'),(51,'2025-05-25 02:43:31.065000','2025-05-26 06:51:59.802000','',10,'http://res.cloudinary.com/dw9krx7ac/image/upload/244594c4-a4c5-426a-a525-9d866a1bfe32_d6fe7a345fae0de4a2d9e86d1a181c62.jpg','Nhà Giả Kim',201,200000,'244594c4-a4c5-426a-a525-9d866a1bfe32','A4','nha-gia-kim',2000,8,'N003'),(52,'2025-05-26 06:54:58.430000','2025-05-26 06:54:58.430000','',5,'http://res.cloudinary.com/dw9krx7ac/image/upload/c8c9b23c-f1fa-4595-85ef-bdfbeae45794_1003w-cptvdrYhx3Y.jpg','Chú chim tí hon',20,30000,'c8c9b23c-f1fa-4595-85ef-bdfbeae45794','A4','chu-chim-ti-hon',2015,18,'N0005');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_authors`
--

DROP TABLE IF EXISTS `book_authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_authors` (
  `book_id` bigint NOT NULL,
  `author_id` bigint NOT NULL,
  PRIMARY KEY (`book_id`,`author_id`),
  KEY `author_id` (`author_id`),
  CONSTRAINT `book_authors_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `book_authors_ibfk_2` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_authors`
--

LOCK TABLES `book_authors` WRITE;
/*!40000 ALTER TABLE `book_authors` DISABLE KEYS */;
INSERT INTO `book_authors` VALUES (44,41),(50,41),(41,42),(52,42),(51,43),(47,44),(48,44),(43,45),(45,45),(49,47);
/*!40000 ALTER TABLE `book_authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_genre`
--

DROP TABLE IF EXISTS `book_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_genre` (
  `book_id` bigint NOT NULL,
  `genre_id` bigint NOT NULL,
  KEY `FK8l6ops8exmjrlr89hmfow4mmo` (`genre_id`),
  KEY `FK52evq6pdc5ypanf41bij5u218` (`book_id`),
  CONSTRAINT `FK52evq6pdc5ypanf41bij5u218` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK8l6ops8exmjrlr89hmfow4mmo` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_genre`
--

LOCK TABLES `book_genre` WRITE;
/*!40000 ALTER TABLE `book_genre` DISABLE KEYS */;
INSERT INTO `book_genre` VALUES (41,39),(43,41),(43,54),(45,52),(46,44),(47,43),(51,46),(51,40),(52,43),(50,39),(49,43),(44,41),(44,43),(48,53),(48,52),(48,44);
/*!40000 ALTER TABLE `book_genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK9emlp6m95v5er2bcqkjsw48he` (`user_id`),
  CONSTRAINT `FKl70asp4l4w0jmbm1tqyofho4o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (21,'2025-05-05 09:41:20.790000','2025-05-05 09:41:20.790000',10),(25,'2025-05-05 09:46:20.950000','2025-05-05 09:46:20.950000',11),(26,'2025-05-05 09:46:38.383000','2025-05-05 09:46:38.383000',9),(27,'2025-05-17 08:21:10.410000','2025-05-17 08:21:10.410000',8),(28,'2025-05-22 08:08:54.057000','2025-05-22 08:08:54.057000',12),(31,'2025-05-25 09:49:20.959000','2025-05-25 09:49:20.959000',23);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_item`
--

DROP TABLE IF EXISTS `cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `book_id` bigint NOT NULL,
  `cart_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKis5hg85qbs5d91etr4mvd4tx6` (`book_id`),
  KEY `FK1uobyhgl1wvgt1jpccia8xxs3` (`cart_id`),
  CONSTRAINT `FK1uobyhgl1wvgt1jpccia8xxs3` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  CONSTRAINT `FKis5hg85qbs5d91etr4mvd4tx6` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_item`
--

LOCK TABLES `cart_item` WRITE;
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
INSERT INTO `cart_item` VALUES (1,'2025-05-05 09:41:20.792000','2025-05-05 10:37:05.633000',45,33,21),(2,'2025-05-05 09:46:20.977000','2025-05-05 09:46:25.254000',3,33,25);
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genre` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_genre_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (39,'2025-05-25 15:45:13.024000',NULL,'Văn học Việt Nam','van-hoc-viet-nam'),(40,'2025-05-25 15:45:13.024000',NULL,'Tiểu thuyết','tieu-thuyet'),(41,'2025-05-25 15:45:13.024000',NULL,'Truyện ngắn','truyen-ngan'),(42,'2025-05-25 15:45:13.024000',NULL,'Thơ ca','tho-ca'),(43,'2025-05-25 15:45:13.024000',NULL,'Sách thiếu nhi','sach-thieu-nhi'),(44,'2025-05-25 15:45:13.024000','2025-05-25 15:45:13.024000','Sách khoa học tiếng việt','sach-khoa-hoc-tieng-viet'),(45,'2025-05-25 15:45:13.024000',NULL,'Kinh tế','kinh-te'),(46,'2025-05-25 15:45:13.024000',NULL,'Tâm lý học','tam-ly-hoc'),(47,'2025-05-25 15:45:13.024000',NULL,'Kỹ năng sống','ky-nang-song'),(49,'2025-05-25 15:45:13.024000',NULL,'Địa lý','dia-ly'),(50,'2025-05-25 15:45:13.024000',NULL,'Triết học','triet-hoc'),(51,'2025-05-25 15:45:13.024000',NULL,'Tôn giáo - Tâm linh','ton-giao-tam-linh'),(52,'2025-05-25 15:45:13.024000',NULL,'Sách giáo khoa','sach-giao-khoa'),(53,'2025-05-25 15:45:13.024000',NULL,'Sách tham khảo','sach-tham-khao'),(54,'2025-05-25 15:45:13.024000',NULL,'Sách nghệ thuật','sach-nghe-thuat'),(55,'2025-05-25 15:45:13.024000',NULL,'Sách kỹ thuật','sach-ky-thuat'),(56,'2025-05-25 15:45:13.024000',NULL,'Sách nấu ăn','sach-nau-an'),(57,'2025-05-25 15:45:13.024000',NULL,'Sách du lịch','sach-du-lich'),(58,'2025-05-25 15:45:13.024000',NULL,'Sách sức khỏe','sach-suc-khoe'),(59,'2025-05-25 15:45:13.024000',NULL,'Sách pháp luật','sach-phap-luat'),(60,'2025-05-25 15:45:13.024000',NULL,'Truyện tranh','truyen-tranh'),(61,'2025-05-25 15:45:13.024000',NULL,'Sách kinh doanh','sach-kinh-doanh'),(64,'2025-05-25 15:40:46.484000','2025-05-25 15:40:46.484000','Lịch sử','lich-su'),(65,'2025-05-25 15:42:34.011000','2025-05-25 15:42:34.011000','Vật lí','vat-li'),(67,'2025-05-25 15:44:27.100000','2025-05-25 15:44:27.100000','sách ngôn ngữ','sach-ngon-ngu'),(68,'2025-05-25 15:45:22.952000','2025-05-25 15:45:22.952000','Toán','toan'),(69,'2025-05-25 15:45:39.686000','2025-05-25 15:45:39.686000','Văn','van'),(74,'2025-05-25 16:06:00.597000','2025-05-25 16:06:00.597000','sách mới','sach-moi'),(75,'2025-05-25 16:08:47.948000','2025-05-25 16:08:47.948000','Toán học','toan-hoc'),(76,'2025-05-25 16:23:30.522000','2025-05-25 16:23:30.522000','thien nhien','thien-nhien');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `quantity` int NOT NULL,
  `book_id` bigint DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `price` double DEFAULT NULL,
  `total_item` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3aceepmpjwpo8pdn7gmjdfckq` (`book_id`),
  KEY `FKrws2q0si6oyd6il8gqe2aennc` (`order_id`),
  CONSTRAINT `FK3aceepmpjwpo8pdn7gmjdfckq` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `FKrws2q0si6oyd6il8gqe2aennc` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (1,'2025-05-25 02:49:23.864000','2025-05-25 02:49:23.864000',1,49,1,20000,20000),(2,'2025-05-25 02:49:23.866000','2025-05-25 02:49:23.866000',1,48,1,63000,63000),(3,'2025-05-25 02:50:46.024000','2025-05-25 02:50:46.024000',1,44,2,135000,135000),(4,'2025-05-25 02:52:14.702000','2025-05-25 02:52:14.702000',1,50,3,200000,200000),(6,'2025-05-26 07:03:03.503000','2025-05-26 07:03:03.503000',1,50,5,166000,166000),(7,'2025-05-26 07:43:07.538000','2025-05-26 07:43:07.538000',1,51,6,180000,180000),(8,'2025-05-26 07:43:07.540000','2025-05-26 07:43:07.540000',1,50,6,166000,166000),(9,'2025-05-26 10:22:43.495000','2025-05-26 10:22:43.495000',1,51,7,180000,180000),(10,'2025-05-31 07:39:58.869000','2025-05-31 07:39:58.869000',1,45,8,150000,150000);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `shipping_fee` double DEFAULT '0',
  `sub_total` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `delivery_address` varchar(255) NOT NULL,
  `delivery_email` varchar(255) NOT NULL,
  `delivery_full_name` varchar(255) NOT NULL,
  `delivery_phone_number` varchar(255) NOT NULL,
  `method` enum('CASH','MOMO','VNPAY') DEFAULT NULL,
  `order_status` enum('AWAITING_CHECK_PAYMENT','PAYMENT_ACCEPTED','READY_TO_SHIP','TRANSIT','PICKUP','DELIVERED') DEFAULT NULL,
  `payment_id` varchar(255) DEFAULT NULL,
  `payment_status` enum('UNPAID','PAID','FAILED') DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `voucher_id` bigint DEFAULT NULL,
  `cart_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKs1sr8a1rkx80gwq9pl0952dar` (`cart_id`),
  KEY `FKel9kyl84ego2otj2accfd8mr7` (`user_id`),
  KEY `FKrx5vk9ur428660yp19hw98nr2` (`voucher_id`),
  CONSTRAINT `FKel9kyl84ego2otj2accfd8mr7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKrx5vk9ur428660yp19hw98nr2` FOREIGN KEY (`voucher_id`) REFERENCES `voucher` (`id`),
  CONSTRAINT `FKtpihbdn6ws0hu56camb0bg2to` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'2025-05-25 02:49:23.855000','2025-05-22 02:49:23.855000',0,30000,83000,113000,'15 - Xã Thái Hòa - Huyện Bình Giang - Tỉnh Hải Dương','staff1@gmail.com','staff1','0342610712','CASH','AWAITING_CHECK_PAYMENT','3e5739de-d8ac-48c3-b3de-3ff63facef94','UNPAID',12,NULL,NULL),(2,'2025-05-25 02:50:46.022000','2025-05-26 10:25:10.170000',0,30000,135000,165000,'124 - Xã Yên Sơn - Huyện Quốc Oai - Thành phố Hà Nội','staff1@gmail.com','staff1','0342610712','VNPAY','DELIVERED','72fe112d-1316-41db-825c-9a49a225ebfb','PAID',12,NULL,NULL),(3,'2025-05-25 02:52:14.700000','2025-05-25 02:52:34.926000',30000,30000,200000,200000,'343 - Xã Hiệp Tùng - Huyện Năm Căn - Tỉnh Cà Mau','staff1@gmail.com','staff1','0342610712','VNPAY','AWAITING_CHECK_PAYMENT','0ed145ba-d022-4780-abe9-b9046e680fd5','FAILED',12,2,NULL),(5,'2025-05-26 07:03:03.491000','2025-05-26 10:53:47.281000',20000,30000,166000,176000,'123 - Xã Tượng Lĩnh - Huyện Kim Bảng - Tỉnh Hà Nam','abcd@gmail.com','Hiến','0342610712','CASH','DELIVERED','e885aeeb-1059-4b17-9d0c-3dcff4afc187','PAID',12,1,NULL),(6,'2025-05-26 07:43:07.532000','2025-05-26 07:58:57.988000',0,30000,346000,376000,'32423 - Xã Phúc Trạch - Huyện Hương Khê - Tỉnh Hà Tĩnh','staff1@gmail.com','staff1','0342610712','VNPAY','AWAITING_CHECK_PAYMENT','932eca69-2823-48e0-85e2-274f673c6477','FAILED',12,NULL,NULL),(7,'2025-05-26 10:22:43.480000','2025-05-26 10:23:20.654000',20000,30000,180000,190000,'124 - Xã Thắng Mố - Huyện Yên Minh - Tỉnh Hà Giang','giahoangdz03@gmail.com','Hoàng','0342610712','VNPAY','AWAITING_CHECK_PAYMENT','b829454d-500a-4885-b300-285ec8e04f0c','FAILED',23,1,NULL),(8,'2025-05-31 07:39:58.829000','2025-05-31 07:40:53.427000',0,30000,150000,180000,'4343 - Xã Phúc Trạch - Huyện Hương Khê - Tỉnh Hà Tĩnh','hien1172004@gmail.com','Hiến','34343','VNPAY','AWAITING_CHECK_PAYMENT','60bb044c-0bfd-4511-939d-9d4d39b8e9c8','PAID',8,NULL,NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publisher`
--

DROP TABLE IF EXISTS `publisher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publisher` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publisher`
--

LOCK TABLES `publisher` WRITE;
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
INSERT INTO `publisher` VALUES (8,NULL,NULL,'NXB Kim Đồng'),(9,NULL,NULL,'NXB Trẻ'),(10,NULL,NULL,'NXB Văn học'),(11,NULL,NULL,'NXB Giáo dục Việt Nam'),(12,NULL,NULL,'NXB Tổng hợp TP.HCM'),(13,NULL,NULL,'NXB Văn hóa - Văn nghệ TP.HCM'),(14,NULL,NULL,'NXB Hội Nhà văn'),(15,NULL,NULL,'NXB Tổng hợp Hà Nội'),(16,NULL,NULL,'NXB Lao Động'),(17,NULL,NULL,'NXB Phụ nữ Việt Nam'),(18,NULL,NULL,'NXB Thế Giới'),(19,NULL,NULL,'NXB Tri Thức'),(20,NULL,NULL,'NXB Dân Trí'),(21,NULL,NULL,'NXB Khoa học và Kỹ thuật'),(22,NULL,'2025-05-25 16:02:56.928000','NXB Đại học Quốc gia Hà Nội2'),(24,'2025-05-25 16:07:31.865000','2025-05-25 16:07:31.865000','NXB Minh Tiến');
/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `book_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `rating` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK70yrt09r4r54tcgkrwbeqenbs` (`book_id`),
  KEY `FKiyf57dy48lyiftdrf7y87rnxi` (`user_id`),
  CONSTRAINT `FK70yrt09r4r54tcgkrwbeqenbs` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `FKiyf57dy48lyiftdrf7y87rnxi` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,'2025-05-25 19:11:55.617000','2025-05-25 19:11:55.617000','sách bổ ích',45,12,5);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_token`
--

DROP TABLE IF EXISTS `tbl_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `access_token` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `refresh_token` varchar(255) DEFAULT NULL,
  `reset_token` varchar(255) DEFAULT NULL,
  `verification_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=163 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_token`
--

LOCK TABLES `tbl_token` WRITE;
/*!40000 ALTER TABLE `tbl_token` DISABLE KEYS */;
INSERT INTO `tbl_token` VALUES (162,'2025-05-26 10:23:37.921000','2025-05-26 10:23:37.921000','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoaWVuMTE3MjAwNEBnbWFpbC5jb20iLCJpYXQiOjE3NDgyNTUwMTcsImV4cCI6MTc0ODM0MTQxN30.jQPC3_kbp-y8pJ2h514dc4lDZjAuY4DV2eTZUduuKUo','hien1172004@gmail.com','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoaWVuMTE3MjAwNEBnbWFpbC5jb20iLCJpYXQiOjE3NDgyNTUwMTcsImV4cCI6MTc0OTQ2NDYxN30.LSD8BBUuKkCzrbQy19JKZT3fd8yhOtmGcPuiplGGRgA',NULL,NULL);
/*!40000 ALTER TABLE `tbl_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tracking`
--

DROP TABLE IF EXISTS `tracking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tracking` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `status` enum('AWAITING_CHECK_PAYMENT','PAYMENT_ACCEPTED','READY_TO_SHIP','TRANSIT','PICKUP','DELIVERED') DEFAULT NULL,
  `time` datetime(6) DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhrymhecl6yb5gofrgnxjxixss` (`user_id`),
  KEY `FKf46j6c8nsgw664aj5apfk46f5` (`order_id`),
  CONSTRAINT `FKf46j6c8nsgw664aj5apfk46f5` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FKhrymhecl6yb5gofrgnxjxixss` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tracking`
--

LOCK TABLES `tracking` WRITE;
/*!40000 ALTER TABLE `tracking` DISABLE KEYS */;
INSERT INTO `tracking` VALUES (1,'2025-05-18 17:23:16.468000','2025-05-18 17:23:16.468000','PAYMENT_ACCEPTED',NULL,39,17),(2,'2025-05-19 17:09:56.544000','2025-05-19 17:09:56.544000','READY_TO_SHIP',NULL,39,17),(3,'2025-05-20 06:04:39.294000','2025-05-20 06:04:39.294000','TRANSIT',NULL,39,17),(4,'2025-05-20 06:04:51.047000','2025-05-20 06:04:51.047000','PICKUP',NULL,39,17),(5,'2025-05-20 06:04:52.662000','2025-05-20 06:04:52.662000','DELIVERED',NULL,39,17),(6,'2025-05-21 18:41:20.847000','2025-05-21 18:41:20.847000','PAYMENT_ACCEPTED',NULL,40,17),(7,'2025-05-24 16:16:15.992000','2025-05-24 16:16:15.992000','PAYMENT_ACCEPTED',NULL,89,12),(8,'2025-05-24 16:16:24.786000','2025-05-24 16:16:24.786000','PAYMENT_ACCEPTED',NULL,87,12),(9,'2025-05-24 16:54:15.539000','2025-05-24 16:54:15.539000','PAYMENT_ACCEPTED',NULL,90,12),(10,'2025-05-24 16:55:54.064000','2025-05-24 16:55:54.064000','PAYMENT_ACCEPTED',NULL,93,12),(11,'2025-05-24 16:56:01.631000','2025-05-24 16:56:01.631000','READY_TO_SHIP',NULL,90,12),(12,'2025-05-25 04:42:25.031000','2025-05-25 04:42:25.031000','PAYMENT_ACCEPTED',NULL,2,12),(13,'2025-05-26 10:24:50.991000','2025-05-26 10:24:50.991000','READY_TO_SHIP',NULL,2,12),(14,'2025-05-26 10:24:52.802000','2025-05-26 10:24:52.802000','TRANSIT',NULL,2,12),(15,'2025-05-26 10:24:57.555000','2025-05-26 10:24:57.555000','PICKUP',NULL,2,12),(16,'2025-05-26 10:25:10.164000','2025-05-26 10:25:10.164000','DELIVERED',NULL,2,12),(17,'2025-05-26 10:53:43.794000','2025-05-26 10:53:43.794000','PAYMENT_ACCEPTED',NULL,5,12),(18,'2025-05-26 10:53:46.507000','2025-05-26 10:53:46.507000','READY_TO_SHIP',NULL,5,12),(19,'2025-05-26 10:53:46.945000','2025-05-26 10:53:46.945000','TRANSIT',NULL,5,12),(20,'2025-05-26 10:53:47.126000','2025-05-26 10:53:47.126000','PICKUP',NULL,5,12),(21,'2025-05-26 10:53:47.276000','2025-05-26 10:53:47.276000','DELIVERED',NULL,5,12);
/*!40000 ALTER TABLE `tracking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `public_id` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `gender` int DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','USER','STAFF') DEFAULT NULL,
  `service` varchar(255) DEFAULT NULL,
  `service_id` varchar(255) DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE','NONE') NOT NULL,
  `cart_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK47dq8urpj337d3o65l3fsjph3` (`cart_id`),
  KEY `idx_user_email` (`email`),
  KEY `idx_user_id` (`id`),
  CONSTRAINT `FKtqa69bib34k2c0jhe7afqsao6` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (8,'2025-05-01 14:57:44.154000','2025-05-26 07:12:56.818000',NULL,'https://lh3.googleusercontent.com/a/ACg8ocLiB3KYkmC0KlT3wu8f6h_eOLqtOrgcEETCNqrSKDL65bLkIA=s96-c',NULL,'hien1172004@gmail.com','Hiến',0,'$2a$10$iyJQMr.0OdEHvQoBmZPyd.MJ6a.LMXeP9iDVnS2yMG7mSAKtZh9Ya',NULL,'ADMIN',NULL,NULL,'ACTIVE',NULL),(9,'2025-05-02 16:31:47.189000','2025-05-02 16:31:47.189000','c876b3b6-21ff-4dbc-a652-cc0a7b045798_5-centimeters-per-second-anime-wallpaper-preview','http://res.cloudinary.com/dw9krx7ac/image/upload/c876b3b6-21ff-4dbc-a652-cc0a7b045798_5-centimeters-per-second-anime-wallpaper-preview_jpg','07/11/2004','abc@gmail.com','abc',1,NULL,'0342111111','USER',NULL,NULL,'ACTIVE',NULL),(10,'2025-05-02 16:35:33.905000','2025-05-02 16:35:33.905000','5067ea72-8d3e-444e-86c9-18dd72693f3e_5-centimeters-per-second-anime-wallpaper-preview','http://res.cloudinary.com/dw9krx7ac/image/upload/5067ea72-8d3e-444e-86c9-18dd72693f3e_5-centimeters-per-second-anime-wallpaper-preview_jpg','07/11/2004','abc1@gmail.com','abc',1,NULL,'0342111111','ADMIN',NULL,NULL,'ACTIVE',NULL),(11,'2025-05-02 16:50:34.220000','2025-05-02 16:50:34.220000','cf673ec0-7d75-4018-ae49-246d38aed9e6_5-centimeters-per-second-anime-wallpaper-preview','http://res.cloudinary.com/dw9krx7ac/image/upload/cf673ec0-7d75-4018-ae49-246d38aed9e6_5-centimeters-per-second-anime-wallpaper-preview_jpg','07/11/2004','abc2@gmail.com','abc',1,NULL,'0342111111','STAFF',NULL,NULL,'ACTIVE',NULL),(12,'2025-05-02 17:13:09.253000','2025-05-24 18:11:16.233000','34c5ea49-0046-4587-bddd-a48d9f2f78c4','http://res.cloudinary.com/dw9krx7ac/image/upload/34c5ea49-0046-4587-bddd-a48d9f2f78c4_fe53105b-36e9-410e-b9dc-e9db7d4b7d6a.jpg','2025-05-06','staff1@gmail.com','staff1',0,'$2a$10$2yylC6BI0We9rJHU6uhotOGAk0WMzVJiRBAzfGys.tfZsEzZ77rXu','0342610712','USER',NULL,NULL,'ACTIVE',NULL),(13,'2025-05-02 17:24:25.009000','2025-05-02 17:24:25.009000','ec7a4659-50ad-43dd-b64e-3e51bbedf294_z4209435453990_29e0d7c8f0c172b85d3f2621e5290b19','http://res.cloudinary.com/dw9krx7ac/image/upload/ec7a4659-50ad-43dd-b64e-3e51bbedf294_z4209435453990_29e0d7c8f0c172b85d3f2621e5290b19_jpg',NULL,'staff2@gmail.com','staff1',0,'$2a$10$dECeVek.C8ZTZuAZrbyTvupGZOTedxrDfBSm/RusKlU7D/8v0INr.',NULL,'STAFF',NULL,NULL,'ACTIVE',NULL),(14,'2025-05-02 17:29:03.117000','2025-05-02 17:29:03.117000','caa80305-9f06-46c1-9837-2bb24b72eb51_z4209435453990_29e0d7c8f0c172b85d3f2621e5290b19','http://res.cloudinary.com/dw9krx7ac/image/upload/caa80305-9f06-46c1-9837-2bb24b72eb51_z4209435453990_29e0d7c8f0c172b85d3f2621e5290b19_jpg',NULL,'staff3@gmail.com','staff1',0,'$2a$10$.nI7Qv0cRMavrHJUt7WDp.A6bBivjYZNA35CqJ5V4zlD3AJElEJQ6',NULL,'STAFF',NULL,NULL,'ACTIVE',NULL),(15,'2025-05-02 17:36:35.746000','2025-05-18 14:34:40.368000','fc84d1db-4495-47f1-86d6-92ba3bc7ed3d_z4209435453990_29e0d7c8f0c172b85d3f2621e5290b19','http://res.cloudinary.com/dw9krx7ac/image/upload/fc84d1db-4495-47f1-86d6-92ba3bc7ed3d_z4209435453990_29e0d7c8f0c172b85d3f2621e5290b19_jpg',NULL,'staff4@gmail.com','staff1',0,'$2a$10$qVxj7nm4fLobTEHhImX/iOUTBXOytjuLFhVnbGuyk6d9gN.hut/.m',NULL,'STAFF',NULL,NULL,'ACTIVE',NULL),(17,'2025-05-04 14:12:48.788000','2025-05-04 14:42:43.489000','174266f8-3c5f-4715-bd38-ae3fe70eb14d','http://res.cloudinary.com/dw9krx7ac/image/upload/174266f8-3c5f-4715-bd38-ae3fe70eb14d_5-centimeters-per-second-anime-wallpaper-preview.jpg','01/01/2004','admin123@gmail.com','admi',1,'$2a$10$Gcw7XzpDD1iUaZMY3z2E7Osqf.peOHyZSxD/vjACWXsZlDrDhzvmq','0322132133','ADMIN',NULL,NULL,'INACTIVE',NULL),(18,'2025-05-10 12:58:34.197000','2025-05-19 12:07:29.119000',NULL,NULL,'11/07/2004','abcd@gmail.com','hien',0,NULL,'0342610712','USER',NULL,NULL,'ACTIVE',NULL),(19,'2025-05-18 14:38:39.801000','2025-05-21 09:50:28.110000',NULL,NULL,NULL,'staff1234@gmail.com','staff5',0,'$2a$10$68/lALa8R7Rr0/IhF7JJS.DyYdpHEt.lyUJFb6.PMXgy8cXSVkxea','123243543','STAFF',NULL,NULL,'ACTIVE',NULL),(20,'2025-05-21 09:54:26.605000','2025-05-25 04:42:59.833000',NULL,NULL,NULL,'tuan@gmail.com','tuan',0,'$2a$10$8zRZFZL7x8rRtx.aspBU1.klduZ35z0fJWw4QKiF/ns1eKsCqaoqa','0322132144','STAFF',NULL,NULL,'INACTIVE',NULL),(23,'2025-05-25 09:49:20.152000','2025-05-25 09:49:20.152000',NULL,'https://lh3.googleusercontent.com/a/ACg8ocLmKVtOvRN15h2ILDwTfsFM7VTC7irs37Vird9r7KtC3Z0kZg=s96-c',NULL,'giahoangdz03@gmail.com','Hoàng',0,NULL,NULL,'USER',NULL,NULL,'ACTIVE',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_voucher`
--

DROP TABLE IF EXISTS `user_voucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_voucher` (
  `user_id` bigint NOT NULL,
  `voucher_id` bigint NOT NULL,
  KEY `FK98wtp768dsh1cjpuiqbnphb7a` (`user_id`),
  KEY `FK5llb4x2ixiwa75csgei7hbl5r` (`voucher_id`),
  CONSTRAINT `FK5llb4x2ixiwa75csgei7hbl5r` FOREIGN KEY (`voucher_id`) REFERENCES `voucher` (`id`),
  CONSTRAINT `FK98wtp768dsh1cjpuiqbnphb7a` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_voucher`
--

LOCK TABLES `user_voucher` WRITE;
/*!40000 ALTER TABLE `user_voucher` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_voucher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voucher`
--

DROP TABLE IF EXISTS `voucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voucher` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `code` varchar(255) NOT NULL,
  `end_date` date DEFAULT NULL,
  `minimum` double DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `value` double DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKpvh1lqheshnjoekevvwla03xn` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voucher`
--

LOCK TABLES `voucher` WRITE;
/*!40000 ALTER TABLE `voucher` DISABLE KEYS */;
INSERT INTO `voucher` VALUES (1,'2025-05-25 02:44:42.245000','2025-05-25 02:44:42.245000','12343','2025-05-29',0,'TET2025','2025-05-19',20000,'amount'),(2,'2025-05-25 02:44:58.057000','2025-05-25 02:44:58.057000','123436','2025-05-29',0,'TET2026','2025-05-19',30000,'amount'),(3,'2025-05-25 02:45:18.704000','2025-05-25 02:45:18.704000','12334','2025-05-29',0,'VLT2024','2025-05-19',10000,'amount'),(5,'2025-05-25 02:45:45.743000','2025-05-25 02:45:45.743000','123345','2025-05-29',0,'VLT2025','2025-05-19',15000,'amount'),(6,'2025-05-25 19:02:35.965000','2025-05-25 19:02:35.965000','12434','2025-05-30',100000,'VN26','2025-05-25',50000,'amount');
/*!40000 ALTER TABLE `voucher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-31 15:22:30
