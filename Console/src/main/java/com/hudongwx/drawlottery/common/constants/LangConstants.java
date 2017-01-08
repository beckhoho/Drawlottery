package com.hudongwx.drawlottery.common.constants;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * Drawlottery.
 * Date: 2017/1/8 0008
 * Time: 1:03
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Component
public class LangConstants {
    @Resource
    private MessageSource messageSource;

    /**
     * 获取语言
     *
     * @param keys 关键字参数，当有多个参数是，得到的字段会以不同语言的逗号分隔符隔开
     * @return 语言信息
     */
    public String getLang(String... keys) {
        StringBuilder stringBuilder = new StringBuilder();
        final Locale locale = LocaleContextHolder.getLocale();
        for (String key : keys) {
            stringBuilder.append(",");
            stringBuilder.append(messageSource.getMessage(key, null, "未找到相关字段", locale));
        }
        if (keys.length > 0)
            stringBuilder.deleteCharAt(0);
        return stringBuilder.toString();
    }

    /**
     * 常量字段 添加 .
     */
    public final String ADD = "ADD";
    /**
     * 常量字段 修改 .
     */
    public final String UPDATE = "UPDATE";

    /**
     * 常量字段 删除 .
     */
    public final String DELETE = "DELETE";
    /**
     * 常量字段 查询 .
     */
    public final String QUERY = "QUERY";

    /**
     * 常量字段 得到 .
     */
    public final String GET = "GET";

    /**
     * 常量字段 成功 .
     */
    public final String SUCCESS = "SUCCESS";

    /**
     * 常量字段 失败 .
     */
    public final String FAIL = "FAIL";

    /**
     * 常量字段 商品 .
     */
    public final String COMMODITY = "COMMODITY";

    /**
     * 常量字段 保存 .
     */
    public final String SAVE = "SAVE";

    /**
     * 常量字段 上架 .
     */
    public final String GROUND = "GROUND";

    /**
     * 常量字段 下架 .
     */
    public final String UNDERCARRIAGE = "UNDERCARRIAGE";

    /**
     * 常量字段 添加商品成功 .
     */
    public final String ADD_COMMODITY_SUCCESS = "ADD_COMMODITY_SUCCESS";

    /**
     * 常量字段 修改商品成功 .
     */
    public final String UPDATE_COMMODITY_SUCCESS = "UPDATE_COMMODITY_SUCCESS";

    /**
     * 常量字段 上架商品成功 .
     */
    public final String GROUND_COMMODITY_SUCCESS = "GROUND_COMMODITY_SUCCESS";

    /**
     * 常量字段 下架商品成功 .
     */
    public final String UNDERCARRIAGE_COMMODITY_SUCCESS = "UNDERCARRIAGE_COMMODITY_SUCCESS";

    /**
     * 常量字段 首页 .
     */
    public final String MAIN = "MAIN";

    /**
     * 常量字段 商品管理 .
     */
    public final String COMMODITY_MANAGEMENT = "COMMODITY_MANAGEMENT";

    /**
     * 常量字段 订单管理 .
     */
    public final String ORDER_MANAGEMENT = "ORDER_MANAGEMENT";

    /**
     * 常量字段 用户管理 .
     */
    public final String USER_MANAGEMENT = "USER_MANAGEMENT";

    /**
     * 常量字段 晒单管理 .
     */
    public final String SHARE_MANAGEMENT = "SHARE_MANAGEMENT";

    /**
     * 常量字段 积分管理 .
     */
    public final String INTEGRAL_MANAGEMENT = "INTEGRAL_MANAGEMENT";

    /**
     * 常量字段 广告管理 .
     */
    public final String AD_MANAGEMENT = "AD_MANAGEMENT";

    /**
     * 常量字段 留言反馈 .
     */
    public final String FEEDBACK = "FEEDBACK";

    /**
     * 常量字段 消息管理 .
     */
    public final String MESSAGE_MANAGEMENT = "MESSAGE_MANAGEMENT";

}


