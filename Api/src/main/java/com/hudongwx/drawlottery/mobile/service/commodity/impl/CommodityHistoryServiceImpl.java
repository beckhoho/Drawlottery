package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.entitys.CommodityHistory;
import com.hudongwx.drawlottery.mobile.entitys.LotteryInfo;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.mappers.*;
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
public class CommodityHistoryServiceImpl implements ICommodityHistoryService {

    @Autowired
    CommodityHistoryMapper chMapper;
    @Autowired
    ExchangeWayMapper exMapper;
    @Autowired
    VirtualCommodityMapper virtMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    CommodityMapper commodityMapper;
    @Autowired
    LotteryInfoMapper lotteryInfoMapper;


//    @Override
//    public List<Map> selectThePastAnnouncedCommList(Long commId) {
//        List<Map> maps = commodityMapper.selectPastLottery(commId);
//
//      /*
//        List<Map<String, Object>> infoList = null;
//        Long aLong = commodityMapper.selectTempIdByCommId(commId);
//        List<CommodityHistory> chList = chMapper.selectByTempId(aLong);
//        if (!chList.isEmpty())
//            infoList = new ArrayList<>();
//        for (CommodityHistory history : chList) {
//            User user = userMapper.selectById(history.getLuckUserAccountId());
//            Map<String, Object> map = new HashMap<>();
//            map.put("commId", history.getCommodityId());//商品id
//            map.put("roundTime", history.getRoundTime());//商品期数
//            map.put("endTime", history.getEndTime());//揭晓时间
//            map.put("UserImgUrl", user.getHeaderUrl());
//            map.put("luckUserName", user.getNickname());//用户昵称
//            map.put("ip", "127.0.0.1");//用户IP
//            map.put("luckCode", history.getLuckCode());//中奖码
//            map.put("userByNum", history.getBuyNumber());//用户当期参与购买量
//            infoList.add(map);
//        }*/
//        return maps;
//    }

    /**
     * 查看该期商品中奖信息
     * @return
     */
    @Override
    public LotteryInfo selectLotteryInfo(Long commId) {
        return lotteryInfoMapper.selectByComId(commId);
    }

}
