package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.entitys.ShoppingCart;
import com.hudongwx.drawlottery.mobile.mappers.CommoditysMapper;
import com.hudongwx.drawlottery.mobile.mappers.ShoppingCartMapper;
import com.hudongwx.drawlottery.mobile.service.user.IShareImgService;
import com.hudongwx.drawlottery.mobile.service.user.IShoppingCartService;
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
 * @version 1.0, 2016/12/31 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/31 16:01　<br/>
 * <p>
 *          购物车service实现类
 * <p>
 * @email 346905702@qq.com
 */
@Service
public class ShoppingCartServiceImpl implements IShoppingCartService {

    @Autowired
    ShoppingCartMapper mapper;
    @Autowired
    CommoditysMapper commodMapper;

    /**
     * 添加商品到购物车
     * @param shopping  购物车对象
     * @return  返回添加结果
     */
    @Override
    public boolean addCommodityToCart(ShoppingCart shopping) {
        return mapper.insert(shopping)>0;
    }

    /**
     * 将商品从购物车移除
     * @param shopping  购物车对象
     * @return  返回删除结果
     */
    @Override
    public boolean deleteCommodity(ShoppingCart shopping) {
        return mapper.delete(shopping)>0;
    }

    /**
     * 修改购物车物品
     * @param shoppingCart  购物车对象
     * @return  返回修改结果
     */
    @Override
    public boolean updateCommodity(ShoppingCart shoppingCart) {
        return mapper.updateByPrimaryKeySelective(shoppingCart)>0;
    }

    /**
     * 查看用户个人购物车商品
     * @param accountId 用户  ID
     * @return  返回购物车商品集合
     */
    @Override
    public List<Map<String, Object>> selectByAccount(Long accountId) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        ShoppingCart shopping = new ShoppingCart();
        shopping.setUserAccountId(accountId);
        List<ShoppingCart> list = mapper.select(shopping);
        for (ShoppingCart sc : list){
            Map<String,Object> map = new HashMap<>();
            Commoditys key = commodMapper.selectByPrimaryKey(sc.getCommodityId());
            map.put("commodityName",key.getName());//添加商品名
            map.put("buyCurrentNumber",key.getBuyCurrentNumber());//添加当前购买人次
            map.put("buyTotalNumber",key.getBuyTotalNumber());//添加总购买人次
            map.put("commodityId",key.getId());//添加商品id
            map.put("coverImgUrl",key.getCoverImgUrl());//添加商品封面图
            map.put("payNumber",sc.getNumber());//添加支付人次
            mapList.add(map);
        }
        return mapList;
    }
}
