CREATE TABLE IF NOT EXISTS `shoplist2shop` (  `id` int(11) NOT NULL AUTO_INCREMENT,  `shopid` int(11) NOT NULL,  `shoplistid` int(11) NOT NULL,  PRIMARY KEY (`id`),  KEY `shopid` (`shopid`),  KEY `shoplistid` (`shoplistid`)) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;
ALTER TABLE `shop` DROP `shoplist`;
ALTER TABLE `shop` ADD column `userid` INT NOT NULL;