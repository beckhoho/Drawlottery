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
     * 上拉刷新标记
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
     * 数据库获取的图片类型(POPULARITY 人气)
     */
    public static final int COMMODITY_ORDER_POPULARITY = 1;

    /**
     * 数据库获取的图片类型(FASTEST 最快)
     */
    public static final int COMMODITY_ORDER_FASTEST = 2;

    /**
     * 数据库获取的图片类型(NEWEST 最新)
     */
    public static final int COMMODITY_ORDER_NEWEST = 3;
    /**
     * 数据库获取的图片类型(HIGH_PRICE 高价)
     */
    public static final int COMMODITY_ORDER_HIGH_PRICE = 4;


}
