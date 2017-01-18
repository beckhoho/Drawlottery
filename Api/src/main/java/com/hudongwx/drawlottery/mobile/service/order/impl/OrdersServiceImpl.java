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
            这是我自己添加的订单添加方法》》》》》
         */
        Long date = new Date().getTime();
        orders.setUserAccountId(accountId);
        orders.setSubmitDate(date);
        int i = mapper.insert(orders);//生成订单
        List<Orders> orderses = mapper.selectByUserDate(orders.getUserAccountId(), date);
        Orders orders1 = orderses.get(0);

        /*
            先使用红包支付，红包>=购买数额则红包作废，其余购买金额作为闪币存入
         */

        int price = orders.getPrice();//70
        //实际购买量
        int TotalNum = updateCommodity(accountId,orders1,commodityAmounts);//3,5
        //余额更改量
        int changeNum;
        //红包面额
        int redNum;

        int tempNum = 0;
        //红包
        Long packetId = orders.getRedPacketId();

        if(packetId != 0 && packetId != null ){ // 如果红包ID不为空
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
        u.setGoldNumber(u.getGoldNumber()+changeNum);

        if(userMapper.updateByPrimaryKeySelective(u) > 0 && i>0){
            return orders1.getId();
        }
        else{
            return 0l;
        }
    }

    /**
     * 生成商品订单和幸运码，返回实际购买量
     *
     *
     *
     *
     * @param orders

     * @return
     */
    public int updateCommodity(Long accountId,Orders orders, List<CommodityAmount> commodityAmounts) {
        //商品剩余量
        int remainingNum;
        //客户购买单个商品数量
        int Amount;

        //商品实际可购买总量
        int TotalNum = 0;

        //客户实际可购买单个商品数量
        int buyNum = 0;

        for (CommodityAmount ca : commodityAmounts) {
            //获取商品信息

            Commoditys byKey = comMapper.selectByKey(ca.getCommodityId());
            remainingNum=byKey.getBuyTotalNumber()-byKey.getBuyCurrentNumber();
            Amount =ca.getAmount();
            //计算购买量和剩余量差值

            int sub = Amount - remainingNum;
            if(sub>=0){
                buyNum = remainingNum;
            }else {
                buyNum = Amount;
            }
            updateLuckCodes(accountId,ca.getCommodityId(), buyNum, orders);
            Commoditys commodity = comMapper.selectByKey(ca.getCommodityId());
            Commodity com = new Commodity();//**

            remainingNum = commodity.getBuyTotalNumber() - commodity.getBuyCurrentNumber();

            //生成商品订单
            OrdersCommoditys ordersCommoditys = new OrdersCommoditys();
            ordersCommoditys.setCommodityId(commodity.getId());
            ordersCommoditys.setOrdersId(orders.getId());

            //判断商品是否符合商品最小购买基数
            int minNum = commodity.getMinimum();
            int extraNum = Amount%minNum;
            if(extraNum!=0){
                Amount -= extraNum;
            }

            if (sub >= 0) {
                buyNum = remainingNum;
                com.setBuyCurrentNumber(commodity.getBuyTotalNumber());

                com.setStateId(2);//进入待揭晓状态
                com.setSellOutTime(System.currentTimeMillis());//添加售罄时间
                /*
                    计算开奖幸运码
                 */
                LotteryInfo raffle = LotteryUtils.raffle(templateMapper,codesMapper, userMapper, commodity);
                lotteryInfoMapper.insert(raffle);


                if (commodity.getAutoRound() == 1 && (remainingNum==0 || remainingNum - buyNum == 0)) {
                    //如果商品卖光，自动生成下一期
                    Long aLong = Long.valueOf(commodity.getRoundTime());
                    Commodity comm = new Commodity();
                    comm.setBuyCurrentNumber(0);
                    comm.setStateId(3);
                    comm.setBuyLastNumber(0);
                    comm.setTempId(commodity.getTempId());
                    comm.setRoundTime(commodityService.generateNewRoundTime()+"");
                    comm.setViewNum(0l);
                    comm.setLastRoundTime(aLong+"");
                    commMapper.insert(comm);

//                    //复用商品幸运码
//                    Commodity commod = commMapper.selectOne(comm);
//                    Long id = ca.getCommodityId();
//                    codesMapper.updateNext(null,commod.getId(),null,null,null);
//                    if(remainingNum==0){
//                        ca.setCommodityId(comm.getId());
//                    }

                    com.setId(commodity.getId());
                    comMapper.updateById(com);//提交商品信息
                    TotalNum += buyNum;//累加实际购买量
                    ordersCommoditys.setAmount(buyNum);//设置商品订单表购买数量
                    orderMapper.insert(ordersCommoditys);//添加商品订单信息

                    //下一期请求
                    continue;
                }
                /*
//                    下期请求
                 */
            } else {
                buyNum = Amount;
                int s = commodity.getBuyCurrentNumber() + buyNum;
                com.setBuyCurrentNumber(s);
            }
            com.setId(commodity.getId());


            int updateById = comMapper.updateById(com);//提交商品信息
            System.out.println(updateById);
            TotalNum = TotalNum+buyNum+extraNum;//累加实际购买量
            ordersCommoditys.setAmount(buyNum);//设置商品订单表购买数量
            int insert = orderMapper.insert(ordersCommoditys);//添加商品订单信息
            System.out.println(insert);
/*
                用户获得幸运码
             */


        }
        return TotalNum;

    }

    /**
     * 生成幸运码
     *
     * @param commodityId
     * @param buyNum
     */
    public boolean updateLuckCodes(Long accountid,long commodityId, int buyNum, Orders orders) {
        //获取商品未使用幸运码
        Date date = new Date();
        int i = codesMapper.updateCodesState(accountid, commodityId, orders.getId(),
                date.getTime(), buyNum);//自定义动态更新sql
        return i>0;
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
    public Map<String, Object> selectPaySuccess(Long accountId,Long orderId ,JSONObject jsonObject) {
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
            map.put("luckCodes", luckCodes(accountId, ca.getCommodityId(),orderId));//用户参与商品的幸运码
            number += ca.getAmount();
            mapList.add(map);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("overallNumber", number);//添加总购买人次
        map.put("overallCommodity", commodityAmounts.size());//添加购买商品总数
        map.put("list",mapList);
        return map;

    }

    //查询用户当前订单参与商品的所有幸运号
    public List<String> luckCodes(Long accountId, Long commodityId,Long ordersId) {
        List<String> list = new ArrayList<>();
        List<UserCodesHistory> list1 = userHistoryMapper.selectByOrders(accountId,commodityId,ordersId);
        for (UserCodesHistory luckCodes : list1) {
            UserCodesHistory codes1 = userHistoryMapper.selectById(luckCodes.getId());
            Long templateId = codes1.getLuckCodeTemplateId();
            LuckCodeTemplate template = templateMapper.selectById(templateId);
            list.add(template.getLuckCode());
        }
        return list;
    }

}
