package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.LotteryInfo;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityHistoryService;
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
 * @version 1.0, 2017/1/21 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/21 19:21　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
public class CommodityHistoryServiceImplTest extends TestBaseMapper {

    @Autowired
    ICommodityHistoryService service;
    @Test
    public void testSelectThePastAnnouncedCommList() throws Exception {
//        List<Map> maps = service.selectThePastAnnouncedCommList(11l);
//        System.out.println(maps);
    }

    @Test
    public void testSelectLotteryInfo() throws Exception {
        LotteryInfo info = service.selectLotteryInfo(11l);
    }

}