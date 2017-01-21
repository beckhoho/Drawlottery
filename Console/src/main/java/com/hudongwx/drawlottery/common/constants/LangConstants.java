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
     * 常量字段 添加成功 .
     */
    public final String ADD_SUCCESS = "ADD_SUCCESS";

    /**
     * 常量字段 修改成功 .
     */
    public final String UPDATE_SUCCESS = "UPDATE_SUCCESS";
    /**
     * 常量字段 删除成功 .
     */
    public final String DELETE_SUCCESS = "DELETE_SUCCESS";

    /**
     * 常量字段 上架商品成功 .
     */
    public final String GROUND_COMMODITY_SUCCESS = "GROUND_COMMODITY_SUCCESS";

    /**
     * 常量字段 下架商品成功 .
     */
    public final String UNDERCARRIAGE_COMMODITY_SUCCESS = "UNDERCARRIAGE_COMMODITY_SUCCESS";

    /**
     * 常量字段 下架时间不能为空 .
     */
    public final String GROUND_TIME_NOT_NULL = "GROUND_TIME_NOT_NULL";
    /**
     * 常量字段 标题不能为空 .
     */
    public final String TITLE_IS_NOT_NULL = "TITLE_IS_NOT_NULL";
    /**
     * 常量字段 必须有分类 .
     */
    public final String TYPE_NOT_NULL = "TYPE_NOT_NULL";

    /**
     * 常量字段 属性不能为空 .
     */
    public final String GENRE_NOT_NULL = "GENRE_NOT_NULL";

    /**
     * 常量字段 必须选择至少一种分配方式 .
     */
    public final String MUST_CHOOSE_WAY = "MUST_CHOOSE_WAY";
    /**
     * 常量字段 未做出任何选择 .
     */
    public final String NOT_CHOOSE_ANY_ONE = "NOT_CHOOSE_ANY_ONE";
    /**
     * 常量字段 上传失败 .
     */
    public final String UPLOAD_FAIL = "UPLOAD_FAIL";
    /**
     * 常量字段 上传成功 .
     */
    public final String UPLOAD_SUCCESS = "UPLOAD_SUCCESS";
    
    /**
     * 常量字段 已经上架的商品不能再上架 .
     */
    public final String ALREADY_GROUND = "ALREADY_GROUND";
    /**
     * 常量字段 已经下架的商品不能再下架 .
     */
    public final String ALREADY_UN_GROUND = "ALREADY_UN_GROUND";

    /**
     * 常量字段 未兑奖 .
     */
    public final String EXCHANGE_STATE_NOT = "EXCHANGE_STATE_NOT";
    
    /**
     * 常量字段 已兑奖 .
     */
    public final String EXCHANGE_STATE_ON = "EXCHANGE_STATE_ON";
    
    /**
     * 常量字段 快递名不能为空 .
     */
    public final String DELIVERY_NAME_CAN_NOT_NULL = "DELIVERY_NAME_CAN_NOT_NULL";
    
    /**
     * 常量字段 快递单号不能为空 .
     */
    public final String DELIVERY_NUMBER_CAN_NOT_NULL = "DELIVERY_NUMBER_CAN_NOT_NULL";

    /**
     * 常量字段 发货成功 .
     */
    public final String DELIVERY_SUCCESS = "DELIVERY_SUCCESS";
}


