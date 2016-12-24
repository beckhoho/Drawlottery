package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;

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
 * 什么类？
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
        //com.setDesc("sssss7");//
        com.setCommodityTypeId(1l);
        com.setGenre("1");
        com.setBuyCurrentNumber(1);
        com.setBuyTotalNumber(4544);
        com.setStartTime(new Date());
        com.setState(1);
        com.setLuckCodeId(1l);
        com.setRoundTime("123213");
        com.setCoverImgId(20l);
        com.setAutoRound(1);
        //com.setCommodityDescUrl("www.com");//
        boolean b = commod.addCommodity(com);
        Assert.assertTrue(b);
    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testSelectByid() throws Exception {
        Commoditys commoditys = commod.selectByid(1l);
        Assert.assertNotNull(commoditys);
    }

    @Test
    public void testSelectTypeAll() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testSelectCount() throws Exception {

    }

    @Test
    public void testSelectPaging() throws Exception {

    }

}