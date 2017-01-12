package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2017/1/12 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2017/1/12 <br/>
 * <p>
 * 用户收货地址
 * <p>
 * @email 294786949@qq.com
 */
public class CommodityExchangeServiceImplTest extends TestBaseMapper {
    @Autowired
    ICommodityExchangeService service;

    @Test
    public void testAddExchange() throws Exception {

    }

    @Test
    public void testDeleteExchange() throws Exception {

    }

    @Test
    public void testUpdateExchange() throws Exception {

    }

    @Test
    public void testSelectByCommodityId() throws Exception {
        List<Map<String, Object>> mapList = service.selectByCommodityId(23L);
        System.out.println(mapList.size());

    }

}