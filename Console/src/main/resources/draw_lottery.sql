/*
Navicat MySQL Data Transfer

Source Server         : 公司数据库
Source Server Version : 50631
Source Host           : 192.168.6.250:3306
Source Database       : draw_lottery

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2017-01-06 22:15:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_advertisement`
-- ----------------------------
DROP TABLE IF EXISTS `t_advertisement`;
CREATE TABLE `t_advertisement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `img_url` varchar(255) NOT NULL COMMENT '图片地址',
  `url` varchar(255) NOT NULL COMMENT '详情地址',
  `state` int(11) DEFAULT NULL COMMENT '可用状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_advertisement
-- ----------------------------
INSERT INTO `t_advertisement` VALUES ('1', 'http://www.yiqiandai.com/fileStore/2/2015/10/19/2804.jpg', 'http://www.yiqiandai.com/fileStore/2/2015/10/19/2804.jpg', '1');
INSERT INTO `t_advertisement` VALUES ('2', 'http://new2.weduty.com/bbs/data/attachment/forum/201503/25/110524htil2sata6mwrhb9.jpg', 'http://www.yiqiandai.com/fileStore/2/2015/10/19/2804.jpg', '1');
INSERT INTO `t_advertisement` VALUES ('3', 'http://img1.gamedog.cn/2015/09/09/211-150Z91613280.jpg', 'http://img1.gamedog.cn/2015/09/09/211-150Z91613280.jpg', '1');
INSERT INTO `t_advertisement` VALUES ('4', 'http://p0.ifengimg.com/fck/2016_20/1b8241c86448be8_w500_h278.png', 'http://www.yiqiandai.com/fileStore/2/2015/10/19/2804.jpg', '1');

-- ----------------------------
-- Table structure for `t_approve`
-- ----------------------------
DROP TABLE IF EXISTS `t_approve`;
CREATE TABLE `t_approve` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_account_id` bigint(20) NOT NULL COMMENT '用户id',
  `real_name` varchar(20) NOT NULL COMMENT '真实姓名',
  `real_number` varchar(20) NOT NULL COMMENT '身份证号',
  `site` varchar(255) NOT NULL COMMENT '常住地址',
  `phone_number` bigint(11) NOT NULL COMMENT '手机号',
  `approve_date` datetime NOT NULL COMMENT '认证日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='认证信息表';

-- ----------------------------
-- Records of t_approve
-- ----------------------------
INSERT INTO `t_approve` VALUES ('1', '1', '小王', '123123123123123123', '成都市', '15283590214', '2016-12-26 18:05:29');

-- ----------------------------
-- Table structure for `t_commodity_history`
-- ----------------------------
DROP TABLE IF EXISTS `t_commodity_history`;
CREATE TABLE `t_commodity_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `commodity_id` bigint(20) NOT NULL COMMENT '商品id',
  `commodity_name` varchar(255) DEFAULT NULL COMMENT '商品名',
  `genre` int(11) DEFAULT NULL COMMENT '商品类别（1：实体，0：虚拟，2、实体不可快递）',
  `luck_code` bigint(20) NOT NULL COMMENT '幸运号id',
  `round_time` varchar(20) NOT NULL COMMENT '期数',
  `buy_number` int(11) NOT NULL COMMENT '中奖人购买数量',
  `end_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '揭晓时间',
  `luck_user_account_id` bigint(20) NOT NULL COMMENT '本期中奖用户ID',
  `cover_img_url` varchar(255) DEFAULT NULL COMMENT '封面图URL',
  `buy_total_number` int(11) NOT NULL COMMENT '当期总需人数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='商品历史表';

-- ----------------------------
-- Records of t_commodity_history
-- ----------------------------
INSERT INTO `t_commodity_history` VALUES ('1', '1', '商品名', '1', '20161231', '1', '10', '2017-01-06 20:37:39', '2', 'http://192.168.6.199:8080/imgs/commodityimg/1395023988671.jpg', '100');
INSERT INTO `t_commodity_history` VALUES ('2', '1', '商品名', '1', '20161231', '1', '10', '2017-01-06 20:37:40', '2', 'http://192.168.6.199:8080/imgs/commodityimg/1395023988671.jpg', '100');
INSERT INTO `t_commodity_history` VALUES ('3', '1', '商品名', '1', '20161231', '1', '10', '2017-01-06 20:37:42', '2', 'http://192.168.6.199:8080/imgs/commodityimg/1395023988671.jpg', '100');

-- ----------------------------
-- Table structure for `t_commodity_states`
-- ----------------------------
DROP TABLE IF EXISTS `t_commodity_states`;
CREATE TABLE `t_commodity_states` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL COMMENT '状态名',
  `message` varchar(255) DEFAULT NULL COMMENT '预留',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='商品状态表';

-- ----------------------------
-- Records of t_commodity_states
-- ----------------------------
INSERT INTO `t_commodity_states` VALUES ('1', '已开奖', null);
INSERT INTO `t_commodity_states` VALUES ('2', '售罄开奖中', null);
INSERT INTO `t_commodity_states` VALUES ('3', '在售', null);
INSERT INTO `t_commodity_states` VALUES ('4', '待售', null);
INSERT INTO `t_commodity_states` VALUES ('5', '已下架', null);

-- ----------------------------
-- Table structure for `t_commodity_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_commodity_type`;
CREATE TABLE `t_commodity_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `img_url` varchar(255) DEFAULT NULL COMMENT '商品类别图标',
  `name` varchar(30) NOT NULL COMMENT '类型名称',
  `state` int(4) NOT NULL COMMENT '当前状态是否可用（0为不可用，1为可用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_commodity_type
-- ----------------------------
INSERT INTO `t_commodity_type` VALUES ('1', 'http://192.168.6.199:8080/imgs/commoditytype/electronicProduct.png', '电子数码', '1');
INSERT INTO `t_commodity_type` VALUES ('2', 'http://192.168.6.199:8080/imgs/commoditytype/householdelectricAppliances.png', '家用电器', '1');
INSERT INTO `t_commodity_type` VALUES ('3', 'http://192.168.6.199:8080/imgs/commoditytype/watchjewelry.png', '钟表首饰', '1');
INSERT INTO `t_commodity_type` VALUES ('4', 'http://192.168.6.199:8080/imgs/commoditytype/cosmeticskincare.png', '化妆护肤', '1');
INSERT INTO `t_commodity_type` VALUES ('5', 'http://192.168.6.199:8080/imgs/commoditytype/computeroffice.png', '电脑办公', '1');
INSERT INTO `t_commodity_type` VALUES ('6', 'http://192.168.6.199:8080/imgs/commoditytype/foodandbeverage.png', '食品饮料', '1');
INSERT INTO `t_commodity_type` VALUES ('7', null, '其他商品', '0');

-- ----------------------------
-- Table structure for `t_commodity_user_history`
-- ----------------------------
DROP TABLE IF EXISTS `t_commodity_user_history`;
CREATE TABLE `t_commodity_user_history` (
  `id` bigint(20) NOT NULL,
  `user_account_id` bigint(20) NOT NULL COMMENT '用户ID',
  `commodity_history_id` bigint(20) NOT NULL COMMENT '商品历史ID',
  `partake_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '参与时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_commodity_user_history
-- ----------------------------

-- ----------------------------
-- Table structure for `t_commoditys`
-- ----------------------------
DROP TABLE IF EXISTS `t_commoditys`;
CREATE TABLE `t_commoditys` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '商品名',
  `commodity_desc` varchar(255) DEFAULT NULL COMMENT '商品描述',
  `commodity_type_id` bigint(20) NOT NULL COMMENT '商品类型id',
  `genre` int(11) NOT NULL COMMENT '商品类别（1：实体，0：虚拟，2、实体不可快递）',
  `buy_last_number` int(11) DEFAULT '0' COMMENT '上一次购买人次',
  `buy_current_number` int(11) DEFAULT NULL COMMENT '当前购买人次',
  `buy_total_number` int(11) NOT NULL COMMENT '总购买人次',
  `ground_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '上架时间',
  `luck_code_id` bigint(20) DEFAULT NULL COMMENT '中奖幸运码id',
  `state_id` int(11) NOT NULL COMMENT '商品状态ID',
  `round_time` varchar(20) NOT NULL COMMENT '当前期数',
  `cover_img_url` varchar(255) NOT NULL COMMENT '封面图片id',
  `auto_round` int(11) NOT NULL DEFAULT '1' COMMENT '是否自动生成下一期（1：是，0：否）',
  `commodity_desc_url` varchar(255) DEFAULT NULL COMMENT '商品详情URL',
  `sell_out_time` datetime NOT NULL COMMENT '售罄时间',
  `undercarriage_time` datetime DEFAULT NULL COMMENT '下架时间',
  `valid` int(11) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `view_num` bigint(20) NOT NULL DEFAULT '0',
  `minimum` int(11) DEFAULT NULL COMMENT '最低购买量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- ----------------------------
-- Records of t_commoditys
-- ----------------------------
INSERT INTO `t_commoditys` VALUES ('1', '兰博基尼', '蝙蝠侠,最新款兰博基尼上线，赶紧来抢！！！', '1', '1', '0', '88888888', '88888888', '2017-01-05 23:40:36', '1', '2', '201612280923', 'http://192.168.6.199:8080/imgs/commodityimg/1395023988671.jpg', '1', 'http://192.168.6.199:8080/api/v1/pub/commodityTemplate/info', '2017-01-05 23:40:00', '2017-01-05 23:40:36', '1', '0', null);
INSERT INTO `t_commoditys` VALUES ('2', 'ip7', '最新款iPhone7上线，赶紧来抢！！！', '2', '1', '0', '20', '4544', '2017-01-05 15:57:21', '1', '1', '42223322', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1482855023226&di=65ef6d57f8f9ecf4f034ae0076fd57b1&imgtype=0&src=http%3A%2F%2Fimg.18183.duoku.com%2Fuploads%2Fphone%2Falbum%2F1414398853_925.jpg', '1', 'http://192.168.6.199:8080/api/v1/pub/commodityTemplate/info', '1970-01-01 00:00:00', '2017-01-05 15:57:21', '1', '0', null);
INSERT INTO `t_commoditys` VALUES ('3', '123', '移动50元充值卡kkkkkkkkkkkk', '3', '1', '0', '12', '12', '2017-01-05 15:57:22', '2', '2', '21321321', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1482855023226&di=65ef6d57f8f9ecf4f034ae0076fd57b1&imgtype=0&src=http%3A%2F%2Fimg.18183.duoku.com%2Fuploads%2Fphone%2Falbum%2F1414398853_925.jpg', '11', 'http://192.168.6.199:8080/api/v1/pub/commodityTemplate/info', '1970-01-01 00:00:00', '2017-01-05 15:57:22', '1', '0', null);
INSERT INTO `t_commoditys` VALUES ('4', '123', '移动100元充值卡', '4', '1', '0', '12', '12', '2017-01-05 15:57:22', '2', '2', '21321321', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1482855023226&di=65ef6d57f8f9ecf4f034ae0076fd57b1&imgtype=0&src=http%3A%2F%2Fimg.18183.duoku.com%2Fuploads%2Fphone%2Falbum%2F1414398853_925.jpg', '11', 'http://192.168.6.199:8080/api/v1/pub/commodityTemplate/info', '1970-01-01 00:00:00', '2017-01-05 15:57:22', '1', '0', null);
INSERT INTO `t_commoditys` VALUES ('5', '火车', '最新绿皮火车', '5', '1', '0', '5665', '2342323', '2017-01-06 14:51:28', '2', '0', '201612280923', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1482855023226&di=65ef6d57f8f9ecf4f034ae0076fd57b1&imgtype=0&src=http%3A%2F%2Fimg.18183.duoku.com%2Fuploads%2Fphone%2Falbum%2F1414398853_925.jpg', '1', 'http://192.168.6.199:8080/api/v1/pub/commodityTemplate/info', '1970-01-01 00:00:00', '2017-01-06 14:51:28', '1', '0', null);
INSERT INTO `t_commoditys` VALUES ('6', '火车', '最新绿皮火车', '6', '1', '0', '56651', '2342323', '2017-01-09 15:57:22', '2', '3', '201612280923', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1482855023226&di=65ef6d57f8f9ecf4f034ae0076fd57b1&imgtype=0&src=http%3A%2F%2Fimg.18183.duoku.com%2Fuploads%2Fphone%2Falbum%2F1414398853_925.jpg', '1', 'http://192.168.6.199:8080/api/v1/pub/commodityTemplate/info', '1970-01-01 00:00:00', '2017-01-06 14:25:27', '1', '0', null);
INSERT INTO `t_commoditys` VALUES ('7', '火车', '最新绿皮火车', '1', '1', '0', '56265', '2342323', '2017-01-05 15:57:22', '2', '3', '201612280923', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1482855023226&di=65ef6d57f8f9ecf4f034ae0076fd57b1&imgtype=0&src=http%3A%2F%2Fimg.18183.duoku.com%2Fuploads%2Fphone%2Falbum%2F1414398853_925.jpg', '1', 'http://192.168.6.199:8080/api/v1/pub/commodityTemplate/info', '1970-01-01 00:00:00', '2017-01-05 15:57:22', '1', '0', null);
INSERT INTO `t_commoditys` VALUES ('8', '123', '移动100元充值卡', '1', '1', '0', '12', '12', '2017-01-05 15:57:23', '2', '2', '21321321', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1482855023226&di=65ef6d57f8f9ecf4f034ae0076fd57b1&imgtype=0&src=http%3A%2F%2Fimg.18183.duoku.com%2Fuploads%2Fphone%2Falbum%2F1414398853_925.jpg', '11', 'http://192.168.6.199:8080/api/v1/pub/commodityTemplate/info', '1970-01-01 00:00:00', '2017-01-05 15:57:23', '1', '0', null);
INSERT INTO `t_commoditys` VALUES ('9', '兰博基尼', '蝙蝠侠,最新款兰博基尼上线，赶紧来抢！！！', '1', '1', '0', '1000', '10000000', '2017-01-05 15:57:23', '1000', '3', '201612280923', 'http://192.168.6.199:8080/imgs/commodityimg/2853786_210345665000_2.jpg', '1', 'http://192.168.6.199:8080/api/v1/pub/commodityTemplate/info', '1970-01-01 00:00:00', '2017-01-05 15:57:23', '1', '0', null);
INSERT INTO `t_commoditys` VALUES ('10', '兰博基尼', '蝙蝠侠,最新款兰博基尼上线，赶紧来抢！！！', '1', '1', '0', '9000', '10000000', '2017-01-05 15:57:23', '1001', '3', '201612280923', 'http://192.168.6.199:8080/imgs/commodityimg/Redocn_2011061616393479.jpg', '1', 'http://192.168.6.199:8080/api/v1/pub/commodityTemplate/info', '1970-01-01 00:00:00', '2017-01-05 15:57:23', '1', '0', null);
INSERT INTO `t_commoditys` VALUES ('11', '兰博基尼', '蝙蝠侠,最新款兰博基尼上线，赶紧来抢！！！kkkkkkk', '1', '1', '0', '10000', '10000000', '2017-01-05 15:57:23', '1002', '3', '201612280923', 'http://192.168.6.199:8080/imgs/commodityimg/17961491_211506421000_2.jpg', '1', 'http://192.168.6.199:8080/api/v1/pub/commodityTemplate/info', '1970-01-01 00:00:00', '2017-01-05 15:57:23', '1', '0', null);
INSERT INTO `t_commoditys` VALUES ('12', '兰博基尼', '蝙蝠侠,最新款兰博基尼上线，赶紧来抢！！！kkkkkkkkkkk', '0', '0', '0', '375758', '100000000', '2017-01-05 15:57:24', '1003', '3', '201612280923', 'http://192.168.6.199:8080/imgs/commodityimg/c0255f64dabfe3c165fbbcd79cfe106e.jpg', '1', 'http://192.168.6.199:8080/api/v1/pub/commodityTemplate/info', '1970-01-01 00:00:00', '2017-01-05 15:57:24', '1', '0', null);
INSERT INTO `t_commoditys` VALUES ('13', 'ip7', '最新款iPhone7上线，赶紧来抢！！！', '1', '1', '0', '2000', '4544', '2017-01-05 15:57:24', '1', '1', '42223322', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1482855023226&di=65ef6d57f8f9ecf4f034ae0076fd57b1&imgtype=0&src=http%3A%2F%2Fimg.18183.duoku.com%2Fuploads%2Fphone%2Falbum%2F1414398853_925.jpg', '1', 'http://192.168.6.199:8080/api/v1/pub/commodityTemplate/info', '1970-01-01 00:00:00', '2017-01-05 15:57:24', '1', '0', null);
INSERT INTO `t_commoditys` VALUES ('14', 'ip7', '最新款iPhone7上线，赶紧来抢！！！', '1', '1', '0', '4544', '4544', '2017-01-05 15:57:24', '5', '2', '201612291411', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1482855023226&di=65ef6d57f8f9ecf4f034ae0076fd57b1&imgtype=0&src=http%3A%2F%2Fimg.18183.duoku.com%2Fuploads%2Fphone%2Falbum%2F1414398853_925.jpg', '1', 'http://192.168.6.199:8080/api/v1/pub/commodityTemplate/info', '1970-01-01 00:00:00', '2017-01-05 15:57:24', '1', '0', null);
INSERT INTO `t_commoditys` VALUES ('15', '冬瓜皮', '凉西皮-凉西皮-凉西皮-凉西皮', '2', '1', '0', '123', '200', '2017-01-05 15:57:24', '5', '3', '201612291411', 'http://s14.sinaimg.cn/mw690/001YtXnhzy6JTINSzO5cd&690', '1', 'http://192.168.6.199:8080/api/v1/pub/commodityTemplate/info', '1970-01-01 00:00:00', '2017-01-05 15:57:24', '1', '0', null);
INSERT INTO `t_commoditys` VALUES ('16', '冬瓜皮', '凉西皮-凉西皮-凉西皮-凉西皮', '2', '1', '0', '123', '200', '2017-01-05 15:57:24', '5', '3', '201612291411', 'http://s14.sinaimg.cn/mw690/001YtXnhzy6JTINSzO5cd&690', '1', 'http://192.168.6.199:8080/api/v1/pub/commodityTemplate/info', '1970-01-01 00:00:00', '2017-01-05 15:57:24', '1', '0', null);
INSERT INTO `t_commoditys` VALUES ('17', '冬瓜皮', '凉西皮-凉西皮-凉西皮-凉西皮', '2', '1', '0', '123', '200', '2017-01-05 15:57:24', '5', '0', '201612291411', 'http://s14.sinaimg.cn/mw690/001YtXnhzy6JTINSzO5cd&690', '1', 'http://192.168.6.199:8080/api/v1/pub/commodityTemplate/info', '1970-01-01 00:00:00', '2017-01-05 15:57:24', '1', '0', null);
INSERT INTO `t_commoditys` VALUES ('18', '冬瓜皮', '凉西皮-凉西皮-凉西皮-凉西皮', '2', '1', '0', '123', '200', '2017-01-05 15:57:24', '5', '0', '201612291411', 'http://s14.sinaimg.cn/mw690/001YtXnhzy6JTINSzO5cd&690', '1', 'http://192.168.6.199:8080/api/v1/pub/commodityTemplate/info', '1970-01-01 00:00:00', '2017-01-05 15:57:24', '1', '0', null);
INSERT INTO `t_commoditys` VALUES ('19', '冬瓜皮', '凉西皮-凉西皮-凉西皮-凉西皮', '2', '1', '0', '123', '200', '2017-01-05 15:57:24', '5', '0', '201612291411', 'http://s14.sinaimg.cn/mw690/001YtXnhzy6JTINSzO5cd&690', '1', 'http://192.168.6.199:8080/api/v1/pub/commodityTemplate/info', '1970-01-01 00:00:00', '2017-01-05 15:57:24', '1', '0', null);
INSERT INTO `t_commoditys` VALUES ('20', '冬瓜皮', '凉西皮-凉西皮-凉西皮-凉西皮', '2', '1', '0', '123', '200', '2017-01-05 15:57:24', '5', '0', '201612291411', 'http://s14.sinaimg.cn/mw690/001YtXnhzy6JTINSzO5cd&690', '1', 'http://192.168.6.199:8080/api/v1/pub/commodityTemplate/info', '1970-01-01 00:00:00', '2017-01-05 15:57:24', '1', '0', null);
INSERT INTO `t_commoditys` VALUES ('21', '冬瓜皮', '凉西皮-凉西皮-凉西皮-凉西皮', '2', '1', '0', '123', '200', '2017-01-05 15:57:24', '5', '0', '201612291411', 'http://s14.sinaimg.cn/mw690/001YtXnhzy6JTINSzO5cd&690', '1', 'http://192.168.6.199:8080/api/v1/pub/commodityTemplate/info', '1970-01-01 00:00:00', '2017-01-05 15:57:24', '1', '0', null);
INSERT INTO `t_commoditys` VALUES ('22', '冬瓜皮', '凉西皮-凉西皮-凉西皮-凉西皮', '2', '1', '0', '123', '200', '2017-01-05 15:57:24', '5', '0', '201612291411', 'http://s14.sinaimg.cn/mw690/001YtXnhzy6JTINSzO5cd&690', '1', 'http://192.168.6.199:8080/api/v1/pub/commodityTemplate/info', '1970-01-01 00:00:00', '2017-01-05 15:57:24', '1', '0', null);

-- ----------------------------
-- Table structure for `t_commoditys_imags`
-- ----------------------------
DROP TABLE IF EXISTS `t_commoditys_imags`;
CREATE TABLE `t_commoditys_imags` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) NOT NULL COMMENT '图片url地址',
  `state` int(11) NOT NULL COMMENT '是否有效',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `commodity_id` bigint(20) NOT NULL COMMENT '商品ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='商品图片表';

-- ----------------------------
-- Records of t_commoditys_imags
-- ----------------------------
INSERT INTO `t_commoditys_imags` VALUES ('1', 'http://192.168.6.199:8080/imgs/commodityimg/17961491_211506421000_2.jpg', '1', '2016-12-24 09:42:44', '10');
INSERT INTO `t_commoditys_imags` VALUES ('2', 'http://192.168.6.199:8080/imgs/commodityimg/c0255f64dabfe3c165fbbcd79cfe106e.jpg', '1', '2016-12-24 09:52:07', '10');
INSERT INTO `t_commoditys_imags` VALUES ('3', 'http://192.168.6.199:8080/imgs/commodityimg/1395023988671.jpg', '1', '2016-12-24 09:52:07', '10');
INSERT INTO `t_commoditys_imags` VALUES ('4', 'http://192.168.6.199:8080/imgs/commodityimg/c0255f64dabfe3c165fbbcd79cfe106e.jpg', '1', '2016-12-29 14:54:53', '1');
INSERT INTO `t_commoditys_imags` VALUES ('5', 'http://192.168.6.199:8080/imgs/commodityimg/c0255f64dabfe3c165fbbcd79cfe106e.jpg', '1', '2016-12-29 14:57:24', '1');
INSERT INTO `t_commoditys_imags` VALUES ('6', 'http://192.168.6.199:8080/imgs/commodityimg/c0255f64dabfe3c165fbbcd79cfe106e.jpg', '1', '2016-12-29 14:57:34', '1');

-- ----------------------------
-- Table structure for `t_delivery_address`
-- ----------------------------
DROP TABLE IF EXISTS `t_delivery_address`;
CREATE TABLE `t_delivery_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_account_id` bigint(20) NOT NULL COMMENT '用户账号',
  `receiver_name` varchar(10) DEFAULT NULL COMMENT '收货人姓名',
  `phone` varchar(13) DEFAULT NULL COMMENT '收货人电话',
  `address` varchar(255) DEFAULT NULL COMMENT '收货地址',
  `state` int(11) DEFAULT NULL COMMENT '是否默认地址（0 否 || 1 是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='收货地址表';

-- ----------------------------
-- Records of t_delivery_address
-- ----------------------------
INSERT INTO `t_delivery_address` VALUES ('4', '10000', 'string', 'string', 'string', '0');
INSERT INTO `t_delivery_address` VALUES ('5', '10000', 'AESTRDFYG', '阿斯顿发股份', '3RAQTGFA', '0');
INSERT INTO `t_delivery_address` VALUES ('6', '10001', 'string', 'string', 'string', '0');
INSERT INTO `t_delivery_address` VALUES ('7', '10001', 'string', 'string', 'string', '0');
INSERT INTO `t_delivery_address` VALUES ('8', '10001', 'string', 'string', 'string', '0');
INSERT INTO `t_delivery_address` VALUES ('9', '10002', 'string', '13990949387', 'string', '0');

-- ----------------------------
-- Table structure for `t_exchange_way`
-- ----------------------------
DROP TABLE IF EXISTS `t_exchange_way`;
CREATE TABLE `t_exchange_way` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '兑换方式',
  `state` int(11) NOT NULL COMMENT '兑换方式可用？  1：不可用，0：不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='兑换方式表';

-- ----------------------------
-- Records of t_exchange_way
-- ----------------------------
INSERT INTO `t_exchange_way` VALUES ('1', '自动充值', '1');
INSERT INTO `t_exchange_way` VALUES ('2', '快递收货', '1');
INSERT INTO `t_exchange_way` VALUES ('3', '兑换现金', '1');
INSERT INTO `t_exchange_way` VALUES ('4', '兑换闪币', '1');
INSERT INTO `t_exchange_way` VALUES ('5', '到店领取', '1');

-- ----------------------------
-- Table structure for `t_hot_search`
-- ----------------------------
DROP TABLE IF EXISTS `t_hot_search`;
CREATE TABLE `t_hot_search` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '搜索关键字',
  `frequency` bigint(20) NOT NULL COMMENT '搜索次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COMMENT='热门搜索表';

-- ----------------------------
-- Records of t_hot_search
-- ----------------------------
INSERT INTO `t_hot_search` VALUES ('1', '手机', '99');
INSERT INTO `t_hot_search` VALUES ('2', '汽车', '80');
INSERT INTO `t_hot_search` VALUES ('3', '手表', '30');
INSERT INTO `t_hot_search` VALUES ('4', '移动充值卡', '199');
INSERT INTO `t_hot_search` VALUES ('5', '电信充值卡', '301');
INSERT INTO `t_hot_search` VALUES ('6', '联通充值卡', '123');
INSERT INTO `t_hot_search` VALUES ('7', '笔记本', '200');
INSERT INTO `t_hot_search` VALUES ('8', '单反', '807');
INSERT INTO `t_hot_search` VALUES ('9', '电视机', '201');
INSERT INTO `t_hot_search` VALUES ('10', 'iPhone', '601');
INSERT INTO `t_hot_search` VALUES ('11', '宝马', '300');
INSERT INTO `t_hot_search` VALUES ('12', '平板', '600');
INSERT INTO `t_hot_search` VALUES ('13', '兰博', '1004');
INSERT INTO `t_hot_search` VALUES ('14', 'lanbo', '1');
INSERT INTO `t_hot_search` VALUES ('15', '1', '36');
INSERT INTO `t_hot_search` VALUES ('16', 'e3242', '1');
INSERT INTO `t_hot_search` VALUES ('17', '\'1\'', '1');
INSERT INTO `t_hot_search` VALUES ('18', 'ada', '1');

-- ----------------------------
-- Table structure for `t_images`
-- ----------------------------
DROP TABLE IF EXISTS `t_images`;
CREATE TABLE `t_images` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `img_url` varchar(255) NOT NULL COMMENT '图片url',
  `depict` varchar(255) DEFAULT NULL COMMENT '描述',
  `skip_url` varchar(255) DEFAULT NULL COMMENT '跳转界面（广告图跳转）',
  `img_genre` int(6) NOT NULL COMMENT '图片类别（1：小图标，2：广告图,3：活动）',
  `state` int(4) NOT NULL COMMENT '状态（1：可用，0：不可用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='小图标，广告图表';

-- ----------------------------
-- Records of t_images
-- ----------------------------
INSERT INTO `t_images` VALUES ('3', 'http://www.yiqiandai.com/fileStore/2/2015/10/19/2804.jpg', null, 'http://www.yiqiandai.com/fileStore/2/2015/10/19/2804.jpg', '2', '1');
INSERT INTO `t_images` VALUES ('4', 'http://new2.weduty.com/bbs/data/attachment/forum/201503/25/110524htil2sata6mwrhb9.jpg', null, 'http://www.yiqiandai.com/fileStore/2/2015/10/19/2804.jpg', '2', '1');
INSERT INTO `t_images` VALUES ('5', 'http://img1.gamedog.cn/2015/09/09/211-150Z91613280.jpg', null, 'http://www.yiqiandai.com/fileStore/2/2015/10/19/2804.jpg', '2', '1');
INSERT INTO `t_images` VALUES ('6', 'http://p0.ifengimg.com/fck/2016_20/1b8241c86448be8_w500_h278.png', null, 'http://www.yiqiandai.com/fileStore/2/2015/10/19/2804.jpg', '2', '1');
INSERT INTO `t_images` VALUES ('7', 'http://s16.sinaimg.cn/mw690/005NeVnxzy6WL7wgQZF1f&690', '闪电夺宝，一网打尽！', 'http://www.yiqiandai.com/fileStore/2/2015/10/19/2804.jpg', '3', '1');
INSERT INTO `t_images` VALUES ('8', 'http://6.pic.pc6.com/up/2016-1/2016127154525108.png', '全部活动', null, '1', '1');
INSERT INTO `t_images` VALUES ('9', 'http://img.25pp.com/uploadfile/app/icon/20160225/1456395699507515.jpg', '品类', null, '1', '1');
INSERT INTO `t_images` VALUES ('10', 'http://img.25pp.com/uploadfile/app/icon/20160511/1462905820695740.jpg', '每日签到', null, '1', '1');
INSERT INTO `t_images` VALUES ('11', 'http://img.25pp.com/uploadfile/app/icon/20160606/1465217533244712.jpg', '高中奖率', null, '1', '1');

-- ----------------------------
-- Table structure for `t_luck_codes`
-- ----------------------------
DROP TABLE IF EXISTS `t_luck_codes`;
CREATE TABLE `t_luck_codes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lock_code` int(11) NOT NULL COMMENT '幸运号',
  `commodity_id` bigint(20) NOT NULL COMMENT '商品ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='幸运号';

-- ----------------------------
-- Records of t_luck_codes
-- ----------------------------
INSERT INTO `t_luck_codes` VALUES ('1', '2016122411', '1');
INSERT INTO `t_luck_codes` VALUES ('3', '123123', '2');
INSERT INTO `t_luck_codes` VALUES ('4', '123321213', '3');
INSERT INTO `t_luck_codes` VALUES ('5', '554444323', '4');
INSERT INTO `t_luck_codes` VALUES ('6', '123567', '5');

-- ----------------------------
-- Table structure for `t_luck_notice`
-- ----------------------------
DROP TABLE IF EXISTS `t_luck_notice`;
CREATE TABLE `t_luck_notice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `commodity_id` bigint(20) NOT NULL COMMENT '商品ID',
  `user_account_id` bigint(20) NOT NULL COMMENT '用户ID',
  `state` int(11) NOT NULL COMMENT '状态',
  `img_url` varchar(255) NOT NULL COMMENT '图片URL',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户中奖通知';

-- ----------------------------
-- Records of t_luck_notice
-- ----------------------------

-- ----------------------------
-- Table structure for `t_notification_campaign`
-- ----------------------------
DROP TABLE IF EXISTS `t_notification_campaign`;
CREATE TABLE `t_notification_campaign` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_account_id` bigint(20) NOT NULL COMMENT '用户ID',
  `notice_title` varchar(255) NOT NULL COMMENT '活动标题',
  `notice_url` varchar(255) NOT NULL COMMENT '详情url',
  `notice_cover_img_url` varchar(255) NOT NULL COMMENT '活动图片url',
  `send_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动表';

-- ----------------------------
-- Records of t_notification_campaign
-- ----------------------------

-- ----------------------------
-- Table structure for `t_notification_prize`
-- ----------------------------
DROP TABLE IF EXISTS `t_notification_prize`;
CREATE TABLE `t_notification_prize` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_account_id` bigint(20) NOT NULL COMMENT '用户accountID',
  `commodity_id` bigint(20) NOT NULL COMMENT '商品ID',
  `luck_codes_id` bigint(20) NOT NULL COMMENT '幸运码ID',
  `on_prize_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '开奖时间',
  `luck_account_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='开奖通知';

-- ----------------------------
-- Records of t_notification_prize
-- ----------------------------
INSERT INTO `t_notification_prize` VALUES ('1', '1', '2', '1', '2016-12-27 21:51:34', '0');
INSERT INTO `t_notification_prize` VALUES ('2', '1', '2', '2', '2016-12-27 21:51:30', '0');
INSERT INTO `t_notification_prize` VALUES ('3', '1', '3', '3', '2016-12-27 21:51:18', '0');

-- ----------------------------
-- Table structure for `t_notification_system`
-- ----------------------------
DROP TABLE IF EXISTS `t_notification_system`;
CREATE TABLE `t_notification_system` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_account_id` bigint(20) NOT NULL COMMENT '用户ID',
  `notice_title` varchar(255) NOT NULL COMMENT '通知title',
  `notice_url` varchar(255) NOT NULL COMMENT '通知url',
  `send_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统通知';

-- ----------------------------
-- Records of t_notification_system
-- ----------------------------

-- ----------------------------
-- Table structure for `t_orders`
-- ----------------------------
DROP TABLE IF EXISTS `t_orders`;
CREATE TABLE `t_orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_account_id` bigint(20) NOT NULL COMMENT '用户accuount_id',
  `pay_mode_id` int(11) DEFAULT NULL COMMENT '支付方式id',
  `submit_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '订单提交时间',
  `price` int(11) DEFAULT NULL COMMENT '价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Records of t_orders
-- ----------------------------
INSERT INTO `t_orders` VALUES ('6', '1', '1', '2016-12-28 17:00:59', null);
INSERT INTO `t_orders` VALUES ('7', '1', '1', '2016-12-28 17:00:59', null);
INSERT INTO `t_orders` VALUES ('8', '1', '2', '2016-12-28 17:00:59', null);
INSERT INTO `t_orders` VALUES ('9', '1', '1', '2016-12-28 17:00:59', null);
INSERT INTO `t_orders` VALUES ('10', '1', '1', '2016-12-28 17:00:42', null);
INSERT INTO `t_orders` VALUES ('11', '1', '1', '2016-12-28 17:00:59', null);
INSERT INTO `t_orders` VALUES ('12', '10000', '1', '2017-01-06 22:11:51', '1000');
INSERT INTO `t_orders` VALUES ('13', '10000', '1', '2017-01-06 22:14:14', '1000');

-- ----------------------------
-- Table structure for `t_orders_commoditys`
-- ----------------------------
DROP TABLE IF EXISTS `t_orders_commoditys`;
CREATE TABLE `t_orders_commoditys` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orders_id` bigint(20) NOT NULL COMMENT '订单ID',
  `commodity_id` bigint(20) NOT NULL COMMENT '商品ID',
  `amount` int(11) DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='订单商品对应表';

-- ----------------------------
-- Records of t_orders_commoditys
-- ----------------------------
INSERT INTO `t_orders_commoditys` VALUES ('1', '13', '1', '100');
INSERT INTO `t_orders_commoditys` VALUES ('2', '13', '1', '100');
INSERT INTO `t_orders_commoditys` VALUES ('3', '13', '1', '100');
INSERT INTO `t_orders_commoditys` VALUES ('4', '13', '1', '100');
INSERT INTO `t_orders_commoditys` VALUES ('5', '13', '1', '100');
INSERT INTO `t_orders_commoditys` VALUES ('6', '13', '1', '100');
INSERT INTO `t_orders_commoditys` VALUES ('7', '13', '1', '100');
INSERT INTO `t_orders_commoditys` VALUES ('8', '13', '1', '100');
INSERT INTO `t_orders_commoditys` VALUES ('9', '13', '1', '100');
INSERT INTO `t_orders_commoditys` VALUES ('10', '13', '1', '100');

-- ----------------------------
-- Table structure for `t_pay_mode`
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_mode`;
CREATE TABLE `t_pay_mode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pay_name` varchar(20) NOT NULL COMMENT '支付方式名',
  `pay_state` int(11) DEFAULT NULL COMMENT '支付状态（1为可用，0为不可用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='支付方式表';

-- ----------------------------
-- Records of t_pay_mode
-- ----------------------------
INSERT INTO `t_pay_mode` VALUES ('1', '余额支付', '1');
INSERT INTO `t_pay_mode` VALUES ('2', '支付宝支付', '1');
INSERT INTO `t_pay_mode` VALUES ('3', '微信支付', '1');
INSERT INTO `t_pay_mode` VALUES ('4', '银联支付', '1');

-- ----------------------------
-- Table structure for `t_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '权限名',
  `role_id` bigint(20) NOT NULL COMMENT '对应角色id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_permission_name_uindex` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- ----------------------------
-- Records of t_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `t_prepaid_card`
-- ----------------------------
DROP TABLE IF EXISTS `t_prepaid_card`;
CREATE TABLE `t_prepaid_card` (
  `id` bigint(11) NOT NULL,
  `user_account_id` bigint(11) DEFAULT NULL COMMENT '用户id',
  `img_url` varchar(255) DEFAULT NULL COMMENT '图片url',
  `card_number` varchar(20) NOT NULL COMMENT '充值卡号',
  `password` varchar(20) NOT NULL COMMENT '卡密',
  `operator` varchar(255) NOT NULL COMMENT '运营商',
  `worth` int(11) NOT NULL COMMENT '价格面额',
  `state` int(11) DEFAULT NULL COMMENT '使用状态（0、未派发，1、已派发）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_prepaid_card
-- ----------------------------

-- ----------------------------
-- Table structure for `t_red_packets`
-- ----------------------------
DROP TABLE IF EXISTS `t_red_packets`;
CREATE TABLE `t_red_packets` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_account_id` bigint(20) NOT NULL COMMENT '用户id',
  `valid_date` datetime NOT NULL COMMENT '生效日期',
  `overdue_date` datetime NOT NULL COMMENT '失效日期',
  `name` varchar(20) DEFAULT NULL COMMENT '红包名字',
  `use_price` int(11) NOT NULL COMMENT '使用条件,订单高于该价格就可使用',
  `worth` int(11) NOT NULL COMMENT '红包大小',
  `use_state` int(11) NOT NULL DEFAULT '0' COMMENT '是否已使用（1：已使用，0：未使用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='红包表';

-- ----------------------------
-- Records of t_red_packets
-- ----------------------------
INSERT INTO `t_red_packets` VALUES ('1', '10000', '2017-01-03 17:51:25', '2017-04-29 17:51:30', '红包', '150', '15', '1');
INSERT INTO `t_red_packets` VALUES ('2', '10000', '2016-12-23 18:10:37', '2016-12-30 18:10:37', '新手红包', '50', '5', '1');
INSERT INTO `t_red_packets` VALUES ('3', '10000', '2016-12-26 18:49:38', '2017-02-23 18:49:38', '新手红包', '1000', '100', '0');
INSERT INTO `t_red_packets` VALUES ('4', '10000', '2016-12-29 09:58:35', '2017-06-22 09:58:41', '新手红包', '100', '10', '0');
INSERT INTO `t_red_packets` VALUES ('6', '10000', '2016-12-29 09:58:35', '2017-06-22 09:58:41', '新手红包', '200', '10', '0');
INSERT INTO `t_red_packets` VALUES ('7', '10000', '2016-12-29 09:58:35', '2017-06-22 09:58:41', '新手红包', '50', '20', '0');
INSERT INTO `t_red_packets` VALUES ('8', '10000', '2016-12-29 09:58:35', '2017-06-22 09:58:41', '新手红包', '100', '15', '0');
INSERT INTO `t_red_packets` VALUES ('9', '10000', '2016-12-29 09:58:35', '2017-06-22 09:58:41', '新手红包', '100', '30', '0');

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------

-- ----------------------------
-- Table structure for `t_share`
-- ----------------------------
DROP TABLE IF EXISTS `t_share`;
CREATE TABLE `t_share` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_account_id` bigint(20) NOT NULL COMMENT '用户id',
  `issue_date` datetime NOT NULL COMMENT '晒单发布日期',
  `commodity_id` bigint(11) NOT NULL COMMENT '商品id',
  `particulars` varchar(255) DEFAULT NULL COMMENT '晒单详情',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_share
-- ----------------------------
INSERT INTO `t_share` VALUES ('2', '2', '2016-12-26 18:40:24', '2', 'url1');
INSERT INTO `t_share` VALUES ('3', '1', '2017-01-04 20:13:48', '10', '这是我写的晒单详情！！！！');
INSERT INTO `t_share` VALUES ('4', '1', '2017-01-04 20:59:22', '10', '这是我写的晒单详情！！！！');
INSERT INTO `t_share` VALUES ('5', '1', '2017-01-04 21:00:08', '10', '这是我写的晒单详情！！！！');

-- ----------------------------
-- Table structure for `t_share_img`
-- ----------------------------
DROP TABLE IF EXISTS `t_share_img`;
CREATE TABLE `t_share_img` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `share_id` bigint(20) NOT NULL COMMENT '晒单ID',
  `share_img_url` varchar(255) NOT NULL COMMENT '晒单照片URL',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_share_img
-- ----------------------------

-- ----------------------------
-- Table structure for `t_shopping_cart`
-- ----------------------------
DROP TABLE IF EXISTS `t_shopping_cart`;
CREATE TABLE `t_shopping_cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `commodity_id` bigint(20) NOT NULL COMMENT '商品ID',
  `user_account_id` bigint(20) NOT NULL COMMENT '用户id',
  `add_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '添加时间',
  `number` int(11) DEFAULT NULL COMMENT '购买人次',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='购物车';

-- ----------------------------
-- Records of t_shopping_cart
-- ----------------------------
INSERT INTO `t_shopping_cart` VALUES ('1', '10', '2', '2016-12-31 16:28:02', '10');

-- ----------------------------
-- Table structure for `t_sign_in`
-- ----------------------------
DROP TABLE IF EXISTS `t_sign_in`;
CREATE TABLE `t_sign_in` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_account_id` bigint(20) NOT NULL COMMENT '用户id',
  `sign_in_day` int(11) DEFAULT '0' COMMENT '签到天数',
  `new_sign_date` datetime DEFAULT NULL COMMENT '最新签到时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_sign_in
-- ----------------------------
INSERT INTO `t_sign_in` VALUES ('1', '10000', '1', '2016-12-28 14:33:13');
INSERT INTO `t_sign_in` VALUES ('2', '10000', '2', '2016-12-29 14:34:14');
INSERT INTO `t_sign_in` VALUES ('3', '10000', '3', '2016-12-30 15:06:26');
INSERT INTO `t_sign_in` VALUES ('4', '10000', '4', '2016-12-31 15:08:08');
INSERT INTO `t_sign_in` VALUES ('5', '10000', '5', '2017-01-01 15:09:47');
INSERT INTO `t_sign_in` VALUES ('6', '10000', '1', '2017-01-03 15:10:42');

-- ----------------------------
-- Table structure for `t_user_luck_codes`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_luck_codes`;
CREATE TABLE `t_user_luck_codes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `luck_code_id` bigint(20) NOT NULL COMMENT '幸运号id',
  `commodity_id` bigint(20) NOT NULL COMMENT '商品ID',
  `user_account_id` bigint(20) NOT NULL COMMENT '用户id',
  `buy_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='用户，商品，幸运号对应表';

-- ----------------------------
-- Records of t_user_luck_codes
-- ----------------------------
INSERT INTO `t_user_luck_codes` VALUES ('1', '1', '1', '10000', '2017-01-05 22:29:51');
INSERT INTO `t_user_luck_codes` VALUES ('2', '2', '2', '2', '2017-01-05 23:31:54');
INSERT INTO `t_user_luck_codes` VALUES ('3', '3', '2', '1', '2016-12-28 16:09:24');
INSERT INTO `t_user_luck_codes` VALUES ('4', '4', '2', '2', '2016-12-28 16:09:27');
INSERT INTO `t_user_luck_codes` VALUES ('5', '5', '2', '1', '2016-12-28 16:09:29');
INSERT INTO `t_user_luck_codes` VALUES ('6', '6', '2', '2', '2016-12-28 16:09:32');
INSERT INTO `t_user_luck_codes` VALUES ('7', '7', '2', '1', '2016-12-28 16:09:34');
INSERT INTO `t_user_luck_codes` VALUES ('8', '8', '2', '2', '2016-12-28 16:09:37');
INSERT INTO `t_user_luck_codes` VALUES ('9', '9', '1', '10000', '2017-01-05 22:29:51');

-- ----------------------------
-- Table structure for `t_user_luck_codes_history`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_luck_codes_history`;
CREATE TABLE `t_user_luck_codes_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_luck_code_id` bigint(20) NOT NULL COMMENT '幸运号id',
  `commodity_id` bigint(20) NOT NULL COMMENT '商品ID',
  `user_account_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='用户，商品，幸运号对应历史表';

-- ----------------------------
-- Records of t_user_luck_codes_history
-- ----------------------------
INSERT INTO `t_user_luck_codes_history` VALUES ('1', '2', '1', '10000');

-- ----------------------------
-- Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色用户关联表';

-- ----------------------------
-- Records of t_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for `t_users`
-- ----------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users` (
  `account_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户账号,ID号',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  `real_name` varchar(15) DEFAULT NULL COMMENT '用户姓名',
  `nickname` varchar(15) DEFAULT NULL COMMENT '昵称',
  `phone_number` varchar(13) DEFAULT NULL COMMENT '用户电话',
  `salt` varchar(255) DEFAULT NULL COMMENT '密码盐',
  `user_integral` int(11) DEFAULT NULL COMMENT '用户积分',
  `current_state` int(11) DEFAULT NULL COMMENT '用户当前状态',
  `header_url` varchar(255) DEFAULT NULL COMMENT '头像id',
  `gold_number` int(11) DEFAULT NULL COMMENT '账户余额',
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10030 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='用户表';

-- ----------------------------
-- Records of t_users
-- ----------------------------
INSERT INTO `t_users` VALUES ('1', '123', 'kkk', 'kkk', '123', 'wqe', '123', '12', 'http://up.qqjia.com/z/26/tu32783_2.jpg', '123');
INSERT INTO `t_users` VALUES ('2', '123', '小张', '我是你爹', '12333', 'qwe', '123', '12', 'http://up.qqjia.com/z/26/tu32783_2.jpg', '23');
INSERT INTO `t_users` VALUES ('3', '123', '马吉', '马吉吉', '12321', '123', '123', '12', 'http://up.qqjia.com/z/26/tu32783_2.jpg', '12');
INSERT INTO `t_users` VALUES ('4', '123', '动词', '动次打次', '123213', '123qwe', '123', '12', 'http://up.qqjia.com/z/26/tu32783_2.jpg', '45');
INSERT INTO `t_users` VALUES ('10000', '9/7vhk5JKKOTrl7TUw69pRLMdpq6CKaeAPCR40eCUGx8AymylkPAfYFSmOisVgJdYcrl2nIjIV+CUki4qZ8GEw==', '未认证', '139*****387', '13990949387', 'XAmG+Q/yrMfaItEldcr/Qg==', '0', '1', 'http://192.168.6.199:8080/imgs/icons/defaultheadportrait.png', '99999000');
INSERT INTO `t_users` VALUES ('10021', 'caXHgfNwJWuUyy6/o5SH/I4jKnsSjO1DPIIpwSqqkG4vzmNX1p2E0jMBTm/FoE69PkVORVEPSbn6aoZQUGVo6g==', '未认证', '未设置', '111111', 'DPrbB7XRaYH4UkWILExtWg==', '0', '1', 'http://192.168.6.199:8080/imgs/icons/defaultheadportrait.png', '0');
INSERT INTO `t_users` VALUES ('10022', '8cku5lFBPIION0kPLxRzuVAl+87PrtI4YXVyhVt2vFKLJCpgx3noR3ikVx2Vw4HQx2yMIYg1qj6In2f1uNk96w==', '未认证', '未设置', '15484', 'O2Oi1E9i2EK3l5KAy+gmRg==', '0', '1', 'http://192.168.6.199:8080/imgs/icons/defaultheadportrait.png', '0');
INSERT INTO `t_users` VALUES ('10023', 'xwvnqTlA76/BX3u9eh4bJOzJZpTcoas41yKmsHDYvH0yYd9Y9MTYVgIFQzHkG8Mz6JsPM5PIymciqPZ3wqii4A==', '未认证', '未设置', '1', '+P0Uk2gKKxmuiS7mFFQw0A==', '0', '1', 'http://192.168.6.199:8080/imgs/icons/defaultheadportrait.png', '0');
INSERT INTO `t_users` VALUES ('10024', 'Tn985fIiehV0GidmpGAN6aXDko1ouuNggJmlU2BTkngK6gNqkFnwyt/1PQX1NYM3fvk+BYuYvNyBuugbHcxtbw==', '未认证', '未设置', '111', '5z5G95qE9pepv2UrnY5Thg==', '0', '1', 'http://192.168.6.199:8080/imgs/icons/defaultheadportrait.png', '0');
INSERT INTO `t_users` VALUES ('10025', 'L7BIvPrmXFuBZIq0EOhOpVGiH52PmWfcARAW1kdkVSLjunpShE/pe70bA5QmQiK1ozRSwQQNM+dGhlUHtX6Ixg==', '未认证', '未设置', '11', 'Rq/WzDxt/o+mtUgoHxE46w==', '0', '1', 'http://192.168.6.199:8080/imgs/icons/defaultheadportrait.png', '0');
INSERT INTO `t_users` VALUES ('10026', 'XNUwLEUaw8ExdDkjFTnqTPW/OYezyUqClhf6Tu4jgOGFZQ9G+BSiFDBdMEGxwX/yNvbXS+CDBlFLLqecug+Lfw==', '未认证', '未设置', '213132', 'txl5qogtMF64KS48H+m2Vw==', '0', '1', 'http://192.168.6.199:8080/imgs/icons/defaultheadportrait.png', '0');
INSERT INTO `t_users` VALUES ('10027', 'ZpEj6LWQF/2y6m8nPYG+m28Co85zzaYHsnbj5d7U3cQ6wycZlfJ6BRRSZ9TQ0rRquqYVWmmFYYKie1BFpbjwZw==', '未认证', '未设置', '1111111', 'pWRf/EZmyPZ/CGMDaY0HIQ==', '0', '1', 'http://192.168.6.199:8080/imgs/icons/defaultheadportrait.png', '0');
INSERT INTO `t_users` VALUES ('10028', '+SER3gFeF10pvXByq+bh1yXnIK5aUof/xNGecQwv7NHybyJZYrfx30ltzVdp+jJuQ9B6GzjgicGBL6HknlzqDQ==', '未认证', '未设置', '213', 'RaTT3PFKxxnkEcORUGW2pA==', '0', '1', 'http://192.168.6.199:8080/imgs/icons/defaultheadportrait.png', '0');
INSERT INTO `t_users` VALUES ('10029', 'ceLQ84tXAK0LCiY8bPv5J0qFdSQ4gR6+0Elnsji7xQNE+1w1j3tixxe06ZHGrigh8sM4c8ilLQr5YNLXe63BeA==', '未认证', '180*****259', '18010612259', 'CVPILubB5vmiYRgRyVoXaA==', '0', '1', 'http://192.168.6.199:8080/imgs/icons/defaultheadportrait.png', '0');
