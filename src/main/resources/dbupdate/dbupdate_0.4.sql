
-- Add new column to the table 'person' for authorization

ALTER TABLE person ADD COLUMN enabled TINYINT(1) DEFAULT 1;


-- Insert 2 new records to the table 'person'

INSERT INTO `person` (`id`, `firstname`, `lastname`, `email`, `password`, `phone`, `gender`, `date_registration`, `dob`, `enabled`)
VALUES (2, 'test', 'test', 'test@test.com', '11111', '+380930001234', 'M', '2013-12-21 22:06:46', '1983-10-15', 1);

INSERT INTO `person` (`id`, `firstname`, `lastname`, `email`, `password`, `phone`, `gender`, `date_registration`, `dob`, `enabled`) 
VALUES (3, 'Forever', 'Alone', 'forever.alone@mail.ru', '11111', '+380671234567', 'M', '2013-12-21 22:07:46', '1984-11-16', 1);


-- Create new table 'user_role'

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(11) PRIMARY KEY,
  `role_name` varchar(100) NOT NULL,
  CONSTRAINT FOREIGN KEY (`user_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=2;


-- Insert records to the table 'user_role'

INSERT INTO `user_role` VALUES(2, 'ROLE_ADMIN');
INSERT INTO `user_role` VALUES(1, 'ROLE_USER');
INSERT INTO `user_role` VALUES(3, 'ROLE_USER');

-- Update 'person'. Change open password to md5

UPDATE `person` SET `password` = 'b0baee9d279d34fa1dfd71aadb908c3f';