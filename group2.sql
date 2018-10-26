

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `ArticleId` int(4) NOT NULL AUTO_INCREMENT,
  `UserId` int(4) NOT NULL,
  `RealName` varchar(50) DEFAULT NULL,
  `ArticleTitle` varchar(50) DEFAULT NULL,
  `ArticleContent` varchar(10000) DEFAULT NULL,
  `PubTime` datetime DEFAULT NULL,
  `PicPath` varchar(255) DEFAULT NULL,
  `visible` int(1) DEFAULT '1',
  PRIMARY KEY (`ArticleId`),
  KEY `UserId` (`UserId`),
  KEY `RealName` (`RealName`),
  CONSTRAINT `article_ibfk_1` FOREIGN KEY (`UserId`) REFERENCES `user` (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=286 DEFAULT CHARSET=utf8;


INSERT INTO `article` VALUES ('11', '62', 'Henry', 'Le Calandre: in the village of Sarmeola di Rubano', '<p>Le Calandre is a restaurant in the village of Sarmeola di Rubano 6 kilometres (3.7 mi) west of Padua, Italy. </p>\r\n<p>Opened in 1981 by Erminio Alajmo and his wife Rita Chimetto, the restaurant gained its first Michelin star in 1992 with Chimetto as chef de cuisine. Their son, Massimiliano Alajmo, took over the restaurant in 1994, and only two years later in December 1996, the restaurant gained its second star and the then 22-year-old Alajmo became the youngest chef with two Michelin stars. When the restaurant gained its third Michelin star in November 2002, an award only given to \"exceptional cuisine with distinctive dishes, precisely executed using superlative ingredients, and worth a special journey\", Alajmo at the age of 28 became the youngest three-star Michelin chef of all time. Le Calandre has retained their three Michelin stars ever since, and could celebrate 15 years with three stars in 2017. </p>\r\n<p>restaurant has consistently been voted among The World\'s 50 Best Restaurants every year since 2006, and is ranked as number 29 on Restaurant\'s 2017 list. Forbes included Le Calandre on their list of \"The 16 Coolest Places To Eat In 2016\".</p>\r\n<p>Le Calandre has been described as \"one of Italy\'s most cutting-edge restaurants\", that \"is consistently judged by major restaurant critics as one of the two or three best restaurants in the country.\", and Head Chef Alajmo has earned the nickname \"The Mozart of the stoves\" (Il Mozart dei fornelli). Food critic Andy Hayler in his review of a 2012 visit to Le Calandre, concluded by saying: \"This was a genuinely memorable meal, one of the best I have eaten. This is truly a restaurant at the pinnacle of culinary achievement.\", and concluded his review the following year by saying: \"Le Calandre is without doubt one of the top restaurants of the world.\" </p>\r\n<p>In 2010, Le Calandre underwent a major refurbishment, and abandoned the use of white tablecloths for raw wooden tables</p>\r\n', '2018-10-22 02:17:54', 'images/Le_Calandre.jpg', '1');
INSERT INTO `article` VALUES ('12', '62', 'Henry', 'Hotel de la Paix', '<p>The Hotel de la Paix is a hotel in Switzerland . Located on the Quai du Mont-Blanc in Geneva , on the shores of Lake Geneva , it has been part of the Concorde Hotels & Resorts group since 2003 . </p>\r\n<p>For more than 200 years, Geneva has been a popular destination for both business and leisure travelers. The hotel luxury Geneva born on the right bank of the lake, lake, the romantic of the xix th  century extol the beauty while the city is experiencing an economic boom. </p>\r\n<p>It is in this context that the Grand Hotel de la Paix was born in 1865 . A time when, despite the Calvinism surrounding, nobody is offended when the Geneva architect Jean - Marie Gignoux decides to give this brand new establishment a style of Italianinspiration rather flamboyant. The lobby of the hotel, in the shape of a square atrium with its faux marble brocatelle columns , is still the main testimony. Heir to the tradition of \"big\" hotels then in vogue, the Grand Hotel de la Paix is resolutely modern for the time. In 1908 , he was added the neighboring building number 9 of the quai du Mont-Blanc. </p>\r\n<p>The hotel owes its name to a period when peace is a recurring collective concern: Victor Hugo and Alphonse de Lamartine joined the Society of Peace in 1830 and, in 1863 , the International Committee of the Red Cross was born in Geneva. It is also the symbol twice: the Geneva State Council gives a big banquet on September 7 , 1872on the occasion of the end of a post -Civil War conflict between the United States and the United Kingdom over the Alabama vessel , a southern vessel armed by the British that had caused significant damage to trade northerner. In 1898 Sissi is assassinated nearby. In 1954 , the Vietnamese delegation decided to organize a cocktail to celebrate the end of the Indochina war. </p>\r\n', '2018-10-22 02:18:17', '/images/Carte_Printemps.jpg', '1');
INSERT INTO `article` VALUES ('52', '62', 'Henry', 'El Celler de Can Roca: Traditional Catalan', '<p>El Celler de Can Roca is a restaurant in Girona, Catalonia, Spain opened in 1986 by the Roca brothers, Joan, Josep and Jordi. It was first located next to their parents\' restaurant Can Roca, but moved to its current purpose-built building in 2007. It has been received warmly by critics, and holds three Michelin stars.  </p>\r\n<p>The cuisine served by the restaurant is traditional Catalan, but with twists which the Michelin Guide describes as \"creative\". The restaurant has a wine cellar of 60,000 bottles. Dishes served include those based on perfumes, and with unusual presentations such as caramelised olives served on a bonsai tree. </p>\r\n<p>El Celler de Can Roca was founded in 1986 by the Roca brothers next to their family\'s main restaurant Can Roca which had been open on the site since 1967. The oldest brother, Joan Roca is the head chef; Josep Roca, the middle brother, is the sommelier, and the youngest brother, Jordi Roca, is in charge of desserts. </p>\r\n<p>The restaurant primarily uses local ingredients from the Catalan area. Simple flavour combinations are combined with molecular gastronomytechniques and unusual presentations of food, including caramelised olives which are presented on a bonsai tree. The Michelin Guidedescribes the type of cuisine produced by El Celler de Can Roca simply as \"creative\", while Edward Owen of The Times said it was a \"fusion of traditional dishes with surrealist touches\". Techniques include the freezing of calamari with liquid nitrogen and then blended in order to be piped and baked into a cracker. </p>\r\n<p>When diners first arrive, they are given a selection of small bites from a section of the kitchen called \"El Món\", who only produce small snack portions for the guests. They typically demonstrate flavours from around the world and come in sets of five. These introduce the diner to the unusual techniques and presentations of the restaurants. For example, a small ball of frozen spiced fish stock coated in cocoa butter represents Thailand, while the set comes on a purpose-built wooden holder. While there are typical fine dining ingredients including in the menu such as lobster and foie gras, due to the Catalan influence on the menu, ingredients such as pigeon, hake and pig\'s trotters also appear. Fish dishes include a crayfish velouté, accompanied by spring onions with cocoa and mint. </p>\r\n', '2018-10-22 02:18:28', 'images/olive.jpg', '1');
INSERT INTO `article` VALUES ('67', '62', 'Henry', 'Nihonryori RyuGin', '<p>Nihonryori RyuGin means “Japanese cuisine;” and RyuGin, meaning “dragon’s voice,” derives from a teaching in Zen Buddhism. </p>\r\n<p>Chef Seiji Yamamoto marries Japan’s kaiseki tradition with a style and sensibility that is both contemporary and highly accessible, even for those visiting Tokyo for the first time.For 11 years, Yamamoto trained under one of Japan’s most revered chefs, Hirohisa Koyama of Aoyagi restaurant. After opening Nihonryori RyuGin in 2003, he gained acclaim for his use of modernist techniques. These days his approach is more traditional but always with an emphasis on premium seafood and produce at the peak of its season. </p>\r\n<p>The menu at Nihonryori RyuGin changes constantly to reflect each new season. Among the highlights are bamboo shoots and wild herbs in spring, sweetfish in summer and the wild mushrooms of fall. Signature dishes include ‘A Message from the Coast of Japan’ and ‘Sanuki Wagyu Beef and Matsutake Mushrooms in Toban Sukiyaki.’</p>\r\n<p> Appetiser of turtle soup with a poached egg and notionally a rice cake, though this was hard to detect. The eighteenth century London Tavern in Bishopsgate used to keep live turtles in their huge cellars, alongside their huge wine collection, for use in their signature turtle soup.The egg used in the soup here has an impressively deep orange yolk and good flavour, so this is certainly interesting. </p>\r\n', '2018-10-22 02:18:38', 'images/RyuGinTokyo.jpg', '1');
INSERT INTO `article` VALUES ('217', '106', 'Tyne Crow', 'SDFG  Vandalised', '<p>SDFHGSD FSDFG SFDG James was here</p>', '2018-10-23 00:00:00', '/Uploaded_Photos/6882_Rangitoto.jpg', '0');
INSERT INTO `article` VALUES ('269', '116', 'Thomas Molloy', 'This is a post from Tom', '<p>The hopfully one and only</p>', '2018-10-24 00:00:00', '', '1');
INSERT INTO `article` VALUES ('275', '120', 'Liza Martynova', 'Russian caviar', '<p>Hi guys! Have you ever tried Russian caviar?</p>', '2018-10-25 00:00:00', '/Uploaded_Photos/5533_download.jpg', '1');
INSERT INTO `article` VALUES ('276', '62', 'Henry', 'New article with a deer', '<p style=\"text-align: center;\"><strong>Deer is here!</strong></p>', '2018-10-25 00:00:00', '/Uploaded_Photos/6928_download.jpg', '1');
INSERT INTO `article` VALUES ('278', '62', 'Henry', 'thistitle2', '', '2018-10-25 00:00:00', '/Uploaded_Photos/2788_download.jpg', '1');
INSERT INTO `article` VALUES ('283', '113', 'Lucas Moriarty', 'test wav', '<p>wav test, hopefully this is fine</p>', '2018-10-26 00:00:00', '/Uploaded_Photos/5534_couchplayin.wav', '1');
INSERT INTO `article` VALUES ('284', '113', 'Lucas Moriarty', 'Poutine in Quebec', '<p>Poutine was invented in Montreal sometime in the 1980s. An idea so simple and yet genius. It has since spread to the rest of Canada and then the rest of the world.</p>\r\n<p>The original poutine is french fries and cheese curd smothered with gravy, but variations quickly proliferated.</p>', '2018-10-26 00:00:00', '', '1');
INSERT INTO `article` VALUES ('285', '113', 'Lucas Moriarty', 'asdf', '<p>asdf</p>', '2018-10-26 00:00:00', '/Uploaded_Photos/8902_Owhanake Bay Waiheke Island.jpg', '1');


DROP TABLE IF EXISTS `articlePhoto`;
CREATE TABLE `articlePhoto` (
  `photoId` int(4) NOT NULL AUTO_INCREMENT,
  `ArticleId` int(4) NOT NULL,
  `photoUrl` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`photoId`),
  KEY `fk1` (`ArticleId`)
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8;


INSERT INTO `articlePhoto` VALUES ('1', '1', '\\articlePhoto\\1.jpg');
INSERT INTO `articlePhoto` VALUES ('2', '2', '\\articlePhoto\\2.jpg');
INSERT INTO `articlePhoto` VALUES ('3', '3', '\\articlePhoto\\3.jpg');
INSERT INTO `articlePhoto` VALUES ('4', '4', '\\articlePhoto\\4.jpg');
INSERT INTO `articlePhoto` VALUES ('145', '285', '/Uploaded_Photos/8902_gulfIslands-22.jpg');
INSERT INTO `articlePhoto` VALUES ('146', '285', '/Uploaded_Photos/8902_Owhanake Bay Waiheke Island.jpg');


DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `CommentId` int(4) NOT NULL AUTO_INCREMENT,
  `ArticleId` int(4) NOT NULL,
  `CommentContent` varchar(200) NOT NULL,
  `UserId` int(4) NOT NULL,
  `UserName` varchar(255) NOT NULL,
  `visible` int(1) DEFAULT '1',
  PRIMARY KEY (`CommentId`),
  KEY `fk-Comment-User` (`UserId`),
  KEY `fk-Comment-Aritcle` (`ArticleId`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;


INSERT INTO `comment` VALUES ('82', '11', 'check if i', '62', 'Henry', '1');
INSERT INTO `comment` VALUES ('83', '11', 'hifhifh', '62', 'Henry', '0');
INSERT INTO `comment` VALUES ('84', '11', 'fchfhc', '62', 'Henry', '1');
INSERT INTO `comment` VALUES ('85', '11', 'dfhchchn', '62', 'Henry', '1');
INSERT INTO `comment` VALUES ('86', '11', 'cfhcgggjn', '62', 'Henry', '1');
INSERT INTO `comment` VALUES ('87', '11', 'kjgkugj', '62', 'Henry', '1');


DROP TABLE IF EXISTS `commentInComment`;
CREATE TABLE `commentInComment` (
  `CommentId` int(4) NOT NULL AUTO_INCREMENT,
  `FatherCommentId` int(4) NOT NULL,
  `CommentContent` varchar(200) NOT NULL,
  `UserId` int(4) NOT NULL,
  `UserName` varchar(255) NOT NULL,
  `visible` int(1) DEFAULT '1',
  PRIMARY KEY (`CommentId`),
  KEY `fk-Comment-User` (`UserId`),
  KEY `fk-Comment-Comment` (`FatherCommentId`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;


INSERT INTO `commentInComment` VALUES ('96', '82', 'rjegiouqwepfoupw', '62', 'Henry', '0');
INSERT INTO `commentInComment` VALUES ('97', '82', '', '62', 'Henry', '1');
INSERT INTO `commentInComment` VALUES ('98', '82', 'ydiuwyeidy', '62', 'Henry', '1');
INSERT INTO `commentInComment` VALUES ('99', '82', 'gjgjhjh', '62', 'Henry', '1');
INSERT INTO `commentInComment` VALUES ('100', '82', 'fffhf', '62', 'Henry', '1');
INSERT INTO `commentInComment` VALUES ('101', '87', 'yruyryj', '62', 'Henry', '1');


DROP TABLE IF EXISTS `multimedia`;
CREATE TABLE `multimedia` (
  `mediaId` int(4) NOT NULL AUTO_INCREMENT,
  `ArticleId` int(4) NOT NULL,
  `mediaURL` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`mediaId`),
  KEY `ArticleId` (`ArticleId`),
  CONSTRAINT `multimedia_ibfk_1` FOREIGN KEY (`ArticleId`) REFERENCES `article` (`ArticleId`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


INSERT INTO `multimedia` VALUES ('38', '283', '/Uploaded_Multimedia/3841_couchplayin.wav');


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `UserId` int(4) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(50) NOT NULL,
  `UserPasswd` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `ProfilePath` varchar(255) NOT NULL,
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8;


INSERT INTO `user` VALUES ('45', 'Neco', '1234', 'nyan779@aucklanduni.ac.nz', '/Uploaded_Profile/Neco.jpg');
INSERT INTO `user` VALUES ('47', 'alias', 'secret', 'fdasfdsa@helo.com', '/Uploaded_Profile/Nick.jpg');
INSERT INTO `user` VALUES ('62', 'Henry', 'secret', 'ac@upupdowndown.com', 'images/Avatars/06.png');
INSERT INTO `user` VALUES ('106', 'tcro', '123', 'tynecrow141@gmail.com', 'images/Avatars/13.png');
INSERT INTO `user` VALUES ('112', 'john1034', 'johnny1', 'kfit706@aucklanduni.ac.nz', 'images/Avatars/13.png');
INSERT INTO `user` VALUES ('113', 'Luke', 'secret', 'l@abc.com', 'images/Avatars/12.png');
INSERT INTO `user` VALUES ('116', 'Tom', 'secret', 'a@b.com', 'images/Avatars/06.png');
INSERT INTO `user` VALUES ('120', 'Liza', 'iamafriend', 'e@e.com', 'images/Avatars/09.png');
INSERT INTO `user` VALUES ('129', 'kimtest', '123', 'kim@fitzmaurice.nz', 'images/Avatars/09.png');


DROP TABLE IF EXISTS `userinformation`;
CREATE TABLE `userinformation` (
  `InformationId` int(4) NOT NULL AUTO_INCREMENT,
  `UserId` int(4) NOT NULL,
  `NickName` varchar(50) DEFAULT NULL,
  `RealName` varchar(50) NOT NULL,
  `Birthday` date DEFAULT NULL,
  `Country` varchar(50) DEFAULT NULL,
  `PublicInfo` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`InformationId`),
  KEY `fk1` (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8;


INSERT INTO `userinformation` VALUES ('15', '45', 'Neco', 'Neco Yang', '1994-09-12', 'China', 'i am a boy lol.');
INSERT INTO `userinformation` VALUES ('30', '61', 'harold', 'Harold Smith', '1972-10-16', 'Austria', 'Hi am testing if this works...');
INSERT INTO `userinformation` VALUES ('31', '62', 'Henry', 'Henry    Smith', '2018-10-04', 'Albania', 'I am administrator of this blog....');
INSERT INTO `userinformation` VALUES ('75', '106', 'tcro', 'Tyne Crow', '1972-03-02', 'New Zealand', 'Tyne!');
INSERT INTO `userinformation` VALUES ('81', '112', 'john1034', 'John   Smith', '1982-03-13', 'New Zealand', 'Hi my name\'s John. I write articles about my favourite food from around the world.  ');
INSERT INTO `userinformation` VALUES ('82', '113', 'Luke', 'Lucas Moriarty', '1981-03-21', 'Estonia', 'This is not a real person');
INSERT INTO `userinformation` VALUES ('83', '114', 'Neco', 'Neco Yang', '1994-09-12', 'China', 'I am Neco.');
INSERT INTO `userinformation` VALUES ('85', '116', 'Tom', 'Thomas Molloy', '2018-10-09', 'Cuba', 'I like karaoke.');
INSERT INTO `userinformation` VALUES ('88', '119', 'Nacha', 'Nacha Nacha', '2018-10-10', 'New Zealand', 'sdfdfdf');
INSERT INTO `userinformation` VALUES ('89', '120', 'Liza', 'Liza Martynova', '1988-05-28', 'Russia', 'hiiii!');
INSERT INTO `userinformation` VALUES ('95', '129', 'kimtest', 'Kim  Test', '1994-10-14', 'New Zealand', 'hello ');
