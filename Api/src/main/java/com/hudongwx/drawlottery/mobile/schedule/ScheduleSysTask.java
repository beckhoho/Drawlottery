package com.hudongwx.drawlottery.mobile.schedule;

import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.mappers.*;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityService;
import com.hudongwx.drawlottery.mobile.service.commodity.IGenerateService;
import com.hudongwx.drawlottery.mobile.utils.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
    OrdersMapper mapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedPacketsMapper redMapper;
    @Autowired
    OrdersCommoditysMapper orderMapper;
    @Autowired
    CommoditysMapper comMapper;
    @Autowired
    LuckCodesMapper codesMapper;
    @Resource
    ICommodityService commodityService;
    @Autowired
    LuckCodeTemplateMapper templateMapper;
    @Autowired
    UserCodesHistoryMapper userHistoryMapper;
    @Autowired
    CommodityTemplateMapper templeMapper;
    @Autowired
    CommodityHistoryMapper historyMapper;
    @Autowired
    LotteryInfoMapper lotteryInfoMapper;
    @Autowired
    CommodityMapper commMapper;
    @Autowired
    CommoditysMapper commsMapper;

    @Resource
    private IGenerateService generateService;

    @Scheduled(fixedDelay = 10000)
    public void updateState() {
        //.... 查询数据库查看是否到修改状态的时候
        List<Commoditys> commsList = commsMapper.selectUnLotteryComm();
        for (Commoditys cmd : commsList) {
            //计算结束时间
            long endTime = cmd.getSellOutTime() + Settings.LOTTERY_ANNOUNCE_TIME_INTERVAL;
            endTime = System.currentTimeMillis() - endTime;
            endTime = endTime / 1000;//换算成秒
            if (endTime >= 0) {//开奖时间到
                commsMapper.updateCommState(cmd.getId(), 1);
            }
        }
    }

    /**
     * 保证同一个模板有三期商品
     */
    @Scheduled(cron = "0/5 * *  * * ?")
    public void generateRound() {
        generateService.keepRound(3);
    }


}
