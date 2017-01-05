package com.hudongwx.drawlottery.common.utils;

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

    /**
     * 用户正常状态
     */
    public static final String USER_HEAD_PORTRAIT_URL_DEFAULT = "http://192.168.6.199:8080/imgs/icons/defaultheadportrait.png";

    /**
     * 用户正常状态
     */
    public static final int USER_STATE_NORMAL = 1;

    /**
     * 用户锁定
     */
    public static final int USER_STATA_LOCKED = -1;

    /**
     * 状态可用
     */
    public static final int STATE_AVAILABLE = 1;

    /**
     * 状态不可用
     */
    public static final int STATE_DISABLE = 0;

    /**
     * 用户最大添加的地址数量
     */
    public static final int ADDRESS_ADD_MAX = 5;

    /**
     * 下拉刷新每页的最大小数
     */
    public static final int PAGE_LOAD_SIZE = 10;

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
     * 商品未开奖
     */
    public static final int COMMODITY_STATE_NO_LOTTERY = 0;

    /**
     * 商品已开奖
     */
    public static final int COMMODITY_STATE_HAS_LOTTERY = 1;

    /**
     * 商品售罄开奖中
     */
    public static final int COMMODITY_STATE_ON_LOTTERY = 2;

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

    /**
     * 设置session时长
     */
    public static final long SESSION_TIME_OUT = 1000 * 60 * 1;

    public static final long ONE_DAY_LONG_VALUE = 1000 * 60 * 60 * 24;

}
