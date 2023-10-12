-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: mxh
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `auction`
--

DROP TABLE IF EXISTS `auction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auction` (
  `auction_id` int NOT NULL AUTO_INCREMENT,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `starting_price` decimal(10,2) DEFAULT NULL,
  `buyout_price` decimal(10,2) DEFAULT NULL,
  `winning_bid` decimal(10,2) DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `winner_user_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`auction_id`),
  KEY `product_id` (`product_id`),
  KEY `winner_user_id` (`winner_user_id`),
  KEY `auction_ibfk_2_idx` (`user_id`),
  CONSTRAINT `auction_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `auction_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `auction_ibfk_3` FOREIGN KEY (`winner_user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auction`
--

LOCK TABLES `auction` WRITE;
/*!40000 ALTER TABLE `auction` DISABLE KEYS */;
INSERT INTO `auction` VALUES (18,'2023-08-30 00:00:00','2023-09-12 00:00:00',34.00,12.00,23.00,18,NULL,NULL),(19,'2023-09-14 00:00:00','2023-09-19 00:00:00',24.00,12.00,34.00,19,NULL,NULL),(20,'2023-09-01 00:00:00','2023-09-12 00:00:00',56.00,12.00,23.00,20,NULL,NULL),(21,'2023-09-02 00:00:00','2023-09-08 00:00:00',12.00,12.00,NULL,21,NULL,NULL),(22,'2023-09-02 00:00:00','2023-09-08 00:00:00',12.00,12.00,NULL,22,NULL,NULL),(23,'2023-09-09 00:00:00','2023-09-11 00:00:00',24.00,12.00,NULL,23,NULL,NULL),(26,NULL,NULL,0.00,12.00,0.00,26,NULL,8);
/*!40000 ALTER TABLE `auction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auctionhistory`
--

DROP TABLE IF EXISTS `auctionhistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auctionhistory` (
  `auction_history_id` int NOT NULL AUTO_INCREMENT,
  `auction_id` int DEFAULT NULL,
  `bidder_id` int DEFAULT NULL,
  `bid_amount` decimal(10,2) DEFAULT NULL,
  `bid_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`auction_history_id`),
  KEY `auction_id` (`auction_id`),
  KEY `bidder_id` (`bidder_id`),
  CONSTRAINT `auctionhistory_ibfk_1` FOREIGN KEY (`auction_id`) REFERENCES `auction` (`auction_id`),
  CONSTRAINT `auctionhistory_ibfk_2` FOREIGN KEY (`bidder_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auctionhistory`
--

LOCK TABLES `auctionhistory` WRITE;
/*!40000 ALTER TABLE `auctionhistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `auctionhistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `create_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `content` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `post_id` int DEFAULT NULL,
  `parent_comment_id` int DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `comments_ibfk_1` (`user_id`),
  KEY `comments_ibfk_2` (`post_id`),
  KEY `comments_ibfk_3` (`parent_comment_id`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`) ON DELETE CASCADE,
  CONSTRAINT `comments_ibfk_3` FOREIGN KEY (`parent_comment_id`) REFERENCES `comments` (`comment_id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,'2023-09-08 07:32:39','hello',8,66,NULL),(19,'2023-09-21 14:26:12','hay',8,73,NULL),(20,'2023-09-30 07:39:19','vjjdvsjffjdsvfjvjds',8,73,NULL),(21,'2023-10-05 15:57:16','sdfdf',8,95,NULL),(22,'2023-10-05 15:57:25','fsdfdsg',8,95,NULL),(23,'2023-10-08 05:38:14','asdasd',8,106,NULL);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hashtags`
--

DROP TABLE IF EXISTS `hashtags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hashtags` (
  `hashtag_id` int NOT NULL AUTO_INCREMENT,
  `hashtag_text` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`hashtag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hashtags`
--

LOCK TABLES `hashtags` WRITE;
/*!40000 ALTER TABLE `hashtags` DISABLE KEYS */;
INSERT INTO `hashtags` VALUES (1,'hay','2023-08-24 11:38:17'),(2,'hai','2023-08-24 11:38:17'),(3,'ba',NULL),(4,'bon',NULL),(5,'hot',NULL),(6,'#hi',NULL),(7,'#zero',NULL),(8,'#hello',NULL),(9,'#dvsdghigf',NULL),(10,'#hihi',NULL),(11,'#xinchao',NULL),(12,'#xinchao',NULL),(13,'#hieuthuhai',NULL),(14,'#test',NULL),(15,'#hehe',NULL),(16,'#sad',NULL),(17,'#djbfjd',NULL),(18,'#hai',NULL),(19,'#YV',NULL),(20,'#avbfgads',NULL),(21,'#n',NULL),(22,'#haha',NULL),(23,'#hdhas',NULL),(24,'#asbdugsduf',NULL),(25,'#yen',NULL),(26,'#dbfg',NULL),(27,'#dasdf',NULL),(28,'#sdfh',NULL),(29,'#fsdf',NULL),(30,'#fgdfug',NULL),(31,'#fsdfgsdg',NULL),(32,'#jj',NULL),(33,'#dfdfg',NULL),(34,NULL,NULL),(35,'#fdhjfg',NULL),(36,'#hay',NULL),(37,'#sdfnsdhfh',NULL),(38,'#sdfgbsgfyus',NULL),(39,'#dfhidhsuifh',NULL),(40,'#fgdgdfg',NULL),(41,'#dg',NULL),(42,'#au',NULL),(43,'#11',NULL),(44,NULL,NULL),(45,NULL,NULL),(46,NULL,NULL),(47,NULL,NULL),(48,NULL,NULL),(49,'#fgnidnug',NULL),(50,NULL,NULL),(51,'#new',NULL),(52,'#dfgdgkfdk',NULL),(53,'#fufu',NULL),(54,NULL,NULL),(55,NULL,NULL),(56,NULL,NULL),(57,NULL,NULL),(58,NULL,NULL),(59,NULL,'2023-09-30 09:02:55'),(60,NULL,'2023-09-30 09:03:00'),(61,'#jshfsdif','2023-09-30 09:03:16'),(62,'#fsdfsdf','2023-10-02 05:54:12'),(63,'#sdjfbhnjisdhgji','2023-10-02 05:56:02'),(64,'#sdfsdf','2023-10-02 05:56:45'),(65,NULL,'2023-10-02 05:58:21'),(66,'#adas','2023-10-02 05:58:31'),(67,'#12','2023-10-02 05:59:06'),(68,'#123','2023-10-02 16:25:04'),(69,'#dfdsf','2023-10-03 15:34:24'),(70,'#a','2023-10-03 15:44:10'),(71,'#h','2023-10-03 15:45:26'),(72,'#sdgas','2023-10-03 16:18:52'),(73,'#fdkjfhk','2023-10-03 16:20:01'),(74,'#kn','2023-10-03 16:23:48'),(75,'#ads','2023-10-03 16:28:04'),(76,NULL,'2023-10-03 16:38:52'),(77,NULL,'2023-10-03 16:43:54'),(78,NULL,'2023-10-03 16:46:59'),(79,NULL,'2023-10-03 16:51:58'),(80,NULL,'2023-10-03 16:53:25'),(81,NULL,'2023-10-03 16:54:56'),(82,'#as','2023-10-05 15:55:55'),(83,'#sdf','2023-10-05 16:08:53'),(84,'#3','2023-10-05 16:09:18'),(85,NULL,'2023-10-05 16:14:08'),(86,'#4','2023-10-05 16:20:31'),(87,NULL,'2023-10-05 16:20:51'),(88,NULL,'2023-10-05 16:22:08'),(89,NULL,'2023-10-05 16:27:05'),(90,'#2','2023-10-05 16:27:48'),(91,'#sdfjhds','2023-10-08 07:51:06');
/*!40000 ALTER TABLE `hashtags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int DEFAULT NULL,
  `post_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `likes_ibfk_2` (`post_id`),
  CONSTRAINT `likes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `likes_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes` VALUES (28,NULL,8,66),(29,NULL,8,66),(30,NULL,8,68),(50,'2023-10-05 15:57:22',8,95),(52,'2023-10-08 05:38:11',8,106);
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifications` (
  `notification_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `action_type` enum('like','comment') DEFAULT NULL,
  `target_id` int DEFAULT NULL,
  `is_read` tinyint(1) DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`notification_id`),
  KEY `user_id` (`user_id`),
  KEY `target_id` (`target_id`),
  CONSTRAINT `notifications_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `notifications_ibfk_2` FOREIGN KEY (`target_id`) REFERENCES `posts` (`post_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (18,8,'like',66,1,'2023-09-08 05:46:56'),(19,8,'like',66,1,'2023-09-08 05:46:56'),(20,8,'like',68,1,'2023-09-08 05:55:42');
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_hashtags`
--

DROP TABLE IF EXISTS `post_hashtags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_hashtags` (
  `post_id` int NOT NULL,
  `hashtag_id` int NOT NULL,
  PRIMARY KEY (`post_id`,`hashtag_id`),
  KEY `hashtag_id` (`hashtag_id`),
  CONSTRAINT `post_hashtags_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`) ON DELETE CASCADE,
  CONSTRAINT `post_hashtags_ibfk_2` FOREIGN KEY (`hashtag_id`) REFERENCES `hashtags` (`hashtag_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_hashtags`
--

LOCK TABLES `post_hashtags` WRITE;
/*!40000 ALTER TABLE `post_hashtags` DISABLE KEYS */;
INSERT INTO `post_hashtags` VALUES (66,51),(67,52),(68,53),(78,62),(82,66),(84,68),(85,69),(86,70),(92,70),(87,71),(91,75),(95,82),(99,83),(100,84),(102,86),(106,90),(107,91);
/*!40000 ALTER TABLE `post_hashtags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts` (
  `post_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `image` varchar(255) DEFAULT NULL,
  `is_locked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`post_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (66,8,'bai moi\r\n','2023-09-08 05:46:50','2023-09-08 05:51:14','https://res.cloudinary.com/dakmujrnc/image/upload/v1694152009/arrwqywg96d9gja61ks6.jpg',0),(67,8,'sdfgdfgdf\r\n','2023-09-08 05:48:21','2023-09-08 05:51:14',NULL,0),(68,8,'new2','2023-09-08 05:55:33',NULL,'https://res.cloudinary.com/dakmujrnc/image/upload/v1694152532/qecedzqbjdvhys1fph8i.jpg',0),(73,8,'xghfdhsfhj','2023-09-09 15:45:40','2023-09-21 08:23:10',NULL,0),(75,8,'dsbfkdsjbjkf','2023-09-30 09:02:55',NULL,'https://res.cloudinary.com/dakmujrnc/image/upload/v1696064574/vm3ednrfxxgpluw0bbdc.png',0),(78,8,'ádasfsdf\r\n','2023-02-02 05:54:12','2023-10-11 04:19:36','https://res.cloudinary.com/dakmujrnc/image/upload/v1696226051/u86gwoquabpbywheemhs.png',0),(81,8,'1323123#ádas','2023-01-02 05:58:20','2023-10-11 04:19:36','https://res.cloudinary.com/dakmujrnc/image/upload/v1696226299/bckbkhkllxywsnl4b1hi.png',0),(82,8,'1323123','2023-03-02 05:58:31','2023-10-11 04:19:36','https://res.cloudinary.com/dakmujrnc/image/upload/v1696226310/uzwuoqvf01krtes68sev.png',0),(84,8,'fghsjdgf123','2023-04-02 16:25:04','2023-10-11 04:19:36','https://res.cloudinary.com/dakmujrnc/image/upload/v1696263903/l7ebmnflmw03fj3ibq0i.png',0),(85,8,'dsad\r\n','2023-05-03 15:34:24','2023-10-11 04:19:36','https://res.cloudinary.com/dakmujrnc/image/upload/v1696347263/grpjzlychzb6aebgoyge.png',0),(86,8,'abab','2023-06-03 15:44:10','2023-10-11 04:19:36','https://res.cloudinary.com/dakmujrnc/image/upload/v1696347850/qbsyj2hgomqgwk4razte.png',0),(87,8,'hihi','2023-07-03 15:45:26','2023-10-11 04:19:36','https://res.cloudinary.com/dakmujrnc/image/upload/v1696347926/qovaqnybqhfivhjcnxai.png',0),(91,8,'sda','2023-08-03 16:28:04','2023-10-11 04:19:36','https://res.cloudinary.com/dakmujrnc/image/upload/v1696350483/gh7h32dfrnap7he60zum.png',0),(92,8,'a','2023-11-03 16:28:36','2023-10-11 04:19:36','https://res.cloudinary.com/dakmujrnc/image/upload/v1696350515/mnu9ypdtwewib6vf0voc.png',0),(93,8,'as@w','2023-11-03 16:38:52','2023-10-11 05:43:52','https://res.cloudinary.com/dakmujrnc/image/upload/v1696351132/a55lin95a1afycnucftg.png',0),(94,8,'sa','2023-10-03 16:43:54',NULL,'https://res.cloudinary.com/dakmujrnc/image/upload/v1696351433/wsmzxxjubsa1jex2za8f.png',0),(95,8,'daaa\r\n\r\n ','2023-10-03 16:46:59','2023-10-05 15:59:19','https://res.cloudinary.com/dakmujrnc/image/upload/v1696521561/ovewvjze9cxbi0nsc367.png',0),(99,8,'asdsa\r\n','2023-10-05 16:08:53',NULL,'https://res.cloudinary.com/dakmujrnc/image/upload/v1696522133/mwrakwcg9mrfqgwbgn7i.png',0),(100,8,'12','2023-10-05 16:09:18',NULL,'https://res.cloudinary.com/dakmujrnc/image/upload/v1696522158/xiorwdjrqkdoezaypiu4.png',0),(101,8,'saa','2023-10-05 16:14:08',NULL,'https://res.cloudinary.com/dakmujrnc/image/upload/v1696522448/rxkv1cecuel6serv4gyk.png',0),(102,8,'12','2023-10-05 16:20:31',NULL,'https://res.cloudinary.com/dakmujrnc/image/upload/v1696522831/ualattgxf2wul4jalb7e.png',0),(103,8,'1','2023-10-05 16:20:51',NULL,'https://res.cloudinary.com/dakmujrnc/image/upload/v1696522851/z6ph8xnq5cagu14rp73h.png',0),(104,8,'w','2023-10-05 16:22:08',NULL,'https://res.cloudinary.com/dakmujrnc/image/upload/v1696522928/m2ka1mpjkm3lzmj4h2gk.png',0),(105,8,'1','2023-10-05 16:27:05',NULL,'https://res.cloudinary.com/dakmujrnc/image/upload/v1696523225/y9dvx4jrqzk1wpumkvrs.png',0),(106,8,'22\r\n ','2023-10-05 16:27:48','2023-10-08 05:37:56','https://res.cloudinary.com/dakmujrnc/image/upload/v1696743479/hbfdp0x1uw13nnbgw3zm.png',0),(107,8,'dfsfdsf','2023-10-08 07:51:06',NULL,'https://res.cloudinary.com/dakmujrnc/image/upload/v1696751466/kgjkc7jnlowltgrfjnuu.png',1);
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `products_ibfk_1_idx` (`category_id`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'scren','fullhd',NULL,NULL),(2,'Iphone7','nice',NULL,NULL),(3,NULL,NULL,NULL,NULL),(4,NULL,NULL,NULL,NULL),(5,NULL,NULL,NULL,NULL),(6,NULL,NULL,NULL,NULL),(7,'tenten','overhop','https://res.cloudinary.com/dakmujrnc/image/upload/v1694062351/b2dcyny6l7topu2nekco.jpg',NULL),(8,'tenten','overhop','https://res.cloudinary.com/dakmujrnc/image/upload/v1694062352/n2dbjdzjoyiwx0f5wl5y.jpg',NULL),(9,'watch','hjhj','https://res.cloudinary.com/dakmujrnc/image/upload/v1694063083/upiskgmq4dstqf3lxrgw.jpg',NULL),(10,'fgdfg','dfgdfg','https://res.cloudinary.com/dakmujrnc/image/upload/v1694063315/dd1x10qcfdt8jxpx1uhv.jpg',NULL),(11,'defer ','dfdsgdfg','https://res.cloudinary.com/dakmujrnc/image/upload/v1694064254/amt4sdtpss4fqzl6zd8y.jpg',NULL),(12,'defer ','dfdsgdfg','https://res.cloudinary.com/dakmujrnc/image/upload/v1694064295/fkkwqmakelbpqfvtwhfb.jpg',NULL),(13,'defer ','dfdsgdfg#vhnfcjvh','https://res.cloudinary.com/dakmujrnc/image/upload/v1694064635/whjoyq9tvydd50453z1v.jpg',NULL),(14,'defer ','dfdsgdfg#vhnfcjvh','https://res.cloudinary.com/dakmujrnc/image/upload/v1694064728/n2qfjykdcj2kpxep6jje.jpg',NULL),(15,'defer ','dfdsgdfg#vhnfcjvh','https://res.cloudinary.com/dakmujrnc/image/upload/v1694066321/bqkiub9aymlxfw1xxgjv.jpg',NULL),(16,'askd,foif','djmgfojg','https://res.cloudinary.com/dakmujrnc/image/upload/v1694066489/hxodo9h9ufxltzi4i6cx.jpg',NULL),(17,'sdfgfdg','asfwerg#fgfdhhndgj','https://res.cloudinary.com/dakmujrnc/image/upload/v1694066649/pevljyfmqi9ch3dizg7l.jpg',NULL),(18,'sp moi','dfsfadfgfdhgfhjgfhj','https://res.cloudinary.com/dakmujrnc/image/upload/v1694152102/nsqrbf7p7zjipzt4yjjf.jpg',NULL),(19,'headphone','dfsdfsdfg','https://res.cloudinary.com/dakmujrnc/image/upload/v1694240006/av1asiiqdzpygmcx2dv5.jpg',NULL),(20,'dfdf','dfsdfvcsxvsdf','https://res.cloudinary.com/dakmujrnc/image/upload/v1694269330/rhyfsodbug5983bdochr.jpg',NULL),(21,'sdgdfg','sdfgsdfg','https://res.cloudinary.com/dakmujrnc/image/upload/v1694273524/xmoutcaekrgqimfrmb8u.jpg',NULL),(22,'sdgdfg','sdfgsdfg','https://res.cloudinary.com/dakmujrnc/image/upload/v1694273524/nnu4zchymd6akywtmh1e.jpg',NULL),(23,'graphic','asdfsedg','https://res.cloudinary.com/dakmujrnc/image/upload/v1694274340/trsye1plrzqvyic8lkxw.jpg',NULL),(24,'sdfsdfs','ghjgfjhgjhg','https://res.cloudinary.com/dakmujrnc/image/upload/v1695443303/jy5bw5wnohrtluioi3pq.png',NULL),(25,'sdfsdfs','ghjgfjhgjhg','https://res.cloudinary.com/dakmujrnc/image/upload/v1695445623/qesox9cgyytrffnggizl.png',NULL),(26,'fbngfchgf','sdfgsdgf','https://res.cloudinary.com/dakmujrnc/image/upload/v1696772668/pqfhdbvogqsckv807ulp.png',NULL);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `report_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `post_id` int DEFAULT NULL,
  `auction_id` int DEFAULT NULL,
  `reason` varchar(50) DEFAULT NULL,
  `report_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`report_id`),
  KEY `report_ibfk_1` (`user_id`),
  KEY `report_ibfk_2` (`auction_id`),
  KEY `report_ibfk_3` (`post_id`),
  CONSTRAINT `report_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `report_ibfk_2` FOREIGN KEY (`auction_id`) REFERENCES `auction` (`auction_id`) ON DELETE CASCADE,
  CONSTRAINT `report_ibfk_3` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (2,8,73,NULL,'sdasdvcch','2023-09-28 04:20:01'),(3,8,NULL,23,'asfsdfds','2023-10-11 08:33:25');
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(50) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (3,'admin','admin@example.com','adminpass','ADMIN','admin-avatar.jpg','2023-08-21 08:16:07'),(7,'usernew','u@new','$2a$10$MR4C6.yCHYNGLdzdhbFVXu29i.A3hLvUhCLXS5YmudECXZe1b9UyO','USER','https://res.cloudinary.com/drhqwhn08/image/upload/v1693581306/feij2vbe7ufwgsubg2we.png','2023-09-01 15:15:05'),(8,'user12','user@12','$2a$10$TIy0bLgLosQQcFxPLMtwSehHt46xCujNKoMxkRyQHNc7mraCbeZai','USER','https://res.cloudinary.com/dakmujrnc/image/upload/v1693730572/evam0z8vepwvb7cfwkjz.jpg','2023-09-03 08:42:51'),(9,'yen','yen@ou','$2a$10$vNb5dneQ1YzmIO91UkYlqOcDrLw7xTUzUy21u4Auj6OMDa8H2RPWi','USER','https://res.cloudinary.com/dakmujrnc/image/upload/v1693840669/r6d6lsr6aqpyakz1ih0l.png','2023-09-04 15:17:50'),(10,'ngocyen','ngoc@yen','$2a$10$YNl7czCWL.H7FaO2k/ysUOTrGqK4uvHFAHH3p0iss/CLbk0GFgv4i','USER','https://res.cloudinary.com/dakmujrnc/image/upload/v1693840733/vmineiotiw35jy1v5nms.png','2023-09-04 15:18:54');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-12 11:46:13
