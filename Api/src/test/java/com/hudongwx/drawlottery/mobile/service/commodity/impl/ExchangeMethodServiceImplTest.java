package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.ExpressDelivery;
import com.hudongwx.drawlottery.mobile.service.commodity.IExchangeMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/11 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/11 22:03　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
public class ExchangeMethodServiceImplTest extends TestBaseMapper {
    @Autowired
    IExchangeMethodService service;
    @Test
    public void testExchangeToGold() throws Exception {

    }

    @Test
    public void testExchangeToCash() throws Exception {

    }

    @Test
    public void testExchangeToExpress() throws Exception {

    }

    @Test
    public void testExchangeToLocale() throws Exception {
        boolean b = service.exchangeToLocale(5l, 1l, 1l);
        Assert.assertTrue(b);
    }

    @Test
    public void testSelectUserRechargeCardPrize() throws Exception {

    }

    @Test
    public void testSelectUserRechargeCardExchangeProcess() throws Exception {

    }

    @Test
    public void testDemo2() throws Exception {

    }

}