-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(20) NOT NULL,
  `fullName` varchar(40) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `addTime` datetime NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

