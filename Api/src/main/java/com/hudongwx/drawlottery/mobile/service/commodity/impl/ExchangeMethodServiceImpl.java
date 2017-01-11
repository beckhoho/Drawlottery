package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.CommodityHistory;
import com.hudongwx.drawlottery.mobile.entitys.ExchangeWay;
import com.hudongwx.drawlottery.mobile.entitys.VirtualCommodity;
import com.hudongwx.drawlottery.mobile.mappers.CommodityHistoryMapper;
import com.hudongwx.drawlottery.mobile.mappers.ExchangeWayMapper;
import com.hudongwx.drawlottery.mobile.mappers.VirtualCommodityMapper;
import com.hudongwx.drawlottery.mobile.service.commodity.IExchangeMethodService;
import com.hudongwx.drawlottery.mobile.utils.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
@Service
public class ExchangeMethodServiceImpl implements IExchangeMethodService {

    @Autowired
    ExchangeWayMapper ewMapper;
    @Autowired
    CommodityHistoryMapper chMapper;
    @Autowired
    VirtualCommodityMapper vcMapper;

    @Override
    public boolean exchangeToGold(Long commId) {
        return false;
    }

    @Override
    public boolean exchangeToCash(JSONObject jsonObject) {
        return false;
    }

    /**
     * 查看user抽中的充值卡
     *
     * @param accountId   用户ID
     * @param commodityId 商品ID
     * @return 返回接口
     */
    @Override
    public List<Map<String, Object>> selectUserRechargeCardPrize(Long accountId, Long commodityId) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        CommodityHistory ch = new CommodityHistory();
        ch.setLuckUserAccountId(accountId);
        ch.setCommodityId(commodityId);
        List<CommodityHistory> list = chMapper.select(ch);
        for (CommodityHistory comHis : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("commodityName", comHis.getCommodityName());//添加商品名
            map.put("roundTime", comHis.getRoundTime());//添加期数
            map.put("commodityId", comHis.getId());//添加商品ID
            map.put("endTime", comHis.getEndTime());//添加揭晓时间；
            map.put("coverImgUrl", Settings.SERVER_URL_PATH + comHis.getCoverImgUrl());//添加商品封面图
            map.put("exchangeState", comHis.getExchangeState());
            //添加兑换状态（1：已选择兑换方式，2：卡密兑换方式派发成功，3：商品派发，4：晒单）
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 查看充值卡兑奖流程进度
     *
     * @param commodityId 商品ID
     * @return
     */
    @Override
    public Map<String, Object> selectUserRechargeCardExchangeProcess(Long accountId, Long commodityId) {
        Map<String, Object> map = new HashMap<>();
        CommodityHistory ch = new CommodityHistory();
        ch.setCommodityId(commodityId);
        ch.setLuckUserAccountId(accountId);
        List<CommodityHistory> list = chMapper.select(ch);
        ExchangeWay way = ewMapper.selectByPrimaryKey(1);
        CommodityHistory history = list.get(0);
        map.put("commodityName", history.getCommodityName());//商品名
        map.put("coverImgUrl", Settings.SERVER_URL_PATH + history.getCoverImgUrl());//商品封面图
        map.put("exchangeState", history.getExchangeState());//兑奖流程进度状态
        map.put("exchangeName", way.getName());//兑换方式名
        map.put("userBuyNumber", history.getBuyNumber());
        map.putAll(demo2(commodityId, map));
        return map;
    }

    public Map<String, Object> demo2(Long commodityId, Map<String, Object> map) {
        VirtualCommodity v = new VirtualCommodity();
        v.setCommodityId(commodityId);
        List<VirtualCommodity> vc = vcMapper.select(v);
        map.put("size", vc.size());//添加有几张充值卡
        int worth = 0;
        List<Map<String, Object>> list = new ArrayList<>();
        for (VirtualCommodity vir : vc) {
            String pwd;
            worth += vir.getWorth();
            Map<String, Object> map1 = new HashMap<>();
            map1.put("cardNumber", vir.getCardNumber());
            if (vir.getState().intValue() == Settings.PASSWORD_VIEWED) {
                pwd = vir.getPassword();
            } else {
                pwd = null;
            }
            map1.put("password", pwd);
            map1.put("state", vir.getState());
            list.add(map1);
        }
        map.put("worth", worth);//添加面额
        map.put("cardList", list);
        return map;
    }
}
