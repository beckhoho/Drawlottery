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
        int price = orders.getPrice();
        //总共需要花费的金额 （商品单价始终为1）
        int sumPrice = 0;
        for (CommodityAmount ca : commodityAmounts) {
            Commoditys currentCommodity = comMapper.selectByKey(ca.getCommodityId());
            if(currentCommodity.getStateId() != Commodity.ON_SELL){
                currentCommodity = commodityService.selectOnSellCommodities(ca.getCommodityId());
                if(null == currentCommodity)
                    throw new NullPointerException("未获取到商品");
            }
            final Integer remainNum = currentCommodity.getBuyTotalNumber() - currentCommodity.getBuyCurrentNumber();
            Integer amount = ca.getAmount();
            sumPrice += amount;

            //调整商品购买数量
            amount -= amount % currentCommodity.getMinimum();
            //购买量与剩余量差值
            final int subNum = amount - remainNum;

            final OrdersCommoditys ordersCommoditys = new OrdersCommoditys(currentCommodity.getId(), orders.getId());

            if (subNum >= 0) {
                final long now = System.currentTimeMillis();
                currentCommodity.setSellOutTime(now);
                if (currentCommodity.getAutoRound() == 1) {
                    final Commodity nextCommodity = commodityService.groundNext(currentCommodity.getId());
                    commMapper.updateBuyCurrentNum(nextCommodity.getId(), subNum);
                    if(subNum > 0){
                        final OrdersCommoditys ordersCommoditys1 = new OrdersCommoditys(nextCommodity.getId(), orders.getId());
                        ordersCommoditys1.setAmount(subNum);
                        orderMapper.insert(ordersCommoditys1);
                    }
                }
                LotteryUtils.raffle(npMapper, commMapper, comMapper, mapper, templateMapper, codesMapper, lotteryInfoMapper, userMapper, currentCommodity);
                //商品售罄开奖，更新商品信息
                Commodity temp = new Commodity();
                temp.setSellOutTime(now);
                temp.setStateId(Commodity.SELL_OUT);
                temp.setId(currentCommodity.getId());
                commMapper.updateById(temp);
            }

            int s = currentCommodity.getBuyCurrentNumber() + Math.min(amount,remainNum);
            commMapper.updateBuyCurrentNum(currentCommodity.getId(), s);

            ordersCommoditys.setAmount(amount);//设置商品订单表购买数量
            orderMapper.insert(ordersCommoditys);//添加商品订单信息

        }

        //余额更改量
        int changeNum;
        //红包面额
        int redNum;

        int tempNum = 0;
        //红包
        Long packetId = orders.getRedPacketId();

        if (packetId != null && packetId != 0) { // 如果红包ID不为空
            RedPackets red = new RedPackets();
            red.setId(orders.getRedPacketId());
            //查询红包面值
            RedPackets redPackets = redMapper.selectByPrimaryKey(orders.getRedPacketId());
            redNum = redPackets.getWorth();

            if (sumPrice != 0) {//有购买量则使用红包
                //更改红包使用状态
                red.setUseState(1);
                redMapper.updateByPrimaryKeySelective(red);
                sumPrice -= redNum;
                if (redNum >= sumPrice) {//红包数额大于购买量
                    sumPrice = 0;
                    tempNum = redNum;
                }
            }
        }

        if (orders.getPayModeId() == 1) {//使用余额付款方式
            changeNum = -sumPrice;
        } else {
            changeNum = price - sumPrice - tempNum;
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
