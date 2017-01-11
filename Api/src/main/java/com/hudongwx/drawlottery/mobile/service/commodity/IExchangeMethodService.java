package com.hudongwx.drawlottery.mobile.service.commodity;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2017/1/11 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2017/1/11 <br/>
 * <p>
 * 用户收货地址
 * <p>
 * @email 294786949@qq.com
 */
public interface IExchangeMethodService {

    /**
     * 兑换成闪币
     *
     * @param commId
     * @return
     */
    boolean exchangeToGold(Long commId);

    /**
     * 兑换现金
     *
     * @param jsonObject
     * @return
     */
    boolean exchangeToCash(JSONObject jsonObject);

    /**
     * 查看user抽中的充值卡
     *
     * @param accountId   用户ID
     * @param commodityId 商品ID
     * @return 返回接口
     */
    List<Map<String, Object>> selectUserRechargeCardPrize(Long accountId, Long commodityId);

    /**
     * 兑换充值卡流程
     *
     * @param accountId
     * @param commodityId
     * @return
     */
    Map<String, Object> selectUserRechargeCardExchangeProcess(Long accountId, Long commodityId);

}
