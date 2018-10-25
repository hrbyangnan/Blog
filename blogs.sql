#
# Created by IntelliJ IDEA.
# User: Neco Yang
# Date: 2018/10/4
# Time: 17:38
# To change this template use File | Settings | File Templates.



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

CREATE TABLE `commentOnComment` (
  `CommentId` int(4) NOT NULL AUTO_INCREMENT,
  `FatherCommentId` int(4) NOT NULL,
  `CommentContent` varchar(200) NOT NULL,
  `UserId` int(4) NOT NULL,
  `UserName` varchar(255) NOT NULL,
  PRIMARY KEY (`CommentId`),
  KEY `fk-Comment-User` (`UserId`),
  KEY `fk-Comment-Comment` (`FatherCommentId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


INSERT INTO `commentOnComment` VALUES ('1', '4', 'good', '39', 'test1@google.com');
INSERT INTO `commentOnComment` VALUES ('2', '5', 'bad', '40', 'test2@google.com');


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `UserId` int(4) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(50) NOT NULL,
  `UserPasswd` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL ,
  `ProfilePath` varchar(255) NOT NULL,
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;


INSERT INTO `user` VALUES ('39', 'test1@google.com', '123','123@G.COM','\\imgFile\\11.jpg');
INSERT INTO `user` VALUES ('40', 'test2@google.com', '123','13@G.COM','\\imgFile\\12.jpg');
INSERT INTO `user` VALUES ('41', '123@google.com', '123','125@G.COM','\\imgFile\\13.jpg');
INSERT INTO `user` VALUES ('42', '123', '1','153@G.COM','\\imgFile\\14.jpg');
INSERT INTO `user` VALUES ('43', '1', '123','163@G.COM','\\imgFile\\15.jpg');


DROP TABLE IF EXISTS `userinformation`;
CREATE TABLE `userinformation` (
  `InformationId` int(4) NOT NULL AUTO_INCREMENT,
  `UserId` int(4) NOT NULL,
  `NickName` varchar(50) DEFAULT NULL,
  `RealName` varchar(50) DEFAULT NULL,
  `Birthday` date DEFAULT NULL,
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

DROP TABLE IF EXISTS `articlePhoto`;
CREATE TABLE `articlePhoto` (
  `photoId` int(4) NOT NULL AUTO_INCREMENT,
  `ArticleId` int(4) NOT NULL,
  `photoUrl` VARCHAR(500) DEFAULT NULL,
  PRIMARY KEY (`photoId`),
  KEY `fk1` (`ArticleId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `ArticleId` int(4) NOT NULL AUTO_INCREMENT,
  `UserId` int(4) NOT NULL,
  'RealName' VARCHAR(50),
  `ArticleTitle` varchar(50),
  `ArticleContent` varchar(1000),
  `PubTime` datetime DEFAULT NULL,
  `PicPath` varchar(255),
  PRIMARY KEY (`ArticleId`),
  KEY `fk-Ariticle-User` (`UserId`),
  FOREIGN KEY ('RealName') REFERENCES userInformation ('RealName')
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

INSERT INTO article VALUES ('12', '62','Henry Smith','Hotel de la Paix ', '<p>The Hotel de la Paix is a hotel in Switzerland . Located on the Quai du Mont-Blanc in Geneva , on the shores of Lake Geneva , it has been part of the Concorde Hotels & Resorts group since 2003 . </p>
<p>For more than 200 years, Geneva has been a popular destination for both business and leisure travelers. The hotel luxury Geneva born on the right bank of the lake, lake, the romantic of the xix th  century extol the beauty while the city is experiencing an economic boom. </p>
<p>It is in this context that the Grand Hotel de la Paix was born in 1865 . A time when, despite the Calvinism surrounding, nobody is offended when the Geneva architect Jean - Marie Gignoux decides to give this brand new establishment a style of Italianinspiration rather flamboyant. The lobby of the hotel, in the shape of a square atrium with its faux marble brocatelle columns , is still the main testimony. Heir to the tradition of "big" hotels then in vogue, the Grand Hotel de la Paix is resolutely modern for the time. In 1908 , he was added the neighboring building number 9 of the quai du Mont-Blanc. </p>
<p>The hotel owes its name to a period when peace is a recurring collective concern: Victor Hugo and Alphonse de Lamartine joined the Society of Peace in 1830 and, in 1863 , the International Committee of the Red Cross was born in Geneva. It is also the symbol twice: the Geneva State Council gives a big banquet on September 7 , 1872on the occasion of the end of a post -Civil War conflict between the United States and the United Kingdom over the Alabama vessel , a southern vessel armed by the British that had caused significant damage to trade northerner. In 1898 Sissi is assassinated nearby. In 1954 , the Vietnamese delegation decided to organize a cocktail to celebrate the end of the Indochina war. </p>
', NOW(),'/images/Carte_Printemps.jpg',1);

INSERT INTO article VALUES ('11', '62', 'Henry Smith','Le Calandre', '<p>Le Calandre is a restaurant in the village of Sarmeola di Rubano 6 kilometres (3.7 mi) west of Padua, Italy. </p>
<p>Opened in 1981 by Erminio Alajmo and his wife Rita Chimetto, the restaurant gained its first Michelin star in 1992 with Chimetto as chef de cuisine. Their son, Massimiliano Alajmo, took over the restaurant in 1994, and only two years later in December 1996, the restaurant gained its second star and the then 22-year-old Alajmo became the youngest chef with two Michelin stars. When the restaurant gained its third Michelin star in November 2002, an award only given to "exceptional cuisine with distinctive dishes, precisely executed using superlative ingredients, and worth a special journey", Alajmo at the age of 28 became the youngest three-star Michelin chef of all time. Le Calandre has retained their three Michelin stars ever since, and could celebrate 15 years with three stars in 2017. </p>
<p>restaurant has consistently been voted among The World''s 50 Best Restaurants every year since 2006, and is ranked as number 29 on Restaurant''s 2017 list. Forbes included Le Calandre on their list of "The 16 Coolest Places To Eat In 2016".</p>
<p>Le Calandre has been described as "one of Italy''s most cutting-edge restaurants", that "is consistently judged by major restaurant critics as one of the two or three best restaurants in the country.", and Head Chef Alajmo has earned the nickname "The Mozart of the stoves" (Il Mozart dei fornelli). Food critic Andy Hayler in his review of a 2012 visit to Le Calandre, concluded by saying: "This was a genuinely memorable meal, one of the best I have eaten. This is truly a restaurant at the pinnacle of culinary achievement.", and concluded his review the following year by saying: "Le Calandre is without doubt one of the top restaurants of the world." </p>
<p>In 2010, Le Calandre underwent a major refurbishment, and abandoned the use of white tablecloths for raw wooden tables</p>
', NOW(),'images/Le_Calandre.jpg',1);

INSERT INTO article VALUES ('52', '62', 'Henry Smith','El Celler de Can Roca ', '<p>El Celler de Can Roca is a restaurant in Girona, Catalonia, Spain opened in 1986 by the Roca brothers, Joan, Josep and Jordi. It was first located next to their parents'' restaurant Can Roca, but moved to its current purpose-built building in 2007. It has been received warmly by critics, and holds three Michelin stars.  </p>
<p>The cuisine served by the restaurant is traditional Catalan, but with twists which the Michelin Guide describes as "creative". The restaurant has a wine cellar of 60,000 bottles. Dishes served include those based on perfumes, and with unusual presentations such as caramelised olives served on a bonsai tree. </p>
<p>El Celler de Can Roca was founded in 1986 by the Roca brothers next to their family''s main restaurant Can Roca which had been open on the site since 1967. The oldest brother, Joan Roca is the head chef; Josep Roca, the middle brother, is the sommelier, and the youngest brother, Jordi Roca, is in charge of desserts. </p>
<p>The restaurant primarily uses local ingredients from the Catalan area. Simple flavour combinations are combined with molecular gastronomytechniques and unusual presentations of food, including caramelised olives which are presented on a bonsai tree. The Michelin Guidedescribes the type of cuisine produced by El Celler de Can Roca simply as "creative", while Edward Owen of The Times said it was a "fusion of traditional dishes with surrealist touches". Techniques include the freezing of calamari with liquid nitrogen and then blended in order to be piped and baked into a cracker. </p>
<p>When diners first arrive, they are given a selection of small bites from a section of the kitchen called "El Món", who only produce small snack portions for the guests. They typically demonstrate flavours from around the world and come in sets of five. These introduce the diner to the unusual techniques and presentations of the restaurants. For example, a small ball of frozen spiced fish stock coated in cocoa butter represents Thailand, while the set comes on a purpose-built wooden holder. While there are typical fine dining ingredients including in the menu such as lobster and foie gras, due to the Catalan influence on the menu, ingredients such as pigeon, hake and pig''s trotters also appear. Fish dishes include a crayfish velouté, accompanied by spring onions with cocoa and mint. </p>
', NOW(),'images/Carte_Printemps.jpg',1);

INSERT INTO article VALUES ('67', '62', 'Henry Smith','test2 Article', '<p>Nihonryori RyuGin means “Japanese cuisine;” and RyuGin, meaning “dragon’s voice,” derives from a teaching in Zen Buddhism. </p>
<p>Chef Seiji Yamamoto marries Japan’s kaiseki tradition with a style and sensibility that is both contemporary and highly accessible, even for those visiting Tokyo for the first time.For 11 years, Yamamoto trained under one of Japan’s most revered chefs, Hirohisa Koyama of Aoyagi restaurant. After opening Nihonryori RyuGin in 2003, he gained acclaim for his use of modernist techniques. These days his approach is more traditional but always with an emphasis on premium seafood and produce at the peak of its season. </p>
<p>The menu at Nihonryori RyuGin changes constantly to reflect each new season. Among the highlights are bamboo shoots and wild herbs in spring, sweetfish in summer and the wild mushrooms of fall. Signature dishes include ‘A Message from the Coast of Japan’ and ‘Sanuki Wagyu Beef and Matsutake Mushrooms in Toban Sukiyaki.’</p>
<p> Appetiser of turtle soup with a poached egg and notionally a rice cake, though this was hard to detect. The eighteenth century London Tavern in Bishopsgate used to keep live turtles in their huge cellars, alongside their huge wine collection, for use in their signature turtle soup.The egg used in the soup here has an impressively deep orange yolk and good flavour, so this is certainly interesting. </p>
', NOW(),'images/RyuGinTokyo.jpg',1);

INSERT INTO `article` VALUES ('11', '40', 'test2 Article', '<h2 style=\"font-style:italic;\">&nbsp;&nbsp;&nbsp;Article</h2>\r\n', null,'\imgFile\1.jpg');
INSERT INTO `article` VALUES ('12', '39', 'test1 Article', '<h1>Article</h1>\r\n', null,'\imgFile\2.jpg');
INSERT INTO `article` VALUES ('52', '41', 'test2 Article', '<h2 style=\"font-style:italic;\">&nbsp;&nbsp;&nbsp;Article</h2>\r\n', null,'\imgFile\1.jpg');

DROP TABLE IF EXISTS comment;
CREATE TABLE comment (
  CommentId int(4) NOT NULL AUTO_INCREMENT,
  ArticleId int(4) NOT NULL,
  CommentContent varchar(200) NOT NULL,
  UserId int(4) NOT NULL,
  UserName varchar(255) NOT NULL,
  PRIMARY KEY (CommentId),
  KEY fk_Comment_User (UserId),
  KEY fk_Comment_Aritcle (ArticleId)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

INSERT INTO aricle(UserId,ArticleTitle,ArticleContent)  VALUES(39,"title","content");
INSERT INTO aricle(UserId,ArticleTitle,ArticleContent)  VALUES(40,"title","content");

INSERT INTO comment VALUES ('4', '11', 'good', '39', 'test1@google.com');
INSERT INTO comment VALUES ('5', '12', 'bad', '40', 'test2@google.com');

INSERT INTO article (UserId,ArticleTitle,ArticleContent,RealName)VALUES(42,'This is a title','I am putting some text in here something something something it is a string, will this even work','Steve Holt');

SELECT * FROM article WHERE ArticleId=53;

SELECT * FROM user JOIN userinformation WHERE UserName='alias' AND NickName ='alias';

SELECT * FROM article WHERE UserId=49;

SELECT * FROM user JOIN userinformation WHERE UserName='Alias' AND NickName ='Alias';

ALTER TABLE user  ADD plaintext VARCHAR(200);

CREATE TABLE `admin` (
  `AdminId` int(4) NOT NULL AUTO_INCREMENT,
  `AdminName` varchar(50) NOT NULL,
  `AdminPasswd` varchar(50) NOT NULL,
 PRIMARY KEY (`AdminId`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

INSERT INTO admin(AdminId,AdminName,AdminPasswd)  VALUES(1,"admin","admin");

alter table user drop column plaintext;

UPDATE user set UserPasswd='444'  WHERE UserName='Neco';

SELECT * FROM article WHERE ArticleId=67;

UPDATE article set PubTime=NOW() WHERE ArticleId = 67;

UPDATE user SET UserName='123',Email='123@q.com',ProfilePath='123' WHERE UserId=54;
UPDATE userinformation SET NickName='123', RealName='Jimmy AS',Birthday='1991-01-02', Country='11', PublicInfo='111' WHERE UserId=54;
UPDATE article SET RealName="test" WHERE UserId=61;

/*ALTER table article add CONSTRAINT Real*/

ALTER TABLE article
  ADD CONSTRAINT RealName
FOREIGN KEY (RealName)
REFERENCES userinformation(RealName)
  ON UPDATE CASCADE;

DROP TABLE IF EXISTS multimedia;
CREATE TABLE multimedia(
  mediaId int(4) NOT NULL AUTO_INCREMENT,
  ArticleId int(4) NOT NULL,
  mediaURL VARCHAR(500),
  PRIMARY KEY(mediaId),
  FOREIGN KEY (ArticleId) REFERENCES article(ArticleId));


/*DROP TABLE IF EXISTS `articlePhoto`;
CREATE TABLE `articlePhoto` (
  `photoId` int(4) NOT NULL AUTO_INCREMENT,
  `ArticleId` int(4) NOT NULL,
  `photoUrl` VARCHAR(500) DEFAULT NULL,
  PRIMARY KEY (`photoId`),
  KEY `fk1` (`ArticleId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `article`;*/


SELECT * FROM article WHERE RealName like '%Henry%' or ArticleTitle like '%Henry%' or PubTime like '%Henry%';

select ProfilePath from user WHERE UserId=62;

SELECT * FROM article WHERE ArticleId=12;