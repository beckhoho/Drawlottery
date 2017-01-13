package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.service.commodity.IHotSearchService;
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
 * @version 1.0, 2017/1/13 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/13 23:33　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
public class HotSearchServiceImplTest extends TestBaseMapper {

    @Autowired
    IHotSearchService service;
    @Test
    public void testAddHotSearch() throws Exception {
        boolean b = service.addHotSearch("请问");
        Assert.assertTrue(b);
    }

    @Test
    public void testQueryRecommend() throws Exception {

    }

}