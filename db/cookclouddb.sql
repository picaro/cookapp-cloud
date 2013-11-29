-- phpMyAdmin SQL Dump
-- version 3.5.7
-- http://www.phpmyadmin.net
--
-- Хост: localhost
-- Время создания: Ноя 28 2013 г., 13:53
-- Версия сервера: 5.5.32
-- Версия PHP: 5.4.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `cookclouddb`
--

-- --------------------------------------------------------

--
-- Структура таблицы `circle`
--

CREATE TABLE IF NOT EXISTS `circle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `note` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Дамп данных таблицы `circle`
--

INSERT INTO `circle` (`id`, `name`, `note`) VALUES
(1, 'семья', NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `person`
--

CREATE TABLE IF NOT EXISTS `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `gender` varchar(1) NOT NULL,
  `date_registration` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dob` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Дамп данных таблицы `person`
--

INSERT INTO `person` (`id`, `firstname`, `lastname`, `email`, `password`, `phone`, `gender`, `date_registration`, `dob`) VALUES
(1, 'Александр', 'Пастухов', 'picaro.vs@gmail.com', '11111', '+380930299309', 'M', '2013-11-26 18:56:46', '1982-08-14');

-- --------------------------------------------------------

--
-- Структура таблицы `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `shoplistid` int(11) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `shoplistid` (`shoplistid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Структура таблицы `shop`
--

CREATE TABLE IF NOT EXISTS `shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `coordinates` varchar(255) DEFAULT NULL,
  `shoplist` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `shoplist` (`shoplist`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Структура таблицы `shoplist`
--

CREATE TABLE IF NOT EXISTS `shoplist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `circleid` int(11) NOT NULL,
  `date_created` datetime NOT NULL,
  `date_kill` datetime NOT NULL,
  `note` text,
  `userid` int(11) NOT NULL,
  `products` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `circleid` (`circleid`),
  KEY `userid` (`userid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Дамп данных таблицы `shoplist`
--

INSERT INTO `shoplist` (`id`, `circleid`, `date_created`, `date_kill`, `note`, `userid`, `products`) VALUES
(1, 1, '2013-11-25 00:00:00', '2013-11-30 00:00:00', 'список 1', 1, '{}');

-- --------------------------------------------------------

--
-- Структура таблицы `user2circle`
--

CREATE TABLE IF NOT EXISTS `user2circle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `circleid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  KEY `circleid` (`circleid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Дамп данных таблицы `user2circle`
--

INSERT INTO `user2circle` (`id`, `userid`, `circleid`) VALUES
(1, 1, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `usersettings`
--

CREATE TABLE IF NOT EXISTS `usersettings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`shoplistid`) REFERENCES `shoplist` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `shoplist`
--
ALTER TABLE `shoplist`
  ADD CONSTRAINT `shoplist_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `shoplist_ibfk_1` FOREIGN KEY (`circleid`) REFERENCES `circle` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `user2circle`
--
ALTER TABLE `user2circle`
  ADD CONSTRAINT `user2circle_ibfk_2` FOREIGN KEY (`circleid`) REFERENCES `circle` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user2circle_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `usersettings`
--
ALTER TABLE `usersettings`
  ADD CONSTRAINT `usersettings_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
