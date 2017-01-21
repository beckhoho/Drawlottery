package com.hudongwx.drawlottery.mobile.utils;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2016/12/26 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2016/12/26 <br/>
 * <p>
 * 用户收货地址
 * <p>
 * @email 294786949@qq.com
 */
public class Settings {

    /**↑↓**/

    /**
     * 最大信息条数
     */
    public static final int MAX_INFO_SIZE = 100;

    /**
     * 最大信息条数
     */
    public static final int MAX_INFO_SIZE_05 = 5;

    /**↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*收貨地址*↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓**/
    /**
     * 默认收货地址
     */
    public static final int DELIVERY_ADDRESS_DEFAULT = 1;

    /**
     * 常规地址
     */
    public static final int DELIVERY_ADDRESS_NORMAL = 0;
    /**↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑*收貨地址*↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑**/

    /**↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*用户状态*↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓**/
    /**
     * 用户正常状态
     */
    public static final int USER_STATE_NORMAL = 1;

    /**
     * 用户锁定
     */
    public static final int USER_STATA_LOCKED = -1;
    /**↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑*用户状态*↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑**/

    /**↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*通用状态*↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓**/
    /**
     * 状态可用
     */
    public static final int STATE_AVAILABLE = 1;

    /**
     * 状态不可用
     */
    public static final int STATE_DISABLE = 0;
    /**↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑*通用状态*↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑**/

    /**
     * 用户最大添加的地址数量
     */
    public static final int ADDRESS_ADD_MAX = 3;

    /**
     * 上拉加载每页的最大条数6
     */
    public static final int PAGE_LOAD_SIZE_06 = 6;

    /**
     * 上拉加载每页的最大条数8
     */
    public static final int PAGE_LOAD_SIZE_08 = 8;

    /**
     * 上拉加载每页的最大条数10
     */
    public static final int PAGE_LOAD_SIZE_10 = 10;

    /**
     * 上拉加载每页的最大条数16
     */
    public static final int PAGE_LOAD_SIZE_16 = 16;

    /**
     * 首页显示通知的最大条数
     */
    public static final int NOTIFY_SHOW_MAX = 5;

    /**
     * 初始太数据标记
     */
    public static final int INITIALIZE_ENTER_STATUS = 0;
    /**
     * 下拉刷新标记
     */
    public static final int DROP_DOWN_REFRESH = 1;
    /**
     * 上拉加载标记
     */
    public static final int PULL_TO_REFRESH = 2;

    /**↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*commodity(商品)*↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓**/
    /**
     * 数据库获取的图片类型(ICONS 小图标)
     */
    public static final int IMG_GENRE_ICONS = 1;

    /**
     * 数据库获取的图片类型(ADVERTISEMENT 广告)
     */
    public static final int IMG_GENRE_ADVERTISEMENT = 2;

    /**
     * 按人气获取数据库商品(POPULARITY 人气)
     */
    public static final int COMMODITY_ORDER_POPULARITY = 1;

    /**
     * 按最快获取数据库商品(FASTEST 最快)
     */
    public static final int COMMODITY_ORDER_FASTEST = 2;

    /**
     * 按最新获取数据库商品(NEWEST 最新)
     */
    public static final int COMMODITY_ORDER_NEWEST = 3;
    /**
     * 按高价获取数据库商品(HIGH_PRICE 高价)
     */
    public static final int COMMODITY_ORDER_HIGH_PRICE = 4;

    /**
     * 按最新获取数据库商品(HIGHT_RATE 高中奖率)
     */
    public static final int COMMODITY_ORDER_HIGHT_RATE = 5;

    /**
     * 商品已开奖
     */
    public static final int COMMODITY_STATE_HAS_LOTTERY = 1;

    /**
     * 商品售罄开奖中
     */
    public static final int COMMODITY_STATE_ON_LOTTERY = 2;

    /**
     * 商品售卖中
     */
    public static final int COMMODITY_STATE_ON_SALE = 3;

    /**
     * 商品已兑换完成
     */
    public static final int COMMODITY_STATE_EXCHANGED = 1;

