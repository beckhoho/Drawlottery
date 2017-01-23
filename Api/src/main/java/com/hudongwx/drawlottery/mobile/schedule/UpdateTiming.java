package com.hudongwx.drawlottery.mobile.schedule;

import com.hudongwx.drawlottery.mobile.entitys.LotteryInfo;
import com.hudongwx.drawlottery.mobile.mappers.LotteryInfoMapper;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/22 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/22 2:17　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
public class UpdateTiming extends DelayTask{

    LotteryInfoMapper lotteryInfoMapper;

    LotteryInfo lotteryInfo;

    public UpdateTiming(LotteryInfoMapper lotteryInfoMapper,LotteryInfo lotteryInf){
        this.lotteryInfo = lotteryInf;
        this.lotteryInfoMapper=lotteryInfoMapper;
    }

    @Override
    public void todo() {
//        int i = lotteryInfoMapper.insertSelective(lotteryInfo);
        System.out.println("开奖信息插入数据库情况：---------->");
    }
}
