package com.hudongwx.drawlottery.mobile.service.order.impl;

import com.hudongwx.drawlottery.mobile.entitys.*;
import com.hudongwx.drawlottery.mobile.mappers.*;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityService;
import com.hudongwx.drawlottery.mobile.service.order.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/22 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/22 19:50　<br/>
 * <p>
 * 订单service实现类
 * <p>
 * @email 346905702@qq.com
 */
@Service
public class OrdersServiceImpl implements IOrdersService {

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
    OrdersServiceImplAsync ordersServiceImplAsync;

    /**
     * 计算扣款
     * 如果购买数大于剩余数量，会购买下一期
     *
     * @param accountId
     * @param orders
     * @return
     * @apiNote 更改逻辑
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Long pay(Long accountId, Orders orders, List<CommodityAmount> commodityAmounts) {

        /*
           1、直接写入订单信息
         */
        Long date = new Date().getTime();
        orders.setUserAccountId(accountId);
        orders.setSubmitDate(date);
        mapper.insertUseGenerated(orders);//写入订单信息，并返回主键ID

        for (CommodityAmount ca : commodityAmounts) {
            //循环遍历获取商品信息
            Commoditys currentCommodity = comMapper.selectByKey(ca.getCommodityId());
            if(currentCommodity.getStateId() != Commodity.ON_SELL){
                currentCommodity = commodityService.selectOnSellCommodities(ca.getCommodityId());
                if(null == currentCommodity)
                    throw new NullPointerException("未获取到商品");
            }
            int remainingNum = currentCommodity.getBuyTotalNumber() - currentCommodity.getBuyCurrentNumber();

            int buyNum = ca.getAmount();
            if (buyNum > remainingNum) {
                Commodity nextCommodity = commodityService.getNextCommodity(currentCommodity.getId());
                updateLuckCodes(accountId, nextCommodity.getId(), buyNum - remainingNum, orders);
                buyNum = remainingNum;
            }

            updateLuckCodes(accountId, currentCommodity.getId(), buyNum, orders);
        }
        ordersServiceImplAsync.payAsync(accountId, orders, commodityAmounts);
        return orders.getId();
    }


    /**
     * 生成幸运码
     *
     * @param commodityId
     * @param buyNum
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean updateLuckCodes(Long accountid, long commodityId, int buyNum, Orders orders) {
        Date date = new Date();
        int i = codesMapper.updateCodesState(accountid, commodityId, orders.getId(),
                date.getTime(), buyNum);//自定义动态更新sql
        System.out.println("codes:" + i);
        return i > 0;
    }


    /**
     * 查询当前用户的所有订单
     *
     * @param userAccount 用户accountID
     * @return 返回当前用户的所有订单信息
     */
    @Override
    public List<Orders> selectByUserAccount(Long userAccount) {
        Orders o = new Orders();
        o.setUserAccountId(userAccount);
        return mapper.select(o);
    }

    /**
     * 通过id删除订单
     *
     * @param id 订单id
     * @return
     */
    @Override
    public boolean deleteOder(Long id) {
        return mapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 通过主键修改订单信息
     *
     * @param oders 订单对象
     * @return 返回修改结果；
     */
    @Override
    public boolean update(Orders oders) {
        return mapper.updateByPrimaryKey(oders) > 0;
    }

    /**
     * 查看用户余额和红包
     *
     * @param accountId 用户ID
     * @param sum       商品总价
     * @return
     */
    @Override
    public Map<String, Object> selectUsableRedPackets(Long accountId, Integer sum) {
        Map<String, Object> m = new HashMap<>();
        List<Long> idList = new ArrayList<>();
        User user = userMapper.selectById(accountId);
        m.put("remainder", user.getGoldNumber());//获得用户账户余额
        List<RedPackets> list = redMapper.selectUserUsableRedPackets(accountId, System.currentTimeMillis());
        for (RedPackets r : list) {
            if (r.getUsePrice() <= sum) {
                idList.add(r.getId());//红包ID
            }
        }
        m.put("useRedPackets", idList);//添加可使用红包ID
        return m;
    }


    /**
     * 支付完成界面
     *
     * @param accountId 用户ID
     * @return
     */
    @Override
    public Map<String, Object> selectPaySuccess(Long accountId, Long orderId) {
        List<CommodityAmount> commodityAmounts = commodityService.selectAmounts(orderId);
        List<Map<String, Object>> mapList = new ArrayList<>();
        Integer number = 0;
        for (CommodityAmount ca : commodityAmounts) {
            Commoditys commoditys = comMapper.selectByKey(ca.getCommodityId());
            Map<String, Object> map = new HashMap<>();
            map.put("commId", ca.getCommodityId());//订单ID
            map.put("amount", ca.getAmount());//参与人次
            map.put("commodityName", commoditys.getName());//商品名
            map.put("roundTime", commoditys.getRoundTime());//期数
            map.put("luckCodes", luckCodes(accountId, ca.getCommodityId(), orderId));//用户参与商品的幸运码
            number += ca.getAmount();
            mapList.add(map);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("overallNumber", number);//添加总购买人次
        map.put("overallCommodity", commodityAmounts.size());//添加购买商品总数
        map.put("list", mapList);
        return map;

    }

    //查询用户当前订单参与商品的所有幸运号
//    public List<String> luckCodesHistory(Long accountId, Long commodityId, Long ordersId) {
//        List<String> list = new ArrayList<>();
//        List<UserCodesHistory> list1 = userHistoryMapper.selectByOrders(accountId, commodityId, ordersId);
//        for (UserCodesHistory luckCodes : list1) {
//            UserCodesHistory codes1 = userHistoryMapper.selectById(luckCodes.getId());
//            Long templateId = codes1.getLuckCodeTemplateId();
//            LuckCodeTemplate template = templateMapper.selectById(templateId);
//            list.add(template.getLuckCode());
//        }
//        return list;
//    }

    //查询用户当前订单参与商品的所有幸运号
    public List<String> luckCodes(Long accountId, Long commodityId, Long ordersId) {
        List<String> list = new ArrayList<>();
        List<LuckCodes> list1 = codesMapper.selectByOrders(accountId, commodityId, ordersId);
        for (LuckCodes luckCodes : list1) {
            LuckCodes codes1 = codesMapper.selectById(luckCodes.getId());
            Long templateId = codes1.getLuckCodeTemplateId();
            LuckCodeTemplate template = templateMapper.selectById(templateId);
            list.add(template.getLuckCode());
        }
        return list;
    }

}
