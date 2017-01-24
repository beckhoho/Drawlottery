package com.hudongwx.drawlottery.mobile.service.order.impl;

import com.hudongwx.drawlottery.mobile.entitys.*;
import com.hudongwx.drawlottery.mobile.mappers.*;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityService;
import com.hudongwx.drawlottery.mobile.utils.LotteryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by 11 on 2017/1/19.
 */
@Component
public class OrdersServiceImplAsync {
    @Autowired
    OrdersMapper mapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedPacketsMapper redMapper;
    @Autowired
    OrdersCommoditysMapper orderMapper;
    @Autowired
    CommoditysMapper comMapper;
    @Autowired
    LuckCodesMapper codesMapper;
    @Autowired
    CommodityMapper commMapper;
    @Autowired
    LotteryInfoMapper lotteryInfoMapper;
    @Resource
    ICommodityService commodityService;
    @Autowired
    LuckCodeTemplateMapper templateMapper;
    @Autowired
    UserCodesHistoryMapper userHistoryMapper;
    @Autowired
    CommodityTemplateMapper templeMapper;
    @Autowired
    CommodityHistoryMapper historyMapper;
    @Autowired
    NotificationPrizeMapper npMapper;

    /*
        订单异步处理方法
        异步处理方法必须和调用方法不在同一个类
     */
    @Async
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void payAsync(Long accountId, Orders orders,
                         List<CommodityAmount> commodityAmounts
    ) {
        /*先使用红包支付，红包>=购买数额则红包作废，其余购买金额作为闪币存入*/
        int price = orders.getPrice();
        /*生成商品订单和幸运码，返回实际购买量*/
        //商品剩余量
        int remainingNum;
        //客户购买单个商品数量
        int Amount;
        //商品实际可购买总量
        int TotalNum = 0;
        int index = 0;
        for (CommodityAmount ca : commodityAmounts) {
//            获取商品信息
            Commoditys currentCommodiy = comMapper.selectByKey(ca.getCommodityId());
            remainingNum = currentCommodiy.getBuyTotalNumber() - currentCommodiy.getBuyCurrentNumber();
            Amount = ca.getAmount();
            TotalNum += Amount;
            //计算购买量和剩余量差值
            int sub = Amount - remainingNum;
            Commodity com = new Commodity();//**
            //生成商品订单
            OrdersCommoditys ordersCommoditys = new OrdersCommoditys();
            ordersCommoditys.setCommodityId(currentCommodiy.getId());
            ordersCommoditys.setOrdersId(orders.getId());
            //判断商品是否符合商品最小购买基数
            int minNum = currentCommodiy.getMinimum();
            int extraNum = Amount % minNum;
            if (extraNum != 0) {
                Amount -= extraNum;
            }
            /*如果用户购买量减去可购买量不为负，用户购买当前可购买量，商品售罄，自动购买下一期*/
            if (sub >= 0) {
                long millis = System.currentTimeMillis();
                com.setBuyCurrentNumber(currentCommodiy.getBuyTotalNumber());
                com.setStateId(2);//进入待揭晓状态
                com.setSellOutTime(millis);//添加售罄时间
                /*如果商品下一期属性为1，生成下一期*/
                if (currentCommodiy.getAutoRound() == 1) {
                    //生成下一期
                    final Commodity nextCommodity = commodityService.groundNext(currentCommodiy.getId());
                    nextCommodity.setBuyCurrentNumber(sub);
                    commMapper.updateById(nextCommodity);
                    com.setId(currentCommodiy.getId());
                    commMapper.updateById(com);//提交商品信息
                    ordersCommoditys.setAmount(Amount);//设置商品订单表购买数量
                    orderMapper.insert(ordersCommoditys);//添加商品订单信息
                    if (remainingNum == 0) {
                        ca.setCommodityId(currentCommodiy.getId());
                        commodityAmounts.add(ca);
                    }
                    /*计算开奖幸运码*/
                    currentCommodiy.setSellOutTime(millis);
                    LotteryUtils.raffle(npMapper, commMapper, comMapper, mapper, templateMapper, codesMapper, lotteryInfoMapper, userMapper, currentCommodiy);
//                    addHistory(ca.getCommodityId());//进入待揭晓状态直接将商品信息写入数据库
                    continue;
                }
            }
            int s = currentCommodiy.getBuyCurrentNumber() + Amount;
            com.setBuyCurrentNumber(s);
            com.setId(currentCommodiy.getId());
            commMapper.updateById(com);//提交商品信息
            ordersCommoditys.setAmount(Amount);//设置商品订单表购买数量
            orderMapper.insert(ordersCommoditys);//添加商品订单信息

        }

        //余额更改量
        int changeNum;
        //红包面额
        int redNum;

        int tempNum = 0;
        //红包
        Long packetId = orders.getRedPacketId();

        if (packetId != 0 && packetId != null) { // 如果红包ID不为空
            RedPackets red = new RedPackets();
            red.setId(orders.getRedPacketId());
            //查询红包面值
            RedPackets redPackets = redMapper.selectByPrimaryKey(orders.getRedPacketId());
            redNum = redPackets.getWorth();

            if (TotalNum != 0) {//有购买量则使用红包
                //更改红包使用状态
                red.setUseState(1);
                redMapper.updateByPrimaryKeySelective(red);
                TotalNum -= redNum;
                if (redNum >= TotalNum) {//红包数额大于购买量
                    TotalNum = 0;
                    tempNum = redNum;
                }
            }
        }

        if (orders.getPayModeId() == 1) {//使用余额付款方式
            changeNum = -TotalNum;
        } else {
            changeNum = price - TotalNum - tempNum;
        }
        User u = userMapper.selectById(accountId);
        u.setGoldNumber(u.getGoldNumber() + changeNum);
        userMapper.updateByPrimaryKeySelective(u);

        //支付成功之后，更改订单支付状态
        orders.setPayState(1);
        mapper.updatePayState(orders.getId(), 1);

    }

    /**
     * 添加历史商品信息
     *
     * @param commodityId 商品ID
     * @return
     */

    @Async
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean addHistory(Long commodityId) {
        Commoditys key = comMapper.selectByKey(commodityId);
        LotteryInfo lotteryInfo = lotteryInfoMapper.selectByComId(commodityId);
        Long lotteryId = lotteryInfo.getLotteryId();
        LuckCodeTemplate byCode = templateMapper.selectByCode(lotteryId + "");
        LuckCodes luckCodes = codesMapper.selectBytemplate(byCode.getId(), commodityId);
        List<LuckCodes> id = codesMapper.selectByUserAccountId(luckCodes.getUserAccountId(), commodityId);

        Commodity com = new Commodity();
        com.setId(key.getId());
        com.setBuyNumber(id.size());
        com.setEndTime(new Date().getTime());
        int i = commMapper.updateByPrimaryKeySelective(com);
        //开奖之后直接更改商品信息

        //int i = userHistoryMapper.insertCopy(commodityId);

        return i > 0;
    }

}
