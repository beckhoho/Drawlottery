package com.hudongwx.drawlottery.mobile.service.oder.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.*;
import com.hudongwx.drawlottery.mobile.mappers.OrdersMapper;
import com.hudongwx.drawlottery.mobile.mappers.RedPacketsMapper;
import com.hudongwx.drawlottery.mobile.mappers.UserMapper;
import com.hudongwx.drawlottery.mobile.service.oder.IOdersService;
import com.hudongwx.drawlottery.mobile.service.oder.IOrdersCommoditysService;
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
public class OdersServiceImpl implements IOdersService {

    @Autowired
    OrdersMapper mapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedPacketsMapper redMapper;
    @Autowired
    IOrdersCommoditysService ordersCommoditysService;

    /**
     * 添加订单对象
     *
     * @param jsonObject 订单对象
     * @return 返回添加结果
     */
    @Override
    public boolean addOder(JSONObject jsonObject) {
        Orders orders = JSONObject.toJavaObject(jsonObject.getJSONObject("order"), Orders.class);
        JSONArray ca = jsonObject.getJSONArray("ca");
        List<CommodityAmount> caList = new ArrayList<>();
        for (int i = 0; i < ca.size(); i++) {
            caList.add(JSONObject.toJavaObject(ca.getJSONObject(i), CommodityAmount.class));
        }
        int i = mapper.insert(orders);
        List<Orders> list = mapper.select(orders);
        for (int s = 0; s < caList.size(); s++) {
            OrdersCommoditys oc = new OrdersCommoditys();
            oc.setOrdersId(list.get(0).getId());//添加订单ID
            oc.setCommodityId(caList.get(s).getCommodityId());//添加商品ID
            oc.setAmount(caList.get(s).getAmount());//添加购买人次；
            ordersCommoditysService.addOrdersCommodity(oc);
        }
        if (orders.getPayModeId() == 1) {//如果支付方式为余额支付
            User u = userMapper.selectByPrimaryKey(orders.getUserAccountId());
            Integer number = orders.getPrice();
            User user = new User();
            user.setAccountId(orders.getUserAccountId());
            user.setGoldNumber(u.getGoldNumber() - number);
            userMapper.updateByPrimaryKeySelective(user);
        }
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
        User user = userMapper.selectByPrimaryKey(accountId);
        m.put("remainder", user.getGoldNumber());//获得用户账户余额
        RedPackets red = new RedPackets();
        red.setUserAccountId(accountId);
        List<RedPackets> list = redMapper.select(red);
        for (RedPackets r : list) {
            if (r.getUsePrice() < sum) {
                idList.add(r.getId());//红包ID
            }
        }
        m.put("useRedPackets", idList);//添加可使用红包ID
        return m;
    }


}
