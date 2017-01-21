package com.hudongwx.drawlottery.mobile.service.commodity;

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
     * 兑换现金
     * @param exchangeTerraceName
     * @param TerraceAccount
     * @param commodity
     * @param accountID
     * @return
     */
    boolean exchangeToCash(String exchangeTerraceName, String TerraceAccount, Long commodity,Long accountID);


    /**
     * 查看user抽中的充值卡
     *
     * @param accountId   用户ID
     * @param commodityId 商品ID
     * @return 返回接口
     */
    List<Map<String, Object>> selectUserRechargeCardPrize(Long accountId, Long commodityId);

    /**
     * 兑换流程进度
     *
     * @param accountId
     * @param commodityId
     * @return
     */
    Map<String, Object> selectUserRechargeCardExchangeProcess(Long accountId, Long commodityId);


    //兑换充值卡
    boolean orTrue(Long accountId, Long commodityId);

    //兑换充值卡
    Map<String,Object> temp1(Long accountId,Long commodityId);

    //快递领取
    Map<String,Object> temp2(Long accountId,Long commodityId,Long addressId);

    //兑换现金
    Map<String,Object> temp3(Long accountId,Long commodityId);

    //兑换闪币
    boolean temp4(Long accountId,Long commodityId);

    //到店领取
    Map<String,Object> temp5(Long accountId,Long commodityId);

    //查看卡密
    String cardPassword(String cardNumber);
}
