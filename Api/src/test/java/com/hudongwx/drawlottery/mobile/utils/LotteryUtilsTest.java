package com.hudongwx.drawlottery.mobile.utils;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Commodity;
import com.hudongwx.drawlottery.mobile.entitys.LotteryInfo;
import com.hudongwx.drawlottery.mobile.mappers.UserLuckCodesMapper;
import com.hudongwx.drawlottery.mobile.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by 11 on 2017/1/16.
 */
public class LotteryUtilsTest extends TestBaseMapper {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserLuckCodesMapper luckCodesMapper;
    @Test
    public void testRaffle() throws Exception {
        Commodity commodity = new Commodity();
        commodity.setId(50L);
        commodity.setBuyCurrentNumber(4444);
        LotteryInfo lotteryInfo = LotteryUtils.raffle(luckCodesMapper,userMapper,commodity);
        assertNotNull(lotteryInfo);
    }

}