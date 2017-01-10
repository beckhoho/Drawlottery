package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.entitys.CommodityHistory;
import com.hudongwx.drawlottery.mobile.entitys.ExchangeWay;
import com.hudongwx.drawlottery.mobile.entitys.VirtualCommodity;
import com.hudongwx.drawlottery.mobile.mappers.CommodityHistoryMapper;
import com.hudongwx.drawlottery.mobile.mappers.ExchangeWayMapper;
import com.hudongwx.drawlottery.mobile.mappers.VirtualCommodityMapper;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityHistoryService;
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
 * @author Kiter
 * @version 1.0, 2017/1/10 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/10 11:46　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
@Service
public class CommodityHistoryServiceImpl implements ICommodityHistoryService{

    @Autowired
    CommodityHistoryMapper mapper;
    @Autowired
    ExchangeWayMapper exMapper;
    @Autowired
    VirtualCommodityMapper virtMapper;
    @Override
    public boolean addCommodHistory(CommodityHistory commodityHistory) {
        return false;
    }

    @Override
    public CommodityHistory selectHistoryCommod() {
        return null;
    }

    /**
     * 查看user充值卡
     * @param accountId 用户ID
     * @param commodityID   商品ID
     * @return  返回接口
     */
    @Override
    public List<Map<String, Object>> selectCard(Long accountId, Long commodityID) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        CommodityHistory ch = new CommodityHistory();
        ch.setLuckUserAccountId(accountId);
        ch.setCommodityId(commodityID);
        List<CommodityHistory> list = mapper.select(ch);
        for (CommodityHistory comHis : list){
            Map<String,Object> map = new HashMap<>();
            map.put("commodityName",comHis.getCommodityName());//添加商品名
            map.put("roundTime",comHis.getRoundTime());//添加期数
            map.put("commodityId",comHis.getId());//添加商品ID
            map.put("endTime",comHis.getEndTime());//添加揭晓时间；
            map.put("coverImgUrl",comHis.getCoverImgUrl());//添加商品封面图
            map.put("exchangeState",comHis.getExchangeState());
            //添加兑换状态（1：已选择兑换方式，2：卡密兑换方式派发成功，3：商品派发成功，4：晒单成功）
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 查看兑奖流程进度
     * @param commodityId   商品ID
     * @return
     */
    @Override
    public List<Map<String, Object>> selectUserPrize(Long commodityId) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        CommodityHistory ch = new CommodityHistory();
        ch.setCommodityId(commodityId);
        List<CommodityHistory> list = mapper.select(ch);
        ExchangeWay way = exMapper.selectByPrimaryKey(1);
        Map<String,Object> map = new HashMap<>();
        CommodityHistory history = list.get(0);
        map.put("commodityName",history.getCommodityName());//商品名
        map.put("coverImgUrl",history.getCoverImgUrl());//商品封面图
        map.put("exchangeState",history.getExchangeState());//兑奖流程进度状态
        map.put("exchangeName",way.getName());//兑换方式名
        mapList.add(map);
        mapList.addAll(demo2(commodityId));
        return mapList;
    }

    public List<Map<String, Object>> demo2(Long commodityId){
        List<Map<String, Object>> mapList = new ArrayList<>();
        VirtualCommodity v = new VirtualCommodity();
        v.setCommodityId(commodityId);
        List<VirtualCommodity> select = virtMapper.select(v);
        for (VirtualCommodity vir : select){
            Map<String,Object> map = new HashMap<>();
            map.put("cardNumber",vir.getCardNumber());//添加卡号
            map.put("password",vir.getPassword());//添加密码；
            mapList.add(map);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("size",select.size());//添加有几张充值卡
        map.put("worth",select.get(0).getWorth());//添加面额
        mapList.add(map);
        return mapList;
    }

}
