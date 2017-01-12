package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.ShoppingCart;
import com.hudongwx.drawlottery.mobile.service.user.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;
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
 * 创建　kiter　2016/12/31 16:23　<br/>
 * <p>
 *          购物车测试类
 * <p>
 * @email 346905702@qq.com
 */
public class ShoppingCartServiceImplTest extends TestBaseMapper {

    @Autowired
    IShoppingCartService service;

    @Test
    public void testAddCommodityToCart() throws Exception {
        ShoppingCart s = new ShoppingCart();
        s.setUserAccountId(2l);
        s.setCommodityId(10l);
        s.setNumber(10);
        s.setAddDate(new Date().getTime());
        boolean b = service.addCommodityToCart(1L,1L,1);
        Assert.assertTrue(b);
    }

    @Test
    public void testDeleteCommodity() throws Exception {

    }

    @Test
    public void testUpdateCommodity() throws Exception {

    }

    @Test
    public void testSelectByAccount() throws Exception {
        List<Map<String, Object>> mapList = service.selectByAccount(2l);
        for (Map<String, Object> map: mapList){
            System.out.println(map.get("commodityName"));
        }

    }

}