#
# Created by IntelliJ IDEA.
# User: Neco Yang
# Date: 2018/10/4
# Time: 17:38
# To change this template use File | Settings | File Templates.

DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `ArticleId` int(4) NOT NULL AUTO_INCREMENT,
  `UserId` int(4) NOT NULL,
  `ArticleTitle` varchar(50) NOT NULL,
  `ArticleContent` varchar(1000) NOT NULL,
  `PubTime` datetime DEFAULT NULL,
  `PicPath` varchar(255) NOT NULL,
  PRIMARY KEY (`ArticleId`),
  KEY `fk-Ariticle-User` (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;


INSERT INTO `article` VALUES ('11', '40', 'test2 Article', '<h2 style=\"font-style:italic;\">&nbsp;&nbsp;&nbsp;Article</h2>\r\n', null,'\imgFile\1.jpg');
INSERT INTO `article` VALUES ('12', '39', 'test1 Article', '<h1>Article</h1>\r\n', null,'\imgFile\2.jpg');


DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `CommentId` int(4) NOT NULL AUTO_INCREMENT,
  `ArticleId` int(4) NOT NULL,
  `CommentContent` varchar(200) NOT NULL,
  `UserId` int(4) NOT NULL,
  `UserName` varchar(255) NOT NULL,
  PRIMARY KEY (`CommentId`),
  KEY `fk-Comment-User` (`UserId`),
  KEY `fk-Comment-Aritcle` (`ArticleId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


INSERT INTO `comment` VALUES ('4', '11', 'good', '39', 'test1@google.com');
INSERT INTO `comment` VALUES ('5', '12', 'bad', '40', 'test2@google.com');


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `UserId` int(4) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(50) NOT NULL,
  `UserPasswd` varchar(50) NOT NULL,
  `ProfilePath` varchar(255) NOT NULL,
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;


INSERT INTO `user` VALUES ('39', 'test1@google.com', '123','\imgFile\11.jpg');
INSERT INTO `user` VALUES ('40', 'test2@google.com', '123','\imgFile\12.jpg');
INSERT INTO `user` VALUES ('41', '123@google.com', '123','\imgFile\13.jpg');
INSERT INTO `user` VALUES ('42', '123', '1','\imgFile\14.jpg');
INSERT INTO `user` VALUES ('43', '1', '123','\imgFile\15.jpg');


DROP TABLE IF EXISTS `userinformation`;
CREATE TABLE `userinformation` (
  `InformationId` int(4) NOT NULL AUTO_INCREMENT,
  `UserId` int(4) NOT NULL,
  `NickName` varchar(50) DEFAULT NULL,
  `RealName` varchar(50) DEFAULT NULL,
  `Birthday` int(8) DEFAULT NULL,
  `Country` varchar(50) DEFAULT NULL,
  `PublicInfo` VARCHAR(500) DEFAULT NULL,
  PRIMARY KEY (`InformationId`),
  KEY `fk1` (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;


INSERT INTO `userinformation` VALUES ('9', '39', 'test1','Tom', '19950101', 'NewZealand','test information');
INSERT INTO `userinformation` VALUES ('10', '40', 'test2','Jack', '19680913', 'Australia','test information');
INSERT INTO `userinformation` VALUES ('11', '41', 'test3','Michael', '19760203', 'India','test information');
INSERT INTO `userinformation` VALUES ('12', '42', 'test4','Betty', '20151209', 'China','test information');
INSERT INTO `userinformation` VALUES ('13', '43','test5','Susan', '19851026', 'UnitedStates','test information');

DROP TABLE IF EXISTS `photo`;
CREATE TABLE `photo` (
  `photoId` int(4) NOT NULL AUTO_INCREMENT,
  `UserId` int(4) NOT NULL,
  `photoUrl` VARCHAR(500) DEFAULT NULL,
  PRIMARY KEY (`photoId`),
  KEY `fk1` (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

INSERT INTO `photo` VALUES ('1', '42', '123.jpg');
INSERT INTO `photo` VALUES ('2', '43','234.jpg');