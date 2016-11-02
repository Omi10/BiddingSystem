
INSERT INTO user (id, name, email, contact_no) VALUES (1,'om','nirankariom@gmail.com','9826471322');
INSERT INTO user (id, name, email, contact_no) VALUES (2,'rahul','rahulpat@gmail.com','9826473281');
INSERT INTO user (id, name, email, contact_no) VALUES (3,'prerna','prerna@gmail.com','9875647210');
INSERT INTO user (id, name, email, contact_no) VALUES (4,'ritesh','rit@gmail.com','9875647211');
INSERT INTO user (id, name, email, contact_no) VALUES (5,'Om Prakash Nirankari','omprakash@practo.com','9826471322');
INSERT INTO user (id, name, email, contact_no) VALUES (6,'Anoop Singh Rawat','anoop.singh@practo.com',NULL);
	
INSERT INTO category (id, category) VALUES (1,'Electronics');
INSERT INTO category (id, category) VALUES	(2,'Vechiles');
INSERT INTO category (id, category) VALUES	(3,'Home Appliances');
INSERT INTO category (id, category) VALUES	(4,'Others');

INSERT INTO item (id, item, description, image_url, initial_price, start_time, end_time, category_id,owner_id, bid_type, min_balance) VALUES (1,'Samsung J5 Mobile','2015 purchased',NULL,5000,NULL,'2016-11-25 18:37:00',1,1,NULL,NULL);
INSERT INTO item (id, item, description, image_url, initial_price, start_time, end_time, category_id,owner_id, bid_type, min_balance) VALUES (4,'Splender Bike','2014 purchased',NULL,40000,NULL,'2016-11-03 13:00:00',2,5,NULL,NULL);
INSERT INTO item (id, item, description, image_url, initial_price, start_time, end_time, category_id,owner_id, bid_type, min_balance) VALUES (5,'Fridge','2013 purchased',NULL,6000,NULL,'2016-11-04 14:30:00',1,2,NULL,NULL);
INSERT INTO item (id, item, description, image_url, initial_price, start_time, end_time, category_id,owner_id, bid_type, min_balance) VALUES (6,'Pulsar 150 cc','2009 purchased',NULL,20000,NULL,'2016-11-01 14:30:00',2,5,NULL,NULL);
INSERT INTO item (id, item, description, image_url, initial_price, start_time, end_time, category_id,owner_id, bid_type, min_balance) VALUES (31,'Laptop','Dell Inspiron',NULL,14500,NULL,'2016-11-27 03:03:00',1,2,NULL,NULL);
INSERT INTO item (id, item, description, image_url, initial_price, start_time, end_time, category_id,owner_id, bid_type, min_balance) VALUES	(36,'Voltas AC','2 years old',NULL,7000,NULL,'2016-11-27 13:00:00',1,2,NULL,NULL);
INSERT INTO item (id, item, description, image_url, initial_price, start_time, end_time, category_id,owner_id, bid_type, min_balance) VALUES	(64,'Maruti Swift','2014 model',NULL,400000,NULL,'2016-11-26 17:39:00',2,5,NULL,NULL);
INSERT INTO item (id, item, description, image_url, initial_price, start_time, end_time, category_id,owner_id, bid_type, min_balance) VALUES	(70,'Sofa','3+2 Seater',NULL,6000,NULL,'2016-11-01 17:39:00',3,3,NULL,NULL);
INSERT INTO item (id, item, description, image_url, initial_price, start_time, end_time, category_id,owner_id, bid_type, min_balance) VALUES	(84,'Chair','Wooden material',NULL,350,NULL,'2016-11-02 23:59:00',3,1,NULL,NULL);
	
INSERT INTO bid (id, user_id, item_id, is_recent, bid_amount, bid_won, bid_time) VALUES (84,1,70,0,6700,0,'2016-11-01 16:07:47');
INSERT INTO bid (id, user_id, item_id, is_recent, bid_amount, bid_won, bid_time) VALUES (85,5,70,0,6900,0,'2016-11-01 16:09:31');
INSERT INTO bid (id, user_id, item_id, is_recent, bid_amount, bid_won, bid_time) VALUES	(86,1,4,0,45000,0,'2016-11-01 19:18:31');
INSERT INTO bid (id, user_id, item_id, is_recent, bid_amount, bid_won, bid_time) VALUES	(87,5,1,0,5100,0,'2016-11-01 19:23:15');
INSERT INTO bid (id, user_id, item_id, is_recent, bid_amount, bid_won, bid_time) VALUES	(88,6,4,0,48000,0,'2016-11-01 19:35:36');
INSERT INTO bid (id, user_id, item_id, is_recent, bid_amount, bid_won, bid_time) VALUES	(89,6,5,0,6500,0,'2016-11-01 19:36:09');
INSERT INTO bid (id, user_id, item_id, is_recent, bid_amount, bid_won, bid_time) VALUES	(90,6,64,0,410000,0,'2016-11-01 19:37:33');