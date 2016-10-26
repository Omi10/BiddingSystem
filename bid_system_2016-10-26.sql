# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.01 (MySQL 5.7.15)
# Database: bid_system
# Generation Time: 2016-10-26 11:47:01 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table bid
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bid`;

CREATE TABLE `bid` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  `is_recent` tinyint(1) NOT NULL,
  `bid_amount` int(11) NOT NULL,
  `bid_won` tinyint(1) DEFAULT NULL,
  `bid_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `item_id` (`item_id`),
  CONSTRAINT `bid_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `bid_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `bid` WRITE;
/*!40000 ALTER TABLE `bid` DISABLE KEYS */;

INSERT INTO `bid` (`id`, `user_id`, `item_id`, `is_recent`, `bid_amount`, `bid_won`, `bid_time`)
VALUES
	(6,1,4,0,23456,0,'2016-10-24 15:56:19'),
	(20,2,1,0,7895,0,'2016-10-23 16:41:34'),
	(27,1,1,0,3456,0,'2016-10-23 18:51:56'),
	(29,1,2,0,3425,0,'2016-10-23 19:01:24'),
	(30,1,2,0,7685,0,'2016-10-23 19:02:07'),
	(31,1,6,0,23,0,'2016-10-23 19:03:03'),
	(39,1,3,0,10,0,'2016-10-23 19:29:31'),
	(40,1,4,0,1234,0,'2016-10-23 19:30:22'),
	(41,1,3,0,234,0,'2016-10-23 21:46:52'),
	(53,1,6,0,12345,0,'2016-10-25 02:41:53'),
	(54,2,3,0,45000,0,'2016-10-02 00:00:00'),
	(60,1,1,0,2345,0,'2016-10-26 04:59:29'),
	(61,1,1,0,0,0,'2016-10-26 05:01:56'),
	(62,1,1,0,2345,0,'2016-10-26 05:08:25'),
	(63,1,2,0,2345,0,'2016-10-26 12:01:48');

/*!40000 ALTER TABLE `bid` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;

INSERT INTO `category` (`id`, `category`)
VALUES
	(1,'Electronics'),
	(2,'Vechiles'),
	(3,'Furniture');

/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table item
# ------------------------------------------------------------

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `initial_price` int(11) NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL,
  `bid_type` tinyint(1) DEFAULT NULL,
  `min_balance` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  KEY `owner_id` (`owner_id`),
  CONSTRAINT `item_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `item_ibfk_2` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;

INSERT INTO `item` (`id`, `item`, `description`, `image_url`, `initial_price`, `start_time`, `end_time`, `category_id`, `owner_id`, `bid_type`, `min_balance`)
VALUES
	(1,'Samsung J5 Mobile','2014 purchased',NULL,3000,NULL,'2016-10-25 18:37:43',1,1,0,NULL),
	(2,'Car','2015 purchased',NULL,500000,NULL,'2015-05-12 19:20:50',1,1,0,213),
	(3,'Camera','Nikon',NULL,4509,NULL,'2016-10-03 00:32:00',1,1,0,234),
	(4,'Bike','2014 purchased',NULL,40000,NULL,'2016-10-01 10:30:20',2,3,0,NULL),
	(5,'Fridge','2013 purchased',NULL,6000,NULL,'2016-10-01 14:30:20',1,1,1,500),
	(6,'Bike','2009 purchased',NULL,6888,NULL,'2016-10-01 14:30:20',2,1,0,7000),
	(31,'Laptop','Dell Inspiron',NULL,14500,NULL,'2016-10-27 03:03:00',NULL,1,NULL,NULL),
	(32,'Kindle','2015 purchased',NULL,5000,NULL,'2016-12-31 12:59:00',NULL,2,NULL,NULL),
	(33,'Splender Bike','2010 purchased',NULL,4502,NULL,'2016-10-13 01:59:00',NULL,1,NULL,NULL),
	(34,'Table','Study table',NULL,600,NULL,'2016-10-18 01:00:00',NULL,1,NULL,NULL);

/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `contact_no` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `name`, `email`, `contact_no`)
VALUES
	(1,'om','nirankariom@gmail.com','9826471322'),
	(2,'rahul','rahulpat@gmail.com','9826473281'),
	(3,'prerna','prerna@gmail.com','9875647210'),
	(4,'ritesh','rit@gmail.com','9875647211');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table wallet
# ------------------------------------------------------------

DROP TABLE IF EXISTS `wallet`;

CREATE TABLE `wallet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `balance` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `wallet_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `wallet` WRITE;
/*!40000 ALTER TABLE `wallet` DISABLE KEYS */;

INSERT INTO `wallet` (`id`, `user_id`, `balance`)
VALUES
	(1,1,500),
	(2,1,500),
	(3,1,500);

/*!40000 ALTER TABLE `wallet` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
