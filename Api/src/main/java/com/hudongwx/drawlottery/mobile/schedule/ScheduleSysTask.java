package com.hudongwx.drawlottery.mobile.schedule;

import com.hudongwx.drawlottery.mobile.entitys.Commodity;
import com.hudongwx.drawlottery.mobile.mappers.CommodityMapper;
import com.hudongwx.drawlottery.mobile.mappers.LotteryInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2017/1/22 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2017/1/22 <br/>
 * <p>
 * 用户收货地址
 * <p>
 * @email 294786949@qq.com
 */
@Component
public class ScheduleSysTask {

    @Autowired
    LotteryInfoMapper lotteryInfoMapper;
    @Autowired
    CommodityMapper commMapper;

    @Scheduled(fixedDelay = 10)
    public void updateState() {
        //...
        //....

        //.... 查询数据库查看是否到修改状态的时候
        List<Commodity> comm = commMapper.selectUnLotteryComm();
//        ServiceUtils.getResidualLotteryMinute(comm);


//        DelayTask.execute(new UpdateTiming(lotteryInfoMapper,),5);

    }

}
