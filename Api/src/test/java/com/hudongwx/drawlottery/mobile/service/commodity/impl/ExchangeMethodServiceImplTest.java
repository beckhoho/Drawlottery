package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.service.commodity.IExchangeMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/13 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/13 16:24　<br/>
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
        boolean b = service.orTrue(10000l, 45l);
        Assert.assertTrue(b);
    }

    @Test
    public void testExchangeToCash() throws Exception {

    }

    @Test
    public void testExchangeToLocale() throws Exception {

    }

    @Test
    public void testExchangeToExpress() throws Exception {

    }

    @Test
    public void testSelectUserRechargeCardPrize() throws Exception {

    }

    @Test
    public void testOrTrue() throws Exception {

    }

    @Test
    public void testSelectUserRechargeCardExchangeProcess() throws Exception {
        Map<String, Object> map = service.selectUserRechargeCardExchangeProcess(10000l, 45l);
        System.out.println(map.get("commodityName"));
    }

    @Test
    public void testDemo2() throws Exception {

    }

}