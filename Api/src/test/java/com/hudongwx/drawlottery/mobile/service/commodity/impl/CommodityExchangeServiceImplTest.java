package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityExchangeService;
import com.hudongwx.drawlottery.mobile.service.commodity.IExchangeWayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/12 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/12 20:06　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
public class CommodityExchangeServiceImplTest extends TestBaseMapper {

    @Autowired
    ICommodityExchangeService service;
    @Autowired
    IExchangeWayService exService;
    @Test
    public void testSelectByCommodityId() throws Exception {
        List<Map<String, Object>> list = service.selectByCommodityId(26l);
        for(Map<String, Object> map : list){
            System.out.println(map.get("id"));
            System.out.println(map.get("exchangeWay"));
            System.out.println(map.get("url"));
            System.out.println(map.get("quota"));
        }
    }

}