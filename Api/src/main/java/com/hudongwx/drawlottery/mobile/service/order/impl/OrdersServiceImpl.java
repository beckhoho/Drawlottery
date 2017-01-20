package com.hudongwx.drawlottery.mobile.service.order.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.*;
import com.hudongwx.drawlottery.mobile.mappers.*;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityService;
import com.hudongwx.drawlottery.mobile.service.order.IOrdersService;
import com.hudongwx.drawlottery.mobile.utils.LotteryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     *
     * @param accountId
     * @param orders
     * @return
     */
    @Override
    public Long pay(Long accountId, Orders orders, List<CommodityAmount> commodityAmounts) {
         /*
           1、直接写入订单信息
         */
        Long date = new Date().getTime();
        orders.setUserAccountId(accountId);
        orders.setSubmitDate(date);
        mapper.insertUseGenerated(orders);     //有问题！！！！
        List<Commoditys> commodityses = new ArrayList<>();
        for (CommodityAmount ca : commodityAmounts) {
            //获取商品信息
            Commoditys byKey = comMapper.selectByKey(ca.getCommodityId());
            int remainingNum = byKey.getBuyTotalNumber() - byKey.getBuyCurrentNumber();
            if(remainingNum==0){
                continue;
            }
            int buyNum = 0;

            if (ca.getAmount() - remainingNum >= 0) {   //判定条件有问题
                buyNum = remainingNum;
            } else {
                buyNum = ca.getAmount();
            }

            /*
                为用户生成幸运码
             */
            updateLuckCodes(accountId, ca.getCommodityId(), buyNum, orders);
            commodityses.add(byKey);
        }
        ordersServiceImplAsync.payAsync(accountId,orders,commodityAmounts,commodityses);
        return orders.getId();
    }


    /**
     * 生成幸运码
     *
     * @param commodityId
     * @param buyNum
     */
    public boolean updateLuckCodes(Long accountid, long commodityId, int buyNum, Orders orders) {
        //获取商品未使用幸运码
        Date date = new Date();
        int i = codesMapper.updateCodesState(accountid, commodityId, orders.getId(),
                date.getTime(), buyNum);//自定义动态更新sql
        System.out.println("codes:"+i);
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
    public Map<String, Object> selectOrders(Long accountId, Integer sum) {
        Map<String, Object> m = new HashMap<>();
        List<Long> idList = new ArrayList<>();
        User user = userMapper.selectById(accountId);
        m.put("remainder", user.getGoldNumber());//获得用户账户余额
        List<RedPackets> list = redMapper.selectByAccount(accountId);
        for (RedPackets r : list) {
            if (r.getUsePrice() < sum) {
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
    public Map<String, Object> selectPaySuccess(Long accountId, Long orderId, JSONObject jsonObject) {
        List<CommodityAmount> commodityAmounts = new ArrayList<>();
        JSONArray caJArray = jsonObject.getJSONArray("ca");
        for (int i = 0; i < caJArray.size(); i++) {
            commodityAmounts.add(JSONObject.toJavaObject(caJArray.getJSONObject(i), CommodityAmount.class));
        }
        List<Map<String, Object>> mapList = new ArrayList<>();
        Integer number = 0;
        for (CommodityAmount ca : commodityAmounts) {
            Map<String, Object> map = new HashMap<>();
            map.put("amount", ca.getAmount());//参与人次
            Commoditys commoditys = comMapper.selectByKey(ca.getCommodityId());
            map.put("commodityName", commoditys.getName());//商品名
            map.put("roundTime", commoditys.getRoundTime());//期数
            //if (commoditys.getStateId() == 3) {
                map.put("luckCodes", luckCodes(accountId, ca.getCommodityId(), orderId));//用户参与商品的幸运码
//            } else {
//                map.put("luckCodes", luckCodesHistory(accountId, ca.getCommodityId(), orderId));//用户参与商品的幸运码
//            }

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
    public List<String> luckCodesHistory(Long accountId, Long commodityId, Long ordersId) {
        List<String> list = new ArrayList<>();
        List<UserCodesHistory> list1 = userHistoryMapper.selectByOrders(accountId, commodityId, ordersId);
        for (UserCodesHistory luckCodes : list1) {
            UserCodesHistory codes1 = userHistoryMapper.selectById(luckCodes.getId());
            Long templateId = codes1.getLuckCodeTemplateId();
            LuckCodeTemplate template = templateMapper.selectById(templateId);
            list.add(template.getLuckCode());
        }
        return list;
    }

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
