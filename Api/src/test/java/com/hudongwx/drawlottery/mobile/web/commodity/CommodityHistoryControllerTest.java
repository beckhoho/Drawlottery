package com.hudongwx.drawlottery.mobile.web.commodity;

import com.hudongwx.drawlottery.mobile.TestBaseWeb;
import com.hudongwx.drawlottery.mobile.entitys.LotteryInfo;
import com.hudongwx.drawlottery.mobile.web.Commodity.CommodityHistoryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by 11 on 2017/1/18.
 */
public class CommodityHistoryControllerTest extends TestBaseWeb {
    @Test
    public void testQueryLotteryInfo() throws Exception {
        LotteryInfo lotteryInfo = commodityHistoryController.queryLotteryInfo(1L);
        assertNotNull(lotteryInfo);
    }

    @Autowired
    CommodityHistoryController commodityHistoryController;

    @Override
    public Object getController() {
        return commodityHistoryController;
    }
}