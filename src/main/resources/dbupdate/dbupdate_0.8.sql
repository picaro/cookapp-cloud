DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` ( `id` int(11) NOT NULL auto_increment PRIMARY KEY,  `userid` int(11) NOT NULL,  `role_name` varchar(100) NOT NULL,  CONSTRAINT FOREIGN KEY (`userid`) REFERENCES `person` (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=2;
INSERT INTO `user_role` VALUES(1, 2, 'ROLE_ADMIN');
INSERT INTO `user_role` VALUES(2, 1, 'ROLE_USER');
INSERT INTO `user_role` VALUES(3, 3, 'ROLE_USER');