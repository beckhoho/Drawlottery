package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.CommodityHistory;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/12 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/12 9:33　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
public class CommodityHistoryServiceImplTest extends TestBaseMapper {
    @Test
    public void testSelectThePastAnnouncedCommList() throws Exception {
        service.selectThePastAnnouncedCommList(43L);
    }

    @Autowired
    ICommodityHistoryService service;
    @Test
    public void testAddCommodHistory() throws Exception {

    }

    @Test
    public void testSelectHistoryCommod() throws Exception {
        CommodityHistory history = service.selectHistoryCommod();
        System.out.println(history.getCommodityName());
        Assert.assertNotNull(history);
    }

}