    /**
     * 商品未兑换完成
     */
    public static final int COMMODITY_STATE_UN_EXCHANGED = 0;

    /**↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑*commodity(商品)*↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑**/

    /**↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*verifyCode*↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓**/
    /**
     * 图形验证码图片宽度
     */
    public static final int VERIFY_CODE_IMG_WIDTH = 200;

    /**
     * 图形验证码图片高度
     */
    public static final int VERIFY_CODE_IMG_HEIGHT = 80;

    /**
     * 验证码长度
     */
    public static final int VERIFY_CODE_LENGTH = 4;
    /**↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑*verifyCode*↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑**/

    /**
     * 设置session时长
     */
    public static final long SESSION_TIME_OUT = 1000 * 60 * 1;

    /**
     * 一天的毫秒数
     */
    public static final long ONE_DAY_LONG_VALUE = 1000 * 60 * 60 * 24;

    /**↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*imgPath*↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓**/
    /**
     * 服务外链地址
     */
    public static final String SERVER_URL_PATH = "http://192.168.6.199:8080/";

    /**
     * 商品图片路径
     */
    public static final String IMG_PATH_COMMODITY = "imgs/commodityimg/";

    /**
     * 商品类型图片路径
     */
    public static final String IMG_PATH_COMMODITY_TYPE = "imgs/commoditytype/";

    /**
     * 图标图片路径
     */
    public static final String IMG_PATH_ICONS = "imgs/icons/";

    /**
     * 用户头像图片路径
     */
    public static final String IMG_PATH_USER_PORTRAIT = "imgs/userportrait/";

    /**
     * 用户正常状态
     */
    public static final String USER_PORTRAIT_URL_DEFAULT = SERVER_URL_PATH + IMG_PATH_ICONS + "me_pic_personal.png";

    /**
     * 图标图片路径
     */
    public static final String IMG_PATH_SHARE = "imgs/shareimg/";
    /**↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑*imgPath*↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑**/

    /**↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*redPacket*↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓**/
    /**
     * 未使用红包
     */
    public static final int RED_PACKET_USE_STATE_UNUSED = 0;

    /**
     * 已使用红包
     */
    public static final int RED_PACKET_USE_STATE_USED = 1;

    /**
     * 红包未过期
     */
    public static final int RED_PACKET_overdue_not = 0;

    /**
     * 红包已过期
     */
    public static final int RED_PACKET_overdue = 1;
    /**↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑*redPacket*↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑**/

    /**↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*Prepaid card*↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓**/
    /**
     * 电话卡已派发
     */
    public static final int PREPAID_CARD_STATE_DISPATCHED = 1;

    /**
     * 电话卡未派发
     */
    public static final int PREPAID_CARD_STATE_UN_DISPATCHED = 0;
    /**↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑*Prepaid card*↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑**/

    /**
     * 设置开奖延后时长
     */
    public static final long LOTTERY_ANNOUNCE_TIME_INTERVAL = 1000 * 60 * 1;

    /**
     * 卡密已查看
     */
    public static final int PASSWORD_VIEWED = 1;
    /**
     * 卡密未被查看
     */
    public static final int PASSWORD_NOT_VIEWED = 0;

    /**↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*兑换方式*↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓**/

    /**
     * 商品兑换充值卡
     */
    public static final int EXCHANGE_METHOD_RECHARGE_CARD = 1;

    /**
     * 商品快递接收
     */
    public static final int EXCHANGE_METHOD_EXPRESS = 2;

    /**
     * 商品兑换现金
     */
    public static final int EXCHANGE_METHOD_CASH = 3;

    /**
     * 商品兑换金币
     */
    public static final int EXCHANGE_METHOD_GOLD = 4;

    /**
     * 商品兑换金币
     */
    public static final int EXCHANGE_METHOD_LOCALE = 5;

    /**↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑*兑换方式*↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑**/

    /**↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*订单状态*↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓**/

    /**
     * 商品订单未付款状态
     */
    public static final int ORDERS_NO_PAYMENT = 0;

    /**
     * 商品订单已付款状态
     */
    public static final int ORDERS_ALREADY_PAID = 1;

    /**↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑*订单状态*↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑**/


}
