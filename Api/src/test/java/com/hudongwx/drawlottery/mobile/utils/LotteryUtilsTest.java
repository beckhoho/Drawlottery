package com.hudongwx.drawlottery.mobile.utils;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Commodity;
import com.hudongwx.drawlottery.mobile.entitys.LotteryInfo;
import com.hudongwx.drawlottery.mobile.entitys.LuckCodes;
import com.hudongwx.drawlottery.mobile.mappers.LuckCodesMapper;
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
    @Autowired
    LuckCodesMapper CodesMapper;
    @Test
    public void testRaffle() throws Exception {
        Commodity commodity = new Commodity();
        commodity.setId(58L);
        commodity.setBuyCurrentNumber(1000);
        LotteryInfo lotteryInfo = LotteryUtils.raffle(luckCodesMapper,userMapper,commodity);
        Long lotteryId = lotteryInfo.getLotteryId();
        LuckCodes luckCodes = CodesMapper.selectByCode(lotteryId+"");
        Assert.assertNotNull(luckCodes,"幸运号为空！！！");
    }

}