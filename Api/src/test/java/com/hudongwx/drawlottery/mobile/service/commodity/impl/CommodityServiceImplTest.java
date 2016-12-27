package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/24 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/24 10:00　<br/>
 * <p>
 *
 * <p>
 * @email 346905702@qq.com
 */
public class CommodityServiceImplTest extends TestBaseMapper {

    @Autowired
    ICommodityService commod;
    @Test
    public void testAddCommodity() throws Exception {
        Commoditys com = new Commoditys();
        com.setName("ip7");
        com.setDesc("最新款iPhone7上线，赶紧来抢！！！");//
        com.setCommodityTypeId(1l);
        com.setGenre(1);
        com.setBuyCurrentNumber(1);
        com.setBuyTotalNumber(4544);
        com.setStartTime(new Date());
        com.setState(1);
        com.setLuckCodeId(1l);
        com.setRoundTime("42223322");
        com.setCoverImgId(20l);
        com.setAutoRound(1);
        com.setCommodityDescUrl("www.com");//
        boolean b = commod.addCommodity(com);
        Assert.assertTrue(b);
    }

    @Test
    public void testDelete() throws Exception {
        boolean delete = commod.delete(1l);
        Assert.assertTrue(delete);
    }

    @Test
    public void testSelectByid() throws Exception {
        Commoditys commoditys = commod.selectByid(1l);
        Assert.assertNotNull(commoditys);
    }

    @Test
    public void testSelectTypeAll() throws Exception {
        List<Commoditys> commodityses = commod.selectAll();
        for (Commoditys com:commodityses){
            System.out.println(com.getBuyCurrentNumber()+"  ;"+com.getName());
        }

    }

    @Test
    public void testUpdate() throws Exception {
        Commoditys com = new Commoditys();
        com.setId(2l);
        com.setBuyCurrentNumber(20);
        boolean update = commod.update(com);
        Assert.assertTrue(update);
    }

    @Test
    public void testSelectCount() throws Exception {
        int i = commod.selectCount(1);
        System.out.println(i);
    }

    @Test
    public void testSelectPaging() throws Exception {
        List<Commoditys> commodityses = commod.selectPaging(1, 0, 1);
        for (Commoditys com:commodityses){
            System.out.println(com.getBuyCurrentNumber()+"  ;"+com.getName());
        }
        Assert.assertNotNull(commodityses);
    }

    @Test
    public void testSelectByName() throws Exception{
        List<Commoditys> i = commod.selectByName("i");
        System.out.println(i.get(0).getName()+"  ;  "+i.get(0).getBuyCurrentNumber());
    }

    @Test
    public void testSelectOn() throws Exception{
        List<Commoditys> commodityses = commod.selectOnLottery();
        Assert.assertNotNull(commodityses);
        System.out.println(commodityses.get(0).getName());
    }
}