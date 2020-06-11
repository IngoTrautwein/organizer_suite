DROP DATABASE `Organizer_Suite`;
CREATE DATABASE `Organizer_Suite` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE Organizer_Suite;

CREATE TABLE `User` (
  `Id` int(32) NOT NULL,
  `Firstname` varchar(40) NOT NULL,
  `Surname` varchar(40) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Calendar` (
  `Id` int(32) NOT NULL,
  `Name` varchar(40) NOT NULL,
  `User_Id` int(32) NOT NULL,
  PRIMARY KEY (`Id`),
  CONSTRAINT `user_calendar_ibfk_1` FOREIGN KEY (`User_Id`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Appointment` (
  `Id` int(32) NOT NULL,
  `Title` varchar(40) NOT NULL,
  `Date` DATETIME NOT NULL,
  `Calendar_Id` int(6) NOT NULL,
  KEY `Calendar_Id` (`Calendar_Id`),
  CONSTRAINT `calendar_appointment_ibfk_1` FOREIGN KEY (`Calendar_Id`) REFERENCES `Calendar` (`Id`),
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Contact_List` (
  `Id` int(32) NOT NULL,
  `Name` varchar(40) NOT NULL,
  `User_Id` int(32) NOT NULL,
  PRIMARY KEY (`Id`),
  CONSTRAINT `user_contact_list_ibfk_1` FOREIGN KEY (`User_Id`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Contact` (
  `Id` int(32) NOT NULL,
  `Firstname` varchar(40) NOT NULL,
  `Surname` varchar(40) NOT NULL,
  `Address` varchar(40) NOT NULL,
  `Contact_List_Id` int(32) NOT NULL,
  PRIMARY KEY (`Id`),
  CONSTRAINT `contact_contact_list_ibfk_1` FOREIGN KEY (`Contact_List_Id`) REFERENCES `Contact_List` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Chatroom` (
  `Id` int(32) NOT NULL,
  `Topic` varchar(40) NOT NULL,
  `User_Id` int(32) NOT NULL,
  PRIMARY KEY (`Id`),
  CONSTRAINT `user_chatroom_ibfk_1` FOREIGN KEY (`User_Id`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Partipiant` (
  `Chatroom_Id` int(32) NOT NULL,
  `User_Id` int(32) NOT NULL,
  PRIMARY KEY (`Chatroom_Id`,`User_Id`),
  CONSTRAINT `chatroom_partipiant_ibfk_1` FOREIGN KEY (`Chatroom_Id`) REFERENCES `Chatroom` (`Id`),
  CONSTRAINT `user_partipiant_ibfk_1` FOREIGN KEY (`User_Id`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Auflösung der n:m Beziehung zwischen Chatroom und User';

CREATE TABLE `Message` (
  `Id` int(32) NOT NULL,
  `Subject` varchar(40) NOT NULL,
  `Text` varchar(255) NOT NULL,
  `Author` int(32) NOT NULL,
  `Chatroom_Id` int(32) NOT NULL,
  PRIMARY KEY (`Id`),
  CONSTRAINT `message_chatroom_ibfk_1` FOREIGN KEY (`Chatroom_Id`) REFERENCES `Chatroom` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Messaging` (
  `Id` int(32) NOT NULL,
  `Text` varchar(255) NOT NULL,
  `Recipient` int(32) NOT NULL,
  `Sender` int(32) NOT NULL,
  PRIMARY KEY (`Id`),
  CONSTRAINT `messaging_user_ibfk_1` FOREIGN KEY (`Recipient`) REFERENCES `User` (`Id`),
  CONSTRAINT `messaging_user_ibfk_2` FOREIGN KEY (`Sender`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
