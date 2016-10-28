


INSERT INTO `user` (`id`, `name`, `email`, `contact_no`)
VALUES
	(1,'om','nirankariom@gmail.com','9826471322'),
	(2,'rahul','rahulpat@gmail.com','9826473281'),
	(3,'prerna','prerna@gmail.com','9875647210'),
	(4,'ritesh','rit@gmail.com','9875647211');
	
INSERT INTO `category` (`id`, `category`)
VALUES
	(1,'Electronics'),
	(2,'Vechiles'),
	(3,'Furniture');

INSERT INTO `item` (`id`, `item`, `description`, `image_url`, `initial_price`, `start_time`, `end_time`, `category_id`, `owner_id`, `bid_type`, `min_balance`)
VALUES
	(1,'Samsung J5 Mobile','2014 purchased',NULL,3000,NULL,'2016-10-25 18:37:43',1,1,0,NULL),
	(2,'Car','2015 purchased',NULL,500000,NULL,'2015-05-12 19:20:50',1,1,0,213),
	(3,'Camera','Nikon',NULL,4509,NULL,'2016-10-03 00:32:00',1,1,0,234),
	(4,'Bike','2014 purchased',NULL,40000,NULL,'2016-10-01 10:30:20',2,3,0,NULL),
	(5,'Fridge','2013 purchased',NULL,6000,NULL,'2016-10-01 14:30:20',1,1,1,500),
	(6,'Bike','2009 purchased',NULL,6888,NULL,'2016-10-01 14:30:20',2,1,0,7000);
	
INSERT INTO `bid` (`id`, `user_id`, `item_id`, `is_recent`, `bid_amount`, `bid_won`, `bid_time`)
VALUES
	(6,1,4,0,23456,0,'2016-10-24 15:56:19'),
	(20,2,1,0,7895,0,'2016-10-23 16:41:34'),
	(27,1,1,0,3456,0,'2016-10-23 18:51:56'),
	(29,1,2,0,3425,0,'2016-10-23 19:01:24'),
	(30,1,2,0,7685,0,'2016-10-23 19:02:07'),